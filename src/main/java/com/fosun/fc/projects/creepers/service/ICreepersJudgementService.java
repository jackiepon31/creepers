package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fosun.fc.projects.creepers.dto.CreepersJudgementDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersJudgement;

/**
 * 
 * <p>
 * description: 法院文书信息表 Service
 * </p>
 * 
 * @author Pengyk
 * @since 2016年6月17日
 * @see
 */
public interface ICreepersJudgementService extends BaseService {

    public List<TCreepersJudgement> queryAll();

    public List<TCreepersJudgement> findByMerName(String merName);

    public Map<String, Object> findByMerNoForMap(String merNo);

    public void processByMerName(String merName);
    
    public Page<CreepersJudgementDTO> queryJudgementList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType);
    
    public void deleteByMerName(String merName);
    
    public void saveEntity(TCreepersJudgement entity);

    public void saveEntity(List<TCreepersJudgement> entityList);
}
