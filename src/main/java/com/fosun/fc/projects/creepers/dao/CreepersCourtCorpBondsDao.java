package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersCourtCorpBonds;

/**
*
* <p>
* description:
* T_CREEPERS_COURT_CORP_BONDS	限制发行企业债黑名单表
* <p>
* @author MaXin
* @since 2016-10-10 15:40:55
* @see
*/

public interface CreepersCourtCorpBondsDao extends JpaRepository<TCreepersCourtCorpBonds, Long>, JpaSpecificationExecutor<TCreepersCourtCorpBonds> {

    @Query("select t from TCreepersCourtCorpBonds t where t.name = :name")
    List<TCreepersCourtCorpBonds> findByName(@Param("name") String name);

    @Modifying(clearAutomatically = true)
    @Query("delete  from TCreepersCourtCorpBonds t where t.name = :name")
    void deleteByName(@Param("name") String name);
    
    @Query("select t from TCreepersCourtCorpBonds t where t.name = :name and t.code = :code")
    List<TCreepersCourtCorpBonds> findListByNameAndCode(String name,String code);

    TCreepersCourtCorpBonds findTopByName(String name);
}