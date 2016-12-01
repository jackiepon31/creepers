package com.fosun.fc.projects.creepers.spider;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.pageprocessor.JudgementPostRequestProcessor;
import com.fosun.fc.projects.creepers.pipeline.JudgementPipline;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

public class SpiderWenShuCourtGovStartTest extends SpiderBaseTest {

    @Autowired
    private JudgementPostRequestProcessor judgementPostRequestProcessor;

    @Autowired
    private JudgementPipline judgementPipline;

    @Autowired
    private HttpRequestDownloader httpRequestDownloader;

    public void methodTest() {
        String merName = "快播";
        CreepersParamDTO param = new CreepersParamDTO();
        param.putSearchKeyWord(merName);
        param.setTaskType(BaseConstant.TaskListType.JUDGEMENT_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        param.putOrderUrl(BaseConstant.OrderUrlKey.ALL_READY_URL, "http://wenshu.court.gov.cn/List/ListContent?num=1");
        param.putNameValuePair("Param", "全文检索:" + merName);
        param.putNameValuePair("Index", "1");
        param.putNameValuePair("Page", String.valueOf(20));
        param.putNameValuePair("Order", "法院层级");
        param.putNameValuePair("Direction", "asc");
        Request request = CommonMethodUtils.buildAllReadyRequest(param);
        
        Spider.create(judgementPostRequestProcessor).addPipeline(judgementPipline)
                .setDownloader(httpRequestDownloader.setParam(param)).thread(1).addRequest(request).runAsync();
    }

    @Test
    public void run() {
        methodTest();
    }

}
