package com.fosun.fc.projects.creepers.pageprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.downloader.FosunLingDownloader;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 
 * <p>
 * Demo: 星之灵
 * </p>
 * 
 * @author MaXin
 * @since 2016年6月25日
 * @see
 */
@Component("fosunLingProcessor")
public class FosunLingProcessor implements PageProcessor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Site site;

    public FosunLingProcessor() {
        if (null == site) {
            site = Site.me().setDomain("gsxt.saic.gov.cn")
                    .setUserAgent(
                            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36")
                    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .addHeader("Accept-Encoding", "gzip, deflate").addHeader("Accept-Language", "zh-CN,zh;q=0.8")
                    .addHeader("Cache-Control", "max-age=0").addHeader("Connection", "keep-alive")
                    .addHeader("Content-Length", "115").addHeader("Content-Type", "application/x-www-form-urlencoded")
                    // .addHeader("Upgrade-Insecure-Requests", "1")
                    .setSleepTime(5000);
        }

    }

    @Override
    public void process(Page page) {

        logger.info(page.getHtml().toString());
        logger.info("页面爬取完毕。");
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new FosunLingProcessor())
                .setDownloader(new FosunLingDownloader())
                .addUrl("https://www.fosunling.com/login.dhtml").thread(1).runAsync();
    }
}
