package com.fosun.fc.projects.creepers.dto;

public class CreepersListPatentDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = 7941470222664994420L;
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
