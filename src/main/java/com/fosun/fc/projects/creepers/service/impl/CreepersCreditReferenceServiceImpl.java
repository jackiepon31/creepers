/**
 * <p>
 * Copyright(c) @2016 Fortune Credit Management Co., Ltd.
 * </p>
 */
package com.fosun.fc.projects.creepers.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.downloader.CreditReferenceSeleniumDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersAccountBak;
import com.fosun.fc.projects.creepers.pageprocessor.CreditReferenceCenterProcessor;
import com.fosun.fc.projects.creepers.pipeline.CreditReferencePipline;
import com.fosun.fc.projects.creepers.pipeline.CreditReferenceUploadFilePipline;
import com.fosun.fc.projects.creepers.service.ICreepersAccountBakService;
import com.fosun.fc.projects.creepers.service.ICreepersAssetHandleService;
import com.fosun.fc.projects.creepers.service.ICreepersBasicService;
import com.fosun.fc.projects.creepers.service.ICreepersCcDetailService;
import com.fosun.fc.projects.creepers.service.ICreepersCompensatoryService;
import com.fosun.fc.projects.creepers.service.ICreepersCreditReferenceService;
import com.fosun.fc.projects.creepers.service.ICreepersGeneralService;
import com.fosun.fc.projects.creepers.service.ICreepersGuaranteeService;
import com.fosun.fc.projects.creepers.service.ICreepersHlDetailService;
import com.fosun.fc.projects.creepers.service.ICreepersListCreditService;
import com.fosun.fc.projects.creepers.service.ICreepersOlDetailService;
import com.fosun.fc.projects.creepers.service.ICreepersPublicCivilService;
import com.fosun.fc.projects.creepers.service.ICreepersPublicEnforcementService;
import com.fosun.fc.projects.creepers.service.ICreepersPublicIspService;
import com.fosun.fc.projects.creepers.service.ICreepersPublicSanctionService;
import com.fosun.fc.projects.creepers.service.ICreepersPublicTaxService;
import com.fosun.fc.projects.creepers.service.ICreepersQueryLogService;

import us.codecraft.webmagic.Spider;

/**
 * <p>
 * description:人行个人征信报告Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年10月19日
 * @see
 */
@Service("creepersCreditReferenceServiceImpl")
public class CreepersCreditReferenceServiceImpl implements ICreepersCreditReferenceService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired(required = true)
    private CreditReferenceCenterProcessor creditReferenceCenterProcessor;

    @Autowired(required = true)
    private CreditReferencePipline creditReferencePipline;

    @Autowired(required = true)
    private CreditReferenceUploadFilePipline creditReferenceUploadFilePipline;
    
    @Autowired
    private ICreepersAccountBakService creepersAccountBakServiceImpl;
    
    @Autowired
    private ICreepersAssetHandleService creepersAssetHandleServiceImpl;
    
    @Autowired
    private ICreepersBasicService creepersBasicServiceImpl;
    
    @Autowired
    private ICreepersCcDetailService creepersCcDetailServiceImpl;
    
    @Autowired
    private ICreepersCompensatoryService creepersCompensatoryServiceImpl;
    
    @Autowired
    private ICreepersGeneralService creepersGeneralServiceImpl;
    
    @Autowired
    private ICreepersGuaranteeService creepersGuaranteeServiceImpl;
    
    @Autowired
    private ICreepersHlDetailService creepersHlDetailServiceImpl;
    
    @Autowired
    private ICreepersOlDetailService creepersOlDetailServiceImpl;
    
    @Autowired
    private ICreepersPublicCivilService creepersPublicCivilServiceImpl;
    
    @Autowired
    private ICreepersPublicEnforcementService creepersPublicEnforcementServiceImpl;
    
    @Autowired
    private ICreepersPublicIspService creepersPublicIspServiceImpl;
    
    @Autowired
    private ICreepersPublicSanctionService creepersPublicSanctionServiceImpl;
    
    @Autowired
    private ICreepersPublicTaxService creepersPublicTaxServiceImpl;
    
    @Autowired
    private ICreepersQueryLogService creepersQueryLogServiceImpl;
    
    @Autowired
    private ICreepersListCreditService creepersListCreditServiceImpl;

    @SuppressWarnings("resource")
    @Override
    public void processByParam(CreepersLoginParamDTO param) {
        logger.info("=============>CreepersInsuranceServiceImpl.processByMerName start!");
        // 初始化Param DTO
        param.setTaskType(BaseConstant.TaskListType.CREDIT_REFERENCE_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, "https://ipcrs.pbccrc.org.cn/");
        // 启动爬虫
        logger.info("=============>启动爬虫!");
        Spider.create(creditReferenceCenterProcessor).addPipeline(creditReferencePipline).addPipeline(creditReferenceUploadFilePipline)
                .setDownloader(new CreditReferenceSeleniumDownloader().setParam(param)).thread(1)
                .addUrl(param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL)).run();
        logger.info("=============>CreepersCreditReferenceServiceImpl.processByMerName start!");

    }

    @Override
    public Map<String, Object> findByLoginNameForMap(CreepersLoginParamDTO param) {
        TCreepersAccountBak accountBakEntity = creepersAccountBakServiceImpl.findTop1ByUsrAndCde(param.getLoginName(), param.getMessageCaptchaValue());
        String rptNo = accountBakEntity.getRptNo();
        Map<String ,Object> result = new HashMap<String ,Object>();
        result.putAll(creepersAccountBakServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersAssetHandleServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersBasicServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersCcDetailServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersCompensatoryServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersGeneralServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersGuaranteeServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersHlDetailServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersOlDetailServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersPublicCivilServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersPublicEnforcementServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersPublicIspServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersPublicSanctionServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersPublicTaxServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersQueryLogServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersListCreditServiceImpl.findByUserCodeAndMessageCode(param.getLoginName(), param.getMessageCaptchaValue()));
        return result;
    }

    @Override
    public void deleteByLoginName(String loginName) {

    }

    @Override
    public void saveEntity(Map<String, Object> map) throws Exception {

    }

}
