package com.fosun.fc.projects.creepers.redis.service.Impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersListCreditDao;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersListCredit;
import com.fosun.fc.projects.creepers.redis.service.IRedisSubService;
import com.fosun.fc.projects.creepers.service.ICreepersCreditReferenceService;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

public class CreditReferenceSubServiceImpl implements IRedisSubService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CreepersListCreditDao creepersListCreditDao;
    
    @Autowired
    private ICreepersCreditReferenceService creepersCreditReferenceServiceImpl;
    
    @Autowired
    private ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;

    @Override
    public void handleMessage(Serializable message) {

        logger.info("=============>CreditReferenceSubServiceImpl.handleMessage start!");
        // 接收消息
        CreepersLoginParamDTO param = JSONObject.parseObject(message.toString(),CreepersLoginParamDTO.class);
        
        // 增加异常处理
        try {
            // 查询任务List，结果为空或状态不为1则启动爬寻
            List<TCreepersListCredit> taskList =  creepersListCreditDao.findByUserCode(param.getLoginName());
            if (CommonMethodUtils.isEmpty(taskList)) {
                TCreepersListCredit task = new TCreepersListCredit();
                task.setUserCode(param.getLoginName());
                task.setPassword(param.getPassword());
                task.setMessageCode(param.getMessageCaptchaValue());
                task.setFlag(BaseConstant.TaskListStatus.DEFAULT.getValue());
                CommonMethodUtils.setByDT(task);
                creepersListCreditDao.save(task);
            }
            creepersCreditReferenceServiceImpl.processByParam(param);
            logger.info("=============>CreditReferenceSubServiceImpl.handleMessage end!");
        } catch (Exception e) {
            e.printStackTrace();
            // 异常处理
            param.putSearchKeyWord(param.getLoginName());
            param.setTaskType(BaseConstant.TaskListType.CREDIT_REFERENCE_LIST.getValue());
            param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
            param.setErrorPath(e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorInfo(e.getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            logger.error("=============>CreditReferenceSubServiceImpl.handleMessage end!");
        }

    }

}
