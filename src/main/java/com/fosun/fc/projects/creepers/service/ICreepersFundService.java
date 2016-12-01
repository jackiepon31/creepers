package com.fosun.fc.projects.creepers.service;

import java.util.Map;

import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;

/**
 * 
 * <p>
 * description: 上海公积金 Service
 * </p>
 * 
 * @author pengyk
 * @since 2016年9月07日
 * @see
 */
public interface ICreepersFundService extends BaseService {

    public Map<String, Object> findByLoginNameForMap(String loginName);

	void deleteAndSave(Map<String,Object> map) throws Exception;
	
	public void processByParam(CreepersLoginParamDTO param);

	public void deleteByLoginName(String loginName);
}
