package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fosun.fc.projects.creepers.dto.CreepersListTradeMarkDTO;

public interface ICreepersListTradeMarkService extends BaseService {

    public Page<CreepersListTradeMarkDTO> queryListTradeMarkList(Map<String, Object> searchParams, int pageNumber,
            int pageSize, String sortType) throws Exception;

    public List<CreepersListTradeMarkDTO> findListByMerName(String merName);

}
