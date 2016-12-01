package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fosun.fc.projects.creepers.dto.CreepersListJudgementDTO;

public interface ICreepersListJudgementService extends BaseService {

    public Page<CreepersListJudgementDTO> findListJudgementList(Map<String, Object> searchParams, int pageNumber,
            int pageSize, String sortType) throws Exception;

    public List<CreepersListJudgementDTO> findListByMerName(String merName);
}
