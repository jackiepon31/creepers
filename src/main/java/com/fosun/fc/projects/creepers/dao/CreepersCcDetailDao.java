package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersCcDetail;

/**
 * 
 *<p>
 *description:
 *简版征信行用卡明细信息表
 *</p>
 * @author MaXin
 * @since 2016年5月25日
 * @see
 */
public interface CreepersCcDetailDao
        extends JpaRepository<TCreepersCcDetail, Long>, JpaSpecificationExecutor<TCreepersCcDetail> {

    @Query("select t from TCreepersCcDetail t where t.rptNo = :rptNo")
    List<TCreepersCcDetail> queryByRptNo(@Param("rptNo")String rptNo);

}
