package com.fosun.fc.projects.creepers.dto;

public class CreepersListCourtDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = -3996634292579729222L;

    private String merNo;
    private String merName;

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
}
