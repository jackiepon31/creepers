package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import com.fosun.fc.projects.creepers.entity.TCreepersPublicSanction;

/**
 * 
 * <p>
 * description: 简版征信行政处罚记录信息表 Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
public interface ICreepersPublicSanctionService extends BaseService {

    public void saveTCreepersPublicSanction(TCreepersPublicSanction entity);

    public List<TCreepersPublicSanction> queryAllPublicSanction();

    public List<TCreepersPublicSanction> findByRptNo(String rptNo);

    public Map<String, Object> findByRptNoForMap(String rptNo);
}
