package com.fosun.fc.projects.creepers.pageprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant.TableNamesCreditReference;
import com.fosun.fc.projects.creepers.downloader.CreditReferenceSeleniumDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.pipeline.CreditReferencePipline;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.fosun.fc.projects.creepers.utils.PropertiesUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/***
 * 
 * <p>
 * r 使用Selenium做页面动态渲染。
 * </p>
 * 
 * @author MaXin 2016-5-11 15:50:12
 */
@Component("creditReferenceCenterProcessor")
public class CreditReferenceCenterProcessor implements PageProcessor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String HTML_CONTENT = "htmlContent";

    private static final String WORD_YEAR = "年";
    private static final String WORD_MONT = "月";
    private static final String WORD_MONT_COUNT = "个月";
    private static final String WORD_DAY = "日";
    private static final String WORD_GRANT = "发放的";
    private static final String WORD_CUT_OFF = "截至";
    private static final String WORD_CREDIT_LIMIT = "信用额度";
    private static final String WORD_CREDIT_LIMIT_TO_RMB = "信用额度折合人民币";
    private static final String WORD_OVERDRAFT_BALANCE = "已使用额度";
    private static final String WORD_OVERDRAFT_BALANCE_TWO = "透支余额";
    private static final String WORD_OVERDRAFT_BALANCE_THR = "透支额度";
    private static final String WORD_LOG_OUT = "销户";
    private static final String WORD_INACTIVATED = "尚未激活";
    private static final String WORD_FOR = "为";
    private static final String WORD_AT = "在";
    private static final String WORD_IN = "其中";
    private static final String WORD_HANDLED = "办理的";
    private static final String WORD_GUARANTEE_CONTRACT_AMOUNT = "担保贷款合同金额";
    private static final String WORD_GUARANTEET_AMOUNT = "担保金额";
    private static final String WORD_GUARANTEED_PRINCIPAL_BALANCE = "担保贷款本金余额";
    private static final String WORD_LAST_FIVE_YEARS = "最近5年内有";
    private static final String WORD_OVERDRAFT_SIXTY = "透支超过60天";
    // private static final String WORD_OVERDRAFT_NINETY = "透支超过90天";

    private static final String SPLIT_WORD = "-";
    private static final String SPLIT_COMMA = "，";
    private static final String SPLIT_PERIOD = "。";
    private static final String SPLIT_WORD_LEFT = "（";
    private static final String SPLIT_WORD_RIGHT = "）";

    private static final String REGEX_BETWEEN_CONTENT = "(.*)";
    private static final String REGEX_DATE_YYYY_MM_DD = "^\\d{4}年\\d+月\\d+日";
    private static final String REGEX_DATE_YYYY_MM = "^\\d{4}年\\d+月";
    private static final String REGEX_NUMBER = "^\\d+";

    private static final String XPATH_NODES_ALL_CONTENT = "/html/body/div/div/table/tbody/tr[2]/td/";
    private static final String XPATH_TD_ALL_TEXT = "//td/allText()";
    private static final String XPATH_LI_ALL_TEXT = "//li/allText()";
    private static final String XPATH_NODES_TD = "/tr/";
    private static final String XPATH_NODES_TR_CENTER = "//tr[@align='center']";
    private static final String XPATH_NODES_LI = "/ol/";
    private static final String XPATH_NODES_TR_TD_TABLE_TBODY_TR_LIST = "/table/tbody/tr/td/table/tbody/tr/td/table/tbody/";

    private static final String LABEL_NULL = "";
    private static final String LABEL_PERIOD = ".";
    private static final String LABEL_SPACE = " ";
    private static final String LABEL_BR = "<BR>";
    private static final String LABEL_START_STRONG = "<strong>";
    private static final String LABEL_END_STRONG = "</strong>";
    private static final String LABEL_END_SPAN = "</span>";
    private static final String LABEL_END_TD = "</td>";

    private static final String CONTENT_CREDIT_HEAD_ONE = "个人信用报告";
    private static final String CONTENT_RPT_NO = "报告编号：";
    private static final String CONTENT_QUERY_DT = "查询时间：";
    private static final String CONTENT_RPT_DT = "报告时间：";
    private static final String CONTENT_NAME = "姓名：";
    private static final String CONTENT_ID_TYPE = "证件类型：";
    private static final String CONTENT_ID_NO = "证件号码：";
    private static final String CONTENT_MARITAL_STATUS = "婚";
    private static final String CONTENT_INFO_SUMMARY = "信息概要";
    private static final String CONTENT_COMMON_INFO = "公共记录";
    private static final String CONTENT_QUERY_BY = "查询操作员";
    private static final String CONTENT_QUERY_REASON = "查询原因";
    private static final String CONTENT_SERIAL_NUM = "编号";
    private static final String CONTENT_CC = "信用卡";
    private static final String CONTENT_ASSET_HANDLE = "资产处理信息";
    private static final String CONTENT_COMPENSATORY = "保证人代偿信息";
    private static final String CONTENT_HOUSING_LOAN = "购房贷款";
    private static final String CONTENT_OTHER_LOAN = "其他贷款";
    private static final String CONTENT_GUARANTEE = "为他人担保信息";
    private static final String CONTENT_ACCOUNT_NO = "账户数";
    private static final String CONTENT_OUTSTANDING_ACCOUNT_NO = "未结清/未销户账户数";
    private static final String CONTENT_OVERDUE_ACCOUNT_NO = "发生过逾期的账户数";
    private static final String CONTENT_NINETY_ACCOUNT_NO = "发生过90天以上逾期的账户数";
    private static final String CONTENT_GUARANTEE_NO = "为他人担保笔数";
    private static final String CONTENT_NEVER_OVERDUE_60 = "从未逾期过的贷记卡及透支未超过60天的准贷记卡账户明细如下：";
    private static final String CONTENT_OVERDUE_60 = "透支超过60天的准贷记卡账户明细如下：";
    private static final String CONTENT_OVERDUE_ACCOUNT = "发生过逾期的准贷记卡账户明细如下：";
    private static final String CONTENT_NEVER_HL_OVERDRAFT = "从未逾期过的账户明细如下：";
    private static final String CONTENT_HL_OVERDRAFT = "发生过逾期的账户明细如下：";
    private static final String CONTENT_NO_COMMON_INFO = "系统中没有您最近5年内的欠税记录、民事判决记录、强制执行记录、行政处罚记录及电信欠费记录。";
    private static final String CONTENT_QUERY_RECORD = "这部分包含您的信用报告最近2年被查询的记录。";

    private static final String VALUE_CC_STATUS_VALID = "0";// 已激活
    private static final String VALUE_CC_STATUS_LOG_OUT = "1";// 已注销
    private static final String VALUE_CC_STATUS_INACTIVATED = "2";// 未激活

    private static final String REPLACE_META_BEFORE = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\"> ";
    private static final String REPLACE_META_AFTER = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset="
            + PropertiesUtil.getApplicationValue("file.charSet", "UTF-8")
            + "\"> \n <style type=\"text/css\">body{font-family:KaiTi_GB2312;}</style>";
    private Site site;

    @Override
    public void process(Page page) {
        // 将页面信息返回 用于生成文件。
        page.putField(HTML_CONTENT, page.getHtml().toString().replace(REPLACE_META_BEFORE, REPLACE_META_AFTER));
        Map<String, String> rptNoMap = new HashMap<String, String>();
        // 初始化putField
        TableNamesCreditReference[] getNames = CreepersConstant.TableNamesCreditReference.values();
        for (TableNamesCreditReference TableNamesCreditReference : getNames) {
            if (TableNamesCreditReference.getMapKey()
                    .equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_ACCOUNT_BAK.getMapKey())) {
                // continue;
            }
            Object obj;
            if (TableNamesCreditReference.isList()) {
                obj = new ArrayList<Map<String, Object>>();
            } else {
                obj = new HashMap<String, Object>();
            }
            page.putField(TableNamesCreditReference.getMapKey(), obj);
        }
        ResultItems resultItems = page.getResultItems();
        CreepersLoginParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        // 开始抓取页面信息
        List<Selectable> nodesList = page.getHtml().xpath(XPATH_NODES_ALL_CONTENT).nodes();
        String lastLabel = "";
        for (int i = 0; i < nodesList.size(); i++) {
            // logger.info("nodesList(" + i + "):\n" + nodesList.get(i) + "\n");
            String nodesCONTENT = nodesList.get(i).toString();
            // 换行符直接跳过
            if (LABEL_BR.equals(nodesCONTENT.trim().toUpperCase())) {
                logger.info("换行符，跳过，执行下一个。");
                continue;
                // 信用卡信息标题
            } else
                if (nodesList.get(i).regex(LABEL_START_STRONG + CONTENT_CC + LABEL_SPACE + LABEL_END_STRONG).match()) {
                lastLabel = LABEL_START_STRONG + CONTENT_CC + LABEL_SPACE + LABEL_END_STRONG;
                logger.info("开始采集信用卡信息");
                continue;
                // 查询记录标题
            } else if (nodesList.get(i).regex(CONTENT_QUERY_RECORD + LABEL_END_STRONG).match()) {
                lastLabel = CONTENT_QUERY_RECORD + LABEL_END_STRONG;
                logger.info("开始采集查询记录信息");
                continue;
                // 保证人代偿信息标题
            } else if (nodesList.get(i)
                    .regex(LABEL_START_STRONG + CONTENT_COMPENSATORY + LABEL_SPACE + LABEL_END_STRONG).match()) {
                lastLabel = LABEL_START_STRONG + CONTENT_COMPENSATORY + LABEL_SPACE + LABEL_END_STRONG;
                logger.info("开始采集保证人代偿信息");
                continue;
                // 资产处理信息标题
            } else if (nodesList.get(i)
                    .regex(LABEL_START_STRONG + CONTENT_ASSET_HANDLE + LABEL_SPACE + LABEL_END_STRONG).match()) {
                lastLabel = LABEL_START_STRONG + CONTENT_ASSET_HANDLE + LABEL_SPACE + LABEL_END_STRONG;
                logger.info("开始采集资产处理信息信息");
                continue;
                // 其他贷款标题
            } else if (nodesList.get(i).regex(LABEL_START_STRONG + CONTENT_OTHER_LOAN + LABEL_SPACE + LABEL_END_STRONG)
                    .match()) {
                lastLabel = LABEL_START_STRONG + CONTENT_OTHER_LOAN + LABEL_SPACE + LABEL_END_STRONG;
                logger.info("开始采集其他贷款信息");
                continue;
                // 购房贷款标题
            } else if (nodesList.get(i).regex(LABEL_START_STRONG + CONTENT_HOUSING_LOAN + LABEL_END_STRONG).match()) {
                lastLabel = LABEL_START_STRONG + CONTENT_HOUSING_LOAN + LABEL_END_STRONG;
                logger.info("开始采集资购房贷款信息");
                continue;
                // 个人征信报告头信息-- 报告编号 查询日期 报告日期
            } else
                if (nodesList.get(i).regex(LABEL_START_STRONG + CONTENT_CREDIT_HEAD_ONE + LABEL_END_STRONG).match()) {
                lastLabel = LABEL_START_STRONG + CONTENT_CREDIT_HEAD_ONE + LABEL_END_STRONG;
                List<Selectable> tdContentList = nodesList.get(i).xpath(XPATH_TD_ALL_TEXT).nodes();
                Map<String, String> accountMap = resultItems
                        .get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_ACCOUNT_BAK.getMapKey());
                Map<String, String> basicMap = resultItems
                        .get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_BASIC.getMapKey());
                for (Selectable selectable : tdContentList) {
                    if (selectable.regex(CONTENT_RPT_NO).match()) {
                        logger.info(CONTENT_RPT_NO
                                + selectable.regex(CONTENT_RPT_NO + REGEX_BETWEEN_CONTENT).toString().trim());
                        accountMap.put(CreepersConstant.TCreepersAccountBakColumn.RPT_NO.getValue(),
                                selectable.regex(CONTENT_RPT_NO + REGEX_BETWEEN_CONTENT).toString().trim());
                        accountMap.put(CreepersConstant.TCreepersAccountBakColumn.USR.getValue(), param.getLoginName());
                        accountMap.put(CreepersConstant.TCreepersAccountBakColumn.PWD.getValue(), param.getPassword());
                        accountMap.put(CreepersConstant.TCreepersAccountBakColumn.CDE.getValue(),
                                param.getMessageCaptchaValue());
                        rptNoMap.put(CreepersConstant.TCreepersAccountBakColumn.RPT_NO.getValue(),
                                selectable.regex(CONTENT_RPT_NO + REGEX_BETWEEN_CONTENT).toString().trim());
                    } else if (selectable.regex(CONTENT_QUERY_DT).match()) {
                        logger.info(CONTENT_QUERY_DT
                                + selectable.regex(CONTENT_QUERY_DT + REGEX_BETWEEN_CONTENT).toString().trim());
                        basicMap.put(CreepersConstant.TCreepersBasicColumn.QUERY_DT.getValue(),
                                selectable.regex(CONTENT_QUERY_DT + REGEX_BETWEEN_CONTENT).toString());
                        basicMap.putAll(rptNoMap);
                    } else if (selectable.regex(CONTENT_RPT_DT).match()) {
                        logger.info(CONTENT_RPT_DT
                                + selectable.regex(CONTENT_RPT_DT + REGEX_BETWEEN_CONTENT).toString().trim());
                        basicMap.put(CreepersConstant.TCreepersBasicColumn.RPT_DT.getValue(),
                                selectable.regex(CONTENT_RPT_DT + REGEX_BETWEEN_CONTENT).toString());
                    }
                }
                // 个人征信报告头信息-- 姓名 证件类型 证件号 婚姻状况
            } else if ((LABEL_START_STRONG + CONTENT_CREDIT_HEAD_ONE + LABEL_END_STRONG).equals(lastLabel)) {
                lastLabel = LABEL_NULL;
                List<Selectable> tdContentList = nodesList.get(i).xpath(XPATH_TD_ALL_TEXT).nodes();
                Map<String, String> basicMap = resultItems
                        .get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_BASIC.getMapKey());
                for (Selectable selectable : tdContentList) {
                    if (selectable.regex(CONTENT_NAME).match()) {
                        logger.info(CONTENT_NAME
                                + selectable.regex(CONTENT_NAME + REGEX_BETWEEN_CONTENT).toString().trim());
                        basicMap.put(CreepersConstant.TCreepersBasicColumn.NAME.getValue(),
                                selectable.regex(CONTENT_NAME + REGEX_BETWEEN_CONTENT).toString().trim());
                    } else if (selectable.regex(CONTENT_ID_TYPE).match()) {
                        logger.info(CONTENT_ID_TYPE
                                + selectable.regex(CONTENT_ID_TYPE + REGEX_BETWEEN_CONTENT).toString().trim());
                        basicMap.put(CreepersConstant.TCreepersBasicColumn.ID_TYPE.getValue(),
                                selectable.regex(CONTENT_ID_TYPE + REGEX_BETWEEN_CONTENT).toString().trim());
                    } else if (selectable.regex(CONTENT_ID_NO).match()) {
                        logger.info(CONTENT_ID_NO
                                + selectable.regex(CONTENT_ID_NO + REGEX_BETWEEN_CONTENT).toString().trim());
                        basicMap.put(CreepersConstant.TCreepersBasicColumn.ID_NO.getValue(),
                                selectable.regex(CONTENT_ID_NO + REGEX_BETWEEN_CONTENT).toString().trim());
                    } else if (selectable.regex(CONTENT_MARITAL_STATUS).match()) {
                        logger.info(CONTENT_MARITAL_STATUS + selectable.regex(REGEX_BETWEEN_CONTENT).toString().trim());
                        basicMap.put(CreepersConstant.TCreepersBasicColumn.MARITAL_STATUS.getValue(),
                                selectable.regex(REGEX_BETWEEN_CONTENT).toString().trim());
                    }
                }
                // 信用卡信息概要
            } else if (nodesList.get(i).regex(CONTENT_INFO_SUMMARY + LABEL_END_SPAN).match()) {
                lastLabel = LABEL_NULL;
                List<Selectable> trContentList = nodesList.get(i).xpath(XPATH_NODES_TR_TD_TABLE_TBODY_TR_LIST).nodes();
                Map<String, String> genralMap = resultItems
                        .get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_GENERAL.getMapKey());
                genralMap.putAll(rptNoMap);
                for (Selectable eachTrContentList : trContentList) {
                    List<Selectable> tdContentList = eachTrContentList.xpath(XPATH_NODES_TD).nodes();
                    if (tdContentList.get(1).regex(CONTENT_CC + LABEL_SPACE + LABEL_END_TD).match()) {
                        logger.info("第一行标题栏，不处理开始下一行处理");
                        continue;
                    } else if (tdContentList.get(0).regex(CONTENT_OUTSTANDING_ACCOUNT_NO + LABEL_SPACE + LABEL_END_TD)
                            .match()) {
                        logger.info(CONTENT_CC
                                + CONTENT_OUTSTANDING_ACCOUNT_NO
                                + tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        genralMap.put(CreepersConstant.TCreepersGeneralColumn.OUTSTANDING_CC_NO.getValue(),
                                tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        logger.info(CONTENT_HOUSING_LOAN
                                + CONTENT_OUTSTANDING_ACCOUNT_NO
                                + tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        genralMap.put(CreepersConstant.TCreepersGeneralColumn.OUTSTANDING_HOUSING_LOAN_NO.getValue(),
                                tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        logger.info(CONTENT_OTHER_LOAN
                                + CONTENT_OUTSTANDING_ACCOUNT_NO
                                + tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        genralMap.put(CreepersConstant.TCreepersGeneralColumn.OUTSTANDING_OTHER_LOAN_NO.getValue(),
                                tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    } else if (tdContentList.get(0).regex(CONTENT_OVERDUE_ACCOUNT_NO + LABEL_SPACE + LABEL_END_TD)
                            .match()) {
                        logger.info(CONTENT_CC
                                + CONTENT_OVERDUE_ACCOUNT_NO
                                + tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        genralMap.put(CreepersConstant.TCreepersGeneralColumn.OVERDUE_CC_NO.getValue(),
                                tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        logger.info(CONTENT_HOUSING_LOAN
                                + CONTENT_OVERDUE_ACCOUNT_NO
                                + tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        genralMap.put(CreepersConstant.TCreepersGeneralColumn.OVERDUE_HOUSING_LOAN_NO.getValue(),
                                tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        logger.info(CONTENT_OTHER_LOAN
                                + CONTENT_OVERDUE_ACCOUNT_NO
                                + tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        genralMap.put(CreepersConstant.TCreepersGeneralColumn.OVERDUE_OTHER_LOAN_NO.getValue(),
                                tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    } else if (tdContentList.get(0).regex(CONTENT_NINETY_ACCOUNT_NO + LABEL_SPACE + LABEL_END_TD)
                            .match()) {
                        logger.info(CONTENT_CC
                                + CONTENT_NINETY_ACCOUNT_NO
                                + tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        genralMap.put(CreepersConstant.TCreepersGeneralColumn.NINETY_CC_NO.getValue(),
                                tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        logger.info(CONTENT_HOUSING_LOAN
                                + CONTENT_NINETY_ACCOUNT_NO
                                + tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        genralMap.put(CreepersConstant.TCreepersGeneralColumn.NINETY_HOUSING_LOAN_NO.getValue(),
                                tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        logger.info(CONTENT_OTHER_LOAN
                                + CONTENT_NINETY_ACCOUNT_NO
                                + tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        genralMap.put(CreepersConstant.TCreepersGeneralColumn.NINETY_OTHER_LOAN_NO.getValue(),
                                tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    } else if (tdContentList.get(0).regex(CONTENT_GUARANTEE_NO + LABEL_SPACE + LABEL_END_TD).match()) {
                        logger.info(CONTENT_CC
                                + CONTENT_GUARANTEE_NO
                                + tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        genralMap.put(CreepersConstant.TCreepersGeneralColumn.GUARANTEE_CC_NO.getValue(),
                                tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        logger.info(CONTENT_HOUSING_LOAN
                                + CONTENT_GUARANTEE_NO
                                + tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        genralMap.put(CreepersConstant.TCreepersGeneralColumn.GUARANTEE_HOUSING_LOAN_NO.getValue(),
                                tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        logger.info(CONTENT_OTHER_LOAN
                                + CONTENT_GUARANTEE_NO
                                + tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        genralMap.put(CreepersConstant.TCreepersGeneralColumn.GUARANTEE_OTHER_LOAN_NO.getValue(),
                                tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    } else if (tdContentList.get(0).regex(CONTENT_ACCOUNT_NO + LABEL_SPACE + LABEL_END_TD).match()) {
                        logger.info(CONTENT_CC
                                + CONTENT_ACCOUNT_NO + tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        genralMap.put(CreepersConstant.TCreepersGeneralColumn.CC_NO.getValue(),
                                tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        logger.info(CONTENT_HOUSING_LOAN
                                + CONTENT_ACCOUNT_NO + tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        genralMap.put(CreepersConstant.TCreepersGeneralColumn.HOUSING_LOAN_NO.getValue(),
                                tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        logger.info(CONTENT_OTHER_LOAN
                                + CONTENT_ACCOUNT_NO + tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                        genralMap.put(CreepersConstant.TCreepersGeneralColumn.OTHER_LOAN_NO.getValue(),
                                tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    }
                }
                // 信用卡信息内容
            } else if ((LABEL_START_STRONG + CONTENT_CC + LABEL_SPACE + LABEL_END_STRONG).equals(lastLabel)) {
                lastLabel = LABEL_NULL;
                List<Selectable> olContentList = nodesList.get(i).xpath(XPATH_NODES_LI).nodes();
                String lastSpan = "";
                List<Map<String, String>> ccAccountList = resultItems
                        .get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_CC_DETAIL.getMapKey());
                for (Selectable liContent : olContentList) {
                    Map<String, String> ccDetaillMap = new HashMap<String, String>();
                    ccDetaillMap.putAll(rptNoMap);
                    if (liContent.regex(CONTENT_NEVER_OVERDUE_60 + LABEL_END_STRONG).match()) {
                        lastSpan = CONTENT_NEVER_OVERDUE_60;
                        continue;
                    } else if ((CONTENT_OVERDUE_60 + LABEL_END_STRONG).equals(lastSpan)) {
                        lastSpan = CONTENT_OVERDUE_60;
                        continue;
                    } else if ((CONTENT_OVERDUE_ACCOUNT + LABEL_END_STRONG).equals(lastSpan)) {
                        lastSpan = CONTENT_OVERDUE_ACCOUNT;
                        continue;
                    } else if (CONTENT_NEVER_OVERDUE_60.equals(lastSpan)) {
                        logger.info(CONTENT_NEVER_OVERDUE_60 + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                        String memo = liContent.xpath(XPATH_LI_ALL_TEXT).toString();
                        // liContent.xpath(XPATH_LI_ALL_TEXT).regex("").all();
                        ccDetaillMap.put(CreepersConstant.TCreepersCcDetailColumn.MEMO.getValue(),
                                (ccAccountList.size() + 1) + LABEL_PERIOD + memo);
                        // analyzeCcDetailNeverOverDue(memo, ccDetaillMap);
                        ccAccountList.add(ccDetaillMap);
                    } else if (CONTENT_OVERDUE_60.equals(lastSpan)) {
                        logger.info(CONTENT_OVERDUE_60 + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                        String memo = liContent.xpath(XPATH_LI_ALL_TEXT).toString();
                        ccDetaillMap.put(CreepersConstant.TCreepersCcDetailColumn.MEMO.getValue(),
                                (ccAccountList.size() + 1) + LABEL_PERIOD + memo);
                        // analyzeCcDetailOverDue(memo, ccDetaillMap);
                        ccAccountList.add(ccDetaillMap);
                    } else if (CONTENT_OVERDUE_ACCOUNT.equals(lastSpan)) {
                        logger.info(CONTENT_OVERDUE_ACCOUNT + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                        ccDetaillMap.put(CreepersConstant.TCreepersCcDetailColumn.MEMO.getValue(),
                                (ccAccountList.size() + 1)
                                        + LABEL_PERIOD + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                        ccAccountList.add(ccDetaillMap);
                    }
                }
                // 购房贷款信息内容
            } else if ((LABEL_START_STRONG + CONTENT_HOUSING_LOAN + LABEL_END_STRONG).equals(lastLabel)) {
                lastLabel = LABEL_NULL;
                List<Selectable> olContentList = nodesList.get(i).xpath(XPATH_NODES_LI).nodes();
                String lastSpan = "";
                List<Map<String, String>> hlDetailList = resultItems
                        .get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_HL_DETAIL.getMapKey());
                for (Selectable liContent : olContentList) {
                    Map<String, String> hlDetailMap = new HashMap<String, String>();
                    hlDetailMap.putAll(rptNoMap);
                    if (liContent.regex(CONTENT_HL_OVERDRAFT + LABEL_END_STRONG).match()) {
                        lastSpan = CONTENT_HL_OVERDRAFT;
                        continue;
                    } else if (liContent.regex(CONTENT_NEVER_HL_OVERDRAFT + LABEL_END_STRONG).match()) {
                        lastSpan = CONTENT_NEVER_HL_OVERDRAFT;
                        continue;
                    } else if (CONTENT_HL_OVERDRAFT.equals(lastSpan)) {
                        logger.info(CONTENT_HL_OVERDRAFT + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                        hlDetailMap.put(CreepersConstant.TCreepersHlDetailColumn.MEMO.getValue(),
                                (hlDetailList.size() + 1)
                                        + LABEL_PERIOD + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                        hlDetailList.add(hlDetailMap);
                    } else if (CONTENT_NEVER_HL_OVERDRAFT.equals(lastSpan)) {
                        logger.info(CONTENT_NEVER_HL_OVERDRAFT + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                        hlDetailMap.put(CreepersConstant.TCreepersHlDetailColumn.MEMO.getValue(),
                                (hlDetailList.size() + 1)
                                        + LABEL_PERIOD + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                        hlDetailList.add(hlDetailMap);
                    }
                }
                // 保证人代偿信息内容
            } else if ((LABEL_START_STRONG + CONTENT_COMPENSATORY + LABEL_SPACE + LABEL_END_STRONG).equals(lastLabel)) {
                lastLabel = LABEL_NULL;
                List<Selectable> olContentList = nodesList.get(i).xpath(XPATH_NODES_LI).nodes();
                List<Map<String, String>> compensatoryList = resultItems
                        .get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_COMPENSATORY.getMapKey());
                for (Selectable liContent : olContentList) {
                    Map<String, String> compensatoryMap = new HashMap<String, String>();
                    compensatoryMap.putAll(rptNoMap);
                    logger.info(CONTENT_COMPENSATORY + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    compensatoryMap.put(CreepersConstant.TCreepersCompensatoryColumn.MEMO.getValue(),
                            (compensatoryList.size() + 1)
                                    + LABEL_PERIOD + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    compensatoryList.add(compensatoryMap);
                }
                // 资产处理信息内容
            } else if ((LABEL_START_STRONG + CONTENT_ASSET_HANDLE + LABEL_SPACE + LABEL_END_STRONG).equals(lastLabel)) {
                lastLabel = LABEL_NULL;
                List<Selectable> olContentList = nodesList.get(i).xpath(XPATH_NODES_LI).nodes();
                List<Map<String, String>> assetHandleList = resultItems
                        .get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_ASSET_HANDLE.getMapKey());
                for (Selectable liContent : olContentList) {
                    Map<String, String> assetHandleMap = new HashMap<String, String>();
                    assetHandleMap.putAll(rptNoMap);
                    logger.info(CONTENT_ASSET_HANDLE + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    assetHandleMap.put(CreepersConstant.TCreepersAssetHandleColumn.MEMO.getValue(),
                            (assetHandleList.size() + 1)
                                    + LABEL_PERIOD + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    assetHandleList.add(assetHandleMap);
                }
                // 其他贷款信息内容
            } else if ((LABEL_START_STRONG + CONTENT_OTHER_LOAN + LABEL_SPACE + LABEL_END_STRONG).equals(lastLabel)) {
                lastLabel = LABEL_NULL;
                List<Selectable> olContentList = nodesList.get(i).xpath(XPATH_NODES_LI).nodes();
                String lastSpan = "";
                List<Map<String, String>> olHandleList = resultItems
                        .get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_OL_DETAIL.getMapKey());
                for (Selectable liContent : olContentList) {
                    Map<String, String> olHandleMap = new HashMap<String, String>();
                    olHandleMap.putAll(rptNoMap);
                    if (liContent.regex(CONTENT_HL_OVERDRAFT + LABEL_END_STRONG).match()) {
                        lastSpan = CONTENT_HL_OVERDRAFT;
                        continue;
                    } else if (liContent.regex(CONTENT_NEVER_HL_OVERDRAFT + LABEL_END_STRONG).match()) {
                        lastSpan = CONTENT_NEVER_HL_OVERDRAFT;
                        continue;
                    } else if (CONTENT_HL_OVERDRAFT.equals(lastSpan)) {
                        logger.info(CONTENT_OTHER_LOAN + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                        olHandleMap.put(CreepersConstant.TCreepersOlDetailColumn.MEMO.getValue(),
                                (olHandleList.size() + 1)
                                        + LABEL_PERIOD + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    } else if (CONTENT_NEVER_HL_OVERDRAFT.equals(lastSpan)) {
                        logger.info(CONTENT_OTHER_LOAN + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                        olHandleMap.put(CreepersConstant.TCreepersOlDetailColumn.MEMO.getValue(),
                                (olHandleList.size() + 1)
                                        + LABEL_PERIOD + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    }
                }
                // 为他人担保信息内容
            } else if ((LABEL_START_STRONG + CONTENT_GUARANTEE + LABEL_SPACE + LABEL_END_STRONG).equals(lastLabel)) {
                lastLabel = LABEL_NULL;
                List<Selectable> olContentList = nodesList.get(i).xpath(XPATH_NODES_LI).nodes();
                List<Map<String, String>> guaranteeList = resultItems
                        .get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_GUARANTEE.getMapKey());
                for (Selectable liContent : olContentList) {
                    Map<String, String> guaranteeMap = new HashMap<String, String>();
                    guaranteeMap.putAll(rptNoMap);
                    logger.info(CONTENT_ASSET_HANDLE + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    String memo = liContent.xpath(XPATH_LI_ALL_TEXT).toString();
                    guaranteeMap.put(CreepersConstant.TCreepersGuaranteeColumn.MEMO.getValue(),
                            (guaranteeList.size() + 1) + LABEL_PERIOD + memo);
                    // analyzeGuarantee(memo, guaranteeMap);
                    guaranteeList.add(guaranteeMap);
                }
                // 公共信息内容
            } else if (nodesList.get(i).regex(CONTENT_COMMON_INFO + LABEL_SPACE + LABEL_END_TD).match()) {
                lastLabel = LABEL_NULL;
                if (nodesList.get(i).regex(CONTENT_NO_COMMON_INFO + LABEL_SPACE + LABEL_END_STRONG).match()) {
                    logger.info("没有公共信息");
                    continue;
                } else {
                    logger.info("暂时没有公共信息数据格式，无法抽取");
                }
                // 查询记录内容
            } else if ((CONTENT_QUERY_RECORD + LABEL_END_STRONG).equals(lastLabel)) {
                lastLabel = LABEL_NULL;
                List<Selectable> trContentList = nodesList.get(i).xpath(XPATH_NODES_TR_CENTER).nodes();
                List<Map<String, String>> queryLogList = resultItems
                        .get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_QUERY_LOG.getMapKey());
                for (Selectable eachtrContentList : trContentList) {
                    Map<String, String> queryLogMap = new HashMap<String, String>();
                    queryLogMap.putAll(rptNoMap);
                    if (eachtrContentList.regex(LABEL_START_STRONG + CONTENT_SERIAL_NUM + LABEL_END_STRONG).match()) {
                        logger.info("第一列不抽取");
                        continue;
                    }
                    List<Selectable> tdContentList = eachtrContentList.xpath(XPATH_TD_ALL_TEXT).nodes();
                    logger.info(CONTENT_SERIAL_NUM + tdContentList.get(0).toString());
                    logger.info(CONTENT_QUERY_DT + tdContentList.get(1).toString());
                    queryLogMap.put(CreepersConstant.TCreepersQueryLogColumn.QUERY_DT.getValue(),
                            tdContentList.get(1).toString().replace(WORD_YEAR, SPLIT_WORD)
                                    .replace(WORD_MONT, SPLIT_WORD).replace(WORD_DAY, LABEL_NULL));
                    logger.info(CONTENT_QUERY_BY + tdContentList.get(2).toString());
                    queryLogMap.put(CreepersConstant.TCreepersQueryLogColumn.QUERY_BY.getValue(),
                            tdContentList.get(2).toString());
                    logger.info(CONTENT_QUERY_REASON + tdContentList.get(3).toString());
                    queryLogMap.put(CreepersConstant.TCreepersQueryLogColumn.QUERY_REASON.getValue(),
                            tdContentList.get(3).toString());
                    queryLogList.add(queryLogMap);
                }
            }

        }
    }

    @SuppressWarnings("unused")
    private void analyzeGuarantee(String content, Map<String, String> map) {
        Result parse = ToAnalysis.parse(content.replaceAll(",", ""));
        String lastWord = "";
        StringBuffer tempWord = new StringBuffer();
        int wordCount = 0;
        boolean isFirstTime = true;
        for (Term term : parse.getTerms()) {
            logger.info("========================================");
            String currentWord = term.getName();
            logger.info(currentWord);
            if (lastWord.isEmpty()) {
                lastWord = currentWord;
                logger.info("====>>"
                        + currentWord.replace(WORD_YEAR, SPLIT_WORD).replace(WORD_MONT, SPLIT_WORD).replace(WORD_DAY,
                                LABEL_NULL));
            } else if (WORD_FOR.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (WORD_FOR.equals(lastWord)) {
                if (!SPLIT_WORD_LEFT.equals(currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                } else if (SPLIT_WORD_LEFT.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersGuaranteeColumn.INSURED_NAME.getValue(), tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    isFirstTime = true;
                    lastWord = currentWord;
                } else {
                    tempWord.append(currentWord);
                    wordCount++;
                    continue;
                }
            } else if (CONTENT_ID_TYPE.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (CONTENT_ID_TYPE.equals(lastWord)) {
                if (!SPLIT_COMMA.equals(currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                } else if (SPLIT_COMMA.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersGuaranteeColumn.INSURED_ID_TYPE.getValue(), tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    isFirstTime = true;
                    lastWord = currentWord;
                } else {
                    tempWord.append(currentWord);
                    wordCount++;
                    continue;
                }
            } else if (CONTENT_ID_NO.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (CONTENT_ID_NO.equals(lastWord)) {
                if (!SPLIT_WORD_RIGHT.equals(currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                } else if (SPLIT_WORD_RIGHT.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersGuaranteeColumn.INSURED_ID_NO.getValue(), tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    isFirstTime = true;
                    lastWord = currentWord;
                } else {
                    tempWord.append(currentWord);
                    wordCount++;
                    continue;
                }
            } else if (WORD_AT.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (WORD_AT.equals(lastWord)) {
                if (!WORD_HANDLED.equals(currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                } else if (WORD_HANDLED.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersGuaranteeColumn.LOAN_ORG.getValue(), tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    isFirstTime = true;
                    lastWord = currentWord;
                } else {
                    tempWord.append(currentWord);
                    wordCount++;
                    continue;
                }
            } else if (WORD_GUARANTEE_CONTRACT_AMOUNT.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (WORD_GUARANTEE_CONTRACT_AMOUNT.equals(lastWord)) {
                if (!SPLIT_COMMA.equals(currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                } else if (SPLIT_COMMA.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersGuaranteeColumn.GUARANTEE_CONTRACT_AMOUNT.getValue(),
                            tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    isFirstTime = true;
                    lastWord = currentWord;
                } else {
                    tempWord.append(currentWord);
                    wordCount++;
                    continue;
                }
            } else if (WORD_GUARANTEET_AMOUNT.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (WORD_GUARANTEET_AMOUNT.equals(lastWord)) {
                if (!SPLIT_PERIOD.equals(currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                } else if (SPLIT_PERIOD.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersGuaranteeColumn.GUARANTEET_AMOUNT.getValue(),
                            tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    isFirstTime = true;
                    lastWord = currentWord;
                } else {
                    tempWord.append(currentWord);
                    wordCount++;
                    continue;
                }
            } else if (WORD_CUT_OFF.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (WORD_CUT_OFF.equals(lastWord)) {
                if (!SPLIT_COMMA.equals(currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                } else if (SPLIT_COMMA.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersGuaranteeColumn.STATISTICAL_DT.getValue(),
                            tempWord.toString().replace(WORD_YEAR, SPLIT_WORD).replace(WORD_MONT, SPLIT_WORD)
                                    .replace(WORD_DAY, LABEL_NULL));
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    isFirstTime = true;
                    lastWord = currentWord;
                } else {
                    tempWord.append(currentWord);
                    wordCount++;
                    continue;
                }
            } else if (WORD_GUARANTEED_PRINCIPAL_BALANCE.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (WORD_GUARANTEED_PRINCIPAL_BALANCE.equals(lastWord)) {
                if (!SPLIT_PERIOD.equals(currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                } else if (SPLIT_PERIOD.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersGuaranteeColumn.GUARANTEED_PRINCIPAL_BALANCE.getValue(),
                            tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    isFirstTime = true;
                    lastWord = currentWord;
                } else {
                    tempWord.append(currentWord);
                    wordCount++;
                    continue;
                }
            }
        }
    }

    @SuppressWarnings("unused")
    private void analyzeCcDetailNeverOverDue(String content, Map<String, String> map) {
        Result parse = ToAnalysis.parse(content.replaceAll(",", ""));
        String lastWord = "";
        StringBuffer tempWord = new StringBuffer();
        int wordCount = 0;
        boolean isLogOut = false;
        boolean isFirstTime = true;
        if (content.contains(WORD_LOG_OUT)) {// 已销户
            map.put(CreepersConstant.TCreepersCcDetailColumn.CC_STATUS.getValue(), VALUE_CC_STATUS_LOG_OUT);
            isLogOut = true;
        } else if (content.contains(WORD_INACTIVATED)) {
            map.put(CreepersConstant.TCreepersCcDetailColumn.CC_STATUS.getValue(), VALUE_CC_STATUS_INACTIVATED);
        } else {
            map.put(CreepersConstant.TCreepersCcDetailColumn.CC_STATUS.getValue(), VALUE_CC_STATUS_VALID);
        }
        for (Term term : parse.getTerms()) {
            logger.info("========================================");
            String currentWord = term.getName();
            logger.info(currentWord);
            if (WORD_CUT_OFF.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (WORD_CREDIT_LIMIT.equals(currentWord) || WORD_CREDIT_LIMIT_TO_RMB.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (WORD_OVERDRAFT_BALANCE.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (lastWord.isEmpty()) {
                lastWord = currentWord;
                map.put(CreepersConstant.TCreepersCcDetailColumn.GRANT_DT.getValue(), currentWord
                        .replace(WORD_YEAR, SPLIT_WORD).replace(WORD_MONT, SPLIT_WORD).replace(WORD_DAY, LABEL_NULL));
            } else if (CommonMethodUtils.matches(REGEX_DATE_YYYY_MM_DD, lastWord)) {
                if (!WORD_GRANT.equals(currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                    continue;
                } else if (WORD_GRANT.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersCcDetailColumn.GRANT_ORG.getValue(), tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    lastWord = currentWord;
                    isFirstTime = true;
                } else {
                    logger.error("当前内容与规则不符，解析失败。");
                }
            } else if (WORD_GRANT.equals(lastWord)) {
                if (!SPLIT_WORD_LEFT.equals(currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                    continue;
                } else if (SPLIT_WORD_LEFT.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersCcDetailColumn.CC_TYPE.getValue(), tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    lastWord = currentWord;
                } else {
                    logger.error("当前内容与规则不符，解析失败。");
                }
            } else if (SPLIT_WORD_LEFT.equals(lastWord)) {
                if (!SPLIT_WORD_RIGHT.equals(currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                    continue;
                } else if (SPLIT_WORD_RIGHT.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersCcDetailColumn.ACCOUNT_TYPE.getValue(), tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    lastWord = currentWord;
                } else {
                    logger.error("当前内容与规则不符，解析失败。");
                }
            } else if (WORD_CUT_OFF.equals(lastWord)) {
                if (!CommonMethodUtils.matches(REGEX_DATE_YYYY_MM, currentWord)) {
                    tempWord.append(currentWord);
                    logger.info("=================>>需要写入DIC学习");
                    logger.info("学习内容:" + tempWord.toString());
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                    continue;
                } else if (CommonMethodUtils.matches(REGEX_DATE_YYYY_MM, currentWord)) {
                    tempWord.append(currentWord);
                    map.put(CreepersConstant.TCreepersCcDetailColumn.STATISTICAL_DT.getValue(),
                            tempWord.toString().replace(WORD_YEAR, SPLIT_WORD).replace(WORD_MONT, LABEL_NULL));
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    lastWord = currentWord;
                    if (isLogOut) {
                        lastWord = WORD_LOG_OUT;
                    }
                } else {
                    logger.error("当前内容与规则不符，解析失败。");
                }
            } else if (WORD_CREDIT_LIMIT.equals(lastWord) || WORD_CREDIT_LIMIT_TO_RMB.equals(lastWord)) {
                if (CommonMethodUtils.matches(REGEX_NUMBER, currentWord)) {
                    tempWord.append(currentWord);
                    continue;
                } else if (!CommonMethodUtils.matches(REGEX_NUMBER, currentWord)
                        && (SPLIT_COMMA.equals(currentWord) || SPLIT_PERIOD.equals(currentWord))) {
                    map.put(CreepersConstant.TCreepersCcDetailColumn.CREDIT_LIMIT.getValue(), tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    lastWord = currentWord;
                } else {
                    tempWord.append(currentWord);
                    wordCount++;
                    continue;
                }
            } else if (WORD_OVERDRAFT_BALANCE.equals(lastWord)) {
                if (CommonMethodUtils.matches(REGEX_NUMBER, currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                    continue;
                } else if (!CommonMethodUtils.matches(REGEX_NUMBER, currentWord) && SPLIT_PERIOD.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersCcDetailColumn.OVERDRAFT_BALANCE.getValue(), tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    lastWord = currentWord;
                    isFirstTime = true;
                } else {
                    tempWord.append(currentWord);
                    wordCount++;
                    continue;
                }
            }
        }
    }

    @SuppressWarnings("unused")
    private void analyzeCcDetailOverDue(String content, Map<String, String> map) {
        Result parse = ToAnalysis.parse(content.replaceAll(",", ""));
        String lastWord = "";
        StringBuffer tempWord = new StringBuffer();
        int wordCount = 0;
        boolean isLogOut = false;
        boolean isFirstTime = true;
        if (content.contains(WORD_LOG_OUT)) {// 已销户
            map.put(CreepersConstant.TCreepersCcDetailColumn.CC_STATUS.getValue(), VALUE_CC_STATUS_LOG_OUT);
            isLogOut = true;
        } else if (content.contains(WORD_INACTIVATED)) {
            map.put(CreepersConstant.TCreepersCcDetailColumn.CC_STATUS.getValue(), VALUE_CC_STATUS_INACTIVATED);
        } else {
            map.put(CreepersConstant.TCreepersCcDetailColumn.CC_STATUS.getValue(), VALUE_CC_STATUS_VALID);
        }
        for (Term term : parse.getTerms()) {
            logger.info("========================================");
            String currentWord = term.getName();
            logger.info(currentWord);
            if (lastWord.isEmpty()) {
                lastWord = currentWord;
                map.put(CreepersConstant.TCreepersCcDetailColumn.GRANT_DT.getValue(), currentWord
                        .replace(WORD_YEAR, SPLIT_WORD).replace(WORD_MONT, SPLIT_WORD).replace(WORD_DAY, LABEL_NULL));
            } else if (CommonMethodUtils.matches(REGEX_DATE_YYYY_MM_DD, lastWord)) {
                if (!WORD_GRANT.equals(currentWord)) {
                    logger.info("=================>>需要写入DIC学习");
                    logger.info("学习内容:" + tempWord.toString());
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                    continue;
                } else if (WORD_GRANT.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersCcDetailColumn.GRANT_ORG.getValue(), tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    lastWord = currentWord;
                    isFirstTime = true;
                } else {
                    logger.error("当前内容与规则不符，解析失败。");
                }
            } else if (WORD_GRANT.equals(lastWord)) {
                if (!SPLIT_WORD_LEFT.equals(currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                    continue;
                } else if (SPLIT_WORD_LEFT.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersCcDetailColumn.CC_TYPE.getValue(), tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    lastWord = currentWord;
                } else {
                    logger.error("当前内容与规则不符，解析失败。");
                }
            } else if (SPLIT_WORD_LEFT.equals(lastWord)) {
                if (!SPLIT_WORD_RIGHT.equals(currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                    continue;
                } else if (SPLIT_WORD_RIGHT.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersCcDetailColumn.ACCOUNT_TYPE.getValue(), tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    lastWord = currentWord;
                } else {
                    logger.error("当前内容与规则不符，解析失败。");
                }
            } else if (WORD_CUT_OFF.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (WORD_CUT_OFF.equals(lastWord)
                    || (SPLIT_WORD_RIGHT.equals(lastWord)
                            && CommonMethodUtils.matches(REGEX_DATE_YYYY_MM, currentWord))) {
                if (!CommonMethodUtils.matches(REGEX_DATE_YYYY_MM, currentWord)) {// 一般不存在这种情况。
                    tempWord.append(currentWord);
                    logger.info("=================>>需要写入DIC学习");
                    logger.info("学习内容:" + tempWord.toString());
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                    continue;
                } else if (CommonMethodUtils.matches(REGEX_DATE_YYYY_MM, currentWord)) {
                    tempWord.append(currentWord);
                    map.put(CreepersConstant.TCreepersCcDetailColumn.STATISTICAL_DT.getValue(),
                            tempWord.toString().replace(WORD_YEAR, SPLIT_WORD).replace(WORD_MONT, LABEL_NULL));
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    lastWord = currentWord;
                    if (isLogOut) {
                        lastWord = WORD_LOG_OUT;
                    }
                } else {
                    logger.error("当前内容与规则不符，解析失败。");
                }
            } else if (WORD_CREDIT_LIMIT.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (WORD_CREDIT_LIMIT.equals(lastWord)) {
                if (CommonMethodUtils.matches(REGEX_NUMBER, currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                    continue;
                } else if (!CommonMethodUtils.matches(REGEX_NUMBER, currentWord) && SPLIT_COMMA.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersCcDetailColumn.CREDIT_LIMIT.getValue(), tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    lastWord = currentWord;
                    isFirstTime = true;
                } else {
                    tempWord.append(currentWord);
                    wordCount++;
                    continue;
                }
            } else if (WORD_OVERDRAFT_BALANCE.equals(currentWord)
                    || WORD_OVERDRAFT_BALANCE_TWO.equals(currentWord)
                    || WORD_OVERDRAFT_BALANCE_THR.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (WORD_OVERDRAFT_BALANCE.equals(lastWord)
                    || WORD_OVERDRAFT_BALANCE_TWO.equals(lastWord) || WORD_OVERDRAFT_BALANCE_THR.equals(lastWord)) {
                if (CommonMethodUtils.matches(REGEX_NUMBER, currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                    continue;
                } else if (!CommonMethodUtils.matches(REGEX_NUMBER, currentWord) && SPLIT_PERIOD.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersCcDetailColumn.OVERDRAFT_BALANCE.getValue(), tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    lastWord = currentWord;
                    isFirstTime = true;
                } else {
                    tempWord.append(currentWord);
                    wordCount++;
                    continue;
                }
            } else if (WORD_LAST_FIVE_YEARS.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (WORD_LAST_FIVE_YEARS.equals(lastWord)) {
                if (CommonMethodUtils.matches(REGEX_NUMBER, currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                    continue;
                } else
                    if (!CommonMethodUtils.matches(REGEX_NUMBER, currentWord) && WORD_MONT_COUNT.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersCcDetailColumn.CC_OVERDRAFT_SIXTY.getValue(),
                            tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    lastWord = currentWord;
                    isFirstTime = true;
                } else {
                    tempWord.append(currentWord);
                    wordCount++;
                    continue;
                }
            } else if (WORD_OVERDRAFT_SIXTY.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (WORD_IN.equals(currentWord)) {
                lastWord = currentWord;
                continue;
            } else if (WORD_IN.equals(lastWord)) {
                if (CommonMethodUtils.matches(REGEX_NUMBER, currentWord)) {
                    tempWord.append(currentWord);
                    if (!isFirstTime) {
                        wordCount++;
                    }
                    isFirstTime = false;
                    continue;
                } else
                    if (!CommonMethodUtils.matches(REGEX_NUMBER, currentWord) && WORD_MONT_COUNT.equals(currentWord)) {
                    map.put(CreepersConstant.TCreepersCcDetailColumn.CC_OVERDRAFT_NINETY.getValue(),
                            tempWord.toString());
                    if (wordCount > 0) {
                        logger.info("=================>>需要写入DIC学习");
                        logger.info("学习内容:" + tempWord.toString());
                    }
                    tempWord.setLength(0);
                    wordCount = 0;
                    lastWord = currentWord;
                    isFirstTime = true;
                } else {
                    tempWord.append(currentWord);
                    wordCount++;
                    continue;
                }
            }
        }
    }

    @Override
    public Site getSite() {
        if (null == site) {
            site = Site.me().setDomain("www.pbccrc.org.cn").setRetryTimes(8).setCycleRetryTimes(8).setTimeOut(30000);
        }
        return site;
    }

    public static void main(String[] args) {
        // 模拟登陆
        Spider.create(new CreditReferenceCenterProcessor()).addPipeline(new CreditReferencePipline("/webmagic/"))
                .addUrl("https://ipcrs.pbccrc.org.cn/").setDownloader(new CreditReferenceSeleniumDownloader()).thread(2)
                .runAsync();
        // local 模拟下载
        // Spider.create(new CreditReferenceCenterProcessor()).addPipeline(new
        // CreditReferencePipline("/webmagic/"))
        // .addUrl("https://ipcrs.pbccrc.org.cn/").setDownloader(new
        // SimulateDownloader()).thread(2).runAsync();
        // Map<String, String> map = new HashMap<String, String>();
        // 1.为他人担保
        // String content =
        // "2009年3月2日，为赵四（证件类型：身份证，证件号码：4202132123xxxx）在中国建设银行金融街支行办理的贷款提供担保，担保贷款合同金额50,000，担保金额50,000。截至2010年10月5日，担保贷款本金余额20,000。";
        // new CreditReferenceCenterProcessor().analyzeGuarantee(content, map);
        // 2.不存在逾期记录的
        // String content =
        // "2014年7月2日浦发银行信用卡中心发放的贷记卡（美元账户）。截至2016年4月,信用额度折合人民币17,000，已使用额度0。";
        // String content = "2012年3月15日中国银行上海市分行发放的贷记卡（美元账户），截至2015年1月已销户。";
        // new
        // CreditReferenceCenterProcessor().analyzeCcDetailNeverOverDue(content,
        // map);
        // 3.超期60天的
        // String content =
        // "2007年6月30日中国银行北京分行发放的准贷记卡（美元账户）。截至2010年10月，信用额度10,000，透支额度5,000。最近5年内有6个月透支超出60天，其中3个月透支超过90天。";
        // "2007年6月30日中国银行北京分行发放的准贷记卡（美元账户）。2010年10月销户，信用额度10,000，透支额度5,000。最近5年内有6个月透支超出60天，其中3个月透支超过90天。";
        // new CreditReferenceCenterProcessor().analyzeCcDetailOverDue(content,
        // map);
        // 4.发生过逾期的
        // String content = "";
        // new CreditReferenceCenterProcessor().an
        // Set<String> set = map.keySet();
        // for (String string : set) {
        // System.out.println(string + ":" + map.get(string));
        // }

    }

}
