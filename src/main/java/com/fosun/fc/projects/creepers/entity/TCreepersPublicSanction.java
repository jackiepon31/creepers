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
 * The persistent class for the T_CREEPERS_PUBLIC_SANCTION database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_PUBLIC_SANCTION")
@NamedQuery(name = "TCreepersPublicSanction.findAll", query = "SELECT t FROM TCreepersPublicSanction t")
public class TCreepersPublicSanction extends com.fosun.fc.modules.entity.BaseEntity {

    private static final long serialVersionUID = -2953545389305079012L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_PUBLIC_SANCTION_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_PUBLIC_SANCTION")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_PUBLIC_SANCTION_ID_GENERATOR")
    private Long id;

    @Column(name = "DOC_NO")
    private String docNo;

    private String memo;

    @Temporal(TemporalType.DATE)
    @Column(name = "PUNISH_DT")
    private Date punishDt;

    @Column(name = "PUNISHMENT_AMOUNT")
    private BigDecimal punishmentAmount;

    @Column(name = "PUNISHMENT_CONTENT")
    private String punishmentContent;

    @Temporal(TemporalType.DATE)
    @Column(name = "PUNISHMENT_ISSUE_DT")
    private Date punishmentIssueDt;

    @Column(name = "PUNISHMENT_ORG")
    private String punishmentOrg;

    @Column(name = "RECONSIDERATION_FLAG")
    private String reconsiderationFlag;

    @Column(name = "RECONSIDERATION_RSLT")
    private String reconsiderationRslt;

    @Column(name = "RPT_NO")
    private String rptNo;

    @ManyToOne
    @JoinColumn(name = "RPT_NO", referencedColumnName = "RPT_NO", insertable = false, updatable = false)
    private TCreepersAccountBak TCreepersAccountBak;

    public TCreepersPublicSanction() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocNo() {
        return this.docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getPunishDt() {
        return this.punishDt;
    }

    public void setPunishDt(Date punishDt) {
        this.punishDt = punishDt;
    }

    public BigDecimal getPunishmentAmount() {
        return this.punishmentAmount;
    }

    public void setPunishmentAmount(BigDecimal punishmentAmount) {
        this.punishmentAmount = punishmentAmount;
    }

    public String getPunishmentContent() {
        return this.punishmentContent;
    }

    public void setPunishmentContent(String punishmentContent) {
        this.punishmentContent = punishmentContent;
    }

    public Date getPunishmentIssueDt() {
        return this.punishmentIssueDt;
    }

    public void setPunishmentIssueDt(Date punishmentIssueDt) {
        this.punishmentIssueDt = punishmentIssueDt;
    }

    public String getPunishmentOrg() {
        return this.punishmentOrg;
    }

    public void setPunishmentOrg(String punishmentOrg) {
        this.punishmentOrg = punishmentOrg;
    }

    public String getReconsiderationFlag() {
        return this.reconsiderationFlag;
    }

    public void setReconsiderationFlag(String reconsiderationFlag) {
        this.reconsiderationFlag = reconsiderationFlag;
    }

    public String getReconsiderationRslt() {
        return this.reconsiderationRslt;
    }

    public void setReconsiderationRslt(String reconsiderationRslt) {
        this.reconsiderationRslt = reconsiderationRslt;
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
