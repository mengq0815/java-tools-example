package com.example.tool.reflection;

import java.math.BigDecimal;

/**
 * @author mengq
 * @date 2020-07-27 17:31
 * @desc
 */
public class ReflectionUtilTest {

    public static void main(String[] args) {
        System.out.println(ReflectionUtil.isJdkClazz(Short.class));
        System.out.println(ReflectionUtil.isJdkClazz(String.class));
        System.out.println(ReflectionUtil.isJdkClazz(Integer.class));
        System.out.println(ReflectionUtil.isJdkClazz(int.class));
        System.out.println(ReflectionUtil.isJdkClazz(double.class));
        System.out.println(ReflectionUtil.isJdkClazz(Boolean.class));
        System.out.println(ReflectionUtil.isJdkClazz(BigDecimal.class));
        System.out.println(ReflectionUtil.isJdkClazz(short.class));
        System.out.println(ReflectionUtil.isJdkClazz(Short.class));
        System.out.println(ReflectionUtil.isJdkClazz(char.class));
        System.out.println(ReflectionUtil.isJdkClazz(Long.class));
        System.out.println(ReflectionUtil.isJdkClazz(long.class));
        System.out.println(ReflectionUtil.isJdkClazz(Float.class));
        System.out.println(Long.class.getClassLoader());

    }

}