package com.fosun.fc.projects.creepers.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersInsuranceBasicDao;
import com.fosun.fc.projects.creepers.dao.CreepersInsurancePaymentDao;
import com.fosun.fc.projects.creepers.dao.CreepersInsuranceSumDao;
import com.fosun.fc.projects.creepers.dao.CreepersInsuranceUnitDao;
import com.fosun.fc.projects.creepers.downloader.SimulateLoginDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersInsuranceBasic;
import com.fosun.fc.projects.creepers.entity.TCreepersInsurancePayment;
import com.fosun.fc.projects.creepers.entity.TCreepersInsuranceSum;
import com.fosun.fc.projects.creepers.entity.TCreepersInsuranceUnit;
import com.fosun.fc.projects.creepers.pageprocessor.InsuranceProcessor;
import com.fosun.fc.projects.creepers.pipeline.InsurancePipeline;
import com.fosun.fc.projects.creepers.service.ICreepersInsuranceService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

@Service
@Transactional
public class CreepersInsuranceServiceImpl extends CreepersBaseServiceImpl implements ICreepersInsuranceService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CreepersInsuranceBasicDao creepersInsuranceBasicDao;
    @Autowired
    private CreepersInsurancePaymentDao creepersInsurancePaymentDao;
    @Autowired
    private CreepersInsuranceUnitDao creepersInsuranceUnitDao;
    @Autowired
    private CreepersInsuranceSumDao creepersInsuranceSumDao;
    @Autowired
    private InsuranceProcessor insuranceProcessor;
    @Autowired
    private InsurancePipeline insurancePipeline;

    @Override
    public void processByParam(CreepersLoginParamDTO param) {
        logger.info("=============>CreepersInsuranceServiceImpl.processByMerName start!");
        // 初始化Param DTO
        param.setTaskType(BaseConstant.TaskListType.INSURANCE_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, "http://www.12333sh.gov.cn/sbsjb/wzb/129.jsp");
        param.putOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL, "http://www.12333sh.gov.cn/sbsjb/wzb/Bmblist.jsp");
        param.putOrderUrl(BaseConstant.OrderUrlKey.DO_LOGIN_URL, "http://www.12333sh.gov.cn/sbsjb/wzb/dologin.jsp");
        param.setCaptchaKey("userjym");
        param.setLoginNameKey("userid");
        param.setPasswordKey("userpw");
        List<String> targetUrlList = new ArrayList<String>();
        targetUrlList.add("http://www.12333sh.gov.cn/sbsjb/wzb/sbsjbcx.jsp");
        param.setTargetUrlList(targetUrlList);
        Request request = CommonMethodUtils.buildIndexRequest(param);
        // 启动爬虫
        logger.info("=============>启动爬虫!");
        Spider.create(insuranceProcessor).addPipeline(insurancePipeline)
                .setDownloader(new SimulateLoginDownloader().setParam(param)).thread(1).addRequest(request).run();
        logger.info("=============>CreepersInsuranceServiceImpl.processByMerName start!");

    }

    @Override
    public Map<String, Object> findListByCertNoForMap(String certNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersInsuranceBasic> basicEntityList = creepersInsuranceBasicDao.queryListByCertNo(certNo);
        List<TCreepersInsurancePayment> paymentEntityList = creepersInsurancePaymentDao.queryListByCertNo(certNo);
        List<TCreepersInsuranceUnit> unitEntityList = creepersInsuranceUnitDao.queryListByCertNo(certNo);
        List<TCreepersInsuranceSum> sumEntityList = creepersInsuranceSumDao.queryListByCertNo(certNo);

        List<Map<String, Object>> basicMapList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> paymentMapList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> unitMapList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> sumMapList = new ArrayList<Map<String, Object>>();

        try {
            if (CommonMethodUtils.isEmpty(basicEntityList)) {
                basicMapList = CommonMethodUtils.entityList(basicEntityList);
                result.put(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_BASIC.getMapKey(), basicMapList);
            }

            if (CommonMethodUtils.isEmpty(paymentEntityList)) {
                paymentMapList = CommonMethodUtils.entityList(paymentEntityList);
                result.put(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_PAYMENT.getMapKey(),
                        paymentMapList);
            }

            if (CommonMethodUtils.isEmpty(unitEntityList)) {
                unitMapList = CommonMethodUtils.entityList(unitEntityList);
                result.put(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_UNIT.getMapKey(), unitMapList);
            }

            if (CommonMethodUtils.isEmpty(sumEntityList)) {
                sumMapList = CommonMethodUtils.entityList(sumEntityList);
                result.put(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_SUM.getMapKey(), sumMapList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("CreepersInsuranceServiceImpl findListByCertNoForMap error:", e);
            CreepersLoginParamDTO param = new CreepersLoginParamDTO();
            param.setLoginName(certNo);
            param.setErrorInfo("CreepersInsuranceServiceImpl findListByCertNoForMap error:"
                    + e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            logger.error("======================>>>  CreepersInsuranceServiceImpl.findListByCertNoForMap end!");
        }
        return result;
    }

    @Override
    public void deleteByCertNo(String certNo) {

        creepersInsuranceBasicDao.deleteByCertNo(certNo);
        creepersInsurancePaymentDao.deleteByCertNo(certNo);
        creepersInsuranceUnitDao.deleteByCertNo(certNo);
        creepersInsuranceSumDao.deleteByCertNo(certNo);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void saveEntity(Map<String, Object> map) throws Exception {
        Map<String, String> basicMap = (Map<String, String>) map
                .get(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_BASIC.getMapKey());
        TCreepersInsuranceBasic basic = new TCreepersInsuranceBasic();
        BeanUtils.populate(basic, basicMap);
        CommonMethodUtils.setByDT(basic);
        creepersInsuranceBasicDao.saveAndFlush(basic);
        List<HashMap<String, String>> paymentMapList = (List<HashMap<String, String>>) map
                .get(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_PAYMENT.getMapKey());
        for (HashMap<String, String> paymentMap : paymentMapList) {
            TCreepersInsurancePayment entity = new TCreepersInsurancePayment();
            BeanUtils.populate(entity, paymentMap);
            CommonMethodUtils.setByDT(entity);
            creepersInsurancePaymentDao.saveAndFlush(entity);
        }
        List<HashMap<String, String>> unitMapList = (List<HashMap<String, String>>) map
                .get(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_UNIT.getMapKey());
        for (HashMap<String, String> unitMap : unitMapList) {
            TCreepersInsuranceUnit entity = new TCreepersInsuranceUnit();
            BeanUtils.populate(entity, unitMap);
            CommonMethodUtils.setByDT(entity);
            creepersInsuranceUnitDao.saveAndFlush(entity);
        }
        List<HashMap<String, String>> sumMapList = (List<HashMap<String, String>>) map
                .get(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_SUM.getMapKey());
        for (HashMap<String, String> sumMap : sumMapList) {
            TCreepersInsuranceSum entity = new TCreepersInsuranceSum();
            BeanUtils.populate(entity, sumMap);
            CommonMethodUtils.setByDT(entity);
            creepersInsuranceSumDao.saveAndFlush(entity);
        }

    }

}
