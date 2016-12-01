package com.fosun.fc.projects.creepers.service;

import java.util.Map;

import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;

public interface ICreepersInsuranceService extends BaseService {
    
    public void processByParam(CreepersLoginParamDTO param);

    public Map<String, Object> findListByCertNoForMap(String certNo);
    
    public void deleteByCertNo(String certNo);
    
    public void saveEntity(Map<String, Object> map) throws Exception;
}
