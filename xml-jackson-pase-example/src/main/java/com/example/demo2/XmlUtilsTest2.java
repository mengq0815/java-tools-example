package com.example.demo2;

import com.example.util.XmlUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


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
        System.out.println(this.getStudentXml());

    }


    /**
     * xml  转 bean 测试
     */
    @Test
    public void xmlToBeanTest1() throws Exception {
        //生成 xml
        String xmlStr = this.getStudentXml();
        System.out.print("bean 转 xml字符串 ：" + xmlStr);
        //转换为对象
        Student student = XmlUtils.xmlStringToObject(xmlStr, Student.class);
        System.out.println("xml 转 bean ：" + student);
    }

    private String getStudentXml() throws Exception {
        StudentClass studentClass = new StudentClass(1, "张老师", "13235627899");
        List<StudentPhone> phoneList = new ArrayList<>(2);
        StudentPhone phone1 = new StudentPhone("13235777777", "上海");
        StudentPhone phone2 = new StudentPhone("13235777778", "杭州");
        phoneList.add(phone1);
        phoneList.add(phone2);

        Student student = new Student();
        student.setName("学生李四");
        student.setAge(16);
        student.setStudentClass(studentClass);
        student.setPhoneList(phoneList);
        return XmlUtils.objectToXmlString(student);
    }

}
