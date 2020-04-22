package com.example.demo2;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 学生- 班级属性 类
 *
 * @author 码农猿
 */
public class StudentClass {
    /**
     * 班级编号
     */
    @JacksonXmlProperty(localName = "class_number")
    private Integer classNumber;

    /**
     * 班主任 手机号
     */
    @JacksonXmlProperty(localName = "teacher_phone")
    private String teacherPhone;

    /**
     * 班主任 姓名
     */
    @JacksonXmlProperty(localName = "teacher_name")
    private String teacherName;

    public StudentClass() {
    }

    public StudentClass(Integer classNumber, String teacherName, String teacherPhone) {
        this.classNumber = classNumber;
        this.teacherName = teacherName;
        this.teacherPhone = teacherPhone;

    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "StudentClass{" +
                "classNumber=" + classNumber +
                ", teacherPhone='" + teacherPhone + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
