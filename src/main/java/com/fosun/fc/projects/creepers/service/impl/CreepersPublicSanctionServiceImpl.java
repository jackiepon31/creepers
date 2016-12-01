package com.fosun.fc.projects.creepers.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersPublicSanctionDao;
import com.fosun.fc.projects.creepers.dto.CreepersPublicSanctionDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicSanction;
import com.fosun.fc.projects.creepers.service.ICreepersPublicSanctionService;

/**
 * <p>
 * description:
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
@Service
public class CreepersPublicSanctionServiceImpl implements ICreepersPublicSanctionService {

    @Autowired
    private CreepersPublicSanctionDao creepersPublicSanctionDao;
    @Override
    public void saveTCreepersPublicSanction(TCreepersPublicSanction entity) {
        creepersPublicSanctionDao.save(entity);
    }

    @Override
    public List<TCreepersPublicSanction> queryAllPublicSanction() {
        return creepersPublicSanctionDao.findAll();
    }

    @Override
    public List<TCreepersPublicSanction> findByRptNo(String rptNo) {
        return creepersPublicSanctionDao.queryByRptNo(rptNo);
    }
    
    @Override
    public Map<String, Object> findByRptNoForMap(String rptNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersPublicSanction> entityList = creepersPublicSanctionDao.queryByRptNo(rptNo);
        List<CreepersPublicSanctionDTO> dtoList = BeanMapper.mapList(entityList, CreepersPublicSanctionDTO.class);
        if ((!CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_SANCTION.isList()) && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_SANCTION.getMapKey(), dtoList.get(0));
        } else
            if (CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_SANCTION.isList() && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_SANCTION.getMapKey(), dtoList);
        } else {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_SANCTION.getMapKey(), null);
        }
        return result;
    }
}
