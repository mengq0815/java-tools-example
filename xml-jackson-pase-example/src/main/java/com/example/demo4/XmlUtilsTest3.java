package com.example.demo4;

import com.example.util.XmlUtils;
import org.junit.Test;

import java.util.Map;


/**
 * bean 转 xml
 * xml  转 bean 测试
 *
 * @author 码农猿
 */
public class XmlUtilsTest3 {


    /**
     * bean 转 xml 测试
     */
    @Test
    public void beanToXmlTest2() throws Exception {


        RequestDemo.ItemParam itemParam = new RequestDemo.ItemParam();
        itemParam.setBbkNbr("69");
        itemParam.setAccNbr("769900002210101");
        itemParam.setBgnDat("20080901");
        itemParam.setEndDat("20080903");
        RequestDemo requestDemo = new RequestDemo(itemParam);

        String xmlString = XmlUtils.objectToXmlString(requestDemo);
        System.out.println(xmlString);



    }


}
