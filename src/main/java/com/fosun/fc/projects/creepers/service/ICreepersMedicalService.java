package com.fosun.fc.projects.creepers.service;

import java.util.List;

import com.fosun.fc.projects.creepers.entity.TCreepersMedical;

/**
 * 
 * <p>
 * description: 医药信息Service
 * </p>
 * 
 * @author MaXin
 * @since 2016-11-22 14:10:45
 * @see
 */
public interface ICreepersMedicalService extends BaseService {

    public void saveEntity(TCreepersMedical entity);
    
    public void saveEntity(List<TCreepersMedical> entityList);

    public void processByJob(String jobName);

    public List<TCreepersMedical> findListByKey(String key);
}
