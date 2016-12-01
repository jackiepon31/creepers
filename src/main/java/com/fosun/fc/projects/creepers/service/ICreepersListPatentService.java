package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fosun.fc.projects.creepers.dto.CreepersListPatentDTO;

public interface ICreepersListPatentService extends BaseService {

    public Page<CreepersListPatentDTO> queryListPatentList(Map<String, Object> searchParams, int pageNumber,
            int pageSize, String sortType) throws Exception;

    public List<CreepersListPatentDTO> findListMerName(String merName);

}
