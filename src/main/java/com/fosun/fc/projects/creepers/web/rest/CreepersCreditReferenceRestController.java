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
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;

@RestController
@RequestMapping(value = "/api")
public class CreepersCreditReferenceRestController extends CreepersBaseRestController {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/creditReference/query", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<JsonResult<Map>> queryByAccount(@RequestBody String inputJson, HttpServletRequest request,
            HttpServletResponse response) {
        JsonResult jsonResult = null;
        CreepersLoginParamDTO param = JSONObject.parseObject(inputJson, CreepersLoginParamDTO.class);
        if (StringUtils.isNotBlank(param.getLoginName())
                && StringUtils.isNotBlank(param.getPassword())
                && StringUtils.isNotBlank(param.getMessageCaptchaValue())) {
            param.setTaskType(BaseConstant.TaskListType.CREDIT_REFERENCE_LIST.getValue());
            jsonResult = creepersListServiceImpl.processByParam(param);
            return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
        } else {
            jsonResult = new JsonResult<Map<String, Object>>(JSON_RESULT_TYPE.failure, "账号，密码，验证码均不能为空！",
                    new HashMap<String, Object>());
            return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
        }
    }
}
