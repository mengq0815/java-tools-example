package com.example.tool.tree;


import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 树形结构
 *
 * @author mengqiang
 * @version TreeTest.java, v 3.0 2019-02-28
 */
public class TreeTest2 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, 1, 1};

        System.out.println((1 + 1 <= 1));
        System.out.println(containsNearbyDuplicate(nums, 1));

    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && Math.abs(map.get(nums[i]) - i) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}