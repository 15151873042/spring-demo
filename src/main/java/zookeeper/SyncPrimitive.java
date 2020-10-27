 package zookeeper;

import java.io.IOException;
import java.text.MessageFormat;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author 胡鹏
 * @date 2020/09/16
 */
public class SyncPrimitive implements Watcher {
    
    static ZooKeeper zk = null;
    static Integer mutex;

    String root;

    SyncPrimitive(String address) {
        if(zk == null){
            try {
                System.out.println("Starting ZK:");
                zk = new ZooKeeper(address, 3000, this);
                mutex = new Integer(-1);
                System.out.println("Finished starting ZK: " + zk);
            } catch (IOException e) {
                e.printStackTrace();
                zk = null;
            }
        }
    }

    @Override
    synchronized public void process(WatchedEvent event) {
        System.err.println(MessageFormat.format("接收到event:{0}", event));
        synchronized (mutex) {
            mutex.notify();
        }
    }
}
