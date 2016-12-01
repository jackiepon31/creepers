package com.fosun.fc.projects.creepers.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant.TableNamesFund;
import com.fosun.fc.projects.creepers.dao.CreepersFundBasicDao;
import com.fosun.fc.projects.creepers.dao.CreepersFundBasicDetailDao;
import com.fosun.fc.projects.creepers.dao.CreepersFundExtraDao;
import com.fosun.fc.projects.creepers.dao.CreepersFundExtraDetailDao;
import com.fosun.fc.projects.creepers.downloader.SimulateLoginDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersFundBasic;
import com.fosun.fc.projects.creepers.entity.TCreepersFundBasicDetail;
import com.fosun.fc.projects.creepers.entity.TCreepersFundExtra;
import com.fosun.fc.projects.creepers.entity.TCreepersFundExtraDetail;
import com.fosun.fc.projects.creepers.pageprocessor.FundProcessor;
import com.fosun.fc.projects.creepers.pipeline.FundPipline;
import com.fosun.fc.projects.creepers.service.ICreepersFundService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.fosun.fc.projects.creepers.utils.code.BaseCode;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

/**
 * 
 * <p>
 * description:
 * </p>
 * 
 * @author pengyk
 * @since 2016年9月07日
 * @see
 */
@Service
@Transactional
public class CreepersFundServiceImpl extends CreepersBaseServiceImpl implements ICreepersFundService {

    @Autowired
    private CreepersFundBasicDao creepersFundBasicDao;
    @Autowired
    private CreepersFundBasicDetailDao creepersFundBasicDetailDao;
    @Autowired
    private CreepersFundExtraDao creepersFundExtraDao;
    @Autowired
    private CreepersFundExtraDetailDao creepersFundExtraDetailDao;
    @Autowired
    private FundProcessor fundProcessor;
    @Autowired
    private FundPipline fundPipline;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 公积金查询
     */
    @Override
    public Map<String, Object> findByLoginNameForMap(String loginName) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersFundBasic> entityBasicList = creepersFundBasicDao.findByLoginName(loginName);
        List<TCreepersFundBasicDetail> entityBasicDetailList = creepersFundBasicDetailDao.findByLoginName(loginName);
        List<TCreepersFundExtra> entityExtraList = creepersFundExtraDao.findByLoginName(loginName);
        List<TCreepersFundExtraDetail> entityExtraDetailList = creepersFundExtraDetailDao.findByLoginName(loginName);

        List<Map<String, Object>> basicDtoList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> basicDetailDtoList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> extraDtoList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> extraDetailDtoList = new ArrayList<Map<String, Object>>();
        try {
            if (!CommonMethodUtils.isEmpty(entityBasicList)) {
                basicDtoList = CommonMethodUtils.entityList(entityBasicList);
                result.put(CreepersConstant.TableNamesFund.T_CREEPERS_FUND_BASIC.getMapKey(), basicDtoList);
            }

            if (!CommonMethodUtils.isEmpty(entityBasicDetailList)) {
                CommonMethodUtils.entityList(entityBasicDetailList);
                result.put(CreepersConstant.TableNamesFund.T_CREEPERS_FUND_BASIC_DETAIL.getMapKey(),
                        basicDetailDtoList);
            }

            if (!CommonMethodUtils.isEmpty(entityExtraList)) {
                CommonMethodUtils.entityList(entityExtraList);
                result.put(CreepersConstant.TableNamesFund.T_CREEPERS_FUND_EXTRA.getMapKey(), extraDtoList);
            }

            if (!CommonMethodUtils.isEmpty(entityExtraDetailList)) {
                CommonMethodUtils.entityList(entityExtraDetailList);
                result.put(CreepersConstant.TableNamesFund.T_CREEPERS_FUND_EXTRA_DETAIL.getMapKey(),
                        extraDetailDtoList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("CreepersFundServiceImpl findByLoginNameForMap error:", e);
            CreepersLoginParamDTO param = new CreepersLoginParamDTO();
            param.setLoginName(loginName);
            param.setErrorInfo("CreepersFundServiceImpl findByLoginNameForMap error:"
                    + e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            logger.error("======================>>>  CreepersFundServiceImpl.findByLoginNameForMap end!");
        }
        return result;
    }

    /**
     * 公积金查询
     */
    @Override
    public void processByParam(CreepersLoginParamDTO param) {

        logger.info("=============>CreepersFundServiceImpl.processByMerName start!");
        // 初始化Param DTO
        param.setTaskType(BaseConstant.TaskListType.FUND_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, "https://persons.shgjj.com/");
        param.putOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL, "https://persons.shgjj.com/VerifyImageServlet");
        param.putOrderUrl(BaseConstant.OrderUrlKey.DO_LOGIN_URL, "https://persons.shgjj.com/SsoLogin");
        param.setCaptchaKey("imagecode");
        param.setLoginNameKey("username");
        param.setPasswordKey("password");
        List<String> targetUrlList = new ArrayList<String>();
        targetUrlList.add("https://persons.shgjj.com/MainServlet?ID=1");
        targetUrlList.add("https://persons.shgjj.com/MainServlet?ID=11");
        targetUrlList.add("https://persons.shgjj.com/MainServlet?ID=3");
        targetUrlList.add("https://persons.shgjj.com/MainServlet?ID=31");
        param.setTargetUrlList(targetUrlList);
        param.putNameValuePair("SUBMIT.x", (int) (Math.random() * 70) + 2 + "");
        param.putNameValuePair("SUBMIT.y", (int) (Math.random() * 20) + 2 + "");
        param.putNameValuePair("password_md5", BaseCode.encryptMD5To16(param.getPassword()));
        Request request = CommonMethodUtils.buildIndexRequest(param);
        // 启动爬虫
        logger.info("=============>启动爬虫!");
        Spider.create(fundProcessor).addPipeline(fundPipline)
                .setDownloader(new SimulateLoginDownloader().setParam(param)).addRequest(request).thread(4).run();
        logger.info("=============>CreepersFundServiceImpl.processByMerName start!");

    }

    @SuppressWarnings("unchecked")
    @Override
    public void deleteAndSave(Map<String, Object> map) throws Exception {
        TableNamesFund[] getNames = CreepersConstant.TableNamesFund.values();
        for (TableNamesFund tableName : getNames) {
            if (tableName.getMapKey().equals(CreepersConstant.TableNamesFund.T_CREEPERS_FUND_BASIC.getMapKey())) {
                if (CommonMethodUtils.isEmpty(get(map, tableName.getMapKey()))) {
                    continue;
                }
                TCreepersFundBasic entity = new TCreepersFundBasic();
                HashMap<String, String> basicMap = (HashMap<String, String>) map.get(tableName.getMapKey());
                CommonMethodUtils.mapTransEntity(basicMap, entity);
                CommonMethodUtils.setByDT(entity);
                logger.info("step: ======>> entry saveEntity");
                creepersFundBasicDao.deleteByLoginName(entity.getLoginName());
                creepersFundBasicDao.saveAndFlush(entity);
                logger.info("step: ======>> saveEntity succeed!");
            } else if (tableName.getMapKey()
                    .equals(CreepersConstant.TableNamesFund.T_CREEPERS_FUND_BASIC_DETAIL.getMapKey())) {
                if (CommonMethodUtils.isEmpty(get(map, tableName.getMapKey()))) {
                    continue;
                }
                List<HashMap<String, String>> basicDetailList = (List<HashMap<String, String>>) map
                        .get(tableName.getMapKey());
                creepersFundBasicDetailDao.deleteByLoginName(basicDetailList.get(0)
                        .get(CreepersConstant.TCreepersFundBasicDetailColumn.LOGIN_NAME.getValue()));
                for (HashMap<String, String> basicDetailMap : basicDetailList) {
                    TCreepersFundBasicDetail entity = new TCreepersFundBasicDetail();
                    CommonMethodUtils.mapTransEntity(basicDetailMap, entity);
                    CommonMethodUtils.setByDT(entity);
                    logger.info("step: ======>> entry saveList");
                    creepersFundBasicDetailDao.saveAndFlush(entity);
                }
                logger.info("step: ======>> saveList succeed!");
            } else
                if (tableName.getMapKey().equals(CreepersConstant.TableNamesFund.T_CREEPERS_FUND_EXTRA.getMapKey())) {
                if (CommonMethodUtils.isEmpty(get(map, tableName.getMapKey()))) {
                    continue;
                }
                TCreepersFundExtra entity = new TCreepersFundExtra();
                HashMap<String, String> extraMap = (HashMap<String, String>) map.get(tableName.getMapKey());
                CommonMethodUtils.mapTransEntity(extraMap, entity);
                CommonMethodUtils.setByDT(entity);
                logger.info("step: ======>> entry saveEntity");
                creepersFundExtraDao.deleteByLoginName(entity.getLoginName());
                creepersFundExtraDao.saveAndFlush(entity);
                logger.info("step: ======>> saveEntity succeed!");
            } else if (tableName.getMapKey()
                    .equals(CreepersConstant.TableNamesFund.T_CREEPERS_FUND_EXTRA_DETAIL.getMapKey())) {
                if (CommonMethodUtils.isEmpty(get(map, tableName.getMapKey()))) {
                    continue;
                }
                List<HashMap<String, String>> extraDetailList = (List<HashMap<String, String>>) map
                        .get(tableName.getMapKey());
                creepersFundExtraDetailDao.deleteByLoginName(extraDetailList.get(0)
                        .get(CreepersConstant.TCreepersFundExtraDetailColumn.LOGIN_NAME.getValue()));
                for (HashMap<String, String> extraDetailMap : extraDetailList) {
                    TCreepersFundExtraDetail entity = new TCreepersFundExtraDetail();
                    CommonMethodUtils.mapTransEntity(extraDetailMap, entity);
                    CommonMethodUtils.setByDT(entity);
                    logger.info("step: ======>> entry saveList");
                    creepersFundExtraDetailDao.saveAndFlush(entity);
                }
                logger.info("step: ======>> saveList succeed!");
            }
        }
        logger.info("step: ======>> FundDetailPipline Insert DB succeed!");
    }

    /**
     * 公积金删除
     */
    @Override
    public void deleteByLoginName(String loginName) {
        creepersFundBasicDao.deleteByLoginName(loginName);
        creepersFundBasicDetailDao.deleteByLoginName(loginName);
        creepersFundExtraDao.deleteByLoginName(loginName);
        creepersFundExtraDetailDao.deleteByLoginName(loginName);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T> T get(Map map, String key) {
        Object o = map.get(key);
        if (o == null) {
            return null;
        }
        return (T) map.get(key);
    }

}
