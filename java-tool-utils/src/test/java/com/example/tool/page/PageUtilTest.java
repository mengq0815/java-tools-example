package com.example.tool.page;

import cn.hutool.core.date.SystemClock;
import com.carrotsearch.sizeof.RamUsageEstimator;
import com.example.tool.page.model.UserModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengqiang
 */
public class PageUtilTest {

    public static void main(String[] args) {
        long time1 = SystemClock.now();
        int pageSize = 30;
        UserModel userModel;
        List<UserModel> userModelList = new ArrayList<>();
        for (int i = 1; i <= 10000 * pageSize; i++) {
            String userId = String.valueOf(i);
            String uerName = "zhangsan";
            String realName = "张三";
            String password = "123456";
            userModel = new UserModel(userId, uerName, realName, password);
            userModelList.add(userModel);
        }
        long time2 = SystemClock.now();

        List<UserModel> pageList = PageUtils.listPage(userModelList, 100, 10);
        long time3 = SystemClock.now();


        System.out.println("构建参数耗时 ：" + (time2 - time1));
        System.out.println("程序分页耗时 ：" + (time3 - time2));

        System.out.println("集合长度 :" + userModelList.size() + " 总集合占用大小 ：" + RamUsageEstimator.humanSizeOf(userModelList));
        System.out.println("集合长度 :" + userModelList.size() + " 总集合占用大小22 ：" + org.apache.lucene.util.RamUsageEstimator.shallowSizeOf(userModelList));
        System.out.println("集合长度: " + pageList.size() + " 分页集合占用大小 ：" + RamUsageEstimator.humanSizeOf(pageList));

        long time4 = SystemClock.now();
        List<UserModel> forPageList = new ArrayList<>();

        for (UserModel user : userModelList) {
            forPageList.add(user);
        }
        for (UserModel user2 : userModelList) {
            forPageList.add(user2);
        }

        for (UserModel user3 : userModelList) {
            forPageList.add(user3);
        }

        long time5 = SystemClock.now();
        System.out.println("3 for ：" + (time5 - time4));
        System.out.println("集合长度 :" + forPageList.size() + " 总集合占用大小 ：" + getNetFileSizeDescription(RamUsageEstimator.sizeOf(forPageList)));

    }


    public static String getNetFileSizeDescription(long size) {
        StringBuffer bytes = new StringBuffer();
        DecimalFormat format = new DecimalFormat("###.0");
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append("GB");
        } else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i)).append("MB");
        } else if (size >= 1024) {
            double i = (size / (1024.0));
            bytes.append(format.format(i)).append("KB");
        } else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B");
            } else {
                bytes.append((int) size).append("B");
            }
        }
        return bytes.toString();
    }
}