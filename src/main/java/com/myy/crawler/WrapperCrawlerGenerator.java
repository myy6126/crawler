package com.myy.crawler;

import com.google.common.base.Stopwatch;
import com.myy.classLoader.WrapperReference;
import com.myy.crawler.bean.ProcessResult;
import com.myy.crawler.bean.ResultStatus;
import com.myy.crawler.wrapper.WrapperRequiredKeys;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class WrapperCrawlerGenerator {

    private static final Logger logger = LoggerFactory.getLogger(WrapperCrawlerGenerator.class);

    private WrapperRequiredKeys requiredKeys;

    public WrapperCrawlerGenerator(WrapperRequiredKeys requiredKeys) {
        this.requiredKeys = requiredKeys;
    }

    public ProcessResult generator() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        if (checkRequiredKeys()) {
            CommonWrapper wrapperIns = WrapperReference.getCommonWrapperRef(requiredKeys.getWrapperId(), requiredKeys.getClassName());
            if (null != wrapperIns) {
                ProcessResult processResult = wrapperIns.executeProcess(requiredKeys);
                return processResult;
            }
        }

        return new ProcessResult(ResultStatus.NO_RESULT);
    }


    private boolean checkRequiredKeys() {
        if (StringUtils.isBlank(requiredKeys.getWrapperId()) || StringUtils.isBlank(requiredKeys.getClassName())) {
            logger.warn("request error. wrapperId or className is null. requiredKeys:{}", requiredKeys);
            return false;
        }

        if (StringUtils.isBlank(requiredKeys.getSource())) {
            logger.warn("request error. source is null. requiredKeys:{}", requiredKeys);
            return false;
        }

        return true;
    }
}
