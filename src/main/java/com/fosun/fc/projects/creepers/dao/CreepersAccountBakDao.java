package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersAccountBak;
/**
 * 
 *<p>
 *description:
 *简版征信账号信息表
 *</p>
 * @author MaXin
 * @since 2016年5月25日
 * @see
 */
public interface CreepersAccountBakDao
        extends JpaRepository<TCreepersAccountBak, Long>, JpaSpecificationExecutor<TCreepersAccountBak> {

    @Query("select t from TCreepersAccountBak t where t.rptNo = :rptNo")
    List<TCreepersAccountBak> queryByRptNo(@Param("rptNo") String rptNo);

    @Query("select t from TCreepersAccountBak t where t.usr = :usr")
    List<TCreepersAccountBak> queryByUsr(@Param("usr")String usr);

    @Query("select t from TCreepersAccountBak t where t.status = :status")
    List<TCreepersAccountBak> queryByStatus(@Param("status")String status);

    @Modifying 
    @Query("update TCreepersAccountBak t set t.status = '1' where t.rptNo = :rptNo")
    void updateStatusByRptNo(@Param("rptNo")String rptNo);
    
    TCreepersAccountBak findTop1ByUsrAndCde(String usr,String cde);

}
