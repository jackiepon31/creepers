package com.fosun.fc.projects.creepers.service;

public interface IMimeMailService {

    void sendNotificationMail(String mobil, String email);

    void sendNotificationMailToCF(String email, String excelName);

}
