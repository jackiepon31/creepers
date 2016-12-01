package com.fosun.fc.projects.creepers.utils;

import org.springframework.util.StringUtils;

import com.fosun.fc.modules.utils.PropertiesLoader;

/**
 * 
 * <p>
 * description: 配置文件操作类
 * </p>
 * 
 * @author Pengyk
 * @since 2016年10月10日
 * @see
 */
public class PropertiesUtil {

    private static PropertiesLoader applicationProperties = null;

    private static void loadApplication() {
        applicationProperties = new PropertiesLoader("classpath:/application.properties");
    }

    public static String getApplicationValue(String key) {
        return getApplicationValue(key,"");
    }
    
    public static String getApplicationValue(String key,String defaul) {

        if (applicationProperties == null) {
            loadApplication();
        }
        Object value = applicationProperties.getProperty(key, "");
        if (StringUtils.isEmpty(value)) {
            System.err.println("cannot find the property of " + key + " in the file:" + " application.properties");
            return defaul;
        } else {
            return value.toString().trim();
        }
    }
    
}
