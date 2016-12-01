package com.fosun.fc.projects.creepers.downloader;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
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
import com.fosun.fc.projects.creepers.constant.BaseConstant.SupperMan;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.fosun.fc.projects.creepers.utils.FileUtil;
import com.fosun.fc.projects.creepers.utils.ImageFilter;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.UrlUtils;

/**
 * 
 * <p>
 * Add Mouse Check Button to continue to get content:
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月10日
 * @see
 */
@Component("creditReferenceSeleniumDownloader")
public class CreditReferenceSeleniumDownloader extends SeleniumDownloader {

    private volatile WebDriverPool webDriverPool;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @SuppressWarnings("unused")
    private int sleepTime = 0;

    private int poolSize = 1;

    private int maxWaitTime = 45;

    protected CreepersLoginParamDTO param = new CreepersLoginParamDTO();


    /**
     * 新建
     *
     * @param chromeDriverPath
     *            chromeDriverPath
     */
    public CreditReferenceSeleniumDownloader(String chromeDriverPath) {
        System.getProperties().setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    /**
     * Constructor without any filed. Construct chrome browser
     * 
     */
    public CreditReferenceSeleniumDownloader() {
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
    public CreditReferenceSeleniumDownloader setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Page download(Request request, Task task) {
        param.setErrorPath(getClass().toString());
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
        Site site = task.getSite();
        if (site.getCookies() != null) {
            for (Map.Entry<String, String> cookieEntry : site.getCookies().entrySet()) {
                Cookie cookie = new Cookie(cookieEntry.getKey(), cookieEntry.getValue());
                manage.addCookie(cookie);
            }
        }

        // manage.window().setSize(pageSize);
        Page page;
        try {
            WebElement pageElement = webDriver.findElement(By.xpath("/html"));
            Dimension pageSize = pageElement.getSize();
            page = new Page();
            WebDriverWait wait = new WebDriverWait(webDriver, maxWaitTime);
            WebElement headerFrameElement = webDriver.findElement(By.xpath("//*[@id=\"headerFrame\"]"));
            Dimension headerFrameSize = headerFrameElement.getSize();
            WebElement conFrameElement = webDriver.findElement(By.xpath("//*[@id=\"conFrame\"]"));
            Dimension conFrameSize = conFrameElement.getSize();
            webDriver.switchTo().frame(conFrameElement);
            WebElement startButtonElement = webDriver.findElement(By.xpath("//a[@class=\"startBtn\"]"));
            startButtonElement.click();
            logger.info("====>>>进入登录页面！");
            int retryTimes = 0;
            boolean isReady = true;
            while (isReady) {
                wait.until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver webDriver) {
                        return isElementExsit(webDriver, "//*[@id=\"loginname\"]");
                    }
                });
                WebElement loginname = webDriver.findElement(By.xpath("//*[@id=\"loginname\"]"));
                loginname.clear();
                loginname.sendKeys(param.getLoginName());
                logger.info("====>>>账号：" + param.getLoginName());
                WebElement password = webDriver.findElement(By.xpath("//*[@id=\"password\"]"));
                password.clear();
                password.sendKeys(param.getPassword());
                logger.info("====>>>密码：" + param.getPassword());
                // *[@id="imgrc"]
                WebElement imgElement = webDriver.findElement(By.xpath("//*[@id=\"imgrc\"]"));
                // 获得webElement的位置和大小。
                Point location = imgElement.getLocation();
                Dimension size = imgElement.getSize();
                // 创建全屏截图。
                BufferedImage originalImage = null;
                try {
                    originalImage = ImageIO.read(
                            new ByteArrayInputStream(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES)));
                } catch (WebDriverException | IOException e) {
                    logger.info("图片截取失败！", e);
                }
                // 截取webElement所在位置的子图。
                int gapX = (pageSize.getWidth() - conFrameSize.getWidth()) / 2;
                int gapY = headerFrameSize.getHeight();
                BufferedImage croppedImage = originalImage.getSubimage(location.getX() + gapX + 15,
                        location.getY() + gapY + 1, size.getWidth() - 15, size.getHeight() - 2);
                String imagePath = "/image/" + UUID.randomUUID() + ".jpg";
                // String imagePath1 = "/image/" + UUID.randomUUID() + ".jpg";
                File imageFile = null;
                try {
                    File filePath = new File("/image");
                    if (filePath.exists()) {
                        logger.info("多级目录已经存在不需要创建！！");
                    } else {
                        filePath.mkdirs();
                    }
                    ImageFilter filter = new ImageFilter(croppedImage);
                    imageFile = new File(imagePath);
                    param.setCaptchaFilePath(imagePath);
                    ImageIO.write(filter.doFilterCreditReference(), "JPEG", imageFile);
                } catch (IOException e) {
                    logger.info("验证码图片输出失败!", e);
                }
                // @SuppressWarnings("resource")
                // Scanner scanner = new Scanner(System.in);// 创建输入流扫描器
                // System.out.println("请输入验证吗：");// 提示用户输入
                // resultValue = scanner.nextLine();// 获取用户输入的一行文本

                String resultValue = "";
                String imageId = "";
                while (StringUtils.isBlank(resultValue)) {
                    Map<SupperMan, String> imageMap = CommonMethodUtils.imageAnalyticalBySupperManHttp(imagePath);
                    resultValue = CommonMethodUtils.getImageAnalyticalValueByChaoren(imageMap);
                    logger.info("resultValue:" + resultValue);
                    imageId = CommonMethodUtils.getImageIdByChaoren(imageMap);
                    logger.info("imageId:" + imageId);
                }
                WebElement imageValueElement = webDriver.findElement(By.xpath("//*[@id=\"_@IMGRC@_\"]"));
                imageValueElement.sendKeys(resultValue);
                webDriver.findElement(By.xpath("//*[@class=\"inputBtn btn2\"]")).click();

                if (isElementExsit(webDriver, "//span[contains(.,\"验证码输入错误,请重新输入\")]")) {
                    logger.error("====>>>验证码错误，开始重试！");
                    retryTimes++;
                    CommonMethodUtils.imageAnalyticalErrorHttp(imageId);
                    webDriver.findElement(By.xpath("//*[@class=\"yzm_a\"]")).click();
                } else if (isElementExsit(webDriver, "//span[contains(.,\"登录名或密码错误！\")]")) {
                    logger.error("====>>>账号密码错误！");
                    logger.error("====>>>账号:" + param.getLoginName());
                    logger.error("====>>>密码:" + param.getPassword());
                    throw new RuntimeException("账号密码错误！人行征信报告爬取停止！");
                } else if (!isElementExsit(webDriver, "//span[contains(.,\"登录名或密码错误！\")]")
                        && !isElementExsit(webDriver, "//span[contains(.,\"验证码输入错误,请重新输入\")]")) {
                    isReady = false;
                    logger.info("====>>>验证码正确！");
                    param.setCaptchaValue(resultValue);
                    param.setCaptchaId(imageId);
                    FileUtil.renameCaptchaImageFileName(param);
                    logger.info("====>>>截屏验证码路径：" + imagePath);
                }

                if (retryTimes > site.getRetryTimes()) {
                    param.setErrorInfo("登录超过最大重试次数！");
                    param.setErrorPath(getClass().toString());
                    creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
                    isReady = false;
                    return null;
                }
            }
            // *[@id="menu"]/li[1]
            if (isElementExsit(webDriver, "//*[@id=\"popupbox\"]/input[2]")) {
                webDriver.findElement(By.xpath("//*[@id=\"popupbox\"]/input[2]")).click();
            }
            wait = new WebDriverWait(webDriver, maxWaitTime);
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    return isElementExsit(webDriver, "//*[@id=\"leftFrame\"]");
                }
            });
            // *[@id="leftFrame"]
            WebElement leftFrameElement = webDriver.findElement(By.xpath("//*[@id=\"leftFrame\"]"));
            webDriver.switchTo().frame(leftFrameElement);
            wait = new WebDriverWait(webDriver, maxWaitTime);
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    return isElementExsit(webDriver, "//*[@id=\"menu\"]/li[contains(.,\"信息服务 >\")]/a");
                }
            });
            // *[@id="menu"]/li[2]/a
            webDriver.findElement(By.xpath("//*[@id=\"menu\"]/li[contains(.,\"信息服务 >\")]/a")).click();
            wait = new WebDriverWait(webDriver, maxWaitTime);
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    return webDriver.findElement(By.xpath("//*[@id=\"menu\"]/li[contains(.,\"信息服务 >\")]/ul/li[2]/a"))
                            .isDisplayed();
                }
            });
            // *[@id="menu"]/li[2]/ul/li[2]/a
            webDriver.findElement(By.xpath("//*[@id=\"menu\"]/li[contains(.,\"信息服务 >\")]/ul/li[2]/a")).click();
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(conFrameElement);
            // *[@id="mainFrame"]
            WebElement mainFrameElement = webDriver.findElement(By.xpath("//*[@id=\"mainFrame\"]"));
            webDriver.switchTo().frame(mainFrameElement);
            WebElement tradeCodeElement = webDriver.findElement(By.xpath("//*[@id=\"tradeCode\"]"));
            tradeCodeElement.sendKeys(param.getMessageCaptchaValue());
            logger.info("====>>>短信验证码：" + param.getMessageCaptchaValue());
            WebElement radiobutton1Element = webDriver.findElement(By.xpath("//*[@id=\"radiobutton1\"]"));
            radiobutton1Element.click();
            String current_handle = webDriver.getWindowHandle();
            WebElement nextstepElement = webDriver.findElement(By.xpath("//*[@id=\"nextstep\"]"));
            nextstepElement.click();
            WebDriver newWindow = null;
            Set<String> all_handles = webDriver.getWindowHandles();
            Iterator<String> it = all_handles.iterator();
            String handle = null;
            while (it.hasNext()) {
                handle = it.next();
                if (current_handle == handle)
                    continue;
                newWindow = webDriver.switchTo().window(handle);
            }
            wait = new WebDriverWait(webDriver, maxWaitTime);
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    return isElementExsit(webDriver, "/html");
                }
            });
            WebElement webElement = newWindow.findElement(By.xpath("/html"));
            String content = webElement.getAttribute("outerHTML");
            logger.info("content:" + content);
            page.setUrl(new PlainText(request.getUrl()));
            page.setRequest(request);
            page.setRawText(content);
            page.putField(BaseConstant.PARAM_DTO_KEY, param);
            page.setHtml(new Html(UrlUtils.fixAllRelativeHrefs(content, request.getUrl())));
            webDriverPool.returnToPool(webDriver);
            return page;
        } catch (Exception e) {
            StringBuffer errorInfo = new StringBuffer("downloader error \t:");
            errorInfo.append(request.getUrl()).append("\n");
            errorInfo.append(e.getMessage());
            logger.error(errorInfo.toString());
            param.setErrorInfo(errorInfo.toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            return null;
        }

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

    public CreepersLoginParamDTO getParam() {
        return param;
    }

    public CreditReferenceSeleniumDownloader setParam(CreepersLoginParamDTO param) {
        this.param = param;
        return this;
    }

}
