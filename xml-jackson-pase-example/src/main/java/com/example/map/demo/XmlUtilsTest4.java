package com.example.map.demo;

import com.example.demo4.RequestDemo;
import com.example.util.XmlUtils;
import org.junit.Test;

import java.util.Map;


/**
 * bean 转 xml
 * xml  转 bean 测试
 *
 * @author 码农猿
 */
public class XmlUtilsTest4 {


    /**
     * bean 转 xml 测试
     */
    @Test
    public void beanToXmlTest2() throws Exception {


        String xmlString = "<CMBSDKPGK>\n" +
                "  <INFO>\n" +
                "    <FUNNAM>SDKNTQABINF</FUNNAM>\n" +
                "    <DATTYP>2</DATTYP>\n" +
                "    <LGNNAM>ZL01</LGNNAM>\n" +
                "  </INFO>\n" +
                "  <NTQABINFY>\n" +
                "    <BBKNBR>69</BBKNBR>\n" +
                "    <ACCNBR>769900002210101</ACCNBR>\n" +
                "    <BGNDAT>20080901</BGNDAT>\n" +
                "    <ENDDAT>20080903</ENDDAT>\n" +
                "  </NTQABINFY>\n" +
                "</CMBSDKPGK>";


        Map map = XmlUtils.xmlStringToObject(xmlString, Map.class);
        System.out.println(map);
    }


}
