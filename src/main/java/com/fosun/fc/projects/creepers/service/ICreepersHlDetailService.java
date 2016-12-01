package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import com.fosun.fc.projects.creepers.entity.TCreepersHlDetail;

/**
 * 
 * <p>
 * description: 简版征信住房贷款明细信息表 Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
public interface ICreepersHlDetailService extends BaseService {

    public void saveTCreepersHlDetail(TCreepersHlDetail entity);

    public List<TCreepersHlDetail> queryAllHlDetail();

    public List<TCreepersHlDetail> findByRptNo(String rptNo);

    public Map<String, Object> findByRptNoForMap(String rptNo);
}
