package com.fosun.fc.projects.creepers.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the T_CREEPERS_PUBLIC_TAX database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_PUBLIC_TAX")
@NamedQuery(name = "TCreepersPublicTax.findAll", query = "SELECT t FROM TCreepersPublicTax t")
public class TCreepersPublicTax extends com.fosun.fc.modules.entity.BaseEntity {

    private static final long serialVersionUID = -7908952665041233115L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_PUBLIC_TAX_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_PUBLIC_TAX")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_PUBLIC_TAX_ID_GENERATOR")
    private Long id;

    @Column(name = "COMPETENT_TAX_AUTHORITY")
    private String competentTaxAuthority;

    private String memo;

    @Column(name = "RPT_NO")
    private String rptNo;

    @Column(name = "TAX_AMOUNT")
    private BigDecimal taxAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "TAX_STATISTIC_DT")
    private Date taxStatisticDt;

    @Column(name = "TAXPAYER_NO")
    private String taxpayerNo;

    @ManyToOne
    @JoinColumn(name = "RPT_NO", referencedColumnName = "RPT_NO", insertable = false, updatable = false)
    private TCreepersAccountBak TCreepersAccountBak;

    public TCreepersPublicTax() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompetentTaxAuthority() {
        return this.competentTaxAuthority;
    }

    public void setCompetentTaxAuthority(String competentTaxAuthority) {
        this.competentTaxAuthority = competentTaxAuthority;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getRptNo() {
        return this.rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public BigDecimal getTaxAmount() {
        return this.taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Date getTaxStatisticDt() {
        return this.taxStatisticDt;
    }

    public void setTaxStatisticDt(Date taxStatisticDt) {
        this.taxStatisticDt = taxStatisticDt;
    }

    public String getTaxpayerNo() {
        return this.taxpayerNo;
    }

    public void setTaxpayerNo(String taxpayerNo) {
        this.taxpayerNo = taxpayerNo;
    }

    public TCreepersAccountBak getTCreepersAccountBak() {
        return this.TCreepersAccountBak;
    }

    public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak) {
        this.TCreepersAccountBak = TCreepersAccountBak;
    }
}
