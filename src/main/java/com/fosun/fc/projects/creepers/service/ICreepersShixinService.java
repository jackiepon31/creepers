package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fosun.fc.projects.creepers.dto.CreepersShixinDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersShixin;

public interface ICreepersShixinService extends BaseService {

    public Page<CreepersShixinDTO> findList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType);

    public void processByMerName(String merName);

    public List<TCreepersShixin> findListByMerName(String merName);

    public void deleteByName(String merName);

    public void saveEntity(TCreepersShixin entity);

    public void saveEntity(List<TCreepersShixin> entityList);

}
