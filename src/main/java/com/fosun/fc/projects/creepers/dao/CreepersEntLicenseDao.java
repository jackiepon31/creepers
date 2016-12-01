package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersEntLicense;

/**
*
* <p>
* description:
* T_CREEPERS_ENT_LICENSE	爬虫信息-企业公示行政许可信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersEntLicenseDao extends JpaRepository<TCreepersEntLicense, Long>, JpaSpecificationExecutor<TCreepersEntLicense> {
}