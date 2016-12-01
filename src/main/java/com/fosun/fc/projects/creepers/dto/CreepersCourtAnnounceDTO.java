package com.fosun.fc.projects.creepers.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CreepersCourtAnnounceDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = 9211291614055653237L;

    private String merNo;
    private String merName;
    private String announceType;
    private String courtName;
    private String announceContent;
    @Temporal(TemporalType.DATE)
    private Date announceDt;
    
    public CreepersCourtAnnounceDTO(){
        
    }

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getAnnounceType() {
        return announceType;
    }

    public void setAnnounceType(String announceType) {
        this.announceType = announceType;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getAnnounceContent() {
        return announceContent;
    }

    public void setAnnounceContent(String announceContent) {
        this.announceContent = announceContent;
    }

    public Date getAnnounceDt() {
        return announceDt;
    }

    public void setAnnounceDt(Date announceDt) {
        this.announceDt = announceDt;
    }

}
