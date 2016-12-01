package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersEntAsset;

/**
 *
 * <p>
 * description: T_CREEPERS_ENT_INVEST 爬虫信息-企业资产状况信息
 * <p>
 * 
 * @author MaXin
 * @since 2016-7-28 18:23:19
 * @see
 */

public interface CreepersEntAssetDao extends JpaRepository<TCreepersEntAsset, Long>, JpaSpecificationExecutor<TCreepersEntAsset> {
}
