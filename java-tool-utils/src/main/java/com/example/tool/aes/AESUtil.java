package com.example.tool.aes;

import com.example.tool.string.StringPool;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;

/**
 * AES加密解密工具包
 *
 * @author 码农猿
 */
public class AESUtil {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AESUtil.class);
    /**
     * 加密算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 密钥长度
     * 候选值：128, 192 , 256
     */
    private static final int KEY_SIZE = 256;
    /**
     * 缓存区大小
     */
    private static final int CACHE_SIZE = 1024;

    /**
     * 加密
     *
     * @param content   内容
     * @param secretKey 秘钥
     * @return
     */
    public static String encrypt(String content, String secretKey) throws Exception {
        //获取加密密钥
        Key k = new SecretKeySpec((Base64.decodeBase64(secretKey)), ALGORITHM);
        //转换为AES专用密钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(k.getEncoded(), ALGORITHM);
        //创建密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        //初始化为加密模式的密码器
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        //执行加密
        byte[] bytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        //base 64 编码返回
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 解密
     *
     * @param content   内容
     * @param secretKey 秘钥
     * @return
     */
    public static String decrypt(String content, String secretKey) throws Exception {
        //base 64 解码
        byte[] data = Base64.decodeBase64(content);
        //获取加密密钥
        Key k = new SecretKeySpec((Base64.decodeBase64(secretKey)), ALGORITHM);
        //转换为AES专用密钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(k.getEncoded(), ALGORITHM);
        //创建密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        //初始化为加密模式的密码器
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        //执行解密
        byte[] decryptContent = cipher.doFinal(data);
        return new String(decryptContent, StandardCharsets.UTF_8);
    }


    /**
     * 文件加密
     *
     * @param secretKey
     * @param sourceFilePath
     * @param destFilePath
     */
    public static void encryptFile(String secretKey, String sourceFilePath, String destFilePath) {
        FileOutputStream outputStream = null;
        FileInputStream inputStream = null;
        CipherInputStream cipherInputStream = null;
        try {
            File sourceFile = new File(sourceFilePath);
            File destFile = new File(destFilePath);
            if (sourceFile.exists() && sourceFile.isFile()) {
                if (!destFile.getParentFile().exists()) {
                    destFile.getParentFile().mkdirs();
                }
                destFile.createNewFile();
                inputStream = new FileInputStream(sourceFile);
                outputStream = new FileOutputStream(destFile);
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, getSecretKeySpec(secretKey));
                cipherInputStream = new CipherInputStream(inputStream, cipher);
                byte[] cache = new byte[CACHE_SIZE];
                int nRead = 0;
                while ((nRead = cipherInputStream.read(cache)) != -1) {
                    outputStream.write(cache, 0, nRead);
                    outputStream.flush();
                }
            }
        } catch (Exception e) {
            LOGGER.info("文件AES加密异常", e);
            throw new RuntimeException("文件AES加密异常");
        } finally {
            //关闭流
            closeStream(inputStream, outputStream, cipherInputStream, null);
        }
    }


    /**
     * <p>
     * 文件解密
     * </p>
     *
     * @param secretKey
     * @param sourceFilePath 加密文件路径
     * @param destFilePath   解密后的文件路径
     */
    public static void decryptFile(String secretKey, String sourceFilePath, String destFilePath) {
        FileOutputStream outputStream = null;
        FileInputStream inputStream = null;
        CipherOutputStream cipherOutputStream = null;
        try {
            File sourceFile = new File(sourceFilePath);
            File destFile = new File(destFilePath);
            if (sourceFile.exists() && sourceFile.isFile()) {
                if (!destFile.getParentFile().exists()) {
                    destFile.getParentFile().mkdirs();
                }
                if (!destFile.createNewFile()) {
                    throw new IOException("创建文件失败");
                }
                inputStream = new FileInputStream(sourceFile);
                outputStream = new FileOutputStream(destFile);
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, getSecretKeySpec(secretKey));
                cipherOutputStream = new CipherOutputStream(outputStream, cipher);
                int nRead = 0;
                byte[] cache = new byte[CACHE_SIZE];
                while ((nRead = inputStream.read(cache)) != -1) {
                    cipherOutputStream.write(cache, 0, nRead);
                    cipherOutputStream.flush();
                }
            }
        } catch (Exception e) {
            LOGGER.info("文件AES解密异常", e);
            throw new RuntimeException("文件AES解密异常");
        } finally {
            //关闭流
            closeStream(inputStream, outputStream, null, cipherOutputStream);
        }
    }

    /**
     * 生成-随机密钥
     *
     * @return
     */
    public static String getSecretKey() {
        return getSecretKey(null);
    }


    /**
     * 生成密钥
     *
     * @param seed 密钥种子
     * @return
     */
    public static String getSecretKey(String seed) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            SecureRandom secureRandom;
            if (seed != null && !StringPool.EMPTY.equals(seed)) {
                secureRandom = new SecureRandom(seed.getBytes());
            } else {
                secureRandom = new SecureRandom();
            }
            keyGenerator.init(KEY_SIZE, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            return Base64Utils.encodeToString(secretKey.getEncoded());
        } catch (Exception e) {
            LOGGER.info("生成AES密钥异常", e);
            throw new RuntimeException("生成AES密钥异常");
        }
    }


    /**
     * 秘钥key处理
     */
    private static SecretKeySpec getSecretKeySpec(String secretKey) {
        Key k = new SecretKeySpec(Base64Utils.decodeFromString(secretKey), ALGORITHM);
        return new SecretKeySpec(k.getEncoded(), ALGORITHM);
    }


    /**
     * 关闭流
     */
    private static void closeStream(FileInputStream inputStream, FileOutputStream outputStream,
                                    CipherInputStream cipherInputStream, CipherOutputStream cipherOutputStream) {
        try {
            if (null != inputStream) {
                inputStream.close();
            }
            if (null != outputStream) {
                outputStream.close();
            }
            if (null != cipherInputStream) {
                cipherInputStream.close();
            }
            if (null != cipherOutputStream) {
                cipherOutputStream.close();
            }
        } catch (Exception e) {
            LOGGER.info("文件AES加密后或解密关闭流异常", e);
            throw new RuntimeException("文件AES加密后或解密关闭流异常");
        }
    }
}