package com.pangpang6.books.hadoop.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.AuthenticationProvider;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
import org.apache.zookeeper.server.auth.IPAuthenticationProvider;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangjiguang on 2018/2/12.
 */
public class ZKTest {

    private static final String zk = "node1:2181,node2:2181,node3:2181";

    private ZKConstruct zkConstruct = new ZKConstruct();
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    ZooKeeper zooKeeper = null;
    @Test
    public void permissionTest() throws Exception {
        String path = "/zk-book-5";

        zooKeeper = new ZooKeeper(zk, 5000, zkConstruct);
        zooKeeper.addAuthInfo("digest", "foo:true".getBytes());
        /*
        List<ACL> list = new ArrayList<>();
        ACL acl = new ACL();
        acl.setPerms(ZooDefs.Perms.ALL);
        Id id = new Id();
        id.setScheme("digest");
        id.setId("foo:true");
        acl.setId(id);
        list.add(acl);
        zooKeeper.setACL(path, list, -1);
        */
        zooKeeper.create(path, "222".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void existTest() throws Exception {
        zooKeeper = new ZooKeeper(zk, 5000, zkConstruct);
        System.out.println(zooKeeper.getState());
        countDownLatch.await();
        String path = "/zk-book";

        zooKeeper.exists(path, true);
        zooKeeper.setData(path, "123".getBytes(), -1);
        zooKeeper.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.delete(path + "/c1", -1);
        zooKeeper.delete(path, -1);

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void createSession() throws Exception {
        zooKeeper = new ZooKeeper(zk, 5000, zkConstruct);
        System.out.println(zooKeeper.getState());
        countDownLatch.await();

        //String path1 = zooKeeper.create("/zk-test-e2", "aaaaa".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        //System.out.println("success create znode : " + path1);
        zooKeeper.create("/zk-test-e2", "aaaaa".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new MyStringCallback(), "000000");

        System.out.println("finished");
        TimeUnit.SECONDS.sleep(5);
    }


    @Test
    public void getDataTest() throws Exception {
        String path = "/zk-book";
        zooKeeper = new ZooKeeper(zk, 5000, zkConstruct);
        countDownLatch.await();
        Stat stat = zooKeeper.exists(path, false);
        if (stat == null) {
            zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        List<String> childrenList = zooKeeper.getChildren(path, true);

        zooKeeper.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        zooKeeper.create(path + "/c2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        zooKeeper.create(path + "/c3", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        Thread.sleep(Integer.MAX_VALUE);
    }


    public class ZKConstruct implements Watcher {
        @Override
        public void process(WatchedEvent event) {
            if (KeeperState.SyncConnected == event.getState()) {
                if (Event.EventType.None == event.getType() && null == event.getPath()) {
                    countDownLatch.countDown();
                }
                if (event.getType() == Event.EventType.NodeChildrenChanged) {
                    try {
                        System.out.println("ReGet Child:" + zooKeeper.getChildren(event.getPath(), true));
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public class MyStringCallback implements AsyncCallback.StringCallback {

        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            System.out.println("--------------");
            System.out.println(String.format("rc=%s,path=%s,%s,%s", rc, path, ctx, name));
        }
    }
}
