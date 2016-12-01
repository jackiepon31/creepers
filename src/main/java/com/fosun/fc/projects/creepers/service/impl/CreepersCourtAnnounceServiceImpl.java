package com.fosun.fc.projects.creepers.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersCourtAnnounceDao;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersCourtAnnounceDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtAnnounce;
import com.fosun.fc.projects.creepers.pageprocessor.CourtAnnouncementProcessor;
import com.fosun.fc.projects.creepers.pipeline.CourtAnnouncementPipline;
import com.fosun.fc.projects.creepers.service.ICreepersCourtAnnounceService;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.utils.HttpConstant;

/**
 * <p>
 * description:
 * </p>
 * 
 * @author LiZhanPing
 * @since 2016年8月8日
 * @see
 */
@Service("creepersCourtAnnounceServiceImpl2")
@Transactional
public class CreepersCourtAnnounceServiceImpl implements ICreepersCourtAnnounceService {

    @Autowired
    private CreepersCourtAnnounceDao creepersCourtAnnounceDao;

    @Autowired
    private CourtAnnouncementProcessor courtAnnouncementProcessor;

    @Autowired
    private CourtAnnouncementPipline courtAnnouncementPipline;

    @Override
    public List<TCreepersCourtAnnounce> findByMerName(String merName) {

        return creepersCourtAnnounceDao.findByMerName(merName);
    }

    @Override
    public void processByMerName(String merName) {
        // 初始化param DTO
        CreepersParamDTO param = new CreepersParamDTO();
        param.putSearchKeyWord(merName);
        param.setTaskType(BaseConstant.TaskListType.COURT_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        // 启动爬虫
        Request request = new Request("http://rmfygg.court.gov.cn/psca/lgnot/bulletin/" + merName + "_0_0.html");
        request.setMethod(HttpConstant.Method.POST);
        Spider.create(courtAnnouncementProcessor).addPipeline(courtAnnouncementPipline)
                .setDownloader(new HttpRequestDownloader().setParam(param)).thread(10).addRequest(request).run();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<CreepersCourtAnnounceDTO> findCourtAnnounceList(Map<String, Object> searchParams, int pageNumber,
            int pageSize, String sortType) {

        PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<TCreepersCourtAnnounce> spec = (Specification<TCreepersCourtAnnounce>) buildSpecification(
                searchParams);
        Page<TCreepersCourtAnnounce> courtPage = creepersCourtAnnounceDao.findAll(spec, pageable);
        List<TCreepersCourtAnnounce> courtList = courtPage.getContent();
        List<CreepersCourtAnnounceDTO> courtAnnounceDTOList = new ArrayList<CreepersCourtAnnounceDTO>();
        courtAnnounceDTOList = BeanMapper.mapList(courtList, CreepersCourtAnnounceDTO.class);
        Page<CreepersCourtAnnounceDTO> courtAnnounceDTOPage = new PageImpl<CreepersCourtAnnounceDTO>(
                new ArrayList<CreepersCourtAnnounceDTO>(courtAnnounceDTOList), pageable, courtPage.getTotalElements());
        return courtAnnounceDTOPage;
    }
    
    public void deleteByMerName(String name){
        creepersCourtAnnounceDao.deleteByMerName(name);
    }
    
    public void saveEntity(TCreepersCourtAnnounce entity){
        creepersCourtAnnounceDao.saveAndFlush(entity);
    }
    
    public void saveEntity(List<TCreepersCourtAnnounce> entityList){
        for (TCreepersCourtAnnounce entity : entityList) {
            creepersCourtAnnounceDao.saveAndFlush(entity);
        }
    }
}
