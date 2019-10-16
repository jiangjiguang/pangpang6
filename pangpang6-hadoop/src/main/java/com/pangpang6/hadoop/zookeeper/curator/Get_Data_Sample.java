package com.pangpang6.hadoop.zookeeper.curator;
import com.pangpang6.utils.MyJSONMapper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

//使用Curator获取数据内容
public class Get_Data_Sample {

    static String path = "/zk-book";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(Constants.ips)
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();
    public static void main(String[] args) throws Exception {
        client.start();
        Object a = client.create()
              .creatingParentsIfNeeded()
              .withMode(CreateMode.EPHEMERAL)
              .forPath(path, "init".getBytes());
        System.out.println(a);
        Stat stat = new Stat();
        System.out.println(new String(client.getData().storingStatIn(stat).forPath(path)));
        System.out.println("--------");
        System.out.println(MyJSONMapper.nonDefaultMapper().toJSONString(stat));
    }
}