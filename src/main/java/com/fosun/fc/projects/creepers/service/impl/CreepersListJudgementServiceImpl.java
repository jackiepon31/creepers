package com.fosun.fc.projects.creepers.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.dao.CreepersListJudgementDao;
import com.fosun.fc.projects.creepers.dto.CreepersListJudgementDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersListJudgement;
import com.fosun.fc.projects.creepers.service.ICreepersListJudgementService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

@Service
@Transactional
public class CreepersListJudgementServiceImpl implements ICreepersListJudgementService {

    @Autowired
    private CreepersListJudgementDao listJudgementDao;

    @Override
    public Page<CreepersListJudgementDTO> findListJudgementList(Map<String, Object> searchParams, int pageNumber,
            int pageSize, String sortType) throws Exception {

        PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
        @SuppressWarnings("unchecked")
        Specification<TCreepersListJudgement> spec = (Specification<TCreepersListJudgement>) CommonMethodUtils
                .buildSpecification(searchParams, "Dt");
        Page<TCreepersListJudgement> listJudgementPage = listJudgementDao.findAll(spec, pageable);
        List<TCreepersListJudgement> listJudgementList = listJudgementPage.getContent();
        List<CreepersListJudgementDTO> creepersListJudgementDTOList = new ArrayList<CreepersListJudgementDTO>();
        creepersListJudgementDTOList = BeanMapper.mapList(listJudgementList, CreepersListJudgementDTO.class);
        Page<CreepersListJudgementDTO> listJudgementDTOPage = new PageImpl<CreepersListJudgementDTO>(
                new ArrayList<CreepersListJudgementDTO>(creepersListJudgementDTOList), pageable,
                listJudgementPage.getTotalElements());

        return listJudgementDTOPage;
    }

    @Override
    public List<CreepersListJudgementDTO> findListByMerName(String merName) {

        List<TCreepersListJudgement> list = listJudgementDao.queryListByMerName(merName);
        List<CreepersListJudgementDTO> dtoList = new ArrayList<CreepersListJudgementDTO>();
        dtoList = BeanMapper.mapList(list, CreepersListJudgementDTO.class);
        return dtoList;
    }

}
