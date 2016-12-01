package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import com.fosun.fc.projects.creepers.entity.TCreepersPublicEnforcement;

/**
 * 
 * <p>
 * description: 简版征信强制执行记录信息表 Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
public interface ICreepersPublicEnforcementService extends BaseService {

    public void saveTCreepersPublicEnforcement(TCreepersPublicEnforcement entity);

    public List<TCreepersPublicEnforcement> queryAllPublicEnforcement();

    public List<TCreepersPublicEnforcement> findByRptNo(String rptNo);

    public Map<String, Object> findByRptNoForMap(String rptNo);
}
