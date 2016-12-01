package com.fosun.fc.projects.creepers.downloader;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
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
 * @author code4crafter@gmail.com <br>
 *         Date: 13-7-26 <br>
 *         Time: 下午1:37 <br>
 */
@Component("seleniumDownloader")
public class SeleniumDownloader implements Downloader, Closeable {

    private volatile WebDriverPool webDriverPool;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private int sleepTime = 0;

    private int poolSize = 1;

    protected CreepersParamDTO param = new CreepersParamDTO();

    @Autowired
    protected ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;

    /**
     * 新建
     *
     * @param chromeDriverPath
     *            chromeDriverPath
     */
    public SeleniumDownloader(String chromeDriverPath) {
        System.getProperties().setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    /**
     * Constructor without any filed. Construct PhantomJS and chrome browser
     * 
     */
    public SeleniumDownloader() {
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
    public SeleniumDownloader setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Page download(Request request, Task task) {
        param.setErrorPath(getClass().toString());
        try {
            checkInit();
            WebDriver webDriver;
            try {
                webDriver = webDriverPool.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.warn("interrupted:" + e);
                param.setErrorInfo(e.getCause().getClass() + e.getCause().getMessage());
                creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
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
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
                param.setErrorInfo(e.getCause().getClass() + e.getCause().getMessage());
                creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            }
            WebElement webElement = webDriver.findElement(By.xpath("/html"));
            String content = webElement.getAttribute("outerHTML");
            Page page = new Page();
            page.setRawText(content);
            page.setHtml(new Html(UrlUtils.fixAllRelativeHrefs(content, request.getUrl())));
            page.setUrl(new PlainText(request.getUrl()));
            page.setRequest(request);
            page.putField(BaseConstant.PARAM_DTO_KEY, param);
            webDriverPool.returnToPool(webDriver);
            return page;
        } catch (Exception e) {
            e.printStackTrace();
            param.setErrorInfo(e.getCause().getClass() + e.getCause().getMessage());
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

    public CreepersParamDTO getParam() {
        return param;
    }

    public SeleniumDownloader setParam(CreepersParamDTO param) {
        this.param = param;
        return this;
    }
    
    public boolean isElementExsit(WebDriver driver, String xpath) {
        boolean flag = false;
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            flag = null != element;
        } catch (NoSuchElementException e) {
            logger.debug("Element:" + xpath + " is not exsit!");
        }
        return flag;
    }
}
