 package zookeeper;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import org.apache.zookeeper.AsyncCallback.StringCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

/**
 * @author 胡鹏
 * @date 2020/09/13
 */
public class ZookeeperClient implements Watcher{
    
    /** 单体ip */
    private static final String SINGLE_IP = "127.0.0.1:2181";
    /** 集群ip */
    private static final String CLUSTER_IP = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2182";
    
    // zookeeper 连接
    @Test
    public void connect() throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper(SINGLE_IP, 5000, new ZookeeperClient());
        System.err.println("客户端准备连接zookeeper服务器。。。。");
        System.err.println("连接状态为：" + zooKeeper.getState());
        System.err.println("连接状态为：" + zooKeeper.getState());
    }
    
    
    // 会话重连
    @Test
    public void sessionReconnection() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(CLUSTER_IP, 5000, new ZookeeperClient());
        System.err.println("客户端准备连接zookeeper服务器。。。。");
        System.err.println("连接状态为：" + zooKeeper.getState());
        Thread.sleep(2000);
        System.err.println("连接状态为：" + zooKeeper.getState());
        Thread.sleep(2000);
        
        System.err.println("开始会话重连。。。");
        
        long sessionId = zooKeeper.getSessionId();
        byte[] sessionPasswd = zooKeeper.getSessionPasswd();
        
        ZooKeeper zooKeeper2 = new ZooKeeper(CLUSTER_IP, 5000, new ZookeeperClient(),
                sessionId, sessionPasswd);
        System.err.println("重连状态为：" + zooKeeper2.getState());
        Thread.sleep(2000);
        System.err.println("重连状态为：" + zooKeeper2.getState());
    }
    
    // 创建节点
    @Test
    @SuppressWarnings("all")
    public void createNode() throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(CLUSTER_IP, 5000, new ZookeeperClient());
        // 同步方式创建零时节点
        String syncCrateResult = zooKeeper.create(
            "/syncCreateNode", 
            "{\"key\":\"value\"}".getBytes(), 
            ZooDefs.Ids.OPEN_ACL_UNSAFE, 
            CreateMode.EPHEMERAL);
        
        
        // 异步方式创建零时节点
        zooKeeper.create(
            "/asyncCreateNode", 
            "{\"key\":\"value\"}".getBytes(), 
            ZooDefs.Ids.OPEN_ACL_UNSAFE,
            CreateMode.EPHEMERAL,
            
            new StringCallback() {
                
                @Override
                public void processResult(int rc, String path, Object ctx, String name) {
                    System.err.println(rc);
                    System.err.println(path);
                    System.err.println(ctx);
                    System.err.println(name);
                    
                }
            }, 
            "回调字符串");
        Thread.sleep(5000);
    }
    
    
    @Test
    @SuppressWarnings("all")
    public void test() throws IOException, KeeperException, InterruptedException {
        Stat stat = new Stat();
        ZooKeeper zooKeeper = new ZooKeeper(SINGLE_IP, 2000, new ZookeeperClient());
        String path = "/hp";
        String data = "data";
        // 创建数据
        System.err.println("创建数据" + path);
        String createResult = zooKeeper.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        // 判断数据是否存在
        Stat exists = zooKeeper.exists(path, false);
        // 设置数据
        zooKeeper.setData(path, "dataModify".getBytes(), exists.getVersion());
        // 获取数据
        byte[] returnData = zooKeeper.getData("/hp", false, stat);
        System.err.println(MessageFormat.format("获取数据{0}的返回值为{1}", path, new String(returnData)));
        // 获取子节点数据
        List<String> children = zooKeeper.getChildren(path, false);
        System.err.println(MessageFormat.format("{0}的子节点数据为{1}", path, children));
        // 删除数据
        zooKeeper.delete(path, stat.getVersion());
        System.err.println(MessageFormat.format("删除数据{0}， 数删除成功", path));
    }
    

    @Override
    public void process(WatchedEvent event) {
        System.err.println("接受到的watch通知为：" + event);
    }
}
