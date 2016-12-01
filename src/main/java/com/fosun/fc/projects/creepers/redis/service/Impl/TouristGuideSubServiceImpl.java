package com.fosun.fc.projects.creepers.redis.service.Impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersListTourGuideDao;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersListTourGuide;
import com.fosun.fc.projects.creepers.redis.service.IRedisSubService;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.service.ICreepersTouristGuideService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

public class TouristGuideSubServiceImpl implements IRedisSubService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersTouristGuideService creepersTouristGuideServiceImpl;

    @Autowired
    private ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;

    @Autowired
    private CreepersListTourGuideDao creepersListTourGuideDao;

    @Override
    public void handleMessage(Serializable message) {

        logger.info("=============>TouristGuideSubServiceImpl.handleMessage start!");
        // 接收消息
        CreepersLoginParamDTO param = JSONObject.parseObject(message.toString(), CreepersLoginParamDTO.class);
        // 增加异常处理
        try {
            TCreepersListTourGuide entity = creepersListTourGuideDao.findTop1ByGuideNoOrCardNo(
                    param.getSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.GUIDE_NO.getValue()),
                    param.getSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.CARD_NO.getValue()));
            if (entity == null) {
                entity = new TCreepersListTourGuide();
                entity.setGuideNo(param.getSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.GUIDE_NO.getValue()));
                entity.setCardNo(param.getSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.CARD_NO.getValue()));
                entity.setCertNo(param.getSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.CERT_NO.getValue()));
                entity.setFlag(BaseConstant.TaskListStatus.DEFAULT.getValue());
                CommonMethodUtils.setByDT(entity);
                creepersListTourGuideDao.saveAndFlush(entity);
            }
            creepersTouristGuideServiceImpl.processByParam(param);
            logger.info("=============>TouristGuideSubServiceImpl.handmleMessage end!");
        } catch (Exception e) {
            e.printStackTrace();
            // 异常处理
            param.setTaskType(BaseConstant.TaskListType.TOUR_GUIDE_LIST.getValue());
            param.setErrorPath(e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorInfo(e.getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            logger.error("=============>TouristGuideSubServiceImpl.handleMessage end!");
        }

    }

}
