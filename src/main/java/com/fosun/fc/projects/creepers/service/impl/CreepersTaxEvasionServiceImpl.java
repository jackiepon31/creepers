package com.fosun.fc.projects.creepers.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersTaxEvasionDao;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.dto.CreepersTaxEvasionDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersTaxEvasion;
import com.fosun.fc.projects.creepers.pageprocessor.CreditChina.TaxEvasionProcessor;
import com.fosun.fc.projects.creepers.pipeline.CreditChina.TaxEvasionPipeline;
import com.fosun.fc.projects.creepers.service.ICreepersJobService;
import com.fosun.fc.projects.creepers.service.ICreepersTaxEvasionService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

@Service
@Transactional
public class CreepersTaxEvasionServiceImpl implements ICreepersTaxEvasionService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TaxEvasionProcessor taxEvasionProcessor;
    @Autowired
    private TaxEvasionPipeline taxEvasionPipeline;
    @Autowired
    private CreepersTaxEvasionDao creepersTaxEvasionDao;
    @Autowired
    private ICreepersJobService creepersJobServiceImpl;

    private static String URL_SYMBOL = "t=";

    @SuppressWarnings("unchecked")
    @Override
    public Page<CreepersTaxEvasionDTO> findList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType) {

        PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<TCreepersTaxEvasion> spec = (Specification<TCreepersTaxEvasion>) buildSpecification(
                searchParams);
        Page<TCreepersTaxEvasion> page = creepersTaxEvasionDao.findAll(spec, pageable);
        List<TCreepersTaxEvasion> list = page.getContent();
        List<CreepersTaxEvasionDTO> dtoList = new ArrayList<CreepersTaxEvasionDTO>();
        dtoList = BeanMapper.mapList(list, CreepersTaxEvasionDTO.class);
        Page<CreepersTaxEvasionDTO> result = new PageImpl<CreepersTaxEvasionDTO>(
                new ArrayList<CreepersTaxEvasionDTO>(dtoList), pageable, page.getTotalElements());
        return result;
    }

    @Override
    public void processByJob(String jobName) {
        logger.info("=============>CreepersTaxEvasionServiceImpl.processByName start!");
        // 查询任务
        CreepersJobDTO job = creepersJobServiceImpl.findJob(jobName);
        // 初始化Param DTO
        CreepersParamDTO param = new CreepersParamDTO();
        Request request;
        String indexUrl;
        int threadNum;
        if (StringUtils.isBlank(job.getMemo())) {
            indexUrl = job.getIndexUrl();
            indexUrl = indexUrl.substring(0, indexUrl.indexOf(URL_SYMBOL)) + URL_SYMBOL + new Date().getTime();
            param.putNameValuePair("keyword", "");
            param.putNameValuePair("searchtype", "0");
            param.putNameValuePair("objectType", "2");
            param.putNameValuePair("areas", "");
            param.putNameValuePair("creditType", "4");
            param.putNameValuePair("dataType", "1");
            param.putNameValuePair("areaCode", "");
            param.putNameValuePair("templateId", "6");
            param.putNameValuePair("exact", "0");
            param.putNameValuePair("page", "1");
            param.putTargetUrlList(indexUrl);
            param.setTaskType(BaseConstant.TaskListType.TAX_EVASION_LIST.getValue());
            // 初始化Request
            request = CommonMethodUtils.buildDefaultRequest(param,indexUrl);
            threadNum = job.getThreadNum();
            request.putExtra("threadNum", threadNum);
            request.putExtra("pageNo", 1);
        } else {
            request = JSON.parseObject(job.getMemo(), Request.class);
            indexUrl = request.getUrl();
            indexUrl = indexUrl.substring(0, indexUrl.indexOf(URL_SYMBOL)) + URL_SYMBOL + new Date().getTime();
            request.setUrl(indexUrl);
            JSONObject jsonObject = JSON.parseObject(job.getMemo());
            JSONObject extras = jsonObject.getJSONObject("extras");
            String nameValuePair = extras.getString("nameValuePair");
            request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, jsonToNameValuePair(nameValuePair));
            param.putTargetUrlList(request.getUrl());
            param.setTaskType(BaseConstant.TaskListType.TAX_EVASION_LIST.getValue());
            threadNum = (int) request.getExtra("threadNum");
            creepersJobServiceImpl.updateResumeRequestByJobName(jobName, "");;
        }
        // 启动爬虫
        logger.info("=============>启动爬虫!");
        Spider.create(taxEvasionProcessor).addPipeline(taxEvasionPipeline)
                .setDownloader(new HttpRequestDownloader().setParam(param)).thread(threadNum)
                .addRequest(request).run();
        logger.info("=============>CreepersTaxEvasionServiceImpl.processByName start!");

    }

    public static NameValuePair[] jsonToNameValuePair(String jsonString){
        JSONArray jsonArray = JSON.parseArray(jsonString);
        NameValuePair[] nameValuePairs = new NameValuePair[jsonArray.size()];
        for(int i=0;i<jsonArray.size();i++){
            String name=jsonArray.getJSONObject(i).getString("name");
            String value=jsonArray.getJSONObject(i).getString("value");
            nameValuePairs[i] = new BasicNameValuePair(name,value);
        }
        return nameValuePairs;
    }

    @Override
    public List<TCreepersTaxEvasion> findListByName(String name) {
        
        return creepersTaxEvasionDao.findByName(name);
    }

    @Override
    public void saveEntity(TCreepersTaxEvasion entity) {
        creepersTaxEvasionDao.saveAndFlush(entity);
    }

    @Override
    public void saveEntity(List<TCreepersTaxEvasion> entityList) {
        for(TCreepersTaxEvasion entity:entityList)
            creepersTaxEvasionDao.saveAndFlush(entity);
    }
    
    @Override
    public void saveOrUpdate(TCreepersTaxEvasion entity){
        TCreepersTaxEvasion oldEntity = creepersTaxEvasionDao.findTopByName(entity.getName());
        if(oldEntity!=null){
            entity.setId(oldEntity.getId());
            entity.setUpdatedDt(new Date());
            entity.setVersion(oldEntity.getVersion());
        }
        creepersTaxEvasionDao.saveAndFlush(entity);
    }
}
