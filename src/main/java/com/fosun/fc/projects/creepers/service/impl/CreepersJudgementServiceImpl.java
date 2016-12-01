/**
 * <p>
 * Copyright(c) @2016 Fortune Credit Management Co., Ltd.
 * </p>
 */
package com.fosun.fc.projects.creepers.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersJudgementDao;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersJudgementDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersJudgement;
import com.fosun.fc.projects.creepers.pageprocessor.JudgementPostRequestProcessor;
import com.fosun.fc.projects.creepers.pipeline.JudgementPipline;
import com.fosun.fc.projects.creepers.service.ICreepersJudgementService;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.utils.HttpConstant;

/**
 * <p>
 * description:
 * </p>
 * 
 * @author Pengyk
 * @since 2016年6月17日
 * @see
 */
@Component
@Transactional
public class CreepersJudgementServiceImpl implements ICreepersJudgementService {

    @Autowired
    private CreepersJudgementDao creepersJudgementDao;

    @Autowired
    private JudgementPostRequestProcessor judgementPostRequestProcessor;

    @Autowired
    private JudgementPipline judgementPipline;

    @Override
    public List<TCreepersJudgement> queryAll() {
        return null;
    }

    @Override
    public List<TCreepersJudgement> findByMerName(String merName) {
        return creepersJudgementDao.queryByMerName(merName);
    }

    @Override
    public Map<String, Object> findByMerNoForMap(String merNo) {
        return null;
    }

    @Override
    public void processByMerName(String merName) {
        // 初始化param DTO
        CreepersParamDTO param = new CreepersParamDTO();
        param.putSearchKeyWord(merName);
        param.setTaskType(BaseConstant.TaskListType.JUDGEMENT_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        // 启动爬虫
        String url = "http://wenshu.court.gov.cn/List/ListContent?num=1";
        Request request = new Request(url);
        request.setMethod(HttpConstant.Method.POST);
        NameValuePair[] valuePairs = new NameValuePair[5];
        valuePairs[0] = new BasicNameValuePair("Param", "全文检索:" + merName);
        valuePairs[1] = new BasicNameValuePair("Index", "1");
        valuePairs[2] = new BasicNameValuePair("Page", String.valueOf(20));
        valuePairs[3] = new BasicNameValuePair("Order", "法院层级");
        valuePairs[4] = new BasicNameValuePair("Direction", "asc");
        request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, valuePairs);
        Spider.create(judgementPostRequestProcessor).addPipeline(judgementPipline)
                .setDownloader(new HttpRequestDownloader().setParam(param)).thread(2).addRequest(request).run();

    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<CreepersJudgementDTO> queryJudgementList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType) {

        PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<TCreepersJudgement> spec = (Specification<TCreepersJudgement>) buildSpecification(searchParams);
        Page<TCreepersJudgement> judgementPage = creepersJudgementDao.findAll(spec, pageable);
        List<TCreepersJudgement> judgementList = judgementPage.getContent();
        List<CreepersJudgementDTO> judgementDTOList = new ArrayList<CreepersJudgementDTO>();
        judgementDTOList = BeanMapper.mapList(judgementList, CreepersJudgementDTO.class);
        Page<CreepersJudgementDTO> judgementDTOPage = new PageImpl<CreepersJudgementDTO>(
                new ArrayList<CreepersJudgementDTO>(judgementDTOList), pageable, judgementPage.getTotalElements());
        return judgementDTOPage;
    }

    @Override
    public void deleteByMerName(String merName) {

        creepersJudgementDao.deleteByMerName(merName);
    }

    @Override
    public void saveEntity(TCreepersJudgement entity) {

        creepersJudgementDao.saveAndFlush(entity);
    }

    @Override
    public void saveEntity(List<TCreepersJudgement> entityList) {

        for (TCreepersJudgement entity : entityList) {
            creepersJudgementDao.saveAndFlush(entity);
        }
    }

}
