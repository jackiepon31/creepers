package com.fosun.fc.projects.creepers.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fosun.fc.projects.creepers.dto.CreepersTaxEvasionDTO;
import com.fosun.fc.projects.creepers.dto.CreepersTaxEvasionDetailDTO;
import com.fosun.fc.projects.creepers.service.ICreepersTaxEvasionDetailService;
import com.fosun.fc.projects.creepers.service.ICreepersTaxEvasionService;
import com.fosun.fc.projects.creepers.utils.TranslateMethodUtil;

@Controller
@RequestMapping(value = "/taxEvasion")
public class TaxEvasionController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ICreepersTaxEvasionService creepersTaxEvasionServiceImpl;
    @Autowired
    private ICreepersTaxEvasionDetailService creepersTaxEvasionDetailServiceImpl;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init() {
        logger.info("========>TaxEvasionController-->init<========");
        return "taxEvasion/taxEvasionList";
    }

    @RequestMapping(value = "/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model, String name)
                    throws Exception {
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("EQ_name", name);
        logger.info("========>TaxEvasionController-->list<========");
        logger.info("========>查询参数：" + searchParams + "<========");
        Page<CreepersTaxEvasionDTO> resultList = (Page<CreepersTaxEvasionDTO>) creepersTaxEvasionServiceImpl
                .findList(searchParams, pageNumber, pageSize, sortType);
        model.addAttribute("resultList", resultList);
        model.addAttribute("name", name);
        model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap(searchParams));
        return "taxEvasion/taxEvasionList";
    }

    @RequestMapping(value = "/queryInfo")
    public String queryInfo(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model, String name) {
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("EQ_name", name);
        logger.info("========>TaxEvasionController-->queryInfo<========");
        logger.info("========>查询参数：" + searchParams + "<========");
        Page<CreepersTaxEvasionDetailDTO> resultList = creepersTaxEvasionDetailServiceImpl.findList(searchParams, pageNumber, pageSize,
                sortType);
        model.addAttribute("resultList", resultList);
        model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap("name", name));
        return "taxEvasion/taxEvasionDetail";
    }
}
