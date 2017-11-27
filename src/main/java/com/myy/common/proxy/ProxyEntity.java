package com.myy.common.proxy;

import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * @author caozhaorui
 */
@EqualsAndHashCode
public class ProxyEntity {

    private String ip;
    private int port;

    public ProxyEntity() {
    }

    public ProxyEntity(String ip, int port) throws IllegalAccessException {
        this.setIp(ip);
        this.setPort(port);
    }

    public void setIp(String ip) throws IllegalAccessException {
        if (StringUtils.isBlank(ip))
            throw new IllegalAccessException();
        this.ip = ip;
    }

    public void setPort(int port) throws IllegalAccessException {
        if (port < 0 || port > 65535)
            throw new IllegalAccessException();
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "ProxyEntity{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
