package com.example.tool.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * txt 文件 IO -> 写出与读取工具类
 *
 * @author 码农猿
 */
public class TxtUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(TxtUtils.class);
    /**
     * 空串常量
     */
    private final static String EMPTY = "";
    /**
     * 编码
     */
    private final static String CHARSET_NAME = "UTF-8";
    /**
     * txt 文件后缀
     */
    private final static String SUFFIX_TXT = ".txt";
    /**
     * 换行符
     */
    private final static String LINE_BREAK = "\n";

    /**
     * 写出新文件，默认会覆盖内容
     *
     * @param filePath 文件路径，包括地址+名称
     * @param content  内容
     */
    public static void writNewTxt(String filePath, String content) {
        List<String> contentList = new ArrayList<>(1);
        contentList.add(content);
        writeTxt(filePath, contentList, false);
    }

    /**
     * 写出单行内容
     *
     * @param filePath 文件路径，包括地址+名称
     * @param content  内容
     */
    public static void writSingleLineTxt(String filePath, String content) {
        List<String> contentList = new ArrayList<>(1);
        contentList.add(content);
        writeTxt(filePath, contentList, true);
    }

    /**
     * 写出多行内容
     *
     * @param filePath    文件路径，包括地址+名称
     * @param contentList 内容
     */
    public static void writeManyLineTxt(String filePath, List<String> contentList) {
        writeTxt(filePath, contentList, true);
    }

    /**
     * 生成txt文件
     *
     * @param filePath    文件路径，包括地址+名称
     * @param contentList 内容集,每一个元素作为一行
     * @param appendFlag  是否继续追加内容
     */
    public static void writeTxt(String filePath, List<String> contentList, boolean appendFlag) {
        //非空判断
        if (null == filePath || EMPTY.equals(filePath)) {
            return;
        }
        //若非.txt结尾
        if (!filePath.endsWith(SUFFIX_TXT)) {
            filePath = filePath.concat(SUFFIX_TXT);
        }
        doWriteTxt(filePath, contentList, appendFlag);
    }

    /**
     * 解析读取txt文件
     *
     * @param filePath
     */
    public static List<String> readTxt(String filePath) {
        List<String> contentList = new ArrayList<>();
        try {
            //若非.txt结尾
            if (!filePath.endsWith(SUFFIX_TXT)) {
                filePath = filePath.concat(SUFFIX_TXT);
            }
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                LOGGER.info("解析读取txt文件不存在,或者非文件 filePath = {}", filePath);
                return contentList;
            }
            contentList = new ArrayList<>();
            FileInputStream in = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, CHARSET_NAME));
            String line = null;
            while ((line = br.readLine()) != null) {
                contentList.add(line);
            }
            //关闭流
            br.close();
        } catch (Exception e) {
            LOGGER.info("解析读取txt文件出现异常 filePath = {} , stack={}", filePath, e);
        }
        return contentList;
    }

    /**
     * 生成txt文件
     *
     * @param filePath    文件全路径，包括地址+名称
     * @param contentList 内容集,每一个元素作为一行
     * @param appendFlag  是否继续追加内容
     */
    private static void doWriteTxt(String filePath, List<String> contentList, boolean appendFlag) {
        //非空判断
        if (null == contentList || contentList.size() == 0) {
            return;
        }
        try {
            File file = new File(filePath);
            File dir = new File(file.getParent());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //文件不存在则创建文件
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, appendFlag)));
            for (String content : contentList) {
                content += LINE_BREAK;
                //写出文件
                output.write(content);
            }
            //关闭
            output.close();
        } catch (Exception e) {
            LOGGER.info("写出文件出现异常", e);
        }
    }
}
