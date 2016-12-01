package com.fosun.fc.projects.creepers.pageprocessor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nlpcn.commons.lang.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant.TableNamesBusinessInfo;
import com.fosun.fc.projects.creepers.pipeline.BusinessInfoDetailPipline;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 
 * <p>
 * Demo: 四川省工商信息明细
 * </p>
 * 
 * @author MaXin
 * @since 2016年7月12日
 * @see
 */
@Component("businessInfoDetail51Processor")
public class BusinessInfoDetail51Processor extends BaseBusinessDetailProcessor implements PageProcessor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Site site;

    public BusinessInfoDetail51Processor() {
        if (null == site) {
            site = Site.me().setSleepTime(1000).setTimeOut(100000);
        }
    }

    // private static final String XPATH_PAGE_MER_NO =
    // "/html/body/*[@name=\"regno\"]/@value";

    private static final String XPATH_ID_DIV_JI_BEN_XIN_XI = "//*[@id=\"jibenxinxi\"]";
    private static final String XPATH_ID_DIV_BEI_AN = "//*[@id=\"beian\"]";
    private static final String XPATH_ID_DIV_XZCF = "//*[@id=\"gsgsxx_xzcf\"]";
    private static final String XPATH_ID_DIV_GU_QUAN_CHU_ZHI = "//*[@id=\"guquanchuzhi\"]";
    private static final String XPATH_ID_DIV_DONG_CHAN_DI_YA = "//*[@id=\"dongchandiya\"]";
    private static final String XPATH_ID_DIV_YI_CHANG_MING_LU = "//*[@id=\"yichangminglu\"]";
    private static final String XPATH_ID_DIV_CHOU_CHA_XIN_XI = "//*[@id=\"chouchaxinxi\"]";
    private static final String XPATH_ID_DIV_YAN_ZHONG_WEI_FA = "//*[@id=\"yanzhongweifa\"]";
    private static final String XPATH_ID_DIV_SI_FA_PAN_DING = "//*[@id=\"sifapanding\"]";
    private static final String XPATH_ID_DIV_QYNB = "//*[@id=\"qynb\"]";
    private static final String XPATH_ID_DIV_TZRBGXX = "//*[@id=\"tzrbgxx\"]";
    private static final String XPATH_ID_DIV_TZRXX = "//*[@id=\"tzrxx\"]";
    private static final String XPATH_ID_DIV_DETAILS = "//*[@id=\"details\"]";

    private static final String XPATH_ID_TABLE_FR = "//*[@id=\"table_fr\"]";
    private static final String XPATH_ID_TABLE_BG = "//*[@id=\"table_bg\"]";
    private static final String XPATH_ID_TABLE_RY1 = "//*[@id=\"table_ry1\"]";
    private static final String XPATH_ID_TABLE_FR2 = "//*[@id=\"table_fr2\"]";
    private static final String XPATH_ID_TABLE_GSCFXX = "//*[@id=\"table_gscfxx\"]";
    private static final String XPATH_ID_TABLE_GQ = "//*[@id=\"table_gq\"]";
    private static final String XPATH_ID_TABLE_DC = "//*[@id=\"table_dc\"]";
    private static final String XPATH_ID_TABLE_YC = "//*[@id=\"table_yc\"]";
    private static final String XPATH_ID_TABLE_CCJC = "//*[@id=\"table_ccjc\"]";
    private static final String XPATH_ID_TABLE_WFXX = "//*[@id=\"table_wfxx\"]";
    private static final String XPATH_ID_TABLE_TZRBGXX = "//*[@id=\"table_tzrbgxx\"]";
    private static final String XPATH_ID_TABLE_QYTZR = "//*[@id=\"table_qytzr\"]";
    private static final String XPATH_ID_TABLE_TZRXXBG = "//*[@id=\"table_tzrxxbg\"]";
    private static final String XPATH_ID_TABLE_WZXX = "//*[@id=\"table_wzxx\"]";
    private static final String XPATH_ID_TABLE_TZRXX = "//*[@id=\"table_tzrxx\"]";
    private static final String XPATH_ID_TABLE_TZXX = "//*[@id=\"table_tzxx\"]";
    private static final String XPATH_ID_TABLE_DBXX = "//*[@id=\"table_dbxx\"]";
    private static final String XPATH_ID_TABLE_GQBG = "//*[@id=\"table_gqbg\"]";
    private static final String XPATH_ID_TABLE_BGXX = "//*[@id=\"table_bgxx\"]";

    private static final String XPATH_NAME_TR_FR = "//*[@name=\"fr\"]";
    private static final String XPATH_NAME_TR_BG = "//*[@name=\"bg\"]";
    private static final String XPATH_NAME_TR_RY1 = "//*[@name=\"ry1\"]";
    private static final String XPATH_NAME_TR_GSCFXX = "//*[@name=\"gscfxx\"]";
    private static final String XPATH_NAME_TR_YC = "//*[@name=\"yc\"]";
    private static final String XPATH_NAME_TR_CCJC = "//*[@name=\"ccjc\"]";
    private static final String XPATH_NAME_TR_WFXX = "//*[@name=\"wfxx\"]";
    private static final String XPATH_NAME_TR_QYTZR = "//*[@name=\"qytzr\"]";
    private static final String XPATH_NAME_TR_TZRXXBG = "//*[@name=\"tzrxxbg\"]";

    private static final String REGEX_URL_BASE = "http://gsxt.scaic.gov.cn/ztxy.do";
    private static final String REGEX_URL_ID_0 = "\\?method=qyInfo&maent.pripid=\\w+&czmk=czmk1&";
    private static final String REGEX_URL_ID_2 = "\\?method=baInfo&maent.pripid=\\w+&czmk=czmk2&";
    private static final String REGEX_URL_ID_3 = "\\?method=cfInfo&maent.pripid=\\w+&czmk=czmk3&";
    private static final String REGEX_URL_ID_4 = "\\?method=gqczxxInfo&maent.pripid=\\w+&czmk=czmk4&";
    private static final String REGEX_URL_ID_5 = "\\?method=dcdyInfo&maent.pripid=\\w+&czmk=czmk4&";
    private static final String REGEX_URL_ID_6 = "\\?method=jyycInfo&maent.pripid=\\w+&czmk=czmk6&";
    private static final String REGEX_URL_ID_7 = "\\?method=ccjcInfo&maent.pripid=\\w+&czmk=czmk7&";
    private static final String REGEX_URL_ID_8 = "\\?method=yzwfInfo&maent.pripid=\\w+&czmk=czmk14&";
    private static final String REGEX_URL_ID_9 = "\\?method=spyzInfo&maent.pripid=\\w+&czmk=czmk19&";
    private static final String REGEX_URL_ID_10 = "\\?method=xfztsjbInfo&maent.pripid=\\w+&czmk=czmk20&";
    private static final String REGEX_URL_ID_11 = "\\?method=jyjcInfo&maent.pripid=\\w+&czmk=czmk22&";
    private static final String REGEX_URL_ID_30 = "\\?method=JcxxfjInfo&maent.pripid=\\w+&czmk=czmk30&";
    private static final String REGEX_URL_ID_31 = "\\?method=YjxxInfo&maent.pripid=\\w+&czmk=czmk31&";
    private static final String REGEX_URL_ID_32 = "\\?method=JsxxInfo&maent.pripid=\\w+&czmk=czmk32&";
    private static final String REGEX_URL_ID_33 = "\\?method=TsxxInfo&maent.pripid=\\w+&czmk=czmk33&";
    private static final String REGEX_URL_ID_GS2 = "\\?method=qygsInfo&maent.pripid=\\w+&czmk=czmk8&";
    private static final String REGEX_URL_ID_GS3 = "\\?method=qtgsInfo&maent.pripid=\\w+&czmk=czmk9&";
    private static final String REGEX_URL_ID_GS4 = "\\?method=sfgsInfo&maent.pripid=\\w+&czmk=czmk17&";
    private static final String REGEX_URL_ID_GS5 = "\\?method=sfgsInfo&maent.pripid=\\w+&czmk=czmk17&";
    private static final String REGEX_URL_ID_NDBG = "\\?method=ndbgDetail&maent.pripid=\\w+&maent.nd=\\d+&random=";
    private static final String REGEX_URL_ID_QYGS_FOR_TZRXX = "\\?method=qygsForTzrxxInfo&maent.pripid=\\w+&czmk=czmk12&";
    private static final String REGEX_URL_ID_QYGS_FOR_TZRBGXX = "\\?method=qygsForTzrbgxxInfo&maent.pripid=\\w+&czmk=czmk15&";

    private static final String REGEX_URL_GET_MAENT_PRIPID = "(?<=&maent.pripid=)(.*)(?=&czmk)";

    private static final String REGEX_CONTENT_MER_NO = "注册号/统一社会信用代码：(.*) ";

    private static final String REPLACE_FUNCTION_DO_XZFY_DETAIL = "doXzfyDetail(";
    private static final String REPLACE_FUNCTION_DO_NDBG = "doNdbg(";

    private static final String APPEND_URL_METHOD_DCDY_DETAIL = "?method=dcdyDetail&maent.pripid=";
    private static final String APPEND_URL_METHOD_DO_XZFY_DETAIL = "?method=doXzfyDetail&maent.pripid=";
    private static final String APPEND_URL_METHOD_NDBG_DETAIL = "?method=ndbgDetail&maent.pripid=";

    // http://gsxt.scaic.gov.cn/ztxy.do?method=ndbgDetail&maent.pripid=\\w+&maent.nd=\\d+&random=

    @Override
    public void process(Page page) {
        String merNo = page.getHtml().xpath(XPATH_ID_DIV_DETAILS + XPATH_ALL_TEXT).regex(REGEX_CONTENT_MER_NO)
                .toString().replaceAll(REPLACE_CONTENT_NBSP + SPILT_SEMICOLON, CONTENT_EMPTY).trim();
        Map<String, String> merNoMap = new HashMap<String, String>();
        merNoMap.put(CreepersConstant.TCreepersListColumn.MER_NO.getValue(), merNo);
        logger.info(CreepersConstant.TCreepersListColumn.MER_NO.getValue()
                + SPILT_COLON + merNoMap.get(CreepersConstant.TCreepersListColumn.MER_NO.getValue()));

        page.putField(PAGE_URL, page.getUrl().toString());

        // 初始化putField
        TableNamesBusinessInfo[] getNames = CreepersConstant.TableNamesBusinessInfo.values();
        for (TableNamesBusinessInfo tableNamesBusinessInfo : getNames) {
            Object obj;
            if (tableNamesBusinessInfo.isList()) {
                obj = new ArrayList<Map<String, Object>>();
            } else {
                obj = new HashMap<String, Object>();
            }
            page.putField(tableNamesBusinessInfo.getMapKey(), obj);
        }

        ResultItems resultItems = page.getResultItems();

        if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_0).match()) {
            // 登记信息 id:0 解析登记信息页面信息 工商公示信息 id:gs1 增加页面其他链接的url
            logger.info("======================>>>method 1!  登记信息   start");
            method1(page.getHtml().xpath(XPATH_ID_DIV_JI_BEN_XIN_XI), resultItems, merNoMap);
            logger.info("======================>>>method 1!  登记信息   finish");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_2).match()) {
            // 备案信息 id:2
            logger.info("======================>>>method 2!  备案信息   start");
            method2(page.getHtml().xpath(XPATH_ID_DIV_BEI_AN), resultItems, merNoMap);
            logger.info("======================>>>method 2!  备案信息   finish");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_3).match()) {
            // 行政处罚信息 id:3
            logger.info("======================>>>method 3!  行政处罚信息 start");
            method3(page.getHtml().xpath(XPATH_ID_DIV_XZCF), resultItems, merNoMap);
            logger.info("======================>>>method 3!  行政处罚信息 finish");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_4).match()) {
            // 股权出质登记信息 id:4
            logger.info("======================>>>method 4!  股权出质登记信息   start");
            method4(page.getHtml().xpath(XPATH_ID_DIV_GU_QUAN_CHU_ZHI), resultItems, merNoMap);
            logger.info("======================>>>method 4!  股权出质登记信息   finish");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_5).match()) {
            // 动产抵押登记信息 id:5
            logger.info("======================>>>method 5!  动产抵押登记信息   start");
            method5(page.getHtml().xpath(XPATH_ID_DIV_DONG_CHAN_DI_YA), resultItems, merNoMap);
            logger.info("======================>>>method 5!  动产抵押登记信息   finish");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_6).match()) {
            // 经营异常信息 id:6
            logger.info("======================>>>method 6!  经营异常信息 start");
            method6(page.getHtml().xpath(XPATH_ID_DIV_YI_CHANG_MING_LU), resultItems, merNoMap);
            logger.info("======================>>>method 6!  经营异常信息 finish");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_7).match()) {
            // 抽查检查信息 id:7
            logger.info("======================>>>method 7!  抽查检查信息 start");
            method7(page.getHtml().xpath(XPATH_ID_DIV_CHOU_CHA_XIN_XI), resultItems, merNoMap);
            logger.info("======================>>>method 7!  抽查检查信息 finish");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_8).match()) {
            // 严重违法信息 id:8
            logger.info("======================>>>method 8!  严重违法信息 start");
            method8(page.getHtml().xpath(XPATH_ID_DIV_YAN_ZHONG_WEI_FA), resultItems, merNoMap);
            logger.info("======================>>>method 8!  严重违法信息 finish");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_9).match()) {
            // id:9
            logger.info("======================>>>method 9!  ");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_10).match()) {
            // id:10
            logger.info("======================>>>method 10!  ");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_11).match()) {
            // id:11
            logger.info("======================>>>method 11!  ");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_30).match()) {
            // id:30
            logger.info("======================>>>method 30!  ");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_31).match()) {
            // id:31
            logger.info("======================>>>method 31!  ");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_32).match()) {
            // id:32
            logger.info("======================>>>method 32!  ");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_33).match()) {
            // id:33
            logger.info("======================>>>method 33!  ");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_GS2).match()) {
            // 企业年报 id:qygs_qynb 企业公示信息 id:gs2 增加页面其他链接的url
            logger.info("======================>>>method gs2!  企业公示信息   start");
            methodQygsQynb(page.getHtml().xpath(XPATH_ID_DIV_QYNB + XPATH_ID_DIV_SI_FA_PAN_DING), page, merNoMap);
            logger.info("======================>>>method gs2!  企业公示信息   finish");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_QYGS_FOR_TZRXX).match()) {
            // 股东及出资信息 id:qygsForTzrxxInfo
            logger.info("======================>>>method qygsForTzrxxInfo!  股东及出资信息   start");
            methodQygsForTzrxxInfo(page.getHtml().xpath(XPATH_ID_DIV_TZRXX), resultItems, merNoMap);
            logger.info("======================>>>method qygsForTzrxxInfo!  股东及出资信息   finish");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_QYGS_FOR_TZRBGXX).match()) {
            // 股权变更信息 id:qygsForTzrbgxxInfo
            logger.info("======================>>>method qygsForTzrbgxxInfo!  股权变更信息   start");
            methodQygsForTzrbgxxInfo(page.getHtml().xpath(XPATH_ID_DIV_TZRBGXX), resultItems, merNoMap);
            logger.info("======================>>>method qygsForTzrbgxxInfo!  股权变更信息    finish");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_NDBG).match()) {
            // 年度报告:
            logger.info("======================>>>method ndbg!  年度报告   start");
            methodNdbg(page.getHtml().xpath(XPATH_ID_DIV_SI_FA_PAN_DING), resultItems, merNoMap);
            logger.info("======================>>>method ndbg!  年度报告   finish");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_GS3).match()) {
            // 其他部门公示信息 id:gs3 增加页面其他链接的url
            logger.info("======================>>>method gs3!  其他部门公示信息");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_GS4).match()) {
            // 司法协助公示信息 id:gs4 增加页面其他链接的url
            logger.info("======================>>>method gs4!  司法协助公示信息");
        } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_GS5).match()) {
            // id:gs5
            logger.info("======================>>>method gs5!  ");
        }
    }

    // 登记信息 id:0 解析登记信息页面信息 工商公示信息 id:gs1 增加页面其他链接的url
    private void method1(Selectable divSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("entry method1!");
        // logger.info(selectable.toString());
        // 基本信息
        merBaseInfo(divSelectable.xpath(XPATH_TABLE).nodes().get(0), resultItems, merNoMap);
        // 股东信息
        method1Shareholder(divSelectable.xpath(XPATH_ID_TABLE_FR), resultItems, merNoMap);
        // 变更信息
        method1ChangeInfo(divSelectable.xpath(XPATH_ID_TABLE_BG), resultItems, merNoMap);
        logger.info("finish method1!");
    }

    // 股东信息
    private void method1Shareholder(Selectable tableSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   method1Shareholder:");
        List<Selectable> trList = tableSelectable.xpath(XPATH_NAME_TR_FR).nodes();
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_SHAREHOLDER.getMapKey());
        for (Selectable eachTr : trList) {
            // logger.info(eachTr.toString());
            List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 5) {
                logger.info("=============>>>当前行不足5个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();
            // 股东
            map.put(CreepersConstant.TCreepersMerShareholderColumn.NAME.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerShareholderColumn.NAME.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.NAME.getValue()));

            // 证照/证件类型
            map.put(CreepersConstant.TCreepersMerShareholderColumn.CERT_TYPE.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerShareholderColumn.CERT_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.CERT_TYPE.getValue()));

            // 证照/证件号码
            map.put(CreepersConstant.TCreepersMerShareholderColumn.CERT_NO.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerShareholderColumn.CERT_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.CERT_NO.getValue()));

            // 股东类型
            map.put(CreepersConstant.TCreepersMerShareholderColumn.SHARE_TYPE.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerShareholderColumn.SHARE_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.SHARE_TYPE.getValue()));

            // 详情
            map.put(CreepersConstant.TCreepersMerShareholderColumn.MEMO.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersMerShareholderColumn.MEMO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.MEMO.getValue()));
            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerShareholderColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.MER_NO.getValue()));
            list.add(map);

        }
        logger.info("======>>   end   method1Shareholder:");

    }

    // 变更信息
    private void method1ChangeInfo(Selectable tableSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   method1ChangeInfo:");
        List<Selectable> trList = tableSelectable.xpath(XPATH_NAME_TR_BG).nodes();
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_CHANGE.getMapKey());
        for (Selectable eachTr : trList) {

            // logger.info(eachTr.toString());
            List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 4) {
                logger.info("=============>>>当前行不足4个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();
            // 变更事项
            map.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_ITEM.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerChangeColumn.CHANGE_ITEM.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.CHANGE_ITEM.getValue()));

            // 变更前内容
            map.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_OLD.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerChangeColumn.CHANGE_OLD.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.CHANGE_OLD.getValue()));

            // 变更后内容
            map.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_NEW.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerChangeColumn.CHANGE_NEW.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.CHANGE_NEW.getValue()));

            // 变更日期
            if (StringUtil.isNotBlank(tdList.get(3).toString())) {
                map.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_DT.getValue(), tdList.get(3).toString());
                logger.info(CreepersConstant.TCreepersMerChangeColumn.CHANGE_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.CHANGE_DT.getValue()));
            }
            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerChangeColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.MER_NO.getValue()));
            list.add(map);

        }
        logger.info("======>>   end   method1ChangeInfo");

    }

    // 备案信息 id:2
    private void method2(Selectable divSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("entry method2!");
        // 主要人员信息
        method2KeyMan(divSelectable.xpath(XPATH_ID_TABLE_RY1), resultItems, merNoMap);
        // 分支机构信息
        method2Branch(divSelectable.xpath(XPATH_ID_TABLE_FR2), resultItems, merNoMap);
        logger.info("finish method2!");
    }

    // 主要人员信息
    private void method2KeyMan(Selectable tableSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("entry  method2KeyMan!");
        List<Selectable> tdList = tableSelectable.xpath(XPATH_NAME_TR_RY1 + XPATH_TD_ALLTEXT).nodes();
        Map<String, String> map = new HashMap<String, String>();
        map.putAll(merNoMap);
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_KEYMAN.getMapKey());
        int rowNum = 0;
        for (Selectable eachTd : tdList) {
            logger.info("current content:" + eachTd.toString());
            rowNum++;
            if (rowNum == 1) {
                // 序号
                map.put(CreepersConstant.TCreepersMerKeymanColumn.SEQ_NO.getValue(), eachTd.toString());
                logger.info(CreepersConstant.TCreepersMerKeymanColumn.SEQ_NO.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerKeymanColumn.SEQ_NO.getValue()));
            } else if (rowNum == 2) {
                // 姓名
                map.put(CreepersConstant.TCreepersMerKeymanColumn.NAME.getValue(), eachTd.toString());
                logger.info(CreepersConstant.TCreepersMerKeymanColumn.NAME.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerKeymanColumn.NAME.getValue()));
            } else if (rowNum == 3) {
                // 职务
                map.put(CreepersConstant.TCreepersMerKeymanColumn.POSITION.getValue(), eachTd.toString());
                logger.info(CreepersConstant.TCreepersMerKeymanColumn.POSITION.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerKeymanColumn.POSITION.getValue()));

                logger.info(CreepersConstant.TCreepersMerKeymanColumn.MER_NO.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerKeymanColumn.MER_NO.getValue()));

                Map<String, String> temp = new HashMap<String, String>();
                temp.putAll(map);
                list.add(temp);
                rowNum = 0;
            }
        }
        logger.info("finish  method2KeyMan!");
    }

    // 分支机构信息
    private void method2Branch(Selectable selectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("entry  method2Branch!");
        List<Selectable> trList = selectable.xpath(XPATH_TR).nodes();
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_BRANCH.getMapKey());
        int rowNum = 1;
        for (Selectable eachTr : trList) {
            if (rowNum < 3) {
                rowNum++;
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();
            List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 4) {
                logger.info("=============>>>当前行不足4个td 无法解析:" + tdList);
                continue;
            }
            // 序号
            map.put(CreepersConstant.TCreepersMerBranchColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerBranchColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerBranchColumn.SEQ_NO.getValue()));

            // 注册号/统一社会信用代码
            map.put(CreepersConstant.TCreepersMerBranchColumn.REG_NO.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerBranchColumn.REG_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerBranchColumn.REG_NO.getValue()));

            // 名称
            map.put(CreepersConstant.TCreepersMerBranchColumn.NAME.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerBranchColumn.NAME.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerBranchColumn.NAME.getValue()));

            // 登记机关
            map.put(CreepersConstant.TCreepersMerBranchColumn.REG_AUTHORITY.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerBranchColumn.REG_AUTHORITY.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerBranchColumn.REG_AUTHORITY.getValue()));

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerBranchColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerBranchColumn.MER_NO.getValue()));

            list.add(map);
        }
        logger.info("finish  method2Branch!");
    }

    // 行政处罚信息 id:3
    private void method3(Selectable selectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("entry method3!");
        Selectable tableSelectable = selectable.xpath(XPATH_ID_TABLE_GSCFXX);
        List<Selectable> trList = tableSelectable.xpath(XPATH_NAME_TR_GSCFXX).nodes();
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PENALTY.getMapKey());
        for (Selectable eachTr : trList) {
            List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 8) {
                logger.info("=============>>>当前行不足8个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();
            // 序号
            map.put(CreepersConstant.TCreepersMerPenaltyColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.SEQ_NO.getValue()));

            // 行政处罚决定书文号
            map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_NO.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_NO.getValue()));

            // 违法行为类型
            map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_TYPE.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_TYPE.getValue()));

            // 行政处罚内容
            map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_CONTENT.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_CONTENT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_CONTENT.getValue()));

            // 作出行政处罚决定机关名称
            map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_AUTHORITY.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_AUTHORITY.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_AUTHORITY.getValue()));

            // 作出行政处罚决定日期
            if (StringUtil.isNotBlank(tdList.get(5).toString())) {
                map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_DT.getValue(), tdList.get(5).toString());
                logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_DT.getValue()));
            }
            // 公示日期
            if (StringUtil.isNotBlank(tdList.get(6).toString())) {
                map.put(CreepersConstant.TCreepersMerPenaltyColumn.MEMO.getValue(), tdList.get(6).toString());
                logger.info(CreepersConstant.TCreepersMerPenaltyColumn.MEMO.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.MEMO.getValue()));
            }
            // 详情
            String detail = eachTr.xpath(XPATH_TD).nodes().get(7).xpath(XPATH_A_ONCLICK).toString();
            String detailURL = method3GetPenaltyDetailURL(detail);
            map.put(CreepersConstant.TCreepersMerPenaltyColumn.DETAILS.getValue(), detailURL);
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.DETAILS.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.DETAILS.getValue()));

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("finish method3!");
    }

    private String method3GetPenaltyDetailURL(String detail) {
        logger.info("entry getPenaltyDetailURL!");
        String[] arrStr = detail.replace(REPLACE_FUNCTION_DO_XZFY_DETAIL + SPILT_APOSTROPHE, CONTENT_EMPTY)
                .replace(SPILT_APOSTROPHE + REPLACE_FUNCTION_END, CONTENT_EMPTY).split(SPILT_COMMA);
        StringBuffer result = new StringBuffer();
        result.append(REGEX_URL_BASE);
        result.append(APPEND_URL_METHOD_DO_XZFY_DETAIL);
        result.append(arrStr[0]);
        result.append(APPEND_URL_MAENT_XH);
        result.append(arrStr[1]);
        result.append(APPEND_URL_RANDOM);
        result.append(new Date().getTime());
        logger.info("finish getPenaltyDetailURL!");
        return result.toString();
    }

    // 股权出质登记信息 id:4
    private void method4(Selectable selectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("entry method4!");
        List<Selectable> trList = selectable.xpath(XPATH_ID_TABLE_GQ + XPATH_TR).nodes();
        int trNum = 1;
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PLEDGE.getMapKey());
        for (Selectable tr : trList) {
            if (trNum < 3) {
                trNum++;
                continue;
            }
            List<Selectable> tdList = tr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 11) {
                logger.info("=============>>>当前行不足11个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();
            // 序号
            map.put(CreepersConstant.TCreepersMerPledgeColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.SEQ_NO.getValue()));
            // 登记编号
            map.put(CreepersConstant.TCreepersMerPledgeColumn.REG_NO.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.REG_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.REG_NO.getValue()));
            // 出质人
            map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER.getValue()));
            // 证照/证件号码
            map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER_CERT_NO.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER_CERT_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER_CERT_NO.getValue()));
            // 出质股权数额
            map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_AMOUNT.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_AMOUNT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_AMOUNT.getValue()));
            // 质权人
            map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE.getValue()));
            // 证照/证件号码
            map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE_CERT_NO.getValue(), tdList.get(6).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE_CERT_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE_CERT_NO.getValue()));
            // 股权出质设立登记日期
            if (StringUtil.isNotBlank(tdList.get(7).toString())) {
                map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_DT.getValue(), tdList.get(7).toString());
                logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_DT.getValue()));
            }
            // 状态
            map.put(CreepersConstant.TCreepersMerPledgeColumn.STATUS.getValue(), tdList.get(8).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.STATUS.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.STATUS.getValue()));
            // 公示日期
            if (StringUtil.isNotBlank(tdList.get(9).toString())) {
                map.put(CreepersConstant.TCreepersMerPledgeColumn.MEMO.getValue(), tdList.get(9).toString());
                logger.info(CreepersConstant.TCreepersMerPledgeColumn.MEMO.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.MEMO.getValue()));
            }
            // 变化情况
            map.put(CreepersConstant.TCreepersMerPledgeColumn.CHANGE_INFO.getValue(), tdList.get(10).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.CHANGE_INFO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.CHANGE_INFO.getValue()));
            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("finish method4!");
    }

    // 动产抵押登记信息 id:5
    private void method5(Selectable divSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("entry method5!");
        Selectable tableSelectable = divSelectable.xpath(XPATH_ID_TABLE_DC);
        List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
        int trNum = 1;
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PROPERTY.getMapKey());
        for (Selectable tr : trList) {
            if (trNum < 3) {
                trNum++;
                continue;
            }
            List<Selectable> tdList = tr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 8) {
                logger.info("=============>>>当前行不足8个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();
            // 序号
            map.put(CreepersConstant.TCreepersMerPropertyColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.SEQ_NO.getValue()));
            // 登记编号
            map.put(CreepersConstant.TCreepersMerPropertyColumn.REG_NO.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.REG_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.REG_NO.getValue()));
            // 登记日期
            if (StringUtil.isNotBlank(tdList.get(2).toString())) {
                map.put(CreepersConstant.TCreepersMerPropertyColumn.REG_DT.getValue(), tdList.get(2).toString());
                logger.info(CreepersConstant.TCreepersMerPropertyColumn.REG_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.REG_DT.getValue()));
            }
            // 登记机关
            map.put(CreepersConstant.TCreepersMerPropertyColumn.REG_AUTHORITY.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.REG_AUTHORITY.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.REG_AUTHORITY.getValue()));
            // 被担保债权数额
            map.put(CreepersConstant.TCreepersMerPropertyColumn.CLAIM_AMOUNT.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.CLAIM_AMOUNT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.CLAIM_AMOUNT.getValue()));
            // 状态
            map.put(CreepersConstant.TCreepersMerPropertyColumn.STATUS.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.STATUS.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.STATUS.getValue()));
            // 公示日期
            if (StringUtil.isNotBlank(tdList.get(6).toString())) {
                map.put(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue(), tdList.get(6).toString());
                logger.info(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue()));
            }
            // 详情
            map.put(CreepersConstant.TCreepersMerPropertyColumn.DETAILS.getValue(),
                    method5GetMerPropertyDetailURL(map));
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.DETAILS.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.DETAILS.getValue()));
            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("entry method5!");
    }

    private static String method5GetMerPropertyDetailURL(Map<String, String> map) {
        StringBuffer result = new StringBuffer();
        result.append(REGEX_URL_BASE);
        result.append(APPEND_URL_METHOD_DCDY_DETAIL);
        result.append(map.get(CreepersConstant.TCreepersListColumn.MER_NO.getValue()));
        result.append(APPEND_URL_MAENT_XH);
        result.append(map.get(CreepersConstant.TCreepersMerPropertyColumn.REG_NO.getValue()));
        result.append(APPEND_URL_RANDOM);
        result.append(new Date().getTime());
        return result.toString();
    }

    // 经营异常信息 id:6
    private void method6(Selectable divSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("entry method6!");
        Selectable tableSelectable = divSelectable.xpath(XPATH_ID_TABLE_YC);
        List<Selectable> trList = tableSelectable.xpath(XPATH_NAME_TR_YC).nodes();
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_ABNORMAL.getMapKey());
        for (Selectable eachTr : trList) {
            List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 5) {
                logger.info("=============>>>当前行不足5个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();
            // 序号
            map.put(CreepersConstant.TCreepersMerAbnormalColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerAbnormalColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.SEQ_NO.getValue()));

            // 列入经营异常名录原因
            map.put(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_REASON.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_REASON.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_REASON.getValue()));

            // 列入日期
            if (StringUtil.isNotBlank(tdList.get(3).toString())) {
                map.put(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_DT.getValue(), tdList.get(2).toString());
                logger.info(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_DT.getValue()));
            }

            // 移出经营异常名录原因
            map.put(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_REASON.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_REASON.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_REASON.getValue()));

            // 移出日期
            if (StringUtil.isNotBlank(tdList.get(4).toString())) {
                map.put(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_DT.getValue(), tdList.get(4).toString());
                logger.info(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_DT.getValue()));
            }
            // 作出决定机关
            map.put(CreepersConstant.TCreepersMerAbnormalColumn.AUTHORITY.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersMerAbnormalColumn.AUTHORITY.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.AUTHORITY.getValue()));

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerAbnormalColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.MER_NO.getValue()));

            list.add(map);
        }
        logger.info("finish method6!");
    }

    // 抽查检查信息 id:7
    private void method7(Selectable divSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("entry method7!");
        Selectable tableSelectable = divSelectable.xpath(XPATH_ID_TABLE_CCJC);
        List<Selectable> trList = tableSelectable.xpath(XPATH_NAME_TR_CCJC).nodes();
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_CHECK.getMapKey());

        for (Selectable eachTr : trList) {
            List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 5) {
                logger.info("=============>>>当前行不足5个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();
            // 序号
            map.put(CreepersConstant.TCreepersMerCheckColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerCheckColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.SEQ_NO.getValue()));

            // 检查实施机关
            map.put(CreepersConstant.TCreepersMerCheckColumn.AUTHORITY.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerCheckColumn.AUTHORITY.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.AUTHORITY.getValue()));

            // 类型
            map.put(CreepersConstant.TCreepersMerCheckColumn.CHECK_TYPE.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerCheckColumn.CHECK_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.CHECK_TYPE.getValue()));

            // 日期
            if (StringUtil.isNotBlank(tdList.get(3).toString())) {
                map.put(CreepersConstant.TCreepersMerCheckColumn.CHECK_DT.getValue(), tdList.get(3).toString());
                logger.info(CreepersConstant.TCreepersMerCheckColumn.CHECK_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.CHECK_DT.getValue()));
            }
            // 结果
            map.put(CreepersConstant.TCreepersMerCheckColumn.CHECK_RESULT.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersMerCheckColumn.CHECK_RESULT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.CHECK_RESULT.getValue()));

            // 工商注册信息
            map.putAll(merNoMap);

            list.add(map);
        }
        logger.info("finish method7!");
    }

    // 严重违法信息 id:8
    private void method8(Selectable divSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        Selectable tableSelectable = divSelectable.xpath(XPATH_ID_TABLE_WFXX);
        List<Selectable> trList = tableSelectable.xpath(XPATH_NAME_TR_WFXX).nodes();
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_ILLEGAL.getMapKey());
        for (Selectable eachTr : trList) {
            List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 6) {
                logger.info("=============>>>当前行不足6个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();
            // 序号
            map.put(CreepersConstant.TCreepersMerIllegalColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerIllegalColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.SEQ_NO.getValue()));

            // 列入严重违法企业名单原因
            map.put(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_REASON.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_REASON.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_REASON.getValue()));

            // 列入日期
            if (StringUtil.isNotBlank(tdList.get(2).toString())) {
                map.put(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_DT.getValue(), tdList.get(2).toString());
                logger.info(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_DT.getValue()));
            }
            // 移出严重违法企业名单原因
            map.put(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_REASON.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_REASON.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_REASON.getValue()));

            // 移出日期
            if (StringUtil.isNotBlank(tdList.get(4).toString())) {
                map.put(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_DT.getValue(), tdList.get(4).toString());
                logger.info(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_DT.getValue()));
            }

            // 作出决定机关
            map.put(CreepersConstant.TCreepersMerIllegalColumn.AUTHORITY.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersMerIllegalColumn.AUTHORITY.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.AUTHORITY.getValue()));

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerIllegalColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.MER_NO.getValue()));

            list.add(map);
        }
    }

    // 企业年报 id:qygs_qynb
    private void methodQygsQynb(Selectable divSelectable, Page page, Map<String, String> merNoMap) {
        logger.info("entry methodQygs_qynb!");
        Selectable tableSelectable = divSelectable.xpath(XPATH_TABLE);
        List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
        int trNum = 1;
        for (Selectable eachTr : trList) {
            if (trNum < 3) {
                trNum++;
                continue;
            }
            List<Selectable> tdList = eachTr.xpath(XPATH_TD).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 3) {
                logger.info("=============>>>当前行不足5个td 无法解析:" + tdList);
                continue;
            }
            // 序号
            logger.info("序号：" + tdList.get(0).toString());
            // 报送年度
            String ndbgParam = tdList.get(1).xpath(XPATH_A_ONCLICK).toString();
            // 年度报告URL
            String ndbgUrl = methodQygsQynbGetNdbgUrl(ndbgParam, page.getUrl().toString());
            logger.info("报送年度：" + ndbgUrl);
            page.addTargetRequest(ndbgUrl);
            // 发布日期
            logger.info("发布日期：" + tdList.get(2).toString());
        }
        logger.info("finish methodQygs_qynb!");
    }

    // 年度报告URL
    private String methodQygsQynbGetNdbgUrl(String ndbgParam, String pageUrl) {
        String param = ndbgParam.replace(REPLACE_FUNCTION_DO_NDBG + SPILT_APOSTROPHE, CONTENT_EMPTY)
                .replace(SPILT_APOSTROPHE + REPLACE_FUNCTION_END, CONTENT_EMPTY);
        StringBuffer result = new StringBuffer();
        result.append(REGEX_URL_BASE);
        result.append(APPEND_URL_METHOD_NDBG_DETAIL);
        result.append(CommonMethodUtils.matchesValue(REGEX_URL_GET_MAENT_PRIPID,
                pageUrl.replace(SPILT_QUESTION, REPLACE_QUESTION)));
        result.append(APPEND_URL_MAENT_ND);
        result.append(param);
        result.append(APPEND_URL_RANDOM);
        result.append(new Date().getTime());
        return result.toString();
    }

    // 股权变更信息
    private void methodQygsForTzrbgxxInfo(Selectable divSelectable, ResultItems resultItems,
            Map<String, String> merNoMap) {
        // XPATH_ID_TABLE_TZRBGXX
        logger.info("entry methodQygsForTzrbgxxInfo!");
        Selectable tableSelectable = divSelectable.xpath(XPATH_ID_TABLE_TZRBGXX);
        List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_EQUITY.getMapKey());
        int trNum = 1;
        for (Selectable eachTr : trList) {
            if (trNum < 3) {
                trNum++;
                continue;
            }
            List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 6) {
                logger.info("=============>>>当前行不足6个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();

            // 序号
            // map.put(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue(),
            // tdList.get(0).toString());
            // logger.info(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue()
            // + SPILT_COLON +
            // map.get(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue()));

            // 股东
            map.put(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue()));

            // 变更前股权比例
            map.put(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue()));

            // 变更后股权比例
            map.put(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue()));

            // 股权变更日期
            if (StringUtil.isNotBlank(tdList.get(4).toString())) {
                map.put(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue(), tdList.get(4).toString());
                logger.info(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue()));
            }

            // 公示日期
            if (StringUtil.isNotBlank(tdList.get(5).toString())) {
                map.put(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue(), tdList.get(5).toString());
                logger.info(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue()));
            }

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("finish methodQygsForTzrbgxxInfo!");

    }

    // 股东及出资信息
    private void methodQygsForTzrxxInfo(Selectable divSelectable, ResultItems resultItems,
            Map<String, String> merNoMap) {
        // XPATH_ID_TABLE_TZRBGXX
        logger.info("entry methodQygsForTzrxxInfo!");

        // 股东及出资信息明细
        methodQygsForTzrxxInfoDetail(divSelectable.xpath(XPATH_ID_TABLE_QYTZR), resultItems, merNoMap);

        // 股权变更信息
        methodQygsForTzrxxInfoChange(divSelectable.xpath(XPATH_ID_TABLE_TZRXXBG), resultItems, merNoMap);

        logger.info("finish methodQygsForTzrxxInfo!");
    }

    // 股东及出资信息明细
    private void methodQygsForTzrxxInfoDetail(Selectable tableSelectable, ResultItems resultItems,
            Map<String, String> merNoMap) {
        logger.info("entry methodQygsForTzrxxInfoDetail!");
        List<Selectable> trList = tableSelectable.xpath(XPATH_NAME_TR_QYTZR).nodes();
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_SHARE.getMapKey());
        for (Selectable eachTr : trList) {
            List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 11) {
                logger.info("=============>>>当前行不足11个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();

            // 股东 T_CREEPERS_ENT_SHARE
            map.put(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue()));

            // 认缴额（万元）
            map.put(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue()));

            // 实缴额（万元）
            map.put(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue()));

            // 认缴出资方式
            map.put(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue()));

            // 认缴出资额（万元）
            map.put(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue()));

            // 认缴出资日期
            if (StringUtil.isNotBlank(tdList.get(5).toString())) {
                map.put(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue(), tdList.get(5).toString());
                logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()));
            }

            // 公示日期
            if (StringUtil.isNotBlank(tdList.get(6).toString())) {
                // map.put(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue(),
                // tdList.get(6).toString());
                logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()));
            }

            // 实缴出资方式
            map.put(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue(), tdList.get(7).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue()));

            // 实缴出资额（万元）
            map.put(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue(), tdList.get(8).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue()));

            // 实缴出资日期
            if (StringUtil.isNotBlank(tdList.get(9).toString())) {
                map.put(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue(), tdList.get(9).toString());
                logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()));
            }

            // 公示日期
            if (StringUtil.isNotBlank(tdList.get(10).toString())) {
                // map.put(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue(),
                // tdList.get(10).toString());
                logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()));
            }

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("finish methodQygsForTzrxxInfoDetail!");

    }

    // 股权变更信息
    private void methodQygsForTzrxxInfoChange(Selectable tableSelectable, ResultItems resultItems,
            Map<String, String> merNoMap) {
        logger.info("entry methodQygsForTzrxxInfoDetail!");
        List<Selectable> trList = tableSelectable.xpath(XPATH_NAME_TR_TZRXXBG).nodes();
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_SHARE_CHANGE.getMapKey());
        for (Selectable eachTr : trList) {
            List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 5) {
                logger.info("=============>>>当前行不足5个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();

            // 序号
            map.put(CreepersConstant.TCreepersEntShareChangeColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntShareChangeColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.SEQ_NO.getValue()));
            list.add(map);

            // 变更事项
            map.put(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_CONTENT.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_CONTENT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_CONTENT.getValue()));
            list.add(map);

            // 变更时间
            if (StringUtil.isNotBlank(tdList.get(10).toString())) {
                map.put(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_DT.getValue(), tdList.get(2).toString());
                logger.info(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_DT.getValue()));
            }
            // 变更前
            map.put(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_OLD.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_OLD.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_OLD.getValue()));

            // 变更后
            map.put(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_NEW.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_NEW.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_NEW.getValue()));

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersEntShareChangeColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("finish methodQygsForTzrxxInfoDetail!");

    }

    // 年度报告:
    private void methodNdbg(Selectable divSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("entry methodNdbg!");
        // 企业基本信息
        entNdbgBasic(divSelectable.xpath(XPATH_TABLE).nodes().get(0), resultItems, merNoMap);

        // 网站或网店信息
        methodNdbgWeb(divSelectable.xpath(XPATH_ID_TABLE_WZXX), resultItems, merNoMap);

        // 股东及出资信息
        methodNdbgShare(divSelectable.xpath(XPATH_ID_TABLE_TZRXX), resultItems, merNoMap);

        // 对外投资信息
        methodNdbgInvest(divSelectable.xpath(XPATH_ID_TABLE_TZXX), resultItems, merNoMap);

        // 企业资产状况信息 
        entNdbgAsset(divSelectable.xpath(XPATH_TABLE).nodes().get(4), resultItems, merNoMap);

        // 对外提供保证担保信息
        methodNdbgWarrant(divSelectable.xpath(XPATH_ID_TABLE_DBXX), resultItems, merNoMap);

        // 股权变更信息
        methodNdbgEquity(divSelectable.xpath(XPATH_ID_TABLE_GQBG), resultItems, merNoMap);

        // 修改记录
        methodNdbgChange(divSelectable.xpath(XPATH_ID_TABLE_BGXX), resultItems, merNoMap);
        logger.info("finish methodNdbg!");
    }

    // 网站或网店信息
    private void methodNdbgWeb(Selectable tableSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   methodNdbgWeb:");
        List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_WEB.getMapKey());
        int trNum = 1;
        for (Selectable eachTr : trList) {
            if (trNum < 3) {
                trNum++;
                continue;
            }
            List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 3) {
                logger.info("=============>>>当前行不足3个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();

            // 类型
            map.put(CreepersConstant.TCreepersEntWebColumn.TYPE.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntWebColumn.TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.TYPE.getValue()));

            // 名称
            map.put(CreepersConstant.TCreepersEntWebColumn.NAME.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntWebColumn.NAME.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.NAME.getValue()));

            // 网址
            map.put(CreepersConstant.TCreepersEntWebColumn.URL.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersEntWebColumn.URL.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.URL.getValue()));

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersEntWebColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("======>>   finish   methodNdbgWeb:");

    }

    // 股东及出资信息
    private void methodNdbgShare(Selectable tableSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   methodNdbgShare:");
        List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_SHARE.getMapKey());
        int trNum = 1;
        for (Selectable eachTr : trList) {
            if (trNum < 3) {
                trNum++;
                continue;
            }
            List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 7) {
                logger.info("=============>>>当前行不足7个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();

            // 股东
            map.put(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue()));

            // 认缴出资额（万元）
            map.put(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue()));

            // 认缴出资时间
            if (StringUtil.isNotBlank(tdList.get(2).toString())) {
                map.put(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue(), tdList.get(2).toString());
                logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()));
            }

            // 认缴出资方式
            map.put(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue()));

            // 实缴出资额（万元）
            map.put(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue()));

            // 实缴出资时间
            if (StringUtil.isNotBlank(tdList.get(5).toString())) {
                map.put(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue(), tdList.get(5).toString());
                logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()));
            }

            // 实缴出资方式
            map.put(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue(), tdList.get(6).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue()));

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersEntWebColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("======>>   finish   methodNdbgShare:");
    }

    // 对外投资信息
    private void methodNdbgInvest(Selectable tableSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   methodNdbgInvest:");
        List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_INVEST.getMapKey());
        int trNum = 1;
        for (Selectable eachTr : trList) {
            if (trNum < 3) {
                trNum++;
                continue;
            }
            List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 2) {
                logger.info("=============>>>当前行不足2个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();

            // 投资设立企业或购买股权企业名称
            map.put(CreepersConstant.TCreepersEntInvestColumn.INVESTED_NAME.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntInvestColumn.INVESTED_NAME.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.INVESTED_NAME.getValue()));

            // 注册号/统一社会信用代码
            map.put(CreepersConstant.TCreepersEntInvestColumn.MEMO.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntInvestColumn.MEMO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.MEMO.getValue()));

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue()));
            list.add(map);
        }

        logger.info("======>>   finish   methodNdbgInvest:");

    }

    // 对外提供保证担保信息
    private void methodNdbgWarrant(Selectable tableSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   methodNdbgWarrant:");
        List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_WARRANT.getMapKey());
        int trNum = 1;
        for (Selectable eachTr : trList) {
            if (trNum < 3) {
                trNum++;
                continue;
            }
            List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 7) {
                logger.info("=============>>>当前行不足7个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();

            // 债权人
            map.put(CreepersConstant.TCreepersEntWarrantColumn.CREDITOR.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.CREDITOR.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.CREDITOR.getValue()));

            // 债务人
            map.put(CreepersConstant.TCreepersEntWarrantColumn.OBLIGOR.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.OBLIGOR.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.OBLIGOR.getValue()));

            // 主债权种类
            map.put(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_TYPE.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_TYPE.getValue()));

            // 主债权数额
            map.put(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_AMOUNT.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_AMOUNT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_AMOUNT.getValue()));

            // 履行债务的期限
            map.put(CreepersConstant.TCreepersEntWarrantColumn.DEBT_PERIOD.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.DEBT_PERIOD.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.DEBT_PERIOD.getValue()));

            // 保证的期间
            map.put(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_PERIOD.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_PERIOD.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_PERIOD.getValue()));

            // 保证的方式
            map.put(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_TYPE.getValue(), tdList.get(7).toString());
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_TYPE.getValue()));

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("======>>   finish   methodNdbgWarrant:");

    }

    // 股权变更信息
    private void methodNdbgEquity(Selectable tableSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   methodNdbgEquity:");
        List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_EQUITY.getMapKey());
        int trNum = 1;
        for (Selectable eachTr : trList) {
            if (trNum < 3) {
                trNum++;
                continue;
            }
            List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 4) {
                logger.info("=============>>>当前行不足4个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();

            // 股东
            map.put(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue()));

            // 变更前股权比例
            map.put(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue()));

            // 变更后股权比例
            map.put(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue()));

            // 股权变更日期
            if (StringUtil.isNotBlank(tdList.get(3).toString())) {
                map.put(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue(), tdList.get(3).toString());
                logger.info(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue()));
            }
            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("======>>   finish   methodNdbgEquity:");

    }

    // 修改记录
    private void methodNdbgChange(Selectable tableSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   methodNdbgChange:");
        List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_CHANGE.getMapKey());
        int trNum = 1;
        for (Selectable eachTr : trList) {
            if (trNum < 3) {
                trNum++;
                continue;
            }
            List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
            if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 5) {
                logger.info("=============>>>当前行不足5个td 无法解析:" + tdList);
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();

            // 序号
            map.put(CreepersConstant.TCreepersEntChangeColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntChangeColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.SEQ_NO.getValue()));

            // 修改事项
            map.put(CreepersConstant.TCreepersEntChangeColumn.ITEM.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntChangeColumn.ITEM.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.ITEM.getValue()));

            // 修改前
            if (tdList.get(2).toString().contains(CONTENT_MORE)) {
                map.put(CreepersConstant.TCreepersEntChangeColumn.PRE_INFO.getValue(),
                        eachTr.xpath(XPATH_TD).nodes().get(2).xpath(XPATH_SPAN + XPATH_ALL_TEXT).nodes().get(1)
                                .toString().replace(CONTENT_MORE, CONTENT_EMPTY));
            } else {
                map.put(CreepersConstant.TCreepersEntChangeColumn.PRE_INFO.getValue(), tdList.get(2).toString());
            }
            logger.info(CreepersConstant.TCreepersEntChangeColumn.PRE_INFO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.PRE_INFO.getValue()));

            // 修改后
            if (tdList.get(3).toString().contains(CONTENT_MORE)) {
                map.put(CreepersConstant.TCreepersEntChangeColumn.POST_INFO.getValue(),
                        eachTr.xpath(XPATH_TD).nodes().get(3).xpath(XPATH_SPAN + XPATH_ALL_TEXT).nodes().get(1)
                                .toString().replace(CONTENT_MORE, CONTENT_EMPTY));
            } else {
                map.put(CreepersConstant.TCreepersEntChangeColumn.POST_INFO.getValue(), tdList.get(3).toString());
            }
            logger.info(CreepersConstant.TCreepersEntChangeColumn.POST_INFO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.POST_INFO.getValue()));

            // 修改日期
            if (StringUtil.isNotBlank(tdList.get(4).toString())) {
                map.put(CreepersConstant.TCreepersEntChangeColumn.CHANGE_DT.getValue(), tdList.get(4).toString());
                logger.info(CreepersConstant.TCreepersEntChangeColumn.CHANGE_DT.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.CHANGE_DT.getValue()));
            }

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersEntChangeColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("======>>   finish   methodNdbgChange:");

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new BusinessInfoDetail51Processor()).addPipeline(new BusinessInfoDetailPipline())
                // 股权出质
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=gqczxxInfo&maent.pripid=CD6675400159&czmk=czmk4&random="
                // + new Date().getTime())
                // 动产质押
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=dcdyInfo&maent.pripid=5106810222229093&czmk=czmk4&random="
                // + new Date().getTime())
                // 基本信息
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=qyInfo&maent.pripid=CD6675400159&czmk=czmk1&from=&random="
                // + new Date().getTime())
                // 备案信息
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=baInfo&maent.pripid=CD6675400159&czmk=czmk2&from=&random="
                // + new Date().getTime())
                // 行政处罚
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=cfInfo&maent.pripid=5115240000000052&czmk=czmk3&from=&random="
                // + new Date().getTime())
                // 严重违法记录
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=yzwfInfo&maent.pripid=5115240000000052&czmk=czmk14&from=&random="
                // + new Date().getTime())
                // 抽查信息
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=ccjcInfo&maent.pripid=5117023000061844&czmk=czmk7&from=&random="
                // + new Date().getTime())
                // 企业年报
                // .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=qygsInfo&maent.pripid=CD6675400159&czmk=czmk8&random="
                // + new Date().getTime())
                // 年报详情
                .addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=ndbgDetail&maent.pripid=CD6675400159&maent.nd=2015&random="
                        + new Date().getTime())
                .thread(1).runAsync();
    }
}
