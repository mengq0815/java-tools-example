package com.example.tool.demo.model;

import lombok.Data;

/**
 * @author mengq
 * @date 2020-11-05 21:35
 * @desc
 */
@Data
public class UserInfo {

    private Integer id;
    private String userName;
    private Integer age;

    public UserInfo(Integer id, String userName, Integer age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }
}