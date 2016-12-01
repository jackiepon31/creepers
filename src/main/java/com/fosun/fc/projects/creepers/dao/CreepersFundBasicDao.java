package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersFundBasic;

/**
 *
 * <p>
 * description: T_CREEPERS_FUND_BASIC 公积金账户信息表
 * <p>
 * 
 * @author pengyk
 * @since 2016-09-06 14:47:24
 * @see
 */

public interface CreepersFundBasicDao
        extends JpaRepository<TCreepersFundBasic, Long>, JpaSpecificationExecutor<TCreepersFundBasic> {
    @Query("select t from TCreepersFundBasic t where t.loginName =:loginName")
    List<TCreepersFundBasic> findByLoginName(@Param("loginName") String loginName);

    @Modifying
    @Query("delete  from TCreepersFundBasic t where t.loginName = :loginName")
    void deleteByLoginName(@Param("loginName") String loginName);
}
