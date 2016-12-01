package com.fosun.fc.projects.creepers.spider;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersListFundDao;
import com.fosun.fc.projects.creepers.downloader.SimulateLoginDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersListFund;
import com.fosun.fc.projects.creepers.pageprocessor.FundProcessor;
import com.fosun.fc.projects.creepers.pipeline.FundPipline;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.fosun.fc.projects.creepers.utils.code.BaseCode;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

public class SpiderFundStartTest extends SpiderBaseTest {

    @Autowired(required = true)
    private FundProcessor fundProcessor;

    @Autowired(required = true)
    private FundPipline fundPipline;

    @Autowired(required = true)
    private CreepersListFundDao creepersListFundDao;

    @Autowired(required = true)
    private SimulateLoginDownloader simulateLoginDownloader;

    public void methodTest() throws Exception {
        // 查询任务List，结果为空或状态不为1则启动爬寻
        String userCode = "lizhanping";
        String password = "123456";
        CreepersLoginParamDTO param = new CreepersLoginParamDTO();
        param.setLoginName(userCode);
        param.setPassword(password);
        param.setTaskType(BaseConstant.TaskListType.FUND_LIST.getValue());
        List<TCreepersListFund> taskList = creepersListFundDao.queryListByUserCode(userCode);
        // Boolean creepFlag = false;
        if (CommonMethodUtils.isEmpty(taskList)) {
            TCreepersListFund task = new TCreepersListFund();
            task.setUserCode(userCode);
            task.setPassword(password);
            task.setFlag(BaseConstant.TaskListStatus.DEFAULT.getValue());
            CommonMethodUtils.setByDT(task);
            creepersListFundDao.save(task);
            // creepFlag = true;
        } else {
            // TCreepersListFund task = (TCreepersListFund) taskList.get(0);
            // String flag = task.getFlag();
            // if (!BaseConstant.TaskListStatus.DEFAULT.getValue().equals(flag))
            // {
            // creepFlag = true;
            // }
        }
        // if (creepFlag) {
        // 初始化Param DTO
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, "https://persons.shgjj.com/");
        param.putOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL, "https://persons.shgjj.com/VerifyImageServlet");
        param.putOrderUrl(BaseConstant.OrderUrlKey.DO_LOGIN_URL, "https://persons.shgjj.com/SsoLogin");
        param.setCaptchaKey("userjym");
        param.setLoginNameKey("userid");
        param.setPasswordKey("userpw");
        List<String> targetUrlList = new ArrayList<String>();
        targetUrlList.add("https://persons.shgjj.com/MainServlet?ID=1");
        targetUrlList.add("https://persons.shgjj.com/MainServlet?ID=11");
        targetUrlList.add("https://persons.shgjj.com/MainServlet?ID=3");
        targetUrlList.add("https://persons.shgjj.com/MainServlet?ID=31");
        param.setTargetUrlList(targetUrlList);
        param.putNameValuePair("SUBMIT.x", (int) (Math.random() * 70) + 2 + "");
        param.putNameValuePair("SUBMIT.y", (int) (Math.random() * 20) + 2 + "");
        param.putNameValuePair("password_md5", BaseCode.encryptMD5To16(param.getPassword()));
        Request request = CommonMethodUtils.buildIndexRequest(param);
        // 启动爬虫
        logger.info("=============>启动爬虫!");
        Spider.create(fundProcessor).addPipeline(fundPipline).setDownloader(simulateLoginDownloader.setParam(param))
                .thread(10).addRequest(request).run();
        // }
    }

    @Test
    public void run() throws Exception {
        methodTest();
    }

}
