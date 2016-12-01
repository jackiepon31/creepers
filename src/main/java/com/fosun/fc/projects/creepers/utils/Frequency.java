package com.fosun.fc.projects.creepers.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.fosun.fc.projects.creepers.redis.service.Impl.AbstractRedisCacheService;

/*
 * 频率控制器
 */
@Component
public class Frequency extends AbstractRedisCacheService<Object, Object> {

    /*
     * 反射调用
     */
    @SuppressWarnings("rawtypes")
    public static Object reflectInvoke(String className, String methodName, Class[] parameterTypes,
            Object[] parameterValues) {

        Class<?> clazz = null;
        Object object = null;
        try {
            clazz = Class.forName(className);
            // 手动获取spring容器的对象
            WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
            className = toLowerCaseFirstOne(className.substring(className.lastIndexOf(".") + 1));
            object = wac.getBean(className);
            // 原始方法：new一个对象
            // object = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        clazz = object.getClass();
        Method method = null;
        Object result = null;
        try {
            method = clazz.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        try {
            result = method.invoke(object, parameterValues);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    public void frequency(String className, String methodName, Class[] parameterTypes, Object[] parameterValues,
            String maxTimeInterVal, String maxTimes, String redisKey, String errorContent) {
        String maxTI = StringUtils.isBlank(PropertiesUtil.getApplicationValue(maxTimeInterVal)) ? "0"
                : PropertiesUtil.getApplicationValue(maxTimeInterVal);
        String maxTs = StringUtils.isBlank(PropertiesUtil.getApplicationValue(maxTimes)) ? "0"
                : PropertiesUtil.getApplicationValue(maxTimes);
        String mutiData = null == get(redisKey) ? "0|0| " : (String) get(redisKey);
        Long timestamp = Long.valueOf(mutiData.split("\\|")[0]);
        String times = mutiData.split("\\|")[1];
        String content = mutiData.split("\\|")[2];
        if (!content.equals(errorContent)) {
            Long nowTS = new Date().getTime();
            if (nowTS - timestamp >= Long.valueOf(maxTI)) {
                reflectInvoke(className, methodName, parameterTypes, parameterValues);
                set(redisKey, nowTS + "|1|" + errorContent);
            } else {
                if (Integer.valueOf(maxTs) - Integer.valueOf(times) > 0) {
                    reflectInvoke(className, methodName, parameterTypes, parameterValues);
                    set(redisKey, nowTS + "|" + (times + 1) + "|" + errorContent);
                }
            }
        }
    }

    @Override
    public void refresh() {
    }

    public static String toLowerCaseFirstOne(String s) {
        char[] cs = s.toCharArray();
        if(cs[0]<91 && cs[0]>64)
        cs[0] += 32;
        return String.valueOf(cs);
    }

    public static void main(String[] args) {
        String s = "Ai";
        s = toLowerCaseFirstOne(s);
        System.out.println(s);
    }
}
