package com.fosun.fc.projects.creepers.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.dao.CreepersJobDao;
import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersJob;
import com.fosun.fc.projects.creepers.schedule.QuartzJobFactory;
import com.fosun.fc.projects.creepers.schedule.QuartzJobFactoryDisallowConcurrentExecution;
import com.fosun.fc.projects.creepers.service.ICreepersJobService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.fosun.fc.projects.creepers.utils.JobUtils;
/**      
 * Job服务实现类      
 * @author pengyk     
 * @since  2016-10-13 
 */  
@Component
@Transactional
public class CreepersJobServiceImpl implements ICreepersJobService{
	Logger logger = LoggerFactory.getLogger(this.getClass());     
	    @Autowired 
	    private SchedulerFactoryBean schedulerFactoryBean;        
	     
	    @Autowired       
	    private CreepersJobDao creepersJobDao;
	    /* (non-Javadoc)
		 * @see com.fosun.fc.projects.creepers.service.impl.ICreepersJobService1#getJob(java.lang.String, java.lang.String)
		 */      
	    @Override
		public CreepersJobDTO getJob(String jobName,String jobGroup) throws SchedulerException{      
	        CreepersJobDTO job =  new CreepersJobDTO(); 
	        TCreepersJob entity = creepersJobDao.findTop1ByJobName(jobName);
	        Scheduler scheduler = schedulerFactoryBean.getScheduler();      
	        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);      
	        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);        
	        if (null != trigger) {      
	            entity.setNextTime(trigger.getNextFireTime()); //下次触发时间      
	            entity.setPreviousTime(trigger.getPreviousFireTime());//上次触发时间      
	            entity.setStartTime(trigger.getStartTime());
	            if (trigger instanceof CronTrigger) {      
	                CronTrigger cronTrigger = (CronTrigger) trigger;      
	                String cronExpression = cronTrigger.getCronExpression();      
	                job.setCronExpression(cronExpression);      
	            }      
	        }     
	        job = BeanMapper.map(entity, CreepersJobDTO.class);
	        return job;      
	    }
	    
	    
	    /**
	     * <p>
	     * 任务List分页查询
	     * </p>
	     * 
	     * @param searchParams,pageNumber,pageSize,sortType,taskType
	     * @author pengyk
	     * @see
	     */
	    @Override
	    @SuppressWarnings({ "unchecked" })
	    public Page<?> findList(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType) throws Exception {
	        Sort sort = null;
			if ("auto".equals(sortType)) {
				sort = new Sort(Direction.DESC, "id");
			}
			PageRequest pageable = new PageRequest(pageNumber - 1, pageSize, sort);
            Specification<TCreepersJob> spec = (Specification<TCreepersJob>) CommonMethodUtils
                    .buildSpecification(searchParams, "Dt");
            Page<TCreepersJob> entityPage = creepersJobDao.findAll(spec, pageable);
            List<TCreepersJob> list = entityPage.getContent();
            List<CreepersJobDTO> dtoList = new ArrayList<CreepersJobDTO>();
            dtoList = BeanMapper.mapList(list, CreepersJobDTO.class);
            if(dtoList!=null && !dtoList.isEmpty()){
            	for (CreepersJobDTO creepersJobDTO : dtoList) {
            		CreepersJobDTO jobInner = getJob(creepersJobDTO.getJobName(),creepersJobDTO.getJobGroup());
            		creepersJobDTO.setStartTime(jobInner.getStartTime());//启动时间
            		creepersJobDTO.setNextTime(jobInner.getNextTime()); //下次触发时间      
            		creepersJobDTO.setPreviousTime(jobInner.getPreviousTime());//上次触发时间
				}
            	
            }
            Page<?> dtoPage = new PageImpl<CreepersJobDTO>(dtoList, pageable, entityPage.getTotalElements());
            return dtoPage;
	    }
	    
	    /* (non-Javadoc)
		 * @see com.fosun.fc.projects.creepers.service.impl.ICreepersJobService1#getAllJobs()
		 */      
	    @Override
		public List<CreepersJobDTO> getAllJobs() throws SchedulerException{      
	        Scheduler scheduler = schedulerFactoryBean.getScheduler();        
	        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();      
	        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);      
	        List<CreepersJobDTO> jobList = new ArrayList<CreepersJobDTO>();      
	        for (JobKey jobKey : jobKeys) {      
	            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);      
	            for (Trigger trigger : triggers) {      
	                CreepersJobDTO job = new CreepersJobDTO();      
	                job.setJobName(jobKey.getName());      
	                job.setJobGroup(jobKey.getGroup());      
	                job.setDescription("触发器:" + trigger.getKey());      
	                      
	                job.setNextTime(trigger.getNextFireTime()); //下次触发时间                 
//	              trigger.getFinalFireTime();//最后一次执行时间      
	                job.setPreviousTime(trigger.getPreviousFireTime());//上次触发时间      
//	              trigger.getStartTime();//开始时间      
//	              trigger.getEndTime();//结束时间                   
	                //触发器当前状态      
	                TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());      
	                job.setStatus(triggerState.name());      
	                //      
	                if (trigger instanceof CronTrigger) {      
	                    CronTrigger cronTrigger = (CronTrigger) trigger;      
	                    String cronExpression = cronTrigger.getCronExpression();      
	                    job.setCronExpression(cronExpression);      
	                }      
	                jobList.add(job);      
	            }      
	        }      
	        return jobList;      
	    }      
	          
	    /* (non-Javadoc)
		 * @see com.fosun.fc.projects.creepers.service.impl.ICreepersJobService1#getRunningJob()
		 */      
	    @Override
		public List<CreepersJobDTO> getRunningJob() throws SchedulerException {      
	        Scheduler scheduler = schedulerFactoryBean.getScheduler();      
	        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();      
	        List<CreepersJobDTO> jobList = new ArrayList<CreepersJobDTO>(executingJobs.size());      
	        for (JobExecutionContext executingJob : executingJobs) {      
	            CreepersJobDTO job = new CreepersJobDTO();      
	            JobDetail jobDetail = executingJob.getJobDetail();      
	            JobKey jobKey = jobDetail.getKey();      
	            Trigger trigger = executingJob.getTrigger();      
	            job.setJobName(jobKey.getName());      
	            job.setJobGroup(jobKey.getGroup());      
	            job.setDescription("触发器:" + trigger.getKey());      
	            TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());      
	            job.setStatus(triggerState.name());      
	            if (trigger instanceof CronTrigger) {      
	                CronTrigger cronTrigger = (CronTrigger) trigger;      
	                String cronExpression = cronTrigger.getCronExpression();      
	                job.setCronExpression(cronExpression);      
	            }      
	            jobList.add(job);      
	        }      
	        return jobList;      
	    }      
	          
	    /* (non-Javadoc)
		 * @see com.fosun.fc.projects.creepers.service.impl.ICreepersJobService1#getTaskList()
		 */      
	    @Override
		public List<CreepersJobDTO> getTaskList(){      
	        List<CreepersJobDTO> jobs = new ArrayList<CreepersJobDTO>();      
	        List<TCreepersJob> jobList = creepersJobDao.findAllUseJobs();   
	        jobs = BeanMapper.mapList(jobList, CreepersJobDTO.class);
	        return jobs;      
	    }      
	          
	    /* (non-Javadoc)
		 * @see com.fosun.fc.projects.creepers.service.impl.ICreepersJobService1#addJob(com.fosun.fc.projects.creepers.dto.CreepersJobDTO)
		 */      
	    @Override
		public boolean addJob(CreepersJobDTO job) throws SchedulerException {      
	        if (job == null || !TriggerState.NORMAL.name().equals(job.getStatus())) {      
	            return false;      
	        }      
	        if(!JobUtils.isValidExpression(job.getCronExpression())){      
	            logger.error("时间表达式错误（"+job.getJobName()+","+job.getJobGroup()+"）,"+job.getCronExpression());      
	            return false;      
	        }else{      
	            Scheduler scheduler = schedulerFactoryBean.getScheduler();      
	            // 任务名称和任务组设置规则：    // 名称：task_1 ..    // 组 ：group_1 ..          
	            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());      
	            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);          
	            // 不存在，创建一个       
	            if(trigger != null){
	            	//如果存在JOB的话，就删除已有JOB
	            	deleteJob(job);
	            }
                //是否允许并发执行      
                Class<? extends Job> clazz = CreepersJobDTO.CONCURRENT_IS.equals(job.getIsConcurrent()) ? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;      
                JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();       
                jobDetail.getJobDataMap().put("scheduleJob", job);       
                // 表达式调度构建器           
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());      
                // 按新的表达式构建一个新的trigger           
                trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();       
                scheduler.scheduleJob(jobDetail, trigger);
//	            threadSleep();//junit测试时可开启
                //保存入库
                updateOrSaveJob(job);
//	            } else {
//	            	// trigger已存在，则更新相应的定时设置           
//	                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());           
//	                // 按新的cronExpression表达式重新构建trigger           
//	                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();          
//	                // 按新的trigger重新设置job执行           
//	                scheduler.rescheduleJob(triggerKey, trigger);    
//	                //更新库中的JOB信息
//	                updateOrSaveJob(job);
////	                creepersJobDao.updateCronExpByJobName(job.getCronExpression(), job.getJobName(), job.getJobGroup());
//	            }                   
	        }      
	        return true;      
	    }
//		private void threadSleep() {
//			try {
//				TimeUnit.SECONDS.sleep(60);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}      
	      
	    /* (non-Javadoc)
		 * @see com.fosun.fc.projects.creepers.service.impl.ICreepersJobService1#pauseJob(com.fosun.fc.projects.creepers.dto.CreepersJobDTO)
		 */      
	    @Override
		public boolean pauseJob(CreepersJobDTO scheduleJob){      
	        Scheduler scheduler = schedulerFactoryBean.getScheduler();      
	        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());    
	        try {      
	            scheduler.pauseJob(jobKey);   
	            creepersJobDao.updateStatusByJobName(TriggerState.PAUSED.name(),scheduleJob.getJobName(), scheduleJob.getJobGroup());
	            return true;      
	        } catch (SchedulerException e) {                  
	        }      
	        return false;      
	    }      
	          
	    /* (non-Javadoc)
		 * @see com.fosun.fc.projects.creepers.service.impl.ICreepersJobService1#resumeJob(com.fosun.fc.projects.creepers.dto.CreepersJobDTO)
		 */      
	    @Override
		public boolean resumeJob(CreepersJobDTO scheduleJob){      
	        Scheduler scheduler = schedulerFactoryBean.getScheduler();      
	        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());      
	        try {      
	            scheduler.resumeJob(jobKey);     
	            creepersJobDao.updateStatusByJobName(TriggerState.NORMAL.name(),scheduleJob.getJobName(), scheduleJob.getJobGroup());
	            return true;      
	        } catch (SchedulerException e) {                  
	        }      
	        return false;      
	    }      
	          
	    /* (non-Javadoc)
		 * @see com.fosun.fc.projects.creepers.service.impl.ICreepersJobService1#deleteJob(com.fosun.fc.projects.creepers.dto.CreepersJobDTO)
		 */      
	    @Override
		public boolean deleteJob(CreepersJobDTO scheduleJob){      
	        Scheduler scheduler = schedulerFactoryBean.getScheduler();      
	        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());      
	        try{      
	            scheduler.deleteJob(jobKey);      
	            creepersJobDao.updateStatusByJobName(TriggerState.NONE.name(),scheduleJob.getJobName(), scheduleJob.getJobGroup());
	            return true;      
	        } catch (SchedulerException e) {                  
	        }      
	        return false;      
	    }      
	          
	    /* (non-Javadoc)
		 * @see com.fosun.fc.projects.creepers.service.impl.ICreepersJobService1#testJob(com.fosun.fc.projects.creepers.dto.CreepersJobDTO)
		 */      
	    @Override
		public void testJob(CreepersJobDTO scheduleJob) throws SchedulerException{      
	        Scheduler scheduler = schedulerFactoryBean.getScheduler();      
	        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());      
	        scheduler.triggerJob(jobKey);      
	    }      
	          
	    /* (non-Javadoc)
		 * @see com.fosun.fc.projects.creepers.service.impl.ICreepersJobService1#updateCronExpression(com.fosun.fc.projects.creepers.dto.CreepersJobDTO)
		 */      
	    @Override
		public void updateCronExpression(CreepersJobDTO scheduleJob) throws SchedulerException{      
	        Scheduler scheduler = schedulerFactoryBean.getScheduler();      
	        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());      
	        //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"      
	        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);      
	        //表达式调度构建器      
	        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());      
	        //按新的cronExpression表达式重新构建trigger      
	        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();      
	        //按新的trigger重新设置job执行      
	        scheduler.rescheduleJob(triggerKey, trigger);    
	        creepersJobDao.updateStatusByJobName(TriggerState.NORMAL.name(),scheduleJob.getJobName(), scheduleJob.getJobGroup());
	    } 
	
	    public void updateOrSaveJob(CreepersJobDTO scheduleJob) {
	    	TCreepersJob entity = new TCreepersJob();
	    	entity = BeanMapper.map(scheduleJob, TCreepersJob.class);
	        String jobName = scheduleJob.getJobName();
	        Long count = creepersJobDao.countByJobName(jobName);
	        if (count == 1L) {
	        	TCreepersJob oldEntity = new TCreepersJob();
	        	oldEntity = creepersJobDao.findTop1ByJobName(jobName);
	            entity.setId(oldEntity.getId());
	            entity.setVersion(oldEntity.getVersion());
	        }
	        CommonMethodUtils.setByDT(entity);
	        creepersJobDao.saveAndFlush(entity);
	    }


        @Override
        public CreepersJobDTO findJob(String jobName) {

            TCreepersJob job = creepersJobDao.findTop1ByJobName(jobName);
            CreepersJobDTO jobDTO = new CreepersJobDTO();
            BeanUtils.copyProperties(job, jobDTO);
            return jobDTO;
        }

        
        /**
         * 通过jobname更新request，request字段暂存在memo字段中
         * @param jobName
         * @param request
         */
        @Transactional(propagation = Propagation.REQUIRES_NEW)
        @Override
        public void updateResumeRequestByJobName(String jobName, String request) {
            
            creepersJobDao.updateRequestByJobName(jobName, request);;
        }
        
        
	    
}
