package com.fosun.fc.projects.creepers.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fosun.fc.modules.web.Servlets;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.downloader.SimulateDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersListCredit;
import com.fosun.fc.projects.creepers.pageprocessor.CreditReferenceCenterProcessor;
import com.fosun.fc.projects.creepers.pipeline.CreditReferencePipline;
import com.fosun.fc.projects.creepers.service.ICreepersAccountBakService;
import com.fosun.fc.projects.creepers.service.ICreepersAssetHandleService;
import com.fosun.fc.projects.creepers.service.ICreepersBasicService;
import com.fosun.fc.projects.creepers.service.ICreepersCcDetailService;
import com.fosun.fc.projects.creepers.service.ICreepersCompensatoryService;
import com.fosun.fc.projects.creepers.service.ICreepersGeneralService;
import com.fosun.fc.projects.creepers.service.ICreepersGuaranteeService;
import com.fosun.fc.projects.creepers.service.ICreepersHlDetailService;
import com.fosun.fc.projects.creepers.service.ICreepersOlDetailService;
import com.fosun.fc.projects.creepers.service.ICreepersPublicCivilService;
import com.fosun.fc.projects.creepers.service.ICreepersPublicEnforcementService;
import com.fosun.fc.projects.creepers.service.ICreepersPublicIspService;
import com.fosun.fc.projects.creepers.service.ICreepersPublicSanctionService;
import com.fosun.fc.projects.creepers.service.ICreepersPublicTaxService;
import com.fosun.fc.projects.creepers.service.ICreepersQueryLogService;

import us.codecraft.webmagic.Spider;

@Controller
@RequestMapping(value = "/reference")
public class CreditReferenceController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersAccountBakService creepersAccountBakServiceImpl;
    @Autowired
    private ICreepersAssetHandleService creepersAssetHandleServiceImpl;
    @Autowired
    private ICreepersBasicService creepersBasicServiceImpl;
    @Autowired
    private ICreepersCcDetailService creepersCcDetailServiceImpl;
    @Autowired
    private ICreepersCompensatoryService creepersCompensatoryServiceImpl;
    @Autowired
    private ICreepersGeneralService creepersGeneralServiceImpl;
    @Autowired
    private ICreepersGuaranteeService creepersGuaranteeServiceImpl;
    @Autowired
    private ICreepersHlDetailService creepersHlDetailServiceImpl;
    @Autowired
    private ICreepersOlDetailService creepersOlDetailServiceImpl;
    @Autowired
    private ICreepersPublicCivilService creepersPublicCivilServiceImpl;
    @Autowired
    private ICreepersPublicEnforcementService creepersPublicEnforcementServiceImpl;
    @Autowired
    private ICreepersPublicIspService creepersPublicIspServiceImpl;
    @Autowired
    private ICreepersPublicSanctionService creepersPublicSanctionServiceImpl;
    @Autowired
    private ICreepersPublicTaxService creepersPublicTaxServiceImpl;
    @Autowired
    private ICreepersQueryLogService creepersQueryLogServiceImpl;

    @RequestMapping(value = "init", method = RequestMethod.GET)
    public String init(Model model, RedirectAttributes redirectAttributes) {
        logger.debug("----creditReference init page");
        // model.addAttribute("action", "init");
        return "creditReference/creditReferenceList";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, ServletRequest request)
                    throws Exception {
        logger.debug("----creditReference query result");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        List<TCreepersListCredit> result = (List<TCreepersListCredit>) creepersListServiceImpl.findList(searchParams,
                pageNumber, pageSize, sortType, BaseConstant.TaskListType.CREDIT_REFERENCE_LIST.getValue());
        // Map<String, Object> searchParams =
        // Servlets.getParametersStartingWith(request, "");
        // Page<CreepersAccountDTO> result =
        // creepersAccountService.getCreepersAccountList(searchParams,
        // pageNumber, pageSize, sortType);
        model.addAttribute("resultLists", result);
        return "creditReference/creditReferenceList";
    }

    @RequestMapping(value = "queryInfo", method = RequestMethod.GET)
    public String queryInfo(Model model, String rptNo) {
        logger.debug("----creditReference query result");
        Map<String, Object> result = new HashMap<String, Object>();
        result.putAll(creepersAccountBakServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersAssetHandleServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersBasicServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersCcDetailServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersCompensatoryServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersGeneralServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersGuaranteeServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersHlDetailServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersOlDetailServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersPublicCivilServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersPublicEnforcementServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersPublicIspServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersPublicSanctionServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersPublicTaxServiceImpl.findByRptNoForMap(rptNo));
        result.putAll(creepersQueryLogServiceImpl.findByRptNoForMap(rptNo));
        model.addAttribute("resultMap", result);
        return "creditReference/creditReferenceDetail";
    }

    @RequestMapping(value = "doRecycle")
    public Map<String, String> doRecycle(String userCode) {
        logger.info("========>CreditReferenceController-->doRecycle<========");
        logger.info("========>重爬参数userCode：" + userCode + "<========");
        CreepersLoginParamDTO param = new CreepersLoginParamDTO();
        param.setTaskType(BaseConstant.TaskListType.CREDIT_REFERENCE_LIST.getValue());
        param.setLoginName(userCode);
        creepersListServiceImpl.doRecycleByParam(param);
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("message", "success");
        return resultMap;
    }

    @RequestMapping(value = "queryLog", method = RequestMethod.GET)
    public String queryLog(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = "4") int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model, String userCode) {
        logger.debug("----creditReference init page");
        // model.addAttribute("action", "init");
        return "creditReference/creditReferenceDetail";
    }

    //这个应该放到新建爬取任务模块中
    @RequestMapping(value = "startCreeper", method = RequestMethod.GET)
    public String startCreeper(Model model, RedirectAttributes redirectAttributes) {
        logger.debug("----startCreeper");
        Spider.create(new CreditReferenceCenterProcessor()).addPipeline(new CreditReferencePipline("/webmagic/"))
                .addUrl("https://ipcrs.pbccrc.org.cn/").setDownloader(new SimulateDownloader()).thread(2).runAsync();
        logger.debug("----end Creeper");
        return "creditReference/creditReferenceDetail";
    }
}
