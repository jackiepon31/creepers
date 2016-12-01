package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fosun.fc.projects.creepers.dto.CreepersAccountBakDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersAccountBak;

/**
 * 
 * <p>
 * description: 简版征信账号信息表 Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
public interface ICreepersAccountBakService extends BaseService {

    public void saveOrIfNotAccountBak(TCreepersAccountBak entity);
    
    public void saveTCreepersAccountBak(TCreepersAccountBak entity);

    public void updateTCreepersAccountBak(TCreepersAccountBak entity);

    public List<TCreepersAccountBak> queryAllCreepersAccount();

    public List<TCreepersAccountBak> findByRptNo(String rptNo);

    public List<TCreepersAccountBak> findByUsr(String usr);

    public List<TCreepersAccountBak> findByStatus(String status);

    public Page<CreepersAccountBakDTO> geTCreepersAccountBakList(Map<String, Object> searchParams, int pageNumber,
            int pageSize, String sortType);

    public Map<String, Object> findByRptNoForMap(String rptNo);

    public void updateStatusByRptNo(String rptNo);

    public TCreepersAccountBak findTop1ByUsrAndCde(String usr, String cde);
}
