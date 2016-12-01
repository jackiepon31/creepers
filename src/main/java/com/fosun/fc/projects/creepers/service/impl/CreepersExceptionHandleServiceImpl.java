/**
 * <p>
 * Copyright(c) @2016 Fortune Credit Management Co., Ltd.
 * </p>
 */
package com.fosun.fc.projects.creepers.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.service.ICreepersErrorListService;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.service.ICreepersJobService;
import com.fosun.fc.projects.creepers.service.ICreepersListService;
import com.fosun.fc.projects.creepers.utils.Frequency;
import com.fosun.fc.projects.creepers.utils.LogInfoFormat;

/**
 * <p>
 * description:
 * 
 * 爬虫异常处理公共服务
 * 
 * 现状：爬取过程中出现的Exception 默认都是只打印log 不抛出异常
 * 
 * 针对这种情况，需要做到：
 * 
 * 1.爬取任务列表状态更新
 * 
 * 2.errorList中记录错误信息
 * 
 * 3.打印固定格式的Log日志，方便后期通过日志抓取异常，统一处理。
 * </p>
 * 
 * @author MaXin
 * @since 2016年8月12日16:01:28
 * @see
 */
@Component
public class CreepersExceptionHandleServiceImpl implements ICreepersExceptionHandleService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersListService creepersListServiceImpl;

    @Autowired
    private ICreepersErrorListService creepersErrorListServiceImpl;

    @Autowired
    private ICreepersJobService creepersJobServiceImpl;

    @Autowired
    private Frequency frequency;

    /**
     * update or insert 错误信息记录表内容 打印日志 打印日志
     */
    @Override
    public void insertOrUpdateErrorList(CreepersParamDTO param) {
        // 1.insert into errorList
        if (StringUtils.isNotBlank(param.getSearchKeyWordForString())) {
            creepersErrorListServiceImpl.saveError(param.getSearchKeyWordForString(), param.getErrorInfo(),
                    param.getTaskType());
        }
        // 2.print log
        logger.error(LogInfoFormat.errorLog(param));
    }

    /**
     * update or insert 错误信息记录表内容 打印日志 打印日志
     */
    @Override
    public void insertOrUpdateErrorList(CreepersLoginParamDTO param) {
        // 1.insert into errorList
        if (StringUtils.isNotBlank(param.getLoginName())) {
            creepersErrorListServiceImpl.saveError(param.getLoginName(), param.getErrorInfo(), param.getTaskType());
        }
        // 2.print log
        logger.error(LogInfoFormat.errorLog(param));
    }

    /**
     * 
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void handleExceptionAndPrintLog(CreepersParamDTO param) {
        param.setTaskStatus(BaseConstant.TaskListStatus.FALSE.getValue());
        // 1.update list status
        creepersListServiceImpl.updateList(param);
        // 2.insert into errorList
        this.insertOrUpdateErrorList(param);

    }

    /**
     * 
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void handleExceptionAndPrintLog(CreepersLoginParamDTO param) {
        param.setTaskStatus(BaseConstant.TaskListStatus.FALSE.getValue());
        // 1.update list status
        creepersListServiceImpl.updateList(param);
        // 2.insert into errorList
        this.insertOrUpdateErrorList(param);

    }

    /**
     * 
     */
    @SuppressWarnings("rawtypes")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void handleJobExceptionAndPrintLog(CreepersParamDTO param) {
        param.setTaskStatus(BaseConstant.TaskListStatus.FALSE.getValue());
        // 1、update job status
        creepersJobServiceImpl.updateResumeRequestByJobName(param.getSearchKeyWordForString(),
                param.getBreakDownRequest());
        // 2、insert into errorList
        this.insertOrUpdateErrorList(param);
        // 3、send mail
        Class[] classes = {String.class,String[].class,String[].class,String.class};
        String sourceMail = "zx.service@fosun.com";
        String[] targetMails = new String[] { "lizhanping@fosun.com" };
        String[] ccMails = new String[] { "pengyk@fosun.com", "maxin@fosun.com", "158869557@qq.com" };
        String content = "各位爬虫组的同事们：\n" + "      爬虫服务器出现bug，请尽快处理问题，以下是详细信息：\n" + "错误路径：" + param.getErrorPath() + "\n"
                + "错误信息：" + param.getErrorInfo() + "\n" + "请求信息：" + param.getBreakDownRequest();
        Object[] parameterValues = {sourceMail,targetMails,ccMails,content};
        frequency.frequency("com.fosun.fc.projects.creepers.service.impl.SimpleMailServiceImpl", "sendNotificationMail", classes, parameterValues, "mail.maxTimeInterval", "mail.maxTimes", param.getTaskType(), param.getErrorInfo());
    }
}
