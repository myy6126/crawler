package com.myy.crawler.wrapper;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

public @Getter
@Setter
@ToString
class WrapperRequiredKeys {
    private String wrapperId;
    private String className;

    private String source;

    private String queryId;

    private Map<String, String> param = Maps.newHashMap();

    public static WrapperRequiredKeys create(String wrapperId, String className, String source) {
        WrapperRequiredKeys requiredKeys = new WrapperRequiredKeys();
        requiredKeys.setWrapperId(wrapperId);
        requiredKeys.setClassName(className);
        requiredKeys.setSource(source);
        return requiredKeys;
    }
}
