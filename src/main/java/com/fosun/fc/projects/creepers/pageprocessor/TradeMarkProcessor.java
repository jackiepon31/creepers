package com.fosun.fc.projects.creepers.pageprocessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.pipeline.TradeMarkPipline;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;

/***
 * 
 * <p>
 * 中国商标网 使用Selenium做页面动态渲染。
 * </p>
 * 
 * @author Pengyk 2016-6-24 15:50:12
 */
@Component("tradeMarkProcessor")
public class TradeMarkProcessor extends BaseCreepersListProcessor implements PageProcessor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Site site;

    @Override
    public void process(Page page) {
        try {
            logger.info("page:" + page.getHtml());
            List<Selectable> trList = page.getHtml().xpath("table[@class='import_hpb_list']//tbody//tr").nodes();
            List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
            boolean isFirst = true;
            for (Selectable eachTr : trList) {
                if (isFirst) {
                    isFirst = false;
                    continue;
                }
                List<Selectable> tdList = eachTr.xpath("//td/allText()").nodes();
                if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 5) {
                    logger.info("=============>>>当前行不足5个td 无法解析:" + tdList);
                    continue;
                }
                Map<String, String> map = new HashMap<String, String>();
                // 序列号
                String seqNo = tdList.get(0).toString();
                map.put(CreepersConstant.TCreepersTradeMarkColumn.SEQ_NO.getValue(), seqNo);
                logger.info(CreepersConstant.TCreepersTradeMarkColumn.SEQ_NO.getValue() + ":" + seqNo);

                // 申请号
                String applyNo = tdList.get(1).toString();
                map.put(CreepersConstant.TCreepersTradeMarkColumn.APPLY_NO.getValue(), applyNo);
                logger.info(CreepersConstant.TCreepersTradeMarkColumn.APPLY_NO.getValue() + ":" + applyNo);
                // 类号
                String categoryNo = tdList.get(2).toString();
                map.put(CreepersConstant.TCreepersTradeMarkColumn.CATEGORY_NO.getValue(), categoryNo);
                logger.info(CreepersConstant.TCreepersTradeMarkColumn.CATEGORY_NO.getValue() + ":" + categoryNo);
                // 商标名称
                String tradeMarkName = tdList.get(3).toString();
                map.put(CreepersConstant.TCreepersTradeMarkColumn.TRADE_MARK_NAME.getValue(), tradeMarkName);
                logger.info(CreepersConstant.TCreepersTradeMarkColumn.TRADE_MARK_NAME.getValue() + ":" + tradeMarkName);
                // 申请人名称
                String applicant = tdList.get(4).toString();
                map.put(CreepersConstant.TCreepersTradeMarkColumn.APPLICANT.getValue(), applicant);
                logger.info(CreepersConstant.TCreepersTradeMarkColumn.APPLICANT.getValue() + ":" + applicant);
                map.put(CreepersConstant.TCreepersTradeMarkColumn.MER_NAME.getValue(), applicant);
                logger.info(CreepersConstant.TCreepersTradeMarkColumn.MER_NAME.getValue() + ":" + applicant);
                // 商品详情
                String wareContent = "http://sbcx.saic.gov.cn:9080/tmois/wszhcx_getLikeCondition.xhtml?regNum="
                        + applyNo + "&intcls=" + categoryNo + "&seriaNum=" + seqNo;
                map.put(CreepersConstant.TCreepersTradeMarkColumn.WARE.getValue(), wareContent);
                logger.info(CreepersConstant.TCreepersTradeMarkColumn.WARE.getValue() + ":" + wareContent);
                resultList.add(map);
            }
            page.putField("resultList", resultList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("TradeMarkProcessor process error:", e);
            CreepersParamDTO param = page.getResultItems().get(BaseConstant.PARAM_DTO_KEY);
            param.setErrorInfo("TradeMarkProcessor process error:" + e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
    }

    @Override
    public Site getSite() {
        if (null == site) {
            site = Site.me().setDomain("sbcx.saic.gov.cn/")// .setRetryTimes(3).setCycleRetryTimes(3)
                    .setTimeOut(90000)
                    .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
        }
        return site;
    }

    public static void main(String[] args) throws IOException {
        String merName = "达成包装制品(苏州)有限公司";
        String encodeName = CommonMethodUtils.toUtf8StringWith(merName, "%25");
        String url = "http://sbcx.saic.gov.cn:9080/tmois/wszhcx_getLikeCondition.xhtml?appCnName="
                + encodeName + "&intCls=&paiType=0";
        Request request = new Request(url);
        request.setMethod(HttpConstant.Method.GET);
        Spider.create(new TradeMarkProcessor()).thread(1).addPipeline(new TradeMarkPipline())
                .setDownloader(new HttpRequestDownloader()).addRequest(request).run();
    }

}
