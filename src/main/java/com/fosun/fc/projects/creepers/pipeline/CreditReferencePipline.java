package com.fosun.fc.projects.creepers.pipeline;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant.TableNamesCreditReference;
import com.fosun.fc.projects.creepers.dao.CreepersAssetHandleDao;
import com.fosun.fc.projects.creepers.dao.CreepersBasicDao;
import com.fosun.fc.projects.creepers.dao.CreepersCcDetailDao;
import com.fosun.fc.projects.creepers.dao.CreepersCompensatoryDao;
import com.fosun.fc.projects.creepers.dao.CreepersGeneralDao;
import com.fosun.fc.projects.creepers.dao.CreepersGuaranteeDao;
import com.fosun.fc.projects.creepers.dao.CreepersHlDetailDao;
import com.fosun.fc.projects.creepers.dao.CreepersOlDetailDao;
import com.fosun.fc.projects.creepers.dao.CreepersPublicCivilDao;
import com.fosun.fc.projects.creepers.dao.CreepersPublicEnforcementDao;
import com.fosun.fc.projects.creepers.dao.CreepersPublicIspDao;
import com.fosun.fc.projects.creepers.dao.CreepersPublicSanctionDao;
import com.fosun.fc.projects.creepers.dao.CreepersPublicTaxDao;
import com.fosun.fc.projects.creepers.dao.CreepersQueryLogDao;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersAccountBak;
import com.fosun.fc.projects.creepers.entity.TCreepersAssetHandle;
import com.fosun.fc.projects.creepers.entity.TCreepersBasic;
import com.fosun.fc.projects.creepers.entity.TCreepersCcDetail;
import com.fosun.fc.projects.creepers.entity.TCreepersCompensatory;
import com.fosun.fc.projects.creepers.entity.TCreepersGeneral;
import com.fosun.fc.projects.creepers.entity.TCreepersGuarantee;
import com.fosun.fc.projects.creepers.entity.TCreepersHlDetail;
import com.fosun.fc.projects.creepers.entity.TCreepersOlDetail;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicCivil;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicEnforcement;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicIsp;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicSanction;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicTax;
import com.fosun.fc.projects.creepers.entity.TCreepersQueryLog;
import com.fosun.fc.projects.creepers.service.ICreepersAccountBakService;
import com.fosun.fc.projects.creepers.service.ICreepersListCreditService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

/**
 * 
 * <p>
 * 将爬取的HTMLContent写入Text文本中: 其他信息写入数据库
 * </p>
 * 
 * @author maxin
 * @since 2016-5-25 19:49:16
 * 
 */
@Component("creditReferencePipline")
public class CreditReferencePipline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired(required = true)
    private ICreepersAccountBakService creepersAccountBakServiceImpl;
    @Autowired(required = true)
    private CreepersAssetHandleDao creepersAssetHandleDao;
    @Autowired(required = true)
    private CreepersBasicDao creepersBasicDao;
    @Autowired(required = true)
    private CreepersCcDetailDao creepersCcDetailDao;
    @Autowired(required = true)
    private CreepersCompensatoryDao creepersCompensatoryDao;
    @Autowired(required = true)
    private CreepersGeneralDao creepersGeneralDao;
    @Autowired(required = true)
    private CreepersGuaranteeDao creepersGuaranteeDao;
    @Autowired(required = true)
    private CreepersHlDetailDao creepersHlDetailDao;
    @Autowired(required = true)
    private CreepersOlDetailDao creepersOlDetailDao;
    @Autowired(required = true)
    private CreepersPublicCivilDao creepersPublicCivilDao;
    @Autowired(required = true)
    private CreepersPublicEnforcementDao creepersPublicEnforcementDao;
    @Autowired(required = true)
    private CreepersPublicIspDao creepersPublicIspDao;
    @Autowired(required = true)
    private CreepersPublicSanctionDao creepersPublicSanctionDao;
    @Autowired(required = true)
    private CreepersPublicTaxDao creepersPublicTaxDao;
    @Autowired(required = true)
    private CreepersQueryLogDao creepersQueryLogDao;
    @Autowired(required = true)
    private ICreepersListCreditService creepersListCreditServiceImpl;

    public CreditReferencePipline() {
        setPath("/webmagic/");
    }

    public CreditReferencePipline(String path) {
        setPath(path);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(ResultItems resultItems, Task task) {
        CreepersLoginParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        // 入库
        TableNamesCreditReference[] getNames = CreepersConstant.TableNamesCreditReference.values();
        try {
            for (TableNamesCreditReference tableName : getNames) {

                if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_ACCOUNT_BAK.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    TCreepersAccountBak entity = new TCreepersAccountBak();
                    CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                    CommonMethodUtils.setByDT(entity);
                    logger.info("step: ======>> entry saveEntity");
                    creepersAccountBakServiceImpl.saveOrIfNotAccountBak(entity);
                    logger.info("step: ======>> saveEntity succeed!");
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_ASSET_HANDLE.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersAssetHandle> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersAssetHandle());
                        for (TCreepersAssetHandle entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersAssetHandleDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersAssetHandle entity = new TCreepersAssetHandle();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersAssetHandleDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_BASIC.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersBasic> entityLis = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersBasic());
                        for (TCreepersBasic entity : entityLis) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersBasicDao.save(entityLis);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersBasic entity = new TCreepersBasic();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersBasicDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_CC_DETAIL.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersCcDetail> entityLis = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersCcDetail());
                        for (TCreepersCcDetail entity : entityLis) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersCcDetailDao.save(entityLis);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersCcDetail entity = new TCreepersCcDetail();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersCcDetailDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_COMPENSATORY.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersCompensatory> entityLis = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersCompensatory());
                        for (TCreepersCompensatory entity : entityLis) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersCompensatoryDao.save(entityLis);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersCompensatory entity = new TCreepersCompensatory();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersCompensatoryDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_GENERAL.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersGeneral> entityLis = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersGeneral());
                        for (TCreepersGeneral entity : entityLis) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersGeneralDao.save(entityLis);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersGeneral entity = new TCreepersGeneral();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersGeneralDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_GUARANTEE.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersGuarantee> entityLis = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersGuarantee());
                        for (TCreepersGuarantee entity : entityLis) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersGuaranteeDao.save(entityLis);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersGuarantee entity = new TCreepersGuarantee();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersGuaranteeDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_HL_DETAIL.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersHlDetail> entityLis = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersHlDetail());
                        for (TCreepersHlDetail entity : entityLis) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersHlDetailDao.save(entityLis);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersHlDetail entity = new TCreepersHlDetail();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersHlDetailDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_OL_DETAIL.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersOlDetail> entityLis = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersOlDetail());
                        for (TCreepersOlDetail entity : entityLis) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersOlDetailDao.save(entityLis);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersOlDetail entity = new TCreepersOlDetail();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersOlDetailDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_CIVIL.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersPublicCivil> entityLis = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersPublicCivil());
                        for (TCreepersPublicCivil entity : entityLis) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersPublicCivilDao.save(entityLis);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersPublicCivil entity = new TCreepersPublicCivil();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersPublicCivilDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_ENFORCEMENT.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersPublicEnforcement> entityLis = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersPublicEnforcement());
                        for (TCreepersPublicEnforcement entity : entityLis) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersPublicEnforcementDao.save(entityLis);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersPublicEnforcement entity = new TCreepersPublicEnforcement();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersPublicEnforcementDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_ISP.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersPublicIsp> entityLis = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersPublicIsp());
                        for (TCreepersPublicIsp entity : entityLis) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersPublicIspDao.save(entityLis);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersPublicIsp entity = new TCreepersPublicIsp();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersPublicIspDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_SANCTION.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersPublicSanction> entityLis = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersPublicSanction());
                        for (TCreepersPublicSanction entity : entityLis) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersPublicSanctionDao.save(entityLis);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersPublicSanction entity = new TCreepersPublicSanction();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersPublicSanctionDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_TAX.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersPublicTax> entityLis = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersPublicTax());
                        for (TCreepersPublicTax entity : entityLis) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersPublicTaxDao.save(entityLis);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersPublicTax entity = new TCreepersPublicTax();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersPublicTaxDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                } else if (tableName.getMapKey()
                        .equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_QUERY_LOG.getMapKey())) {
                    if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
                        continue;
                    }
                    if (tableName.isList()) {
                        List<TCreepersQueryLog> entityList = CommonMethodUtils
                                .mapList(resultItems.get(tableName.getMapKey()), new TCreepersQueryLog());
                        for (TCreepersQueryLog entity : entityList) {
                            CommonMethodUtils.setByDT(entity);
                        }
                        logger.info("step: ======>> entry saveList");
                        creepersQueryLogDao.save(entityList);
                        logger.info("step: ======>> saveList succeed!");
                    } else {
                        TCreepersQueryLog entity = new TCreepersQueryLog();
                        CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()), entity);
                        CommonMethodUtils.setByDT(entity);
                        logger.info("step: ======>> entry saveEntity");
                        creepersQueryLogDao.save(entity);
                        logger.info("step: ======>> saveEntity succeed!");
                    }
                }
            }
            creepersListCreditServiceImpl.updateTaskListStatusSucceed(param.getLoginName());
        } catch (Exception e) {
            logger.error("write DB error", e);
            logger.error("step:  ======>>  CreditReferencePipline save FALSE!!!");
            logger.error("write DB error", e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorInfo("write DB error" + e.getCause().getClass() + e.getCause().getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
    }
}
