package com.fosun.fc.projects.creeps.web.rest;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ImageAnalyticalTest {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        SimpleDateFormat sf = new SimpleDateFormat("EEE%20MMM%20dd%20yyyy%20HH:mm:ss", Locale.ENGLISH);
        // http://shixin.court.gov.cn/image.jsp?date=Tue%20Jul%2005%202016%2013:42:12%20GMT+0800%20(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)

        for (int i = 0; i < 5; i++) {
        }

        // String imagePath =
        // "http://shixin.court.gov.cn/image.jsp?date=Wed%20Jul%2006%202016%2010:51:15%20GMT+0800%20(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)";
        // String imageValue = CommonMethodUtils.imageAnalytical(imagePath,
        // BaseConstant.ImageAnalyticalType.URL_PATH.getValue(), imagePath);
        // System.out.println(imageValue);
    }

}
