package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersListTourGuide;

/**
 *
 * <p>
 * description: T_CREEPERS_LIST_TOUR_GUIDE 爬虫任务队列-导游信息
 * <p>
 * 
 * @author MaXin
 * @since 2016-10-31 17:22:03
 * @see
 */

public interface CreepersListTourGuideDao
        extends JpaRepository<TCreepersListTourGuide, Long>, JpaSpecificationExecutor<TCreepersListTourGuide> {


    List<TCreepersListTourGuide> findByGuideNoAndCardNo(String guideNo, String cardNo);

    List<TCreepersListTourGuide> findByGuideNoOrCertNo(String guideNo, String cardNo);

    List<TCreepersListTourGuide> findByGuideNoAndCardNoAndCertNo(String guideNo, String cardNo, String certNo);

    List<TCreepersListTourGuide> findByGuideNoAndCertNoOrCardNoAndCertNo(String guideNo, String certNo1, String cardNo, String certNo2);

    TCreepersListTourGuide findTop1ByGuideNoOrCardNo(String guideNo, String cardNo);

}
