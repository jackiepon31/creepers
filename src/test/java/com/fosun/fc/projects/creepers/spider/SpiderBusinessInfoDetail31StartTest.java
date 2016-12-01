package com.fosun.fc.projects.creepers.spider;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.pageprocessor.BusinessInfoDetail31Processor;
import com.fosun.fc.projects.creepers.pipeline.BusinessInfoDetailPipline;

import us.codecraft.webmagic.Spider;

public class SpiderBusinessInfoDetail31StartTest extends SpiderBaseTest {

    @Autowired(required = true)
    private BusinessInfoDetail31Processor businessInfoDetail31Processor;

    @Autowired(required = true)
    private BusinessInfoDetailPipline businessInfoDetailPipline;

    @Autowired(required = true)
    private HttpRequestDownloader httpRequestDownloader;

    public void methodTest() {
        Spider.create(businessInfoDetail31Processor).addPipeline(businessInfoDetailPipline)
                .setDownloader(httpRequestDownloader)
                .addUrl("https://www.sgs.gov.cn/notice/notice/view?uuid=FFN9nvGXZhNPPsj0P63DQLhFH7AIwilg&tab=01")
                .thread(1).runAsync();
    }

    @Test
    public void run() {
        // methodTest();
    }

}
