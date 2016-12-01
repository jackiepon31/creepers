package com.fosun.fc.projects.creepers.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the T_CREEPERS_INSURANCE_BASIC database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_INSURANCE_BASIC")
@NamedQuery(name = "TCreepersInsuranceBasic.findAll", query = "SELECT t FROM TCreepersInsuranceBasic t")
public class TCreepersInsuranceBasic extends com.fosun.fc.modules.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_INSURANCE_BASIC_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_INSURANCE_BASIC")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_INSURANCE_BASIC_ID_GENERATOR")
    private long id;

    @Column(name = "CERT_NO")
    private String certNo;

    private String name;

    private String password;

    @Column(name = "WORK_TIME")
    private String workTime;

    public TCreepersInsuranceBasic() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCertNo() {
        return this.certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWorkTime() {
        return this.workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

}
