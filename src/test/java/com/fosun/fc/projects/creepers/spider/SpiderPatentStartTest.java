package com.fosun.fc.projects.creepers.spider;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.downloader.SeleniumDownloader;
import com.fosun.fc.projects.creepers.pageprocessor.PatentProcessor;
import com.fosun.fc.projects.creepers.pipeline.PatentPipline;

import us.codecraft.webmagic.Spider;

public class SpiderPatentStartTest extends SpiderBaseTest {

    @Autowired(required = true)
    private PatentProcessor patentProcessor;

    @Autowired(required = true)
    private PatentPipline patentPipline;

    @Autowired(required = true)
    private SeleniumDownloader seleniumDownloader;

    public void methodTest() {
        String companyName = "深圳市裕同包装科技股份有限公司";
        Spider.create(patentProcessor).addPipeline(patentPipline).setDownloader(seleniumDownloader)
                .addUrl("http://cpquery.sipo.gov.cn//txnQueryOrdinaryPatents.do?select-key%3Ashenqingh=&select-key%3Azhuanlimc=&select-key%3Ashenqingrxm="
                        + companyName
                        + "&select-key%3Azhuanlilx=&select-key%3Ashenqingr_from=&select-key%3Ashenqingr_to=&attribute-node:record_start-row=1&attribute-node:record_page-row=1000&")
                .thread(2).runAsync();
    }

    @Test
    public void run() {
        // methodTest();
    }

}
