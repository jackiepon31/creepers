/**
 * <p>
 * Copyright(c) @2016 Fortune Credit Management Co., Ltd.
 * </p>
 */
package com.fosun.fc.projects.creepers.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersPatentDao;
import com.fosun.fc.projects.creepers.downloader.SeleniumDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.dto.CreepersPatentDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersPatent;
import com.fosun.fc.projects.creepers.pageprocessor.PatentProcessor;
import com.fosun.fc.projects.creepers.pipeline.PatentPipline;
import com.fosun.fc.projects.creepers.service.ICreepersPatentService;

import us.codecraft.webmagic.Spider;

/**
 * <p>
 * description:
 * </p>
 * 
 * @author MaXin
 * @since 2016年8月4日15:54:08
 * @see
 */
@Component
@Transactional
public class CreepersPatentServiceImpl implements ICreepersPatentService {

    @Autowired
    private CreepersPatentDao creepersPatentDao;

    @Autowired
    private PatentProcessor patentProcessor;

    @Autowired
    private PatentPipline patentPipline;

    @Override
    public List<TCreepersPatent> queryAllOlDetail() {
        return null;
    }

    @Override
    public List<TCreepersPatent> findByMerName(String merName) {
        return creepersPatentDao.findByMerName(merName);
    }

    @Override
    public Map<String, Object> findByRptNoForMap(String rptNo) {
        return null;
    }

    @SuppressWarnings("resource")
    @Override
    public void processByMerName(String merName) {
        // 初始化param DTO
        CreepersParamDTO param = new CreepersParamDTO();
        param.putSearchKeyWord(merName);
        param.setTaskType(BaseConstant.TaskListType.PATENT_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        // 启动爬虫
        Spider.create(patentProcessor).addPipeline(patentPipline)
                .setDownloader(new SeleniumDownloader().setParam(param)).thread(10)
                .addUrl("http://cpquery.sipo.gov.cn/txnQueryOrdinaryPatents.do?select-key%3Ashenqingh=&select-key%3Azhuanlimc=&select-key%3Ashenqingrxm="
                        + merName
                        + "&select-key%3Azhuanlilx=&select-key%3Ashenqingr_from=&select-key%3Ashenqingr_to=&attribute-node:record_start-row=1&attribute-node:record_page-row=1000&")
                .run();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<CreepersPatentDTO> queryPatentList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType) {
        PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<TCreepersPatent> spec = (Specification<TCreepersPatent>) buildSpecification(searchParams);
        Page<TCreepersPatent> patentPage = creepersPatentDao.findAll(spec, pageable);
        List<TCreepersPatent> patentList = patentPage.getContent();
        List<CreepersPatentDTO> patentDTOList = new ArrayList<CreepersPatentDTO>();
        patentDTOList = BeanMapper.mapList(patentList, CreepersPatentDTO.class);
        Page<CreepersPatentDTO> resultPage = new PageImpl<CreepersPatentDTO>(
                new ArrayList<CreepersPatentDTO>(patentDTOList), pageable, patentPage.getTotalElements());
        return resultPage;
    }

    @Override
    public void deleteByMerName(String merName) {

        creepersPatentDao.deleteByMerName(merName);
    }

    @Override
    public void saveEntity(TCreepersPatent entity) {

        creepersPatentDao.saveAndFlush(entity);
    }

    @Override
    public void saveEntity(List<TCreepersPatent> entityList) {

        for (TCreepersPatent entity : entityList) {
            creepersPatentDao.saveAndFlush(entity);
        }
    }

}
