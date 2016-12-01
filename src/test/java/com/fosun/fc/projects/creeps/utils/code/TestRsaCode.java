package com.fosun.fc.projects.creeps.utils.code;

import org.junit.Before;
import org.junit.Test;

import com.fosun.fc.projects.creepers.utils.code.BaseCode;
import com.fosun.fc.projects.creepers.utils.code.RsaCode;

import java.util.Map;

/**
 * <p>
 * description: RSA加密測試
 * </p>
 * 
 * @author MaXin
 * @since 2016-9-14 16:36:41
 * @see
 */
public class TestRsaCode {

    private String publicKey;
    private String privateKey;

    @Before
    public void setUp() throws Exception {
        Map<String, Object> keyMap = RsaCode.initKey();

        publicKey = RsaCode.getPublicKey(keyMap);
        privateKey = RsaCode.getPrivateKey(keyMap);
        System.err.println("公钥: \n\r" + publicKey);
        System.err.println("私钥： \n\r" + privateKey);
    }

    @Test
    public void test() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String inputStr = "abc";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RsaCode.encryptByPublicKey(data, publicKey);

        byte[] decodedData = RsaCode.decryptByPrivateKey(encodedData, privateKey);

        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        System.err.println("验证RSA对于同一内容【公钥加密——私钥解密】是否一致:"
                + BaseCode.compareValue(inputStr, outputStr));

    }

    @Test
    public void testSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String inputStr = "sign";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RsaCode.encryptByPrivateKey(data, privateKey);

        byte[] decodedData = RsaCode.decryptByPublicKey(encodedData, publicKey);

        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        System.err.println("验证RSA对于同一内容【私钥加密——公钥解密】是否一致:"
                + BaseCode.compareValue(inputStr, outputStr));

        System.err.println("私钥签名——公钥验证签名");
        // 产生签名
        String sign = RsaCode.sign(encodedData, privateKey);
        System.err.println("签名:\r" + sign);

        // 验证签名
        boolean status = RsaCode.verify(encodedData, publicKey, sign);
        System.err.println("状态:\r" + status);
        System.err.println("验证RSA对于同一内容【私钥签名——公钥验证签名】是否一致:"
                + status);

    }

}
