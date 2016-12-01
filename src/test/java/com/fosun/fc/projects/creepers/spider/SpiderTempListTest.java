package com.fosun.fc.projects.creepers.spider;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.downloader.SeleniumDownloader;
import com.fosun.fc.projects.creepers.pageprocessor.BusinessInfoNameTempProcessor;
import com.fosun.fc.projects.creepers.pipeline.BusinessInfoTempPipline;

import us.codecraft.webmagic.Spider;

public class SpiderTempListTest extends SpiderBaseTest {

    @Autowired(required = true)
    private BusinessInfoNameTempProcessor businessInfoNameTempProcessor;

    @Autowired(required = true)
    private BusinessInfoTempPipline businessInfoTempPipline;

    @Autowired(required = true)
    private SeleniumDownloader seleniumDownloader;

    public void methodTest() {
        // int pageTotal = 42365;
        Spider spider = Spider.create(businessInfoNameTempProcessor).addPipeline(businessInfoTempPipline)
                .setDownloader(seleniumDownloader).thread(1);
        spider.addUrl("https://www.sgs.gov.cn/notice/search/ent_except_list");
        spider.runAsync();
    }

    @Test
    public void run() {
        // methodTest();
    }

}
