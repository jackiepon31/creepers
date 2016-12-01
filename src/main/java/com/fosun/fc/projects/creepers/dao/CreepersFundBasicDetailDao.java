package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersFundBasicDetail;

/**
 *
 * <p>
 * description: T_CREEPERS_FUND_BASIC_DETAIL 公积金账户本年度明细表
 * <p>
 * 
 * @author pengyk
 * @since 2016-09-06 14:47:24
 * @see
 */

public interface CreepersFundBasicDetailDao
        extends JpaRepository<TCreepersFundBasicDetail, Long>, JpaSpecificationExecutor<TCreepersFundBasicDetail> {
    @Query("select t from TCreepersFundBasicDetail t where t.loginName =:loginName")
    List<TCreepersFundBasicDetail> findByLoginName(@Param("loginName") String loginName);

    @Modifying
    @Query("delete  from TCreepersFundBasicDetail t where t.loginName = :loginName")
    void deleteByLoginName(@Param("loginName") String loginName);
}
