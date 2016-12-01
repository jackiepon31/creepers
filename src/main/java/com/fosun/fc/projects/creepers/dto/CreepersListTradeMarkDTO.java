package com.fosun.fc.projects.creepers.dto;

public class CreepersListTradeMarkDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = 8887650073349468610L;
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
