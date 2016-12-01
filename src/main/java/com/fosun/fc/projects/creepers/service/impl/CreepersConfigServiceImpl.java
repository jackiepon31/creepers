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
import com.fosun.fc.projects.creepers.dao.CreepersConfigDao;
import com.fosun.fc.projects.creepers.dto.CreepersConfigDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersConfig;
import com.fosun.fc.projects.creepers.service.ICreepersConfigService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

@Service("creepersConfigServiceImpl")
public class CreepersConfigServiceImpl implements ICreepersConfigService {

    @Autowired
    private CreepersConfigDao creepersConfigDao;
    
    @Transactional
    @Override
    public void updateEntity(TCreepersConfig entity) {
        String requestType = entity.getRequestType();
        
        long count = creepersConfigDao.countByRequestType(requestType);
        if (count == 1L) {
            TCreepersConfig oldEntity = new TCreepersConfig();
            oldEntity = creepersConfigDao.findTop1ByRequestType(requestType);
            entity.setId(oldEntity.getId());
            entity.setVersion(oldEntity.getVersion());
        }
        CommonMethodUtils.setByDT(entity);
        creepersConfigDao.saveAndFlush(entity);
    }
    
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public Page<CreepersConfigDTO> queryForListByRequestType(Map<String, Object> searchParams, int pageNumber,
            int pageSize, String sortType) throws Exception {
        PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<TCreepersConfig> spec = (Specification<TCreepersConfig>) CommonMethodUtils
                .buildSpecification(searchParams, "Dt");
        Page<TCreepersConfig> creepersConfigPage = creepersConfigDao.findAll(spec, pageable);
        List<TCreepersConfig> creepersConfigList = creepersConfigPage.getContent();
        List<CreepersConfigDTO> creepersConfigDTOList = new ArrayList<CreepersConfigDTO>();
        creepersConfigDTOList = BeanMapper.mapList(creepersConfigList, CreepersConfigDTO.class);
        Page<CreepersConfigDTO> resultPage = new PageImpl<CreepersConfigDTO>(
                new ArrayList<CreepersConfigDTO>(creepersConfigDTOList), pageable,
                creepersConfigPage.getTotalElements());

        return resultPage;
    }

    @Transactional(readOnly = true)
    @Override
    public TCreepersConfig queryEntity(String requestType) {
        return creepersConfigDao.findTop1ByRequestType(requestType);
    }
    @Transactional(readOnly = true)
    @Override
    public long countEntity(String requestType) {
        return creepersConfigDao.countByRequestType(requestType);
    }

}
