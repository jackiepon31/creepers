package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersTaxEvasion;

/**
 *
 * <p>
 * description: T_CREEPERS_TAX_EVASION 信用中国-行政处罚黑名单
 * <p>
 * 
 * @author MaXin
 * @since 2016-11-01 10:58:23
 * @see
 */

public interface CreepersTaxEvasionDao
        extends JpaRepository<TCreepersTaxEvasion, Long>, JpaSpecificationExecutor<TCreepersTaxEvasion> {
    
    List<TCreepersTaxEvasion> findByName(String name);

    TCreepersTaxEvasion findTopByName(String name);
}
