package com.fosun.fc.projects.creepers.web.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fosun.fc.modules.utils.JsonResult;
import com.fosun.fc.modules.utils.JsonResult.JSON_RESULT_TYPE;
import com.fosun.fc.modules.web.MediaTypes;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersTourBlackListDTO;

@RestController
@RequestMapping(value = "/api")
public class CreepersTouristBlackListRestController extends CreepersBaseRestController {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/touristBlackList/query", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<JsonResult<Map>> queryByGuideNo(@RequestBody String inputJson, HttpServletRequest request,
            HttpServletResponse response) {
        // 用执业证号唯一索引查询都用这个接口
        JsonResult jsonResult = null;
        CreepersTourBlackListDTO param = JSONObject.parseObject(inputJson, CreepersTourBlackListDTO.class);
        if (StringUtils.isNotBlank(param.getGuideNo())) {
            jsonResult = creepersListServiceImpl.processByMerName(BaseConstant.TaskListType.TOUR_BLACK_LIST.getValue(),
                    param.getGuideNo());
            return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
        } else {
            jsonResult = new JsonResult<Map<String, Object>>(JSON_RESULT_TYPE.failure, "执业证号，不能为空！",
                    new HashMap<String, Object>());
            return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/touristAgencyBlackList/query", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<JsonResult<Map>> queryByAgentName(@RequestBody String inputJson, HttpServletRequest request,
            HttpServletResponse response) {
        // 对于旅行社信息需要用单位名称查询，走这里分支，后面查询中加上限定条件类型。
        JsonResult jsonResult = null;
        CreepersTourBlackListDTO param = JSONObject.parseObject(inputJson, CreepersTourBlackListDTO.class);
        if (StringUtils.isNotBlank(param.getAgentName())) {
            jsonResult = creepersListServiceImpl.processByMerName(BaseConstant.TaskListType.TOUR_AGENCY_BLACK_LIST.getValue(),
                    param.getAgentName());
            return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
        } else {
            jsonResult = new JsonResult<Map<String, Object>>(JSON_RESULT_TYPE.failure, "旅行社单位名称，不能为空！",
                    new HashMap<String, Object>());
            return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
        }
    }
}
