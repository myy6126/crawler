package com.myy.crawler.http;

import com.google.common.collect.Lists;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.message.BasicHeader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author xu.ma
 */
@Component
public class CrawlerClient {

    private HttpClient httpClient;

    @PostConstruct
    private void postConstruct() {
        httpClient = HttpClientBuilder
                .create()
                .setRetryHandler(new StandardHttpRequestRetryHandler(5, true))
                .setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(100000).build())
                .setMaxConnPerRoute(16)
                .setDefaultHeaders(
                        Lists.newArrayList(
                                new BasicHeader("accept-encoding", "gzip, deflate, sdch, br"),
                                new BasicHeader("x-requested-with", "XMLHttpRequest"),
                                new BasicHeader("accept-language", "en-US,en;q=0.8"),
                                new BasicHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) ),Chrome/51.0.2704.103 Safari/537.36"),
                                new BasicHeader("accept", "*/*"),
                                new BasicHeader("authority", "sg.carousell.com"),
                                new BasicHeader(
                                        "cookie",
                                        "__cfduid=dfe24abd5e75f104ca9adaed3f96909161472407071; _csrf=HXVj5j2tbfiIv0Lrnh5QjyHM; _gat=1; cf_clearance=293584680ba03a5dda3eb8dab3dcb3b5cbdd3591-1472555963-300; _ga=GA1.2.1791051273.1472407403; amplitude_idcarousell.com=eyJkZXZpY2VJZCI6Ijg2MTMwNGY0LTUwN2MtNDQyMy1hYmI4LTE4OTZkYjYyNDdiOCIsInVzZXJJZCI6bnVsbCwib3B0T3V0IjpmYWxzZX0=")))
                .build();
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }
}
