package com.fosun.fc.projects.creepers.schedule;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import com.fosun.fc.modules.utils.Threads;

public class SpringCronJob implements Runnable {

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(SpringCronJob.class);

    private String cronExpression;

    private int shutdownTimeout = Integer.MAX_VALUE;

    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @PostConstruct
    public void start() {
        Validate.notBlank(cronExpression);
        threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setThreadNamePrefix("SpringCronJob");
        threadPoolTaskScheduler.setPoolSize(10);
        threadPoolTaskScheduler.initialize();
        threadPoolTaskScheduler.schedule(this, new CronTrigger(cronExpression));
    }

    @PreDestroy
    public void stop() {
        ScheduledExecutorService scheduledExecutorService = threadPoolTaskScheduler.getScheduledExecutor();
        Threads.normalShutdown(scheduledExecutorService, shutdownTimeout, TimeUnit.SECONDS);
    }

    @Override
    public void run() {

    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public void setShutdownTimeout(int shutdownTimeout) {
        this.shutdownTimeout = shutdownTimeout;
    }
}
