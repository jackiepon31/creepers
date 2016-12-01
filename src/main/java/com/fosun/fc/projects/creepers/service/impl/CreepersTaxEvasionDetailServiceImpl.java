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
import com.fosun.fc.projects.creepers.dao.CreepersTaxEvasionDetailDao;
import com.fosun.fc.projects.creepers.dto.CreepersTaxEvasionDetailDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersTaxEvasionDetail;
import com.fosun.fc.projects.creepers.service.ICreepersTaxEvasionDetailService;

@Service
@Transactional
public class CreepersTaxEvasionDetailServiceImpl implements ICreepersTaxEvasionDetailService {

    @Autowired
    private CreepersTaxEvasionDetailDao creepersTaxEvasionDetailDao;

    @SuppressWarnings("unchecked")
    @Override
    public Page<CreepersTaxEvasionDetailDTO> findList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType) {

        PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<TCreepersTaxEvasionDetail> spec = (Specification<TCreepersTaxEvasionDetail>) buildSpecification(
                searchParams);
        Page<TCreepersTaxEvasionDetail> page = creepersTaxEvasionDetailDao.findAll(spec, pageable);
        List<TCreepersTaxEvasionDetail> list = page.getContent();
        List<CreepersTaxEvasionDetailDTO> dtoList = new ArrayList<CreepersTaxEvasionDetailDTO>();
        dtoList = BeanMapper.mapList(list, CreepersTaxEvasionDetailDTO.class);
        Page<CreepersTaxEvasionDetailDTO> result = new PageImpl<CreepersTaxEvasionDetailDTO>(
                new ArrayList<CreepersTaxEvasionDetailDTO>(dtoList), pageable, page.getTotalElements());
        return result;
    }

    @Override
    public List<TCreepersTaxEvasionDetail> findListByName(String name) {
        
        return creepersTaxEvasionDetailDao.findByName(name);
    }

    @Override
    public void saveEntity(TCreepersTaxEvasionDetail entity) {
        creepersTaxEvasionDetailDao.saveAndFlush(entity);
    }

    @Override
    public void saveEntity(List<TCreepersTaxEvasionDetail> entityList) {
        for(TCreepersTaxEvasionDetail entity:entityList)
            creepersTaxEvasionDetailDao.saveAndFlush(entity);
    }

    @Override
    public void deleteByName(String name) {
        
        creepersTaxEvasionDetailDao.deleteByName(name);
    }
}
