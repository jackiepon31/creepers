package com.fosun.fc.projects.creepers.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersConfigDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersConfig;
import com.fosun.fc.projects.creepers.redis.service.Impl.AbstractRedisCacheService;
import com.fosun.fc.projects.creepers.service.ICreepersConfigService;
import com.fosun.fc.projects.creepers.utils.TranslateMethodUtil;

/**
 * 
 * <p>
 * description:爬虫配置管理
 * </p>
 * 
 * @author MaXin
 * @since 2016-9-7 15:19:10
 * @see
 */
@Controller
@Transactional
@RequestMapping(value = "/configCenter")
public class ConfigCenterController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersConfigService creepersConfigServiceImpl;
    @SuppressWarnings("rawtypes")
    @Autowired
    private AbstractRedisCacheService redisConfigServiceImpl;

    @RequestMapping(value = "init", method = RequestMethod.GET)
    public String init(Model model) {
        logger.debug("----configCenter init page");
        model.addAttribute("taskTypeList", BaseConstant.getTaskListTypeList());
        return "configCenter/configCenterList";
    }

    @RequestMapping(value = "queryList", method = RequestMethod.GET)
    public String queryList(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, CreepersConfigDTO configDTO)
                    throws Exception {
        logger.debug("----configCenter queryList page");
        Map<String, Object> map = new HashMap<String, Object>();
        BeanMapper.copy(configDTO, map);
        Map<String, Object> searchParams = TranslateMethodUtil.pageMapToEqSearchMap(map);
        Page<CreepersConfigDTO> resultList = creepersConfigServiceImpl.queryForListByRequestType(searchParams,
                pageNumber, pageSize, sortType);
        model.addAllAttributes(BeanMapper.toMap(configDTO));
        model.addAttribute("resultList", resultList);
        model.addAttribute("taskTypeList", BaseConstant.getTaskListTypeList());
        model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap(map));
        return "configCenter/configCenterList";
    }

    @RequestMapping(value = "saveAndUpdate", method = RequestMethod.GET)
    public String saveAndUpdateInfo(Model model, CreepersConfigDTO configDTO) {
        logger.debug("----configCenter saveAndFlushInfo");
        TCreepersConfig entity = new TCreepersConfig();
        BeanMapper.copy(configDTO, entity);
        model.addAllAttributes(BeanMapper.toMap(configDTO));
        creepersConfigServiceImpl.updateEntity(entity);
        model.addAttribute("taskTypeList", BaseConstant.getTaskListTypeList());
        return "configCenter/configCenterDetail";
    }

    @RequestMapping(value = "doModify", method = RequestMethod.GET)
    public String doModify(Model model, CreepersConfigDTO configDTO) {
        logger.debug("----configCenter doModify");
        if (configDTO.getRequestType() != null && configDTO.getRequestType().length() > 0) {
            model.addAllAttributes(BeanMapper.toMap(configDTO));
        }
        model.addAttribute("taskTypeList", BaseConstant.getTaskListTypeList());
        return "configCenter/configCenterModify";
    }

    @RequestMapping(value = "showDetail", method = RequestMethod.GET)
    public String showDetail(Model model, String requestType) {
        logger.debug("----configCenter showDetail");
        TCreepersConfig entity = creepersConfigServiceImpl.queryEntity(requestType);
        model.addAllAttributes(BeanMapper.toMap(entity));
        model.addAttribute("taskTypeList", BaseConstant.getTaskListTypeList());
        return "configCenter/configCenterDetail";
    }

    @RequestMapping(value = "doRefresh", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> doRefresh() {
        logger.debug("----configCenter doRefresh");
        Map<String, String> map = new HashMap<String, String>();
        String result = "配置缓存刷新成功！";
        try {
            redisConfigServiceImpl.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getCause().getClass() + e.getCause().getMessage());
            result = "配置缓存刷新失败！";
        }
        map.put("result", result);
        return map;
    }
}
