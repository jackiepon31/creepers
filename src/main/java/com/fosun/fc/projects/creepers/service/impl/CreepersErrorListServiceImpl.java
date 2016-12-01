package com.fosun.fc.projects.creepers.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.dao.CreepersErrorListDao;
import com.fosun.fc.projects.creepers.dto.CreepersErrorListDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersErrorList;
import com.fosun.fc.projects.creepers.service.ICreepersErrorListService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

/**
 * <p>
 * description:错误列表Service
 * </p>
 * 
 * @author pengyk
 * @since 2016年8月10日
 * @see
 */
@Service
@Transactional
public class CreepersErrorListServiceImpl implements ICreepersErrorListService {

    @Autowired
    private CreepersErrorListDao creepersErrorListDao;

    @Override
    public void saveError(String merName, String errorDesc, String taskType) {
        List<TCreepersErrorList> list = creepersErrorListDao.queryListByMerName(merName, taskType);
        TCreepersErrorList tCreepersErrorList = new TCreepersErrorList();
        if (!CommonMethodUtils.isEmpty(list)) {
            tCreepersErrorList = list.get(0);
            tCreepersErrorList.setErrorDesc(errorDesc);
        } else {
            tCreepersErrorList.setMerName(merName);
            tCreepersErrorList.setErrorDesc(errorDesc);
            tCreepersErrorList.setTaskType(taskType);
            tCreepersErrorList.setFlag("0");
        }
        CommonMethodUtils.setByDT(tCreepersErrorList);
        creepersErrorListDao.save(tCreepersErrorList);
    }

    @Override
    public List<TCreepersErrorList> queryList(CreepersErrorListDTO param) {
        Specification<TCreepersErrorList> spec = new Specification<TCreepersErrorList>() {
            @Override
            public Predicate toPredicate(Root<TCreepersErrorList> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (param.getTaskType() != null && param.getTaskType().trim().length() > 0) {
                    list.add(cb.equal(root.get("taskType").as(String.class), param.getTaskType()));
                }
                if (param.getFlag() != null && param.getFlag().trim().length() > 0) {
                    list.add(cb.like(root.get("flag").as(String.class), "%" + param.getFlag() + "%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
        return creepersErrorListDao.findAll(spec);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<CreepersErrorListDTO> queryErrorList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType) {
        PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<TCreepersErrorList> spec = (Specification<TCreepersErrorList>) buildSpecification(searchParams);
        Page<TCreepersErrorList> errorListPage = creepersErrorListDao.findAll(spec, pageable);
        List<TCreepersErrorList> errorList = errorListPage.getContent();
        List<CreepersErrorListDTO> errorListDTOList = new ArrayList<CreepersErrorListDTO>();
        errorListDTOList = BeanMapper.mapList(errorList, CreepersErrorListDTO.class);
        Page<CreepersErrorListDTO> errorListDTOPage = new PageImpl<CreepersErrorListDTO>(
                new ArrayList<CreepersErrorListDTO>(errorListDTOList), pageable, errorListPage.getTotalElements());
        return errorListDTOPage;
    }

}
