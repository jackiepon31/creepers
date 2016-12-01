package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fosun.fc.projects.creepers.dto.CreepersPatentDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersPatent;

/**
 * 
 * <p>
 * description: 爬虫信息-专利信息 Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年8月4日15:42:07
 * @see
 */
public interface ICreepersPatentService extends BaseService {

    public List<TCreepersPatent> queryAllOlDetail();

    public List<TCreepersPatent> findByMerName(String rptNo);

    public Map<String, Object> findByRptNoForMap(String rptNo);

    public void processByMerName(String merName);

    public Page<CreepersPatentDTO> queryPatentList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType);
    
    public void deleteByMerName(String merName);
    
    public void saveEntity(TCreepersPatent entity);
    
    public void saveEntity(List<TCreepersPatent> entity);
    
}
