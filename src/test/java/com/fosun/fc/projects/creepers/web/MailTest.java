package com.fosun.fc.projects.creepers.web;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.fosun.fc.modules.test.category.UnStable;
import com.fosun.fc.modules.test.spring.SpringTransactionalTestCase;
import com.fosun.fc.projects.creepers.service.ISimpleMailService;

@Category(UnStable.class)
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml","/email/applicationContext-email.xml"})
@TransactionConfiguration(defaultRollback = false)
public class MailTest extends SpringTransactionalTestCase {


    @Autowired(required = true)
    private ISimpleMailService simpleMailServiceImpl;

    @Test	
    public void testSend() throws SchedulerException {
//        simpleMailServiceImpl.sendNotificationMail("test");
    	String[] targetMails = new String[] { "lizhanping@fosun.com" };
        String[] ccMails = new String[] { "pengyk@fosun.com", "maxin@fosun.com" };
        simpleMailServiceImpl.sendNotificationMail("zx.service@fosun.com", targetMails, ccMails, 
                "错误路径：" + 1 + "\n" + "错误信息：" + 2 + "\n" + "请求信息："
                        + 3);
    }
    @Test
    public void testRemove() {/*
    	UserDTO dto = new UserDTO();
    	dto.setUserName("samuel");
    	dto.setBirthday(new Date());
    	dto.setEmail("jackiepon@163.com");
    	dto.setSex("M");
    	dto.setCreatedBy("admin");
    	dto.setCreatedDt(new Date());
    	dto.setUpdatedBy("admin");
    	dto.setUpdatedDt(new Date());
    	redisCacheTemplate.opsForSet().remove("samuel", dto);
    	UserDTO dto2 = redisCacheTemplate.opsForSet().pop(dto.getUserName());
    	System.out.println("=========1========="+dto2.getEmail());  		
    */}

}
