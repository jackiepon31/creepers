package com.fosun.fc.projects.creepers.pageprocessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

/***
 * 
 * <p>
 * 中国裁判文书网 使用Selenium做页面动态渲染。
 * </p>
 * 
 * @author MaXin 2016-5-11 15:50:12
 */
@Component("judgementPostRequestProcessor")
public class JudgementPostRequestProcessor extends BaseCreepersListProcessor implements PageProcessor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Site site;

    private static final int pageSize = 20;

    @Override
    public Site getSite() {
        if (null == site) {
            site = Site.me().setDomain("wenshu.court.gov.cn").setTimeOut(100000)
                    .setSleepTime(CommonMethodUtils.randomSleepTime())
                    .setUserAgent(
                            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2774.3 Safari/537.36");
//                    .setHttpProxyPool(ProxyPoolUtil.getProxyArrayList()).enableHttpProxyPool();
        }
        return site;
    }

    @Override
    public void process(Page page) {

        try {
            String jsonString = page.getJson().get().substring(1);
            jsonString = jsonString.substring(0, jsonString.length() - 1).replaceAll("\\\\\"", "\"")
                    .replaceAll("\\\\\\\"", "\\\\“");
            logger.info("获取到的json:" + jsonString);
            if ("remind".equals(jsonString)) {
                logger.error("爬取频率过快,法院判决书页面需要验证码登录！");
                CreepersParamDTO param = page.getResultItems().get(BaseConstant.PARAM_DTO_KEY);
                param.setErrorPath(getClass().toString());
                param.setErrorInfo("Oh My God！！  爬取频率过快,被验证码拦截！请联系运维人员切换IP。");
                creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
                logger.error("因频率过快,错误信息记录完毕,爬取停止结束!");
                page.setSkip(true);
                Request currentRequest = page.getRequest();
                site.returnHttpProxyToPool((HttpHost) currentRequest.getExtra(Request.PROXY),
                        (Integer) currentRequest.getExtra(Request.STATUS_CODE));
                return;
            }
            JSONArray jsonArr = JSON.parseArray(jsonString);
            boolean isFirst = true;
            int totalNum = 0;
            String merName = ((CreepersParamDTO) (page.getResultItems().get(BaseConstant.PARAM_DTO_KEY))).getSearchKeyWordForString();
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            for (Object object : jsonArr) {
                if (isFirst) {
                    totalNum = ((JSONObject) object).getIntValue("Count");
                    isFirst = false;
                } else {
                    Map<String, String> map = new HashMap<String, String>();
                    // logger.info("案件类型:" + ((JSONObject)
                    // object).getString("案件类型"));
                    map.put(CreepersConstant.TCreepersJudgementColumn.MER_NAME.getValue(), merName);
                    logger.info("法院名称:" + ((JSONObject) object).getString("法院名称"));
                    map.put(CreepersConstant.TCreepersJudgementColumn.COURT.getValue(),
                            ((JSONObject) object).getString("法院名称"));
                    logger.info("案号:" + ((JSONObject) object).getString("案号"));
                    map.put(CreepersConstant.TCreepersJudgementColumn.CASE_NO.getValue(),
                            ((JSONObject) object).getString("案号"));
                    logger.info("审判程序:" + ((JSONObject) object).getString("审判程序"));
                    logger.info("文书ID:" + ((JSONObject) object).getString("文书ID"));
                    map.put(CreepersConstant.TCreepersJudgementColumn.DOC_ID.getValue(),
                            ((JSONObject) object).getString("文书ID"));
                    logger.info("案件名称:" + ((JSONObject) object).getString("案件名称"));
                    map.put(CreepersConstant.TCreepersJudgementColumn.CASE_TITLE.getValue(),
                            ((JSONObject) object).getString("案件名称"));
                    logger.info("裁判日期:" + ((JSONObject) object).getString("裁判日期"));
                    map.put(CreepersConstant.TCreepersJudgementColumn.CASE_DT.getValue(),
                            ((JSONObject) object).getString("裁判日期"));
                    // if (((JSONObject) object).containsKey("DocContent")) {
                    // logger.info("DocContent:"+((JSONObject)
                    // object).getString("DocContent"));
                    // }
                    list.add(map);
                }
            }
            page.putField("resultList", list);
            if (page.getUrl().regex("http://wenshu.court.gov.cn/List/ListContent\\?num=1").match()) {
                int maxNum = totalNum % pageSize == 0 ? totalNum / pageSize : (totalNum / pageSize) + 1;
                for (int i = 2; i < maxNum; i++) {
                    String url = "http://wenshu.court.gov.cn/List/ListContent?" + i;
                    Request request = new Request(url);
                    request.setMethod(HttpConstant.Method.POST);
                    NameValuePair[] valuePairs = new NameValuePair[10];
                    valuePairs[0] = new BasicNameValuePair("Param", "全文检索:" + merName);
                    valuePairs[1] = new BasicNameValuePair("Index", String.valueOf(i));
                    valuePairs[2] = new BasicNameValuePair("Page", String.valueOf(pageSize));
                    valuePairs[3] = new BasicNameValuePair("Order", "法院层级");
                    valuePairs[4] = new BasicNameValuePair("Direction", "asc");
                    request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, valuePairs);
                    page.addTargetRequest(request);
                    logger.info("===============page.addTargetRequest(request):" + request.getUrl());
                }
            }
            logger.info("===============page end:==================");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("JudgementPostRequestProcessor process error:", e);
            CreepersParamDTO param = page.getResultItems().get(BaseConstant.PARAM_DTO_KEY);
            param.setErrorInfo("JudgementPostRequestProcessor process error:"
                    + e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
    }

    public static void main(String[] args) throws IOException {
        String merName = "360";
        CreepersParamDTO param = new CreepersParamDTO();
        param.putSearchKeyWord(merName);
        param.setTaskType(BaseConstant.TaskListType.JUDGEMENT_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        String url = "http://wenshu.court.gov.cn/List/ListContent?num=1";
        Request request = new Request(url);
        request.setMethod(HttpConstant.Method.POST);
        NameValuePair[] valuePairs = new NameValuePair[5];
        valuePairs[0] = new BasicNameValuePair("Param", "全文检索:" + merName);
        valuePairs[1] = new BasicNameValuePair("Index", "1");
        valuePairs[2] = new BasicNameValuePair("Page", String.valueOf(20));
        valuePairs[3] = new BasicNameValuePair("Order", "法院层级");
        valuePairs[4] = new BasicNameValuePair("Direction", "asc");
        request.putExtra("nameValuePair", valuePairs);
        Spider.create(new JudgementPostRequestProcessor()).setDownloader(new HttpRequestDownloader().setParam(param))
                .thread(2).addRequest(request).run();

    }
}
