package com.pangpang6.utils.ribbon;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pangpang6.utils.MyJSONMapper;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 心跳和配置查询服务
 * Created by jiangjg on 2017/6/30.
 */
public class Ribbons {
    private static final Logger logger = LoggerFactory.getLogger(Ribbons.class);
    private static Map<String, Map<RibbonNodeGroup, List<String>>> ribbonNodesCache = Maps.newConcurrentMap();

    public static Map<RibbonNodeGroup, List<String>> getRibbonNodes(String remoteWs) {
        return ribbonNodesCache.get(remoteWs);
    }

    /**
     * 获取可用的Ribbon节点
     */
    public static void syncRibbonConfigs(Set<String> serviceNames, String configsWsURL) throws IOException {
        for (String serviceName : serviceNames) {
            String body = Request.Post(configsWsURL)
                    .bodyForm(Form.form()
                            .add("serviceName", serviceName)
                            .build(), Charset.forName("UTF-8"))
                    .connectTimeout(1000).socketTimeout(10000)
                    .execute().returnContent().asString();
            Map<String, List<Map>> gNodes = MyJSONMapper.nonDefaultMapper().parseObject(body, Map.class);
            if (gNodes != null) {
                Map<RibbonNodeGroup, List<String>> gNodeIpList = Maps.newHashMap();
                for (String gs : gNodes.keySet()) {
                    RibbonNodeGroup group = RibbonNodeGroup.parse(gs);
                    if (group != null) {
                        List<String> ipList = Lists.newArrayList();
                        for (Map gn : gNodes.get(gs)) {
                            RibbonNode node = MyJSONMapper.nonDefaultMapper().parseObject(MyJSONMapper.nonDefaultMapper().toJSONString(gn), RibbonNode.class);
                            if (node.getNodeStatus() == RibbonNodeStatus.ENABLED
                                    // 没有心跳历史 或 3S以内有心跳 都算是健康
                                    && (node.getHeartBeatLag() == null || node.getHeartBeatLag() < 3 * 1000L)) {
                                ipList.add(node.getNodeKey());
                            }
                        }
                        gNodeIpList.put(group, ipList);
                    }
                }
                ribbonNodesCache.put(serviceName, gNodeIpList);
            }
        }
    }

    /**
     * 服务提供方的心跳数据
     */
    public static void heartBeat(String serviceName, String servicePort, String configsWsURL) {
        try {
            StatusLine status = Request.Post(configsWsURL)
                    .bodyForm(Form.form()
                            .add("serviceName", serviceName)
                            .add("servicePort", servicePort)
                            .build(), Charset.forName("UTF-8"))
                    .connectTimeout(1000).socketTimeout(10000)
                    .execute().returnResponse().getStatusLine();
            if (status.getStatusCode() != 200) {
                logger.error("exec heartBeat faut. serviceName: " + serviceName
                        + ", httpCode: " + status.getStatusCode() + ", reason: " + status.getReasonPhrase());
            }
        } catch (Exception ex) {
            logger.error("exec heartBeat faut.", ex);
        }
    }
}
