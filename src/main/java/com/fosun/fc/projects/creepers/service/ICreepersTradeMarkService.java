package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fosun.fc.projects.creepers.dto.CreepersTradeMarkDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersTradeMark;

/**
 * 
 * <p>
 * description: 商标信息Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
public interface ICreepersTradeMarkService extends BaseService {

    public List<TCreepersTradeMark> findByMerName(String merName);

    public void processByMerName(String merName);
    
    public Page<CreepersTradeMarkDTO> queryTradeMarkList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType) throws Exception;
    
    public void deleteByMerName(String merName);
    
    public void saveEntity(TCreepersTradeMark entity);
    
    public void saveEntity(List<TCreepersTradeMark> entity);
    
}
