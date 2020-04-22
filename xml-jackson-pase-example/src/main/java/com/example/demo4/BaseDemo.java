package com.example.demo4;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 测试基础报文参数
 *
 * @author 码农猿
 */
public class BaseDemo {

    @JacksonXmlProperty(localName = "FUNNAM")
    private String funName;

    /**
     * 类型 固定为 2
     */
    @JacksonXmlProperty(localName = "DATTYP")
    private Integer datTyp = 2;

    @JacksonXmlProperty(localName = "LGNNAM")
    private String lgnNam;

    public BaseDemo() {
    }

    public BaseDemo(String funName, String lgnNam) {
        this.funName = funName;
        this.lgnNam = lgnNam;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public Integer getDatTyp() {
        return datTyp;
    }

    public void setDatTyp(Integer datTyp) {
        this.datTyp = datTyp;
    }

    public String getLgnNam() {
        return lgnNam;
    }

    public void setLgnNam(String lgnNam) {
        this.lgnNam = lgnNam;
    }
}