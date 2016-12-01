package com.fosun.fc.projects.creepers.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the T_CREEPERS_TOUR_GUIDE database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_TOUR_GUIDE")
@NamedQuery(name = "TCreepersTourGuide.findAll", query = "SELECT t FROM TCreepersTourGuide t")
public class TCreepersTourGuide extends com.fosun.fc.modules.entity.BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_TOUR_GUIDE_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_TOUR_GUIDE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_TOUR_GUIDE_ID_GENERATOR")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AGENT_NAME")
    private String agentName;

    private String area;

    @Column(name = "CARD_NO")
    private String cardNo;

    @Column(name = "CERT_DT")
    private String certDt;

    @Column(name = "CERT_NO")
    private String certNo;

    private String education;

    @Column(name = "GUIDE_LEVEL")
    private String guideLevel;

    @Column(name = "GUIDE_NO")
    private String guideNo;

    private String lan;

    private String people;

    private String phone;

    @Column(name = "PUBLISH_DT")
    private String publishDt;

    @Column(name = "PUBLISH_TYPE")
    private String publishType;

    @Column(name = "QULIFY_NO")
    private String qulifyNo;

    private String score;

    private String sex;

    public TCreepersTourGuide() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgentName() {
        return this.agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCertDt() {
        return this.certDt;
    }

    public void setCertDt(String certDt) {
        this.certDt = certDt;
    }

    public String getCertNo() {
        return this.certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getEducation() {
        return this.education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGuideLevel() {
        return this.guideLevel;
    }

    public void setGuideLevel(String guideLevel) {
        this.guideLevel = guideLevel;
    }

    public String getGuideNo() {
        return this.guideNo;
    }

    public void setGuideNo(String guideNo) {
        this.guideNo = guideNo;
    }

    public String getLan() {
        return this.lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getPeople() {
        return this.people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPublishDt() {
        return this.publishDt;
    }

    public void setPublishDt(String publishDt) {
        this.publishDt = publishDt;
    }

    public String getPublishType() {
        return this.publishType;
    }

    public void setPublishType(String publishType) {
        this.publishType = publishType;
    }

    public String getQulifyNo() {
        return this.qulifyNo;
    }

    public void setQulifyNo(String qulifyNo) {
        this.qulifyNo = qulifyNo;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
