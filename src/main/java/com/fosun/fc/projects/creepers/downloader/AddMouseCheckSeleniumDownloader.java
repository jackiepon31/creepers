package com.fosun.fc.projects.creepers.downloader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
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
@Component("addMouseCheckSeleniumDownloader")
public class AddMouseCheckSeleniumDownloader extends SeleniumDownloader {

    private volatile WebDriverPool webDriverPool;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private int sleepTime = 0;

    private int poolSize = 1;

    /**
     * 新建
     *
     * @param chromeDriverPath
     *            chromeDriverPath
     */
    public AddMouseCheckSeleniumDownloader(String chromeDriverPath) {
        System.getProperties().setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    /**
     * Constructor without any filed. Construct chrome browser
     * 
     */
    public AddMouseCheckSeleniumDownloader() {
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
    public AddMouseCheckSeleniumDownloader setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    @SuppressWarnings({ "deprecation", "unused" })
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
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        Page page = new Page();
        List<Page> resultPage = new ArrayList<Page>();
        boolean isFirstTime = true;
        while (true) {
            WebDriverWait wait = new WebDriverWait(webDriver, 600);
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    return !webDriver.findElement(By.xpath("/html")).findElement(By.xpath("//*[@id=\"resultList\"]"))
                            .getText().contains("正在加载，请稍候...");
                }
            });
            // if (isFirstTime) {
            // webDriver.findElement(By.id("13_input_20")).click();
            // isFirstTime = false;
            // continue;
            // }
            WebElement webElement = webDriver.findElement(By.xpath("/html"));
            String content = webElement.getAttribute("outerHTML");
            Page eachPage = new Page();
            eachPage.setUrl(new PlainText(request.getUrl()));
            eachPage.setRequest(request);
            eachPage.setRawText(content);
            eachPage.setHtml(new Html(UrlUtils.fixAllRelativeHrefs(content, request.getUrl())));
            eachPage.putField(BaseConstant.PARAM_DTO_KEY, param);
            resultPage.add(eachPage);
            // 判决书List
            WebElement webElementList = webElement.findElement(By.xpath("//*[@id=\"resultList\"]"));

            logger.info("webElementList获取页面的resultList模块:=====================================");
            logger.info(webElementList.getText());
            logger.info("webElementList获取页面的resultList模块:=====================================end");
            // 分页模块
            WebElement webElementBottom = webElementList.findElement(By.xpath("//*[@id=\"pageNumber\"]"));

            // System.out.println("webElementBottom.getText()分页模块:\n" +
            // webElementBottom.getText());

            // 上一页
            WebElement webElementCurrentNum = webElementBottom.findElement(By.className("current"));
            logger.info("当前页：" + webElementCurrentNum.getText());
            // WebElement webElementPrev =
            // webElementBottom.findElement(By.className("prev"));
            // System.out.println("webElementprev.getTagName()上一页:" +
            // webElementPrev.getTagName());

            // 下一页
            WebElement webElementNext = webElementBottom.findElement(By.className("next"));
            logger.info("webElementNext.getTagName()下一页:" + webElementNext.getTagName());
            if ("span".equals(webElementNext.getTagName())) {
                page.setUrl(new PlainText(request.getUrl()));
                page.setRequest(request);
                page.setRawText(content);
                page.setHtml(new Html(UrlUtils.fixAllRelativeHrefs(content, request.getUrl())));
                logger.info("最后一页，爬取结束！ ");
                break;
            }
            webElementNext.click();
        }
        page.putField("pageList", resultPage);
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
