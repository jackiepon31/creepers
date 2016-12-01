package com.fosun.fc.projects.creepers.spider;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.pageprocessor.BusinessInfoDetail51Processor;
import com.fosun.fc.projects.creepers.pipeline.BusinessInfoDetailPipline;

import us.codecraft.webmagic.Spider;

public class SpiderBusinessInfoDetail51StartTest extends SpiderBaseTest {

    @Autowired(required = true)
    private BusinessInfoDetail51Processor businessInfoDetail51Processor;

    @Autowired(required = true)
    private BusinessInfoDetailPipline businessInfoDetailPipline;

    @Autowired(required = true)
    private HttpRequestDownloader httpRequestDownloader;

    public void methodTest() {
        Spider.create(businessInfoDetail51Processor).addPipeline(businessInfoDetailPipline)
                .setDownloader(httpRequestDownloader)
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=dcdyInfo&maent.pripid=5106810222229093&czmk=czmk4&random="
                // + new Date().getTime())
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=gqczxxInfo&maent.pripid=CD6675400159&czmk=czmk4&random="
                // + new Date().getTime())
                // // 股权出质
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=gqczxxInfo&maent.pripid=CD6675400159&czmk=czmk4&random="
                // + new Date().getTime())
                // // 动产质押
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=dcdyInfo&maent.pripid=5106810222229093&czmk=czmk4&random="
                // + new Date().getTime())
                // // 基本信息
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=qyInfo&maent.pripid=CD6675400159&czmk=czmk1&from=&random="
                // + new Date().getTime())
                // // 备案信息
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=baInfo&maent.pripid=CD6675400159&czmk=czmk2&from=&random="
                // + new Date().getTime())
                // // 行政处罚
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=cfInfo&maent.pripid=5115240000000052&czmk=czmk3&from=&random="
                // + new Date().getTime())
                // // 严重违法记录
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=yzwfInfo&maent.pripid=5115240000000052&czmk=czmk14&from=&random="
                // + new Date().getTime())
                // // 抽查信息
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=ccjcInfo&maent.pripid=5117023000061844&czmk=czmk7&from=&random="
                // + new Date().getTime())
                // 企业年报
                .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=qygsInfo&maent.pripid=CD6675400159&czmk=czmk8&random="
                        + new Date().getTime())
                .thread(5).runAsync();
    }

    @Test
    public void run() {
        // methodTest();
    }

}
