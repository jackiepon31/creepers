package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import com.fosun.fc.projects.creepers.entity.TCreepersQueryLog;

/**
 * 
 * <p>
 * description: 简版征信查询记录信息表Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
public interface ICreepersQueryLogService extends BaseService {

    public void saveTCreepersQueryLog(TCreepersQueryLog entity);

    public List<TCreepersQueryLog> queryAllQueryLog();

    public List<TCreepersQueryLog> findByRptNo(String rptNo);

    public Map<String, Object> findByRptNoForMap(String rptNo);
    
}
