package com.fosun.fc.projects.creepers.pageprocessor.CFDA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.fosun.fc.projects.creepers.utils.DocUtil;
import com.fosun.fc.projects.creepers.utils.FileUtil;
import com.fosun.fc.projects.creepers.utils.TranslateMethodUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 
 * <p>
 * Demo: GSP证书 广东省
 * </p>
 * 
 * @author MaXin
 * @since 2016-11-24 16:18:59
 * @see
 */
@Component("dicalGMPGDProcessor")
public class MedicalGMPGDProcessor implements PageProcessor {

    public static final String URL_PAGE_TEMPLATE = "http://www.sda.gov.cn/wbpp/generalsearch?sort=true&sortId=CTIME&columnid=CLID%7CBCID&relation=MUST%7CMUST&tableName=Region&colNum=2&qryNum=2&qryidstr=CLID%7CBCID&qryValue=cl0885%7C0019&record=20&mytarget=~blank&dateFormat=yyyy%C4%EAMM%D4%C2dd%C8%D5&titleLength=-1&subTitleFlag=0&classStr=%7C-1%7C-1%7CListColumnClass15%7CLawListSub15%7Clistnew15%7Clisttddate15%7Clistmore15%7Cdistance15%7Cclasstab15%7Cclasstd15%7CpageTdSTR15%7CpageTdSTR15%7CpageTd15%7CpageTdF15%7CpageTdE15%7CpageETd15%7CpageTdGO15%7CpageTdGOTB15%7CpageGOButton15%7CpageDatespan15%7Cpagestab15%7CpageGOText15&curPage=";

    private static final String REGEX_URL_PAGE = "http://www.sda.gov.cn/wbpp/generalsearch\\?sort=true&sortId=CTIME&columnid=CLID%7CBCID&relation=MUST%7CMUST&tableName=Region&colNum=2&qryNum=2&qryidstr=CLID%7CBCID&qryValue=cl0885%7C0019&record=20&mytarget=~blank&dateFormat=yyyy%C4%EAMM%D4%C2dd%C8%D5&titleLength=-1&subTitleFlag=0&classStr=%7C-1%7C-1%7CListColumnClass15%7CLawListSub15%7Clistnew15%7Clisttddate15%7Clistmore15%7Cdistance15%7Cclasstab15%7Cclasstd15%7CpageTdSTR15%7CpageTdSTR15%7CpageTd15%7CpageTdF15%7CpageTdE15%7CpageETd15%7CpageTdGO15%7CpageTdGOTB15%7CpageGOButton15%7CpageDatespan15%7Cpagestab15%7CpageGOText15&curPage=";

    private static final String REGEX_URL_DETAIL = "http://www.sda.gov.cn/WS01/CL0885/(.*).html";

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Site site;

    public MedicalGMPGDProcessor() {
        if (null == site) {
            site = Site.me().setDomain("www.sda.gov.cn").setUserAgent(CommonMethodUtils.getRandomUserAgent()).setSleepTime(3100).setTimeOut(90000);
        }

    }

    @Override
    public void process(Page page) {
        logger.info("========================>>MedicalInstrumentDomesticProcessor:  start");
        ResultItems resultItem = page.getResultItems();
        CreepersParamDTO param = resultItem.get(BaseConstant.PARAM_DTO_KEY);
        page.setSkip(true);
        if (page.getUrl().toString().equals(param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL))) {
            // 初始链接，则增加起始页
            logger.info("======>>>初始链接!");
            String totalPageNum = page.getHtml().regex("页/共(.*)页</TD>").regex("\\d+").toString();
            logger.info("totalPage:" + totalPageNum);
            String thredNum = (String) page.getRequest().getExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM);
            if (StringUtils.isBlank(thredNum)) {
                thredNum = "1";
            }
            if (StringUtils.isBlank(totalPageNum)) {
                totalPageNum = "1";
            }
            for (int i = 1; i <= Integer.valueOf(thredNum.trim()); i++) {
                if (i > Integer.valueOf(totalPageNum.trim())) {
                    break;
                }
                Request request = CommonMethodUtils.buildGetRequestCarryMap(URL_PAGE_TEMPLATE + String.valueOf(i));
                request.putExtra(BaseConstant.PARAM_EXTRA_CURRENT_PAGE_NO, String.valueOf(i));
                request.putExtra(BaseConstant.PARAM_EXTRA_TOTAL_PAGE_NO, totalPageNum);
                request.putExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM, thredNum);
                page.addTargetRequest(request);
                logger.info("add request：" + request.getUrl());
            }
        } else if (page.getUrl().regex(REGEX_URL_PAGE).match()) {
            // 分页链接 则增加分页url
            logger.info("======>>>分页链接!");
            List<Selectable> tdList = page.getHtml().xpath("//td[@class=\"ListColumnClass15\"]").nodes();
            for (Selectable td : tdList) {
                logger.debug("td:" + td.xpath("//allText()"));
                String detailURL = td.xpath("//a/@href").toString();
                if (detailURL.contains("sda.gov.cn/CL0885/")) {
                    detailURL = detailURL.replace("sda.gov.cn/CL0885/", "sda.gov.cn/WS01/CL0885/");
                }
                logger.info("detailURL:" + detailURL);
                Request request = new Request(detailURL);
                page.addTargetRequest(request);
            }
            String pageNo = (String) page.getRequest().getExtra(BaseConstant.PARAM_EXTRA_CURRENT_PAGE_NO);
            String totalPageNum = (String) page.getRequest().getExtra(BaseConstant.PARAM_EXTRA_TOTAL_PAGE_NO);
            String thredNum = (String) page.getRequest().getExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM);
            pageNo = String.valueOf(Integer.valueOf(pageNo) + Integer.valueOf(thredNum));
            if (Integer.valueOf(pageNo) <= Integer.valueOf(totalPageNum)) {
                Request request = CommonMethodUtils.buildGetRequestCarryMap(URL_PAGE_TEMPLATE + pageNo);
                request.putExtra(BaseConstant.PARAM_EXTRA_CURRENT_PAGE_NO, pageNo);
                request.putExtra(BaseConstant.PARAM_EXTRA_TOTAL_PAGE_NO, totalPageNum);
                request.putExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM, thredNum);
                page.addTargetRequest(request);
                logger.info("add pageURL:" + request.getUrl());
            }
        } else if (page.getUrl().regex(REGEX_URL_DETAIL).match()) {
            logger.info("=======>>> 详情内容：");
            List<Selectable> trList = page.getHtml().xpath("//td[@class=\"articlecontent3\"]//table/tbody/tr").nodes();
            String[] key = {};
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            if (CommonMethodUtils.isEmpty(trList)) {
                // 没有table 需要解析excel
                Selectable linksSelectable = page.getHtml().xpath("//td[@class=\"articlecontent3\"]").links();
                logger.info("linksSelectable:" + linksSelectable.toString());
                logger.debug("is match:" + linksSelectable.regex("http://www.sda.gov.cn/directory/web/WS01/images/(.*).doc").match());
                page.addTargetRequest(linksSelectable.toString());
            } else {
                // table 解析
                int titleRowNum = 0;
                for (int i = 0; i < trList.size(); i++) {
                    Selectable eachTr = trList.get(i);
                    logger.debug("tr:" + eachTr.toString());
                    List<Selectable> tdList = eachTr.xpath("//td").nodes();
                    if (i == titleRowNum) {
                        if ((i + 1) < trList.size() && tdList.size() < trList.get(i + 1).xpath("//td").nodes().size()) {
                            titleRowNum++;
                            continue;
                        }
                        key = new String[tdList.size()];
                        for (int j = 0; j < tdList.size(); j++) {
                            key[j] = tdList.get(j).xpath("//allText()").toString();
                        }
                    } else {
                        for (int j = 0; j < tdList.size(); j++) {
                            Map<String, String> map = new HashMap<String, String>();
                            String value = "";
                            value = tdList.get(j).xpath("//allText()").toString();
                            logger.info(key[j] + ":" + value);
                            if ("企业名称".equals(CommonMethodUtils.matchesChineseValue(key[j]))) {
                                map.put(CreepersConstant.TCreepersMedicalColumn.KEY.getValue(), value);
                                logger.info(CreepersConstant.TCreepersMedicalColumn.KEY.getValue() + ":" + value);
                            }
                            map.put(key[j], value);
                            list.add(map);
                        }
                    }
                }
                TranslateMethodUtil.addCommonParamMapMedical(list, CreepersConstant.TCreepersMedicalColumn.TYPE.getValue(), BaseConstant.TaskListType.MEDICAL_GMP_LIST.getValue());
                page.putField(BaseConstant.RESULT_DATA_KEY, list);
                logger.info(resultItem.get(BaseConstant.RESULT_DATA_KEY).toString());
                page.setSkip(false);
            }
        } else if (page.getUrl().regex(param.getOrderUrl(BaseConstant.OrderUrlKey.DOWNLOAD_FILE_URL_1_REGEX)).match()) {
            String filePath = page.getResultItems().get(BaseConstant.PAGE_FILE_PATH);
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            if (filePath.endsWith(BaseConstant.FileSuffix.DOC.getValue().toLowerCase())) {
                DocUtil.readWordTableMedical(filePath, list);
            } else if (filePath.endsWith(BaseConstant.FileSuffix.XLS.getValue().toLowerCase())) {
                DocUtil.readExcelTableMedical(filePath, list, 1, "企业名称 (中文）", CreepersConstant.TCreepersMedicalColumn.KEY.getValue());
            }
            FileUtil.delete(filePath);
            TranslateMethodUtil.addCommonParamMapMedical(list, CreepersConstant.TCreepersMedicalColumn.TYPE.getValue(), BaseConstant.TaskListType.MEDICAL_GMP_LIST.getValue());
            page.putField(BaseConstant.RESULT_DATA_KEY, list);
            logger.info(resultItem.get(BaseConstant.RESULT_DATA_KEY).toString());
            page.setSkip(false);
        }
        logger.info("========================>>MedicalInstrumentDomesticProcessor:  end");

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        CreepersParamDTO param = new CreepersParamDTO();
        param.setTaskType(BaseConstant.TaskListType.MEDICAL_GMP_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL,
                "http://www.sda.gov.cn/WS01/CL0885/145901.html");
        param.putOrderUrl(BaseConstant.OrderUrlKey.DOWNLOAD_FILE_URL_1_REGEX, "http://www.sda.gov.cn/directory/web/WS01/images/(.*).xls");
        // param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL,
        // URL_PAGE_TEMPLATE + "1&index");
        Request request = CommonMethodUtils.buildGetRequestCarryMap(param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL));
        request.putExtra(BaseConstant.PARAM_EXTRA_CURRENT_PAGE_NO, "1");
        request.putExtra(BaseConstant.PARAM_EXTRA_TOTAL_PAGE_NO, "100");
        request.putExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM, "1");
        Spider.create(new MedicalGMPGDProcessor()).setDownloader(new HttpRequestDownloader().setParam(param))
                .addRequest(request).thread(1).runAsync();
    }
}
