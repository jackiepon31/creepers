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
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dto.CreepersListTourGuideDTO;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;

@RestController
@RequestMapping(value = "/api")
public class CreepersTouristGuideRestController extends CreepersBaseRestController {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/touristGuide/query", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<JsonResult<Map>> queryByAccount(@RequestBody String inputJson, HttpServletRequest request,
            HttpServletResponse response) {
        JsonResult jsonResult = null;
        CreepersListTourGuideDTO param = JSONObject.parseObject(inputJson, CreepersListTourGuideDTO.class);
        if (StringUtils.isNotBlank(param.getGuideNo()) || StringUtils.isNotBlank(param.getCardNo())) {
            CreepersLoginParamDTO loginParam = new CreepersLoginParamDTO();
            loginParam.setTaskType(BaseConstant.TaskListType.TOUR_GUIDE_LIST.getValue());
            loginParam.putSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue(),
                    param.getGuideNo());
            loginParam.putSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue(),
                    param.getCardNo());
            loginParam.putSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue(),
                    param.getCertNo());
            jsonResult = creepersListServiceImpl.processByParam(loginParam);
            return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
        } else {
            jsonResult = new JsonResult<Map<String, Object>>(JSON_RESULT_TYPE.failure, "导游证号，导游卡号，至少需要录入一个！",
                    new HashMap<String, Object>());
            return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
        }
    }
}
