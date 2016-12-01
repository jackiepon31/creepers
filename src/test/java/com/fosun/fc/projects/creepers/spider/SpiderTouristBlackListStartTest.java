package com.fosun.fc.projects.creepers.spider;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.pageprocessor.TouristBlackListProcessor;
import com.fosun.fc.projects.creepers.pipeline.TouristBlackListPipline;

import us.codecraft.webmagic.Spider;

public class SpiderTouristBlackListStartTest extends SpiderBaseTest {

    @Autowired(required = true)
    private TouristBlackListProcessor touristBlackListProcessor;

    @Autowired(required = true)
    private TouristBlackListPipline touristBlackListPipline;

    @Autowired(required = true)
    private HttpRequestDownloader httpRequestDownloader;

    public void methodTest() {
        CreepersParamDTO param = new CreepersParamDTO();
        param.setTaskType(BaseConstant.TaskListType.TOUR_BLACK_LIST.getValue());
        Spider.create(touristBlackListProcessor).addPipeline(touristBlackListPipline)
                .addUrl("http://qualitytourism.cnta.gov.cn/tools/submit_ajax.ashx?action=searchbadinfo&type=旅行社&bdate=&edate=&key=")
                .addUrl("http://qualitytourism.cnta.gov.cn/tools/submit_ajax.ashx?action=searchbadinfo&type=导%20游&bdate=&edate=&key=")
                .setDownloader(httpRequestDownloader.setParam(param)).thread(15).run();
    }

    @Test
    public void run() {
        methodTest();
    }

}
