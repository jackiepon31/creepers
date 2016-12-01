package com.fosun.fc.projects.creepers.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.service.ISimpleMailService;

/**
 * 纯文本邮件服务类.
 * 
 * @author pengyk
 * @since 2016-10-14
 */
@Component("simpleMailServiceImpl")
@Transactional
public class SimpleMailServiceImpl implements ISimpleMailService {
    private static Logger logger = LoggerFactory.getLogger(SimpleMailServiceImpl.class);

    private JavaMailSender mailSender;
    private String textTemplate;
    @Value("${mail.switch}")
    private boolean emailSwitch;

    /**
     * 发送纯文本的用户修改通知邮件.
     */
    @Override
    public void sendNotificationMail(String userName) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("zx.service@fosun.com");
        String[] array = new String[] { "lizhanping@fosun.com", "maxin@fosun.com" };
        msg.setTo(array);
        msg.setSubject(BaseConstant.MAIL_SUBJECT);

        // 将用户名与当期日期格式化到邮件内容的字符串模板
        String content = "占平太Low了！！！！";
        content = content + String.format(textTemplate, new Date());
        msg.setText(content);
        msg.setCc("pengyk@fosun.com");
        try {
            mailSender.send(msg);
            if (logger.isInfoEnabled()) {
                logger.info("纯文本邮件已发送至{}", StringUtils.join(msg.getTo(), ","));
            }
        } catch (Exception e) {
            logger.error("发送邮件失败", e);
        }
    }

    /**
     * Spring的MailSender.
     */
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * 邮件内容的字符串模板.
     */
    public void setTextTemplate(String textTemplate) {
        this.textTemplate = textTemplate;
    }

    @Override
    public void sendNotificationMail(String sourceMail, String[] targetMail, String[] ccMail, String content) {
        if(emailSwitch){
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(sourceMail);
            msg.setTo(targetMail);
            msg.setSubject(BaseConstant.MAIL_SUBJECT);
            
            // 将用户名与当期日期格式化到邮件内容的字符串模板
            String mailContent = content + String.format(textTemplate, new Date());
            msg.setText(mailContent);
            msg.setCc(ccMail);
            try {
                mailSender.send(msg);
                if (logger.isInfoEnabled()) {
                    logger.info("纯文本邮件已发送至{}", StringUtils.join(msg.getTo(), ","));
                }
            } catch (Exception e) {
                logger.error("发送邮件失败", e);
            }
        }
    }

    public void setEmailSwitch(boolean emailSwitch) {
        this.emailSwitch = emailSwitch;
    }
}
