/**
 * <p>
 * Copyright(c) @2016 Fortune Credit Management Co., Ltd.
 * </p>
 */
package com.fosun.fc.projects.creepers.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersTourBlackListDao;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersTourBlackList;
import com.fosun.fc.projects.creepers.pageprocessor.TouristBlackListProcessor;
import com.fosun.fc.projects.creepers.pipeline.TouristBlackListPipline;
import com.fosun.fc.projects.creepers.service.ICreepersJobService;
import com.fosun.fc.projects.creepers.service.ICreepersTouristBlackListService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

/**
 * <p>
 * description: 导游 旅行社黑名单信息Service
 * </p>
 * 
 * @author MaXin
 * @since 2016-11-11 14:27:54
 * @see
 */
@Service("creepersTouristBlackListServiceImpl")
public class CreepersTouristBlackListServiceImpl extends CreepersBaseServiceImpl
        implements ICreepersTouristBlackListService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CreepersTourBlackListDao creepersTourBlackListDao;

    @Autowired
    private ICreepersJobService creepersJobServiceImpl;

    @Autowired
    private TouristBlackListProcessor touristBlackListProcessor;

    @Autowired
    private TouristBlackListPipline touristBlackListPipline;

    @Override
    public void saveEntity(TCreepersTourBlackList entity) {

        TCreepersTourBlackList oldEntity = creepersTourBlackListDao.findTop1ByGuideNo(entity.getGuideNo());
        if (oldEntity != null) {
            entity.setId(oldEntity.getId());
            entity.setVersion(oldEntity.getVersion());
        }
        creepersTourBlackListDao.saveAndFlush(entity);
    }

    @Override
    public void processByJob(String jobName) {

        logger.info("=============>creepersTouristBlackListServiceImpl.processByJob start!");
        // 查询任务
        CreepersJobDTO job = creepersJobServiceImpl.findJob(jobName);
        Request request = null;
        String threadNum = "1";
        CreepersParamDTO param = new CreepersParamDTO();
        param.setTaskType(BaseConstant.TaskListType.TOUR_BLACK_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        param.putTargetUrlList(
                "http://qualitytourism.cnta.gov.cn/tools/submit_ajax.ashx?action=searchbadinfo&type=导%20游&bdate=&edate=&key=");
        param.putTargetUrlList(
                "http://qualitytourism.cnta.gov.cn/tools/submit_ajax.ashx?action=searchbadinfo&type=旅行社&bdate=&edate=&key=");
        if (StringUtils.isBlank(job.getMemo())) {
            param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, job.getIndexUrl());
            // 初始化Request
            request = CommonMethodUtils.buildGetRequestCarryMap(param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL));
            threadNum = String.valueOf(job.getThreadNum());
        } else {
            // 反序列化Request
            request = JSON.parseObject(job.getMemo(), Request.class);
            param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, request.getUrl());
            threadNum = String.valueOf(request.getExtra("threadNum"));
            creepersJobServiceImpl.updateResumeRequestByJobName(jobName, StringUtils.EMPTY);
        }

        // 启动爬虫
        logger.info("=============>启动爬虫!");
        Spider.create(touristBlackListProcessor).addPipeline(touristBlackListPipline)
                .setDownloader(new HttpRequestDownloader().setParam(param)).thread(Integer.parseInt(threadNum))
                .addRequest(request).run();
        logger.info("=============>creepersTouristBlackListServiceImpl.processByJob end!");

    }

    @Override
    public List<TCreepersTourBlackList> findListByGuideNo(String merName) {
        List<TCreepersTourBlackList> list = new ArrayList<TCreepersTourBlackList>();
        if (StringUtils.isBlank(merName)) {
            return list;
        }
        return creepersTourBlackListDao.findByGuideNo(merName);
    }

    @Override
    public List<TCreepersTourBlackList> findListByAgentNameAndType(String merName) {
        List<TCreepersTourBlackList> list = new ArrayList<TCreepersTourBlackList>();
        if (StringUtils.isBlank(merName)) {
            return list;
        }
        return creepersTourBlackListDao.findByAgentNameAndType(merName, BaseConstant.TouristBlackListType.TRAVEL_AGENCY.getValue());
    }

}
