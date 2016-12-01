package com.fosun.fc.projects.creepers.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
import com.fosun.fc.projects.creepers.service.ICreepersListService;

/**      
 * Job实现类  无状态      
 * 若此方法上一次还未执行完，而下一次执行时间轮到时则该方法也可并发执行      
 * @author pengyk      
 */       
public class QuartzJobFactory implements Job {       
       
	Logger logger = LoggerFactory.getLogger(this.getClass());      
    
	@Autowired
    private ICreepersListService creepersListServiceImpl;
	
    @Override       
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 使得job对象可以通过注解实现依赖注入
        CreepersJobDTO scheduleJob = (CreepersJobDTO)context.getMergedJobDataMap().get("scheduleJob");       
        logger.info("运行任务组名称 = [" + scheduleJob.getJobGroup() + "]");
        logger.info("运行任务名称 = [" + scheduleJob.getJobName() + "]");       
        creepersListServiceImpl.addTaskByMerName(scheduleJob.getJobGroup(), scheduleJob.getJobName());       
    }       
           
}  