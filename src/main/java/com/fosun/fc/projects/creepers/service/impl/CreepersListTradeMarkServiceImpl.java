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
import com.fosun.fc.projects.creepers.dao.CreepersListTradeMarkDao;
import com.fosun.fc.projects.creepers.dto.CreepersListTradeMarkDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersListTradeMark;
import com.fosun.fc.projects.creepers.service.ICreepersListTradeMarkService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

@Service
@Transactional
public class CreepersListTradeMarkServiceImpl implements ICreepersListTradeMarkService {

    @Autowired
    private CreepersListTradeMarkDao listTradeMarkDao;

    @Override
    public Page<CreepersListTradeMarkDTO> queryListTradeMarkList(Map<String, Object> searchParams, int pageNumber,
            int pageSize, String sortType) throws Exception {

        PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
        @SuppressWarnings("unchecked")
        Specification<TCreepersListTradeMark> spec = (Specification<TCreepersListTradeMark>) CommonMethodUtils
                .buildSpecification(searchParams, "Dt");
        Page<TCreepersListTradeMark> listTradeMarkPage = listTradeMarkDao.findAll(spec, pageable);
        List<TCreepersListTradeMark> listTradeMarkList = listTradeMarkPage.getContent();
        List<CreepersListTradeMarkDTO> creepersListTradeMarkDTOList = new ArrayList<CreepersListTradeMarkDTO>();
        creepersListTradeMarkDTOList = BeanMapper.mapList(listTradeMarkList, CreepersListTradeMarkDTO.class);
        Page<CreepersListTradeMarkDTO> resultPage = new PageImpl<CreepersListTradeMarkDTO>(
                new ArrayList<CreepersListTradeMarkDTO>(creepersListTradeMarkDTOList), pageable,
                listTradeMarkPage.getTotalElements());

        return resultPage;
    }

    @Override
    public List<CreepersListTradeMarkDTO> findListByMerName(String merName) {

        List<TCreepersListTradeMark> list = listTradeMarkDao.queryListByMerName(merName);
        List<CreepersListTradeMarkDTO> dtoList = new ArrayList<CreepersListTradeMarkDTO>();
        dtoList = BeanMapper.mapList(list, CreepersListTradeMarkDTO.class);
        return dtoList;
    }

}
