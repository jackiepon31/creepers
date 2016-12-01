package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import com.fosun.fc.projects.creepers.entity.TCreepersPublicCivil;

/**
 * 
 * <p>
 * description: 简版征信民事判决记录信息表Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
public interface ICreepersPublicCivilService extends BaseService {

    public void saveCreepersPublicCivilDTO(TCreepersPublicCivil entity);

    public List<TCreepersPublicCivil> queryAllCompensatory();

    public List<TCreepersPublicCivil> findByRptNo(String rptNo);

    public Map<String, Object> findByRptNoForMap(String rptNo);
}
