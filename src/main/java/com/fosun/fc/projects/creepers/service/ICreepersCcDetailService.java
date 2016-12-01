package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import com.fosun.fc.projects.creepers.entity.TCreepersCcDetail;

/**
 * 
 * <p>
 * description: 简版征信行用卡明细信息表 Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
public interface ICreepersCcDetailService extends BaseService {

    public void saveTCreepersCcDetail(TCreepersCcDetail entity);

    public List<TCreepersCcDetail> queryAllCcDetail();

    public List<TCreepersCcDetail> findByRptNo(String rptNo);

    public Map<String, Object> findByRptNoForMap(String rptNo);
}
