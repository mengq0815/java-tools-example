package com.example.demo3;

import com.example.demo2.Student;
import com.example.util.XmlUtils;
import com.example.demo1.UserDemo;
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
public class XmlUtilsTest2 {

    /**
     * bean 转 xml 测试
     */
    @Test
    public void beanToXmlTest1() throws Exception {
        System.out.println(this.getRequestXml());

    }

    /**
     * xml  转 bean 测试
     */
    @Test
    public void xmlToBeanTest1() throws Exception {
        //获取 xml
        String xmlStr = this.getRequestXml();
        System.out.print("bean 转 xml字符串 ：" + xmlStr);
        RequestDemo<UserDemo> requestDemo = XmlUtils.xmlStringToObject(xmlStr, RequestDemo.class);

        System.out.println("xml 转 bean ：" + requestDemo);
    }

    private String getRequestXml() throws Exception {
        UserDemo user = new UserDemo("李四", "123456", "133331112266", 12, new BigDecimal("120.33"), true);
        RequestDemo<UserDemo> requestDemo = new RequestDemo<UserDemo>("10001", "1.0", "sign...123456");
        requestDemo.setContent(user);
        //转换 xml
        return XmlUtils.objectToXmlString(requestDemo);
    }

}
