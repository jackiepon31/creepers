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
import com.fosun.fc.projects.creepers.dto.CreepersMedicalDTO;

@RestController
@RequestMapping(value = "/api")
public class CreepersMedicalRestController extends CreepersBaseRestController {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/medicalInstrumentDomestic/query", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<JsonResult<Map>> queryMedicalInstrumentDomesticBykey(@RequestBody String inputJson, HttpServletRequest request,
            HttpServletResponse response) {
        JsonResult jsonResult = null;
        CreepersMedicalDTO param = JSONObject.parseObject(inputJson, CreepersMedicalDTO.class);
        if (StringUtils.isNotBlank(param.getKey())) {
            jsonResult = creepersListServiceImpl.processByMerName(BaseConstant.TaskListType.MEDICAL_INSTRUMENT_DOMESTIC_LIST.getValue(), param.getKey());
            return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
        } else {
            jsonResult = new JsonResult<Map<String, Object>>(JSON_RESULT_TYPE.failure, "key(唯一标识号)，不能为空！",
                    new HashMap<String, Object>());
            return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/medicalInstrumentForeign/query", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<JsonResult<Map>> queryMedicalInstrumentForeignBykey(@RequestBody String inputJson, HttpServletRequest request,
            HttpServletResponse response) {
        JsonResult jsonResult = null;
        CreepersMedicalDTO param = JSONObject.parseObject(inputJson, CreepersMedicalDTO.class);
        if (StringUtils.isNotBlank(param.getKey())) {
            jsonResult = creepersListServiceImpl.processByMerName(BaseConstant.TaskListType.MEDICAL_INSTRUMENT_FOREIGN_LIST.getValue(), param.getKey());
            return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
        } else {
            jsonResult = new JsonResult<Map<String, Object>>(JSON_RESULT_TYPE.failure, "key(唯一标识号)，不能为空！",
                    new HashMap<String, Object>());
            return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/medicalGSP/query", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<JsonResult<Map>> queryMedicalGSPBykey(@RequestBody String inputJson, HttpServletRequest request,
            HttpServletResponse response) {
        JsonResult jsonResult = null;
        CreepersMedicalDTO param = JSONObject.parseObject(inputJson, CreepersMedicalDTO.class);
        if (StringUtils.isNotBlank(param.getKey())) {
            jsonResult = creepersListServiceImpl.processByMerName(BaseConstant.TaskListType.MEDICAL_GSP_LIST.getValue(), param.getKey());
            return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
        } else {
            jsonResult = new JsonResult<Map<String, Object>>(JSON_RESULT_TYPE.failure, "key(唯一标识号)，不能为空！",
                    new HashMap<String, Object>());
            return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/medicalGMP/query", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<JsonResult<Map>> queryMedicalGMPBykey(@RequestBody String inputJson, HttpServletRequest request,
            HttpServletResponse response) {
        JsonResult jsonResult = null;
        CreepersMedicalDTO param = JSONObject.parseObject(inputJson, CreepersMedicalDTO.class);
        if (StringUtils.isNotBlank(param.getKey())) {
            jsonResult = creepersListServiceImpl.processByMerName(BaseConstant.TaskListType.MEDICAL_GMP_LIST.getValue(), param.getKey());
            return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
        } else {
            jsonResult = new JsonResult<Map<String, Object>>(JSON_RESULT_TYPE.failure, "key(唯一标识号)，不能为空！",
                    new HashMap<String, Object>());
            return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
        }
    }
}
