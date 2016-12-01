package com.fosun.fc.projects.creepers.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fosun.fc.modules.dto.BaseDTO;

/**
 * 
 * <p>
 * description: Creepers 基础DTO类 继承core的baseDTO
 *
 * 增加字段： 创建者 创建时间 修改者 修改时间
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月25日
 * @see
 */
public class CreepersBaseDTO extends BaseDTO {

    private static final long serialVersionUID = -2695341454487109538L;

    // 查询的日期区间

    public String GT_createdDt="";

    public String LT_createdDt="";

    public String GT_updatedDt="";

    public String LT_updatedDt="";

    public String createdBy="";

    @Temporal(TemporalType.DATE)
    public Date createdDt;

    public String updatedBy;

    @Temporal(TemporalType.DATE)
    public Date updatedDt;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

    /**
     * @return the gT_createdDt
     */
    public String getGT_createdDt() {
        return GT_createdDt;
    }

    /**
     * @param gT_createdDt the gT_createdDt to set
     */
    public void setGT_createdDt(String gT_createdDt) {
        GT_createdDt = gT_createdDt;
    }

    /**
     * @return the lT_createdDt
     */
    public String getLT_createdDt() {
        return LT_createdDt;
    }

    /**
     * @param lT_createdDt the lT_createdDt to set
     */
    public void setLT_createdDt(String lT_createdDt) {
        LT_createdDt = lT_createdDt;
    }

    /**
     * @return the gT_updatedDt
     */
    public String getGT_updatedDt() {
        return GT_updatedDt;
    }

    /**
     * @param gT_updatedDt the gT_updatedDt to set
     */
    public void setGT_updatedDt(String gT_updatedDt) {
        GT_updatedDt = gT_updatedDt;
    }

    /**
     * @return the lT_updatedDt
     */
    public String getLT_updatedDt() {
        return LT_updatedDt;
    }

    /**
     * @param lT_updatedDt the lT_updatedDt to set
     */
    public void setLT_updatedDt(String lT_updatedDt) {
        LT_updatedDt = lT_updatedDt;
    }


}
