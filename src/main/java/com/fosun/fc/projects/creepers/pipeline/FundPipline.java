package com.fosun.fc.projects.creepers.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.service.ICreepersFundService;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

/**
 * 
 * <p>
 * 工商信息详情入库
 * </p>
 * 
 * @author pengyk
 * @since 2016-09-07 01:02:02
 * @see
 */
@Component
public class FundPipline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersFundService creepersFundServiceImpl;

    public FundPipline() {
        setPath("/data/webmagic/");
    }

    public FundPipline(String path) {
        setPath(path);
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        CreepersLoginParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        try {
            logger.info("step: ======>> delete old data and save new data fundInfo");
            creepersFundServiceImpl.deleteAndSave(resultItems.getAll());
            logger.info("step: ======>> update listFund");
            param.setTaskStatus(BaseConstant.TaskListStatus.SUCCEED.getValue());
            creepersListServiceImpl.updateList(param);
            logger.info("step:  ======>>  FundPipeline.process succeed!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("write DB error", e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorInfo("write DB error" + e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            logger.info("step:  ======>>  FundPipeline.process fail,Something unexpected happened!");
        }
    }
}
