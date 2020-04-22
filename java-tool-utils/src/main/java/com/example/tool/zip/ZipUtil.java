package com.example.tool.zip;

import com.example.tool.file.FileUtil;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.*;
import java.util.List;

/**
 * 解压工具类
 *
 * @author 码农猿
 */
public class ZipUtil {

    /**
     * zip 文件后缀
     */
    private final static String SUFFIX_ZIP = ".zip";

    /**
     * zip压缩-文件
     *
     * @param sourceFilePath 源文件路径
     * @param zipFilePath    压缩后目标文件路径
     */
    public static void zip(String sourceFilePath, String zipFilePath) {
        //获取源文件路径下所有文件
        List<String> paths = FileUtil.getFiles(sourceFilePath);
        //文件后缀处理
        if (!zipFilePath.endsWith(SUFFIX_ZIP)) {
            zipFilePath = zipFilePath.concat(SUFFIX_ZIP);
        }
        //执行压缩
        doZipFiles(paths, zipFilePath, sourceFilePath);
    }

    /**
     * zip解压-文件-到指定路径
     *
     * @param zipFilePath   zip压缩文件路径, 如 "c:/test/aa.zip"
     * @param targetFileDir 解压后的文件存放路径, 如"c:/test/"
     */
    public static void unzip(String zipFilePath, String targetFileDir) {
        unzipEncoding(zipFilePath, targetFileDir, null);
    }


    /**
     * zip解压-文件-到指定路径
     *
     * @param zipFilePath   zip压缩文件路径, 如 "c:/test/aa.zip"
     * @param targetFileDir 解压后的文件存放路径, 如"c:/test/"
     * @param encoding      指定解压encoding
     */
    public static void unzipEncoding(String zipFilePath, String targetFileDir, String encoding) {
        if (!targetFileDir.endsWith("\\") && !targetFileDir.endsWith("/")) {
            targetFileDir += File.separator;
        }
        File file = new File(zipFilePath);
        //压缩文件是否存在
        if (!file.exists()) {
            return;
        }
        File dir = new File(targetFileDir);
        //目标路径存在
        if (!dir.exists()) {
            dir.mkdirs();
        }
        InputStream is = null;
        ZipArchiveInputStream zipInputStream = null;
        try {
            is = new FileInputStream(file);
            if (null == encoding) {
                zipInputStream = new ZipArchiveInputStream(is);
            } else {
                zipInputStream = new ZipArchiveInputStream(is, encoding);
            }
            ArchiveEntry archiveEntry = null;
            while ((archiveEntry = zipInputStream.getNextEntry()) != null) {
                //获取文件名
                String entryFileName = archiveEntry.getName();
                //构造解压出来的文件路径
                String entryFilePath = targetFileDir + entryFileName;
                OutputStream os = null;
                try {
                    //把解压出来的文件写到指定路径
                    File entryFile = new File(entryFilePath);
                    if (entryFileName.endsWith("/")) {
                        entryFile.mkdirs();
                    } else {
                        os = new BufferedOutputStream(new FileOutputStream(entryFile));
                        int len = -1;
                        byte[] buffer = new byte[1024];
                        while ((len = zipInputStream.read(buffer)) != -1) {
                            os.write(buffer, 0, len);
                        }
                    }
                } catch (IOException e) {
                    throw new IOException(e);
                } finally {
                    if (os != null) {
                        os.flush();
                        os.close();
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (zipInputStream != null) {
                    zipInputStream.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 把文件压缩成zip格式
     *
     * @param fileList    需要压缩的文件
     * @param zipFilePath 压缩后的zip文件路径   ,如"D:/test/aa.zip";
     */
    private static void doZipFiles(List<String> fileList, String zipFilePath, String dir) {
        if (fileList == null || fileList.size() <= 0) {
            return;
        }
        ZipArchiveOutputStream zipOutputStream = null;
        try {
            File zipFile = new File(zipFilePath);
            zipOutputStream = new ZipArchiveOutputStream(zipFile);
            zipOutputStream.setUseZip64(Zip64Mode.AsNeeded);
            //将每个文件用ZipArchiveEntry封装
            //再用ZipArchiveOutputStream写到压缩文件中
            for (String strFile : fileList) {
                File file = new File(strFile);
                if (null == file) {
                    continue;
                }
                String name = getFilePathName(dir, strFile);
                ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, name);
                zipOutputStream.putArchiveEntry(zipArchiveEntry);
                //是否为文件夹
                if (file.isDirectory()) {
                    continue;
                }
                InputStream inputStream = null;
                try {
                    inputStream = new BufferedInputStream(new FileInputStream(file));
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    while ((len = inputStream.read(buffer)) != -1) {
                        //把缓冲区的字节写入到ZipArchiveEntry
                        zipOutputStream.write(buffer, 0, len);
                    }
                    zipOutputStream.closeArchiveEntry();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    if (null != inputStream) {
                        inputStream.close();
                    }
                }
            }
            zipOutputStream.finish();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != zipOutputStream) {
                    zipOutputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * 文件名处理
     *
     * @param dir
     * @param path
     * @return
     */
    private static String getFilePathName(String dir, String path) {
        String p = path.replace(dir + File.separator, "");
        p = p.replace("\\", "/");
        return p;
    }


}
