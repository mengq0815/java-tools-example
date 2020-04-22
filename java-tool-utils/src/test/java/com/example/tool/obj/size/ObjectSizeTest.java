package com.example.tool.obj.size;

import org.apache.lucene.util.RamUsageEstimator;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author mengqiang
 * @version .java, v 0.1   -   -
 */
public class ObjectSizeTest {

    @Test
    public void testInteger() {
        Integer integer = 1111111110;
        int num = 99999990;
        System.out.println("int大小 ：" + RamUsageEstimator.shallowSizeOf(num));
        System.out.println("integer大小 ：" + RamUsageEstimator.shallowSizeOf(integer));
    }

    @Test
    public void testString() {
        String str = "";
        System.out.println("1-String大小 ：" + RamUsageEstimator.shallowSizeOf(str));
        String str2 = "11111";
        System.out.println("2-String大小 ：" + RamUsageEstimator.shallowSizeOf(str2));
    }


    @Test
    public void testDouble() {
        Double double1 = 22222288888888888888888.0020D;
        System.out.println("double1 ：" + RamUsageEstimator.shallowSizeOf(double1));
        double double2 = 11111110.999999d;
        System.out.println("double2 ：" + RamUsageEstimator.shallowSizeOf(double2));
    }

    @Test
    public void testBoolean() {
        boolean flag1 = false;
        System.out.println("flag1大小 ：" + RamUsageEstimator.shallowSizeOf(flag1));
        Boolean flag2 = Boolean.TRUE;
        System.out.println("flag2大小 ：" + RamUsageEstimator.shallowSizeOf(flag2));
    }

    @Test
    public void testLong() {
        long num1 = 1000000000000L;
        Long num2 = 1L;
        System.out.println("flag1大小 ：" + RamUsageEstimator.shallowSizeOf(num1));
        System.out.println("flag2大小 ：" + RamUsageEstimator.shallowSizeOf(num2));
    }


    @Test
    public void testBigDecimal() {
        BigDecimal bigDecimal1 = new BigDecimal("0");
        BigDecimal bigDecimal2 = BigDecimal.ZERO;
        System.out.println("bigDecimal1 大小 ：" + RamUsageEstimator.shallowSizeOf(bigDecimal1));
        System.out.println("bigDecimal2 大小 ：" + RamUsageEstimator.shallowSizeOf(bigDecimal2));
    }


    @Test
    public void testObject() {
        Object obj = new Object();
        System.out.println("Object大小 ：" + RamUsageEstimator.shallowSizeOf(obj));
    }


    @Test
    public void testList() {

        List<Object> arrayList = new ArrayList<>();
        System.out.println("List大小 ：" + RamUsageEstimator.shallowSizeOf(arrayList));

        Map<Object, Object> hashMap = new HashMap<>(0);
        System.out.println("Map大小 ：" + RamUsageEstimator.shallowSizeOf(hashMap));

        HashSet<Object> hashSet = new HashSet<>(0);
        System.out.println("Set大小 ：" + RamUsageEstimator.shallowSizeOf(hashSet));
    }

}