package com.fosun.fc.projects.creepers.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fosun.fc.modules.entity.BaseEntity;

/**
 * The persistent class for the T_CREEPERS_ACCOUNT_BAK database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_ACCOUNT_BAK")
@NamedQuery(name = "TCreepersAccountBak.findAll", query = "SELECT t FROM TCreepersAccountBak t")
public class TCreepersAccountBak extends BaseEntity {

    private static final long serialVersionUID = 5445156075801891159L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_ACCOUNT_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_ACCOUNT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_ACCOUNT_ID_GENERATOR")
    private Long id;

    @Column(name = "RPT_NO")
    private String rptNo;

    private String filePath;

    private String cde;

    private String memo;

    private String pwd;

    private String status;

    private String usr;

    @OneToMany(mappedBy = "TCreepersAccountBak")
    private List<TCreepersAssetHandle> TCreepersAssetHandles;

    @OneToMany(mappedBy = "TCreepersAccountBak")
    private List<TCreepersBasic> TCreepersBasics;

    @OneToMany(mappedBy = "TCreepersAccountBak")
    private List<TCreepersCcDetail> TCreepersCcDetails;

    @OneToMany(mappedBy = "TCreepersAccountBak")
    private List<TCreepersCompensatory> TCreepersCompensatories;

    @OneToMany(mappedBy = "TCreepersAccountBak")
    private List<TCreepersGeneral> TCreepersGenerals;

    @OneToMany(mappedBy = "TCreepersAccountBak")
    private List<TCreepersGuarantee> TCreepersGuarantees;

    @OneToMany(mappedBy = "TCreepersAccountBak")
    private List<TCreepersHlDetail> TCreepersHlDetails;

    @OneToMany(mappedBy = "TCreepersAccountBak")
    private List<TCreepersOlDetail> TCreepersOlDetails;

    @OneToMany(mappedBy = "TCreepersAccountBak")
    private List<TCreepersPublicCivil> TCreepersPublicCivils;

    @OneToMany(mappedBy = "TCreepersAccountBak")
    private List<TCreepersPublicEnforcement> TCreepersPublicEnforcements;

    @OneToMany(mappedBy = "TCreepersAccountBak")
    private List<TCreepersPublicIsp> TCreepersPublicIsps;

    @OneToMany(mappedBy = "TCreepersAccountBak")
    private List<TCreepersPublicSanction> TCreepersPublicSanctions;

    @OneToMany(mappedBy = "TCreepersAccountBak")
    private List<TCreepersPublicTax> TCreepersPublicTaxs;


    public TCreepersAccountBak() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRptNo() {
        return rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCde() {
        return this.cde;
    }

    public void setCde(String cde) {
        this.cde = cde;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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

    public List<TCreepersAssetHandle> getTCreepersAssetHandles() {
        return this.TCreepersAssetHandles;
    }

    public void setTCreepersAssetHandles(List<TCreepersAssetHandle> TCreepersAssetHandles) {
        this.TCreepersAssetHandles = TCreepersAssetHandles;
    }

    public TCreepersAssetHandle addTCreepersAssetHandle(TCreepersAssetHandle TCreepersAssetHandle) {
        getTCreepersAssetHandles().add(TCreepersAssetHandle);
        TCreepersAssetHandle.setTCreepersAccountBak(this);

        return TCreepersAssetHandle;
    }

    public TCreepersAssetHandle removeTCreepersAssetHandle(TCreepersAssetHandle TCreepersAssetHandle) {
        getTCreepersAssetHandles().remove(TCreepersAssetHandle);
        TCreepersAssetHandle.setTCreepersAccountBak(null);

        return TCreepersAssetHandle;
    }

    public List<TCreepersBasic> getTCreepersBasics() {
        return this.TCreepersBasics;
    }

    public void setTCreepersBasics(List<TCreepersBasic> TCreepersBasics) {
        this.TCreepersBasics = TCreepersBasics;
    }

    public TCreepersBasic addTCreepersBasic(TCreepersBasic TCreepersBasic) {
        getTCreepersBasics().add(TCreepersBasic);
        TCreepersBasic.setTCreepersAccountBak(this);

        return TCreepersBasic;
    }

    public TCreepersBasic removeTCreepersBasic(TCreepersBasic TCreepersBasic) {
        getTCreepersBasics().remove(TCreepersBasic);
        TCreepersBasic.setTCreepersAccountBak(null);

        return TCreepersBasic;
    }

    public List<TCreepersCcDetail> getTCreepersCcDetails() {
        return this.TCreepersCcDetails;
    }

    public void setTCreepersCcDetails(List<TCreepersCcDetail> TCreepersCcDetails) {
        this.TCreepersCcDetails = TCreepersCcDetails;
    }

    public TCreepersCcDetail addTCreepersCcDetail(TCreepersCcDetail TCreepersCcDetail) {
        getTCreepersCcDetails().add(TCreepersCcDetail);
        TCreepersCcDetail.setTCreepersAccountBak(this);

        return TCreepersCcDetail;
    }

    public TCreepersCcDetail removeTCreepersCcDetail(TCreepersCcDetail TCreepersCcDetail) {
        getTCreepersCcDetails().remove(TCreepersCcDetail);
        TCreepersCcDetail.setTCreepersAccountBak(null);

        return TCreepersCcDetail;
    }

    public List<TCreepersCompensatory> getTCreepersCompensatories() {
        return this.TCreepersCompensatories;
    }

    public void setTCreepersCompensatories(List<TCreepersCompensatory> TCreepersCompensatories) {
        this.TCreepersCompensatories = TCreepersCompensatories;
    }

    public TCreepersCompensatory addTCreepersCompensatory(TCreepersCompensatory TCreepersCompensatory) {
        getTCreepersCompensatories().add(TCreepersCompensatory);
        TCreepersCompensatory.setTCreepersAccountBak(this);

        return TCreepersCompensatory;
    }

    public TCreepersCompensatory removeTCreepersCompensatory(TCreepersCompensatory TCreepersCompensatory) {
        getTCreepersCompensatories().remove(TCreepersCompensatory);
        TCreepersCompensatory.setTCreepersAccountBak(null);

        return TCreepersCompensatory;
    }

    public List<TCreepersGeneral> getTCreepersGenerals() {
        return this.TCreepersGenerals;
    }

    public void setTCreepersGenerals(List<TCreepersGeneral> TCreepersGenerals) {
        this.TCreepersGenerals = TCreepersGenerals;
    }

    public TCreepersGeneral addTCreepersGeneral(TCreepersGeneral TCreepersGeneral) {
        getTCreepersGenerals().add(TCreepersGeneral);
        TCreepersGeneral.setTCreepersAccountBak(this);

        return TCreepersGeneral;
    }

    public TCreepersGeneral removeTCreepersGeneral(TCreepersGeneral TCreepersGeneral) {
        getTCreepersGenerals().remove(TCreepersGeneral);
        TCreepersGeneral.setTCreepersAccountBak(null);

        return TCreepersGeneral;
    }

    public List<TCreepersGuarantee> getTCreepersGuarantees() {
        return this.TCreepersGuarantees;
    }

    public void setTCreepersGuarantees(List<TCreepersGuarantee> TCreepersGuarantees) {
        this.TCreepersGuarantees = TCreepersGuarantees;
    }

    public TCreepersGuarantee addTCreepersGuarantee(TCreepersGuarantee TCreepersGuarantee) {
        getTCreepersGuarantees().add(TCreepersGuarantee);
        TCreepersGuarantee.setTCreepersAccountBak(this);

        return TCreepersGuarantee;
    }

    public TCreepersGuarantee removeTCreepersGuarantee(TCreepersGuarantee TCreepersGuarantee) {
        getTCreepersGuarantees().remove(TCreepersGuarantee);
        TCreepersGuarantee.setTCreepersAccountBak(null);

        return TCreepersGuarantee;
    }

    public List<TCreepersHlDetail> getTCreepersHlDetails() {
        return this.TCreepersHlDetails;
    }

    public void setTCreepersHlDetails(List<TCreepersHlDetail> TCreepersHlDetails) {
        this.TCreepersHlDetails = TCreepersHlDetails;
    }

    public TCreepersHlDetail addTCreepersHlDetail(TCreepersHlDetail TCreepersHlDetail) {
        getTCreepersHlDetails().add(TCreepersHlDetail);
        TCreepersHlDetail.setTCreepersAccountBak(this);

        return TCreepersHlDetail;
    }

    public TCreepersHlDetail removeTCreepersHlDetail(TCreepersHlDetail TCreepersHlDetail) {
        getTCreepersHlDetails().remove(TCreepersHlDetail);
        TCreepersHlDetail.setTCreepersAccountBak(null);

        return TCreepersHlDetail;
    }

    public List<TCreepersOlDetail> getTCreepersOlDetails() {
        return this.TCreepersOlDetails;
    }

    public void setTCreepersOlDetails(List<TCreepersOlDetail> TCreepersOlDetails) {
        this.TCreepersOlDetails = TCreepersOlDetails;
    }

    public TCreepersOlDetail addTCreepersOlDetail(TCreepersOlDetail TCreepersOlDetail) {
        getTCreepersOlDetails().add(TCreepersOlDetail);
        TCreepersOlDetail.setTCreepersAccountBak(this);

        return TCreepersOlDetail;
    }

    public TCreepersOlDetail removeTCreepersOlDetail(TCreepersOlDetail TCreepersOlDetail) {
        getTCreepersOlDetails().remove(TCreepersOlDetail);
        TCreepersOlDetail.setTCreepersAccountBak(null);

        return TCreepersOlDetail;
    }

    public List<TCreepersPublicCivil> getTCreepersPublicCivils() {
        return this.TCreepersPublicCivils;
    }

    public void setTCreepersPublicCivils(List<TCreepersPublicCivil> TCreepersPublicCivils) {
        this.TCreepersPublicCivils = TCreepersPublicCivils;
    }

    public TCreepersPublicCivil addTCreepersPublicCivil(TCreepersPublicCivil TCreepersPublicCivil) {
        getTCreepersPublicCivils().add(TCreepersPublicCivil);
        TCreepersPublicCivil.setTCreepersAccountBak(this);

        return TCreepersPublicCivil;
    }

    public TCreepersPublicCivil removeTCreepersPublicCivil(TCreepersPublicCivil TCreepersPublicCivil) {
        getTCreepersPublicCivils().remove(TCreepersPublicCivil);
        TCreepersPublicCivil.setTCreepersAccountBak(null);

        return TCreepersPublicCivil;
    }

    public List<TCreepersPublicEnforcement> getTCreepersPublicEnforcements() {
        return this.TCreepersPublicEnforcements;
    }

    public void setTCreepersPublicEnforcements(List<TCreepersPublicEnforcement> TCreepersPublicEnforcements) {
        this.TCreepersPublicEnforcements = TCreepersPublicEnforcements;
    }

    public TCreepersPublicEnforcement addTCreepersPublicEnforcement(
            TCreepersPublicEnforcement TCreepersPublicEnforcement) {
        getTCreepersPublicEnforcements().add(TCreepersPublicEnforcement);
        TCreepersPublicEnforcement.setTCreepersAccountBak(this);

        return TCreepersPublicEnforcement;
    }

    public TCreepersPublicEnforcement removeTCreepersPublicEnforcement(
            TCreepersPublicEnforcement TCreepersPublicEnforcement) {
        getTCreepersPublicEnforcements().remove(TCreepersPublicEnforcement);
        TCreepersPublicEnforcement.setTCreepersAccountBak(null);

        return TCreepersPublicEnforcement;
    }

    public List<TCreepersPublicIsp> getTCreepersPublicIsps() {
        return this.TCreepersPublicIsps;
    }

    public void setTCreepersPublicIsps(List<TCreepersPublicIsp> TCreepersPublicIsps) {
        this.TCreepersPublicIsps = TCreepersPublicIsps;
    }

    public TCreepersPublicIsp addTCreepersPublicIsp(TCreepersPublicIsp TCreepersPublicIsp) {
        getTCreepersPublicIsps().add(TCreepersPublicIsp);
        TCreepersPublicIsp.setTCreepersAccountBak(this);

        return TCreepersPublicIsp;
    }

    public TCreepersPublicIsp removeTCreepersPublicIsp(TCreepersPublicIsp TCreepersPublicIsp) {
        getTCreepersPublicIsps().remove(TCreepersPublicIsp);
        TCreepersPublicIsp.setTCreepersAccountBak(null);

        return TCreepersPublicIsp;
    }

    public List<TCreepersPublicSanction> getTCreepersPublicSanctions() {
        return this.TCreepersPublicSanctions;
    }

    public void setTCreepersPublicSanctions(List<TCreepersPublicSanction> TCreepersPublicSanctions) {
        this.TCreepersPublicSanctions = TCreepersPublicSanctions;
    }

    public TCreepersPublicSanction addTCreepersPublicSanction(TCreepersPublicSanction TCreepersPublicSanction) {
        getTCreepersPublicSanctions().add(TCreepersPublicSanction);
        TCreepersPublicSanction.setTCreepersAccountBak(this);

        return TCreepersPublicSanction;
    }

    public TCreepersPublicSanction removeTCreepersPublicSanction(TCreepersPublicSanction TCreepersPublicSanction) {
        getTCreepersPublicSanctions().remove(TCreepersPublicSanction);
        TCreepersPublicSanction.setTCreepersAccountBak(null);

        return TCreepersPublicSanction;
    }

    public List<TCreepersPublicTax> getTCreepersPublicTaxs() {
        return this.TCreepersPublicTaxs;
    }

    public void setTCreepersPublicTaxs(List<TCreepersPublicTax> TCreepersPublicTaxs) {
        this.TCreepersPublicTaxs = TCreepersPublicTaxs;
    }

    public TCreepersPublicTax addTCreepersPublicTax(TCreepersPublicTax TCreepersPublicTax) {
        getTCreepersPublicTaxs().add(TCreepersPublicTax);
        TCreepersPublicTax.setTCreepersAccountBak(this);

        return TCreepersPublicTax;
    }

    public TCreepersPublicTax removeTCreepersPublicTax(TCreepersPublicTax TCreepersPublicTax) {
        getTCreepersPublicTaxs().remove(TCreepersPublicTax);
        TCreepersPublicTax.setTCreepersAccountBak(null);

        return TCreepersPublicTax;
    }

}
