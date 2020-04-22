package com.example.tool.aes;


public class AESUtilTest {
    public static void main(String[] args) {
        String secretKey = AESUtil.getSecretKey();
        System.out.println(secretKey);
        String sourceFilePath = "/Users/mengqiang/project/Github/Java-tools-example/java-tool-utils/src/test/java/com/example/tool/io/test1.txt";
        String destFilePath = "/Users/mengqiang/project/Github/Java-tools-example/java-tool-utils/src/test/java/com/example/tool/io/test2.txt";
        AESUtil.encryptFile(secretKey, sourceFilePath, destFilePath);
        System.out.println("success 1 ");
        String sourceFilePath2 = "/Users/mengqiang/project/Github/Java-tools-example/java-tool-utils/src/test/java/com/example/tool/io/test2.txt";
        String destFilePath2 = "/Users/mengqiang/project/Github/Java-tools-example/java-tool-utils/src/test/java/com/example/tool/io/test3.txt";

        AESUtil.decryptFile(secretKey, sourceFilePath2, destFilePath2);
        System.out.println("success 2");
    }
}