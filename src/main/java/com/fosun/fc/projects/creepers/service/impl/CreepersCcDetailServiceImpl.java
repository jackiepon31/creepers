package com.fosun.fc.projects.creepers.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersCcDetailDao;
import com.fosun.fc.projects.creepers.dto.CreepersCcDetailDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersCcDetail;
import com.fosun.fc.projects.creepers.service.ICreepersCcDetailService;

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
public class CreepersCcDetailServiceImpl implements ICreepersCcDetailService {

    @Autowired
    private CreepersCcDetailDao creepersCcDetailDao;

    @Override
    public void saveTCreepersCcDetail(TCreepersCcDetail entity) {
        creepersCcDetailDao.save(entity);
    }

    @Override
    public List<TCreepersCcDetail> queryAllCcDetail() {
        return creepersCcDetailDao.findAll();
    }

    @Override
    public List<TCreepersCcDetail> findByRptNo(String rptNo) {
        return creepersCcDetailDao.queryByRptNo(rptNo);
    }

    @Override
    public Map<String, Object> findByRptNoForMap(String rptNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersCcDetail> entityList = creepersCcDetailDao.queryByRptNo(rptNo);
        List<CreepersCcDetailDTO> dtoList = BeanMapper.mapList(entityList, CreepersCcDetailDTO.class);
        if ((!CreepersConstant.TableNamesCreditReference.T_CREEPERS_CC_DETAIL.isList()) && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_CC_DETAIL.getMapKey(), dtoList.get(0));
        } else if (CreepersConstant.TableNamesCreditReference.T_CREEPERS_CC_DETAIL.isList() && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_CC_DETAIL.getMapKey(), dtoList);
        } else {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_CC_DETAIL.getMapKey(), null);
        }
        return result;
    }
}
