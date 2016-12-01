/**
 * <p>
 * Copyright(c) @2016 Fortune Credit Management Co., Ltd.
 * </p>
 */
package com.fosun.fc.projects.creepers.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.modules.constant.GlobalConstant;
import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersAccountBakDao;
import com.fosun.fc.projects.creepers.dto.CreepersAccountBakDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersAccountBak;
import com.fosun.fc.projects.creepers.service.ICreepersAccountBakService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

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
public class CreepersAccountBakServiceImpl implements ICreepersAccountBakService {

    @Autowired
    private CreepersAccountBakDao creepersAccountBakDao;

    @Override
    @SuppressWarnings("unchecked")
    public Page<CreepersAccountBakDTO> geTCreepersAccountBakList(Map<String, Object> searchParams, int pageNumber,
            int pageSize, String sortType) {
        PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
        searchParams.put("EQ_flag", GlobalConstant.LOGIC_DELETE_FLAG__NO_DELETE);
        Specification<TCreepersAccountBak> spec = (Specification<TCreepersAccountBak>) buildSpecification(searchParams);
        Page<TCreepersAccountBak> TCreepersAccountBakPage = creepersAccountBakDao.findAll(spec, pageable);
        List<TCreepersAccountBak> TCreepersAccountBakList = TCreepersAccountBakPage.getContent();
        List<CreepersAccountBakDTO> TCreepersAccountBakDTOList = new ArrayList<CreepersAccountBakDTO>();
        TCreepersAccountBakDTOList = BeanMapper.mapList(TCreepersAccountBakList, CreepersAccountBakDTO.class);
        Page<CreepersAccountBakDTO> TCreepersAccountBakDTOPage = new PageImpl<CreepersAccountBakDTO>(
                new ArrayList<CreepersAccountBakDTO>(TCreepersAccountBakDTOList));
        return TCreepersAccountBakDTOPage;
    }

    @Override
    public void saveTCreepersAccountBak(TCreepersAccountBak entity) {
        creepersAccountBakDao.save(entity);

    }

    @Override
    public void updateTCreepersAccountBak(TCreepersAccountBak entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<TCreepersAccountBak> queryAllCreepersAccount() {

        return creepersAccountBakDao.findAll();
    }

    @Override
    public List<TCreepersAccountBak> findByRptNo(String rptNo) {
        return creepersAccountBakDao.queryByRptNo(rptNo);
    }

    @Override
    public List<TCreepersAccountBak> findByUsr(String usr) {

        return creepersAccountBakDao.queryByUsr(usr);
    }

    @Override
    public List<TCreepersAccountBak> findByStatus(String status) {
        return creepersAccountBakDao.queryByStatus(status);
    }

    @Override
    public Map<String, Object> findByRptNoForMap(String rptNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersAccountBak> entityList = creepersAccountBakDao.queryByRptNo(rptNo);
        List<CreepersAccountBakDTO> dtoList = BeanMapper.mapList(entityList, CreepersAccountBakDTO.class);
        if ((!CreepersConstant.TableNamesCreditReference.T_CREEPERS_ACCOUNT_BAK.isList()) && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_ACCOUNT_BAK.getMapKey(), dtoList.get(0));
        } else if (CreepersConstant.TableNamesCreditReference.T_CREEPERS_ACCOUNT_BAK.isList() && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_ACCOUNT_BAK.getMapKey(), dtoList);
        } else {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_ACCOUNT_BAK.getMapKey(), null);
        }
        return result;
    }

    @Override
    public void updateStatusByRptNo(String rptNo) {
        creepersAccountBakDao.updateStatusByRptNo(rptNo);
        
    }

    @Override
    public TCreepersAccountBak findTop1ByUsrAndCde(String usr, String cde) {
        return creepersAccountBakDao.findTop1ByUsrAndCde(usr, cde);
    }

    /* 
     * @see com.fosun.fc.projects.creepers.service.ICreepersAccountBakService#saveOrUpdateAccountBak(com.fosun.fc.projects.creepers.entity.TCreepersAccountBak)
     */
    @Override
    public void saveOrIfNotAccountBak(TCreepersAccountBak entity) {
        List<TCreepersAccountBak> entityList = creepersAccountBakDao.queryByRptNo(entity.getRptNo());
        if (CommonMethodUtils.isEmpty(entityList)) {
            creepersAccountBakDao.save(entity);
        }
        
    }

}
