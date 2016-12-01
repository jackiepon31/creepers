package com.fosun.fc.projects.creepers.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
import com.fosun.fc.projects.creepers.schedule.QuartzJobFactoryDisallowConcurrentExecution;

public class JobUtils {
	 private final static Logger logger = LoggerFactory.getLogger(JobUtils.class); 
	 /**      
     * 通过反射调用scheduleJob中定义的方法      
     *       
     * @param scheduleJob      
     */       
    public static void invokMethod(CreepersJobDTO scheduleJob) {       
        Object object = null;       
        Class<?> clazz = null;       
        //springId不为空先按springId查找bean       
        if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {       
//            object = SpringUtils.getBean(scheduleJob.getSpringId());       
        } else if (StringUtils.isNotBlank(scheduleJob.getJobClass())) {//按jobClass查找       
            try {       
                clazz = Class.forName(scheduleJob.getJobClass());       
                object = clazz.newInstance();       
            } catch (Exception e) {       
                e.printStackTrace();       
            }       
        }       
        if (object == null) {       
            logger.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查执行类是否配置正确！！！");       
            return;       
        }       
        clazz = object.getClass();       
        Method method = null;       
        try {       
            method = clazz.getDeclaredMethod(scheduleJob.getMethodName(),new Class[]{String.class});       
        } catch (NoSuchMethodException e) {       
            logger.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查执行类的方法名是否设置错误！！！");       
        } catch (SecurityException e) {       
            e.printStackTrace();               
        }       
        if (method != null) {       
            try {       
                method.invoke(object, new Object[] {scheduleJob.getJobName()});   
            } catch (IllegalAccessException e) {       
                // TODO Auto-generated catch block       
                e.printStackTrace();       
            } catch (IllegalArgumentException e) {       
                // TODO Auto-generated catch block       
                e.printStackTrace();       
            } catch (InvocationTargetException e) {       
                // TODO Auto-generated catch block       
                e.printStackTrace();       
            }       
        }              
    }       
       
    /**      
     * 判断cron时间表达式正确性      
     * @param cronExpression      
     * @return       
     */       
    public static boolean isValidExpression(final String cronExpression){       
//      CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);       
        CronTriggerImpl trigger = new CronTriggerImpl();          
        try {       
            trigger.setCronExpression(cronExpression);       
            Date date = trigger.computeFirstFireTime(null);         
            return date != null && date.after(new Date());          
        } catch (ParseException e) {       
        }        
        return false;       
    }       
           
    public static void main(String[] args) throws SchedulerException{       
    	  SchedulerFactory schedulerFactory = new StdSchedulerFactory();
          Scheduler scheduler = schedulerFactory.getScheduler();

          JobDetail jobDetail = JobBuilder.newJob()
                  .ofType(QuartzJobFactoryDisallowConcurrentExecution.class)
                  .usingJobData("Test1","Quartz")
                  .withIdentity("Test1","Group1")
                  .build();//通过JobBuilder构建JobDetailImpl 实例,也可以直接new JobDetailImpl

          Trigger trigger = TriggerBuilder.newTrigger()
                  .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))  //还有更多时间格式，详情可以百度一下
                  .forJob("Test1","Group1")       //Trigger找到对应的名称为Test1组为Group1的Job,如果不存在则会在执行scheduler.scheduleJob(jobDetail,trigger);报错
                  .build();//通过TriggerBuilder构建CronTriggerImpl实例,也可以直接new CronTriggerImpl

          scheduler.scheduleJob(jobDetail,trigger);//任务每5秒触发一次

          scheduler.start();
    	
    	
    }       
    
    public void run(String jobName){
    	System.out.println("Job running===="+System.currentTimeMillis());
    	System.out.println("Job jobName===="+jobName);
    }
           
    /*      
     * 任务运行状态      
     */       
    public enum TASK_STATE{       
        NONE("NONE","未知"),       
        NORMAL("NORMAL", "正常运行"),       
        PAUSED("PAUSED", "暂停状态"),        
        COMPLETE("COMPLETE",""),       
        ERROR("ERROR","错误状态"),       
        BLOCKED("BLOCKED","锁定状态");       
               
        private TASK_STATE(String index,String name) {          
            this.name = name;          
            this.index = index;          
        }         
        private String index;         
        private String name;         
        public String getName() {          
            return name;          
        }          
        public String getIndex() {          
            return index;          
        }       
    }       
}
