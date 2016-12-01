package com.fosun.fc.projects.creepers.pageprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.nlpcn.commons.lang.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant.TableNamesBusinessInfo;
import com.fosun.fc.projects.creepers.pipeline.BusinessInfoDetailPipline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 
 * <p>
 * Demo: 河南省工商信息明细
 * </p>
 * 
 * @author LiZhanPing
 * @since 2016年7月25日
 * @see
 */
@Component("businessInfoDetail41Processor")
public class BusinessInfoDetail41Processor extends BaseBusinessDetailProcessor implements PageProcessor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Site site;

    @Override
    public Site getSite() {
        return site;
    }

    public BusinessInfoDetail41Processor() {
        if (null == site) {
            site = Site.me().setSleepTime(1000).setTimeOut(100000);
        }
    }

    // 工商公示信息表
    private static final String XPATH_ID_DIV_JI_BEN_XIN_XI = "//*[@id=\"jibenxinxi\"]";
    private static final String XPATH_ID_DIV_BEI_AN = "//*[@id=\"beian\"]";
    private static final String XPATH_ID_DIV_DONG_CHAN_DI_YA = "//*[@id=\"dongchandiya\"]";
    private static final String XPATH_ID_DIV_GU_QUAN_CHU_ZHI = "//*[@id=\"guquanchuzhi\"]";
    private static final String XPATH_ID_DIV_XING_ZHENG_CHU_FA = "//*[@id=\"xingzhengchufa\"]";
    private static final String XPATH_ID_DIV_JING_YING_YI_CHANG_MINGLU = "//*[@id=\"jingyingyichangminglu\"]";
    private static final String XPATH_ID_DIV_YAN_ZHONG_WEI_FA_QI_YE = "//*[@id=\"yanzhongweifaqiye\"]";
    private static final String XPATH_ID_DIV_CHOU_CHA_XIN_XI = "//*[@id=\"chouchaxinxi\"]";
    // 企业公示信息表
    private static final String XPATH_ID_DIV_QI_YE_NIAN_BAO = "//*[@id=\"qiyenianbao\"]";
    private static final String XPATH_ID_DIV_SI_FA_PAN_DING = "//*[@id=\"sifapanding\"]";
    private static final String XPATH_ID_DIV_TOU_ZI_REN = "//*[@id=\"touziren\"]";
    private static final String XPATH_ID_DIV_GU_DONG_GU_QUAN = "//*[@id=\"gudongguquan\"]";
    private static final String XPATH_ID_DIV_GQBG = "//*[@id=\"gqbg\"]";
    // private static final String XPATH_ID_DIV_XING_ZHENG_XU_KE =
    // "//*[@id=\"xingzhengxuke\"]";
    // private static final String XPATH_ID_DIV_ZHI_SHI_CHAN_QUAN =
    // "//*[@id=\"zhishichanquan\"]";
    // private static final String XPATH_ID_DIV_XZCFDIV = "//[@id=\"xzcfDiv\"]";

    private static final String XPATH_PROPERTY_ONCLICK = "//@onclick";
    private static final String XPATH_PROPERTY_HREF = "//@href";
    private static final String XPATH_PROPERTY_ID = "//@id";
    // private static final String XPATH_PROPERTY_STYLE = "//@style";

    private static final String XPATH_DIV = "//div";
    private static final String XPATH_TABLE = "//table";
    private static final String XPATH_TR1 = "//tr[1]";
    private static final String XPATH_TR2 = "//tr[2]";
    private static final String XPATH_TH = "//th";
    private static final String XPATH_A = "//A";
    private static final String XPATH_HTML = "//html()";

    private static final String BASE_URL = "http://222.143.24.157";

    private static final String REGEX_URL_BUSINESS_PUBLICITY = "/businessPublicity.jspx\\?id=\\w+";
    private static final String REGEX_URL_ENTERPRISE_PUBLICITY = "/enterprisePublicity.jspx\\?id=\\w+";
    private static final String REGEX_URL__OTHER_DEPARTMENT = "/otherDepartment.jspx\\?id=\\w+";
    private static final String REGEX_URL_JUSTICE_ASSISTANCE = "/justiceAssistance.jspx\\?id=\\w+";

    private static final String REGEX_URL_PAGINATION_0 = "/QueryInvList.jspx\\?pno=\\d+&mainId=\\w+";
    private static final String REGEX_URL_PAGINATION_1 = "/QueryAltList.jspx\\?pno=\\d+&mainId=\\w+";
    private static final String REGEX_URL_PAGINATION_2 = "/QueryMemList.jspx\\?pno=\\d+&mainId=\\w+";
    private static final String REGEX_URL_PAGINATION_3 = "/QueryChildList.jspx\\?pno=\\d+&mainId=\\w+";
    private static final String REGEX_URL_PAGINATION_4 = "/QueryMortList.jspx\\?pno=\\d+&mainId=\\w+";
    private static final String REGEX_URL_PAGINATION_5 = "/QueryPledgeList.jspx\\?pno=\\d+&mainId=\\w+";
    private static final String REGEX_URL_PAGINATION_6 = "/QueryPunList.jspx\\?pno=\\d+&mainId=\\w+";
    private static final String REGEX_URL_PAGINATION_7 = "/QueryExcList.jspx\\?pno=\\d+&mainId=\\w+";
    private static final String REGEX_URL_PAGINATION_8 = "/QuerySerillList.jspx\\?pno=\\d+&mainId=\\w+";
    private static final String REGEX_URL_PAGINATION_9 = "/QuerySpotCheckList.jspx\\?pno=\\d+&mainId=\\w+";
    private static final String REGEX_URL_PAGINATION_10 = "/QueryYearExamineDetail.jspx\\?id=\\w+&merNo=\\w+";

    private static final String REGEX_NEXT = ",(\\d+),\"next\"";

    private static final String LINK_ENTERPRISE_PUBLICITY = "/enterprisePublicity.jspx?id=";
    private static final String LINK_OTHER_DEPARTMENT = "/otherDepartment.jspx?id=";
    private static final String LINK_JUSTICE_ASSISTANCE = "/justiceAssistance.jspx?id=";
    private static final String LINK_SHARE_HOLDER = "/QueryInvList.jspx?pno=";
    private static final String LINK_CHANGE = "/QueryAltList.jspx?pno=";
    private static final String LINK_KEY_MEN = "/QueryMemList.jspx?pno=";
    private static final String LINK_BRANCH = "/QueryChildList.jspx?pno=";
    private static final String LINK_PROPERTY = "/QueryMortList.jspx?pno=";
    private static final String LINK_PLEDGE = "/QueryPledgeList.jspx?pno=";
    private static final String LINK_PENALTY = "/QueryPunList.jspx?pno=";
    private static final String LINK_ABNORMAL = "/QueryExcList.jspx?pno=";
    private static final String LINK_ILLEGAL = "/QuerySerillList.jspx?pno=";
    private static final String LINK_CHECK = "/QuerySpotCheckList.jspx?pno=";

    private static final String NEXT = "next";
    private static final String INVDIV = "invDiv";
    private static final String ALTDIV = "altDiv";
    private static final String MEMDIV = "memDiv";
    private static final String CHILDDIV = "childDiv";
    private static final String MORTDIV = "mortDiv";
    private static final String PLEDGEDIV = "pledgeDiv";
    private static final String PUNDIV = "punDiv";
    private static final String EXCDIV = "excDiv";
    private static final String ILLEGALDIV = "xingzhengchufa";
    private static final String SPOTCHECKDIV = "spotCheckDiv";
    private static final String GDDIV = "gdDiv";
    private static final String ALTINV = "altInv";

    private static final String BASICINFO = "基本信息";
    private static final String CLEARINFO = "清算信息";
    private static final String ENT_BASIC_INFO = "企业基本信息";
    private static final String ENT_WEB_INFO = "网站或网店信息";
    private static final String ENT_SHARE_INFO = "股东及出资信息";
    private static final String ENT_INVEST_INFO = "对外投资信息";
    private static final String ENT_ASSET_INFO = "企业资产状况信息";
    private static final String ENT_WARRANT_INFO = "对外提供保证担保信息";
    private static final String ENT_EQUITY_INFO = "股权变更信息";
    private static final String ENT_CHANGE_INFO = "修改记录";

    private static final String CONTENT_CREDIT_CODE1 = "注册号/统一社会信用代码";
    private static final String CONTENT_CREDIT_CODE2 = "注册号";
    private static final String CONTENT_NAME = "名称";
    private static final String CONTENT_TYPE = "类型";
    private static final String CONTENT_LEGAL_REPRESENTATIVE1 = "法定代表人";
    private static final String CONTENT_LEGAL_REPRESENTATIVE2 = "投资人";
    private static final String CONTENT_LEGAL_REPRESENTATIVE3 = "经营者";
    private static final String CONTENT_REGISTERED_CAPITAL = "注册资本";
    private static final String CONTENT_CONSTRUCT_DATE1 = "成立日期";
    private static final String CONTENT_CONSTRUCT_DATE2 = "注册日期";
    private static final String CONTENT_ADDRESS1 = "住所";
    private static final String CONTENT_ADDRESS2 = "经营场所";
    private static final String CONTENT_OPERATING_PERIOD_START = "营业期限自";
    private static final String CONTENT_OPERATING_PERIOD_END = "营业期限至";
    private static final String CONTENT_BUSINESS_SCOPE = "经营范围";
    private static final String CONTENT_REGISTRATION_AUTHORITY = "登记机关";
    private static final String CONTENT_AUTHORIZE_DATE = "核准日期";
    private static final String CONTENT_REGISTRATION_STATUS = "登记状态";

    private static final String CONTENT_COMPANY_NAME = "企业名称";
    private static final String CONTENT_COMPANY_PHONE = "企业联系电话";
    private static final String CONTENT_COMPANY_POST_CODE = "邮政编码";
    private static final String CONTENT_COMPANY_ADDR = "企业通信地址";
    private static final String CONTENT_COMPANY_EMAIL = "企业电子邮箱";
    private static final String CONTENT_COMPANY_IS_TRANSFER = "有限责任公司本年度是否发生股东股权转让";
    private static final String CONTENT_COMPANY_OPERATING_STATUS = "企业经营状态";
    private static final String CONTENT_COMPANY_IS_WEBSITE = "是否有网站或网店";
    private static final String CONTENT_COMPANY_IS_INVEST = "是否有投资信息或购买其他公司股权";
    private static final String CONTENT_COMPANY_CREW_NUMBER = "从业人数";

    private static final String CONTENT_COMPANY_TOTAL_ASSET = "资产总额";
    private static final String CONTENT_COMPANY_TOTAL_EQUITY = "所有者权益合计";
    private static final String CONTENT_COMPANY_TOTAL_AVENUE = "利润总额";
    private static final String CONTENT_COMPANY_TOTAL_INCOME = "营业总收入";
    private static final String CONTENT_COMPANY_TOTAL_BUS_INCOME = "营业总收入中主营业务收入";
    private static final String CONTENT_COMPANY_NET_PROFIT = "净利润";
    // private static final String CONTENT_COMPANY_TOTAL_TAX = "纳税总额";
    private static final String CONTENT_COMPANY_TOTAL_LIABILITIES = "负债总额";

    @Override
    public void process(Page page) {

        String mainId = page.getUrl().regex("businessPublicity.jspx\\?id=(.*)").toString();

        // *[@id="details"]/h2
        String merNo = "";
        Selectable merNoSel = page.getHtml().xpath("//*[@id=details]/h2");
        if (merNoSel.regex("：([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").match()) {
            merNo = merNoSel.regex("：([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString().trim();
        } else {
            logger.info("================>>页面没有注册号/统一社会信用编码<<================");
        }

        Map<String, String> urlParaNameAndValue = new LinkedHashMap<String, String>();
        urlParaNameAndValue.put("mainId", mainId);
        // 分页没有注册号/统一社会编码
        urlParaNameAndValue.put("merNo", merNo);

        Map<String, String> merNoMap = new HashMap<String, String>();
        merNoMap.put(CreepersConstant.TCreepersListColumn.MER_NO.getValue(), merNo);
        logger.info(CreepersConstant.TCreepersListColumn.MER_NO.getValue()
                + SPILT_COLON + merNoMap.get(CreepersConstant.TCreepersListColumn.MER_NO.getValue()));
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

        if (page.getUrl().regex(BASE_URL + REGEX_URL_BUSINESS_PUBLICITY).match()) {
            logger.info("======================>>>  添加企业公示信息链接");
            page.addTargetRequest(BASE_URL + LINK_ENTERPRISE_PUBLICITY + mainId + "&merNo=" + merNo);
            logger.info("======================>>>  添加其他部门公示信息链接");
            page.addTargetRequest(BASE_URL + LINK_OTHER_DEPARTMENT + mainId + "&merNo=" + merNo);
            logger.info("======================>>>  添加司法协助公示信息链接");
            page.addTargetRequest(BASE_URL + LINK_JUSTICE_ASSISTANCE + mainId + "&merNo=" + merNo);
            logger.info("======================>>>  工商公示信息   start");
            logger.info("======================>>>  登记信息   start");
            // 获取登记信息div的子节点
            List<Selectable> tbDivList = page.getHtml().xpath(XPATH_ID_DIV_JI_BEN_XIN_XI + XPATH_SPILT).nodes();
            if (tbDivList.size() > 0) {
                for (int i = 0; i < tbDivList.size(); i++) {
                    // 判断子节点是否包含指定的id且子节点中的table的tr行数大于0-->tr的数目小于0的话，分页tble不存在
                    if (tbDivList.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(INVDIV).match()
                            && tbDivList.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                        getShareholderInfo(tbDivList.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                        addPaginationLink(page, tbDivList.get(i + 1), BASE_URL + LINK_SHARE_HOLDER,
                                urlParaNameAndValue);
                    } else if (tbDivList.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(ALTDIV).match()
                            && tbDivList.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                        getChangeInfo(tbDivList.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                        addPaginationLink(page, tbDivList.get(i + 1), BASE_URL + LINK_CHANGE, urlParaNameAndValue);
                    } else if (tbDivList.get(i).regex(BASICINFO).match()) {
                        merBaseInfo(tbDivList.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                    }
                }
            }
            logger.info("======================>>>  登记信息   finish");

            logger.info("======================>>>  备案信息   start");
            // 获取备案信息div的子节点
            List<Selectable> tbDivList2 = page.getHtml().xpath(XPATH_ID_DIV_BEI_AN + XPATH_SPILT).nodes();
            if (tbDivList2.size() > 0) {
                for (int i = 0; i < tbDivList2.size(); i++) {
                    // Selectable s = tbDivList2.get(i);
                    if (tbDivList2.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(MEMDIV).match()
                            && tbDivList2.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                        getKeyManInfo(tbDivList2.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                        addPaginationLink(page, tbDivList2.get(i + 1), BASE_URL + LINK_KEY_MEN, urlParaNameAndValue);
                    } else if (tbDivList2.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(CHILDDIV).match()
                            && tbDivList2.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                        getBranchInfo(tbDivList2.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                        addPaginationLink(page, tbDivList2.get(i + 1), BASE_URL + LINK_BRANCH, urlParaNameAndValue);
                    } else if (tbDivList2.get(i).regex(CLEARINFO).match()) {
                        getClearInfo(tbDivList2.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                    }
                }
            }
            logger.info("======================>>>  备案信息   finish");

            logger.info("======================>>>  动产抵押登记信息   start");
            // 获取动产抵押登记信息div的子节点
            List<Selectable> tbDivList3 = page.getHtml().xpath(XPATH_ID_DIV_DONG_CHAN_DI_YA + XPATH_SPILT).nodes();
            if (tbDivList3.size() > 0) {
                for (int i = 0; i < tbDivList3.size(); i++) {
                    // Selectable s = tbDivList3.get(i);
                    if (tbDivList3.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(MORTDIV).match()
                            && tbDivList3.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                        getPropertyInfo(tbDivList3.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                        addPaginationLink(page, tbDivList3.get(i + 1), BASE_URL + LINK_PROPERTY, urlParaNameAndValue);
                    }
                }
            }
            logger.info("======================>>>  动产抵押登记信息   finish");

            logger.info("======================>>>  股权出质登记信息   start");
            // 获取股权出质登记信息div的子节点
            List<Selectable> tbDivList4 = page.getHtml().xpath(XPATH_ID_DIV_GU_QUAN_CHU_ZHI + XPATH_SPILT).nodes();
            if (tbDivList4.size() > 0) {
                for (int i = 0; i < tbDivList4.size(); i++) {
                    // Selectable s = tbDivList4.get(i);
                    if (tbDivList4.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(PLEDGEDIV).match()
                            && tbDivList4.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                        getPledgeInfo(tbDivList4.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                        addPaginationLink(page, tbDivList4.get(i + 1), BASE_URL + LINK_PLEDGE, urlParaNameAndValue);
                    }
                }
            }
            logger.info("======================>>>  股权出质登记信息   finish");

            logger.info("======================>>>  行政处罚信息   start");
            // 获取行政处罚信息div的子节点
            List<Selectable> tbDivList5 = page.getHtml().xpath(XPATH_ID_DIV_XING_ZHENG_CHU_FA + XPATH_SPILT).nodes();
            if (tbDivList5.size() > 0) {
                for (int i = 0; i < tbDivList5.size(); i++) {
                    // Selectable s = tbDivList5.get(i);
                    if (tbDivList5.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(PUNDIV).match()
                            && tbDivList5.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                        getPenaltyInfo(tbDivList5.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                        addPaginationLink(page, tbDivList5.get(i + 1), BASE_URL + LINK_PENALTY, urlParaNameAndValue);
                    }
                }
            }
            logger.info("======================>>>  行政处罚信息   finish");

            logger.info("======================>>>  经营异常信息   start");
            // 获取经营异常信息div的子节点
            List<Selectable> tbDivList6 = page.getHtml().xpath(XPATH_ID_DIV_JING_YING_YI_CHANG_MINGLU + XPATH_SPILT)
                    .nodes();
            if (tbDivList6.size() > 0) {
                for (int i = 0; i < tbDivList6.size(); i++) {
                    // Selectable s = tbDivList6.get(i);
                    if (tbDivList6.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(EXCDIV).match()
                            && tbDivList6.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                        getAbnormalInfo(tbDivList6.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                        addPaginationLink(page, tbDivList6.get(i + 1), BASE_URL + LINK_ABNORMAL, urlParaNameAndValue);
                    }
                }
            }
            logger.info("======================>>>  经营异常信息   finish");

            logger.info("======================>>>  严重违法信息   start");
            // 获取严重违法信息div的子节点
            List<Selectable> tbDivList7 = page.getHtml().xpath(XPATH_ID_DIV_YAN_ZHONG_WEI_FA_QI_YE + XPATH_SPILT)
                    .nodes();
            if (tbDivList7.size() > 0) {
                for (int i = 0; i < tbDivList7.size(); i++) {
                    // Selectable s = tbDivList7.get(i);
                    if (tbDivList7.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(ILLEGALDIV).match()
                            && tbDivList7.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                        getIllegalInfo(tbDivList7.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                        addPaginationLink(page, tbDivList7.get(i + 1), BASE_URL + LINK_ILLEGAL, urlParaNameAndValue);
                    }
                }
            }
            logger.info("======================>>>  严重违法信息   finish");

            logger.info("======================>>>  抽查检查信息   start");
            // 获取抽查检查信息div的子节点
            List<Selectable> tbDivList8 = page.getHtml().xpath(XPATH_ID_DIV_CHOU_CHA_XIN_XI + XPATH_SPILT).nodes();
            if (tbDivList8.size() > 0) {
                for (int i = 0; i < tbDivList8.size(); i++) {
                    // Selectable s = tbDivList8.get(i);
                    if (tbDivList8.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(SPOTCHECKDIV).match()
                            && tbDivList8.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                        getCheckInfo(tbDivList8.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                        addPaginationLink(page, tbDivList8.get(i + 1), BASE_URL + LINK_CHECK, urlParaNameAndValue);
                    }
                }
            }
            logger.info("======================>>>  抽查检查信息   finish");
            logger.info("======================>>>  工商公示信息   finish");
            // page.addTargetRequest("http://222.143.24.157/QueryInvList.jspx?pno=2&&mainId=36A75CD835576395E053050A080A2F4E");

        } else if (page.getUrl().regex(BASE_URL + REGEX_URL_ENTERPRISE_PUBLICITY).match()) {
            // 企业公示信息
            logger.info("======================>>>  企业公示信息   start");
            // 股东信息
            merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
            merNoMap.put("merNo", merNo);
            logger.info("======================>>>  企业年报信息   start");
            // 获取动产抵押登记信息div的子节点
            List<Selectable> tbDivList1 = page.getHtml().xpath(XPATH_ID_DIV_QI_YE_NIAN_BAO + XPATH_SPILT).nodes();
            if (tbDivList1.size() > 0) {
                getQygsNianbao(tbDivList1.get(0), page, merNo);
            }
            logger.info("======================>>>  企业年报信息   finish");

            logger.info("======================>>>  股东及出资信息   start");
            List<Selectable> tbDivList2 = page.getHtml().xpath(XPATH_ID_DIV_TOU_ZI_REN + XPATH_SPILT).nodes();
            if (tbDivList2.size() > 0) {
                for (int i = 0; i < tbDivList2.size(); i++) {
                    // Selectable s = tbDivList2.get(i);
                    if (tbDivList2.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(GDDIV).match()
                            && tbDivList2.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 3) {
                        getQygsShareInfo(tbDivList2.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                        addPaginationLink(page, tbDivList2.get(i + 1), BASE_URL + LINK_KEY_MEN, urlParaNameAndValue);
                    } else if (tbDivList2.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(ALTINV).match()
                            && tbDivList2.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 2) {
                        getQygsShareChangeInfo(tbDivList2.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                        addPaginationLink(page, tbDivList2.get(i + 1), BASE_URL + LINK_BRANCH, urlParaNameAndValue);
                    }
                }
            }
            logger.info("======================>>>  股东及出资信息   finish");
            logger.info("======================>>>  股权变更信息   start");
            List<Selectable> tbDivList3 = page.getHtml().xpath(XPATH_ID_DIV_GU_DONG_GU_QUAN).nodes();
            if (tbDivList2.size() > 0) {
                for (int i = 0; i < tbDivList3.size(); i++) {
                    if (tbDivList3.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(XPATH_ID_DIV_GQBG).match()
                            && tbDivList3.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 2) {
                        getQygsEquityInfo(tbDivList3.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                        addPaginationLink(page, tbDivList2.get(i + 1), BASE_URL + LINK_BRANCH, urlParaNameAndValue);
                    }
                }
            }
            logger.info("======================>>>  股权变更信息   finish");

            logger.info("======================>>>  企业公示信息   finish");

        } else if (page.getUrl().regex(BASE_URL + REGEX_URL__OTHER_DEPARTMENT).match()) {
            // 其他部门公示信息

        } else if (page.getUrl().regex(BASE_URL + REGEX_URL_JUSTICE_ASSISTANCE).match()) {
            // 司法协助公示信息

        } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_0).match()) {
            // 股东信息
            merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
            merNoMap.put("merNo", merNo);
            getShareholderInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
        } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_1).match()) {
            // 变更信息
            merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
            merNoMap.put("merNo", merNo);
            getChangeInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
        } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_2).match()) {
            // 主要人员信息
            merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
            merNoMap.put("merNo", merNo);
            getKeyManInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
        } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_3).match()) {
            // 分支机构信息
            merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
            merNoMap.put("merNo", merNo);
            getBranchInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
        } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_4).match()) {
            // 动产抵押登记信息
            merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
            merNoMap.put("merNo", merNo);
            getPropertyInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
        } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_5).match()) {
            // 股权出质登记信息
            merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
            merNoMap.put("merNo", merNo);
            getPledgeInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
        } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_6).match()) {
            // 行政处罚信息
            merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
            merNoMap.put("merNo", merNo);
            getPenaltyInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
        } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_7).match()) {
            // 经营异常信息
            merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
            merNoMap.put("merNo", merNo);
            getAbnormalInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
        } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_8).match()) {
            // 严重违法信息
            merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
            merNoMap.put("merNo", merNo);
            getIllegalInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
        } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_9).match()) {
            // 抽查检查信息
            merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
            merNoMap.put("merNo", merNo);
            getCheckInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
        } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_10).match()) {
            Map<String, String> merNoAndYear = new HashMap<String, String>();
            merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
            merNoAndYear.put("merNo", merNo);
            logger.info("======================>>>  年度报告   start");
            // 获取抽查检查信息div的子节点
            List<Selectable> tbDivList = page.getHtml().xpath(XPATH_ID_DIV_SI_FA_PAN_DING + XPATH_TABLE).nodes();
            if (tbDivList.size() > 0) {
                String year = tbDivList.get(0).xpath(XPATH_TR1).regex("([0-9]+)年度").toString();
                merNoAndYear.put("memo", year);
                for (int i = 0; i < tbDivList.size(); i++) {
                    // Selectable s = tbDivList.get(i);
                    if (tbDivList.get(i).xpath(XPATH_TR2).regex(ENT_BASIC_INFO).match()
                            && tbDivList.get(i).xpath(XPATH_TR).nodes().size() > 2) {
                        getNdbgBasic(tbDivList.get(i), resultItems, merNoMap);
                    } else if (tbDivList.get(i).xpath(XPATH_TR1).regex(ENT_WEB_INFO).match()
                            && tbDivList.get(i).xpath(XPATH_TR).nodes().size() > 2) {
                        getNdbgWebInfo(tbDivList.get(i), resultItems, merNoAndYear);
                    } else if (tbDivList.get(i).xpath(XPATH_TR1).regex(ENT_SHARE_INFO).match()
                            && tbDivList.get(i).xpath(XPATH_TR).nodes().size() > 2) {
                        getNdbgShareInfo(tbDivList.get(i), resultItems, merNoAndYear);
                    } else if (tbDivList.get(i).xpath(XPATH_TR1).regex(ENT_INVEST_INFO).match()
                            && tbDivList.get(i).xpath(XPATH_TR).nodes().size() > 2) {
                        getNdbgInvestInfo(tbDivList.get(i), resultItems, merNoAndYear);
                    } else if (tbDivList.get(i).xpath(XPATH_TR1).regex(ENT_ASSET_INFO).match()
                            && tbDivList.get(i).xpath(XPATH_TR).nodes().size() > 1) {
                        getNdbgAssetInfo(tbDivList.get(i), resultItems, merNoAndYear);
                    } else if (tbDivList.get(i).xpath(XPATH_TR1).regex(ENT_WARRANT_INFO).match()
                            && tbDivList.get(i).xpath(XPATH_TR).nodes().size() > 2) {
                        getNdbgWarrantInfo(tbDivList.get(i), resultItems, merNoAndYear);
                    } else if (tbDivList.get(i).xpath(XPATH_TR1).regex(ENT_EQUITY_INFO).match()
                            && tbDivList.get(i).xpath(XPATH_TR).nodes().size() > 2) {
                        getNdbgEquityInfo(tbDivList.get(i), resultItems, merNoAndYear);
                    } else if (tbDivList.get(i).xpath(XPATH_TR1).regex(ENT_CHANGE_INFO).match()
                            && tbDivList.get(i).xpath(XPATH_TR).nodes().size() > 2) {
                        getNdbgChangeInfo(tbDivList.get(i), resultItems, merNoAndYear);
                    }

                }
            }
        } else {
            logger.info("=======其他URL:" + page.getUrl() + "=======");
        }

    }

    /**
     * <p>
     * description:添加分页链接
     * </p>
     * 
     * @param page
     * @param xpathStr
     * @param url
     * @param urlParaNameAndValue
     * @author LiZhanPing
     * @see
     */
    public void addPaginationLink(Page page, Selectable tableSel, String url, Map<String, String> urlParaNameAndValue) {
        logger.info("======>>   entry   addPaginationLink:");
        int alinkNum = 0;
        if (tableSel.xpath(XPATH_TH).nodes().size() > 0) {
            // 判断“>>”链接是否包含总页数
            if (tableSel.xpath(XPATH_TH).toString().contains(NEXT)) {
                alinkNum = Integer.parseInt(tableSel.xpath(XPATH_TH).regex(REGEX_NEXT).toString());
                for (int i = 2; i <= alinkNum; i++) {
                    StringBuilder paraStr = new StringBuilder();
                    for (Map.Entry<String, String> entry : urlParaNameAndValue.entrySet()) {
                        paraStr.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                    }
                    page.addTargetRequest(url + i + paraStr);
                    logger.info("add Url:" + url + i + paraStr);
                }
            } else {
                List<Selectable> list = tableSel.xpath(XPATH_SPAN + XPATH_ALL_TEXT).nodes();
                if (list.size() > 3) {
                    alinkNum = Integer.parseInt(list.get(list.size() - 2).toString());
                    for (int i = 2; i <= alinkNum; i++) {
                        StringBuilder paraStr = new StringBuilder();
                        for (Map.Entry<String, String> entry : urlParaNameAndValue.entrySet()) {
                            paraStr.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                        }
                        page.addTargetRequest(url + i + paraStr);
                        logger.info("add Url:" + url + i + paraStr);
                    }
                }
            }
        }
        logger.info("======>>   end  addPaginationLink:");
    }

    /**
     * <p>
     * description:获取web页面数据表的单条数据的通用方法
     * </p>
     * 
     * @param tdList
     *            指定表的表格的List<selectable>
     * @param resultItems
     * @param tableName
     *            常量中定义的数据库表名
     * @param matchedColumnNameMap
     *            列名匹配map 该map必须是LinkedHashMap形式的 存放的是数据库表的列名和web页面数据表的列名的对应关系
     * @author LiZhanPing
     * @see
     */
    public void getData(List<Selectable> tdList, ResultItems resultItems,
            CreepersConstant.TableNamesBusinessInfo tableName, Map<String, String> matchedColumnNameMap) {

        logger.info("======>>   entry   " + tableName.getMapKey() + ":");
        if (!tableName.isList()) {
            // 获取存放数据的存储结构
            Map<String, String> map = resultItems.get(tableName.getMapKey());
            boolean isFirstTr = true;
            String lastContent = CONTENT_EMPTY;
            if (tdList.size() > 0
                    && matchedColumnNameMap.size() > 0 && ((tdList.size() - 1) / 2 == matchedColumnNameMap.size())) {
                for (Selectable tdSel : tdList) {
                    // 第一个表格是表名，需要去掉
                    if (isFirstTr) {
                        isFirstTr = false;
                        continue;
                    }
                    String currentContent = tdSel.xpath(XPATH_ALL_TEXT).toString();
                    logger.info("currentContent:" + currentContent);
                    for (Map.Entry<String, String> entry : matchedColumnNameMap.entrySet()) {
                        if (entry.getValue().contains(currentContent)) {
                            lastContent = tdSel.xpath(XPATH_ALL_TEXT).toString();
                        } else if (entry.getValue().contains(lastContent)) {
                            map.put(entry.getKey(), currentContent);
                            logger.info(entry.getKey() + SPILT_COLON + currentContent);
                            lastContent = CONTENT_EMPTY;
                        }
                    }
                }
            } else {
                logger.info("================>>columnName的长度不符合web页面数据表的列数<<================");
            }
        } else {
            logger.info("================>>tableName参数的类型不是一个map<<================");
        }
    }

    /**
     * <p>
     * description:获取web页面数据表的多条数据的通用方法
     * </p>
     * 
     * @param tableSel
     *            指定table的selectable
     * @param resultItems
     * @param tableName
     *            常量中定义的数据库表名
     * @param columnNameList
     *            数据库表的列名数组
     * @author LiZhanPing
     * @see
     */
    public void getDatas(Selectable tableSel, ResultItems resultItems,
            CreepersConstant.TableNamesBusinessInfo tableName, List<String> columnNameList) {

        logger.info("======>>   entry   " + tableName.getMapKey() + ":");
        // 获取存放数据的存储结构
        if (tableName.isList()) {
            List<Map<String, String>> list = resultItems.get(tableName.getMapKey());
            // 获取web页面的数据
            List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
            // web页面数据表的列数
            int webColumns = trList.get(0).xpath(XPATH_TD).nodes().size();
            // 获取数据库表的列数
            int dbColumns = columnNameList.size();
            // 判断数据库表的列数是否符合web页面数据表的列数
            if (webColumns > 0 && dbColumns > 0 && webColumns % dbColumns == 0) {
                for (Selectable trSel : trList) {
                    // 获取一行web页面数据
                    List<Selectable> tdList = trSel.xpath(XPATH_TD).nodes();
                    // web页面中多个相同的数据表并列
                    for (int m = 0; m < (webColumns / dbColumns); m++) {
                        // 存放单条数据
                        Map<String, String> map = new HashMap<String, String>();
                        // web页面数据表的列的下标
                        int webColumnIndex = 0;
                        for (int n = 0; n < dbColumns; n++) {
                            webColumnIndex = m * dbColumns + n;
                            if (tdList.get(webColumnIndex).xpath(XPATH_HTML).regex("window.open").match()) {
                                String urlPara = tdList.get(webColumnIndex).xpath(XPATH_A + XPATH_PROPERTY_ONCLICK)
                                        .toString().replace("window.open('", CONTENT_EMPTY).replace("')", CONTENT_EMPTY)
                                        .replace("?", "\\\\?");
                                map.put(columnNameList.get(n), BASE_URL + urlPara);
                                logger.info(columnNameList.get(n) + SPILT_COLON + columnNameList.get(n));
                            } else {
                                map.put(columnNameList.get(n),
                                        tdList.get(webColumnIndex).xpath(XPATH_ALL_TEXT).toString());
                                logger.info(columnNameList.get(n) + SPILT_COLON + map.get(columnNameList.get(n)));
                            }
                        }
                        // 注册号/统一社会信用编码
                        map.put("merNo", resultItems.get("merNo"));
                        logger.info("merNo" + SPILT_COLON + map.get("merNo"));
                        list.add(map);
                    }
                }
            } else {
                logger.info("================>>columnName的长度不符合web页面数据表的列数<<================");
            }
        } else {
            logger.info("================>>tableName参数的类型不是一个List<<================");
        }

        logger.info("======>>   end   " + tableName.getMapKey() + "List<Selectable>:");
    }

    /**
     * <p>
     * description:列名排序，使web页面表的列名和数据库表中的列名对应
     * </p>
     * 
     * @param webColumnNameList
     *            web页面表的列名的数组
     * @param columnNum
     *            web页面中相同的数据表并列的个数
     * @param matchedColumnNameMap
     *            列名匹配map 该map必须是LinkedHashMap形式的 存放的是数据库表的列名和web页面数据表的列名的对应关系
     * @return
     * @author LiZhanPing
     * @see
     */
    public List<String> sortColumnName(List<Selectable> webColumnNameList, int columnNum,
            Map<String, String> matchedColumnNameMap) {
        List<String> columnNameList = new ArrayList<String>();
        // 判断数据库表的列数是否符合web页面数据表的列数
        if (matchedColumnNameMap.size() > 0
                && webColumnNameList.size() > 0
                && (webColumnNameList.size() / matchedColumnNameMap.size()) == columnNum) {
            int i = 0;
            // 一一对应数据表和web页面数据表的列名
            for (Map.Entry<String, String> entry : matchedColumnNameMap.entrySet()) {
                if (entry.getValue().contains(webColumnNameList.get(i).toString())) {
                    columnNameList.add(entry.getKey());
                } else {
                    logger.info("================>>数据库表的列名和web页面的列名对应出错！"
                            + entry.getKey() + ":" + entry.getValue() + ":" + webColumnNameList.get(i).toString()
                            + "<<================");
                }
            }
        } else {
            logger.info("================>>数据库表的列数不符合web页面数据表的列数<<================");
        }
        return columnNameList;
    }

    // 工商公示信息--基本信息
    protected void merBaseInfo(Selectable tableSelectable, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   merBaseInfo:");
        // logger.info(selectable.toString());
        List<Selectable> tdThList = tableSelectable.xpath(XPATH_TBODY + XPATH_TR + XPATH_SPILT).nodes();
        boolean isFirstTr = true;
        Map<String, String> map = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_BASIC.getMapKey());
        // 工商注册号
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerBasicColumn.MER_NO.getValue()
                + SPILT_COLON + map.get(CreepersConstant.TCreepersMerBasicColumn.MER_NO.getValue()));
        String lastContent = CONTENT_EMPTY;
        for (Selectable eachTdTh : tdThList) {
            if (isFirstTr) {
                isFirstTr = false;
                continue;
            }
            String currentContent = eachTdTh.xpath("//text(1)").toString();
            logger.info("currentContent:" + currentContent);
            if (CONTENT_CREDIT_CODE1.equals(currentContent) || CONTENT_CREDIT_CODE2.equals(currentContent)) {
                lastContent = CONTENT_CREDIT_CODE1;
                continue;
            } else if (CONTENT_CREDIT_CODE1.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 注册号/统一社会信用代码
                map.put(CreepersConstant.TCreepersMerBasicColumn.MEMO.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersMerBasicColumn.MEMO.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_NAME.equals(currentContent)) {
                lastContent = CONTENT_NAME;
                continue;
            } else if (CONTENT_NAME.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 名称
                map.put(CreepersConstant.TCreepersMerBasicColumn.MER_NAME.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersMerBasicColumn.MER_NAME.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_TYPE.equals(currentContent)) {
                lastContent = CONTENT_TYPE;
                continue;
            } else if (CONTENT_TYPE.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 类型
                map.put(CreepersConstant.TCreepersMerBasicColumn.MER_TYPE.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersMerBasicColumn.MER_TYPE.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_LEGAL_REPRESENTATIVE1.equals(currentContent)
                    || CONTENT_LEGAL_REPRESENTATIVE2.equals(currentContent)
                    || CONTENT_LEGAL_REPRESENTATIVE3.equals(currentContent)) {
                lastContent = CONTENT_LEGAL_REPRESENTATIVE1;
                continue;
            } else if (CONTENT_LEGAL_REPRESENTATIVE1.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 法定代表人
                map.put(CreepersConstant.TCreepersMerBasicColumn.LEGAL_REP.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersMerBasicColumn.LEGAL_REP.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_REGISTERED_CAPITAL.equals(currentContent)) {
                lastContent = CONTENT_REGISTERED_CAPITAL;
                continue;
            } else if (CONTENT_REGISTERED_CAPITAL.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 注册资本
                map.put(CreepersConstant.TCreepersMerBasicColumn.REG_CAPITAL.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersMerBasicColumn.REG_CAPITAL.getValue() + SPILT_COLON + currentContent);
            } else
                if (CONTENT_CONSTRUCT_DATE1.equals(currentContent) || CONTENT_CONSTRUCT_DATE2.equals(currentContent)) {
                lastContent = CONTENT_CONSTRUCT_DATE1;
                continue;
            } else if (CONTENT_CONSTRUCT_DATE1.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 成立日期
                if (StringUtil.isNotBlank(currentContent)) {
                    map.put(CreepersConstant.TCreepersMerBasicColumn.FOUND_DT.getValue(), currentContent);
                    logger.info(CreepersConstant.TCreepersMerBasicColumn.FOUND_DT.getValue()
                            + SPILT_COLON + currentContent);
                }
            } else if (CONTENT_ADDRESS1.equals(currentContent) || CONTENT_ADDRESS2.equals(currentContent)) {
                lastContent = CONTENT_ADDRESS1;
                continue;
            } else if (CONTENT_ADDRESS1.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 住所
                map.put(CreepersConstant.TCreepersMerBasicColumn.ADDR.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersMerBasicColumn.ADDR.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_OPERATING_PERIOD_START.equals(currentContent)) {
                lastContent = CONTENT_OPERATING_PERIOD_START;
                continue;
            } else if (CONTENT_OPERATING_PERIOD_START.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 营业期限自
                if (StringUtil.isNotBlank(currentContent)) {
                    map.put(CreepersConstant.TCreepersMerBasicColumn.OPR_START_DT.getValue(), currentContent);
                    logger.info(CreepersConstant.TCreepersMerBasicColumn.OPR_START_DT.getValue()
                            + SPILT_COLON + currentContent);
                }
            } else if (CONTENT_OPERATING_PERIOD_END.equals(currentContent)) {
                lastContent = CONTENT_OPERATING_PERIOD_END;
                continue;
            } else if (CONTENT_OPERATING_PERIOD_END.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 营业期限至
                if (StringUtil.isNotBlank(currentContent)) {
                    map.put(CreepersConstant.TCreepersMerBasicColumn.OPR_END_DT.getValue(), currentContent);
                    logger.info(CreepersConstant.TCreepersMerBasicColumn.OPR_END_DT.getValue()
                            + SPILT_COLON + currentContent);
                }
            } else if (CONTENT_BUSINESS_SCOPE.equals(currentContent)) {
                lastContent = CONTENT_BUSINESS_SCOPE;
                continue;
            } else if (CONTENT_BUSINESS_SCOPE.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 经营范围
                map.put(CreepersConstant.TCreepersMerBasicColumn.SCOPE.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersMerBasicColumn.SCOPE.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_REGISTRATION_AUTHORITY.equals(currentContent)) {
                lastContent = CONTENT_REGISTRATION_AUTHORITY;
                continue;
            } else if (CONTENT_REGISTRATION_AUTHORITY.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 登记机关
                map.put(CreepersConstant.TCreepersMerBasicColumn.REG_AUTHORITY.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersMerBasicColumn.REG_AUTHORITY.getValue()
                        + SPILT_COLON + currentContent);
            } else if (CONTENT_AUTHORIZE_DATE.equals(currentContent)) {
                lastContent = CONTENT_AUTHORIZE_DATE;
                continue;
            } else if (CONTENT_AUTHORIZE_DATE.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 核准日期
                if (StringUtil.isNotBlank(currentContent)) {
                    map.put(CreepersConstant.TCreepersMerBasicColumn.MER_TYPE.getValue(), currentContent);
                    logger.info(CreepersConstant.TCreepersMerBasicColumn.MER_TYPE.getValue()
                            + SPILT_COLON + currentContent);
                }
            } else if (CONTENT_REGISTRATION_STATUS.equals(currentContent)) {
                lastContent = CONTENT_REGISTRATION_STATUS;
                continue;
            } else if (CONTENT_REGISTRATION_STATUS.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 登记状态
                map.put(CreepersConstant.TCreepersMerBasicColumn.REG_STATUS.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersMerBasicColumn.REG_STATUS.getValue() + SPILT_COLON + currentContent);
            }
        }
        logger.info("======>>   end  merBaseInfo:");
    }

    /**
     * <p>
     * description:获取股东信息
     * </p>
     * 
     * @param selectable
     * @param resultItems
     * @param merNoMap
     * @author LiZhanPing
     * @see
     */
    public void getShareholderInfo(Selectable selectable, ResultItems resultItems, Map<String, String> merNoMap) {

        logger.info("======>>   entry   getShareholder:");
        // 获取存放所有ShareHolder数据的存储结构
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_SHAREHOLDER.getMapKey());
        // 获取所有行的ShareHolder数据
        List<Selectable> trList = selectable.xpath(XPATH_TR).nodes();
        for (Selectable trSel : trList) {
            // 存放单条的ShareHolder数据
            Map<String, String> map = new HashMap<String, String>();
            // 获取一行的ShareHolder数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            // 添加股东
            map.put(CreepersConstant.TCreepersMerShareholderColumn.NAME.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerShareholderColumn.NAME.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.NAME.getValue()));

            // 添加证照/证件类型
            map.put(CreepersConstant.TCreepersMerShareholderColumn.CERT_TYPE.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerShareholderColumn.CERT_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.CERT_TYPE.getValue()));

            // 添加证照/证件号码
            map.put(CreepersConstant.TCreepersMerShareholderColumn.CERT_NO.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerShareholderColumn.CERT_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.CERT_NO.getValue()));

            // 添加股东类型
            map.put(CreepersConstant.TCreepersMerShareholderColumn.SHARE_TYPE.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerShareholderColumn.SHARE_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.SHARE_TYPE.getValue()));

            // 添加详情
            String urlPara = trSel.xpath(XPATH_A + XPATH_PROPERTY_ONCLICK).toString()
                    .replace("window.open('", CONTENT_EMPTY).replace("')", CONTENT_EMPTY).replace("?", "\\\\?");
            map.put(CreepersConstant.TCreepersMerShareholderColumn.MEMO.getValue(), BASE_URL + urlPara);
            logger.info(CreepersConstant.TCreepersMerShareholderColumn.MEMO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.MEMO.getValue()));

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerShareholderColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("======>>   end   getShareholder:");
    }

    /**
     * <p>
     * description:获取变更信息
     * </p>
     * 
     * @param selectable
     * @param resultItems
     * @param merNoMap
     * @author LiZhanPing
     * @see
     */
    public void getChangeInfo(Selectable selectable, ResultItems resultItems, Map<String, String> merNoMap) {

        logger.info("======>>   entry   getMerChange:");
        // 获取存放所有MerChange数据的存储结构
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_CHANGE.getMapKey());
        // 获取所有行的MerChange数据
        List<Selectable> trList = selectable.xpath(XPATH_TR).nodes();
        for (Selectable trSel : trList) {
            // 存放单条的MerChange数据
            Map<String, String> map = new HashMap<String, String>();
            // 获取单条的MerChange数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            // 添加变更事项
            map.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_ITEM.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerChangeColumn.CHANGE_ITEM.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.CHANGE_ITEM.getValue()));

            // 添加变更前内容
            map.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_OLD.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerChangeColumn.CHANGE_OLD.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.CHANGE_OLD.getValue()));

            // 添加变更后内容
            map.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_NEW.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerChangeColumn.CHANGE_NEW.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.CHANGE_NEW.getValue()));

            // 变更日期
            map.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_DT.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerChangeColumn.CHANGE_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.CHANGE_DT.getValue()));
            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerChangeColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.MER_NO.getValue()));
            list.add(map);
        }

        logger.info("======>>   end   getMerChange:");
    }

    public void getKeyManInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoMap) {

        logger.info("======>>   entry   getKeyManInfo:");
        // 获取存放所有KeyMan数据的存储结构
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_KEYMAN.getMapKey());
        // 获取存放在web页面的 每行KeyMan数据
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        for (Selectable trSel : trList) {
            List<Selectable> strList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            for (int m = 0; m < 2; m++) {
                Map<String, String> map = new HashMap<String, String>();
                map.putAll(merNoMap);
                logger.info(CreepersConstant.TCreepersMerKeymanColumn.MER_NO.getValue()
                        + SPILT_COLON + map.get(CreepersConstant.TCreepersMerKeymanColumn.MER_NO.getValue()));
                for (int n = 0; n < 3; n++) {
                    if (n == 0) {
                        map.put(CreepersConstant.TCreepersMerKeymanColumn.SEQ_NO.getValue(),
                                strList.get(n + m * 3).toString());
                        logger.info(CreepersConstant.TCreepersMerKeymanColumn.SEQ_NO.getValue()
                                + SPILT_COLON + map.get(CreepersConstant.TCreepersMerKeymanColumn.SEQ_NO.getValue()));
                    } else if (n == 1) {
                        map.put(CreepersConstant.TCreepersMerKeymanColumn.NAME.getValue(),
                                strList.get(n + m * 3).toString());
                        logger.info(CreepersConstant.TCreepersMerKeymanColumn.NAME.getValue()
                                + SPILT_COLON + map.get(CreepersConstant.TCreepersMerKeymanColumn.NAME.getValue()));
                    } else if (n == 2) {
                        map.put(CreepersConstant.TCreepersMerKeymanColumn.POSITION.getValue(),
                                strList.get(n + m * 3).toString());
                        logger.info(CreepersConstant.TCreepersMerKeymanColumn.POSITION.getValue()
                                + SPILT_COLON + map.get(CreepersConstant.TCreepersMerKeymanColumn.POSITION.getValue()));
                    }
                }
                list.add(map);
            }
        }
        logger.info("======>>   end   getKeyManInfo:");
    }

    public void getBranchInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoMap) {

        logger.info("======>>   entry   getBranchInfo:");
        // 获取存放所有KeyMan数据的存储结构
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_KEYMAN.getMapKey());
        // 获取存放在web页面的 每行KeyMan数据
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        for (Selectable trSel : trList) {
            Map<String, String> map = new HashMap<String, String>();
            List<Selectable> strList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerBranchColumn.MER_NO.getValue()
                    + map.get(CreepersConstant.TCreepersMerBranchColumn.MER_NO.getValue()));
            // 序号
            map.put(CreepersConstant.TCreepersMerBranchColumn.SEQ_NO.getValue(), strList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerBranchColumn.SEQ_NO.getValue()
                    + map.get(CreepersConstant.TCreepersMerBranchColumn.SEQ_NO.getValue()));
            // 注册号/统一社会信用编号
            map.put(CreepersConstant.TCreepersMerBranchColumn.REG_NO.getValue(), strList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerBranchColumn.REG_NO.getValue()
                    + map.get(CreepersConstant.TCreepersMerBranchColumn.REG_NO.getValue()));
            // 名字
            map.put(CreepersConstant.TCreepersMerBranchColumn.NAME.getValue(), strList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerBranchColumn.NAME.getValue()
                    + map.get(CreepersConstant.TCreepersMerBranchColumn.NAME.getValue()));
            // 注册机构
            map.put(CreepersConstant.TCreepersMerBranchColumn.REG_AUTHORITY.getValue(), strList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerBranchColumn.REG_AUTHORITY.getValue()
                    + map.get(CreepersConstant.TCreepersMerBranchColumn.REG_AUTHORITY.getValue()));
            list.add(map);
        }
        logger.info("======>>   end   getKeyManInfo:");
    }

    // 获取清算信息的方法
    public void getClearInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoMap) {

        // 此方法为保留方法，现在暂不实现
        logger.info("================>>此方法为保留方法，现在暂不实现<<================");
    }

    public void getPropertyInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   getProperty:");
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PROPERTY.getMapKey());
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        for (Selectable trSel : trList) {
            // 存放单条的Property数据
            Map<String, String> map = new HashMap<String, String>();
            // 获取一行的Property数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            // 添加序号
            map.put(CreepersConstant.TCreepersMerPropertyColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.SEQ_NO.getValue()));

            // 添加登记编号
            map.put(CreepersConstant.TCreepersMerPropertyColumn.REG_NO.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.REG_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.REG_NO.getValue()));

            // 添加登记日期
            map.put(CreepersConstant.TCreepersMerPropertyColumn.REG_DT.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.REG_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.REG_DT.getValue()));

            // 添加登记机关
            map.put(CreepersConstant.TCreepersMerPropertyColumn.REG_AUTHORITY.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.REG_AUTHORITY.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.REG_AUTHORITY.getValue()));

            // 添加被担保债权数额
            map.put(CreepersConstant.TCreepersMerPropertyColumn.CLAIM_AMOUNT.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.CLAIM_AMOUNT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.CLAIM_AMOUNT.getValue()));

            // 添加状态
            map.put(CreepersConstant.TCreepersMerPropertyColumn.STATUS.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.STATUS.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.STATUS.getValue()));

            // 添加公示日期
            map.put(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue(), tdList.get(6).toString());

            logger.info("================>>此字段为保留字段，暂时存储在memo中<<================");
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue()));

            // 添加详情
            String urlPara = trSel.xpath(XPATH_PROPERTY_ONCLICK).toString().replace("window.open('", CONTENT_EMPTY)
                    .replace("')", CONTENT_EMPTY).replace("?", "\\\\?");
            map.put(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue(), BASE_URL + urlPara);
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue()));

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("======>>   end   getProperty:");
    }

    public void getPledgeInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   getPledgeInfo:");
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PLEDGE.getMapKey());
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        for (Selectable trSel : trList) {
            // 存放单条的Property数据
            Map<String, String> map = new HashMap<String, String>();
            // 获取一行的Property数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            // 添加序号
            map.put(CreepersConstant.TCreepersMerPledgeColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.SEQ_NO.getValue()));

            // 添加登记编号
            map.put(CreepersConstant.TCreepersMerPledgeColumn.REG_NO.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.REG_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.REG_NO.getValue()));

            // 添加出质人
            map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER.getValue()));

            // 添加证照/证件号码
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

            // 质权人证件号
            map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE_CERT_NO.getValue(), tdList.get(6).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE_CERT_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE_CERT_NO.getValue()));

            // 登记日期
            map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_DT.getValue(), tdList.get(7).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_DT.getValue()));

            // 状态
            map.put(CreepersConstant.TCreepersMerPledgeColumn.STATUS.getValue(), tdList.get(8).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.STATUS.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.STATUS.getValue()));

            // 添加公示日期
            map.put(CreepersConstant.TCreepersMerPledgeColumn.MEMO.getValue(), tdList.get(9).toString());

            logger.info("================>>此字段为保留字段，暂时存储在memo中<<================");
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.MEMO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.MEMO.getValue()));

            // 添加变化情况
            map.put(CreepersConstant.TCreepersMerPledgeColumn.CHANGE_INFO.getValue(), tdList.get(10).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.CHANGE_INFO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.CHANGE_INFO.getValue()));

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("======>>   end   getPledgeInfo:");
    }

    public void getPenaltyInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   getPenaltyInfo:");
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PENALTY.getMapKey());
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        for (Selectable trSel : trList) {
            // 存放单条的Property数据
            Map<String, String> map = new HashMap<String, String>();
            // 获取一行的Property数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            // 添加序号
            map.put(CreepersConstant.TCreepersMerPenaltyColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.SEQ_NO.getValue()));

            // 添加行政处罚决定书文号
            map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_NO.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_NO.getValue()));

            // 添加违法行为类型
            map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_TYPE.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_TYPE.getValue()));

            // 添加行政处罚内容
            map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_CONTENT.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_CONTENT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_CONTENT.getValue()));

            // 添加作出行政处罚决定机关名称
            map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_AUTHORITY.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_AUTHORITY.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_AUTHORITY.getValue()));

            // 添加作出行政处罚决定日期
            map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_DT.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_DT.getValue()));

            // 添加公示日期
            map.put(CreepersConstant.TCreepersMerPenaltyColumn.MEMO.getValue(), tdList.get(6).toString());

            logger.info("================>>此字段为保留字段，暂时存储在memo中<<================");
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.MEMO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.MEMO.getValue()));

            // 添加详情
            String urlPara = trSel.xpath(XPATH_PROPERTY_ONCLICK).toString().replace("window.open('", CONTENT_EMPTY)
                    .replace("')", CONTENT_EMPTY).replace("?", "\\\\?");
            map.put(CreepersConstant.TCreepersMerPenaltyColumn.DETAILS.getValue(), BASE_URL + urlPara);
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.DETAILS.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.DETAILS.getValue()));

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("======>>   end   getPenaltyInfo:");
    }

    public void getAbnormalInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   getAbnormalInfo:");
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_ABNORMAL.getMapKey());
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        for (Selectable trSel : trList) {
            // 存放单条的Property数据
            Map<String, String> map = new HashMap<String, String>();
            // 获取一行的Property数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            // 添加序号
            map.put(CreepersConstant.TCreepersMerAbnormalColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerAbnormalColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.SEQ_NO.getValue()));

            // 添加列入经营异常名录原因
            map.put(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_REASON.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_REASON.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_REASON.getValue()));

            // 添加列入日期
            map.put(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_DT.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_DT.getValue()));

            // 添加移出经营异常名录原因
            map.put(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_REASON.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_REASON.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_REASON.getValue()));

            // 添加移出日期
            map.put(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_DT.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_DT.getValue()));

            // 添加作出决定机关
            map.put(CreepersConstant.TCreepersMerAbnormalColumn.AUTHORITY.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersMerAbnormalColumn.AUTHORITY.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.AUTHORITY.getValue()));

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerAbnormalColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("======>>   end   getAbnormalInfo:");
    }

    public void getIllegalInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   getIllegalInfo:");
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_ILLEGAL.getMapKey());
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        for (Selectable trSel : trList) {
            // 存放单条的Property数据
            Map<String, String> map = new HashMap<String, String>();
            // 获取一行的Property数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            // 添加序号
            map.put(CreepersConstant.TCreepersMerIllegalColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerIllegalColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.SEQ_NO.getValue()));

            // 添加列入严重违法失信企业名单原因
            map.put(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_REASON.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_REASON.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_REASON.getValue()));

            // 添加列入日期
            map.put(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_DT.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_DT.getValue()));

            // 添加移出严重违法失信企业名单原因
            map.put(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_REASON.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_REASON.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_REASON.getValue()));

            // 添加移出日期
            map.put(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_DT.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_DT.getValue()));

            // 添加作出决定机关
            map.put(CreepersConstant.TCreepersMerIllegalColumn.AUTHORITY.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersMerIllegalColumn.AUTHORITY.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.AUTHORITY.getValue()));

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerIllegalColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("======>>   end   getIllegalInfo:");
    }

    public void getCheckInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   getCheckInfo:");
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_CHECK.getMapKey());
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        for (Selectable trSel : trList) {
            // 存放单条的Property数据
            Map<String, String> map = new HashMap<String, String>();
            // 获取一行的Property数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            // 添加序号
            map.put(CreepersConstant.TCreepersMerCheckColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersMerCheckColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.SEQ_NO.getValue()));

            // 添加检查实施机关
            map.put(CreepersConstant.TCreepersMerCheckColumn.AUTHORITY.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersMerCheckColumn.AUTHORITY.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.AUTHORITY.getValue()));

            // 添加类型
            map.put(CreepersConstant.TCreepersMerCheckColumn.CHECK_TYPE.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerCheckColumn.CHECK_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.CHECK_TYPE.getValue()));

            // 添加日期
            map.put(CreepersConstant.TCreepersMerCheckColumn.CHECK_DT.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerCheckColumn.CHECK_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.CHECK_DT.getValue()));

            // 添加结果
            map.put(CreepersConstant.TCreepersMerCheckColumn.CHECK_RESULT.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersMerCheckColumn.CHECK_RESULT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.CHECK_RESULT.getValue()));

            // 工商注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerCheckColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.MER_NO.getValue()));
            list.add(map);
        }
        logger.info("======>>   end   getCheckInfo:");
    }

    public void getQygsNianbao(Selectable tableSel, Page page, String merNo) {
        logger.info("======>>   entry   getQygsNianbao:");
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        int trNum = 0;
        for (Selectable trSel : trList) {
            if (trNum < 2) {
                trNum++;
                continue;
            }
            List<Selectable> tdList = trSel.xpath(XPATH_TD).nodes();
            if (tdList.size() != 3) {
                logger.info("=============>>>当前表格不匹配，无法解析.");
                continue;
            }

            // 序号
            logger.info("序号：" + tdList.get(0).xpath(XPATH_ALL_TEXT).toString());
            // 报送年度
            String ndbgParam = tdList.get(1).xpath(XPATH_A + XPATH_PROPERTY_HREF).toString();
            // 年度报告URL
            String ndbgUrl = ndbgParam + "&merNo=" + merNo;
            logger.info("报送年度：" + ndbgUrl);
            page.addTargetRequest(ndbgUrl);
            // 发布日期
            logger.info("发布日期：" + tdList.get(2).toString());
        }
        logger.info("======>>   end   getQygsNianbao:");
    }

    // 年报-企业基本信息
    public void getNdbgBasic(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoAndYear) {

        logger.info("======>>   entry   getNdbgBasic:");
        List<Selectable> tdThList = tableSel.xpath(XPATH_TBODY + XPATH_TR + XPATH_SPILT).nodes();
        Map<String, String> map = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_BASIC.getMapKey());
        // 工商注册号
        map.putAll(merNoAndYear);
        logger.info(CreepersConstant.TCreepersEntBasicColumn.MER_NO.getValue()
                + SPILT_COLON + map.get(CreepersConstant.TCreepersEntBasicColumn.MER_NO.getValue()));
        int tdThNum = 0;
        String lastContent = CONTENT_EMPTY;
        for (Selectable eachTdTh : tdThList) {
            if (tdThNum < 2) {
                if (tdThNum == 0) {
                    String year = eachTdTh.regex("([0-9]+)年度").toString();
                    map.put(CreepersConstant.TCreepersEntBasicColumn.MEMO.getValue(), year);
                }
                tdThNum++;
                continue;
            }
            String currentContent = eachTdTh.xpath(XPATH_ALL_TEXT).toString();
            logger.info("currentContent:" + currentContent);
            if (CONTENT_CREDIT_CODE1.equals(currentContent) || CONTENT_CREDIT_CODE2.equals(currentContent)) {
                lastContent = CONTENT_CREDIT_CODE1;
                continue;
            } else if (CONTENT_CREDIT_CODE1.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 注册号/统一社会信用代码
                // map.put(CreepersConstant.TCreepersEntBasicColumn.MEMO.getValue(),
                // currentContent);
                // logger.info(CreepersConstant.TCreepersEntBasicColumn.MEMO.getValue()
                // + SPILT_COLON + currentContent);
                logger.info("===========>注册号/统一社会信用代码暂不存储");
            } else if (CONTENT_COMPANY_NAME.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_NAME;
                continue;
            } else if (CONTENT_COMPANY_NAME.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 企业名称
                map.put(CreepersConstant.TCreepersEntBasicColumn.MER_NAME.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersEntBasicColumn.MER_NAME.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_COMPANY_PHONE.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_PHONE;
                continue;
            } else if (CONTENT_COMPANY_PHONE.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 企业联系电话
                map.put(CreepersConstant.TCreepersEntBasicColumn.PHONE.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersEntBasicColumn.PHONE.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_COMPANY_POST_CODE.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_POST_CODE;
                continue;
            } else if (CONTENT_COMPANY_POST_CODE.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 邮政编码
                map.put(CreepersConstant.TCreepersEntBasicColumn.POST_CODE.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersEntBasicColumn.POST_CODE.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_COMPANY_ADDR.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_ADDR;
                continue;
            } else if (CONTENT_COMPANY_ADDR.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 企业通信地址
                map.put(CreepersConstant.TCreepersEntBasicColumn.ADDR.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersEntBasicColumn.ADDR.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_COMPANY_EMAIL.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_EMAIL;
                continue;
            } else if (CONTENT_COMPANY_EMAIL.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 企业电子邮箱
                map.put(CreepersConstant.TCreepersEntBasicColumn.EMAIL.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersEntBasicColumn.EMAIL.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_COMPANY_IS_TRANSFER.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_IS_TRANSFER;
                continue;
            } else if (CONTENT_COMPANY_IS_TRANSFER.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 有限责任公司本年度是否发生股东股权转让
                map.put(CreepersConstant.TCreepersEntBasicColumn.IS_TRANSFER.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersEntBasicColumn.IS_TRANSFER.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_COMPANY_OPERATING_STATUS.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_OPERATING_STATUS;
                continue;
            } else if (CONTENT_COMPANY_OPERATING_STATUS.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 企业经营状态
                map.put(CreepersConstant.TCreepersEntBasicColumn.STATUS.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersEntBasicColumn.STATUS.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_COMPANY_IS_WEBSITE.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_IS_WEBSITE;
                continue;
            } else if (CONTENT_COMPANY_IS_WEBSITE.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 是否有网站或网店
                map.put(CreepersConstant.TCreepersEntBasicColumn.IS_WEBSITE.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersEntBasicColumn.IS_WEBSITE.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_COMPANY_IS_INVEST.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_IS_INVEST;
                continue;
            } else if (CONTENT_COMPANY_IS_INVEST.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 是否有投资信息或购买其他公司股权
                map.put(CreepersConstant.TCreepersEntBasicColumn.IS_INVEST.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersEntBasicColumn.IS_INVEST.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_COMPANY_CREW_NUMBER.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_CREW_NUMBER;
                continue;
            } else if (CONTENT_COMPANY_CREW_NUMBER.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 从业人数
                map.put(CreepersConstant.TCreepersEntBasicColumn.CREW_NUMBER.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersEntBasicColumn.CREW_NUMBER.getValue() + SPILT_COLON + currentContent);
            }
        }
        logger.info("======>>   finish   getNdbgBasic:");
    }

    // 年报-网站或网店信息
    public void getNdbgWebInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoAndYear) {
        logger.info("======>>   entry   getNdbgWebInfo:");
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_WEB.getMapKey());
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        int trNum = 0;
        for (Selectable trSel : trList) {
            // 存放单条的Property数据
            Map<String, String> map = new HashMap<String, String>();

            trNum++;
            // 头两个tr是表名和列名，不取
            if (trNum < 3) {
                continue;
            }
            // 最后一个tr是假分页，不取
            if (trNum == trList.size()) {
                continue;
            }
            // 内容为空的不取
            if (StringUtils.isBlank(trSel.xpath(XPATH_ALL_TEXT).nodes().get(0).toString())) {
                continue;
            }
            // 获取一行的Property数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            if (tdList.size() != 3) {
                logger.info("=============>>>当前表格不匹配，无法解析.");
                continue;
            }
            // 添加类型
            map.put(CreepersConstant.TCreepersEntWebColumn.TYPE.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntWebColumn.TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.TYPE.getValue()));

            // 添加名称
            map.put(CreepersConstant.TCreepersEntWebColumn.NAME.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntWebColumn.NAME.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.NAME.getValue()));

            // 添加网址
            map.put(CreepersConstant.TCreepersEntWebColumn.URL.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersEntWebColumn.URL.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.URL.getValue()));

            // 添加注册号和报送年度
            map.putAll(merNoAndYear);
            logger.info(CreepersConstant.TCreepersEntWebColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.MER_NO.getValue()));
            logger.info(CreepersConstant.TCreepersEntWebColumn.MEMO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.MEMO.getValue()));
            list.add(map);
            list.add(map);
        }
        logger.info("======>>   end   getNdbgWebInfo:");
    }

    // 年报-股东及出资信息
    public void getNdbgShareInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoAndYear) {
        logger.info("======>>   entry   getNdbgShareInfo:");
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_WEB.getMapKey());
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        int trNum = 0;
        for (Selectable trSel : trList) {
            // 存放单条的Property数据
            Map<String, String> map = new HashMap<String, String>();

            trNum++;
            // 头两个tr是表名和列名，不取
            if (trNum < 3) {
                continue;
            }
            // 最后一个tr是假分页，不取
            if (trNum == trList.size()) {
                continue;
            }
            // 内容为空的不取
            if (StringUtils.isBlank(trSel.xpath(XPATH_ALL_TEXT).nodes().get(0).toString())) {
                continue;
            }
            // 获取一行的Property数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            if (tdList.size() != 7) {
                logger.info("=============>>>当前表格不匹配，无法解析.");
                continue;
            }
            // 添加股东
            map.put(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue()));

            // 添加认缴出资额（万元）
            map.put(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue()));

            // 添加认缴出资时间
            map.put(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()));

            // 添加认缴出资方式
            map.put(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue()));

            // 添加实缴出资额（万元）
            map.put(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue()));

            // 添加出资时间
            map.put(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()));

            // 添加出资方式
            map.put(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue(), tdList.get(6).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue()));

            // 添加注册号和报送年度
            map.putAll(merNoAndYear);
            logger.info(CreepersConstant.TCreepersEntShareColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.MER_NO.getValue()));
            logger.info(CreepersConstant.TCreepersEntShareColumn.MEMO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.MEMO.getValue()));
            list.add(map);
        }
        logger.info("======>>   end   getNdbgShareInfo:");
    }

    // 年报-对外投资信息
    public void getNdbgInvestInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoAndYear) {
        logger.info("======>>   entry   getNdbgInvestInfo:");
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_INVEST.getMapKey());
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        int trNum = 0;
        for (Selectable trSel : trList) {
            // 存放单条的Property数据
            Map<String, String> map = new HashMap<String, String>();

            trNum++;
            // 头两个tr是表名和列名，不取
            if (trNum < 3) {
                continue;
            }
            // 最后一个tr是假分页，不取
            if (trNum == trList.size()) {
                continue;
            }
            // 内容为空的不取
            if (StringUtils.isBlank(trSel.xpath(XPATH_ALL_TEXT).nodes().get(0).toString())) {
                continue;
            }
            // 获取一行的Property数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            if (tdList.size() != 2) {
                logger.info("=============>>>当前表格不匹配，无法解析.");
                continue;
            }
            // 添加注册号和报送年度
            map.putAll(merNoAndYear);
            logger.info(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue()));
            logger.info(CreepersConstant.TCreepersEntInvestColumn.MEMO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.MEMO.getValue()));

            // 添加投资设立企业或购买股权企业名称
            map.put(CreepersConstant.TCreepersEntInvestColumn.INVESTED_NAME.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntInvestColumn.INVESTED_NAME.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.INVESTED_NAME.getValue()));

            // 添加注册号/统一社会信用代码
            map.put(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue()));

            list.add(map);
        }
        logger.info("======>>   end   getNdbgInvestInfo:");
    }

    // 年报-企业资产状况信息
    public void getNdbgAssetInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoAndYear) {

        logger.info("======>>   entry   getNdbgAssetInfo:");
        List<Selectable> tdThList = tableSel.xpath(XPATH_TBODY + XPATH_TR + XPATH_SPILT).nodes();
        Map<String, String> map = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_ASSET.getMapKey());
        // 添加注册号和报送年度
        map.putAll(merNoAndYear);
        logger.info(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue()
                + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue()));
        logger.info(CreepersConstant.TCreepersEntInvestColumn.MEMO.getValue()
                + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.MEMO.getValue()));
        int tdThNum = 0;
        String lastContent = CONTENT_EMPTY;
        for (Selectable eachTdTh : tdThList) {
            tdThNum++;
            if (tdThNum < 2) {
                continue;
            }
            String currentContent = eachTdTh.xpath(XPATH_ALL_TEXT).toString();
            logger.info("currentContent:" + currentContent);
            if (CONTENT_COMPANY_TOTAL_ASSET.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_TOTAL_ASSET;
                continue;
            } else if (CONTENT_COMPANY_TOTAL_ASSET.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 资产总额
                map.put(CreepersConstant.TCreepersEntAssetColumn.TOTAL_ASSET.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersEntAssetColumn.TOTAL_ASSET.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_COMPANY_TOTAL_EQUITY.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_TOTAL_EQUITY;
                continue;
            } else if (CONTENT_COMPANY_TOTAL_EQUITY.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 所有者权益合计
                map.put(CreepersConstant.TCreepersEntAssetColumn.TOTAL_EQUITY.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersEntAssetColumn.TOTAL_EQUITY.getValue()
                        + SPILT_COLON + currentContent);
            } else if (CONTENT_COMPANY_TOTAL_LIABILITIES.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_TOTAL_LIABILITIES;
                continue;
            } else if (CONTENT_COMPANY_TOTAL_LIABILITIES.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 负债总额
                map.put(CreepersConstant.TCreepersEntAssetColumn.TOTAL_LIABILITIES.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersEntAssetColumn.TOTAL_LIABILITIES.getValue()
                        + SPILT_COLON + currentContent);
            } else if (CONTENT_COMPANY_TOTAL_INCOME.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_TOTAL_INCOME;
                continue;
            } else if (CONTENT_COMPANY_TOTAL_INCOME.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 营业总收入
                map.put(CreepersConstant.TCreepersEntAssetColumn.TOTAL_INCOME.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersEntAssetColumn.TOTAL_INCOME.getValue()
                        + SPILT_COLON + currentContent);
            } else if (CONTENT_COMPANY_TOTAL_AVENUE.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_TOTAL_AVENUE;
                continue;
            } else if (CONTENT_COMPANY_TOTAL_AVENUE.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 利润总额
                map.put(CreepersConstant.TCreepersEntAssetColumn.TOTAL_AVENUE.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersEntAssetColumn.TOTAL_AVENUE.getValue()
                        + SPILT_COLON + currentContent);
            } else if (CONTENT_COMPANY_TOTAL_BUS_INCOME.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_TOTAL_BUS_INCOME;
                continue;
            } else if (CONTENT_COMPANY_TOTAL_BUS_INCOME.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 营业总收入中主营业务收入
                map.put(CreepersConstant.TCreepersEntAssetColumn.TOTAL_BUS_INCOME.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersEntAssetColumn.TOTAL_BUS_INCOME.getValue()
                        + SPILT_COLON + currentContent);
            } else if (CONTENT_COMPANY_NET_PROFIT.equals(currentContent)) {
                lastContent = CONTENT_COMPANY_NET_PROFIT;
                continue;
            } else if (CONTENT_COMPANY_NET_PROFIT.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 净利润
                map.put(CreepersConstant.TCreepersEntAssetColumn.NET_PROFIT.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersEntAssetColumn.NET_PROFIT.getValue() + SPILT_COLON + currentContent);
            }
        }
        logger.info("======>>   finish   getNdbgAssetInfo:");
    }

    // 年报-对外提供保证担保信息
    public void getNdbgWarrantInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoAndYear) {
        logger.info("======>>   entry   getNdbgWarrantInfo:");
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_WARRANT.getMapKey());
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        int trNum = 0;
        for (Selectable trSel : trList) {
            // 存放单条的Property数据
            Map<String, String> map = new HashMap<String, String>();

            trNum++;
            // 头两个tr是表名和列名，不取
            if (trNum < 3) {
                continue;
            }
            // 最后一个tr是假分页，不取
            if (trNum == trList.size()) {
                continue;
            }
            // 内容为空的不取
            if (StringUtils.isBlank(trSel.xpath(XPATH_ALL_TEXT).nodes().get(0).toString())) {
                continue;
            }
            // 获取一行的Property数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            if (tdList.size() != 7) {
                logger.info("=============>>>当前表格不匹配，无法解析.");
                continue;
            }
            // 添加注册号和报送年度
            map.putAll(merNoAndYear);
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.MER_NO.getValue()));
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.MEMO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.MEMO.getValue()));

            // 添加债权人
            map.put(CreepersConstant.TCreepersEntWarrantColumn.CREDITOR.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.CREDITOR.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.CREDITOR.getValue()));

            // 添加债务人
            map.put(CreepersConstant.TCreepersEntWarrantColumn.OBLIGOR.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.OBLIGOR.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.OBLIGOR.getValue()));

            // 添加主债权种类
            map.put(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_TYPE.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_TYPE.getValue()));

            // 添加主债权数额
            map.put(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_AMOUNT.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_AMOUNT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_AMOUNT.getValue()));

            // 添加履行债务的期限
            map.put(CreepersConstant.TCreepersEntWarrantColumn.DEBT_PERIOD.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.DEBT_PERIOD.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.DEBT_PERIOD.getValue()));

            // 添加保证的期间
            map.put(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_PERIOD.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_PERIOD.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_PERIOD.getValue()));

            // 添加保证的方式
            map.put(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_TYPE.getValue(), tdList.get(6).toString());
            logger.info(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_TYPE.getValue()));

            list.add(map);
        }
        logger.info("======>>   end   getNdbgWarrantInfo:");
    }

    // 年报-股权变更
    public void getNdbgEquityInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoAndYear) {
        logger.info("======>>   entry   getNdbgEquityInfo:");
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_SHARE_CHANGE.getMapKey());
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        int trNum = 0;
        for (Selectable trSel : trList) {
            // 存放单条的Property数据
            Map<String, String> map = new HashMap<String, String>();

            trNum++;
            // 头两个tr是表名和列名，不取
            if (trNum < 3) {
                continue;
            }
            // 最后一个tr是假分页，不取
            if (trNum == trList.size()) {
                continue;
            }
            // 内容为空的不取
            if (StringUtils.isBlank(trSel.xpath(XPATH_ALL_TEXT).nodes().get(0).toString())) {
                continue;
            }
            // 获取一行的Property数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            if (tdList.size() != 4) {
                logger.info("=============>>>当前表格不匹配，无法解析.");
                continue;
            }
            // 添加注册号和报送年度
            map.putAll(merNoAndYear);
            logger.info(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue()));
            logger.info(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue()));

            // 添加股东
            map.put(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue()));

            // 添加变更前股权比例
            map.put(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue()));

            // 添加变更后股权比例
            map.put(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue()));

            // 添加股权变更日期
            map.put(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue()));

            list.add(map);
        }
        logger.info("======>>   end   getNdbgEquityInfo:");
    }

    // 年报-修改记录
    public void getNdbgChangeInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoAndYear) {
        logger.info("======>>   entry   getNdbgChangeInfo:");
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_CHANGE.getMapKey());
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        int trNum = 0;
        for (Selectable trSel : trList) {
            // 存放单条的Property数据
            Map<String, String> map = new HashMap<String, String>();

            trNum++;
            // 头两个tr是表名和列名，不取
            if (trNum < 3) {
                continue;
            }
            // 最后一个tr是假分页，不取
            if (trNum == trList.size()) {
                continue;
            }
            // 内容为空的不取
            if (StringUtils.isBlank(trSel.xpath(XPATH_ALL_TEXT).nodes().get(0).toString())) {
                continue;
            }
            // 获取一行的Property数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            if (tdList.size() != 5) {
                logger.info("=============>>>当前表格不匹配，无法解析.");
                continue;
            }
            // 添加注册号和报送年度
            map.putAll(merNoAndYear);
            logger.info(CreepersConstant.TCreepersEntChangeColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.MER_NO.getValue()));
            logger.info(CreepersConstant.TCreepersEntChangeColumn.MEMO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.MEMO.getValue()));

            // 添加序号
            map.put(CreepersConstant.TCreepersEntChangeColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntChangeColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.SEQ_NO.getValue()));

            // 添加修改事项
            map.put(CreepersConstant.TCreepersEntChangeColumn.ITEM.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntChangeColumn.ITEM.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.ITEM.getValue()));

            // 添加修改前
            map.put(CreepersConstant.TCreepersEntChangeColumn.PRE_INFO.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersEntChangeColumn.PRE_INFO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.PRE_INFO.getValue()));

            // 修改后
            map.put(CreepersConstant.TCreepersEntChangeColumn.POST_INFO.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersEntChangeColumn.POST_INFO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.POST_INFO.getValue()));

            // 修改日期
            map.put(CreepersConstant.TCreepersEntChangeColumn.CHANGE_DT.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersEntChangeColumn.CHANGE_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.CHANGE_DT.getValue()));

            list.add(map);
        }
        logger.info("======>>   end   getNdbgChangeInfo:");
    }

    // 企业公示信息-股东及出资信息
    public void getQygsShareInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   getQygsShareInfo:");
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_SHARE.getMapKey());
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        int trNum = 0;
        for (Selectable trSel : trList) {
            // 存放单条的Property数据
            Map<String, String> map = new HashMap<String, String>();

            // 头三个tr是表名和列名，不取
            if (trNum < 3) {
                trNum++;
                continue;
            }
            // 获取一行的Property数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            if (tdList.size() != 11) {
                logger.info("=============>>>当前表格不匹配，无法解析.");
                continue;
            }
            // 添加注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersEntShareColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.MER_NO.getValue()));

            // 添加股东
            map.put(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue()));

            // 添加认缴额（万元）
            map.put(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue()));

            // 添加实缴额（万元）
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
            map.put(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()));
            // 公示日期
            map.put(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue(), tdList.get(6).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()));
            logger.info("=============没有公示日期列先存在认缴日期中==============");
            // 实缴出资方式
            map.put(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue(), tdList.get(7).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue()));
            // 实缴出资额（万元）
            map.put(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue(), tdList.get(8).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue()));
            // 实缴出资日期
            map.put(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue(), tdList.get(9).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()));
            // 公示日期
            map.put(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue(), tdList.get(10).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()));

            list.add(map);
        }
        logger.info("======>>   end   getQygsShareInfo:");
    }

    // 企业公示信息-变更信息
    public void getQygsShareChangeInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   getQygsShareChangeInfo:");
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_CHANGE.getMapKey());
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        int trNum = 0;
        for (Selectable trSel : trList) {
            // 存放单条的Property数据
            Map<String, String> map = new HashMap<String, String>();

            // 头两个tr是表名和列名，不取
            if (trNum < 2) {
                trNum++;
                continue;
            }
            // 内容为空的不取
            if (StringUtils.isBlank(trSel.xpath(XPATH_ALL_TEXT).nodes().get(0).toString())) {
                continue;
            }
            // 获取一行的Property数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            if (tdList.size() != 5) {
                logger.info("=============>>>当前表格不匹配，无法解析.");
                continue;
            }
            // 添加注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersEntShareChangeColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.MER_NO.getValue()));

            // 添加序号
            map.put(CreepersConstant.TCreepersEntShareChangeColumn.SEQ_NO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntShareChangeColumn.SEQ_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.SEQ_NO.getValue()));

            // 添加变更事项
            map.put(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_CONTENT.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_CONTENT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_CONTENT.getValue()));

            // 添加变更时间
            map.put(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_DT.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_DT.getValue()));

            // 变更前
            map.put(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_OLD.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_OLD.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_OLD.getValue()));

            // 变更后
            map.put(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_NEW.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_NEW.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_NEW.getValue()));

            list.add(map);
        }
        logger.info("======>>   end   getQygsShareChangeInfo:");
    }

    // 企业公示信息-股权变更信息
    public void getQygsEquityInfo(Selectable tableSel, ResultItems resultItems, Map<String, String> merNoMap) {
        logger.info("======>>   entry   getQygsEquityInfo:");
        List<Map<String, String>> list = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_EQUITY.getMapKey());
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        int trNum = 0;
        for (Selectable trSel : trList) {
            // 存放单条的Property数据
            Map<String, String> map = new HashMap<String, String>();

            // 头两个tr是表名和列名，不取
            if (trNum < 2) {
                trNum++;
                continue;
            }
            // 内容为空的不取
            if (StringUtils.isBlank(trSel.xpath(XPATH_ALL_TEXT).nodes().get(0).toString())) {
                continue;
            }
            // 获取一行的Property数据
            List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
            if (tdList.size() != 6) {
                logger.info("=============>>>当前表格不匹配，无法解析.");
                continue;
            }
            // 添加注册号
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue()));

            // 添加序号
            map.put(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue(), tdList.get(0).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue()));

            // 添加股东
            map.put(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue(), tdList.get(1).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue()));

            // 变更前比例
            map.put(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue()));

            // 变更后比例
            map.put(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue()));

            // 添加变更时间
            map.put(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue()));

            // 公示时间
            map.put(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue()
                    + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue()));

            list.add(map);
        }
        logger.info("======>>   end   getQygsEquityInfo:");
    }

    public static void main(String[] args) {
        Spider.create(new BusinessInfoDetail41Processor()).addPipeline(new BusinessInfoDetailPipline())
                // 没有股东或投资人信息
                // .addUrl("http://222.143.24.157/businessPublicity.jspx?id=33D15586E0F85FA4E053050A080AED3E")
                // 有股东信息，但是分页的总数小于5
                // .addUrl("http://222.143.24.157/businessPublicity.jspx?id=36A75CD835576395E053050A080A2F4E")
                // 有股东信息，但是分页的总数大于5
                // .addUrl("http://222.143.24.157/businessPublicity.jspx?id=36F7C96588D35FDCE053050A080AB1D7")
                // 第二种样式的基本信息（投资人）
                // .addUrl("http://222.143.24.157/businessPublicity.jspx?id=33D155B5AB885FA4E053050A080AED3E")
                // 第三种样式的基本信息（经营者）
                // .addUrl("http://222.143.24.157/businessPublicity.jspx?id=33D155811EE85FA4E053050A080AED3E")
                // 有动产抵押信息的
                // .addUrl("http://222.143.24.157/businessPublicity.jspx?id=36F9A182911966AAE053050A080A5C89")
                // 有公司公示信息的
                .addUrl("http://222.143.24.157/businessPublicity.jspx?id=33D155B8B2AE5FA4E053050A080AED3E").thread(1)
                .runAsync();

        // 企业公示-->股权变更信息未完成-->3个添加链接
    }
}
