package com.myy.crawler.bean;

/**
 * @author caozhaorui
 */
public class ProcessResult<T> {
    private ResultStatus resultStatus;

    private T result;

    public ProcessResult() {
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public ProcessResult(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }


}
