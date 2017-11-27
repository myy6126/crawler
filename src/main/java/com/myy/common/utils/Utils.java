package com.myy.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author hao.wang
 * @since 2016/7/21 19:57
 */
public class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);


    public static String get(CloseableHttpClient httpClient, HttpClientContext context, String url) {
        String result;
        try {
            HttpResponse response = httpClient.execute(new HttpGet(url), context);
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            LOGGER.warn("squarefoot hdb crawler error,execute request error, url={}", url);
            return StringUtils.EMPTY;
        }
        LOGGER.info("squarefoot get start url={}", url);
        return result;
    }

}
