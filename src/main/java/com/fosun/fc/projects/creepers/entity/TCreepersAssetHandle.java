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

import com.fosun.fc.modules.entity.BaseEntity;

/**
 * The persistent class for the T_CREEPERS_ASSET_HANDLE database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_ASSET_HANDLE")
@NamedQuery(name = "TCreepersAssetHandle.findAll", query = "SELECT t FROM TCreepersAssetHandle t")
public class TCreepersAssetHandle extends BaseEntity {

    private static final long serialVersionUID = 3240031076906178386L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_ASSET_HANDLE_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_ASSET_HANDLE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_ASSET_HANDLE_ID_GENERATOR")
    private Long id;

    @Column(name = "ASSET_DISPOSAL_AMOUNT")
    private BigDecimal assetDisposalAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "ASSET_DISPOSAL_DT")
    private Date assetDisposalDt;

    @Column(name = "ASSET_DISPOSAL_ORG")
    private String assetDisposalOrg;

    private BigDecimal balance;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_BACK_DT")
    private Date lastBackDt;

    private String memo;

    @Column(name = "RPT_NO")
    private String rptNo;

    @ManyToOne
    @JoinColumn(name = "RPT_NO", referencedColumnName = "RPT_NO", insertable = false, updatable = false)
    private TCreepersAccountBak TCreepersAccountBak;

    public TCreepersAssetHandle() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAssetDisposalAmount() {
        return this.assetDisposalAmount;
    }

    public void setAssetDisposalAmount(BigDecimal assetDisposalAmount) {
        this.assetDisposalAmount = assetDisposalAmount;
    }

    public Date getAssetDisposalDt() {
        return this.assetDisposalDt;
    }

    public void setAssetDisposalDt(Date assetDisposalDt) {
        this.assetDisposalDt = assetDisposalDt;
    }

    public String getAssetDisposalOrg() {
        return this.assetDisposalOrg;
    }

    public void setAssetDisposalOrg(String assetDisposalOrg) {
        this.assetDisposalOrg = assetDisposalOrg;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getLastBackDt() {
        return this.lastBackDt;
    }

    public void setLastBackDt(Date lastBackDt) {
        this.lastBackDt = lastBackDt;
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

    public TCreepersAccountBak getTCreepersAccountBak() {
        return this.TCreepersAccountBak;
    }

    public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak) {
        this.TCreepersAccountBak = TCreepersAccountBak;
    }
}
