package com.fosun.fc.projects.creepers.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the T_CREEPERS_COURT_DISHONEST database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_COURT_DISHONEST")
@NamedQuery(name="TCreepersCourtDishonest.findAll", query="SELECT t FROM TCreepersCourtDishonest t")
public class TCreepersCourtDishonest extends com.fosun.fc.modules.entity.BaseEntity implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 8943711576929338114L;

    @Id
	@SequenceGenerator(name="T_CREEPERS_COURT_DISHONEST_ID_GENERATOR", sequenceName="SEQ_CREEPERS_COURT_DISHONEST")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_COURT_DISHONEST_ID_GENERATOR")
	private long id;

	private String code;

	private String memo;

	private String name;

	private String province;

	private String source;

	public TCreepersCourtDishonest() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}