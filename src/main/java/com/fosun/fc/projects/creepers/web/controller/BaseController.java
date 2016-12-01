package com.fosun.fc.projects.creepers.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.service.ICreepersErrorListService;
import com.fosun.fc.projects.creepers.service.ICreepersListService;

/**
 * 
 * <p>
 * description: 公共controller基础类 用于注入通用的服务
 * </p>
 * 
 * @author MaXin
 * @since 2016-11-7 21:12:55
 * @see
 */
public class BaseController extends com.fosun.fc.modules.web.controller.BaseController {

    @Autowired
    protected ICreepersErrorListService creepersErrorListServiceImpl;

    @Autowired
    protected ICreepersListService creepersListServiceImpl;

}
