package com.fosun.fc.projects.creepers.pageprocessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 
 * <p>
 * Demo: Post request
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月11日
 * @see
 */
public class PostWenShuProcessor implements PageProcessor {

    private Site site;

    public PostWenShuProcessor() {
        if (null == site) {
            site = Site.me().setDomain("rarbg.to").setTimeOut(100000)
                    .setUserAgent(
                            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2774.3 Safari/537.36");
        }

    }

    @Override
    public void process(Page page) {
        /// html/body/div[1]/div[2]/div/div/div[1]/ul
        /*
         * 对于POST请求处理 --webmagic的Post处理方式 String 增加POST请求参数 Request request =
         * new Request(urlString).putExtra("nameValuePair","Post请求参数数组");
         * 设置请求方法为POST request.setMethod(HttpConstant。Method。POST);
         * 添加请求到目标爬取请求地址中 page。addTargetRequest(request);
         */
        System.out.println(page.getHtml());
    }

    @Override
    public Site getSite() {

        return site;
    }

    public static void main(String[] args) {
        Spider.create(new PostWenShuProcessor()).thread(1)// .addPipeline(new
                                                          // FilePipeline("/webmagic/"))
                // .setDownloader(new PostRequestDownloader())
                .addUrl("https://rarbg.to/torrents.php?page=1").runAsync();
    }
}
