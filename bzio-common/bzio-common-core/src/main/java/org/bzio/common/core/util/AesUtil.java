package org.bzio.common.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author snow
 */
public class AesUtil {

    private static final Logger log = LoggerFactory.getLogger(AesUtil.class);

    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
    private static final String AES = "AES";

    public static String encrypt(String content, String key) {
        try {
            // 根据密码生成AES密钥
            byte[] raw = key.getBytes();
            SecretKeySpec secretKey = new SecretKeySpec(raw, AES);
            // 根据指定算法ALGORITHM自成密码器
            // "算法/模式/补码方式
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            // 初始化密码器
            // 第一个参数为加密(ENCRYPT_MODE)或者解密(DECRYPT_MODE)操作
            // 第二个参数为生成的AES密钥
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            // 获取加密内容的字节数组
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            // 密码器加密数据
            byte[] encodeContent = cipher.doFinal(byteContent);
            Base64.Encoder encoder = Base64.getEncoder();
            // 将加密后的数据转换为字符串返回
            return new String(encoder.encode(encodeContent));
        } catch (Exception e) {
            log.error("加密异常，异常信息：", e);
            return "";
        }
    }

    public static String decrypt(String encryptStr, String key) {
        try {
            // 获得密码的字节数组
            byte[] raw = key.getBytes();
            // 根据密码生成AES密钥
            SecretKeySpec secretKey = new SecretKeySpec(raw, AES);
            // 根据指定算法ALGORITHM自成密码器
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            // 初始化密码器，第一个参数为加密(ENCRYPT_MODE)或者解密(DECRYPT_MODE)操作，第二个参数为生成的AES密钥
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            Base64.Decoder base64 = Base64.getDecoder();
            // 把密文字符串转回密文字节数组
            byte[] encodeContent = base64.decode(encryptStr);
            // 密码器解密数据
            byte[] byteContent = cipher.doFinal(encodeContent);
            // 将解密后的数据转换为字符串返回
            return new String(byteContent, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("解密异常，异常信息：", e);
            return "";
        }
    }
}
