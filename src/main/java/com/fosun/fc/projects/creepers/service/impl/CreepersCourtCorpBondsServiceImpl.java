package com.fosun.fc.projects.creepers.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersCourtCorpBondsDao;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersCourtCorpBondsDTO;
import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtCorpBonds;
import com.fosun.fc.projects.creepers.pageprocessor.CreditChina.CorporateBondsBlacklistProcessor;
import com.fosun.fc.projects.creepers.pipeline.CreditChina.CorporateBondsBlacklistPipeline;
import com.fosun.fc.projects.creepers.service.ICreepersCourtCorpBondsService;
import com.fosun.fc.projects.creepers.service.ICreepersJobService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.utils.HttpConstant;

@Service
@Transactional
public class CreepersCourtCorpBondsServiceImpl implements ICreepersCourtCorpBondsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CorporateBondsBlacklistProcessor corporateBondsBlacklistProcessor;
    @Autowired
    private CorporateBondsBlacklistPipeline corporateBondsBlacklistPipeline;
    @Autowired
    private CreepersCourtCorpBondsDao creepersCourtCorpBondsDao;
    @Autowired
    private ICreepersJobService creepersJobServiceImpl;

    private static String URL_SYMBOL = "?_=";

    @SuppressWarnings("unchecked")
    @Override
    public Page<CreepersCourtCorpBondsDTO> findList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType) {

        PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<TCreepersCourtCorpBonds> spec = (Specification<TCreepersCourtCorpBonds>) buildSpecification(
                searchParams);
        Page<TCreepersCourtCorpBonds> page = creepersCourtCorpBondsDao.findAll(spec, pageable);
        List<TCreepersCourtCorpBonds> list = page.getContent();
        List<CreepersCourtCorpBondsDTO> dtoList = new ArrayList<CreepersCourtCorpBondsDTO>();
        dtoList = BeanMapper.mapList(list, CreepersCourtCorpBondsDTO.class);
        Page<CreepersCourtCorpBondsDTO> result = new PageImpl<CreepersCourtCorpBondsDTO>(
                new ArrayList<CreepersCourtCorpBondsDTO>(dtoList), pageable, page.getTotalElements());
        return result;
    }

    @Override
    public void processByJob(String jobName) {
        logger.info("=============>CreepersCourtCorpBondsServiceImpl.processByName start!");
        // 查询任务
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
        Spider.create(corporateBondsBlacklistProcessor).addPipeline(corporateBondsBlacklistPipeline)
                .setDownloader(new HttpRequestDownloader().setParam(param)).thread(threadNum)
                .addRequest(request).run();
        logger.info("=============>CreepersCourtCorpBondsServiceImpl.processByName start!");

    }
    
    @Transactional(readOnly = true)
    @Override
    public List<TCreepersCourtCorpBonds> findListByName(String name) {
        return creepersCourtCorpBondsDao.findByName(name);
    }

    @Override
    public void queryByName(String name) {
        creepersCourtCorpBondsDao.deleteByName(name);
    }

    @Override
    public void saveEntity(TCreepersCourtCorpBonds entity) {
        creepersCourtCorpBondsDao.saveAndFlush(entity);
    }

    @Override
    public void saveEntity(List<TCreepersCourtCorpBonds> entityList) {
        for (TCreepersCourtCorpBonds entity : entityList) {
            creepersCourtCorpBondsDao.saveAndFlush(entity);
        }
    }

    @Override
    public List<TCreepersCourtCorpBonds> findListByNameAndCode(String name,String code) {
        return creepersCourtCorpBondsDao.findListByNameAndCode(name,code);
    }
    
    @Override
    public void saveOrUpdate(TCreepersCourtCorpBonds entity) {
        TCreepersCourtCorpBonds oldEntity = creepersCourtCorpBondsDao.findTopByName(entity.getName());
        if(oldEntity!=null){
            entity.setId(oldEntity.getId());
            entity.setUpdatedDt(new Date());
            entity.setVersion(oldEntity.getVersion());
        }
        creepersCourtCorpBondsDao.saveAndFlush(entity);
    }
}
