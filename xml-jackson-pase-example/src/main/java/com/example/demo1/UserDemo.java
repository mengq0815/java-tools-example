package com.example.demo1;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.math.BigDecimal;

/**
 * 人员测试
 *
 * @author 码农猿
 */
@JacksonXmlRootElement(localName = "user",namespace = "http://1234")
public class UserDemo {
    /**
     * 用户名
     */
    @JacksonXmlProperty(localName = "user_name")
    private String username;

    /**
     * 密码
     */
    @JacksonXmlProperty(localName = "password")
    private String userPassword;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 体重
     * 注:小数属性测试
     */
    @JacksonXmlProperty(localName = "body_weight")
    private BigDecimal bodyWeight;

    /**
     * 是否管理员
     */
    @JacksonXmlProperty(localName = "is_admin")
    private Boolean isAdmin;


    public UserDemo() {
    }

    public UserDemo(String username, String userPassword, String phone, Integer age, BigDecimal bodyWeight, Boolean isAdmin) {
        this.username = username;
        this.userPassword = userPassword;
        this.phone = phone;
        this.age = age;
        this.bodyWeight = bodyWeight;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(BigDecimal bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", bodyWeight=" + bodyWeight +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
