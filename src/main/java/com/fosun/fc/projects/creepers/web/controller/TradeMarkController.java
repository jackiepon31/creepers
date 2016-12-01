package com.fosun.fc.projects.creepers.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fosun.fc.modules.web.Servlets;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersErrorListDTO;
import com.fosun.fc.projects.creepers.dto.CreepersListTradeMarkDTO;
import com.fosun.fc.projects.creepers.dto.CreepersTradeMarkDTO;
import com.fosun.fc.projects.creepers.service.ICreepersListTradeMarkService;
import com.fosun.fc.projects.creepers.service.ICreepersTradeMarkService;
import com.fosun.fc.projects.creepers.utils.TranslateMethodUtil;

@Controller
@RequestMapping(value = "/tradeMark")
public class TradeMarkController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ICreepersTradeMarkService tradeMarkService;
    @Autowired
    private ICreepersListTradeMarkService creepersListTradeMarkServiceImpl;

    @RequestMapping(value = "/init")
    public String init() {
        logger.info("========>tradeMarkController-->init<========");
        return "tradeMark/tradeMarkList";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
            ServletRequest request) throws Exception {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        logger.info("========>tradeMarkController-->list<========");
        logger.info("========>查询参数：" + searchParams + "<========");
        Page<CreepersListTradeMarkDTO> resultList = creepersListTradeMarkServiceImpl.queryListTradeMarkList(searchParams,
                pageNumber, pageSize, sortType);
        model.addAttribute("resultList", resultList);
        model.addAttribute("sortType", sortType);
        model.addAttribute("param", TranslateMethodUtil.buildPageSearchParam(searchParams, "search_"));
        model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap(searchParams, "search_"));
        return "tradeMark/tradeMarkList";
    }

    @RequestMapping(value = "/queryInfo")
    public String queryInfo(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model, String merName)
                    throws Exception {
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("EQ_merName", merName);
        logger.info("========>tradeMarkController-->queryInfo<========");
        logger.info("========>查询参数：" + searchParams + "<========");
        Page<CreepersTradeMarkDTO> resultList = tradeMarkService.queryTradeMarkList(searchParams, pageNumber, pageSize,
                sortType);
        model.addAttribute("resultList", resultList);
        model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap("merName", merName));
        return "tradeMark/tradeMarkDetail";
    }

    @ResponseBody
    @RequestMapping(value = "/doRecycle")
    public Map<String, String> doRecycle(String merName) {
        logger.info("========>tradeMarkController-->doRecycle<========");
        logger.info("========>查询参数：merName=" + merName + "<========");
        Map<String, String> resultMap = new HashMap<String, String>();
        creepersListServiceImpl.doRecycleByMerName(BaseConstant.TaskListType.TRADE_MARK_LIST.getValue(), merName);
        resultMap.put("message", "success");
        return resultMap;
    }

    @RequestMapping(value = "/queryLog")
    public String queryLog(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model, String merName) {
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("EQ_merName", merName);
        searchParams.put("EQ_taskType", BaseConstant.TaskListType.TRADE_MARK_LIST.getValue());
        logger.info("========>tradeMarkController-->queryLog<========");
        logger.info("========>查询参数：" + searchParams + "<========");
        Page<CreepersErrorListDTO> resultList = creepersErrorListServiceImpl.queryErrorList(searchParams, pageNumber, pageSize,
                sortType);
        model.addAttribute("resultList", resultList);
        model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap("merName", merName));
        return "creeperTask/errorLog";
    }
}
