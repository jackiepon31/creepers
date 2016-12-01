package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersTaxEvasionDetail;

/**
 *
 * <p>
 * description: T_CREEPERS_TAX_EVASION_DETAIL 信用中国-行政处罚黑名单详情
 * <p>
 * 
 * @author MaXin
 * @since 2016-11-01 10:58:23
 * @see
 */

public interface CreepersTaxEvasionDetailDao
        extends JpaRepository<TCreepersTaxEvasionDetail, Long>, JpaSpecificationExecutor<TCreepersTaxEvasionDetail> {
    
    List<TCreepersTaxEvasionDetail> findByName(String name);
    
    @Modifying(clearAutomatically = true)
    @Query("delete  from TCreepersTaxEvasionDetail t where t.name = :name")
    void deleteByName(@Param("name") String name);
}
