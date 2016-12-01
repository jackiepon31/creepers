package com.fosun.fc.projects.creepers.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersPublicIspDao;
import com.fosun.fc.projects.creepers.dto.CreepersPublicIspDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicIsp;
import com.fosun.fc.projects.creepers.service.ICreepersPublicIspService;

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
public class CreepersPublicIspServiceImpl implements ICreepersPublicIspService {

    @Autowired
    private CreepersPublicIspDao creepersPublicIspDao;

    @Override
    public void saveTCreepersPublicIsp(TCreepersPublicIsp entity) {
        creepersPublicIspDao.save(entity);
    }

    @Override
    public List<TCreepersPublicIsp> queryAllPublicIsp() {
        return creepersPublicIspDao.findAll();
    }

    @Override
    public List<TCreepersPublicIsp> findByRptNo(String rptNo) {
        return creepersPublicIspDao.queryByRptNo(rptNo);
    }

    @Override
    public Map<String, Object> findByRptNoForMap(String rptNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersPublicIsp> entityList = creepersPublicIspDao.queryByRptNo(rptNo);
        List<CreepersPublicIspDTO> dtoList = BeanMapper.mapList(entityList, CreepersPublicIspDTO.class);
        if ((!CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_ISP.isList())
                && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_ISP.getMapKey(), dtoList.get(0));
        } else if (CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_ISP.isList()
                && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_ISP.getMapKey(), dtoList);
        } else {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_ISP.getMapKey(), null);
        }
        return result;
    }
}
