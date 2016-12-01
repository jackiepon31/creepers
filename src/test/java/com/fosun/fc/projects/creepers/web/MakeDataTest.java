package com.fosun.fc.projects.creepers.web;

import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.fosun.fc.modules.test.category.UnStable;
import com.fosun.fc.modules.test.spring.SpringTransactionalTestCase;
import com.fosun.fc.projects.creepers.dao.CreepersCourtCorpBondsDao;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtCorpBonds;

@Category(UnStable.class)
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml" })
@TransactionConfiguration(defaultRollback = false)
public class MakeDataTest extends SpringTransactionalTestCase {

//    @Autowired
//    private CreepersShixinAllDao creepersShixinAllDao;
    @Autowired
    private CreepersCourtCorpBondsDao creepersCourtCorpBondsDao;

    @Test
    public void test() {
//        TCreepersShixinAll shixinAll = new TCreepersShixinAll();
//        shixinAll.setName("good");
//        shixinAll.setCode("123");
//        shixinAll.setFlag("0");
//        CommonMethodUtils.setByDT(shixinAll);
//        creepersShixinAllDao.save(shixinAll);
//        List<TCreepersCourtCorpBonds> oldList = creepersCourtCorpBondsDao.findByName("吉林成城集团股份有限公司");
        List<TCreepersCourtCorpBonds> entityList = creepersCourtCorpBondsDao.findByName("吉林成城集团股份有限公司");
        TCreepersCourtCorpBonds entity = entityList.get(0);
        entity.setProvince("河南");
        System.out.println(entity.getVersion());
        creepersCourtCorpBondsDao.saveAndFlush(entity);
        List<TCreepersCourtCorpBonds> entityList1 = creepersCourtCorpBondsDao.findByName("吉林成城集团股份有限公司");
        TCreepersCourtCorpBonds entity1 = entityList1.get(0);
        entity1.setProvince("吉林");
        System.out.println(entity.getVersion());
        creepersCourtCorpBondsDao.saveAndFlush(entity1);
    }
}