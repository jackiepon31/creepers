package com.fosun.fc.projects.creepers.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fosun.fc.modules.utils.JsonResult;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;

/**
 * 
 * <p>
 * description: 文件操作类
 * </p>
 * 
 * @author Pengyk
 * @since 2016年10月10日
 * @see
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private String encodeName;

    public String getEncodeName() {
        return encodeName;
    }

    public void setEncodeName(String encodeName) {
        this.encodeName = encodeName;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String upload(String filePath) {
        logger.info("上传文件开始。。。。。。。。。");
        String imageUrl = "";
        String url = PropertiesUtil.getApplicationValue(BaseConstant.SK_UPLOAD_URL);
        ;
        RestTemplate rest = new RestTemplate();
        FileSystemResource resource = new FileSystemResource(new File(filePath));
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("requestCode", PropertiesUtil.getApplicationValue(BaseConstant.SK_REQUESTCODE));
        param.add("invokerName", PropertiesUtil.getApplicationValue(BaseConstant.SK_INVOKERNAME));
        param.add("file", resource);
        param.add("bizGroup", BaseConstant.SK_BIZ_GROUP);
        param.add("bizSub", resource.getFilename());
        param.add("sysName", BaseConstant.SK_SYS_NAME);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(param);
        ResponseEntity<JsonResult> responseEntity = rest.exchange(url, HttpMethod.POST, httpEntity, JsonResult.class);
        JsonResult jsonResult = responseEntity.getBody();
        if (JsonResult.JSON_RESULT_TYPE.success.name().equals(jsonResult.getType())) {
            Map<String, String> dataMap = (Map<String, String>) jsonResult.getData();
            imageUrl = getRealName(dataMap.get("encodeName"));
            // 删除本地文件
            delete(filePath);
        }
        logger.debug("上传文件结束。。。。。。。。。");
        return imageUrl;
    }

    public static void delete(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static String getRealName(String encodeName) {
        String realPath = "";
        String downloadApi = PropertiesUtil.getApplicationValue(BaseConstant.SK_DOWNLOAD_URL);
        RestTemplate rest = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("requestCode", PropertiesUtil.getApplicationValue(BaseConstant.SK_REQUESTCODE));
        params.add("invokerName", PropertiesUtil.getApplicationValue(BaseConstant.SK_INVOKERNAME));
        params.add("bizGroup", BaseConstant.SK_BIZ_GROUP);
        params.add("bizSub", encodeName);
        params.add("encodeName", encodeName);
        params.add("sysName", BaseConstant.SK_SYS_NAME);
        String url = UriComponentsBuilder.fromHttpUrl(downloadApi).queryParams(params).build().toUriString();
        JsonResult jsonResult = rest.getForObject(url, JsonResult.class);
        if (JsonResult.JSON_RESULT_TYPE.success.name().equals(jsonResult.getType())) {
            Map<String, String> dataMap = (Map<String, String>) jsonResult.getData();
            realPath = dataMap.get("realPath");
        }
        logger.debug("文件Link：" + realPath);
        return realPath;
    }

    public static byte[] input2byte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

    /**
     * 
     * <p>
     * description:检查文件路径，文件路径/文件存在，则不作处理,返回true，文件路径不存在，则创建路径，返回true；文件不存在，
     * 则返回false。
     * </p>
     * 
     * @param filePath
     * @author MaXin
     * 
     * @see 2016-10-13 17:30:41
     */
    public static boolean checkFilePath(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            if (file.exists()) {
                file.mkdirs();
                logger.info("文件路径不存在，已创建路径。");
            } else {
                logger.info("路径存在。");
            }
            return true;
        } else {
            if (file.exists()) {
                logger.info("文件存在");
                return true;
            } else {
                logger.info("文件不存在");
                return false;
            }
        }
    }

    public static String readFile2String(String fileFullName) {
        StringBuffer content = new StringBuffer();
        if (checkFilePath(fileFullName)) {
            File file = new File(fileFullName);
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("文件读取错误!  FilePath:" + fileFullName);
            }
        }
        return content.toString();
    }

    /**
     * 
     * <p>
     * description:创建验证码图片文件
     * </p>
     * 
     * @param imageFilePath
     * @param imageInputStream
     * @return
     * @author MaXin
     * @since 2016-10-31 15:21:15
     * @see
     */
    public static String creatCaptchaImageFile(String imageFilePath, InputStream imageInputStream) {
        try {
            if (StringUtils.isBlank(imageFilePath)) {
                imageFilePath = creatCaptchaImageFileName();
            }
            ImageFilter imageFiler = new ImageFilter(ImageIO.read(imageInputStream));
            ImageIO.write(imageFiler.changeGrey(), "JPEG", new File(imageFilePath));
            return imageFilePath;
        } catch (IOException e) {
            logger.error("生成验证码图片错误！！FilePath:" + imageFilePath);
            return "";
        }

    }

    /**
     * 
     * <p>
     * description:创建文件 不传入文件名，默认自动生成文件名 /image/随机uuid.jpg
     * </p>
     * 
     * @param fileInputStream
     *            文件流
     * @return
     * @author MaXin
     * @since 2016-11-30 12:53:29
     * @see
     */
    public static String creatDownloadFile(InputStream fileInputStream, String fileSuffix) {
        return creatDownloadFile(null, fileInputStream, fileSuffix);
    }

    public static String creatDownloadFile(String filePath, InputStream fileInputStream, String fileSuffix) {
        try {
            if (StringUtils.isBlank(filePath)) {
                filePath = createRandomUUIDFileName(fileSuffix);
            }
            FileOutputStream outf = new FileOutputStream(new File(filePath));
            outf.write(input2byte(fileInputStream));
            outf.flush();
            outf.close();
            return filePath;
        } catch (IOException e) {
            logger.error("生成文件错误！！FilePath:" + filePath);
            return "";
        }

    }

    public static String creatCaptchaImageFile(InputStream imageInputStream) {
        return creatCaptchaImageFile(null, imageInputStream);
    }

    /**
     * 
     * <p>
     * description: 默认自动生成文件名 /image/随机uuid.jpg
     * </p>
     * 
     * @return
     * @author MaXin
     * @since 2016-10-31 15:26:20
     * @see
     */
    public static String creatCaptchaImageFileName() {
        return createRandomUUIDFileName(BaseConstant.FileSuffix.JPG.getValue());
    }

    /**
     * 
     * <p>
     * description: 默认自动生成文件名 /image/随机uuid+文件后缀
     * </p>
     * 
     * @return
     * @author MaXin
     * @since 2016-11-30 12:10:20
     * @see
     */
    public static String createRandomUUIDFileName(String fileSuffix) {
        return BaseConstant.IMAGE_FILE_BASE_PATH
                + BaseConstant.FILE_PATH_SEPARATION + UUID.randomUUID() + fileSuffix;
    }

    /**
     * 
     * <p>
     * description: 通过DTO对打码成功的图片进行重命名。
     * </p>
     * 
     * @param param
     *            登录或带有验证信息的DTO
     * @return 重命名后的图片名
     * @author MaXin
     * @since 2016-11-2 11:14:44
     * @see
     */
    public static String renameCaptchaImageFileName(CreepersLoginParamDTO param) {
        StringBuffer newFileName = new StringBuffer();
        if (StringUtils.isNotBlank(param.getCaptchaFilePath())) {
            File file = new File(param.getCaptchaFilePath());
            if (file.exists()) {
                // newFileName 格式： /image/时间戳_验证码值_打码超人ImageID_任务类型.jpg
                newFileName.append(BaseConstant.IMAGE_FILE_BASE_PATH).append(BaseConstant.FILE_PATH_SEPARATION)
                        .append(param.getTaskType()).append(BaseConstant.FILE_NAME_UNDERLINED)
                        .append(new Date().getTime()).append(BaseConstant.FILE_NAME_UNDERLINED)
                        .append(param.getCaptchaValue()).append(BaseConstant.FILE_NAME_UNDERLINED)
                        .append(param.getCaptchaId()).append(BaseConstant.FileSuffix.JPG.getValue());
                file.renameTo(new File(newFileName.toString()));
                logger.info("======>>> 重命名后的验证码图片路径：" + newFileName.toString());
            }
        }
        return newFileName.toString();
    }

    public static void main(String[] args) {
        // try {
        // String filename =
        // FileUtil.getRealName("MjAxNjA1MzAwNzM4MjNfX0RELTJiOTctMjAxNjA1MzAxNTIzLnBkZg==");
        // System.out.println("filename:" + filename);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        String fileFullName = "d:/pdf1/";
        System.out.println(checkFilePath(fileFullName));
    }
}
