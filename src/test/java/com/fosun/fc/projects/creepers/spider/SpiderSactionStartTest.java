package com.fosun.fc.projects.creepers.spider;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.pageprocessor.CreditChina.AdministrationSactionProcessor;
import com.fosun.fc.projects.creepers.pipeline.CreditChina.AdministrationSactionPipeline;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

public class SpiderSactionStartTest extends SpiderBaseTest {

    @Autowired(required=true)
    private AdministrationSactionProcessor administrationSactionProcessor;
    
    @Autowired(required=true)
    private AdministrationSactionPipeline administrationSactionPipeline;
    
    public void methodTest() {
        CreepersParamDTO param = new CreepersParamDTO();
        param.setTaskType(BaseConstant.TaskListType.SACTION_LIST.getValue());
        String url = "http://www.creditchina.gov.cn/publicity_info_search?t=" + new Date().getTime();
        param.putNameValuePair("keyword", "");
        param.putNameValuePair("searchtype", "1");
        param.putNameValuePair("objectType", "2");
        param.putNameValuePair("areas", "");
        param.putNameValuePair("creditType", "");
        param.putNameValuePair("dataType", "0");
        param.putNameValuePair("areaCode", "");
        param.putNameValuePair("templateId", "");
        param.putNameValuePair("exact", "0");
        param.putNameValuePair("page", "1");
        param.putTargetUrlList(url);
        param.setTaskType(BaseConstant.TaskListType.SACTION_LIST.getValue());
        Request request = CommonMethodUtils.buildDefaultRequest(param,url);
        int threadNum = 5;
        request.putExtra("threadNum", threadNum);
        // 启动爬虫
        logger.info("=============>启动爬虫!");
        Spider.create(administrationSactionProcessor).addPipeline(administrationSactionPipeline)
                .setDownloader(new HttpRequestDownloader().setParam(param)).thread(threadNum)
                .addRequest(request).run();
    }

    @Test
    public void run() {
        methodTest();
    }

}
