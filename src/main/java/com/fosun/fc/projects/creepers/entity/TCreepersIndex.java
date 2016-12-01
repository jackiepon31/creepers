package com.fosun.fc.projects.creepers.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the T_CREEPERS_INDEX database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_INDEX")
@NamedQuery(name="TCreepersIndex.findAll", query="SELECT t FROM TCreepersIndex t")
public class TCreepersIndex implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_INDEX_ID_GENERATOR", sequenceName="SEQ_CREEPERS_ENT_INTEL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_INDEX_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="SEQ_NO", length=200)
	private String seqNo;

	public TCreepersIndex() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

}