package com.fosun.fc.projects.creepers.pipeline;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.service.ICreepersInsuranceService;
import com.fosun.fc.projects.creepers.service.ICreepersListService;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

@Component
public class InsurancePipeline extends BasePipeline {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ICreepersInsuranceService creepersInsuranceServiceImpl;
    @Autowired
    private ICreepersListService creepersListServiceImpl;
    public InsurancePipeline(){
        setPath("/data/webmagic/");
    }
    
    public InsurancePipeline(String path){
        setPath(path);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void process(ResultItems resultItems, Task task) {
        CreepersLoginParamDTO param=resultItems.get(BaseConstant.PARAM_DTO_KEY);
        try {
            Map<String, Object> map = resultItems.getAll();
            Map<String, String> basicMap = (Map<String, String>) map.get(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_BASIC.getMapKey());
            if(null!=basicMap&&basicMap.size()>0){
                creepersInsuranceServiceImpl.deleteByCertNo(param.getLoginName());;
//                System.out.println(1/0);
                creepersInsuranceServiceImpl.saveEntity(map);
                param.setTaskStatus(BaseConstant.TaskListStatus.SUCCEED.getValue());
                creepersListServiceImpl.updateList(param);
                logger.info("step:  ======>>  InsurancePipeline.process succeed!");
            }else {
                logger.info("step:  ======>>  input resultList is empty! Then update status to false!");
                param.setErrorInfo("input resultList is empty! Then update status to false!");
                creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
                logger.info("step:  ======>>  InsurancePipeline.process fail,data is empty!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("write DB error", e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorInfo("write DB error" + e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            logger.info("step:  ======>>  InsurancePipeline.process fail,Something unexpected happened!");
        }
    }
}
