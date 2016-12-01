package com.fosun.fc.projects.creepers.pageprocessor;

import java.util.ArrayList;
import java.util.List;

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

/**
 * 
 * <p>
 * Demo: 法院被执行人信息
 * </p>
 * 
 * @author MaXin
 * @since 2016年7月7日
 * @see
 */
@Component("enforceeProcessor")
public class EnforceeProcessor implements PageProcessor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Site site;

    public EnforceeProcessor() {
    }

    @Override
    public void process(Page page) {
        logger.info("========================>>EnforceeProcessor:  start");
        ResultItems resultItem = page.getResultItems();
        CreepersLoginParamDTO param = resultItem.get(BaseConstant.PARAM_DTO_KEY);
        // 1.登陆页面，获取cookie，添加验证码链接，解析验证码。
        if (param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL).equals(page.getUrl().toString())) {
            page.setSkip(true);
            // A. use get method captcha url
            page.addTargetRequest(param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL));

            // B. use post method captcha url
            // Request request = CommonMethodUtils.buildCaptchaRequest(param);
            // page.addTargetRequest(request);

            // 2.验证码解析,写入验证码,执行登陆.
        } else if (param.getOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL).equals(page.getUrl().toString())) {
            page.setSkip(true);
            // use post method doLogin url
            Request request = CommonMethodUtils.buildDoLoginRequest(param);
            page.addTargetRequest(request);
            logger.info("current page is image. then skip.");
            // 3.登陆完成,添加target页面链接进行解析。
        } else if (param.getOrderUrl(BaseConstant.OrderUrlKey.DO_LOGIN_URL).equals(page.getUrl().toString())) {
            page.setSkip(true);

            // A. use post method targetUrls
            List<String> targetUrlList = param.getTargetUrlList();
            for (String targetUrl : targetUrlList) {
                Request request = CommonMethodUtils.buildDefaultRequest(param, targetUrl);
                page.addTargetRequest(request);
            }

            // B. use get method targetUrls
            // page.addTargetRequests(param.getTargetUrlList());

            // 4.target urls 下载解析
        } else {
            logger.info("page:" + page.getHtml());
            logger.info("页面爬取完毕。");
        }
    }

    @Override
    public Site getSite() {
        site = Site.me().setDomain("12333sh.gov.cn")
                .setUserAgent(
                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2774.3 Safari/537.36")
                .setSleepTime(5000).setAcceptStatCode(Sets.newHashSet(200, 302))
                .addHeader("Accept-Encoding", "gzip, deflate, sdch, br")
                // .addHeader("Accept",
                // "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .setCharset("GBK");
        return site;
    }

    public static void main(String[] args) {
        CreepersLoginParamDTO param = new CreepersLoginParamDTO();
        param.putOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL, "http://www.12333sh.gov.cn/sbsjb/wzb/Bmblist.jsp");
        param.putOrderUrl(BaseConstant.OrderUrlKey.DO_LOGIN_URL, "http://www.12333sh.gov.cn/sbsjb/wzb/dologin.jsp");
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, "http://www.12333sh.gov.cn/sbsjb/wzb/226.jsp");
        param.setCaptchaKey("userjym");
        param.setLoginNameKey("userid");
        param.setPasswordKey("userpw");
        param.setLoginName("411302199005180037");
        param.setPassword("123456");
        List<String> targetUrlList = new ArrayList<String>();
        targetUrlList.add("http://www.12333sh.gov.cn/sbsjb/wzb/sbsjbcx.jsp");
        param.setTargetUrlList(targetUrlList);
        // 对于请求登录时，参数只有用户名、密码、验证码的请求，不需要增加下面的请求参数设置。
        // Map<String, String> nameValuePair = new HashMap<String, String>();
        // nameValuePair.put("userid", "411302199005180037");
        // nameValuePair.put("userpw", "123456");
        // nameValuePair.put(param.getCaptchaKey(), "default");
        // param.setNameValuePair(nameValuePair);
        // 启动爬虫
        Request request = CommonMethodUtils.buildIndexRequest(param);
        Spider.create(new EnforceeProcessor()).setDownloader(new SimulateLoginDownloader().setParam(param))
                .addRequest(request).thread(1).runAsync();
    }
}
