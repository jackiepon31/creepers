package com.fosun.fc.projects.creepers.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersPublicCivilDao;
import com.fosun.fc.projects.creepers.dto.CreepersPublicCivilDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicCivil;
import com.fosun.fc.projects.creepers.service.ICreepersPublicCivilService;

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
public class CreepersPublicCivilServiceImpl implements ICreepersPublicCivilService {

    @Autowired
    private CreepersPublicCivilDao creepersPublicCivilDao;

    @Override
    public void saveCreepersPublicCivilDTO(TCreepersPublicCivil entity) {
        creepersPublicCivilDao.save(entity);
    }

    @Override
    public List<TCreepersPublicCivil> queryAllCompensatory() {
        return creepersPublicCivilDao.findAll();
    }

    @Override
    public List<TCreepersPublicCivil> findByRptNo(String rptNo) {
        return creepersPublicCivilDao.queryByRptNo(rptNo);
    }

    @Override
    public Map<String, Object> findByRptNoForMap(String rptNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersPublicCivil> entityList = creepersPublicCivilDao.queryByRptNo(rptNo);
        List<CreepersPublicCivilDTO> dtoList = BeanMapper.mapList(entityList, CreepersPublicCivilDTO.class);
        if ((!CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_CIVIL.isList())
                && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_CIVIL.getMapKey(), dtoList.get(0));
        } else if (CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_CIVIL.isList()
                && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_CIVIL.getMapKey(), dtoList);
        } else {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_CIVIL.getMapKey(), null);
        }
        return result;
    }
}
