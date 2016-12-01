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
import com.fosun.fc.projects.creepers.dao.CreepersCourtDisInfoDao;
import com.fosun.fc.projects.creepers.dto.CreepersCourtDisInfoDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtDisInfo;
import com.fosun.fc.projects.creepers.service.ICreepersCourtDisInfoService;

@Service
@Transactional
public class CreepersCourtDisInfoServiceImpl implements ICreepersCourtDisInfoService {

    @Autowired
    private CreepersCourtDisInfoDao creepersCourtDisInfoDao;

    @SuppressWarnings("unchecked")
    @Override
    public Page<CreepersCourtDisInfoDTO> findList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType) {

        PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<TCreepersCourtDisInfo> spec = (Specification<TCreepersCourtDisInfo>) buildSpecification(searchParams);
        Page<TCreepersCourtDisInfo> page = creepersCourtDisInfoDao.findAll(spec, pageable);
        List<TCreepersCourtDisInfo> list = page.getContent();
        List<CreepersCourtDisInfoDTO> dtoList = new ArrayList<CreepersCourtDisInfoDTO>();
        dtoList = BeanMapper.mapList(list, CreepersCourtDisInfoDTO.class);
        Page<CreepersCourtDisInfoDTO> result = new PageImpl<CreepersCourtDisInfoDTO>(new ArrayList<CreepersCourtDisInfoDTO>(dtoList),
                pageable, page.getTotalElements());
        return result;
    }

    @Override
    public List<TCreepersCourtDisInfo> findListByName(String name) {
        return creepersCourtDisInfoDao.findByName(name);
    }

    @Override
    public void deleteByName(String name) {
        creepersCourtDisInfoDao.deleteByName(name);

    }

    @Override
    public void saveEntity(TCreepersCourtDisInfo entity) {
        creepersCourtDisInfoDao.saveAndFlush(entity);
    }

    @Override
    public void saveEntity(List<TCreepersCourtDisInfo> entityList) {
        for(TCreepersCourtDisInfo entity:entityList){
            creepersCourtDisInfoDao.saveAndFlush(entity);
        }
    }

}
