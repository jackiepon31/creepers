package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import com.fosun.fc.projects.creepers.entity.TCreepersOlDetail;

/**
 * 
 * <p>
 * description: 简版征信其他贷款明细信息表 Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
public interface ICreepersOlDetailService extends BaseService {

    public void saveTCreepersOlDetail(TCreepersOlDetail entity);

    public List<TCreepersOlDetail> queryAllOlDetail();

    public List<TCreepersOlDetail> findByRptNo(String rptNo);

    public Map<String, Object> findByRptNoForMap(String rptNo);
}
