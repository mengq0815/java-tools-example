package com.example.tool.compare;

import com.example.tool.compare.anno.FieldAliasAttr;
import lombok.Data;

import java.util.List;


@Data
public class UserInfo extends BaseInfo {

    @FieldAliasAttr(alias = "userId")
    private String userId;

    @FieldAliasAttr(alias = "人员登录名")
    private String username;

    @FieldAliasAttr(alias = "登录密码")
    private String password;

    @FieldAliasAttr(alias = "")
    private String headerImg;
    private String mobile;
    private String phone;
    private Boolean flag;
    private Boolean isFlag;
    private int sex;
    private int age;

    private List<String> userTestList;

    private RoleInfo roleInfo;

    public UserInfo() {
    }

    public UserInfo(String userId, String username, String password, String headerImg, String mobile, int sex) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.headerImg = headerImg;
        this.mobile = mobile;
        this.sex = sex;
    }
}