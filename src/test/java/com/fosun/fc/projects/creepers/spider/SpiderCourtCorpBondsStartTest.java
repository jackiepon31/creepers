package com.fosun.fc.projects.creepers.spider;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.pageprocessor.CreditChina.CorporateBondsBlacklistProcessor;
import com.fosun.fc.projects.creepers.pipeline.CreditChina.CorporateBondsBlacklistPipeline;
import com.fosun.fc.projects.creepers.service.ICreepersCourtCorpBondsService;
import com.fosun.fc.projects.creepers.service.ICreepersJobService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.utils.HttpConstant;

public class SpiderCourtCorpBondsStartTest extends SpiderBaseTest {

    @Autowired(required=true)
    private ICreepersCourtCorpBondsService creepersCourtCorpBondsServiceImpl;
    @Autowired(required=true)
    private CorporateBondsBlacklistProcessor corporateBondsBlacklistProcessor;
    @Autowired(required=true)
    private CorporateBondsBlacklistPipeline corporateBondsBlacklistPipeline;
    @Autowired(required = true)
    private ICreepersJobService creepersJobServiceImpl;
    public void methodTest() {
        creepersCourtCorpBondsServiceImpl.processByJob("jobName");
    }

    private static String URL_SYMBOL = "?_=";
    public void methodTest2() {
        // 初始化Param DTO
//        CreepersParamDTO param = new CreepersParamDTO();
//        param.setTaskType(BaseConstant.TaskListType.COURT_CORP_BONDS_LIST.getValue());
//        param.putOrderUrl(BaseConstant.OrderUrlKey.ALL_READY_URL, "http://www.creditchina.gov.cn/uploads/creditinfo/data_info?_=" + new Date().getTime());
//        param.
//        Request request = CommonMethodUtils.buildAllReadyRequest(param,HttpConstant.Method.GET);
        String jobName = "testCourtCorpBonds";
        
        CreepersJobDTO job = creepersJobServiceImpl.findJob(jobName);
        CreepersParamDTO param = new CreepersParamDTO();
        Request request;
        String indexUrl;
        int threadNum;
        if(StringUtils.isBlank(job.getMemo())){
            indexUrl = job.getIndexUrl();
            indexUrl = indexUrl.substring(0, indexUrl.indexOf(URL_SYMBOL)) + URL_SYMBOL + new Date().getTime();
            // 初始化Param DTO
            param.putOrderUrl(BaseConstant.OrderUrlKey.ALL_READY_URL, indexUrl);
            param.setTaskType(BaseConstant.TaskListType.COURT_CORP_BONDS_LIST.getValue());
            // 初始化Request
            request = CommonMethodUtils.buildAllReadyRequest(param, HttpConstant.Method.GET);
            threadNum = job.getThreadNum();
            request.putExtra("threadNum", threadNum);
        }else {
            request=JSON.parseObject(job.getMemo(), Request.class);
            indexUrl = request.getUrl();
            indexUrl = indexUrl.substring(0, indexUrl.indexOf(URL_SYMBOL)) + URL_SYMBOL + new Date().getTime();
            request.setUrl(indexUrl);
            param.putTargetUrlList(request.getUrl());
            param.setTaskType(BaseConstant.TaskListType.COURT_CORP_BONDS_LIST.getValue());
            threadNum = (int) request.getExtra("threadNum");
            creepersJobServiceImpl.updateResumeRequestByJobName(jobName, "");
        }
        // 启动爬虫
        logger.info("=============>启动爬虫!");
        Spider.create(corporateBondsBlacklistProcessor).addPipeline(corporateBondsBlacklistPipeline).setDownloader(new HttpRequestDownloader().setParam(param)).thread(1)
                .addRequest(request).run();
    }
    
    @Test
    public void run() {
        methodTest2();
    }

}
