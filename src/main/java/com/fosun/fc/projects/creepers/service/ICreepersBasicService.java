package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import com.fosun.fc.projects.creepers.entity.TCreepersBasic;

/**
 * 
 * <p>
 * description: 简版征信基本信息表 Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
public interface ICreepersBasicService extends BaseService {

    public void saveTCreepersBasic(TCreepersBasic entity);

    public List<TCreepersBasic> queryAllBasic();

    public List<TCreepersBasic> findByRptNo(String RptNo);

    public List<TCreepersBasic> findByName(String name);

    public List<TCreepersBasic> findByIdNo(String idNo);

    public Map<String, Object> findByRptNoForMap(String rptNo);

}
