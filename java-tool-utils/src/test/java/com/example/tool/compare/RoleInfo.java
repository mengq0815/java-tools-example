package com.example.tool.compare;

import lombok.Data;


@Data
public class RoleInfo {

    private String roleId;
    private String roleName;

    private MenuInfo menuInfo;

    public RoleInfo() {
    }

    public RoleInfo(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public RoleInfo(String roleId, String roleName, MenuInfo menuInfo) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.menuInfo = menuInfo;
    }
}