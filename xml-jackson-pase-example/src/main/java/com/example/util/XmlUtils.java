package com.example.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;

/**
 * bean 转 xml
 * xml  转 bean 转换工具类
 *
 * @author 码农猿
 */
public class XmlUtils {

    private static final ObjectMapper OBJECT_MAPPER = new XmlMapper();

    /**
     * 将Object 转 XML 字符串
     *
     * @param object 要转换的对象
     * @throws JsonProcessingException
     */
    public static String objectToXmlString(Object object) throws JsonProcessingException {
        //美化输出的 xml 字符串
        OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        //字段为null，自动忽略，不再序列化
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return OBJECT_MAPPER.writeValueAsString(object);
    }

    /**
     * XMl >
     * 将XML字符串转换为JavaBean对象，
     * ObjectMapper还提供了很多重载方法，详情查看源码，这里不一一列举
     *
     * @param xmlStr xml字符串
     * @param tClass 转换成的类
     * @param <T>    转换成的类
     * @throws IOException
     */
    public static <T> T xmlStringToObject(String xmlStr, Class<T> tClass) throws IOException {
        //字段为null，自动忽略，不再序列化
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return OBJECT_MAPPER.readValue(xmlStr, tClass);
    }
}
