package com.fosun.fc.projects.creepers.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CreepersAccountBakDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = 853761142523269807L;

    private String cde;

    @Temporal(TemporalType.DATE)
    private Date createdDt;

    private String pwd;

    private String rptNo;

    private String status;

    @Temporal(TemporalType.DATE)
    private Date updatedDt;

    private String usr;

    private String startDt;

    private String endDt;

    public CreepersAccountBakDTO() {
    }

    public String getCde() {
        return this.cde;
    }

    public void setCde(String cde) {
        this.cde = cde;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRptNo() {
        return this.rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsr() {
        return this.usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getStartDt() {
        return startDt;
    }

    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }

    public String getEndDt() {
        return endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

}
