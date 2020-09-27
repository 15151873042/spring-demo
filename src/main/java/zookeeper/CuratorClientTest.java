 package zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

 /**
 * @author 胡鹏
 * @date 2020/09/08
 */
public class CuratorClientTest {

    private static final String ZK_PATH = "/hupeng";
    
    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
        System.out.println("连接成功");
        
        // 2.Client API test
        // 2.1 Create node
        String data1 = "hello";
        print("create", ZK_PATH, data1);
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(Ids.OPEN_ACL_UNSAFE)
                .forPath(ZK_PATH, data1.getBytes());

        // 2.2 Get node and data
        print("ls", "/");
        print(client.getChildren().forPath("/"));
        print("get", ZK_PATH);
        print(client.getData().forPath(ZK_PATH));

        // 2.3 Modify data
        String data2 = "world";
        print("set", ZK_PATH, data2);
        client.setData()
//                .withVersion(100) // 不传version也可以
                .forPath(ZK_PATH, data2.getBytes());
        print("get", ZK_PATH);
        print(client.getData().forPath(ZK_PATH));

        // 2.4 Remove node
        print("delete", ZK_PATH);
        client.delete()
                .guaranteed()
                .deletingChildrenIfNeeded() // 如果有子节点也一起删除
//                .withVersion(0)
                .forPath(ZK_PATH);
        print("ls", "/");
        print(client.getChildren().forPath("/"));
    }
    
    private static void print(String... cmds) {
        StringBuilder text = new StringBuilder("$ ");
        for (String cmd : cmds) {
            text.append(cmd).append(" ");
        }
        System.out.println(text.toString());
    }
    
    
    private static void print(Object result) {
        System.out.println(
                result instanceof byte[]
                    ? new String((byte[]) result)
                        : result);
    }

    @Test
    public void leaderLatchTest() throws Exception {

        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));
        curatorFramework.start();
        LeaderLatch leaderLatch = new LeaderLatch(curatorFramework, "/leaderLatchTest");
        leaderLatch.start();
        boolean b = leaderLatch.hasLeadership();
        leaderLatch.await(2, TimeUnit.SECONDS);
        boolean b1 = leaderLatch.hasLeadership();
        leaderLatch.close();
        System.out.println("结束");
    }
}
