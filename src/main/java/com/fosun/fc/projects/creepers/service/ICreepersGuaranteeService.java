package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import com.fosun.fc.projects.creepers.entity.TCreepersGuarantee;

/**
 * 
 * <p>
 * description: 简版征信为他人担保信息表 Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
public interface ICreepersGuaranteeService extends BaseService {

    public void saveTCreepersGuarantee(TCreepersGuarantee entity);

    public List<TCreepersGuarantee> queryAllGuarantee();

    public List<TCreepersGuarantee> findByRptNo(String rptNo);

    public Map<String, Object> findByRptNoForMap(String rptNo);
}
