package com.xia.demo.registory;

import com.xia.demo.common.Constants;
import com.xia.demo.exception.ZkConnectException;
import com.xia.demo.model.ProviderInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author xiafb
 * @date Created in 2019/7/24 15:15
 * description
 * modified By
 * version
 */
@Slf4j
public class ServiceDiscovery {

    private volatile List<ProviderInfo> dataList = new ArrayList<>();

    public ServiceDiscovery(String registerAddress) throws ZkConnectException {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(registerAddress, 2000, watchedEvent -> {
                log.info("consumer connect zk success...");
            });
            watchNode(zooKeeper);
        } catch (Exception e) {
            throw new ZkConnectException("connect to zk exception," + e.getMessage(), e.getCause());
        }
    }

    /**
     * 监控zk节点
     *
     * @param zk
     */
    public void watchNode(final ZooKeeper zk) {
        try {
            List<String> nodeList = zk.getChildren(Constants.ZK_ROOT_DIR, event -> {
                //节点改变有服务上线或者下线
                if (Watcher.Event.EventType.NodeChildrenChanged.equals(event.getType())) {
                    watchNode(zk);
                }
            });
            ArrayList<ProviderInfo> providerInfos = new ArrayList<>();
            //循环子节点获取服务名称
            for (String node : nodeList) {
                byte[] data = zk.getData(Constants.ZK_ROOT_DIR + "/" + node, false, null);
                String[] providerInfo = new String(data).split(",");
                if (providerInfo.length == 2) {
                    providerInfos.add(new ProviderInfo(providerInfo[0], providerInfo[1]));
                }
            }
            this.dataList = providerInfos;
            log.info("获取服务端列表成功：{}", this.dataList);
        } catch (Exception ex) {
            log.error("watch error", ex);
        }
    }

    /**
     * 获取一个服务提供者
     *
     * @param providerName
     * @return
     */
    public ProviderInfo discover(String providerName) {
        if (dataList.isEmpty()) {
            log.info("暂无服务提供者");
            return null;
        }
        List<ProviderInfo> collect = dataList.stream().
                filter(one -> providerName.equals(one.getName())).
                collect(Collectors.toList());
        if (collect.isEmpty()) {
            return null;
        }
        return collect.get(ThreadLocalRandom.current().nextInt(collect.size()));
    }
}
