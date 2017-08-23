package com.pangpang6.utils.ribbon;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangjg on 2017/6/30.
 */
public class RibbonDeamon {
    private static final Logger logger = LoggerFactory.getLogger(RibbonDeamon.class);
    private String serviceName;
    private Set<String> serviceNames = Sets.newHashSet();
    private String servicePort;
    private String configsWsUrlPrefix;

    public void startConfigsSync() throws IOException {
        if (serviceName != null) {
            serviceNames.add(serviceName);
        }
        Ribbons.syncRibbonConfigs(serviceNames, configsWsUrlPrefix + "/rest/ribbons/loadRibbonConfigsV2");
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Ribbons.syncRibbonConfigs(serviceNames, configsWsUrlPrefix + "/rest/ribbons/loadRibbonConfigsV2");
                } catch (Exception ex) {
                    logger.error("exec Ribbons.syncRibbonConfigs faut.", ex);
                }
            }
        }, 3, 3, TimeUnit.SECONDS);
    }

    public void startServiceHeartBeat() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Ribbons.heartBeat(serviceName, servicePort, configsWsUrlPrefix + "/rest/ribbons/heartBeat");
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Set<String> getServiceNames() {
        return serviceNames;
    }

    public void setServiceNames(Set<String> serviceNames) {
        this.serviceNames = serviceNames;
    }

    public String getServicePort() {
        return servicePort;
    }

    public void setServicePort(String servicePort) {
        this.servicePort = servicePort;
    }

    public String getConfigsWsUrlPrefix() {
        return configsWsUrlPrefix;
    }

    public void setConfigsWsUrlPrefix(String configsWsUrlPrefix) {
        this.configsWsUrlPrefix = configsWsUrlPrefix;
    }
}
