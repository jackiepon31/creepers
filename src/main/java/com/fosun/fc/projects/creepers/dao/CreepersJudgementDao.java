package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersJudgement;

/**
 *
 * <p>
 * description: T_CREEPERS_JUDGEMENT 爬虫信息-法院文书信息
 * <p>
 * 
 * @author MaXin
 * @since 2016-07-14 17:38:05
 * @see
 */

public interface CreepersJudgementDao
        extends JpaRepository<TCreepersJudgement, Long>, JpaSpecificationExecutor<TCreepersJudgement> {
	
    @Query("select t from TCreepersJudgement t where t.merName = :merName")
    List<TCreepersJudgement> queryByMerName(@Param("merName") String merName);
    
    @Modifying
    @Query("delete  from TCreepersJudgement t where t.merName = :merName")
    void deleteByMerName(@Param("merName") String merName);    
}
