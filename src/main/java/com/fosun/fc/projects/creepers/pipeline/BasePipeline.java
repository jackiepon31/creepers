package com.fosun.fc.projects.creepers.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.service.ICreepersListService;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

/**
 * 
 * <p>
 * 基础pipeline
 * </p>
 * 
 * @author maxin
 * @since 2016年6月27日
 * @see
 */
@Transactional
@Component("basePipeline")
public class BasePipeline extends FilePersistentBase implements Pipeline {

    @Autowired
    protected ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;

    @Autowired
    protected ICreepersListService creepersListServiceImpl;
    
    public BasePipeline() {
        setPath("/data/webmagic/");
    }

    public BasePipeline(String path) {
        setPath(path);
    }

    public ICreepersExceptionHandleService getCreepersExceptionHandleServiceImpl() {
        return creepersExceptionHandleServiceImpl;
    }

    public void setCreepersExceptionHandleServiceImpl(
            ICreepersExceptionHandleService creepersExceptionHandleServiceImpl) {
        this.creepersExceptionHandleServiceImpl = creepersExceptionHandleServiceImpl;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
    }
}
