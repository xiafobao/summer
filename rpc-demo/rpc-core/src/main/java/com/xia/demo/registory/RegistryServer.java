package com.xia.demo.registory;

import com.xia.demo.common.Constants;
import com.xia.demo.exception.ZkConnectException;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import static org.apache.zookeeper.CreateMode.EPHEMERAL_SEQUENTIAL;

@Slf4j
public class RegistryServer {

    /**
     * zk的地址
     */
    private String addr;

    /**
     * 超时时间
     */
    private int timeout;

    /**
     * 服务名
     */
    private String serverName;

    private String host;

    private int port;

    public RegistryServer(String addr,
                          int timeout,
                          String serverName,
                          String host,
                          int port) {
        this.addr = addr;
        this.timeout = timeout;
        this.serverName = serverName;
        this.host = host;
        this.port = port;
    }

    public void registry() throws ZkConnectException {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(addr, timeout, watchedEvent -> {
                log.info("register zk connect success...");
            });
            if (zooKeeper.exists(Constants.ZK_ROOT_DIR, false) == null) {
                zooKeeper.create(Constants.ZK_ROOT_DIR, Constants.ZK_ROOT_DIR.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            zooKeeper.create(Constants.ZK_ROOT_DIR + "/" + serverName, (serverName + "," + host + ":" + port).getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, EPHEMERAL_SEQUENTIAL);
            log.info("provider register success {}", serverName);
        } catch (Exception e) {
            throw new ZkConnectException("register to zk exception," + e.getMessage(), e.getCause());
        }
    }
}