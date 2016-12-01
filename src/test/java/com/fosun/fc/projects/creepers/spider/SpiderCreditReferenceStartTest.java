package com.fosun.fc.projects.creepers.spider;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.downloader.CreditReferenceSeleniumDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.pageprocessor.CreditReferenceCenterProcessor;
import com.fosun.fc.projects.creepers.pipeline.CreditReferencePipline;
import com.fosun.fc.projects.creepers.pipeline.CreditReferenceUploadFilePipline;

import us.codecraft.webmagic.Spider;

public class SpiderCreditReferenceStartTest extends SpiderBaseTest {
 
    @Autowired(required = true)
    private CreditReferenceCenterProcessor creditReferenceCenterProcessor;

    @Autowired(required = true)
    private CreditReferencePipline creditReferencePipline;

    @Autowired(required = true)
    private CreditReferenceUploadFilePipline creditReferenceUploadFilePipline;
    
    @SuppressWarnings("resource")
    public void methodTest() {
        CreepersLoginParamDTO param = new CreepersLoginParamDTO();
        param.setLoginName("110Roey");
        param.setPassword("lzp110");
        param.setMessageCaptchaValue("abq9kh");
        Spider.create(creditReferenceCenterProcessor).addPipeline(creditReferencePipline).addPipeline(creditReferenceUploadFilePipline)
                .addUrl("https://ipcrs.pbccrc.org.cn/").setDownloader(new CreditReferenceSeleniumDownloader().setParam(param)).thread(1).run();
    }

    @Test
    public void run() {
         methodTest();
    }

}
