package com.fosun.fc.projects.creepers.pageprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.downloader.SimulateLoginDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.google.common.collect.Sets;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

/**
 * 
 * <p>
 * Demo: 失信被执行人信息
 * </p>
 * 
 * @author MaXin
 * @since 2016年6月25日
 * @see
 */
@Component("dishonestyProcessor")
public class DishonestyProcessor implements PageProcessor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Site site;

    public DishonestyProcessor() {
        if (null == site) {
            site = Site.me().setDomain("daoyou-chaxun.cnta.gov.cn").setUserAgent(
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36")
                    // .addHeader("Accept",
                    // "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    // .addHeader("Accept-Encoding", "gzip,
                    // deflate").addHeader("Accept-Language", "zh-CN,zh;q=0.8")
                    // .addHeader("Cache-Control",
                    // "max-age=0").addHeader("Connection", "keep-alive")
                    // .addHeader("Content-Length",
                    // "115").addHeader("Content-Type",
                    // "application/x-www-form-urlencoded")
                    // .addHeader("Upgrade-Insecure-Requests", "1")
                    .setAcceptStatCode(Sets.newHashSet(200, 302))
                    .setSleepTime(5000);
        }

    }

    @Override
    public void process(Page page) {
        ResultItems resultItem = page.getResultItems();
        CreepersLoginParamDTO param = resultItem.get(BaseConstant.PARAM_DTO_KEY);
        if (page.getUrl().toString().equals(param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL))) {
            page.addTargetRequest(param.getOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL));
        } else if (page.getUrl().toString().equals(param.getOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL))) {
            Request request = CommonMethodUtils.buildDoLoginRequest(param);
            page.addTargetRequest(request);
        } else {
            logger.info(page.getHtml().toString());
            logger.info("页面爬取完毕。");
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        CreepersLoginParamDTO param = new CreepersLoginParamDTO();
        param.putSearchKeyWord("D-4502-009024");
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL,
                "http://daoyou-chaxun.cnta.gov.cn/single_info/selectlogin_1.asp?11");
        param.putOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL,
                "http://daoyou-chaxun.cnta.gov.cn/single_info/validatecode.asp");
        param.putOrderUrl(BaseConstant.OrderUrlKey.DO_LOGIN_URL,
                "http://daoyou-chaxun.cnta.gov.cn/single_info/selectlogin_1.asp");
        param.putNameValuePair("text_dyzh", param.getSearchKeyWordForString());
        param.putNameValuePair("text_dykh", "");
        param.putNameValuePair("text_dysfzh", "");
        param.putNameValuePair("useCapture", "false");
        param.putNameValuePair("passive", "false");
        param.setCaptchaKey("vcode");
        param.putNameValuePair("x", "30");
        param.putNameValuePair("y", "14");
        param.setDoRedirect(true);
        Request request = CommonMethodUtils.buildIndexRequest(param);
        request.setMethod(HttpConstant.Method.GET);
        Spider.create(new DishonestyProcessor()).setDownloader(new SimulateLoginDownloader().setParam(param))
                // .addUrl("http://daoyou-chaxun.cnta.gov.cn/single_info/selectlogin_1.asp")
                .addRequest(request).thread(1).runAsync();
    }
}
