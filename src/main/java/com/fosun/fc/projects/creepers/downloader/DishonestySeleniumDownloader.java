package com.fosun.fc.projects.creepers.downloader;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
 * 使用Selenium调用浏览器进行渲染。<br>
 * 需要下载Selenium driver支持。<br>
 * 支持Chrome Driver PhantomJS Firefox 暂时没有下载驱动，需要安装驱动后支持。
 *
 * @author MaXin<br>
 *         Date: 2016-7-6 13:18:51 <br>
 */
@Component("dishonestySeleniumDownloader")
public class DishonestySeleniumDownloader implements Downloader, Closeable {

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
    public DishonestySeleniumDownloader(String chromeDriverPath) {
        System.getProperties().setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    /**
     * Constructor without any filed. Construct PhantomJS browser
     * 
     */
    public DishonestySeleniumDownloader() {
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
    public DishonestySeleniumDownloader setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    public DishonestySeleniumDownloader setImageXPath(String xpathString) {
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

//        WebDriverWait wait = new WebDriverWait(webDriver, 600);
//        wait.until(new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver webDriver) {
//                return webDriver.findElement(By.xpath(imageXPath)).isDisplayed();
//            }
//        });
        webDriver.findElement(By.xpath("//*[@id=\"pName\"]")).sendKeys("金国平");
        WebElement imgElement = webDriver.findElement(By.xpath(imageXPath));
        // 获得webElement的位置和大小。
        Point location = imgElement.getLocation();
        Dimension size = imgElement.getSize();
        // 创建全屏截图。
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO
                    .read(new ByteArrayInputStream(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES)));
        } catch (WebDriverException | IOException e) {
            logger.info("图片截取失败！", e);
        }
        // 截取webElement所在位置的子图。
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
            ImageIO.write(croppedImage, "JPEG", new File(imagePath));
        } catch (IOException e) {
            logger.info("验证码图片输出失败!", e);
        }
        String imageURL = "http://shixin.court.gov.cn/image.jsp";
        String imageValue = CommonMethodUtils.imageAnalytical(imagePath,
                BaseConstant.ImageAnalyticalType.FILE_PATH.getValue(),imageURL);
        logger.info("OCR_KING:"+imageValue);
        String resultValue = CommonMethodUtils
                .getNodeValue(
                        CommonMethodUtils.getChildElement(CommonMethodUtils
                                .getChildElement(CommonMethodUtils.getRootElement(imageValue), "ResultList"), "Item"),
                "Result");
        logger.info(resultValue);
        //// *[@id=\"pCode\"]
        webDriver.findElement(By.xpath("//*[@id=\"pCode\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"pCode\"]")).sendKeys(resultValue);
        // *[@id=\"searchForm\"]/div/table/tbody/tr[5]/td/div
        webDriver.findElement(By.xpath("//*[@id=\"searchForm\"]/div/table/tbody/tr[5]/td/div")).click();

        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(new File(imagePath + ".txt")));
            printWriter.write(imageValue);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        webDriver.switchTo().frame("contentFrame");
        WebElement webElement = webDriver.findElement(By.xpath("/html"));
        String content = webElement.getAttribute("outerHTML");
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
