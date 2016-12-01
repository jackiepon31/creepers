package com.fosun.fc.projects.creepers.downloader;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.UrlUtils;

/**
 * 使用Selenium调用浏览器进行渲染。目前仅支持chrome。<br>
 * 需要下载Selenium driver支持。<br>
 *
 * @author MaXin <br>
 *         Date: 2016-7-6 13:21:56 <br>
 */
@Component("chromeGetImgSeleniumDownloader")
public class ChromeGetImgSeleniumDownloader implements Downloader, Closeable {

    private volatile WebDriverPool webDriverPool;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @SuppressWarnings("unused")
    private int sleepTime = 0;

    private int poolSize = 1;

    private String imageXPath;

    /**
     * 新建
     *
     * @param chromeDriverPath
     *            chromeDriverPath
     */
    public ChromeGetImgSeleniumDownloader(String chromeDriverPath) {
        System.getProperties().setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    public ChromeGetImgSeleniumDownloader() {
        System.getProperties().setProperty("webdriver.chrome.driver", CommonMethodUtils.getChromeWebDriver());
        System.getProperties().setProperty("phantomjs.binary.path", CommonMethodUtils.getPhantomJSWebDriver());
        System.getProperties().setProperty("webdriver.firefox.bin", CommonMethodUtils.getFirefoxWebDriver());
    }

    /**
     * set sleep time to wait until load success
     *
     * @param sleepTime
     *            sleepTime
     * @return this
     */
    public ChromeGetImgSeleniumDownloader setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    public ChromeGetImgSeleniumDownloader setImageXPath(String xpathString) {
        this.imageXPath = xpathString;
        return this;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Page download(Request request, Task task) {
        checkInit();
        WebDriver webDriver;
        try {
            webDriver = webDriverPool.get();
        } catch (InterruptedException e) {
            logger.warn("interrupted", e);
            return null;
        }
        logger.info("downloading page " + request.getUrl());
        webDriver.get(request.getUrl());
        WebDriver.Options manage = webDriver.manage();
        manage.window().maximize();
        Site site = task.getSite();
        if (site.getCookies() != null) {
            for (Map.Entry<String, String> cookieEntry : site.getCookies().entrySet()) {
                Cookie cookie = new Cookie(cookieEntry.getKey(), cookieEntry.getValue());
                manage.addCookie(cookie);
            }
        }

        WebDriverWait wait = new WebDriverWait(webDriver, 600);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath(imageXPath)).isDisplayed();
            }
        });
        WebElement webElement = webDriver.findElement(By.xpath("/html"));
        String content = webElement.getAttribute("outerHTML");
        // logger.info(content);
        webDriver.findElement(By.xpath(imageXPath)).click();
        WebElement imgElement = webDriver.findElement(By.xpath(imageXPath));
        Point location = imgElement.getLocation();
        Dimension size = imgElement.getSize();
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO
                    .read(new ByteArrayInputStream(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES)));
        } catch (WebDriverException | IOException e) {
            logger.info("图片截取失败！", e);
        }
        BufferedImage croppedImage = originalImage.getSubimage(location.getX(), location.getY(), size.getWidth(),
                size.getHeight());
        String imagePath = "/image/" + UUID.randomUUID() + ".jpg";
        try {
            File filePath = new File("/image");
            if (filePath.exists()) {
                logger.info("多级目录已经存在不需要创建！！");
            } else {
                filePath.mkdirs();
            }
            // ImageIO.write(originalImage, "JPEG", new File(imagePath));
            ImageIO.write(croppedImage, "JPEG", new File(imagePath));
        } catch (IOException e) {
            logger.info("验证码图片输出失败!", e);
        }
        SimpleDateFormat sf = new SimpleDateFormat("EEE%20MMM%20dd%20yyyy%20HH:mm:ss", Locale.ENGLISH);
        // "http://shixin.court.gov.cn/image.jsp?date=Wed%20Jul%2006%202016%2010:51:15%20GMT+0800%20(中国标准时间)"
        String imageValue = CommonMethodUtils.imageAnalytical(imagePath,
                BaseConstant.ImageAnalyticalType.FILE_PATH.getValue(),
                "http://shixin.court.gov.cn/image.jsp?date=" + sf.format(new Date()) + "%20GMT+0800%20(中国标准时间)");
        logger.info(imageValue);

        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(new File(imagePath + ".txt")));
            printWriter.write(imageValue);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Page page = new Page();
        page.setRawText(content);
        page.setHtml(new Html(UrlUtils.fixAllRelativeHrefs(content, request.getUrl())));
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        webDriverPool.returnToPool(webDriver);
        return page;
    }

    private void checkInit() {
        if (webDriverPool == null) {
            synchronized (this) {
                webDriverPool = new WebDriverPool(poolSize);
            }
        }
    }

    @Override
    public void setThread(int thread) {
        this.poolSize = thread;
    }

    @Override
    public void close() throws IOException {
        webDriverPool.closeAll();
    }
}
