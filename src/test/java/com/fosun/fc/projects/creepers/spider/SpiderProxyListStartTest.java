package com.fosun.fc.projects.creepers.spider;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.pageprocessor.ProxyMimvpProcessor;
import com.fosun.fc.projects.creepers.pipeline.ProxyMimvpPipline;

import us.codecraft.webmagic.Spider;

public class SpiderProxyListStartTest extends SpiderBaseTest {

    @Autowired(required = true)
    private ProxyMimvpProcessor proxyMimvpProcessor;

    @Autowired(required = true)
    private ProxyMimvpPipline proxyMimvpPipline;

    @Autowired(required = true)
    private HttpRequestDownloader httpRequestDownloader;

    public void methodTest() {
        Spider.create(proxyMimvpProcessor).addPipeline(proxyMimvpPipline).setDownloader(httpRequestDownloader)
                .addUrl("http://proxy.mimvp.com/free.php?proxy=in_tp")
                .addUrl("http://proxy.mimvp.com/free.php?proxy=in_hp").thread(2).runAsync();
    }

    @Test
    public void run() {
         methodTest();
    }

}
