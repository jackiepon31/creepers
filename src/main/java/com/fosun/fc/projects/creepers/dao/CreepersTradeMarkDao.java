package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersTradeMark;

/**
*
* <p>
* description:
* T_CREEPERS_TRADE_MARK	爬虫信息-商标信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersTradeMarkDao extends JpaRepository<TCreepersTradeMark, Long>, JpaSpecificationExecutor<TCreepersTradeMark> {
    
    @Query("select t from TCreepersTradeMark t where t.merName = :merName")
    List<TCreepersTradeMark> findByMerName(@Param("merName") String merName);
    
    @Modifying
    @Query("delete  from TCreepersTradeMark t where t.merName = :merName")
    void deleteByMerName(@Param("merName") String merName);

}