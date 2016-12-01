package com.fosun.fc.projects.creepers.pageprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.pipeline.ResultListToFileModelPipline;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 
 * <p>
 * Demo: http://proxy.mimvp.com/free.php?proxy=in_tp 爬取 免费的proxy
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月11日
 * @see
 */
@Component("proxyMimvpProcessor")
public class ProxyMimvpProcessor implements PageProcessor {

    Logger logger = LoggerFactory.getLogger(getClass());
    private Site site;

    public ProxyMimvpProcessor() {
    }

    @SuppressWarnings("deprecation")
    @Override
    public void process(Page page) {
        Selectable tableSelectable = page.getHtml().xpath("//*[@id=\"list\"]/table");
        // logger.error("tableSelectable:"+tableSelectable);
        List<Selectable> trList = tableSelectable.xpath("//tbody/tr").nodes();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Selectable eachTr : trList) {
            // logger.error("======>>> eachTr:"+eachTr);
            List<Selectable> tdList = eachTr.xpath("//td").nodes();
            Map<String, Object> map = new HashMap<String, Object>();
            String seqNo = tdList.get(0).xpath("//allText()").toString();
            map.put("seqNo:", seqNo);
            logger.info("seqNo:" + seqNo);
            String ip = tdList.get(1).xpath("//allText()").toString();
            map.put(CreepersConstant.TCreepersProxyListColumn.IP.getValue(), ip);
            logger.info(CreepersConstant.TCreepersProxyListColumn.IP.getValue() + ":" + ip);
            String port = tdList.get(2).xpath("//img/@src").toString();

            String url = "http://proxy.mimvp.com/" + port;
            String resultXml = CommonMethodUtils.imageAnalytical(url,
                    BaseConstant.ImageAnalyticalType.URL_PATH.getValue(), url);
            String portValue = "";
            try {
                portValue = CommonMethodUtils.getNodeValue(
                        CommonMethodUtils.getChildElement(CommonMethodUtils
                                .getChildElement(CommonMethodUtils.getRootElement(resultXml), "ResultList"), "Item"),
                        "Result");
            } catch (Exception e) {
                logger.error("OCR_KING xml analytical false!");
                logger.error("resultXml:" + resultXml);
            }
            logger.info("OCR_KING:" + portValue);
            if (portValue.contains("B")) {
                portValue = portValue.replaceAll("B", "8");
            }
            if (portValue.contains("T")) {
                portValue = portValue.replaceAll("T", "7");
            }
            logger.info("befor replace:" + portValue);
            // http://proxy.mimvp.com/common/ygrandimg.php?id=17&port=NmDiQm5vNpzk3
            logger.info("img_src:" + url);
            map.put(CreepersConstant.TCreepersProxyListColumn.PORT.getValue(), portValue);
            logger.info(CreepersConstant.TCreepersProxyListColumn.IP.getValue() + ":" + portValue);
            String type = tdList.get(3).xpath("//allText()").toString();
            map.put(CreepersConstant.TCreepersProxyListColumn.IP_TYPE.getValue(), type);
            logger.info(CreepersConstant.TCreepersProxyListColumn.IP_TYPE.getValue() + ":" + type);
            if (portValue.length() > 0) {
                resultList.add(map);
            }
        }
        page.putField(CreepersConstant.TableNamesOthers.T_CREEPERS_PROXY_LIST.getMapKey(), resultList);
    }

    @Override
    public Site getSite() {
        site = Site.me().setDomain("proxy.mimvp.com").setRetryTimes(3).setCycleRetryTimes(3).setTimeOut(10000)
                .setSleepTime(CommonMethodUtils.randomSleepTime())
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0")
                ;
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new ProxyMimvpProcessor()).thread(2).addUrl("http://proxy.mimvp.com/free.php?proxy=in_tp")
                .addUrl("http://proxy.mimvp.com/free.php?proxy=in_hp")
                .addPipeline(new ResultListToFileModelPipline("/webmagic/")).runAsync();
    }
}
