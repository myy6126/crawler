package com.myy.crawler.web;

import com.google.common.collect.Maps;
import com.myy.crawler.WrapperCrawlerGenerator;
import com.myy.crawler.bean.ProcessResult;
import com.myy.crawler.bean.ResultStatus;
import com.myy.crawler.wrapper.WrapperRequiredKeys;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CommonCrawlController extends InternalBaseController {

    private static final Logger logger = LoggerFactory.getLogger(CommonCrawlController.class);

    @RequestMapping(value = "/crawl", method = RequestMethod.POST)
    public ResponseEntity<Object> crawl(@RequestBody CrawlRequestParam param) {
        return this.crawlResult(param);
    }

    @RequestMapping(value = "/proxy/crawl", method = RequestMethod.POST)
    public ResponseEntity<Object> crawlWithProxy(@RequestBody CrawlRequestParam param) {
        return this.crawlResult(param);
    }

    private ResponseEntity<Object> crawlResult(CrawlRequestParam param) {
        if (StringUtils.isBlank(param.getSource())) {
            param.setSource(param.getWrapperId());
        }
        WrapperRequiredKeys requiredKeys = WrapperRequiredKeys.create(param.getWrapperId(), param.getClassName(), param.getSource());
        requiredKeys.setParam(param.getParam());
        WrapperCrawlerGenerator generator = new WrapperCrawlerGenerator(requiredKeys);
        ProcessResult processResult = generator.generator();

        if (processResult.getResultStatus().getValue() != ResultStatus.NORMAL.getValue()) {
            logger.warn("crawl result may error. status:{}", processResult.getResultStatus().getValue());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(processResult.getResult(), HttpStatus.OK);
        }
    }

    @Getter
    @Setter
    public static class CrawlRequestParam {
        @NotBlank
        private String wrapperId;
        @NotBlank
        private String className;
        private String source;
        private Map<String, String> param = Maps.newHashMap();
    }

}
