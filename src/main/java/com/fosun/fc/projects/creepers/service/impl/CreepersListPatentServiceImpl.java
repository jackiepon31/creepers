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
import com.fosun.fc.projects.creepers.dao.CreepersListPatentDao;
import com.fosun.fc.projects.creepers.dto.CreepersListPatentDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersListPatent;
import com.fosun.fc.projects.creepers.service.ICreepersListPatentService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

@Service
@Transactional
public class CreepersListPatentServiceImpl implements ICreepersListPatentService {

    @Autowired
    private CreepersListPatentDao listPatentDao;

    @Override
    public Page<CreepersListPatentDTO> queryListPatentList(Map<String, Object> searchParams, int pageNumber,
            int pageSize, String sortType) throws Exception {

        PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
        @SuppressWarnings("unchecked")
        Specification<TCreepersListPatent> spec = (Specification<TCreepersListPatent>) CommonMethodUtils
                .buildSpecification(searchParams, "Dt");
        Page<TCreepersListPatent> listPatentPage = listPatentDao.findAll(spec, pageable);
        List<TCreepersListPatent> listPatentList = listPatentPage.getContent();
        List<CreepersListPatentDTO> creepersListPatentDTOList = new ArrayList<CreepersListPatentDTO>();
        creepersListPatentDTOList = BeanMapper.mapList(listPatentList, CreepersListPatentDTO.class);
        Page<CreepersListPatentDTO> resultPage = new PageImpl<CreepersListPatentDTO>(
                new ArrayList<CreepersListPatentDTO>(creepersListPatentDTOList), pageable,
                listPatentPage.getTotalElements());
        return resultPage;
    }

    @Override
    public List<CreepersListPatentDTO> findListMerName(String merName) {

        List<TCreepersListPatent> list = listPatentDao.queryListByMerName(merName);
        List<CreepersListPatentDTO> dtoList = new ArrayList<CreepersListPatentDTO>();
        dtoList = BeanMapper.mapList(list, CreepersListPatentDTO.class);
        return dtoList;
    }

}
