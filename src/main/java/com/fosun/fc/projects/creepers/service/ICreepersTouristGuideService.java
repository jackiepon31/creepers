package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersTourGuide;

/**
 * 
 * <p>
 * description: 信用中国-导游信息Service
 * </p>
 * 
 * @author MaXin
 * @since 2016-11-2 12:26:10
 * @see
 */
public interface ICreepersTouristGuideService extends BaseService {

    public void saveEntity(TCreepersTourGuide entity);

    public void processByParam(CreepersLoginParamDTO param);
    
    public List<TCreepersTourGuide> findByEntityForMap(TCreepersTourGuide entity);

    public Map<String ,Object> findByParamForMap(CreepersLoginParamDTO param);
}
