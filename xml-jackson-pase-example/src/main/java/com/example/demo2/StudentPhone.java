package com.example.demo2;

/**
 * 学生- 手机属性 类
 *
 * @author 码农猿
 */
public class StudentPhone {
    /**
     * 号码
     */
    private String number;

    /**
     * 归属地
     */
    private String address;

    public StudentPhone() {
    }

    public StudentPhone(String number, String address) {
        this.number = number;
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "StudentPhone{" +
                "number='" + number + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
