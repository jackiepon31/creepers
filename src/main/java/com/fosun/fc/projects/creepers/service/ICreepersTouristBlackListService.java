package com.fosun.fc.projects.creepers.service;

import java.util.List;

import com.fosun.fc.projects.creepers.entity.TCreepersTourBlackList;

/**
 * 
 * <p>
 * description: 信用中国-导游旅行社黑名单信息Service
 * </p>
 * 
 * @author MaXin
 * @since 2016-11-10 18:44:06
 * @see
 */
public interface ICreepersTouristBlackListService extends BaseService {

    public void saveEntity(TCreepersTourBlackList entity);

    public void processByJob(String jobName);

    public List<TCreepersTourBlackList> findListByGuideNo(String merName);

    public List<TCreepersTourBlackList> findListByAgentNameAndType(String merName);
}
