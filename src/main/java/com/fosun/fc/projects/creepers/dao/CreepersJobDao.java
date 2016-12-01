package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersJob;

/**
 *
 * <p>
 * description: T_CREEPERS_JOB 爬虫批处理任务调度表
 * <p>
 * 
 * @author pengyk
 * @since 2016-10-09 15:42:59
 * @see
 */

public interface CreepersJobDao extends JpaRepository<TCreepersJob, Long>, JpaSpecificationExecutor<TCreepersJob> {
    @Modifying(clearAutomatically = true)
    @Query("update TCreepersJob t set t.cronExpression = :cronExpression, t.updatedDt = sysdate where t.jobName = :jobName and t.jobGroup = :jobGroup")
    void updateCronExpByJobName(@Param("cronExpression") String cronExpression, @Param("jobName") String jobName,
            @Param("jobGroup") String jobGroup);

    @Modifying(clearAutomatically = true)
    @Query("update TCreepersJob t set t.status = :status, t.updatedDt = sysdate where t.jobName = :jobName and t.jobGroup = :jobGroup")
    void updateStatusByJobName(@Param("status") String status, @Param("jobName") String jobName,
            @Param("jobGroup") String jobGroup);

    @Query("select t from TCreepersJob t where t.status != 'NONE'")
    List<TCreepersJob> findAllUseJobs();

    long countByJobName(String jobName);

    TCreepersJob findTop1ByJobName(String jobName);
    
    @Modifying(clearAutomatically = true)
    @Query("update TCreepersJob t set t.memo = :request, t.updatedDt = sysdate where t.jobName = :jobName")
    void updateRequestByJobName(@Param("jobName") String jobName, @Param("request") String request);
}
