package com.fosun.fc.projects.creepers.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersShixinDao;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.dto.CreepersShixinDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersShixin;
import com.fosun.fc.projects.creepers.pageprocessor.CreditChina.CreditChinaGovProcessor;
import com.fosun.fc.projects.creepers.pipeline.CreditChina.CreditChinaGovPipeline;
import com.fosun.fc.projects.creepers.service.ICreepersShixinService;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.utils.HttpConstant;

@Service
@Transactional
public class CreepersShixinServiceImpl implements ICreepersShixinService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CreepersShixinDao creepersShixinDao;

    @Autowired
    private CreditChinaGovProcessor creditChinaGovProcessor;

    @Autowired
    private CreditChinaGovPipeline creditChinaGovPipeline;

    @SuppressWarnings("unchecked")
    @Override
    public Page<CreepersShixinDTO> findList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType) {

        PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<TCreepersShixin> spec = (Specification<TCreepersShixin>) buildSpecification(searchParams);
        Page<TCreepersShixin> page = creepersShixinDao.findAll(spec, pageable);
        List<TCreepersShixin> list = page.getContent();
        List<CreepersShixinDTO> dtoList = new ArrayList<CreepersShixinDTO>();
        dtoList = BeanMapper.mapList(list, CreepersShixinDTO.class);
        Page<CreepersShixinDTO> result = new PageImpl<CreepersShixinDTO>(new ArrayList<CreepersShixinDTO>(dtoList),
                pageable, page.getTotalElements());
        return result;
    }

    @Override
    public void processByMerName(String merName) {
        logger.info("=============>CreepersShixinServiceImpl.processByMerName start!");
        // 初始化Param DTO
        CreepersParamDTO param = new CreepersParamDTO();
        param.putSearchKeyWord(merName);
        param.setTaskType(BaseConstant.TaskListType.SHI_XIN_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        String url = "http://www.creditchina.gov.cn/credit_info_search?t=" + new Date().getTime() + "&currentPageNo=1";
        Request request = new Request(url);
        request.setMethod(HttpConstant.Method.POST);
        NameValuePair[] nameValuePair = new NameValuePair[10];
        nameValuePair[0] = new BasicNameValuePair("keyword", merName);
        nameValuePair[1] = new BasicNameValuePair("searchtype", "0");
        nameValuePair[2] = new BasicNameValuePair("objectType", "2");
        nameValuePair[3] = new BasicNameValuePair("areas", "");
        nameValuePair[4] = new BasicNameValuePair("creditType", "8");
        nameValuePair[5] = new BasicNameValuePair("dataType", "1");
        nameValuePair[6] = new BasicNameValuePair("areaCode", "");
        nameValuePair[7] = new BasicNameValuePair("templateId", "1");
        nameValuePair[8] = new BasicNameValuePair("exact", "0");
        nameValuePair[9] = new BasicNameValuePair("page", "1");
        request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, nameValuePair);
        // 启动爬虫
        logger.info("=============>启动爬虫!");
        Spider.create(creditChinaGovProcessor).addPipeline(creditChinaGovPipeline)
                .setDownloader(new HttpRequestDownloader().setParam(param)).thread(1).addRequest(request).run();
        logger.info("=============>CreepersShixinServiceImpl.processByMerName start!");

    }

    @Override
    public List<TCreepersShixin> findListByMerName(String merName) {
        return creepersShixinDao.findByName(merName);
    }

    @Override
    public void deleteByName(String merName) {
        creepersShixinDao.deleteByName(merName);

    }

    @Override
    public void saveEntity(TCreepersShixin entity) {
        creepersShixinDao.saveAndFlush(entity);
    }

    @Override
    public void saveEntity(List<TCreepersShixin> entityList) {
        for(TCreepersShixin entity:entityList){
            creepersShixinDao.saveAndFlush(entity);
        }
    }

}
