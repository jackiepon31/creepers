package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fosun.fc.projects.creepers.dto.CreepersSactionDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersSaction;

public interface ICreepersSactionService extends BaseService {

    public Page<CreepersSactionDTO> findList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType);

    public void processByJob(String JobName);

    public List<TCreepersSaction> findListByName(String name);

    public void saveEntity(TCreepersSaction entity);

    public void saveEntity(List<TCreepersSaction> entityList);
    
    public void saveOrUpdate(TCreepersSaction entity);
}
