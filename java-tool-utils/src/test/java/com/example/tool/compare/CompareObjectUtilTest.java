package com.example.tool.compare;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Arrays;
import java.util.Map;

public class CompareObjectUtilTest {

    public static void main(String[] args) {

        UserInfo oldUser = new UserInfo("U1001", "zhangsan", "123456", "header", "mobile", 10);
        oldUser.setFlag(Boolean.FALSE);
        MenuInfo menuInfo = new MenuInfo("M001", "人员管理");
        RoleInfo roleInfo = new RoleInfo("R001", "admin", menuInfo);
        oldUser.setRoleInfo(roleInfo);
        oldUser.setUserTestList(Arrays.asList("111,222,333,44".split(",")));

        UserInfo newUser = new UserInfo("U1001", "new-lisi", "12345678", "header1", "mobile1", 10);

        newUser.setFlag(Boolean.FALSE);
        MenuInfo menuInfo2 = new MenuInfo("M001", "角色管理");
        RoleInfo roleInfo2 = new RoleInfo("R001", "system", menuInfo2);
        roleInfo2.setMenuInfo(menuInfo2);
        newUser.setRoleInfo(roleInfo2);
        newUser.setUserTestList(Arrays.asList("333,44,测试".split(",")));

        Map<String, Object> allFieldValues = CompareObjectUtil.getAllFieldValues(oldUser, newUser, UserInfo.class);
        Map<String, Object> changeFieldValues = CompareObjectUtil.getChangeFieldValues(oldUser, newUser, UserInfo.class);

        System.out.println(JSON.toJSONString(allFieldValues, SerializerFeature.PrettyFormat));
        System.out.println("");
        System.out.println(JSON.toJSONString(changeFieldValues, SerializerFeature.PrettyFormat));


    }
}