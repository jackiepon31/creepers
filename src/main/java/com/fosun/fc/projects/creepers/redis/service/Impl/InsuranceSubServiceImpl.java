package com.fosun.fc.projects.creepers.redis.service.Impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersListInsuranceDao;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersListInsurance;
import com.fosun.fc.projects.creepers.redis.service.IRedisSubService;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.service.ICreepersInsuranceService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

public class InsuranceSubServiceImpl implements IRedisSubService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CreepersListInsuranceDao creepersListInsuranceDao;

    @Autowired
    private ICreepersInsuranceService creepersInsuranceServiceImpl;

    @Autowired
    private ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;

    @Override
    public void handleMessage(Serializable message) {

        logger.info("=============>InsuranceSubServiceImpl.handleMessage start!");
        // 接收消息
        CreepersLoginParamDTO param = JSONObject.parseObject(message.toString(),CreepersLoginParamDTO.class);
        
        // 增加异常处理
        try {
            // 查询任务List，结果为空或状态不为1则启动爬寻
            List<TCreepersListInsurance> taskList = creepersListInsuranceDao.queryListByUserCode(param.getLoginName());
            if (CommonMethodUtils.isEmpty(taskList)) {
                TCreepersListInsurance task = new TCreepersListInsurance();
                task.setUserCode(param.getLoginName());
                task.setPassword(param.getPassword());
                task.setFlag(BaseConstant.TaskListStatus.DEFAULT.getValue());
                CommonMethodUtils.setByDT(task);
                creepersListInsuranceDao.save(task);
            }
            creepersInsuranceServiceImpl.processByParam(param);
            logger.info("=============>InsuranceSubServiceImpl.handleMessage end!");
        } catch (Exception e) {
            e.printStackTrace();
            // 异常处理
            param.putSearchKeyWord(param.getLoginName());
            param.setTaskType(BaseConstant.TaskListType.INSURANCE_LIST.getValue());
            param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
            param.setErrorPath(e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorInfo(e.getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            logger.error("=============>InsuranceSubServiceImpl.handleMessage end!");
        }

    }

}
