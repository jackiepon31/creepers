package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersTourGuide;

/**
 *
 * <p>
 * description: T_CREEPERS_TOUR_GUIDE 信用中国-导游信息
 * <p>
 * 
 * @author MaXin
 * @since 2016-10-31 17:22:04
 * @see
 */

public interface CreepersTourGuideDao
        extends JpaRepository<TCreepersTourGuide, Long>, JpaSpecificationExecutor<TCreepersTourGuide> {

    TCreepersTourGuide findTop1ByGuideNo(String guideNo);

    List<TCreepersTourGuide> findByGuideNoAndCardNo(String guideNo, String cardNo);

    List<TCreepersTourGuide> findByGuideNoOrCertNo(String guideNo, String cardNo);

    List<TCreepersTourGuide> findByGuideNoAndCardNoAndCertNo(String guideNo, String cardNo, String certNo);

    List<TCreepersTourGuide> findByGuideNoAndCertNoOrCardNoAndCertNo(String guideNo, String certNo1, String cardNo,
            String certNo2);
}
