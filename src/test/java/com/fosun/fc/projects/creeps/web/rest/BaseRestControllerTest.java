package com.fosun.fc.projects.creeps.web.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fosun.fc.modules.test.category.UnStable;
import com.fosun.fc.modules.test.spring.SpringContextTestCase;
import com.fosun.fc.modules.utils.JsonResult;

@Category(UnStable.class)
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class BaseRestControllerTest extends SpringContextTestCase {

    @Autowired
    private RestTemplate restTemplate;

   
    @SuppressWarnings({ "unchecked", "rawtypes" })
     @Test
    //导游证验真测试
    public void testTourGuideInfo() {
        String guideNo = "D-4308-004238";// D-4502-009024
        Map<String, String> params = new HashMap<String, String>();
        params.put("guideNo", guideNo);
        params.put("requestCode", "creepers_tour_guide");  // 调用的接口
        params.put("bizUk", guideNo); // 订单号
        params.put("invokerName", "creepers");   // 业务名称
        params.put("systemName", "project-creepers");   // 系统名称
        String jsonString = JSON.toJSONString(params);
        // 导游证验真
//      String baseUrl = "http://localhost:8080/creepers/api/touristGuide/query";
//      String baseUrl = "http://10.166.10.214:10086/creepers/api/touristGuide/query";   
//      String baseUrl = "http://10.166.0.246:10086/creepers/api/touristGuide/query";        
      String baseUrl = "http://creepers.mis.liangfuzhengxin.com/creepers/api/touristGuide/query";     
//        String baseUrl = "http://10.166.10.216:10080/diplomat/api/services";
        JsonResult<Map> jsonResult = restTemplate.postForObject(baseUrl, JSON.parse(jsonString), JsonResult.class);
        System.err.println("\njsonResult:====================>>>>" + jsonResult);
        Map msg = jsonResult.getData();
        System.err.println("\njsonResult.getData():====================>>>>" + msg);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
//     @Test
    //导游黑名单测试
    public void testTouristGuideBlackList() {
        String type = "2", guideNo = "D-4301-505009" ;
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", type);
        params.put("guideNo", guideNo);
        params.put("bizUk", guideNo); 
        params.put("requestCode", "creepers_tour_blacklist");  // 调用的接口
        params.put("invokerName", "creepers");   // 业务名称
        params.put("systemName", "project-creepers");   // 系统名称
        String jsonString = JSON.toJSONString(params);
        // 导游黑名单
//      String baseUrl = "http://localhost:8080/creepers/api/touristBlackList/query";
//      String baseUrl = "http://10.166.10.214:10086/creepers/api/touristBlackList/query";   
      String baseUrl = "http://creepers.mis.liangfuzhengxin.com/creepers/api/touristBlackList/query";        
//        String baseUrl = "http://10.166.10.216:10080/diplomat/api/services";
        JsonResult<Map> jsonResult = restTemplate.postForObject(baseUrl, JSON.parse(jsonString), JsonResult.class);
        System.err.println("\njsonResult:====================>>>>" + jsonResult);
        Map msg = jsonResult.getData();
        System.err.println("\njsonResult.getData():====================>>>>" + msg);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
//    @Test
    //旅行社黑名单测试
    public void testTouristAgentcyBlackList() {
        String type = "1", guideNo = "L-XZ00036",agentName = "海南航旅旅行社有限公司";
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", type);
        params.put("guideNo", guideNo);
        params.put("agentName", agentName);
        params.put("bizUk", guideNo); 
        params.put("requestCode", "creepers_tourAgency_blacklist");  // 调用的接口
        params.put("invokerName", "creepers");   // 业务名称
        params.put("systemName", "project-creepers");   // 系统名称
        String jsonString = JSON.toJSONString(params);
        // 旅行社导游黑名单
//      String baseUrl = "http://localhost:8080/creepers/api/touristAgencyBlackList/query";
//      String baseUrl = "http://10.166.10.214:10086/creepers/api/touristAgencyBlackList/query";   
        String baseUrl = "http://10.166.0.246:10086/creepers/api/touristAgencyBlackList/query";        
//      String baseUrl = "http://creepers.mis.liangfuzhengxin.com/creepers/api/touristAgencyBlackList/query";
        // 导游证查询
//         String baseUrl = "http://10.166.10.216:10080/diplomat/api/services";
        JsonResult<Map> jsonResult = restTemplate.postForObject(baseUrl, JSON.parse(jsonString), JsonResult.class);
        System.err.println("\njsonResult:====================>>>>" + jsonResult);
        Map msg = jsonResult.getData();
        System.err.println("\njsonResult.getData():====================>>>>" + msg);
    }


    // @Test
    public void testCreepers() {
        String baseUrl = "http://10.166.10.214:10084/diplomat/api/service";
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<String, String>();
        requestMap.add("bizUk", "深圳市裕同包装科技股份有限公司");
        requestMap.add("requestCode", "creepers_patent");
        requestMap.add("invokerName", "test");
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParams(requestMap).build().toUriString();
        JsonResult<?> jsonResult = restTemplate.getForObject(url, JsonResult.class, new Object[0]);
        logger.debug("-------------------jsonResult-------------------:" + jsonResult.toString());
    }

    @SuppressWarnings("unused")
    private String objectToString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, obj);
        return writer.toString();
    }

    @SuppressWarnings("unused")
    private String jsonRead(String fullFileName) {
        File file = new File(fullFileName);
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return buffer.toString();
    }

    @SuppressWarnings("unused")
    private String ReadFile(String Path) {
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }
}
