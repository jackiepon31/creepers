package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersCourtAnnounce;

/**
 *
 * <p>
 * description: T_CREEPERS_COURT_ANNOUNCE 法院公告信息表
 * <p>
 * 
 * @author MaXin
 * @since 2016-07-14 17:38:05
 * @see
 */

public interface CreepersCourtAnnounceDao
        extends JpaRepository<TCreepersCourtAnnounce, Long>, JpaSpecificationExecutor<TCreepersCourtAnnounce> {

    @Query("select t from TCreepersCourtAnnounce t where t.merName =:merName")
    List<TCreepersCourtAnnounce> findByMerName(@Param("merName") String merName);

    @Modifying
    @Query("delete  from TCreepersCourtAnnounce t where t.merName = :merName")
    void deleteByMerName(@Param("merName") String merName);
}
