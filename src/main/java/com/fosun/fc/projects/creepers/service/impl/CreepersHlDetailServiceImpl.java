/**
 * <p>
 * Copyright(c) @2016 Fortune Credit Management Co., Ltd.
 * </p>
 */
package com.fosun.fc.projects.creepers.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersHlDetailDao;
import com.fosun.fc.projects.creepers.dto.CreepersHlDetailDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersHlDetail;
import com.fosun.fc.projects.creepers.service.ICreepersHlDetailService;

/**
 * <p>
 * description:
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
@Component
@Transactional
public class CreepersHlDetailServiceImpl implements ICreepersHlDetailService {

    @Autowired
    private CreepersHlDetailDao creepersHlDetailDao;
    @Override
    public void saveTCreepersHlDetail(TCreepersHlDetail entity) {
        creepersHlDetailDao.save(entity);
    }

    @Override
    public List<TCreepersHlDetail> queryAllHlDetail() {
        return creepersHlDetailDao.findAll();
    }

    @Override
    public List<TCreepersHlDetail> findByRptNo(String rptNo) {
        return creepersHlDetailDao.queryByRptNo(rptNo);
    }
    
    @Override
    public Map<String, Object> findByRptNoForMap(String rptNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersHlDetail> entityList = creepersHlDetailDao.queryByRptNo(rptNo);
        List<CreepersHlDetailDTO> dtoList = BeanMapper.mapList(entityList, CreepersHlDetailDTO.class);
        if ((!CreepersConstant.TableNamesCreditReference.T_CREEPERS_HL_DETAIL.isList()) && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_HL_DETAIL.getMapKey(), dtoList.get(0));
        } else
            if (CreepersConstant.TableNamesCreditReference.T_CREEPERS_HL_DETAIL.isList() && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_HL_DETAIL.getMapKey(), dtoList);
        } else {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_HL_DETAIL.getMapKey(), null);
        }
        return result;
    }
}
