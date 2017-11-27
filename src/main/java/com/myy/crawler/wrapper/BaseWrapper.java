package com.myy.crawler.wrapper;

import com.myy.crawler.CommonWrapper;
import com.myy.crawler.bean.ProcessResult;
import com.myy.crawler.bean.ResultStatus;

/**
 * @author caozhaorui
 */
public abstract class BaseWrapper extends CommonWrapper {

    public BaseWrapper(String charset, String wrapperId, boolean isProxy) {
        super(charset, wrapperId, isProxy);
    }

    @Override
    public ProcessResult executeProcess(WrapperRequiredKeys requiredKeys) {
        return extractInfo(requiredKeys);
    }

    private ProcessResult extractInfo(WrapperRequiredKeys requiredKeys) {
        try {
            return process(requiredKeys);
        } catch (Exception e) {
            log.error("EXTRACT_INFO_EXCEPTION:{}", requiredKeys.toString(), e);
            return new ProcessResult(ResultStatus.PARSING_FAIL);
        }
    }

    public abstract ProcessResult process(WrapperRequiredKeys requiredKeys);
}
