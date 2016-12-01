package com.fosun.fc.projects.creepers.utils.code;

import java.math.BigInteger;
import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <p>
 * description:加密解密 基础的加密解密信息
 * 
 * 警告：sun.misc.BASE64Decoder 是 Sun 的专用 API，可能会在未来版本中删除
 * 
 * import sun.misc.BASE64Decoder; ^ 警告：sun.misc.BASE64Encoder 是 Sun 的专用
 * API，可能会在未来版本中删除
 * 
 * import sun.misc.BASE64Encoder; ^
 * 
 * BASE64Encoder 和BASE64Decoder是非官方JDK实现类。虽然可以在JDK里能找到并使用，但是在API里查不到。JRE 中 sun 和
 * com.sun 开头包的类都是未被文档化的，他们属于 java, javax 类库的基础，其中的实现大多数与底层平台有关，一般来说是不推荐使用的。
 * 
 * </p>
 * 
 * @author MaXin
 * @since 2016年9月14日
 * @see
 */
@SuppressWarnings("restriction")
public class BaseCode {

    public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";

    /**
     * MAC算法可选以下多种算法
     * 
     * <pre>
     * HmacMD5 
     * HmacSHA1 
     * HmacSHA256 
     * HmacSHA384 
     * HmacSHA512
     * </pre>
     */
    public static final String KEY_MAC = "HmacMD5";

    /**
     * BASE64解密
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * MD5加密
     * 
     * @param data
     *            byte类型数组
     * @return MD5加密密文
     * @throws Exception
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);

        return md5.digest();

    }

    public final static byte[] encryptMD5(String data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data.getBytes());

        return md5.digest();
    }

    /**
     * 
     * @param digest
     *            MD5加密密文
     * @return 十六进制的字符串形式密文
     */
    public final static String encryptMD5To16(byte[] digest) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            // 获得密文
            byte[] md = encryptMD5(digest);
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final static String encryptMD5To16(String digest) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            // 获得密文
            byte[] md = encryptMD5(digest.getBytes());
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * SHA加密
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);

        return sha.digest();

    }

    /**
     * 初始化HMAC密钥
     * 
     * @return
     * @throws Exception
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC加密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);

        return mac.doFinal(data);

    }

    /**
     * 
     * <p>
     * description: 比较obj1 obj2的值是否相同 目前支持obj 为String 或 byte[] 后续会扩展
     * </p>
     * 
     * @param obj1
     * @param obj2
     * @return
     * @author MaXin
     * @see 2016-9-14 11:53:36
     */
    public static boolean compareValue(Object obj1, Object obj2) {
        if (obj1 instanceof byte[] && obj2 instanceof byte[]) {
            String str1 = new String((byte[]) obj1);
            String str2 = new String((byte[]) obj2);
            return str1.equals(str2);
        } else if (obj1 instanceof byte[] && obj2 instanceof String) {
            String str1 = new String((byte[]) obj1);
            String str2 = (String) obj2;
            return str1.equals(str2);
        } else if (obj1 instanceof String && obj2 instanceof byte[]) {
            String str1 = (String) obj1;
            String str2 = new String((byte[]) obj2);
            return str1.equals(str2);
        } else if (obj1 instanceof String && obj2 instanceof String) {
            String str1 = (String) obj1;
            String str2 = (String) obj2;
            return str1.equals(str2);
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        String inputStr = "654123";
        System.err.println("原文:\n" + inputStr);

        byte[] inputData = inputStr.getBytes();
        String code = BaseCode.encryptBASE64(inputData);

        System.err.println("BASE64加密后:\n" + code);

        byte[] output = BaseCode.decryptBASE64(code);

        String outputStr = new String(output);

        System.err.println("BASE64解密后:\n" + outputStr);

        // 验证BASE64加密解密一致性
        System.err.println("验证BASE64加密解密一致性:" + inputStr.equals(outputStr));

        // 验证MD5对于同一内容加密是否一致
        System.err.println("验证MD5对于同一内容加密是否一致:"
                + BaseCode.compareValue(BaseCode.encryptMD5(inputData), BaseCode.encryptMD5(inputData)));

        // 验证SHA对于同一内容加密是否一致
        System.err.println("验证SHA对于同一内容加密是否一致:"
                + BaseCode.compareValue(BaseCode.encryptSHA(inputData), BaseCode.encryptSHA(inputData)));

        String key = BaseCode.initMacKey();
        System.err.println("Mac密钥:\n" + key);

        // 验证HMAC对于同一内容，同一密钥加密是否一致
        System.err.println("验证HMAC对于同一内容，同一密钥加密是否一致:"
                + BaseCode.compareValue(BaseCode.encryptHMAC(inputData, key), BaseCode.encryptHMAC(inputData, key)));

        BigInteger md5 = new BigInteger(BaseCode.encryptMD5(inputData));
        System.err.println("MD5:\n" + md5.toString(16));
        System.err.println("trans1:" + new String(BaseCode.encryptMD5To16(inputData)));
        System.err.println("trans2:" + BaseCode.encryptMD5(inputStr));
        BigInteger sha = new BigInteger(BaseCode.encryptSHA(inputData));
        System.err.println("SHA:\n" + sha.toString(32));

        BigInteger mac = new BigInteger(BaseCode.encryptHMAC(inputData, inputStr));
        System.err.println("HMAC:\n" + mac.toString(16));
    }
}
