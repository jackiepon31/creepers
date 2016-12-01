package com.sz789;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.BaseConstant.SupperMan;

public class qqchaoren {
    static {
        System.loadLibrary("JDC");
    }

    // 按本地图片路径识别
    public native static String JRecYZM_A(String strYZMPath, String strVcodeUser, String strVcodePass,
            String strsoftkey);

    // 上传图片字节数组识别
    public native static String JRecByte_A(byte[] img, int len, String strVcodeUser, String strVcodePass,
            String strSoftkey);

    // 查询剩余点数
    public native static String JGetUserInfo(String strUser, String strPass);

    // 报告错误验证码
    public native static void JReportError(String strVcodeUser, String strYzmId);

    // 读本地图片至字节数组
    public static byte[] toByteArrayFromFile(String imageFile) throws Exception {
        InputStream is = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            is = new FileInputStream(imageFile);
            byte[] b = new byte[1024];
            int n;
            while ((n = is.read(b)) != -1) {
                out.write(b, 0, n);

            }      // end while
        } catch (Exception e) {
            throw new Exception("System error,SendTimingMms.getBytesFromFile", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                }      // end try
            }      // end if

        }      // end try
        return out.toByteArray();
    }

    public static Map<SupperMan, String> imageAnalytical(String filePath) throws Exception {
        Map<SupperMan, String> map = new HashMap<SupperMan, String>();
        // 打码配置信息开始
        map.put(BaseConstant.SupperMan.SUPPER_MAN_USER_NAME, "20161012");
        map.put(BaseConstant.SupperMan.SUPPER_MAN_PASS_WORD, "123456");
        map.put(BaseConstant.SupperMan.SUPPER_MAN_SOFT_ID, "55147");

        String user = map.get(BaseConstant.SupperMan.SUPPER_MAN_USER_NAME);
        String pass = map.get(BaseConstant.SupperMan.SUPPER_MAN_PASS_WORD);
        String softId = map.get(BaseConstant.SupperMan.SUPPER_MAN_SOFT_ID);// 软件ID,作者帐号后台添加
        // 打码配置信息结束

        String info = JGetUserInfo(user, pass);
        System.out.println("剩余点数:" + info);

        // String s = JRecYZM_A(yzmPath,user,pass,softId);
        // System.out.println(s);

        byte[] img = toByteArrayFromFile(filePath);
        String s = JRecByte_A(img, img.length, user, pass, softId);
        String[] result = s.split("\\|!\\|");
        if (result.length == 2) {
            System.out.println("识别结果:" + result[0]);
            System.out.println("验证码ID:" + result[1]);
            map.put(BaseConstant.SupperMan.SUPPER_MAN_RESULT, result[0]);
            map.put(BaseConstant.SupperMan.SUPPER_MAN_IMAGE_ID, result[1]);
            map.put(BaseConstant.SupperMan.SUPPER_MAN_STATUS, BaseConstant.SupperManType.SUCCEED.toString());
            return map;
        } else {
            System.out.println("识别失败,失败原因为:" + s);
            map.put(BaseConstant.SupperMan.SUPPER_MAN_STATUS, BaseConstant.SupperManType.FAIL.toString());
            map.put(BaseConstant.SupperMan.SUPPER_MAN_IMAGE_ID, result[1]);
            return map;
        }
    }

    public static void imageAnalyticalError(Map<SupperMan, String> map) {
        // 提交验证码,提交目标返回验证码对错
        JReportError(map.get(BaseConstant.SupperMan.SUPPER_MAN_USER_NAME),
                map.get(BaseConstant.SupperMan.SUPPER_MAN_IMAGE_ID));// 如果验证码错误,则报告错误,返回积分
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
        // 打码配置信息开始
        String user = "20161012";
        String pass = "123456";
        String softId = "55147";// 软件ID,作者帐号后台添加
        // 打码配置信息结束

        String info = JGetUserInfo(user, pass);
        System.out.println("剩余点数:" + info);

        // String s = JRecYZM_A(yzmPath,user,pass,softId);
        // System.out.println(s);

      /*  String yzmPath = "d:/image/20161012153126.png";
        byte[] img = toByteArrayFromFile(yzmPath);
        String s = JRecByte_A(img, img.length, user, pass, softId);
        String[] result = s.split("\\|!\\|");
        if (result.length == 2) {
            System.out.println("识别结果:" + result[0]);
            System.out.println("验证码ID:" + result[1]);
            // 提交验证码,提交目标返回验证码对错
            // JReportError(user,result[1]);//如果验证码错误,则报告错误,返回积分
        } else {
            System.out.println("识别失败,失败原因为:" + s);
        }*/
    }
}
