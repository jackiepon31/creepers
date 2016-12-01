package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: ﻿T_CREEPERS_LIST_SHIXIN 爬虫任务队列-失信被执行人信息
 * <p>
 * 
 * @author LiZhanPing
 * @since 2016-08-31 22:24:32
 * @see
 */

public class CreepersListShixinDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = 8900230892999919712L;
    // 商户名称
    private String merName;
    // 备注信息
    private String memo;

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
