//package com.fosun.fc.projects.creepers.service.impl;
//
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.mail.internet.MimeMessage;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
//
//import com.fosun.fc.projects.creepers.service.IMimeMailService;
//import com.fosun.fc.projects.creepers.utils.PropertiesUtil;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
//
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//
///**
// * @author pengyk
// * @since 2016-10-14
// * MIME邮件服务类.
// * 
// * 演示由Freemarker引擎生成的的html格式邮件, 并带有附件.
// * 
// * 
// */
//@SuppressWarnings("restriction")
//@Component
//@Transactional
//public class MimeMailServiceImpl implements IMimeMailService {
//
//    private static final String DEFAULT_ENCODING = "utf-8";
//
//    private static Logger logger = LoggerFactory.getLogger(IMimeMailService.class);
//
//    private JavaMailSender mailSender;
//
//    private Template template;
//
//    /**
//     * 发送MIME格式的用户修改通知邮件.
//     */
//    @Override
//    public void sendNotificationMailToCF(String email, String excelName) {
//        try {
//            MimeMessage msg = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(msg, true, DEFAULT_ENCODING);
//            helper.setTo(email);
//            helper.setFrom(PropertiesUtil.getApplicationValue("SEND_MAIL"));
//            helper.setSubject("爬虫系统通知");
//            String content = "爬虫数据信息数据信息。";
//            helper.setText(content, true);
//            File attachment = new File(PropertiesUtil.getApplicationValue("EXCEL_PATH") + excelName);
//            helper.addAttachment(excelName, attachment);
//            mailSender.send(msg);
//
//        } catch (Exception e) {
//            logger.error("发送邮件失败", e);
//        }
//    }
//
//    /**
//     * 发送MIME格式的用户修改通知邮件.
//     */
//    @Override
//    public void sendNotificationMail(String mobil, String email) {
//        try {
//            MimeMessage msg = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(msg, true, DEFAULT_ENCODING);
//
//            helper.setTo(email);
//            helper.setFrom(PropertiesUtil.getApplicationValue("SEND_MAIL"));
//            helper.setSubject("密码重置通知");
//
//            String content = generateContent(mobil, email);
//            helper.setText(content, true);
//            /*
//             * File attachment = generateAttachment();
//             * helper.addAttachment("attachment.txt", attachment);
//             */
//
//            mailSender.send(msg);
//            logger.info("邮件发送成功");
//
//        } catch (MessagingException e) {
//            logger.error("构造邮件失败", e);
//        } catch (Exception e) {
//            logger.error("发送邮件失败", e);
//        }
//    }
//
//    /**
//     * 使用Freemarker生成html格式内容.
//     */
//    @SuppressWarnings({ "unused", "rawtypes" })
//    private String generateContent(String mobil, String email) throws MessagingException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
//        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy年MM月dd日");
//        try {
//            Map context = new HashMap();
//            return FreeMarkerTemplateUtils.processTemplateIntoString(template, context);
//        } catch (IOException e) {
//            logger.error("生成邮件内容失败, FreeMarker模板不存在", e);
//            throw new MessagingException("FreeMarker模板不存在", e);
//        } catch (TemplateException e) {
//            logger.error("生成邮件内容失败, FreeMarker处理失败", e);
//            throw new MessagingException("FreeMarker处理失败", e);
//        }
//    }
//
//    /**
//     * 获取classpath中的附件.
//     */
//    @SuppressWarnings({ "unused" })
//    private File generateAttachment() throws MessagingException {
//        try {
//            Resource resource = new ClassPathResource("/email/mailAttachment.txt");
//            return resource.getFile();
//        } catch (IOException e) {
//            logger.error("构造邮件失败,附件文件不存在", e);
//            throw new MessagingException("附件文件不存在", e);
//        }
//    }
//
//    /**
//     * Spring的MailSender.
//     */
//    public void setMailSender(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    /**
//     * 注入Freemarker引擎配置,构造Freemarker 邮件内容模板.
//     */
//    public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) throws IOException {
//        // 根据freemarkerConfiguration的templateLoaderPath载入文件.
//        template = freemarkerConfiguration.getTemplate("mailTemplate.ftl", DEFAULT_ENCODING);
//    }
//}
