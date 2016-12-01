package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import com.fosun.fc.projects.creepers.entity.TCreepersCompensatory;

/**
 * 
 * <p>
 * description: 简版征信保证人代偿信息表 Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
public interface ICreepersCompensatoryService extends BaseService {

    public void saveTCreepersCompensatory(TCreepersCompensatory entity);

    public List<TCreepersCompensatory> queryAllCompensatory();

    public List<TCreepersCompensatory> findByRptNo(String rptNo);

    public Map<String, Object> findByRptNoForMap(String rptNo);
}
