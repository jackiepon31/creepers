package com.fosun.fc.projects.creepers.pageprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;

/**
 * 
 * <p>
 * 所有存在CreepersList的网站爬取，都需要继承这个基础类 提供公共方法进行异常统一处理及日志格式化打印
 * </p>
 * 
 * @author MaXin
 * @since 2016年8月15日16:40:05
 * @see
 */

public class BaseCreepersListProcessor {

    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    protected ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;
}
