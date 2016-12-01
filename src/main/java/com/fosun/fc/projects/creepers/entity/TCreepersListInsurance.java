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
 * The persistent class for the T_CREEPERS_LIST_INSURANCE database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_LIST_INSURANCE")
@NamedQuery(name="TCreepersListInsurance.findAll", query="SELECT t FROM TCreepersListInsurance t")
public class TCreepersListInsurance extends com.fosun.fc.modules.entity.BaseEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_LIST_INSURANCE_ID_GENERATOR", sequenceName="SEQ_CREEPERS_LIST_INSURANCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_LIST_INSURANCE_ID_GENERATOR")
	private long id;

	private String memo;

	@Column(name="USER_CODE")
	private String userCode;

    private String password;
    
	public TCreepersListInsurance() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}