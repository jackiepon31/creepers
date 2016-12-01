package com.fosun.fc.projects.creepers.redis.service.Impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.redis.service.IRedisSubService;
import com.fosun.fc.projects.creepers.service.ICreepersCourtCorpBondsService;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;

public class CourtCorpBondsSubServiceImpl implements IRedisSubService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersCourtCorpBondsService creepersCourtCorpBondsServiceImpl;

    @Autowired
    private ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;

    @Override
    public void handleMessage(Serializable message) {

        logger.info("=============>CourtCropBondsSubServiceImpl.handleMessage start!");
        // 接收消息
        String jobName = message.toString();
        // 增加异常处理
        try {
            creepersCourtCorpBondsServiceImpl.processByJob(jobName);
            logger.info("=============>CourtCropBondsSubServiceImpl.handmleMessage end!");
        } catch (Exception e) {
            e.printStackTrace();
            // 异常处理
            CreepersParamDTO paramDTO = new CreepersParamDTO();
            paramDTO.putSearchKeyWord(jobName);
            paramDTO.setTaskType(BaseConstant.TaskListType.COURT_CORP_BONDS_LIST.getValue());
            paramDTO.setErrorPath(e.getCause().getClass() + e.getCause().getMessage());
            paramDTO.setErrorInfo(e.getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(paramDTO);
            logger.error("=============>CourtCropBondsSubServiceImpl.handleMessage end!");
        }

    }

}
