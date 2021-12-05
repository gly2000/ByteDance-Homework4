/**
 * Copyright 2021 bejson.com
 */
package com.example.sjtu_network.api;
import java.util.List;

/**
 * Auto-generated: 2021-12-05 15:3:33
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class YouDaoBean {

    private String type;
    private int errorCode;
    private int elapsedTime;
    private List<List<com.example.sjtu_network.api.TranslateResult>> translateResult;
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setTranslateResult(List<List<com.example.sjtu_network.api.TranslateResult>> translateResult) {
        this.translateResult = translateResult;
    }
    public List<List<com.example.sjtu_network.api.TranslateResult>> getTranslateResult() {
        return translateResult;
    }

}