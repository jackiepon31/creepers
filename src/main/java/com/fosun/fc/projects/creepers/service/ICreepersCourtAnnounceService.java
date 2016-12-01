package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fosun.fc.projects.creepers.dto.CreepersCourtAnnounceDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtAnnounce;

/**
 * <p>
 * description:爬虫信息-法院公告信息service
 * </p>
 * 
 * @author LiZhanPing
 * @since 2016年8月8日
 * @see
 */
public interface ICreepersCourtAnnounceService extends BaseService {

    public List<TCreepersCourtAnnounce> findByMerName(String merName);

    public void processByMerName(String merName);

    public Page<CreepersCourtAnnounceDTO> findCourtAnnounceList(Map<String, Object> searchParams, int pageNumber,
            int pageSize, String sortType);

    public void deleteByMerName(String name);

    public void saveEntity(TCreepersCourtAnnounce entity);

    public void saveEntity(List<TCreepersCourtAnnounce> entityList);
}
