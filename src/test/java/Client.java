import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 胡鹏
 * @date 2020/08/10
 */
public class Client {



    // 创建节点并每5秒钟更新节点数
    @Test
    public void updateServiceData() throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
        String path = client.create().orSetData().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/services/service", "hp".getBytes());
        while (true) {
            long l = System.currentTimeMillis();
            Stat stat = client.setData().forPath(path, String.valueOf(l).getBytes());
            System.out.println(stat);
            Thread.sleep(5000);
        }
    }



    // 监听节点数据的变化
    @Test
    public void watchServiceData() throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
        NodeCache watcher = new NodeCache(client, "/services/service");
        watcher.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                ChildData currentData = watcher.getCurrentData();
                System.err.println(MessageFormat.format("节点{0}数据发生变化，变化之后的值为：{1}", currentData.getPath(), new String(currentData.getData(), "GBK")));
                System.err.println("currentData" + currentData);
            }
        });
        watcher.start();

        Thread.sleep(Integer.MAX_VALUE);
    }
        
}
