package com.fosun.fc.projects.creepers.spider;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.pageprocessor.BusinessInfoName51Processor;
import com.fosun.fc.projects.creepers.pipeline.BusinessInfoPipline;

import us.codecraft.webmagic.Spider;

public class SpiderBusinessInfoName51StartTest extends SpiderBaseTest {

    @Autowired(required = true)
    private BusinessInfoName51Processor businessInfoName51Processor;

    @Autowired(required = true)
    private BusinessInfoPipline businessInfoPipline;

    @Autowired(required = true)
    private HttpRequestDownloader httpRequestDownloader;

    public void methodTest() {
        Spider.create(businessInfoName51Processor).addPipeline(businessInfoPipline).setDownloader(httpRequestDownloader)
                .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=list&yzmYesOrNo=no&maent.entname=510700000004075&random="
                        + new Date().getTime())
                .thread(2).runAsync();
    }

    @Test
    public void run() {
        // methodTest();
    }

}
