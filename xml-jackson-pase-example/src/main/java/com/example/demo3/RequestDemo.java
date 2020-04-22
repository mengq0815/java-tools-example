package com.example.demo3;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 公共请求 类
 *
 * @author 码农猿
 */
@JacksonXmlRootElement(localName = "request")
public class RequestDemo<T> {

    /**
     * 请求方 appid
     */
    @JacksonXmlProperty(localName = "app_id")
    private String appId;

    /**
     * 版本
     */
    @JacksonXmlProperty(localName = "version")
    private String version;

    /**
     * 签名
     */
    @JacksonXmlProperty(localName = "sign")
    private String sign;


    /**
     * 请求内容
     */
    private T content;

    public RequestDemo() {
    }

    public RequestDemo(String appId, String version, String sign) {
        this.appId = appId;
        this.version = version;
        this.sign = sign;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RequestDemo{" +
                "appId='" + appId + '\'' +
                ", version='" + version + '\'' +
                ", sign='" + sign + '\'' +
                ", content=" + content +
                '}';
    }
}