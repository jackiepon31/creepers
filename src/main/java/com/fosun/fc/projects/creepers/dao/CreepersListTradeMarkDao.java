package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersListTradeMark;

/**
 *
 * <p>
 * description: T_CREEPERS_LIST_TRADE_MARK 爬虫任务队列-商标信息
 * <p>
 * 
 * @author MaXin
 * @since 2016-08-05 10:45:18
 * @see
 */

public interface CreepersListTradeMarkDao
        extends JpaRepository<TCreepersListTradeMark, Long>, JpaSpecificationExecutor<TCreepersListTradeMark> {
    @Query("select t from TCreepersListTradeMark t where t.merName = :merName")
    List<TCreepersListTradeMark> queryListByMerName(@Param("merName") String merName);

    @Modifying(clearAutomatically = true)
    @Query("update TCreepersListTradeMark t set t.flag = :flag, t.updatedDt = sysdate where t.merName = :merName")
    void updateListByMerName(@Param("merName") String merName, @Param("flag") String flag);
}
