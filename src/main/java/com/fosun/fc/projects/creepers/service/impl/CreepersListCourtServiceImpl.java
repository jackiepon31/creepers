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
import com.fosun.fc.projects.creepers.dao.CreepersListCourtDao;
import com.fosun.fc.projects.creepers.dto.CreepersListCourtDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersListCourt;
import com.fosun.fc.projects.creepers.service.ICreepersListCourtService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

@Service
@Transactional
public class CreepersListCourtServiceImpl implements ICreepersListCourtService {

    @Autowired
    private CreepersListCourtDao listCourtDao;
    
    @SuppressWarnings("unchecked")
    @Override
    public Page<CreepersListCourtDTO> findListCourtList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType) throws Exception {

        PageRequest pageable=buildPageRequest(pageNumber, pageSize, sortType);
        Specification<TCreepersListCourt> spec = (Specification<TCreepersListCourt>) CommonMethodUtils.buildSpecification(searchParams, "Dt");
        Page<TCreepersListCourt> listCourtPage = listCourtDao.findAll(spec,pageable);
        List<TCreepersListCourt> listCourtList = listCourtPage.getContent();
        List<CreepersListCourtDTO> creepersListCourtDTOList = new ArrayList<CreepersListCourtDTO>();
        creepersListCourtDTOList = BeanMapper.mapList(listCourtList, CreepersListCourtDTO.class);
        Page<CreepersListCourtDTO> creepersListCourtDTOPage = new PageImpl<CreepersListCourtDTO>(new ArrayList<CreepersListCourtDTO>(creepersListCourtDTOList),pageable,listCourtPage.getTotalElements());

        return creepersListCourtDTOPage;
    }

    @Override
    public List<CreepersListCourtDTO> findListByMerName(String merName) {
        
        List<TCreepersListCourt> listCourtList = listCourtDao.queryListByMerName(merName);
        List<CreepersListCourtDTO> creepersListCourtDTOList = new ArrayList<CreepersListCourtDTO>();
        creepersListCourtDTOList = BeanMapper.mapList(listCourtList, CreepersListCourtDTO.class);
        return creepersListCourtDTOList;
    }

}
