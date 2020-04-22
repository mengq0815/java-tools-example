package com.example.demo2;

import com.fasterxml.jackson.dataformat.xml.annotation.*;
import java.util.List;

/**
 * 学生测试 类
 *
 * @author 码农猿
 */
@JacksonXmlRootElement(localName = "student")
public class Student {

    /**
     * 姓名
     */
    @JacksonXmlProperty(localName = "student_name")
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 班级
     */
    @JacksonXmlProperty(localName = "class")
    private StudentClass studentClass;

    /**
     * 手机号 （多个）
     */
    @JacksonXmlElementWrapper(localName = "phone_list")
    @JacksonXmlProperty(localName = "phone")
    private List<StudentPhone> phoneList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    public List<StudentPhone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<StudentPhone> phoneList) {
        this.phoneList = phoneList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", studentClass=" + studentClass +
                ", phoneList=" + phoneList +
                '}';
    }
}
