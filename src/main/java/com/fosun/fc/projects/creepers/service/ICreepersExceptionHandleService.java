package com.fosun.fc.projects.creepers.service;

import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;

/**
 * 
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
 * @since 2016年8月12日
 * @see
 */
public interface ICreepersExceptionHandleService {

    public void insertOrUpdateErrorList(CreepersParamDTO param);
    public void insertOrUpdateErrorList(CreepersLoginParamDTO param);

    public void handleExceptionAndPrintLog(CreepersParamDTO param);
    public void handleExceptionAndPrintLog(CreepersLoginParamDTO param);
    
    public void handleJobExceptionAndPrintLog(CreepersParamDTO param);
}
