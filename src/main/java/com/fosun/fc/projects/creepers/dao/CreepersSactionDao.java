package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersSaction;

/**
 *
 * <p>
 * description: T_CREEPERS_SACTION 信用中国-行政处罚黑名单表
 * <p>
 * 
 * @author MaXin
 * @since 2016-11-01 10:58:23
 * @see
 */

public interface CreepersSactionDao
        extends JpaRepository<TCreepersSaction, Long>, JpaSpecificationExecutor<TCreepersSaction> {
    
    @Query("select t from TCreepersSaction t where t.name = :name")
    List<TCreepersSaction> findByName(@Param("name") String name);

    @Modifying(clearAutomatically = true)
    @Query("delete  from TCreepersSaction t where t.name = :name")
    void deleteByName(@Param("name") String name);

    TCreepersSaction findTopByName(String name);
}
