package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fosun.fc.projects.creepers.dto.CreepersShixinAllDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersShixinAll;

public interface ICreepersShixinAllService extends BaseService {

    public Page<CreepersShixinAllDTO> findList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType);

    public void processByJob(String JobName);

    public List<TCreepersShixinAll> findListByName(String name);

    public void saveEntity(TCreepersShixinAll entity);

    public void saveEntity(List<TCreepersShixinAll> entityList);

    public void saveOrUpdate(TCreepersShixinAll entity);

}
