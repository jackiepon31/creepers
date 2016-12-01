package com.fosun.fc.projects.creepers.pageprocessor.CFDA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;

/**
 * 
 * <p>
 * Demo: 国外医疗器械
 * </p>
 * 
 * @author MaXin
 * @since 2016-11-22 09:39:19
 * @see
 */
@Component("medicalInstrumentForeignProcessor")
public class MedicalInstrumentForeignProcessor implements PageProcessor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String URL_PAGE_TEMPLATE = "http://qy1.sfda.gov.cn/datasearch/face3/search.jsp?tableId=27&bcId=118103063506935484150101953610&curstart=";

    private static final String REGEX_URL_PAGE = "http://qy1.sfda.gov.cn/datasearch/face3/search.jsp\\?tableId=27&bcId=118103063506935484150101953610&curstart=";

    private static final String REGEX_URL_DETAIL = "http://qy1.sfda.gov.cn/datasearch/face3/content.jsp\\?tableId=27&tableName=TABLE27&tableView=进口器械&Id=";

    private Site site;

    public MedicalInstrumentForeignProcessor() {
        if (null == site) {
            site = Site.me().setDomain("qy1.sfda.gov.cn").setUserAgent(
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36")
                    .setSleepTime(1800).setTimeOut(90000);
        }

    }

    @Override
    public void process(Page page) {
        logger.info("========================>>MedicalInstrumentForeignProcessor:  start");
        logger.info("page.Html:" + page.getHtml().toString());
        ResultItems resultItem = page.getResultItems();
        CreepersParamDTO param = resultItem.get(BaseConstant.PARAM_DTO_KEY);
        if (page.getUrl().toString().equals(param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL))) {
            // 初始链接，则增加起始页
            String totalPageNum = page.getHtml().regex("javascript:goPage(.*)\"></form>").regex("\\d+").toString();
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
                Request request = CommonMethodUtils.buildDefaultRequest(param, URL_PAGE_TEMPLATE + String.valueOf(i));
                request.putExtra(BaseConstant.PARAM_EXTRA_CURRENT_PAGE_NO, String.valueOf(i));
                request.putExtra(BaseConstant.PARAM_EXTRA_TOTAL_PAGE_NO, totalPageNum);
                request.putExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM, thredNum);
                page.addTargetRequest(request);
            }
            page.setSkip(true);
        } else if (page.getUrl().regex(REGEX_URL_PAGE).match()) {
            // 分页链接 则增加分页url

            List<Selectable> tdList = page.getHtml().xpath("//td[@height=\"30\"]//a").nodes();
            for (Selectable td : tdList) {
                logger.info("td:" + td.xpath("//allText()"));
                logger.info("id:" + td.regex(";Id=(.*)',null"));
                String detailURL = "http://qy1.sfda.gov.cn/datasearch/face3/content.jsp?tableId=27&tableName=TABLE27&tableView=进口器械&Id="
                        + td.regex(";Id=(.*)',null");
                logger.info("detailURL:" + detailURL);
                Request request = new Request(detailURL);
                request.setMethod(HttpConstant.Method.POST);
                page.addTargetRequest(request);
            }
            String pageNo = (String) page.getRequest().getExtra(BaseConstant.PARAM_EXTRA_CURRENT_PAGE_NO);
            String totalPageNum = (String) page.getRequest().getExtra(BaseConstant.PARAM_EXTRA_TOTAL_PAGE_NO);
            String thredNum = (String) page.getRequest().getExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM);
            pageNo = String.valueOf(Integer.valueOf(pageNo) + Integer.valueOf(thredNum));
            if (Integer.valueOf(pageNo) <= Integer.valueOf(totalPageNum)) {
                Request request = CommonMethodUtils.buildDefaultRequest(param, URL_PAGE_TEMPLATE + pageNo);
                request.putExtra(BaseConstant.PARAM_EXTRA_CURRENT_PAGE_NO, pageNo);
                request.putExtra(BaseConstant.PARAM_EXTRA_TOTAL_PAGE_NO, totalPageNum);
                request.putExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM, thredNum);
                page.addTargetRequest(request);
            }
            page.setSkip(true);
        } else if (page.getUrl().regex(REGEX_URL_DETAIL).match()) {
            Map<String, String> result = new HashMap<String, String>();
            result.put(CreepersConstant.TCreepersMedicalColumn.TYPE.getValue(), BaseConstant.TaskListType.MEDICAL_INSTRUMENT_FOREIGN_LIST.getValue());
            List<Selectable> tdList = page.getHtml().xpath("//td[@bgcolor]//allText()").nodes();
            String key = StringUtils.EMPTY;
            String value = StringUtils.EMPTY;
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            for (int i = 0; i < tdList.size(); i++) {
                Selectable eachTd = tdList.get(i);
                String content = eachTd.toString();
                logger.info("td:" + content);
                if (i % 2 == 0) {
                    key = content;
                } else if (i % 2 == 1) {
                    Map<String, String> map = new HashMap<String, String>();
                    value = content;
                    map.put(key, value);
                    list.add(map);
                    if ("注册证编号".equals(key)) {
                        result.put(CreepersConstant.TCreepersMedicalColumn.KEY.getValue(), value);
                    }
                    key = StringUtils.EMPTY;
                    value = StringUtils.EMPTY;
                }
            }
            Map<String, Object> contentMap = new HashMap<String, Object>();
            contentMap.put(BaseConstant.TaskListType.MEDICAL_INSTRUMENT_DOMESTIC_LIST.getValue(), list);
            result.put(CreepersConstant.TCreepersMedicalColumn.CONTENT.getValue(), JSONObject.toJSONString(contentMap, BaseConstant.features));
            List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
            resultList.add(result);
            page.putField(BaseConstant.RESULT_DATA_KEY, resultList);
            logger.info(resultItem.get(BaseConstant.RESULT_DATA_KEY).toString());
        }
        logger.info("========================>>MedicalInstrumentForeignProcessor:  end");
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        CreepersParamDTO param = new CreepersParamDTO();
        param.setTaskType(BaseConstant.TaskListType.MEDICAL_INSTRUMENT_FOREIGN_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, "http://qy1.sfda.gov.cn/datasearch/face3/search.jsp?tableId=27&bcId=118103063506935484150101953610");
        Request request = CommonMethodUtils.buildDefaultRequest(param, param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL));
        request.putExtra(BaseConstant.PARAM_EXTRA_CURRENT_PAGE_NO, "1");
        request.putExtra(BaseConstant.PARAM_EXTRA_TOTAL_PAGE_NO, "100");
        request.putExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM, "1");

        Spider.create(new MedicalInstrumentForeignProcessor()).setDownloader(new HttpRequestDownloader().setParam(param))
                .addRequest(request).thread(1).runAsync();
    }
}
