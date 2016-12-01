package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersMedical;

/**
 *
 * <p>
 * description: T_CREEPERS_MEDICAL 医药类信息
 * <p>
 * 
 * @author MaXin
 * @since 2016-11-22 13:27:32
 * @see
 */

public interface CreepersMedicalDao
        extends JpaRepository<TCreepersMedical, Long>, JpaSpecificationExecutor<TCreepersMedical> {

    List<TCreepersMedical> findByKey(String key);

    TCreepersMedical findTop1ByKey(String key);
    
    TCreepersMedical findTop1ByKeyAndType(String key, String type);
}
