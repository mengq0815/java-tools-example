package com.example.demo1;

import com.example.util.XmlUtils;
import org.junit.Test;

import java.math.BigDecimal;


/**
 * 普通类无嵌套
 * <p>
 * bean 转 xml
 * xml  转 bean 测试
 *
 * @author 码农猿
 */
public class XmlUtilsTest1 {


    /**
     * bean 转 xml 测试
     */
    @Test
    public void beanToXmlTest1() throws Exception {
        System.out.println(this.getXmlString());

    }


    /**
     * xml  转 bean 测试
     */
    @Test
    public void xmlToBeanTest1() throws Exception {
        //生成 xml
        String xmlStr = this.getXmlString();
        System.out.print("bean 转 xml字符串 ：" + xmlStr);
        //转换为对象
        UserDemo user = XmlUtils.xmlStringToObject(xmlStr, UserDemo.class);
        System.out.println("xml 转 bean ：" + user);
    }

    private String getXmlString() throws Exception {
        UserDemo user1 = new UserDemo("张三", "123456", "13233333366", 12, new BigDecimal("120.33"), true);
        return XmlUtils.objectToXmlString(user1);
    }

}
