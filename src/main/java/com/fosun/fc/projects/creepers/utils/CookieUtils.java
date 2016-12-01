package com.fosun.fc.projects.creepers.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


import org.nlpcn.commons.lang.util.StringUtil;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class CookieUtils {

    public static void addCookieToFile(WebDriver driver){
        addCookieToFile(driver, null);
    }
    public static void addCookieToFile(WebDriver driver,String filePath) {
        if (StringUtil.isBlank(filePath)) {
            filePath = "/cookie/broswer.data";
        }
        File file = new File(filePath);
        if (file.exists()) {
        } else {
            file.mkdirs();
        }
        try {
            // delete file if exists
            file.delete();
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Cookie ck : driver.manage().getCookies()) {
                bw.write(ck.getName() + ";" + ck.getValue() + ";" + ck.getDomain() + ";" + ck.getPath() + ";"
                        + ck.getExpiry() + ";" + ck.isSecure());
                bw.newLine();
            }
            bw.flush();
            bw.close();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("cookie write to file");
        }
    }
}
