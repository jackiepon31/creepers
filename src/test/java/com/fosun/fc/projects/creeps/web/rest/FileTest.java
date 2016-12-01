
package com.fosun.fc.projects.creeps.web.rest;

import java.io.File;
import java.util.Map;

import org.junit.experimental.categories.Category;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fosun.fc.modules.test.category.UnStable;
import com.fosun.fc.modules.test.spring.SpringTransactionalTestCase;
import com.fosun.fc.modules.utils.JsonResult;
import com.fosun.fc.projects.creepers.utils.PropertiesUtil;

@Category(UnStable.class)
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class FileTest extends SpringTransactionalTestCase {

//	@Test  
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public void testUpload() throws Exception {  
	    String url =  PropertiesUtil.getApplicationValue("sk_upload_url");;  
	    String filePath = "D:/image/207293f9-2d59-405a-a92d-b894abd195bb.jpg";  
	    
	    RestTemplate rest = new RestTemplate();  
	    FileSystemResource resource = new FileSystemResource(new File(filePath));  
	    MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
	    param.add("requestCode", PropertiesUtil.getApplicationValue("sk_requestcode"));
        param.add("invokerName", PropertiesUtil.getApplicationValue("sk_invokername"));
	    param.add("file", resource);  
	    param.add("bizGroup", "CREEPERS_IMAGE");
	    param.add("bizSub", resource.getFilename());
	    param.add("sysName" , "projects-creepers");
	    HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String,Object>>(param);
	    ResponseEntity<JsonResult> responseEntity = rest.exchange(url, HttpMethod.POST, httpEntity, JsonResult.class);  
	    JsonResult jsonResult = responseEntity.getBody();
	    if (JsonResult.JSON_RESULT_TYPE.success.name().equals(jsonResult.getType())) {
	    	Map<String, String> dataMap =  (Map<String, String>) jsonResult.getData();
		    System.out.println(getRealName(dataMap.get("encodeName"))); 
		    File file = new File(filePath);
	        if (file.exists()) {
	            file.delete();
	        }
		}
	   
	}  
	
//	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public String getRealName(String encodeName) {
//		String encodeName = "MjAxNjEwMTAwNzM0NTRfXzNjYmQyMThiLWRlNzMtNDZkNS05YzMzLTZlYTI5NGY5MmU1Ni5qcGc=";
		String downloadApi = PropertiesUtil.getApplicationValue("sk_download_url");
		RestTemplate rest = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>(); 
        params.add("requestCode", PropertiesUtil.getApplicationValue("sk_requestcode"));
        params.add("invokerName", PropertiesUtil.getApplicationValue("sk_invokername"));
        params.add("bizGroup", "CREEPERS_IMAGE");
        params.add("bizSub", encodeName);
        params.add("encodeName", encodeName);
        params.add("sysName", "projects-creepers"); 
        String url = UriComponentsBuilder  
                .fromHttpUrl(downloadApi)  
                .queryParams(params).build().toUriString(); 
        JsonResult jsonResult= rest.getForObject(url, JsonResult.class);
        String realPath = "";
        if (JsonResult.JSON_RESULT_TYPE.success.name().equals(jsonResult.getType())) {
	    	Map<String, String> dataMap =  (Map<String, String>) jsonResult.getData();
	    	realPath = dataMap.get("realPath");
		}
        return realPath;
	}
}
