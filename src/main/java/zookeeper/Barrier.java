 package zookeeper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

/**
 * zookeeper官网教程，实现CyclicBarrier
 * @author 胡鹏
 * @date 2020/09/16
 */
public class Barrier extends SyncPrimitive {
    
    int size;
    String name;

    Barrier(String address, String root, int size) {
        super(address);
        this.root = root;
        this.size = size;
        // Create barrier node
        if (zk != null) {
            try {
                Stat s = zk.exists(root, false);
                if (s == null) {
                    zk.create(root, new byte[0], Ids.OPEN_ACL_UNSAFE,
                            CreateMode.PERSISTENT);
                }
            } catch (KeeperException e) {
                System.out
                        .println("Keeper exception when instantiating queue: "
                                + e.toString());
            } catch (InterruptedException e) {
                System.out.println("Interrupted exception");
            }
        }

        // My node name
        try {
            name = new String(InetAddress.getLocalHost().getCanonicalHostName().toString());
        } catch (UnknownHostException e) {
            System.out.println(e.toString());
        }
    }
    
    
    boolean enter() throws KeeperException, InterruptedException{
        zk.create(root + "/" + name, new byte[0], Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);
        while (true) {
            synchronized (mutex) {
                List<String> list = zk.getChildren(root, true);
                if (list.size() < size) {
                    mutex.wait();
                } else {
                    return true;
                }
            }
        }
    }
    
    
    boolean leave() throws KeeperException, InterruptedException {
        zk.delete(root + "/" + name, 0);
        while (true) {
            synchronized (mutex) {
                List<String> list = zk.getChildren(root, true);
                    if (list.size() > 0) {
                        mutex.wait();
                    } else {
                        return true;
                    }
                }
            }
    }
    
    public static void main(String[] args) {

        Barrier b = new Barrier("127.0.0.1:2181", "/b1", 5);
        try{
            boolean flag = b.enter();
            System.out.println("Entered barrier: " + 5);
            if(!flag) System.out.println("Error when entering the barrier");
        } catch (KeeperException e){
        } catch (InterruptedException e){
        }

        // Generate random integer
        Random rand = new Random();
        int r = rand.nextInt(100);
        // Loop for rand iterations
        for (int i = 0; i < r; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        try{
            b.leave();
        } catch (KeeperException e){

        } catch (InterruptedException e){

        }
        System.out.println("Left barrier");
    
    }
    
    
  
    
}
