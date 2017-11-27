/**
 *
 */
package com.myy.common.proxy;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

/**
 * @author zhaorui.cao
 */
public class ProxyService {

    public final static Logger logger = LoggerFactory.getLogger(ProxyService.class);
    private List<ProxyEntity> proxyList = Lists.newArrayList();
    private final static ProxyService instance = new ProxyService();

    private ProxyService() {

    }

    public static ProxyService getInstance() {
        return instance;
    }

    public ProxyEntity getProxy() {
        ProxyEntity proxyEntity = null;
        if (!proxyList.isEmpty()) {
            proxyEntity = proxyList.get(new Random().nextInt(proxyList.size()));
        }
        return proxyEntity;
    }

    public List<ProxyEntity> getProxyList() {
        return proxyList;
    }

    public void setProxyList(List<ProxyEntity> proxyList) {
        this.proxyList = proxyList;
    }

    public void addProxy(ProxyEntity proxyEntity) {
        this.proxyList.add(proxyEntity);
    }

    public void remove(ProxyEntity proxyEntity) {
        this.proxyList.remove(proxyEntity);
    }
}
