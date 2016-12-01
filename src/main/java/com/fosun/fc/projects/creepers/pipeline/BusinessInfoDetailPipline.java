package com.fosun.fc.projects.creepers.pipeline;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant.TableNamesBusinessInfo;
import com.fosun.fc.projects.creepers.dao.CreepersEntAssetDao;
import com.fosun.fc.projects.creepers.dao.CreepersEntBasicDao;
import com.fosun.fc.projects.creepers.dao.CreepersEntChangeDao;
import com.fosun.fc.projects.creepers.dao.CreepersEntEquityDao;
import com.fosun.fc.projects.creepers.dao.CreepersEntIntelDao;
import com.fosun.fc.projects.creepers.dao.CreepersEntInvestDao;
import com.fosun.fc.projects.creepers.dao.CreepersEntLicenseDao;
import com.fosun.fc.projects.creepers.dao.CreepersEntShareChangeDao;
import com.fosun.fc.projects.creepers.dao.CreepersEntShareDao;
import com.fosun.fc.projects.creepers.dao.CreepersEntWarrantDao;
import com.fosun.fc.projects.creepers.dao.CreepersEntWebDao;
import com.fosun.fc.projects.creepers.dao.CreepersMerAbnormalDao;
import com.fosun.fc.projects.creepers.dao.CreepersMerBasicDao;
import com.fosun.fc.projects.creepers.dao.CreepersMerBranchDao;
import com.fosun.fc.projects.creepers.dao.CreepersMerChangeDao;
import com.fosun.fc.projects.creepers.dao.CreepersMerCheckDao;
import com.fosun.fc.projects.creepers.dao.CreepersMerIllegalDao;
import com.fosun.fc.projects.creepers.dao.CreepersMerKeymanDao;
import com.fosun.fc.projects.creepers.dao.CreepersMerPenaltyDao;
import com.fosun.fc.projects.creepers.dao.CreepersMerPledgeDao;
import com.fosun.fc.projects.creepers.dao.CreepersMerPropertyDao;
import com.fosun.fc.projects.creepers.dao.CreepersMerShareholderDao;
import com.fosun.fc.projects.creepers.dao.CreepersMerUndoDao;
import com.fosun.fc.projects.creepers.dao.CreepersOtherFreezeDao;
import com.fosun.fc.projects.creepers.dao.CreepersOtherShareChangeDao;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersEntAsset;
import com.fosun.fc.projects.creepers.entity.TCreepersEntBasic;
import com.fosun.fc.projects.creepers.entity.TCreepersEntChange;
import com.fosun.fc.projects.creepers.entity.TCreepersEntEquity;
import com.fosun.fc.projects.creepers.entity.TCreepersEntIntel;
import com.fosun.fc.projects.creepers.entity.TCreepersEntInvest;
import com.fosun.fc.projects.creepers.entity.TCreepersEntLicense;
import com.fosun.fc.projects.creepers.entity.TCreepersEntShare;
import com.fosun.fc.projects.creepers.entity.TCreepersEntShareChange;
import com.fosun.fc.projects.creepers.entity.TCreepersEntWarrant;
import com.fosun.fc.projects.creepers.entity.TCreepersEntWeb;
import com.fosun.fc.projects.creepers.entity.TCreepersMerAbnormal;
import com.fosun.fc.projects.creepers.entity.TCreepersMerBasic;
import com.fosun.fc.projects.creepers.entity.TCreepersMerBranch;
import com.fosun.fc.projects.creepers.entity.TCreepersMerChange;
import com.fosun.fc.projects.creepers.entity.TCreepersMerCheck;
import com.fosun.fc.projects.creepers.entity.TCreepersMerIllegal;
import com.fosun.fc.projects.creepers.entity.TCreepersMerKeyman;
import com.fosun.fc.projects.creepers.entity.TCreepersMerPenalty;
import com.fosun.fc.projects.creepers.entity.TCreepersMerPledge;
import com.fosun.fc.projects.creepers.entity.TCreepersMerProperty;
import com.fosun.fc.projects.creepers.entity.TCreepersMerShareholder;
import com.fosun.fc.projects.creepers.entity.TCreepersMerUndo;
import com.fosun.fc.projects.creepers.entity.TCreepersOtherFreeze;
import com.fosun.fc.projects.creepers.entity.TCreepersOtherShareChange;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

/**
 * 
 * <p>
 * 工商信息详情入库
 * </p>
 * 
 * @author maxin
 * @since 2016-7-15 01:02:02
 * @see
 */
@Component("businessInfoDetailPipline")
public class BusinessInfoDetailPipline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired(required = true)
    private CreepersEntAssetDao creepersEntAssetDao;

    @Autowired(required = true)
    private CreepersEntBasicDao creepersEntBasicDao;

    @Autowired(required = true)
    private CreepersEntChangeDao creepersEntChangeDao;

    @Autowired(required = true)
    private CreepersEntEquityDao creepersEntEquityDao;

    @Autowired(required = true)
    private CreepersEntIntelDao creepersEntIntelDao;

    @Autowired(required = true)
    private CreepersEntInvestDao creepersEntInvestDao;

    @Autowired(required = true)
    private CreepersEntLicenseDao creepersEntLicenseDao;

    @Autowired(required = true)
    private CreepersEntShareDao creepersEntShareDao;

    @Autowired(required = true)
    private CreepersEntShareChangeDao creepersEntShareChangeDao;

    @Autowired(required = true)
    private CreepersEntWarrantDao creepersEntWarrantDao;

    @Autowired(required = true)
    private CreepersEntWebDao creepersEntWebDao;

    @Autowired(required = true)
    private CreepersMerAbnormalDao creepersMerAbnormalDao;

    @Autowired(required = true)
    private CreepersMerBasicDao creepersMerBasicDao;

    @Autowired(required = true)
    private CreepersMerBranchDao creepersMerBranchDao;

    @Autowired(required = true)
    private CreepersMerChangeDao creepersMerChangeDao;

    @Autowired(required = true)
    private CreepersMerCheckDao creepersMerCheckDao;

    @Autowired(required = true)
    private CreepersMerIllegalDao creepersMerIllegalDao;

    @Autowired(required = true)
    private CreepersMerKeymanDao creepersMerKeymanDao;

    @Autowired(required = true)
    private CreepersMerPenaltyDao creepersMerPenaltyDao;

    @Autowired(required = true)
    private CreepersMerPledgeDao creepersMerPledgeDao;

    @Autowired(required = true)
    private CreepersMerPropertyDao creepersMerPropertyDao;

    @Autowired(required = true)
    private CreepersMerShareholderDao creepersMerShareholderDao;

    @Autowired(required = true)
    private CreepersMerUndoDao creepersMerUndoDao;

    @Autowired(required = true)
    private CreepersOtherFreezeDao creepersOtherFreezeDao;

    @Autowired(required = true)
    private CreepersOtherShareChangeDao creepersOtherShareChangeDao;

    public BusinessInfoDetailPipline() {
        setPath("/data/webmagic/");
    }

    public BusinessInfoDetailPipline(String path) {
        setPath(path);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("step: ======>> entry BusinessInfoDetailPipline");

        TableNamesBusinessInfo[] getNames = CreepersConstant.TableNamesBusinessInfo.values();
        try {
            for (TableNamesBusinessInfo tableName : getNames) {
                if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_ASSET.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersEntAsset> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersEntAsset());
                        for (TCreepersEntAsset entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersEntAssetDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersEntAsset entity = new TCreepersEntAsset();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersEntAssetDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_BASIC.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersEntBasic> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersEntBasic());
                        for (TCreepersEntBasic entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersEntBasicDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersEntBasic entity = new TCreepersEntBasic();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersEntBasicDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_CHANGE.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersEntChange> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersEntChange());
                        for (TCreepersEntChange entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersEntChangeDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersEntChange entity = new TCreepersEntChange();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersEntChangeDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_EQUITY.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersEntEquity> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersEntEquity());
                        for (TCreepersEntEquity entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersEntEquityDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersEntEquity entity = new TCreepersEntEquity();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersEntEquityDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_INTEL.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersEntIntel> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersEntIntel());
                        for (TCreepersEntIntel entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersEntIntelDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersEntIntel entity = new TCreepersEntIntel();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersEntIntelDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_INVEST.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersEntInvest> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersEntInvest());
                        for (TCreepersEntInvest entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersEntInvestDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersEntInvest entity = new TCreepersEntInvest();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersEntInvestDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_LICENSE.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersEntLicense> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersEntLicense());
                        for (TCreepersEntLicense entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersEntLicenseDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersEntLicense entity = new TCreepersEntLicense();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersEntLicenseDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_SHARE.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersEntShare> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersEntShare());
                        for (TCreepersEntShare entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersEntShareDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersEntShare entity = new TCreepersEntShare();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersEntShareDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_SHARE_CHANGE.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersEntShareChange> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersEntShareChange());
                        for (TCreepersEntShareChange entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersEntShareChangeDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersEntShareChange entity = new TCreepersEntShareChange();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersEntShareChangeDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_WARRANT.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersEntWarrant> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersEntWarrant());
                        for (TCreepersEntWarrant entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersEntWarrantDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersEntWarrant entity = new TCreepersEntWarrant();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersEntWarrantDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_WEB.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersEntWeb> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersEntWeb());
                        for (TCreepersEntWeb entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersEntWebDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersEntWeb entity = new TCreepersEntWeb();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersEntWebDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }

                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_ABNORMAL.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersMerAbnormal> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersMerAbnormal());
                        for (TCreepersMerAbnormal entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersMerAbnormalDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersMerAbnormal entity = new TCreepersMerAbnormal();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersMerAbnormalDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_BASIC.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersMerBasic> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersMerBasic());
                        for (TCreepersMerBasic entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersMerBasicDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersMerBasic entity = new TCreepersMerBasic();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersMerBasicDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_BRANCH.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersMerBranch> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersMerBranch());
                        for (TCreepersMerBranch entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersMerBranchDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersMerBranch entity = new TCreepersMerBranch();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersMerBranchDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_CHANGE.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersMerChange> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersMerChange());
                        for (TCreepersMerChange entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersMerChangeDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersMerChange entity = new TCreepersMerChange();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersMerChangeDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_CHECK.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersMerCheck> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersMerCheck());
                        for (TCreepersMerCheck entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersMerCheckDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersMerCheck entity = new TCreepersMerCheck();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersMerCheckDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_ILLEGAL.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersMerIllegal> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersMerIllegal());
                        for (TCreepersMerIllegal entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersMerIllegalDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersMerIllegal entity = new TCreepersMerIllegal();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersMerIllegalDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_KEYMAN.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersMerKeyman> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersMerKeyman());
                        for (TCreepersMerKeyman entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersMerKeymanDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersMerKeyman entity = new TCreepersMerKeyman();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersMerKeymanDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PENALTY.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersMerPenalty> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersMerPenalty());
                        for (TCreepersMerPenalty entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersMerPenaltyDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersMerPenalty entity = new TCreepersMerPenalty();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersMerPenaltyDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PLEDGE.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersMerPledge> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersMerPledge());
                        for (TCreepersMerPledge entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersMerPledgeDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersMerPledge entity = new TCreepersMerPledge();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersMerPledgeDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PROPERTY.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersMerProperty> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersMerProperty());
                        for (TCreepersMerProperty entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersMerPropertyDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersMerProperty entity = new TCreepersMerProperty();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersMerPropertyDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_SHAREHOLDER.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersMerShareholder> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersMerShareholder());
                        for (TCreepersMerShareholder entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersMerShareholderDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersMerShareholder entity = new TCreepersMerShareholder();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersMerShareholderDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_UNDO.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersMerUndo> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersMerUndo());
                        for (TCreepersMerUndo entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersMerUndoDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersMerUndo entity = new TCreepersMerUndo();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersMerUndoDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_OTHER_FREEZE.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersOtherFreeze> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersOtherFreeze());
                        for (TCreepersOtherFreeze entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersOtherFreezeDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersOtherFreeze entity = new TCreepersOtherFreeze();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersOtherFreezeDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_OTHER_SHARE_CHANGE.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersOtherShareChange> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersOtherShareChange());
                        for (TCreepersOtherShareChange entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersOtherShareChangeDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersOtherShareChange entity = new TCreepersOtherShareChange();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersOtherShareChangeDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("write DB error", e.getCause().getClass() + e.getCause().getMessage());
            CreepersParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
            param.setErrorPath(getClass().toString());
            param.setErrorInfo("write DB error" + e.getCause().getClass() + e.getCause().getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
        logger.info("step: ======>> BusinessInfoDetailPipline Insert DB succeed!");
    }

}
