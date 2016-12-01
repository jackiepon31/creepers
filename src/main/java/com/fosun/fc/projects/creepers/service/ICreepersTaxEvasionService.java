package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fosun.fc.projects.creepers.dto.CreepersTaxEvasionDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersTaxEvasion;

public interface ICreepersTaxEvasionService extends BaseService {

    public Page<CreepersTaxEvasionDTO> findList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType);

    public void processByJob(String JobName);

    public List<TCreepersTaxEvasion> findListByName(String name);

    public void saveEntity(TCreepersTaxEvasion entity);

    public void saveEntity(List<TCreepersTaxEvasion> entityList);

    public void saveOrUpdate(TCreepersTaxEvasion entity);

}
