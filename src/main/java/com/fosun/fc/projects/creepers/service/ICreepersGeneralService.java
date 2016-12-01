package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import com.fosun.fc.projects.creepers.entity.TCreepersGeneral;

/**
 * 
 * <p>
 * description: 简版征信概要信息表 Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
public interface ICreepersGeneralService extends BaseService {

    public void saveTCreepersGeneral(TCreepersGeneral entity);

    public List<TCreepersGeneral> queryAllGeneral();

    public List<TCreepersGeneral> findByRptNo(String rptNo);

    public Map<String, Object> findByRptNoForMap(String rptNo);
}
