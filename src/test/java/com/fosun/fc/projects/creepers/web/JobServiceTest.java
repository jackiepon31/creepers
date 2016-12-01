package com.fosun.fc.projects.creepers.web;

import java.util.Date;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.fosun.fc.modules.test.category.UnStable;
import com.fosun.fc.modules.test.spring.SpringTransactionalTestCase;
import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
import com.fosun.fc.projects.creepers.service.impl.CreepersJobServiceImpl;

@Category(UnStable.class)
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml","/schedule/applicationContext-spring-scheduler.xml"})
@TransactionConfiguration(defaultRollback = false)
public class JobServiceTest extends SpringTransactionalTestCase {


    @Autowired(required = true)
    private CreepersJobServiceImpl creepersJobService;

    @Test
    public void testAdd() throws SchedulerException {
    	CreepersJobDTO dto = new CreepersJobDTO();
    	dto.setCreatedBy("admin");
    	dto.setCreatedDt(new Date());
    	dto.setUpdatedBy("admin");
    	dto.setUpdatedDt(new Date());
    	dto.setCronExpression("0/10 * * * * ?");
    	dto.setFlag("0");
    	dto.setIsConcurrent("0");
    	dto.setStatus("NORMAL");
    	dto.setJobClass("com.fosun.fc.projects.creepers.utils.JobUtils");
    	dto.setJobGroup("test");
    	dto.setJobName("test002");
    	dto.setMethodName("run");
    	dto.setStatus("NORMAL");
    	creepersJobService.addJob(dto);
    }
    @Test
    public void testRemove() {/*
    	UserDTO dto = new UserDTO();
    	dto.setUserName("samuel");
    	dto.setBirthday(new Date());
    	dto.setEmail("jackiepon@163.com");
    	dto.setSex("M");
    	dto.setCreatedBy("admin");
    	dto.setCreatedDt(new Date());
    	dto.setUpdatedBy("admin");
    	dto.setUpdatedDt(new Date());
    	redisCacheTemplate.opsForSet().remove("samuel", dto);
    	UserDTO dto2 = redisCacheTemplate.opsForSet().pop(dto.getUserName());
    	System.out.println("=========1========="+dto2.getEmail());  		
    */}

}
