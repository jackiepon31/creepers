package com.fosun.fc.projects.creepers.pageprocessor;

import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.pipeline.FileTxtModelPipline;

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
public class OtherInfoProcessor implements PageProcessor {

    private Site site;

    public OtherInfoProcessor() {
        if (null == site) {
            site = Site.me().setDomain("SHIXIN.COM").setRetryTimes(3).setCycleRetryTimes(3).setTimeOut(30000);
        }

    }

    @Override
    public void process(Page page) {

        /*
         * 对于POST请求处理 --webmagic的Post处理方式 String 增加POST请求参数 Request request =
         * new Request(urlString).putExtra("nameValuePair","Post请求参数数组");
         * 设置请求方法为POST request.setMethod(HttpConstant。Method。POST);
         * 添加请求到目标爬取请求地址中 page。addTargetRequest(request);
         */
        if (page.getUrl().regex("http://www.qixin.com/search\\?key=").match()) {
            
            System.out.println("\n ===============>>>查询URL：" + page.getUrl());
            page.putField("==============>>>>URL:", page.getUrl().toString());
//            http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=王若莎&idNumber=441522199408261765
            page.putField("姓名+证件号:", page.getUrl().regex("http://www.qixin.com/search?key=(.*)&type=negative&source=&isGlobal=Y").toString());
            System.out.println(
                    page.getHtml().xpath("//*[@id=\"searchLeft\"]/div[2]/div/div[1]/div/div[4]/div/div").toString());
            page.putField("result:", page.getHtml().xpath("//*[@id=\"searchLeft\"]/div[2]/div/div[1]/div/div[4]/div/div").toString());
        } else if (page.getUrl().regex("http://www.dailianmeng.com/xinyong/q/").match()) {
            System.out.println(page.getUrl().toString());
            // System.out.println(page.getHtml().xpath("/html/body/div[3]/div[3]").toString());
            // System.out.println(page.getHtml().xpath("//*[@id=\"yw1\"]").toString());
            System.out.println(page.getHtml());
        } else if (page.getUrl().regex("http://www.51zhengxin.com/front/blackDetail.html").match()) {
            System.out.println("\n ===============>>>查询URL：" + page.getUrl());
            page.putField("姓名:", page.getUrl().regex("http://www.51zhengxin.com/front/blackDetail.html\\?isP2PQuery=true&isDishonestyQuery=true&iname=(.*)&idNumber=").toString());
            page.putField("证件号:", page.getUrl().regex("&idNumber=(.*)").toString());
            page.putField("==============>>>>URL:", page.getUrl().toString());
//            http://www.qixin.com/search?key=覃志珍+450881197912126106&type=negative&source=&isGlobal=Y
//            if (page.getHtml().xpath("//*[@class=\"mc yuqi no-yuqi\"]").regex("<img src=\"images/noyuqi.jpg\"> ")
//                    .match()) {
//                System.out.println("无记录。");
//                page.putField("result:", "无记录。");
//            } else {
                System.out.println(page.getHtml().xpath("//*[@class=\"mc yuqi no-yuqi\"]").toString());
                page.putField("result:", page.getHtml().xpath("//*[@class=\"mc yuqi no-yuqi\"]").toString());
//            }
        }
    }

    @Override
    public Site getSite() {
        // site =
        // Site.me().setDomain("test.postSend.com").addCookie("bdshare_firstime",
        // "1461898580313")
        // .addCookie("wzwsconfirm",
        // "5b8ed6b4847eceb8911a509c243daf41").addCookie("wzwstemplate", "OA==")
        // .addCookie("ccpassport",
        // "f270fbddc3cc9a087b35b94c03ac06a7").addCookie("wzwschallenge", "-1")
        // .addCookie("yunsuo_session_verify",
        // "8446e8c62dca435047c198c1cf5b0f17")
        // .addCookie("_gsref_2116842793", "http://wenshu.court.gov.cn/")
        // .addCookie("_gscu_2116842793", "617565118yiix178")
        // .addCookie("_gscs_2116842793",
        // "629522554fyp2328|pv:2").addCookie("_gscbrs_2116842793", "1")
        // .addCookie("yunsuo_session_verify",
        // "08efbf7c7e1711b8c47173d70b9b86f0");

        return site;
    }

    public static void main(String[] args) {
        Spider.create(new OtherInfoProcessor()).thread(5).addPipeline(new FileTxtModelPipline("/webmagic/"))
                .setDownloader(new HttpRequestDownloader())
                // .addUrl("http://www.qixin.com/search?key=%E5%88%98%E6%96%B0%E4%BC%9A+441424197102196305&type=negative&source=&isGlobal=Y")
                //// .addUrl("http://www.dailianmeng.com/xinyong/q/432902197511050049.html")//验证码
                // .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=刘丽娟&idNumber=441523198609095523")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=王若莎&idNumber=441522199408261765")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=吴秋仪&idNumber=421181199310275549")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=林跃如&idNumber=445221199006201947")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=戎明礼&idNumber=420111198310125036")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=郑泽坚&idNumber=441522198412121779")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=李华春&idNumber=512926197808254190")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=卓俊伟&idNumber=441522198010213371")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=叶培春&idNumber=362127197612050916")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=张凤芝&idNumber=430522198101253886")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=朱军委&idNumber=441422198005223713")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=何云东&idNumber=612425198811187034")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=朱增华&idNumber=441581199206242494")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=唐幼桥&idNumber=422201197912280519")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=蔡思苗&idNumber=441581199710147787")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=冉娟&idNumber=422422198001142147")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=郑丙树&idNumber=441522198011027992")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=覃志珍&idNumber=450881197912126106")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=玉秋华&idNumber=450821198704100655")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=朱淑梅&idNumber=440623196610292327")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=范美莹&idNumber=445322199407234020")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=余光统&idNumber=440825198006291152")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=董敏&idNumber=441825197904300045")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=罗志丹&idNumber=445281198907100432")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=张兰娟&idNumber=44098119891112326X")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=黄梅桂&idNumber=44142719740929112X")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=梁展燕&idNumber=450104197608151525")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=欧爱群&idNumber=441522197804010045")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=陈广权&idNumber=441424198502056955")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=曾华开&idNumber=441423198309092333")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=谢惠珠&idNumber=441581199307202467")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=梁红李&idNumber=441581197906048626")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=徐伟&idNumber=411528197812197113")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=朱香珠&idNumber=441522198605128627")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=唐匡师&idNumber=452428198501262050")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=杨娇梅&idNumber=440902198710245628")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=李东根&idNumber=440982199205065171")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=林洋渊&idNumber=441522197505133037")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=彭志发&idNumber=440526196609010816")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=程宜才&idNumber=440921198106054218")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=刘丽娟&idNumber=441523198609095523")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=周永章&idNumber=441522198206208638")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=李建康&idNumber=432301197002106017")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=卓韩子&idNumber=441522198409213373")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=程海军&idNumber=362330198111092417")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=林曲生&idNumber=441522197208153058")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=杨伟生&idNumber=445281198306231314")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=周儒荣&idNumber=512224196312252479")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=刘新会&idNumber=441424197102196305")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=叶远杯&idNumber=441523198810147030")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=邹良香&idNumber=432902197511050049")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=彭石平&idNumber=440204197609133111")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=宋春燕&idNumber=421281198401040025")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=巫培尧&idNumber=441622197502265734")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=李水坚&idNumber=441481198606223858")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=袁协波&idNumber=445221197810231259")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=余芬芬&idNumber=362322198808138462")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=明会兰&idNumber=42022219840919444x")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=康卓洪&idNumber=44128119800919121X")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=林忠平&idNumber=362123198606081897")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=蔡春霞&idNumber=441424198802280326")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=苏秀梅&idNumber=440924196109261423")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=耿斯佳&idNumber=440982198402101499")
//                .addUrl("http://www.51zhengxin.com/front/blackDetail.html?isP2PQuery=true&isDishonestyQuery=true&iname=黄甲&idNumber=441522197812165979")
                .addUrl("http://www.qixin.com/search?key=王若莎+441522199408261765&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=吴秋仪+421181199310275549&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=林跃如+445221199006201947&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=戎明礼+420111198310125036&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=郑泽坚+441522198412121779&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=李华春+512926197808254190&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=卓俊伟+441522198010213371&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=叶培春+362127197612050916&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=张凤芝+430522198101253886&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=朱军委+441422198005223713&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=何云东+612425198811187034&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=朱增华+441581199206242494&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=唐幼桥+422201197912280519&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=蔡思苗+441581199710147787&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=冉娟+422422198001142147&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=郑丙树+441522198011027992&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=覃志珍+450881197912126106&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=玉秋华+450821198704100655&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=朱淑梅+440623196610292327&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=范美莹+445322199407234020&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=余光统+440825198006291152&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=董敏+441825197904300045&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=罗志丹+445281198907100432&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=张兰娟+44098119891112326X&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=黄梅桂+44142719740929112X&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=梁展燕+450104197608151525&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=欧爱群+441522197804010045&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=陈广权+441424198502056955&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=曾华开+441423198309092333&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=谢惠珠+441581199307202467&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=梁红李+441581197906048626&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=徐伟+411528197812197113&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=朱香珠+441522198605128627&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=唐匡师+452428198501262050&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=杨娇梅+440902198710245628&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=李东根+440982199205065171&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=林洋渊+441522197505133037&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=彭志发+440526196609010816&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=程宜才+440921198106054218&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=刘丽娟+441523198609095523&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=周永章+441522198206208638&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=李建康+432301197002106017&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=卓韩子+441522198409213373&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=程海军+362330198111092417&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=林曲生+441522197208153058&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=杨伟生+445281198306231314&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=周儒荣+512224196312252479&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=刘新会+441424197102196305&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=叶远杯+441523198810147030&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=邹良香+432902197511050049&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=彭石平+440204197609133111&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=宋春燕+421281198401040025&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=巫培尧+441622197502265734&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=李水坚+441481198606223858&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=袁协波+445221197810231259&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=余芬芬+362322198808138462&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=明会兰+42022219840919444x&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=康卓洪+44128119800919121X&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=林忠平+362123198606081897&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=蔡春霞+441424198802280326&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=苏秀梅+440924196109261423&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=耿斯佳+440982198402101499&type=negative&source=&isGlobal=Y")
                .addUrl("http://www.qixin.com/search?key=黄甲+441522197812165979&type=negative&source=&isGlobal=Y")

        .runAsync();
    }
}
