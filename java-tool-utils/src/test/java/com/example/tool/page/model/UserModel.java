package com.example.tool.page.model;

import lombok.Data;

/**
 * @author mengqiang
 */
@Data
public class UserModel {

    private String userId;
    private String uerName;
    private String realName;
    private String password;

    public UserModel() {
    }

    public UserModel(String userId, String uerName, String realName, String password) {
        this.userId = userId;
        this.uerName = uerName;
        this.realName = realName;
        this.password = password;
    }
}