package com.fosun.fc.projects.creepers.web.rest;

import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.service.ICreepersListService;

public class CreepersBaseRestController {
    @Autowired
    protected ICreepersListService creepersListServiceImpl;
}
