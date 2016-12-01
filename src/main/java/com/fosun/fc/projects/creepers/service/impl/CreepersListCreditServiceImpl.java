package com.fosun.fc.projects.creepers.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersListCreditDao;
import com.fosun.fc.projects.creepers.dto.CreepersListCreditDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersListCredit;
import com.fosun.fc.projects.creepers.service.ICreepersListCreditService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

/**
 * 
 * <p>
 * description: 人行个人征信报告--任务列表
 * </p>
 * 
 * @author MaXin
 * @since 2016年10月19日
 * @see
 */
@Service("creepersListCreditServiceImpl")
public class CreepersListCreditServiceImpl implements ICreepersListCreditService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CreepersListCreditDao creepersListCreditDao;

    @Override
    public Map<String, Object> findByUserCodeAndMessageCode(String loginName, String messageCaptchaValue) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersListCredit> entityList = creepersListCreditDao.findTop1ByUserCodeAndMessageCode(loginName,
                messageCaptchaValue);
        List<CreepersListCreditDTO> dtoList = BeanMapper.mapList(entityList, CreepersListCreditDTO.class);
        result.put(CreepersConstant.TableNamesListName.T_CREEPERS_LIST_CREDIT.getMapKey(), dtoList.get(0));
        return result;
    }

    /*
     * @see com.fosun.fc.projects.creepers.service.ICreepersListCreditService#
     * updateImagePath(java.lang.String, java.lang.String)
     */
    @Override
    public void updateImagePath(String loginName, String imagePath) {
        TCreepersListCredit entity = creepersListCreditDao.findTop1ByUserCode(loginName);
        entity.setImageUrl(imagePath);
        CommonMethodUtils.setByDT(entity);
        creepersListCreditDao.saveAndFlush(entity);
    }

    /*
     * @see com.fosun.fc.projects.creepers.service.ICreepersListCreditService#
     * updateHtmlFilePath(java.lang.String, java.lang.String)
     */
    @Override
    public void updateHtmlFilePath(String loginName, String filePath) {
        TCreepersListCredit entity = creepersListCreditDao.findTop1ByUserCode(loginName);
        entity.setHtmlUrl(filePath);
        CommonMethodUtils.setByDT(entity);
        creepersListCreditDao.saveAndFlush(entity);
    }

    /*
     * @see com.fosun.fc.projects.creepers.service.ICreepersListCreditService#
     * updateImageAndHtmlPath(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public void updateImageAndHtmlPath(String loginName, String imagePath, String filePath) {
        TCreepersListCredit entity = creepersListCreditDao.findTop1ByUserCode(loginName);
        entity.setHtmlUrl(filePath);
        entity.setImageUrl(imagePath);
        CommonMethodUtils.setByDT(entity);
        creepersListCreditDao.saveAndFlush(entity);
    }

    /*
     * @see com.fosun.fc.projects.creepers.service.ICreepersListCreditService#
     * updateTaskListStatusSucceed(java.lang.String)
     */
    @Override
    public void updateTaskListStatusSucceed(String loginName) {
        TCreepersListCredit entity = creepersListCreditDao.findTop1ByUserCode(loginName);
        entity.setFlag(BaseConstant.TaskListStatus.SUCCEED.getValue());
        CommonMethodUtils.setByDT(entity);
        creepersListCreditDao.saveAndFlush(entity);

    }

    @Override
    public void updateTaskListStatus(String loginName, String status) {
        TCreepersListCredit entity = creepersListCreditDao.findTop1ByUserCode(loginName);
        entity.setFlag(status);
        CommonMethodUtils.setByDT(entity);
        creepersListCreditDao.saveAndFlush(entity);

    }
}
