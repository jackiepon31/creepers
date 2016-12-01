package com.fosun.fc.projects.creepers.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersAssetHandleDao;
import com.fosun.fc.projects.creepers.dto.CreepersAssetHandleDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersAssetHandle;
import com.fosun.fc.projects.creepers.service.ICreepersAssetHandleService;

/**
 * 
 * <p>
 * description:
 * </p>
 * 
 * @author Maxin
 * @since 2016年5月26日
 * @see
 */
@Component
@Transactional
public class CreepersAssetHandleServiceImpl implements ICreepersAssetHandleService {

    @Autowired
    private CreepersAssetHandleDao creepersAssetHandleDao;

    @Override
    public void saveTCreepersAssetHandle(TCreepersAssetHandle entity) {
        creepersAssetHandleDao.save(entity);

    }

    @Override
    public List<TCreepersAssetHandle> queryAllAssetHandle() {
        return creepersAssetHandleDao.findAll();
    }

    @Override
    public List<TCreepersAssetHandle> findByRptNo(String rptNo) {
        return creepersAssetHandleDao.queryByRptNo(rptNo);
    }

    @Override
    public Map<String, Object> findByRptNoForMap(String rptNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersAssetHandle> entityList = creepersAssetHandleDao.queryByRptNo(rptNo);
        List<CreepersAssetHandleDTO> dtoList = BeanMapper.mapList(entityList, CreepersAssetHandleDTO.class);
        if ((!CreepersConstant.TableNamesCreditReference.T_CREEPERS_ASSET_HANDLE.isList()) && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_ASSET_HANDLE.getMapKey(), dtoList.get(0));
        } else
            if (CreepersConstant.TableNamesCreditReference.T_CREEPERS_ASSET_HANDLE.isList() && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_ASSET_HANDLE.getMapKey(), dtoList);
        } else {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_ASSET_HANDLE.getMapKey(), null);
        }
        return result;
    }
}
