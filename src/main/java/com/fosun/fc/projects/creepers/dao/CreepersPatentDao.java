package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersPatent;

/**
 *
 * <p>
 * description: T_CREEPERS_PATENT 爬虫信息-专利信息
 * <p>
 * 
 * @author MaXin
 * @since 2016-07-14 17:38:05
 * @see
 */

public interface CreepersPatentDao
        extends JpaRepository<TCreepersPatent, Long>, JpaSpecificationExecutor<TCreepersPatent> {

    @Query("select t from TCreepersPatent t where t.merName = :merName")
    List<TCreepersPatent> findByMerName(@Param("merName") String merName);

    @Modifying
    @Query("delete  from TCreepersPatent t where t.merName = :merName")
    void deleteByMerName(@Param("merName") String merName);
}
