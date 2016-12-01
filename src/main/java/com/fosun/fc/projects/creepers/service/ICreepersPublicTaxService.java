package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import com.fosun.fc.projects.creepers.entity.TCreepersPublicTax;

/**
 * 
 * <p>
 * description: 简版征信欠税记录信息表 Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
public interface ICreepersPublicTaxService extends BaseService {

    public void saveTCreepersPublicTax(TCreepersPublicTax entity);

    public List<TCreepersPublicTax> queryAllPublicTax();

    public List<TCreepersPublicTax> findByRptNo(String rptNo);

    public Map<String, Object> findByRptNoForMap(String rptNo);
}
