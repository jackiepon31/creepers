package com.fosun.fc.projects.creeps.web.rest;

import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MethodTest {

    public static void main(String[] args) throws ParseException, IOException {

        // System.out.println(CommonMethodUtils.unescape("%E5%8C%BB%E8%8D%AF%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8"));
        // System.out.println(CommonMethodUtils.toUtf8String("西藏诺活医药有限公司"));
        // SimpleDateFormat sf = new
        // SimpleDateFormat("EEE%20MMM%20dd%20yyyy%20HH:mm:ss",Locale.ENGLISH);
        // sf.format(new Date())+"%20GMT%200800 (中国标准时间)";
        // System.out.println(sf.format(new Date())+"%20GMT%200800 (中国标准时间)");
        // System.out.println("Wed Jun 29 2016 15:20:04 GMT 0800 (中国标准时间)");

        // BufferedImage inputStream = ImageIO.read(new
        // File("E:/image/image.jpg"));
        // System.out.println(inputStream.getSource());
        // ImageIO.write(inputStream, ".png", new
        // File("E:/image/"+UUID.randomUUID()+".png"));
        // Random random = new Random();
        // System.out.println(random.nextInt(100));

        // System.out.println(BaseConstant.SeleniumWebDriverPath.PHANTOM_JS_PATH.getValue());
        // System.out.println(System.getProperty("os.name"));
//        StringBuffer readFile = new StringBuffer();
//        BufferedReader br = new BufferedReader(new FileReader(new File("E:/9.txt")));
//        String readLine = "";
//        while ((readLine = br.readLine()) != null) {
//            readFile.append(readLine);
//        }
//        br.close();
//        String result = readFile.toString();
//        result = StringEscapeUtils.unescapeHtml4(StringEscapeUtils.unescapeJava(result));
//        System.out.println(result);
        // System.out.println(StringEscapeUtils.unescapeHtml4("<td
        // class=\"\&quot;t1\&quot;\" colspan=\"\&quot;2\&quot;\"
        // width=\"\&quot;100%\&quot;\">\r\n\t\t\t\t\t\t <font
        // color=\"\&quot;red\&quot;\">仅供参考，不具有法律效力</font>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t"));
        
        
        WebDriver web = new ChromeDriver();
        web.get("www.baidu.com");
        WebElement webElement = web.findElement(By.xpath("/html"));
        String content = webElement.getAttribute("outerHTML");
        System.out.println(content);
    }
}
