package com.example.tool.uuid;

import java.util.Locale;
import java.util.UUID;

/**
 * UUID 工具类
 *
 * @author 码农猿
 */
public final class UUIDUtil {

    private UUIDUtil() {
    }

    /**
     * 获取32位大写UUID
     *
     * @author 码农猿
     */
    public static String get32UpperCaseUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase(Locale.ENGLISH);
    }
}
