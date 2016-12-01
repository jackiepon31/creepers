package com.fosun.fc.projects.creepers.redis.service.Impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.pageprocessor.ProxyMimvpProcessor;
import com.fosun.fc.projects.creepers.pipeline.ProxyMimvpPipline;
import com.fosun.fc.projects.creepers.redis.service.IRedisSubService;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;

import us.codecraft.webmagic.Spider;

/**
 * <p>
 * description:的消费端实现
 * </p>
 * 
 * @author MaXin
 * @since 2016-8-24 13:44:28
 * @see
 */
@Service
public class ProxyMimvpSubServiceImpl implements IRedisSubService {
    @Autowired
    private ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;

    @Autowired(required = true)
    private ProxyMimvpProcessor proxyMimvpProcessor;

    @Autowired(required = true)
    private ProxyMimvpPipline proxyMimvpPipline;

    @Autowired(required = true)
    private HttpRequestDownloader httpRequestDownloader;

    @Override
    public void handleMessage(Serializable message) {
        String merName = message.toString();
        // 1.初始化param DTO
        CreepersParamDTO param = new CreepersParamDTO();
        param.putSearchKeyWord(merName);
        param.setTaskType(BaseConstant.TaskListType.PROXY_IP_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        try {
            Spider.create(proxyMimvpProcessor).addPipeline(proxyMimvpPipline)
                    .setDownloader(httpRequestDownloader.setParam(param))
                    .addUrl("http://proxy.mimvp.com/free.php?proxy=in_tp")
                    .addUrl("http://proxy.mimvp.com/free.php?proxy=in_hp").thread(1).run();
        } catch (Exception e) {
            e.printStackTrace();
            param.setErrorPath(e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorInfo(e.getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
    }

}
