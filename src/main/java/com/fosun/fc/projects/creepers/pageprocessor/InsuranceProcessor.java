package com.fosun.fc.projects.creepers.pageprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant.TCreepersInsuranceBasicColumn;
import com.fosun.fc.projects.creepers.constant.CreepersConstant.TCreepersInsurancePaymentColumn;
import com.fosun.fc.projects.creepers.constant.CreepersConstant.TCreepersInsuranceSumColumn;
import com.fosun.fc.projects.creepers.constant.CreepersConstant.TCreepersInsuranceUnitColumn;
import com.fosun.fc.projects.creepers.constant.CreepersConstant.TableNamesInsurance;
import com.fosun.fc.projects.creepers.downloader.SimulateLoginDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.google.common.collect.Sets;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

@Component("insuranceProcessor")
public class InsuranceProcessor implements PageProcessor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;

    private Site site;

    private static final String BASE_URL = "http://www.12333sh.gov.cn/";

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0";

    // private static final String XPATH_DIV_ID_AA = "//*[@id=\"aa\"]";
    // private static final String REGEX_TB_ID_DATAISXXB_SUM1121 =
    // "id=\"dataisxxb_sum1121\"";
    // private static final String REGEX_TB_ID_DATAISXXB_SUM2121 =
    // "id=\"dataisxxb_sum2121\"";
    // private static final String REGEX_TB_ID_DATAISXXB_SUM4121 =
    // "id=\"dataisxxb_sum4121\"";

    private static final String XPATH_TR = "//tr";
    private static final String XPATH_TD = "//td";
    private static final String XPATH_SPAN = "//span";
    private static final String XPATH_ALLTEXT = "//allText()";
    private static final String XPATH_TEXT = "//text()";

    private static final String XPATH_ERROR_TIP = "//table//td//span";

    private static final String T_BASIC_NAME = "姓名：";
    private static final String T_BASIC_CERT_NO = "身份证：";
    private static final String T_BASIC_WORK_TIME = "92年底前连续工龄：个月";

    // private static final String T_UNIT = "参保单位名称";

    private static final String SYMBOL_SEMICOLON = ";";
    private static final String SYMBOL_COLON = ":";

    @Override
    public void process(Page page) {

        try {
            logger.info("===========>InsuranceProcessor.process start");
            logger.info("page.url:" + page.getUrl().toString());

            ResultItems resultItems = page.getResultItems();
            CreepersLoginParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);

            int triedTimes = 1;
            if (page.getRequest().getExtras().containsKey(Request.CYCLE_TRIED_TIMES)) {
                triedTimes = (int) page.getRequest().getExtra(Request.CYCLE_TRIED_TIMES);
            }

            if (param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL).equals(page.getUrl().toString())) {
                Request request = new Request(param.getOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL));
                CommonMethodUtils.checkNeedRecycle(triedTimes, request);
                page.addTargetRequest(request);
                page.setSkip(true);
            } else if (param.getOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL).equals(page.getUrl().toString())) {
                param.setDoRedirect(true);
                Request request = CommonMethodUtils.buildDoLoginRequest(param);
                CommonMethodUtils.checkNeedRecycle(triedTimes, request);
                page.addTargetRequest(request);
                page.setSkip(true);
            } else if (page.getUrl().regex("http://www.12333sh.gov.cn/sbsjb/wzb/helpinfo.jsp\\?id=0").match()) {
                List<String> targetUrlList = param.getTargetUrlList();
                for (String targetUrl : targetUrlList) {
                    Request request = CommonMethodUtils.buildDefaultRequest(param, targetUrl);
                    CommonMethodUtils.checkNeedRecycle(triedTimes, request);
                    page.addTargetRequest(request);
                }
            } else if (page.getUrl().regex("http://www.12333sh.gov.cn/sbsjb/wzb/helpinfo.jsp\\?id=2").match()// 验证码错
                    || page.getUrl().regex("http://www.12333sh.gov.cn/sbsjb/wzb/helpinfo.jsp\\?id=4").match()// 登录超时，重新登录
            ) {
                logger.error(
                        "============== >>>   " + page.getHtml().xpath(XPATH_ERROR_TIP + XPATH_ALLTEXT).toString());//
                param.setErrorInfo(page.getHtml().xpath(XPATH_ERROR_TIP + XPATH_ALLTEXT).toString());
                param.setErrorPath(getClass().toString());
                // creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
                Request request = new Request(param.getOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL));
                triedTimes++;
                if (triedTimes > getSite().getRetryTimes()) {
                    param.setErrorInfo(page.getHtml().xpath(XPATH_ERROR_TIP + XPATH_ALLTEXT).toString());
                    param.setErrorPath(getClass().toString());
                    creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
                }else {
                    CommonMethodUtils.checkNeedRecycle(triedTimes, request);
                    page.addTargetRequest(request);
                }
                page.setSkip(true);
            } else if (page.getUrl().regex("http://www.12333sh.gov.cn/sbsjb/wzb/helpinfo.jsp\\?id=").match()) {
                logger.error(
                        "============== >>>   " + page.getHtml().xpath(XPATH_ERROR_TIP + XPATH_ALLTEXT).toString());// 异常处理
            } else if (param.getTargetUrlList().contains(page.getUrl().toString())
                    && page.getHtml().xpath("//*[@id=\"dataisxxb_sum1\"]").nodes().size() > 0) {
                TableNamesInsurance[] tbNames = CreepersConstant.TableNamesInsurance.values();
                Object obj;
                for (int i = 0; i < tbNames.length; i++) {
                    if (tbNames[i].isList()) {
                        obj = new ArrayList<Map<String, String>>();
                    } else {
                        obj = new HashMap<String, String>();
                    }
                    page.putField(tbNames[i].getMapKey(), obj);
                }
                analyzeXML(page);
            }
            logger.info("===========>InsuranceProcessor.process end");
            // if (page.getUrl().regex(BASE_URL + ACCESS_URL_1).match()) {
            // List<Selectable> tbList = page.getHtml().xpath(XPATH_DIV_ID_AA +
            // XPATH_TABLE).nodes();
            // Map<String, String> nameAndCertNomap = new HashMap<String,
            // String>();
            // for (int i = 0; i < tbList.size(); i++) {
            // if (tbList.get(i).regex(REGEX_TB_ID_DATAISXXB_SUM1121).match()
            // && tbList.get(i).xpath(XPATH_TD).nodes().size() > 0) {
            // nameAndCertNomap = getInsuranceBasicInfo(tbList.get(i),
            // resultItems);
            // } else if
            // (tbList.get(i).regex(REGEX_TB_ID_DATAISXXB_SUM2121).match()
            // && tbList.get(i).xpath(XPATH_TR).nodes().size() > 0) {
            // getInsurancePaymentInfo(tbList.get(i), resultItems,
            // nameAndCertNomap);
            // } else if (tbList.get(i).regex(T_UNIT).match()) {
            // getInsuranceUnitInfo(tbList.get(i), resultItems,
            // nameAndCertNomap);
            // } else if
            // (tbList.get(i).regex(REGEX_TB_ID_DATAISXXB_SUM4121).match()
            // && tbList.get(i).xpath(XPATH_TR).nodes().size() > 0) {
            // getInsuranceSumInfo(tbList.get(i), resultItems,
            // nameAndCertNomap);
            // }
            // }
            // }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("insuranceProcessor process error:", e);
            CreepersLoginParamDTO param = page.getResultItems().get(BaseConstant.PARAM_DTO_KEY);
            param.setErrorInfo(
                    "insuranceProcessor process error:" + e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            logger.error("======================>>>  insuranceProcessor.process end!");
        }
    }

    @SuppressWarnings("unchecked")
    private void analyzeXML(Page page) {
        ResultItems resultItems = page.getResultItems();
        logger.info("=========>获取社保基础信息");
        // 获取paramDTO,用于获取密码
        CreepersLoginParamDTO paramDTO = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        // 获取社保基础信息
        Selectable basicSel = page.getHtml().xpath("//*[@id=\"dataisxxb_sum1\"]").nodes().get(0);
        Element basicRoot = CommonMethodUtils.getRootElement(basicSel.toString());
        Element basicXxblist = basicRoot.element("xxblist");
        Element basicJsjs = basicXxblist.element("jsjs");
        Map<String, String> basicMap = resultItems
                .get(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_BASIC.getMapKey());
        basicMap.put(TCreepersInsuranceBasicColumn.PASSWORD.getValue(), paramDTO.getPassword());
        logger.info(TCreepersInsuranceBasicColumn.PASSWORD.getValue()
                + SYMBOL_COLON + basicMap.get(TCreepersInsuranceBasicColumn.PASSWORD.getValue()));
        basicMap.put(TCreepersInsuranceBasicColumn.NAME.getValue(), basicJsjs.elementText("xm").trim());
        logger.info(TCreepersInsuranceBasicColumn.NAME.getValue()
                + SYMBOL_COLON + basicMap.get(TCreepersInsuranceBasicColumn.NAME.getValue()));
        basicMap.put(TCreepersInsuranceBasicColumn.CERT_NO.getValue(), paramDTO.getLoginName());
        logger.info(TCreepersInsuranceBasicColumn.CERT_NO.getValue()
                + SYMBOL_COLON + basicMap.get(TCreepersInsuranceBasicColumn.CERT_NO.getValue()));
        basicMap.put(TCreepersInsuranceBasicColumn.WORK_TIME.getValue(), basicJsjs.elementText("jsjs1").trim());
        logger.info(TCreepersInsuranceBasicColumn.WORK_TIME.getValue()
                + SYMBOL_COLON + basicMap.get(TCreepersInsuranceBasicColumn.WORK_TIME.getValue()));

        logger.info("=========>获取社保缴费信息");
        Selectable paymentSel = page.getHtml().xpath("//*[@id=\"dataisxxb_sum2\"]").nodes().get(0);
        Element paymentRoot = CommonMethodUtils.getRootElement(paymentSel.toString());
        Element paymentXxblist = paymentRoot.element("xxblist");
        List<Element> paymentJsjsList = paymentXxblist.elements("jsjs");
        List<Map<String, String>> paymentList = resultItems
                .get(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_PAYMENT.getMapKey());
        for (Element element : paymentJsjsList) {
            Map<String, String> map = new HashMap<String, String>();
            map.put(TCreepersInsurancePaymentColumn.NAME.getValue(), basicJsjs.elementText("xm").trim());
            logger.info(TCreepersInsurancePaymentColumn.NAME.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsurancePaymentColumn.NAME.getValue()));
            map.put(TCreepersInsurancePaymentColumn.CERT_NO.getValue(), paramDTO.getLoginName());
            logger.info(TCreepersInsurancePaymentColumn.CERT_NO.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsurancePaymentColumn.CERT_NO.getValue()));
            map.put(TCreepersInsurancePaymentColumn.PAYMENT_DT.getValue(), element.elementText("jsjs1").trim());
            logger.info(TCreepersInsurancePaymentColumn.PAYMENT_DT.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsurancePaymentColumn.PAYMENT_DT.getValue()));
            map.put(TCreepersInsurancePaymentColumn.PAYMENT_BASE.getValue(), element.elementText("jsjs3").trim());
            logger.info(TCreepersInsurancePaymentColumn.PAYMENT_BASE.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsurancePaymentColumn.PAYMENT_BASE.getValue()));
            map.put(TCreepersInsurancePaymentColumn.ENDOWMENT.getValue(), element.elementText("jsjs4").trim());
            logger.info(TCreepersInsurancePaymentColumn.ENDOWMENT.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsurancePaymentColumn.ENDOWMENT.getValue()));
            map.put(TCreepersInsurancePaymentColumn.MEDICAL.getValue(), element.elementText("jsjs6").trim());
            logger.info(TCreepersInsurancePaymentColumn.MEDICAL.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsurancePaymentColumn.MEDICAL.getValue()));
            map.put(TCreepersInsurancePaymentColumn.UNEMPLOYMENT.getValue(), element.elementText("jsjs8").trim());
            logger.info(TCreepersInsurancePaymentColumn.UNEMPLOYMENT.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsurancePaymentColumn.UNEMPLOYMENT.getValue()));
            paymentList.add(map);
        }

        logger.info("=========>获取社保账户信息");
        Selectable unitSel = page.getHtml().xpath("//*[@id=\"dataisxxb_sum12\"]").nodes().get(0);
        Element unitRoot = CommonMethodUtils.getRootElement(unitSel.toString());
        Element unitJfdwlist = unitRoot.element("jfdwlist");
        List<Element> unitJfdwinfoList = unitJfdwlist.elements("jfdwinfo");
        List<Map<String, String>> unitList = resultItems
                .get(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_UNIT.getMapKey());
        for (Element element : unitJfdwinfoList) {
            Map<String, String> map = new HashMap<String, String>();
            map.put(TCreepersInsuranceUnitColumn.NAME.getValue(), basicJsjs.elementText("xm").trim());
            logger.info(TCreepersInsuranceUnitColumn.NAME.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsuranceUnitColumn.NAME.getValue()));
            map.put(TCreepersInsuranceUnitColumn.CERT_NO.getValue(), paramDTO.getLoginName());
            logger.info(TCreepersInsuranceUnitColumn.CERT_NO.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsuranceUnitColumn.CERT_NO.getValue()));
            map.put(TCreepersInsuranceUnitColumn.UNIT.getValue(), element.elementText("jfsj").trim());
            logger.info(TCreepersInsuranceUnitColumn.UNIT.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsuranceUnitColumn.UNIT.getValue()));
            map.put(TCreepersInsuranceUnitColumn.PERIOD.getValue(), element.elementText("jfdw").trim());
            logger.info(TCreepersInsuranceUnitColumn.PERIOD.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsuranceUnitColumn.PERIOD.getValue()));
            unitList.add(map);
        }

        logger.info("=========>获取社保累计缴费信息");
        Selectable sumSel = page.getHtml().xpath("//*[@id=\"dataisxxb_sum4\"]").nodes().get(0);
        Element sumRoot = CommonMethodUtils.getRootElement(sumSel.toString());
        Element sumJfdwlist = sumRoot.element("xxblist");
        List<Element> sumJfdwinfoList = sumJfdwlist.elements("jsjs");
        List<Map<String, String>> sumList = resultItems
                .get(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_SUM.getMapKey());
        for (Element element : sumJfdwinfoList) {
            Map<String, String> map = new HashMap<String, String>();
            map.put(TCreepersInsuranceSumColumn.NAME.getValue(), basicJsjs.elementText("xm").trim());
            logger.info(TCreepersInsuranceSumColumn.NAME.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsuranceSumColumn.NAME.getValue()));
            map.put(TCreepersInsuranceSumColumn.CERT_NO.getValue(), paramDTO.getLoginName());
            logger.info(TCreepersInsuranceSumColumn.CERT_NO.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsuranceSumColumn.CERT_NO.getValue()));
            map.put(TCreepersInsuranceSumColumn.END_DT.getValue(), element.elementText("jsjs1").trim());
            logger.info(TCreepersInsuranceSumColumn.END_DT.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsuranceSumColumn.END_DT.getValue()));
            map.put(TCreepersInsuranceSumColumn.MONTHS.getValue(), element.elementText("jsjs2").trim());
            logger.info(TCreepersInsuranceSumColumn.MONTHS.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsuranceSumColumn.MONTHS.getValue()));
            map.put(TCreepersInsuranceSumColumn.ENDOWMENT_SUM.getValue(), element.elementText("jsjs3").trim());
            logger.info(TCreepersInsuranceSumColumn.ENDOWMENT_SUM.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsuranceSumColumn.ENDOWMENT_SUM.getValue()));
            map.put(TCreepersInsuranceSumColumn.ENDOWMENT_SUM_PRIVATE.getValue(), element.elementText("jsjs4").trim());
            logger.info(TCreepersInsuranceSumColumn.ENDOWMENT_SUM_PRIVATE.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsuranceSumColumn.ENDOWMENT_SUM_PRIVATE.getValue()));
            sumList.add(map);
        }
    }

    public Map<String, String> getInsuranceBasicInfo(Selectable itemSel, ResultItems resultItems) {

        logger.info("===========>getInsuranceBasicInfo start!");
        Map<String, String> result = resultItems.get(TableNamesInsurance.T_CREEPERS_INSURANCE_BASIC.getMapKey());
        CreepersLoginParamDTO paramDTO = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        Map<String, String> map = new HashMap<String, String>();
        List<Selectable> tdList = itemSel.xpath(XPATH_TR + XPATH_TD).nodes();
        for (int i = 0; i < tdList.size(); i++) {
            String value = tdList.get(i).xpath(XPATH_TD + XPATH_SPAN + XPATH_TEXT).toString();
            String key = tdList.get(i).xpath(XPATH_TD + XPATH_TEXT).toString();
            result.put(CreepersConstant.TCreepersInsuranceBasicColumn.PASSWORD.getValue(), paramDTO.getPassword());
            logger.info(TCreepersInsuranceBasicColumn.PASSWORD.getValue()
                    + SYMBOL_COLON + result.get(TCreepersInsuranceBasicColumn.PASSWORD.getValue()));
            if (T_BASIC_NAME.contains(key)) {
                result.put(CreepersConstant.TCreepersInsuranceBasicColumn.NAME.getValue(), value);
                logger.info(TCreepersInsuranceBasicColumn.NAME.getValue()
                        + SYMBOL_COLON + result.get(TCreepersInsuranceBasicColumn.NAME.getValue()));
                map.put(CreepersConstant.TCreepersInsuranceBasicColumn.NAME.getValue(), value);
            } else if (T_BASIC_CERT_NO.contains(key)) {
                result.put(CreepersConstant.TCreepersInsuranceBasicColumn.CERT_NO.getValue(), value);
                logger.info(TCreepersInsuranceBasicColumn.CERT_NO.getValue()
                        + SYMBOL_COLON + result.get(TCreepersInsuranceBasicColumn.CERT_NO.getValue()));
                map.put(CreepersConstant.TCreepersInsuranceBasicColumn.CERT_NO.getValue(), value);
            } else if (T_BASIC_WORK_TIME.contains(key)) {
                result.put(CreepersConstant.TCreepersInsuranceBasicColumn.WORK_TIME.getValue(), value);
                logger.info(TCreepersInsuranceBasicColumn.WORK_TIME.getValue()
                        + SYMBOL_COLON + result.get(TCreepersInsuranceBasicColumn.WORK_TIME.getValue()));
            }
        }
        logger.info("===========>getInsuranceBasicInfo end!");
        return map;
    }

    // 社保应缴记录表
    public void getInsurancePaymentInfo(Selectable itemSel, ResultItems resultItems,
            Map<String, String> nameAndCertNomap) {

        logger.info("===========>getInsurancePaymentInfo start!");
        List<Map<String, String>> result = resultItems
                .get(TableNamesInsurance.T_CREEPERS_INSURANCE_PAYMENT.getMapKey());
        List<Selectable> trList = itemSel.xpath(XPATH_TR).nodes();
        for (Selectable trSel : trList) {
            List<Selectable> tdList = trSel.xpath(XPATH_TD + XPATH_ALLTEXT).nodes();
            if (tdList.size() != 5)
                break;
            Map<String, String> map = new HashMap<String, String>();
            map.putAll(nameAndCertNomap);
            logger.info(TCreepersInsurancePaymentColumn.NAME.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsurancePaymentColumn.NAME.getValue()));
            logger.info(TCreepersInsurancePaymentColumn.CERT_NO.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsurancePaymentColumn.CERT_NO.getValue()));

            map.put(TCreepersInsurancePaymentColumn.PAYMENT_DT.getValue(), tdList.get(0).toString());
            logger.info(TCreepersInsurancePaymentColumn.PAYMENT_DT.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsurancePaymentColumn.PAYMENT_DT.getValue()));

            map.put(TCreepersInsurancePaymentColumn.PAYMENT_BASE.getValue(), tdList.get(1).toString());
            logger.info(TCreepersInsurancePaymentColumn.PAYMENT_BASE.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsurancePaymentColumn.PAYMENT_BASE.getValue()));

            map.put(TCreepersInsurancePaymentColumn.ENDOWMENT.getValue(), tdList.get(2).toString());
            logger.info(TCreepersInsurancePaymentColumn.ENDOWMENT.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsurancePaymentColumn.ENDOWMENT.getValue()));

            map.put(TCreepersInsurancePaymentColumn.MEDICAL.getValue(), tdList.get(3).toString());
            logger.info(TCreepersInsurancePaymentColumn.MEDICAL.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsurancePaymentColumn.MEDICAL.getValue()));

            map.put(TCreepersInsurancePaymentColumn.UNEMPLOYMENT.getValue(), tdList.get(4).toString());
            logger.info(TCreepersInsurancePaymentColumn.UNEMPLOYMENT.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsurancePaymentColumn.UNEMPLOYMENT.getValue()));
            result.add(map);
        }

        logger.info("===========>getInsurancePaymentInfo end!");
    }

    public void getInsuranceUnitInfo(Selectable itemSel, ResultItems resultItems,
            Map<String, String> nameAndCertNomap) {

        logger.info("===========>getInsuranceUnitInfo start!");
        List<Map<String, String>> result = resultItems.get(TableNamesInsurance.T_CREEPERS_INSURANCE_UNIT.getMapKey());
        List<Selectable> spanList = itemSel.xpath(XPATH_TR + XPATH_TD + XPATH_SPAN + XPATH_TEXT).nodes();
        for (Selectable spanSel : spanList) {
            if (spanSel.regex("养老保险个人实际缴费信息").match()) {
                continue;
            }
            String[] values = spanSel.toString().split(SYMBOL_SEMICOLON);
            for (String value : values) {
                Map<String, String> map = new HashMap<String, String>();
                value = value.trim();
                if (null != value && !"".equals(value) && value.length() > 6) {
                    map.putAll(nameAndCertNomap);
                    logger.info(TCreepersInsuranceUnitColumn.NAME.getValue()
                            + SYMBOL_COLON + map.get(TCreepersInsuranceUnitColumn.NAME.getValue()));
                    logger.info(TCreepersInsuranceUnitColumn.CERT_NO.getValue()
                            + SYMBOL_COLON + map.get(TCreepersInsuranceUnitColumn.CERT_NO.getValue()));

                    String period = pickUpStr(value, "[0-9-]{6,13}");
                    String unit = value.replace(period, "");
                    map.put(CreepersConstant.TCreepersInsuranceUnitColumn.PERIOD.getValue(), period);
                    logger.info(TCreepersInsuranceUnitColumn.PERIOD.getValue()
                            + SYMBOL_COLON + map.get(TCreepersInsuranceUnitColumn.PERIOD.getValue()));

                    map.put(CreepersConstant.TCreepersInsuranceUnitColumn.UNIT.getValue(), unit);
                    logger.info(TCreepersInsuranceUnitColumn.UNIT.getValue()
                            + SYMBOL_COLON + map.get(TCreepersInsuranceUnitColumn.UNIT.getValue()));
                    result.add(map);
                }
            }
        }
        logger.info("===========>getInsuranceUnitInfo end!");

    }

    public void getInsuranceSumInfo(Selectable itemSel, ResultItems resultItems, Map<String, String> nameAndCertNomap) {

        logger.info("===========>getInsuranceSumInfo start!");
        List<Map<String, String>> result = resultItems.get(TableNamesInsurance.T_CREEPERS_INSURANCE_SUM.getMapKey());
        List<Selectable> trList = itemSel.xpath(XPATH_TR).nodes();
        for (Selectable trSel : trList) {
            List<Selectable> tdList = trSel.xpath(XPATH_TD + XPATH_ALLTEXT).nodes();
            if (tdList.size() != 4)
                break;
            Map<String, String> map = new HashMap<String, String>();
            map.putAll(nameAndCertNomap);
            logger.info(TCreepersInsuranceSumColumn.NAME.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsuranceSumColumn.NAME.getValue()));
            logger.info(TCreepersInsuranceSumColumn.CERT_NO.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsuranceSumColumn.CERT_NO.getValue()));

            map.put(TCreepersInsuranceSumColumn.END_DT.getValue(), tdList.get(0).toString());
            logger.info(TCreepersInsuranceSumColumn.END_DT.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsuranceSumColumn.END_DT.getValue()));

            map.put(TCreepersInsuranceSumColumn.MONTHS.getValue(), tdList.get(1).toString());
            logger.info(TCreepersInsuranceSumColumn.MONTHS.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsuranceSumColumn.MONTHS.getValue()));

            map.put(TCreepersInsuranceSumColumn.ENDOWMENT_SUM.getValue(), tdList.get(2).toString());
            logger.info(TCreepersInsuranceSumColumn.ENDOWMENT_SUM.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsuranceSumColumn.ENDOWMENT_SUM.getValue()));

            map.put(TCreepersInsuranceSumColumn.ENDOWMENT_SUM_PRIVATE.getValue(), tdList.get(3).toString());
            logger.info(TCreepersInsuranceSumColumn.ENDOWMENT_SUM_PRIVATE.getValue()
                    + SYMBOL_COLON + map.get(TCreepersInsuranceSumColumn.ENDOWMENT_SUM_PRIVATE.getValue()));
            result.add(map);
        }
        logger.info("===========>getInsuranceSumInfo end!");
    }

    public static String pickUpStr(String sourceStr, String pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("pattern argument cannot be null.");
        }
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(sourceStr);
        while (m.find())
            return m.group(0);
        return "";
    }

    @Override
    public Site getSite() {

        if (null == site) {
            site = Site.me().setDomain(BASE_URL).setUserAgent(USER_AGENT).setSleepTime(5000)
                    .setAcceptStatCode(Sets.newHashSet(200, 302)).setRetryTimes(5)
                    .addHeader("Accept-Encoding", "gzip, deflate, sdch, br").setCharset("GBK");
        }
        return site;
    }

    public static void main(String[] args) {
        CreepersLoginParamDTO param = new CreepersLoginParamDTO();
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, "http://www.12333sh.gov.cn/sbsjb/wzb/129.jsp");
        param.putOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL, "http://www.12333sh.gov.cn/sbsjb/wzb/Bmblist.jsp");
        param.putOrderUrl(BaseConstant.OrderUrlKey.DO_LOGIN_URL, "http://www.12333sh.gov.cn/sbsjb/wzb/dologin.jsp");
        param.setCaptchaKey("userjym");
        param.setLoginNameKey("userid");
        param.setPasswordKey("userpw");
        param.setLoginName("411302199005180037");// 社保账号
        param.setPassword("123456");// 社保密码
        param.putTargetUrlList("http://www.12333sh.gov.cn/sbsjb/wzb/sbsjbcx.jsp");
        param.setDoRedirect(true);
        Request request = CommonMethodUtils.buildIndexRequest(param);
        Spider.create(new InsuranceProcessor()).setDownloader(new SimulateLoginDownloader().setParam(param)).thread(1)
                .addRequest(request).run();
        // System.out.println(pickUpStr("201508-201603安盛天平财产保险股份有限公司","[^0-9-]+"));
    }

}
