package com.fosun.fc.projects.creepers.pageprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant.TableNamesBusinessInfo;
import com.fosun.fc.projects.creepers.downloader.SeleniumDownloader;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 
 * <p>
 * 上海市工商信息明细
 * </p>
 * 
 * @author pengyk
 * @since 2016年7月12日
 * @see
 */
@SuppressWarnings("unused")
@Component("businessInfoDetail31Processor")
public class BusinessInfoDetail31Processor implements PageProcessor {

	private Site site;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final String HTML_CONTENT = "htmlContent";
	private static final String REGEX_TAB1 = "https://www.sgs.gov.cn/notice/notice/view\\?uuid=\\w+&tab=01";
	private static final String REGEX_TAB2 = "https://www.sgs.gov.cn/notice/notice/view\\?uuid=\\w+&tab=02";
	private static final String REGEX_TAB3 = "https://www.sgs.gov.cn/notice/notice/view\\?uuid=\\w+&tab=03";
	private static final String REGEX_TAB4 = "https://www.sgs.gov.cn/notice/notice/view\\?uuid=\\w+&tab=04";
	private static final String XPATH_MER_BASIC_START = "//div[@rel='layout-01_01']//";
	private static final String XPATH_MER_SHAREHOLDER_START = "table[@id='investorTable']//tbody//tr[@class='page-item']";
	private static final String XPATH_MER_CHANGE_START = "//table[@id='alterTable']";
	private static final String XPATH_MER_KEYMAN_START = "table[@id='memberTable']/tbody//tr[@class='page-item']";
	private static final String XPATH_MER_BRANCH_START = "table[@id='branchTable']/tbody//tr[@class='page-item']";
	private static final String XPATH_MER_PROPERTY_START = "table[@id='mortageTable']/tbody//tr[@class='page-item']";
	private static final String XPATH_MER_PLEDGE_START = "table[@id='pledgeTable']/tbody//tr[@class='page-item']";
	private static final String XPATH_MER_PENALTY_START = "table[@id='punishTable']/tbody//tr[@class='page-item']";
	private static final String XPATH_MER_ABNORMAL_START = "table[@id='exceptTable']/tbody//tr[@class='page-item']";
	private static final String XPATH_MER_ILLEGAL_START = "table[@id='blackTable']/tbody//tr[@class='page-item']";
	private static final String XPATH_MER_BASIC_END = "/text()";
	private static final String XPATH_MER_BASIC_MER_NO = "tr[2]/td[1]";
	private static final String XPATH_MER_BASIC_MER_NAME = "tr[2]/td[2]";
	private static final String XPATH_MER_BASIC_MER_TYPE = "tr[3]/td[1]";
	private static final String XPATH_MER_BASIC_LEGAL_REP= "tr[3]/td[2]";
	private static final String XPATH_MER_BASIC_REG_CAPITAL = "tr[4]/td[1]";
	private static final String XPATH_MER_BASIC_FOUND_DT = "tr[4]/td[2]";
	private static final String XPATH_MER_BASIC_ADDR= "tr[5]/td[1]";
	private static final String XPATH_MER_BASIC_OPR_START_DT = "tr[6]/td[1]";
	private static final String XPATH_MER_BASIC_OPR_END_DT = "tr[6]/td[2]";
	private static final String XPATH_MER_BASIC_SCOPE = "tr[7]/td[1]";
	private static final String XPATH_MER_REG_AUTHORITY= "tr[8]/td[1]";
	private static final String XPATH_MER_BASIC_APPROVAL_DATE = "tr[8]/td[2]";
	private static final String XPATH_MER_BASIC_REG_STATUS = "tr[9]/td[1]";
	private static final String XPATH_TH_TEXT = "//tr/th/allText()";
	private static final String XPATH_TR_CLASS = "//tr[@class='page-item']";
	private static final String TH_TITLE_1 = "变更信息";
	private static final String TH_TITLE_2 = "撤销信息";
	private static final String XPATH_TD_DETAIL = "/a/@href";
	private static final String XPATH_TD_1 = "//td[1]"; 
	private static final String XPATH_TD_2 = "//td[2]"; 
	private static final String XPATH_TD_3 = "//td[3]"; 
	private static final String XPATH_TD_4 = "//td[4]"; 
	private static final String XPATH_TD_5 = "//td[5]"; 
	private static final String XPATH_TD_6 = "//td[6]";
	private static final String XPATH_TD_7 = "//td[7]";
	private static final String XPATH_TD_8 = "//td[8]"; 
	private static final String XPATH_TD_9 = "//td[9]"; 
	private static final String XPATH_TD_10 = "//td[10]"; 
	public BusinessInfoDetail31Processor() {
		if (null == site) {
			site = Site.me().setDomain("gsxt.saic.gov.cn")
					.setUserAgent(
							"Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36")
					.addCookie("_gscu_993197171“", "665853201vizsz21")
					.addCookie("JSESSIONID", "0001SnjrsQdHeokYndSsYNkOFRx:1ahbb47sb")
					.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
					.addHeader("Accept-Encoding", "gzip, deflate").addHeader("Accept-Language", "zh-CN,zh;q=0.8")
					.addHeader("Cache-Control", "max-age=0").addHeader("Connection", "keep-alive")
					.addHeader("Content-Length", "188").addHeader("Content-Type", "application/x-www-form-urlencoded")
					.addHeader("Host", "www.sgs.gov.cn").addHeader("Origin", "https://www.sgs.gov.cn")
					.addHeader("Referer", "https://www.sgs.gov.cn/notice/search/ent_info_list")
					.addHeader("Upgrade-Insecure-Requests", "1").setSleepTime(5000);
		}

	}

	@Override
	public void process(Page page) {
		// 将页面信息返回 用于生成文件。
		page.putField(HTML_CONTENT, page.getHtml().toString());
		TableNamesBusinessInfo[] getNames = CreepersConstant.TableNamesBusinessInfo.values();
        for (TableNamesBusinessInfo TableNamesBusinessInfo : getNames) {
            Object obj;
            if (TableNamesBusinessInfo.isList()) {
                obj = new ArrayList<Map<String, Object>>();
            } else {
                obj = new HashMap<String, Object>();
            }
            page.putField(TableNamesBusinessInfo.getMapKey(), obj);
        }
		if (page.getUrl().regex(REGEX_TAB1).match()) {
			logger.debug("=====>>>工商公示信息爬取开始!");
			methodGsgs(page);
			logger.debug("=====>>>工商公示信息爬取结束!");
		} else if (page.getUrl().regex(REGEX_TAB2).match()) {
			logger.debug("=====>>>企业公示信息爬取开始!");
		} else if (page.getUrl().regex(REGEX_TAB3).match()) {
			logger.debug("=====>>>其他部门公示信息爬取开始!");
		} else if (page.getUrl().regex(REGEX_TAB4).match()) {
			logger.debug("=====>>>司法协助公示信息爬取开始!");
		}
	}
	/**
     * 
     * <p>
     * description: 工商公示信息爬取
     * </p>
     * 
     * @param page
     * @author Pengyk
     * @see 2016-7-12 13:04:31
     */
    private void methodGsgs(Page page) {
        logger.debug("============1-1-1==========>>>工商公示信息-登记信息爬取开始!");
        step1(page);
        logger.debug("============1-1-2==========>>>工商公示信息-股东信息爬取开始!");
        step2(page);
        logger.debug("============1-1-2==========>>>工商公示信息-变更及撤销信息爬取开始!");
        step3(page);
        logger.debug("============1-2-1==========>>>工商公示信息-主要人员信息爬取开始!");
        step4(page);
        logger.debug("============1-2-2==========>>>工商公示信息-分支机构信息爬取开始!");
        step5(page);
        logger.debug("============1-3-1==========>>>工商公示信息-动产抵押登记信息爬取开始!");
        step6(page);
        logger.debug("============1-4-1==========>>>工商公示信息-股权出质信息爬取开始!");
        step7(page);
        logger.debug("============1-5-1==========>>>工商公示信息-行政处罚信息爬取开始!");
        step8(page);
        logger.debug("============1-6-1==========>>>工商公示信息-异常经营信息爬取开始!");
        step9(page);
        logger.debug("============1-6-1==========>>>工商公示信息-异常经营信息爬取开始!");
        step10(page);        
    }

	/**
	 * 
	 * <p>
	 * description: 企业公式信息爬取
	 * </p>
	 * 
	 * @param page
	 * @author Pengyk
	 * @see 2016-7-12 13:04:31
	 */
	
    private void methodQygs(Page page) {

	}

	/**
	 * 
	 * <p>
	 * description: 其他部门公示信息爬取
	 * </p>
	 * 
	 * @param page
	 * @author Pengyk
	 * @see 2016-7-12 13:04:31
	 */
	private void methodQtgs(Page page) {

	}

	/**
	 * 
	 * <p>
	 * description: 司法协助信息爬取
	 * </p>
	 * 
	 * @param page
	 * @author Pengyk
	 * @see 2016-7-12 13:04:31
	 */
	private void methodSfxz(Page page) {

	}
	
	/**
	 * @param page
	 * @param resultItems
	 */
	private void step10(Page page) {
		ResultItems resultItems = page.getResultItems();
		List<Selectable> illegalList = page.getHtml().xpath(XPATH_MER_ILLEGAL_START).nodes();
        List<Map<String, String>> ceepersMerIllegalList = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_ILLEGAL.getMapKey());
        for (Selectable selectable : illegalList) {
        	Map<String,String> ceepersMerIllegal = new HashMap<String,String>();
        	//序号
    		String seqNo = selectable.xpath(XPATH_TD_1).toString().trim();
    		ceepersMerIllegal.put(CreepersConstant.TCreepersMerIllegalColumn.SEQ_NO.getValue(), seqNo);
    		//列入严重违法名录原因
    		String illegalReason = selectable.xpath(XPATH_TD_2).toString().trim();
    		ceepersMerIllegal.put(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_REASON.getValue(),illegalReason);
    		//列入日期
    		String illegalDt = selectable.xpath(XPATH_TD_3).toString().trim();
    		ceepersMerIllegal.put(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_DT.getValue(), illegalDt);
    		//移出严重违法名录原因
    		String removeReason = selectable.xpath(XPATH_TD_4).toString().trim();
    		ceepersMerIllegal.put(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_REASON.getValue(), removeReason);
    		//移出日期
    		String removeDt = selectable.xpath(XPATH_TD_5).toString().trim();
    		ceepersMerIllegal.put(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_DT.getValue(), removeDt);
    		//作出决定机关
    		String illegalAuthority = selectable.xpath(XPATH_TD_6).toString().trim();
    		ceepersMerIllegal.put(CreepersConstant.TCreepersMerIllegalColumn.AUTHORITY.getValue(), illegalAuthority);
    		ceepersMerIllegalList.add(ceepersMerIllegal);
		}
        logger.debug("============1-6-1==========>>>工商公示信息-异常经营信息爬取结束!");
	}

	/**
	 * @param page
	 * @param resultItems
	 */
	private void step9(Page page) {
		ResultItems resultItems = page.getResultItems();
		List<Selectable> abnormalList = page.getHtml().xpath(XPATH_MER_ABNORMAL_START).nodes();
        List<Map<String, String>> ceepersMerAbnormalList = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_ABNORMAL.getMapKey());
        for (Selectable selectable : abnormalList) {
        	Map<String,String> ceepersMerAbnormal = new HashMap<String,String>();
        	//序号
    		String seqNo = selectable.xpath(XPATH_TD_1).toString().trim();
    		ceepersMerAbnormal.put(CreepersConstant.TCreepersMerAbnormalColumn.SEQ_NO.getValue(), seqNo);
    		//列入经营异常名录原因
    		String abnormalReason = selectable.xpath(XPATH_TD_2).toString().trim();
    		ceepersMerAbnormal.put(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_REASON.getValue(),abnormalReason);
    		//列入日期
    		String abnormalDt = selectable.xpath(XPATH_TD_3).toString().trim();
    		ceepersMerAbnormal.put(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_DT.getValue(), abnormalDt);
    		//移出经营异常名录原因
    		String removeReason = selectable.xpath(XPATH_TD_4).toString().trim();
    		ceepersMerAbnormal.put(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_REASON.getValue(), removeReason);
    		//移出日期
    		String removeDt = selectable.xpath(XPATH_TD_5).toString().trim();
    		ceepersMerAbnormal.put(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_DT.getValue(), removeDt);
    		//作出决定机关
    		String abnormalAuthority = selectable.xpath(XPATH_TD_6).toString().trim();
    		ceepersMerAbnormal.put(CreepersConstant.TCreepersMerAbnormalColumn.AUTHORITY.getValue(), abnormalAuthority);
    		ceepersMerAbnormalList.add(ceepersMerAbnormal);
		}
        logger.debug("============1-6-1==========>>>工商公示信息-异常经营信息爬取结束!");
	}

	/**
	 * @param page
	 * @param resultItems
	 */
	private void step8(Page page) {
		ResultItems resultItems = page.getResultItems();
		List<Selectable> penaltyList = page.getHtml().xpath(XPATH_MER_PENALTY_START).nodes();
        List<Map<String, String>> ceepersMerPenaltyList = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PENALTY.getMapKey());
        for (Selectable selectable : penaltyList) {
        	Map<String,String> ceepersMerPenalty = new HashMap<String,String>();
        	//序号
    		String seqNo = selectable.xpath(XPATH_TD_1).toString().trim();
    		ceepersMerPenalty.put(CreepersConstant.TCreepersMerPenaltyColumn.SEQ_NO.getValue(), seqNo);
    		//行政处罚决定文书号
    		String penaltyNo = selectable.xpath(XPATH_TD_2).toString().trim();
    		ceepersMerPenalty.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_NO.getValue(),penaltyNo );
    		//违法行为类型
    		String penaltyType = selectable.xpath(XPATH_TD_3).toString().trim();
    		ceepersMerPenalty.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_TYPE.getValue(), penaltyType);
    		//行政处罚内容
    		String penaltyContent = selectable.xpath(XPATH_TD_4).toString().trim();
    		ceepersMerPenalty.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_CONTENT.getValue(), penaltyContent);
    		//作出行政处罚机关
    		String penaltyAuthority = selectable.xpath(XPATH_TD_5).toString().trim();
    		ceepersMerPenalty.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_AUTHORITY.getValue(), penaltyAuthority);
    		//处罚内容决定日期
    		String penaltyDt = selectable.xpath(XPATH_TD_6).toString().trim();
    		ceepersMerPenalty.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_DT.getValue(), penaltyDt);
    		//详情
    		String penaltyDetails = selectable.xpath(XPATH_TD_7+XPATH_TD_DETAIL).toString().trim();
    		ceepersMerPenalty.put(CreepersConstant.TCreepersMerPenaltyColumn.DETAILS.getValue(), penaltyDetails);
    		ceepersMerPenaltyList.add(ceepersMerPenalty);
		}
        logger.debug("============1-5-1==========>>>工商公示信息-行政处罚信息爬取结束!");
	}

	/**
	 * @param page
	 * @param resultItems
	 */
	private void step7(Page page) {
		ResultItems resultItems = page.getResultItems();
		List<Selectable> pledgeList = page.getHtml().xpath(XPATH_MER_PLEDGE_START).nodes();
        List<Map<String, String>> ceepersMerPledgeList = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PLEDGE.getMapKey());
        for (Selectable selectable : pledgeList) {
        	Map<String,String> ceepersMerPledge = new HashMap<String,String>();
        	//序号
    		String seqNo = selectable.xpath(XPATH_TD_1).toString().trim();
    		ceepersMerPledge.put(CreepersConstant.TCreepersMerPledgeColumn.SEQ_NO.getValue(), seqNo);
    		//登记号
    		String regNo = selectable.xpath(XPATH_TD_2).toString().trim();
    		ceepersMerPledge.put(CreepersConstant.TCreepersMerPledgeColumn.REG_NO.getValue(), regNo);
    		//出质人
    		String pledger = selectable.xpath(XPATH_TD_3).toString().trim();
    		ceepersMerPledge.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER.getValue(), pledger);
    		//出质人证件号
    		String pledgerCertNo = selectable.xpath(XPATH_TD_4).toString().trim();
    		ceepersMerPledge.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER_CERT_NO.getValue(), pledgerCertNo);
    		//出质股权数额
    		String pledgeAmount = selectable.xpath(XPATH_TD_5).toString().trim();
    		ceepersMerPledge.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_AMOUNT.getValue(), pledgeAmount);
    		//质权人
    		String pledgee = selectable.xpath(XPATH_TD_6).toString().trim();
    		ceepersMerPledge.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE.getValue(), pledgee);
    		//质权人证件号
    		String pledgeeCertNo = selectable.xpath(XPATH_TD_7).toString().trim();
    		ceepersMerPledge.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE_CERT_NO.getValue(), pledgeeCertNo);
    		//股权出质设立登记日期
    		String pledgeDt = selectable.xpath(XPATH_TD_8).toString().trim();
    		ceepersMerPledge.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_DT.getValue(), pledgeDt);
    		//状态
    		String statusPledge = selectable.xpath(XPATH_TD_9).toString().trim();
    		ceepersMerPledge.put(CreepersConstant.TCreepersMerPledgeColumn.STATUS.getValue(), statusPledge);
    		//变化情况
    		String changeInfoPledge = selectable.xpath(XPATH_TD_10+XPATH_TD_DETAIL).toString().trim();
    		ceepersMerPledge.put(CreepersConstant.TCreepersMerPledgeColumn.CHANGE_INFO.getValue(), changeInfoPledge);
    		ceepersMerPledgeList.add(ceepersMerPledge);
		}
        logger.debug("============1-4-1==========>>>工商公示信息-股权出质登记信息爬取结束!");
	}

	/**
	 * @param page
	 * @param resultItems
	 */
	private void step6(Page page) {
		ResultItems resultItems = page.getResultItems();
		List<Selectable> propertyList = page.getHtml().xpath(XPATH_MER_PROPERTY_START).nodes();
        List<Map<String, String>> ceepersMerPropertyList = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PROPERTY.getMapKey());
        for (Selectable selectable : propertyList) {
        	Map<String,String> ceepersMerProperty = new HashMap<String,String>();
        	//序号
    		String seqNo = selectable.xpath(XPATH_TD_1).toString().trim();
    		ceepersMerProperty.put(CreepersConstant.TCreepersMerPropertyColumn.SEQ_NO.getValue(), seqNo);
    		//登记号
    		String regNo = selectable.xpath(XPATH_TD_2).toString().trim();
    		ceepersMerProperty.put(CreepersConstant.TCreepersMerPropertyColumn.REG_NO.getValue(), regNo);
    		//登记日期
    		String regDt = selectable.xpath(XPATH_TD_3).toString().trim();
    		ceepersMerProperty.put(CreepersConstant.TCreepersMerPropertyColumn.REG_DT.getValue(), regDt);
    		//登记机关
    		String regAuthorityPro = selectable.xpath(XPATH_TD_4).toString().trim();
    		ceepersMerProperty.put(CreepersConstant.TCreepersMerPropertyColumn.REG_AUTHORITY.getValue(), regAuthorityPro);
    		//被担保债权数额
    		String claimAmount = selectable.xpath(XPATH_TD_5).toString().trim();
    		ceepersMerProperty.put(CreepersConstant.TCreepersMerPropertyColumn.CLAIM_AMOUNT.getValue(), claimAmount);
    		//状态
    		String status = selectable.xpath(XPATH_TD_6).toString().trim();
    		ceepersMerProperty.put(CreepersConstant.TCreepersMerPropertyColumn.STATUS.getValue(), status);
    		//详情
    		String details = selectable.xpath(XPATH_TD_7+XPATH_TD_DETAIL).toString().trim();
    		ceepersMerProperty.put(CreepersConstant.TCreepersMerPropertyColumn.DETAILS.getValue(), details);
    		ceepersMerPropertyList.add(ceepersMerProperty);
		}
        logger.debug("============1-3-1==========>>>工商公示信息-动产抵押登记信息爬取结束!");
	}

	/**
	 * @param page
	 * @param resultItems
	 */
	private void step5(Page page) {
		ResultItems resultItems = page.getResultItems();
		List<Selectable> branchList = page.getHtml().xpath(XPATH_MER_BRANCH_START).nodes();
        List<Map<String, String>> ceepersMerBranchList = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_BRANCH.getMapKey());
        for (Selectable selectable : branchList) {
        	Map<String,String> ceepersMerBranch = new HashMap<String,String>();
        	//序号
    		String seqNo = selectable.xpath(XPATH_TD_1).toString().trim();
    		ceepersMerBranch.put(CreepersConstant.TCreepersMerBranchColumn.SEQ_NO.getValue(), seqNo);
    		//注册号/统一社会信用代码
    		String regNo = selectable.xpath(XPATH_TD_2).toString().trim();
    		ceepersMerBranch.put(CreepersConstant.TCreepersMerBranchColumn.REG_NO.getValue(), regNo);
    		//名称
    		String name = selectable.xpath(XPATH_TD_3).toString().trim();
    		ceepersMerBranch.put(CreepersConstant.TCreepersMerBranchColumn.NAME.getValue(), name);
    		//登记机关
    		String regAuthorityBranch = selectable.xpath(XPATH_TD_4).toString().trim();
    		ceepersMerBranch.put(CreepersConstant.TCreepersMerBranchColumn.REG_AUTHORITY.getValue(), regAuthorityBranch);
    		ceepersMerBranchList.add(ceepersMerBranch);
		}
        logger.debug("============1-2-2==========>>>工商公示信息-分之机构信息爬取结束!");
	}

	/**
	 * @param page
	 * @param resultItems
	 */
	private void step4(Page page) {
		ResultItems resultItems = page.getResultItems();
		List<Selectable> keymanList = page.getHtml().xpath(XPATH_MER_KEYMAN_START).nodes();
        List<Map<String, String>> ceepersMerKeyManList = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_KEYMAN.getMapKey());
        for (Selectable selectable : keymanList) {
        	Map<String,String> ceepersKeyMan = new HashMap<String,String>();
        	//序列号
    		String seqNo = selectable.xpath(XPATH_TD_1).toString().trim();
    		ceepersKeyMan.put(CreepersConstant.TCreepersMerKeymanColumn.SEQ_NO.getValue(), seqNo);
    		//姓名
    		String name = selectable.xpath(XPATH_TD_2).toString().trim();
    		ceepersKeyMan.put(CreepersConstant.TCreepersMerKeymanColumn.NAME.getValue(), name);
    		//职务
    		String position = selectable.xpath(XPATH_TD_3).toString().trim();
    		ceepersKeyMan.put(CreepersConstant.TCreepersMerKeymanColumn.POSITION.getValue(), position);
    		ceepersMerKeyManList.add(ceepersKeyMan);
    		Map<String,String> ceepersKeyManNew = new HashMap<String,String>();
    		//序列号
    		String seqNoNew = selectable.xpath(XPATH_TD_4).toString().trim();
    		if(seqNoNew!=null && !"".equals(seqNoNew)){
    			ceepersKeyManNew.put(CreepersConstant.TCreepersMerKeymanColumn.SEQ_NO.getValue(), seqNoNew);
        		//姓名
        		String nameNew = selectable.xpath(XPATH_TD_5).toString().trim();
        		ceepersKeyManNew.put(CreepersConstant.TCreepersMerKeymanColumn.NAME.getValue(), nameNew);
        		//职务
        		String positionNew = selectable.xpath(XPATH_TD_6).toString().trim();
        		ceepersKeyManNew.put(CreepersConstant.TCreepersMerKeymanColumn.POSITION.getValue(), positionNew);
        		ceepersMerKeyManList.add(ceepersKeyManNew);
    		}
			
		}
        logger.debug("============1-2-1==========>>>工商公示信息-主要人员信息爬取结束!");
	}

	/**
	 * @param page
	 * @param resultItems
	 */
	private void step3(Page page) {
		ResultItems resultItems = page.getResultItems();
		List<Selectable> alertList = page.getHtml().xpath(XPATH_MER_CHANGE_START).nodes();
        List<Map<String, String>> ceepersMerChangeList = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_CHANGE.getMapKey());
        List<Map<String, String>> ceepersMerUndoList = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_UNDO.getMapKey());
        for (Selectable selectableTotal : alertList) {
        	if (selectableTotal.xpath(XPATH_TH_TEXT).regex(TH_TITLE_1).match()) {
				 logger.debug("============1-1-3==========>>>工商公示信息-变更信息爬取开始!");
			        List<Selectable> changeList = selectableTotal.xpath(XPATH_TR_CLASS).nodes();
			        for (Selectable selectable : changeList) {
			        	Map<String,String> ceepersMerChange = new HashMap<String,String>();
			        	//变更事项
			    		String changeItem = selectable.xpath(XPATH_TD_1).toString().trim();
			    		ceepersMerChange.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_ITEM.getValue(), changeItem);
			    		//变更前内容
			    		String changeOld = selectable.xpath(XPATH_TD_2).toString().trim();
			    		ceepersMerChange.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_OLD.getValue(), changeOld);
			    		//变更后内容
			    		String changeNew = selectable.xpath(XPATH_TD_3).toString().trim();
			    		ceepersMerChange.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_NEW.getValue(), changeNew);
			    		//变更日期
			    		String changeDt = selectable.xpath(XPATH_TD_4).toString().trim();
			    		ceepersMerChange.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_DT.getValue(), changeDt);
			    		ceepersMerChangeList.add(ceepersMerChange);
					}
			        logger.debug("============1-1-3==========>>>工商公示信息-变更信息爬取结束!");
				
				
			} else if (selectableTotal.xpath(XPATH_TH_TEXT).regex(TH_TITLE_2).match()) {
				logger.info("entry 撤销信息");
				 logger.debug("============1-1-4==========>>>工商公示信息-撤销信息爬取开始!");
		        List<Selectable> undoList = selectableTotal.xpath(XPATH_TR_CLASS).nodes();
		        for (Selectable selectable : undoList) {
		        	Map<String,String> ceepersMerUndo = new HashMap<String,String>();
		        	//撤销事项
		    		String undoItem = selectable.xpath(XPATH_TD_1).toString().trim();
		    		ceepersMerUndo.put(CreepersConstant.TCreepersMerUndoColumn.UNDO_ITEM.getValue(), undoItem);
		    		//撤销前内容
		    		String undoOld = selectable.xpath(XPATH_TD_2).toString().trim();
		    		ceepersMerUndo.put(CreepersConstant.TCreepersMerUndoColumn.UNDO_OLD.getValue(), undoOld);
		    		//撤销后内容
		    		String undoNew = selectable.xpath(XPATH_TD_3).toString().trim();
		    		ceepersMerUndo.put(CreepersConstant.TCreepersMerUndoColumn.UNDO_NEW.getValue(), undoNew);
		    		//撤销日期
		    		String undoDt = selectable.xpath(XPATH_TD_4).toString().trim();
		    		ceepersMerUndo.put(CreepersConstant.TCreepersMerUndoColumn.UNDO_DT.getValue(), undoDt);
		    		ceepersMerUndoList.add(ceepersMerUndo);
				}
		        logger.debug("============1-1-4==========>>>工商公示信息-撤销信息爬取结束!");
		        
			}
		}
	}

	/**
	 * @param page
	 */
	private void step2(Page page) {
		ResultItems resultItems = page.getResultItems();
		List<Selectable> shareList = page.getHtml().xpath(XPATH_MER_SHAREHOLDER_START).nodes();
        List<Map<String, String>> ceepersMerShareholderList = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_SHAREHOLDER.getMapKey());
        for (Selectable selectable : shareList) {
        	//股东类型
        	Map<String,String> ceepersMerShareholder = new HashMap<String,String>();
        	String shareType = selectable.xpath(XPATH_TD_1).toString().trim();
        	ceepersMerShareholder.put(CreepersConstant.TCreepersMerShareholderColumn.SHARE_TYPE.getValue(), shareType);
    		//股东
    		String name = selectable.xpath(XPATH_TD_2).toString().trim();
    		ceepersMerShareholder.put(CreepersConstant.TCreepersMerShareholderColumn.NAME.getValue(), name);
    		//证件类型
    		String certType = selectable.xpath(XPATH_TD_3).toString().trim();
    		ceepersMerShareholder.put(CreepersConstant.TCreepersMerShareholderColumn.CERT_TYPE.getValue(), certType);
    		//证件号码
    		String certNo = selectable.xpath(XPATH_TD_4).toString().trim();
    		ceepersMerShareholder.put(CreepersConstant.TCreepersMerShareholderColumn.CERT_NO.getValue(), certNo);
    		//详情
    		String memo = selectable.xpath(XPATH_TD_5).toString().trim();
    		ceepersMerShareholder.put(CreepersConstant.TCreepersMerShareholderColumn.MEMO.getValue(), memo);
    		ceepersMerShareholderList.add(ceepersMerShareholder);
		}
        logger.debug("============1-1-2==========>>>工商公示信息-股东信息爬取结束!");
	}

	/**
	 * @param page
	 */
	private void step1(Page page) {
		ResultItems resultItems = page.getResultItems();
		Selectable selectableStart = page.getHtml().xpath(XPATH_MER_BASIC_START);
        Map<String, String> ceepersMerBasic = resultItems
                .get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_BASIC.getMapKey());
        //注册号
        String merNo = selectableStart.xpath(XPATH_MER_BASIC_MER_NO+XPATH_MER_BASIC_END).toString().trim();
        ceepersMerBasic.put(CreepersConstant.TCreepersMerBasicColumn.MER_NO.getValue(), merNo);
        //名称
        String merName = selectableStart.xpath(XPATH_MER_BASIC_MER_NAME+XPATH_MER_BASIC_END).toString().trim();
        ceepersMerBasic.put(CreepersConstant.TCreepersMerBasicColumn.MER_NAME.getValue(), merName);
        String merType = selectableStart.xpath(XPATH_MER_BASIC_MER_TYPE+XPATH_MER_BASIC_END).toString().trim();//类型
        ceepersMerBasic.put(CreepersConstant.TCreepersMerBasicColumn.MER_TYPE.getValue(), merType);
    	String legalRep = selectableStart.xpath(XPATH_MER_BASIC_LEGAL_REP+XPATH_MER_BASIC_END).toString().trim();//法定代表人
    	ceepersMerBasic.put(CreepersConstant.TCreepersMerBasicColumn.LEGAL_REP.getValue(), legalRep);
    	String regCapital = selectableStart.xpath(XPATH_MER_BASIC_REG_CAPITAL+XPATH_MER_BASIC_END).toString().trim();//注册资本
    	ceepersMerBasic.put(CreepersConstant.TCreepersMerBasicColumn.REG_CAPITAL.getValue(), regCapital);
    	String foundDt = selectableStart.xpath(XPATH_MER_BASIC_FOUND_DT+XPATH_MER_BASIC_END).toString().trim();//成立日期
//    	Date foundDate = CommonMethodUtils.parseDate(foundDt, "yyyy年MM月dd日");
    	ceepersMerBasic.put(CreepersConstant.TCreepersMerBasicColumn.FOUND_DT.getValue(), foundDt);
    	String addr = selectableStart.xpath(XPATH_MER_BASIC_ADDR+XPATH_MER_BASIC_END).toString().trim();//住所
    	ceepersMerBasic.put(CreepersConstant.TCreepersMerBasicColumn.ADDR.getValue(), addr);
    	String oprStartDt = selectableStart.xpath(XPATH_MER_BASIC_OPR_START_DT+XPATH_MER_BASIC_END).toString().trim();//经营开始日期
    	ceepersMerBasic.put(CreepersConstant.TCreepersMerBasicColumn.OPR_START_DT.getValue(), oprStartDt);
    	String oprEndDt = selectableStart.xpath(XPATH_MER_BASIC_OPR_END_DT+XPATH_MER_BASIC_END).toString().trim();//经营结束日期
    	ceepersMerBasic.put(CreepersConstant.TCreepersMerBasicColumn.OPR_END_DT.getValue(), oprEndDt);
    	String scope = selectableStart.xpath(XPATH_MER_BASIC_SCOPE+XPATH_MER_BASIC_END).toString().trim();//经营范畴
    	ceepersMerBasic.put(CreepersConstant.TCreepersMerBasicColumn.SCOPE.getValue(), scope);
    	String regAuthority = selectableStart.xpath(XPATH_MER_REG_AUTHORITY+XPATH_MER_BASIC_END).toString().trim();//登记机关
    	ceepersMerBasic.put(CreepersConstant.TCreepersMerBasicColumn.REG_AUTHORITY.getValue(), regAuthority);
    	String approvalDate = selectableStart.xpath(XPATH_MER_BASIC_APPROVAL_DATE+XPATH_MER_BASIC_END).toString().trim();//核准日期
    	ceepersMerBasic.put(CreepersConstant.TCreepersMerBasicColumn.APPROVAL_DATE.getValue(), approvalDate);
    	String regStatus = selectableStart.xpath(XPATH_MER_BASIC_REG_STATUS+XPATH_MER_BASIC_END).toString().trim();//登记状态
    	ceepersMerBasic.put(CreepersConstant.TCreepersMerBasicColumn.REG_STATUS.getValue(), regStatus);
        logger.debug("============1-1-1==========>>>工商公示信息-登记信息爬取结束!");
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new BusinessInfoDetail31Processor())
//		.addPipeline(pipeline)
		//YueKBFLXXkyqqbOFPbO80iCdQ.KyjtDC 上海山美重型矿山机械股份有限公司
		//FFN9nvGXZhNPPsj0P63DQLhFH7AIwilg 复星国际
				.setDownloader(new SeleniumDownloader(
						System.getProperty("user.dir").replaceAll("\\\\", "/") + "/bin/chromedriver.exe"))
				.addUrl("https://www.sgs.gov.cn/notice/notice/view?uuid=YueKBFLXXkyqqbOFPbO80iCdQ.KyjtDC&tab=01")
				.thread(1).runAsync();
	}
}
