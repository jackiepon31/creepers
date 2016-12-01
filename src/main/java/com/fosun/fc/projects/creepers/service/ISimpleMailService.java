package com.fosun.fc.projects.creepers.service;

public interface ISimpleMailService {

    void sendNotificationMail(String userName);

    void sendNotificationMail(String sourceMail, String[] targetMail, String[] ccMail, String content);

}
