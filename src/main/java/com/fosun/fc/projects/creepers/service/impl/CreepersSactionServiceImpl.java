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
import com.fosun.fc.projects.creepers.dao.CreepersSactionDao;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.dto.CreepersSactionDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersSaction;
import com.fosun.fc.projects.creepers.pageprocessor.CreditChina.AdministrationSactionProcessor;
import com.fosun.fc.projects.creepers.pipeline.CreditChina.AdministrationSactionPipeline;
import com.fosun.fc.projects.creepers.service.ICreepersJobService;
import com.fosun.fc.projects.creepers.service.ICreepersSactionService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

@Service
@Transactional
public class CreepersSactionServiceImpl implements ICreepersSactionService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdministrationSactionProcessor administrationSactionProcessor;
    @Autowired
    private AdministrationSactionPipeline administrationSactionPipeline;
    @Autowired
    private CreepersSactionDao creepersSactionDao;
    @Autowired
    private ICreepersJobService creepersJobServiceImpl;

    private static String URL_SYMBOL = "t=";

    @SuppressWarnings("unchecked")
    @Override
    public Page<CreepersSactionDTO> findList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType) {

        PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<TCreepersSaction> spec = (Specification<TCreepersSaction>) buildSpecification(
                searchParams);
        Page<TCreepersSaction> page = creepersSactionDao.findAll(spec, pageable);
        List<TCreepersSaction> list = page.getContent();
        List<CreepersSactionDTO> dtoList = new ArrayList<CreepersSactionDTO>();
        dtoList = BeanMapper.mapList(list, CreepersSactionDTO.class);
        Page<CreepersSactionDTO> result = new PageImpl<CreepersSactionDTO>(
                new ArrayList<CreepersSactionDTO>(dtoList), pageable, page.getTotalElements());
        return result;
    }

    @Override
    public void processByJob(String jobName) {
        logger.info("=============>CreepersSactionServiceImpl.processByName start!");
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
            param.putNameValuePair("searchtype", "1");
            param.putNameValuePair("objectType", "2");
            param.putNameValuePair("areas", "");
            param.putNameValuePair("creditType", "");
            param.putNameValuePair("dataType", "0");
            param.putNameValuePair("areaCode", "");
            param.putNameValuePair("templateId", "");
            param.putNameValuePair("exact", "0");
            param.putNameValuePair("page", "1");
            param.putTargetUrlList(indexUrl);
            param.setTaskType(BaseConstant.TaskListType.COURT_DISHONESTY_LIST.getValue());
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
            param.setTaskType(BaseConstant.TaskListType.COURT_DISHONESTY_LIST.getValue());
            threadNum = (int) request.getExtra("threadNum");
            creepersJobServiceImpl.updateResumeRequestByJobName(jobName, "");;
        }
        // 启动爬虫
        logger.info("=============>启动爬虫!");
        Spider.create(administrationSactionProcessor).addPipeline(administrationSactionPipeline)
                .setDownloader(new HttpRequestDownloader().setParam(param)).thread(threadNum)
                .addRequest(request).run();
        logger.info("=============>CreepersSactionServiceImpl.processByName start!");

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
    public List<TCreepersSaction> findListByName(String name) {
        
        return creepersSactionDao.findByName(name);
    }

    @Override
    public void saveEntity(TCreepersSaction entity) {
        creepersSactionDao.saveAndFlush(entity);
    }

    @Override
    public void saveEntity(List<TCreepersSaction> entityList) {
        for(TCreepersSaction entity:entityList)
            creepersSactionDao.saveAndFlush(entity);
    }
    
    @Override
    public void saveOrUpdate(TCreepersSaction entity){
        TCreepersSaction oldEntity = creepersSactionDao.findTopByName(entity.getName());
        if(oldEntity!=null){
            entity.setId(oldEntity.getId());
            entity.setUpdatedDt(new Date());
            entity.setVersion(oldEntity.getVersion());
        }
        creepersSactionDao.saveAndFlush(entity);
    }
}
