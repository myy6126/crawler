package com.myy.crawler;

import com.google.common.collect.Lists;
import com.myy.common.log.LogConstant;
import com.myy.common.proxy.ProxyEntity;
import com.myy.common.proxy.ProxyService;
import com.myy.common.utils.Configs;
import com.myy.crawler.bean.ProcessResult;
import com.myy.crawler.http.HttpRequestUtils;
import com.myy.crawler.wrapper.WrapperRequiredKeys;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class CommonWrapper {
    protected final static Logger log = LoggerFactory.getLogger(LogConstant.WRAPPER_GRAB);

    protected HttpClient hc;

    protected String charset;

    protected String wrapperId;

    protected static final int TIME_OUT = Configs.getTimeOut();

    public CommonWrapper(String charset, String wrapperId, boolean isProxy) {
        this.charset = charset;
        this.wrapperId = wrapperId;
        this.hc = getHttpClient(isProxy);
    }

    private HttpClient getHttpClient(boolean isProxy) {
        HttpClientBuilder builder = getHttpClientBuilder(isProxy);
        return builder.build();
    }

    protected HttpClientBuilder getHttpClientBuilder(boolean isProxy) {
        HttpClientBuilder builder = HttpClientBuilder.create();
        Collection<Header> headers = getDefaultHeaders();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(TIME_OUT)
                .setSocketTimeout(TIME_OUT).setCookieSpec(CookieSpecs.DEFAULT).build();
        builder.setDefaultHeaders(headers).setDefaultRequestConfig(requestConfig);
        if (isProxy) {
            ProxyEntity proxy = ProxyService.getInstance().getProxy();
            if (Objects.nonNull(proxy)) {
                builder.setProxy(new HttpHost(proxy.getIp(), proxy.getPort(), HttpHost.DEFAULT_SCHEME_NAME));
            }
        }
        return builder;
    }

    private Collection<Header> getDefaultHeaders() {
        Collection<Header> headers = Lists.newArrayList();
        headers.add(new BasicHeader("Accept", "*/*"));
        headers.add(new BasicHeader("Accept-Language", "zh-cn"));
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36"));
        headers.add(new BasicHeader("UA-CPU", "x86"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));
        headers.add(new BasicHeader("Connection", "close"));
        return headers;
    }

    public HttpClient getHttpClient(boolean isProxy, HttpClientBuilder builder) {
        if (isProxy) {
            ProxyEntity proxy = ProxyService.getInstance().getProxy();
            if (Objects.nonNull(proxy)) {
                builder.setProxy(new HttpHost(proxy.getIp(), proxy.getPort(), HttpHost.DEFAULT_SCHEME_NAME));
            }
        }
        return builder.build();
    }

    public HttpClient getHttpClient(HttpClientBuilder builder) {
        return builder.build();
    }

    protected String executeHttp(String url, Map<String, String> params) {
        return executeHttp(url, Collections.emptyMap(), params);
    }

    protected String executeHttp(String url, Map<String, String> headers, Map<String, String> params) {
        List<NameValuePair> formParams = HttpRequestUtils.buildNameValuePair(params);
        if (CollectionUtils.isEmpty(formParams)) {
            HttpGet get = new HttpGet(url);
            addHeaders(get, headers);
            return HttpRequestUtils.invoke(get, hc);
        } else {
            HttpPost httpPost = new HttpPost(url);
            addHeaders(httpPost, headers);
            HttpEntity entity;
            try {
                entity = new UrlEncodedFormEntity(formParams, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.error("[HttpClient] utf-8 unsupported encoding!!!");
                return null;
            }
            httpPost.setEntity(entity);
            return HttpRequestUtils.invoke(httpPost, hc);
        }
    }

    private void addHeaders(HttpRequestBase request, Map<String, String> headers) {
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(request::addHeader);
        }
    }

    public abstract ProcessResult executeProcess(WrapperRequiredKeys requiredKeys);


}
