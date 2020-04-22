package com.example.demo4;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 测试某业务 > 请求报文参数
 * <p>
 * 注解JacksonXmlRootElement：根节点
 *
 * @author 码农猿
 */
@JacksonXmlRootElement(localName = "CMBSDKPGK")
public class RequestDemo {

    /**
     * 公共参数
     */
    @JacksonXmlProperty(localName = "INFO")
    private BaseDemo info;

    /**
     * 本次操作 > 业务参数
     */
    @JacksonXmlProperty(localName = "NTQABINFY")
    private ItemParam ntqabinfy;

    public static class ItemParam {
        @JacksonXmlProperty(localName = "BBKNBR")
        private String bbkNbr;

        @JacksonXmlProperty(localName = "ACCNBR")
        private String accNbr;

        @JacksonXmlProperty(localName = "BGNDAT")
        private String bgnDat;

        @JacksonXmlProperty(localName = "ENDDAT")
        private String endDat;

        public String getBbkNbr() {
            return bbkNbr;
        }

        public void setBbkNbr(String bbkNbr) {
            this.bbkNbr = bbkNbr;
        }

        public String getAccNbr() {
            return accNbr;
        }

        public void setAccNbr(String accNbr) {
            this.accNbr = accNbr;
        }

        public String getBgnDat() {
            return bgnDat;
        }

        public void setBgnDat(String bgnDat) {
            this.bgnDat = bgnDat;
        }

        public String getEndDat() {
            return endDat;
        }

        public void setEndDat(String endDat) {
            this.endDat = endDat;
        }
    }

    public RequestDemo(ItemParam ntqabinfy) {
        //公共参数
        this.info = new BaseDemo("SDKNTQABINF", "ZL01");
        this.ntqabinfy = ntqabinfy;
    }

    public BaseDemo getInfo() {
        return info;
    }

    public void setInfo(BaseDemo info) {
        this.info = info;
    }

    public ItemParam getNtqabinfy() {
        return ntqabinfy;
    }

    public void setNtqabinfy(ItemParam ntqabinfy) {
        this.ntqabinfy = ntqabinfy;
    }
}