package com.fosun.fc.projects.creepers.web.rest;

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
public class EntityMethodTest extends SpringTransactionalTestCase {

    // @Autowired(required = true)
    // private CreepersListShixinDao creepersListShixinDao;
    // @Autowired(required = true)
    // private CreepersListCreditDao creepersListCreditDao;
//    @Autowired(required = true)
//    private CreepersListTourGuideDao creepersListTourGuideDao;
    @Autowired(required = true)
    private CreepersCourtCorpBondsDao creepersCourtCorpBondsDao;

    public void methodTest() {
        /*
         * TCreepersAccount entity = new TCreepersAccount();
         * entity.setUpdatedBy("test"); entity.setCreatedDt(new Date());
         * entity.setUsr("CRE3"); entity.setPwd("888888");
         * entity.setRptNo("100000000001"); entity.setStatus("0");
         * entity.setFilePath("D:/"); entity.setCde("sa90");
         * entity.setCreatedBy("test"); entity.setUpdatedDt(new Date()); //
         * creepersAccountDao.save(entity);
         */

        /*
         * TCreepersBasic basicEntity = new TCreepersBasic();
         * basicEntity.setRptNo("100000000001"); basicEntity.setQueryDt(new
         * Date()); basicEntity.setRptDt(new Date());
         * basicEntity.setName("asd"); basicEntity.setIdType("1");
         * basicEntity.setIdNo("17784757585");
         * CommonMethodUtils.setByDT(basicEntity);
         */

        // TCreepersProxyList entity = new TCreepersProxyList();
        // entity.setId(308);
        // entity.setIp("222.174.72.122");
        // entity.setPort("99992");
        // entity.setIp("HTTP/HTTPS");
        // CommonMethodUtils.setByDT(entity);
        // creepersProxyListDao.saveAndFlush(entity);
        /*
         * List<TCreepersProxyList> entityList = creepersProxyListDao.findAll();
         * StringBuffer result = new StringBuffer(); StringBuffer proxyList =
         * new StringBuffer(); proxyList.append(
         * "List<String[]> list = new ArrayList<String[]>();\n"); for
         * (TCreepersProxyList entity : entityList) {
         * result.append(entity.getIp()).append(" "
         * ).append(entity.getPort()).append("\n");
         * 
         * try { Integer.valueOf(entity.getPort());
         * proxyList.append("list.add(").append("new String[]{\""
         * ).append(entity.getIp()).append("\",\"")
         * .append(entity.getPort()).append("\"});").append("\n"); } catch
         * (Exception e) { System.out.println(
         * "port is not a int,can't be translated!"); } }
         * System.out.println(proxyList.toString()); try { File file = new
         * File("/webmagic/proxyFile/"); if (file.exists()) { // to do nothing.
         * } else { file.mkdirs(); } PrintWriter pw = new PrintWriter(new
         * FileWriter(new File("/webmagic/proxyFile/proxy.txt")));
         * pw.write(result.toString()); pw.flush(); pw.close(); } catch
         * (IOException e) { // TODO Auto-generated catch block
         * e.printStackTrace(); }
         * 
         * 
         */
        // String requestType =
        // BaseConstant.TaskListType.JUDGEMENT_LIST.getValue();
        // long count = creepersConfigDao.countByRequestType(requestType);
        // TCreepersConfig oldEntity =
        // creepersConfigDao.findTop1ByRequestType(requestType);
        // TCreepersConfig entity = new TCreepersConfig();
        // entity.setId(oldEntity.getId());
        // entity.setRequestType(BaseConstant.TaskListType.JUDGEMENT_LIST.getValue());
        // entity.setAgentFlag("1");
        // entity.setCdRequestCode("setCdRequestCode");
        // entity.setCdUrl("setCdUrl");
        // entity.setDomain("setDomain");
        // entity.setHeaders("setHeaders");
        // entity.setRetryTimes("3");
        // entity.setRootUrl("setRootUrl");
        // entity.setSwitchFlag("0");
        // entity.setThreadNum("11");
        // entity.setTimeOut("3000000");
        // entity.setUserAgent("setUserAgent");
        // CommonMethodUtils.setByDT(entity);
        // creepersConfigDao.saveAndFlush(entity);
        // creepersListShixinDao.updateListByMerName("111", "0");
        // TCreepersListCredit entity = new TCreepersListCredit();
        // entity.setUserCode("1234");
        // CommonMethodUtils.setByDT(entity);
        // creepersListCreditDao.save(entity);

        /*
         * CreepersLoginParamDTO dto = new CreepersLoginParamDTO();
         * dto.setLoginName("123"); dto.setPassword("456");
         * dto.setMessageCaptchaValue("789"); String str =
         * JSONObject.toJSONString(dto); System.out.println(str);
         */
        // List<TCreepersListCredit> result =
        // creepersListCreditDao.findByUserCode("1234");
        // for (TCreepersListCredit eachEntity : result) {
        // System.err.println(eachEntity.toString());
        // }
        // TCreepersListTourGuide entity = new TCreepersListTourGuide();
        // entity.setCardNo("60065813");
        // entity.setCertNo("411");
        // entity.setGuideNo("D-4502-009024");
        // CommonMethodUtils.setByDT(entity);
        // creepersListTourGuideDao.save(entity);
//        List<TCreepersListTourGuide> result = creepersListTourGuideDao
//                .findByGuideNoAndCertNoOrCardNoAndCertNo("D-4502-009024", "411", "", "411");
//        System.err.println("=======>>>:" + result.size());
        
        List<TCreepersCourtCorpBonds> oldList = creepersCourtCorpBondsDao.findByName("吉林成城集团股份有限公司");
        TCreepersCourtCorpBonds entity1 = oldList.get(0);
        entity1.setProvince("河南3");
        creepersCourtCorpBondsDao.save(entity1);
        TCreepersCourtCorpBonds entity2 = oldList.get(0);
        entity2.setProvince("河南4");
        creepersCourtCorpBondsDao.save(entity2);
        TCreepersCourtCorpBonds entity3 = oldList.get(0);
        entity1.setProvince("河南5");
        creepersCourtCorpBondsDao.save(entity3);
        TCreepersCourtCorpBonds entity4 = oldList.get(0);
        entity2.setProvince("河南6");
        creepersCourtCorpBondsDao.save(entity4);
        List<TCreepersCourtCorpBonds> oldList1 = creepersCourtCorpBondsDao.findByName("吉林成城集团股份有限公司");
        System.err.println(oldList1);
        System.out.println("最后一次查询完成！");
    }

    @Test
    public void run() {
        methodTest();
    }

}
