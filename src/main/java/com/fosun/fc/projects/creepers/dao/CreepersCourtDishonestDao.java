package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersCourtDishonest;

/**
*
* <p>
* description:
* T_CREEPERS_COURT_DISHONEST	最高法失信人黑名单主表
* <p>
* @author MaXin
* @since 2016-10-10 15:40:55
* @see
*/

public interface CreepersCourtDishonestDao extends JpaRepository<TCreepersCourtDishonest, Long>, JpaSpecificationExecutor<TCreepersCourtDishonest> {

    @Query("select t from TCreepersCourtDishonest t where t.name = :name")
    List<TCreepersCourtDishonest> findByName(@Param("name") String name);

    @Modifying(clearAutomatically = true)
    @Query("delete  from TCreepersCourtDishonest t where t.name = :name")
    void deleteByName(@Param("name") String name);
    
    @Query("select t from TCreepersCourtDishonest t where t.name = :name and t.code = :code")
    List<TCreepersCourtDishonest> findListByNameAndCode(@Param("name") String name,@Param("code") String code);

    TCreepersCourtDishonest findTopByName(String name);
}