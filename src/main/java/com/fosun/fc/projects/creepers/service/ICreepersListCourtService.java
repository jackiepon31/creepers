package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fosun.fc.projects.creepers.dto.CreepersListCourtDTO;

public interface ICreepersListCourtService extends BaseService {

    public Page<CreepersListCourtDTO> findListCourtList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType) throws Exception;

    public List<CreepersListCourtDTO> findListByMerName(String merName);
}
