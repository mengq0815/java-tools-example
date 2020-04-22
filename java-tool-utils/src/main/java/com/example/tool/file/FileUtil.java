package com.example.tool.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件-工具类
 *
 * @author 码农猿
 */
public class FileUtil {

    /**
     * 移除路径下文件
     *
     * @param path
     */
    public static void removeFiles(String path) {
        File file = new File(path);
        if (file.exists()) {
            deleteFile(file);
        }
    }

    /**
     * 删除文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFile(files[i]);
            }
        }
        file.delete();
    }


    /**
     * 递归-获取当前目录下所有文件
     *
     * @param dir 当前文件夹
     * @return 所有文件绝对路径
     */
    public static List<String> getFiles(String dir) {
        List<String> listFiles = new ArrayList<>();
        File file = new File(dir);
        //是否是-文件夹
        if (!file.isDirectory()) {
            listFiles.add(file.getAbsolutePath());
            return listFiles;
        }
        File[] files = file.listFiles();
        if (null == files) {
            return listFiles;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                listFiles.add(f.getAbsolutePath());
                listFiles.addAll(getFiles(f.getAbsolutePath()));
            } else {
                listFiles.add(f.getAbsolutePath());
            }
        }
        return listFiles;
    }


}
