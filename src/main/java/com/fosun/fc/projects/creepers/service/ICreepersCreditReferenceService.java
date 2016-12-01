package com.fosun.fc.projects.creepers.service;

import java.util.Map;

import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;

public interface ICreepersCreditReferenceService extends BaseService {
    
    public void processByParam(CreepersLoginParamDTO param);

    public Map<String, Object> findByLoginNameForMap(CreepersLoginParamDTO param);
    
    public void deleteByLoginName(String certNo);
    
    public void saveEntity(Map<String, Object> map) throws Exception;
}
