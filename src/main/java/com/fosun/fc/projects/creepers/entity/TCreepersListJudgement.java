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
 * The persistent class for the T_CREEPERS_LIST_JUDGEMENT database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_LIST_JUDGEMENT")
@NamedQuery(name="TCreepersListJudgement.findAll", query="SELECT t FROM TCreepersListJudgement t")
public class TCreepersListJudgement extends com.fosun.fc.modules.entity.BaseEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_LIST_JUDGEMENT_ID_GENERATOR", sequenceName="SEQ_CREEPERS_JUDGEMENT_LIST")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_LIST_JUDGEMENT_ID_GENERATOR")
	private long id;

	private String memo;

	@Column(name="MER_NAME")
	private String merName;

	public TCreepersListJudgement() {
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

	public String getMerName() {
		return this.merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

}