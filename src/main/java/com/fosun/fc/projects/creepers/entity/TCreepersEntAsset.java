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
 * The persistent class for the T_CREEPERS_ENT_ASSET database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_ENT_ASSET")
@NamedQuery(name="TCreepersEntAsset.findAll", query="SELECT t FROM TCreepersEntAsset t")
public class TCreepersEntAsset extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9199807384649770849L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_ENT_ASSET_ID_GENERATOR", sequenceName="SEQ_CREEPERS_ENT_ASSET")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_ENT_ASSET_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=200)
	private String memo;

	@Column(name="MER_NO", length=100)
	private String merNo;

	@Column(name="NET_PROFIT", length=60)
	private String netProfit;

	@Column(length=800)
	private String status;

	@Column(name="TOTAL_ASSET", length=60)
	private String totalAsset;

	@Column(name="TOTAL_AVENUE", length=60)
	private String totalAvenue;

	@Column(name="TOTAL_BUS_INCOME", length=60)
	private String totalBusIncome;

	@Column(name="TOTAL_EQUITY", length=60)
	private String totalEquity;

	@Column(name="TOTAL_INCOME", length=60)
	private String totalIncome;

	@Column(name="TOTAL_LIABILITIES", length=60)
	private String totalLiabilities;

	@Column(name="TOTAL_TAX", length=60)
	private String totalTax;

	public TCreepersEntAsset() {
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

	public String getMerNo() {
		return this.merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getNetProfit() {
		return this.netProfit;
	}

	public void setNetProfit(String netProfit) {
		this.netProfit = netProfit;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotalAsset() {
		return this.totalAsset;
	}

	public void setTotalAsset(String totalAsset) {
		this.totalAsset = totalAsset;
	}

	public String getTotalAvenue() {
		return this.totalAvenue;
	}

	public void setTotalAvenue(String totalAvenue) {
		this.totalAvenue = totalAvenue;
	}

	public String getTotalBusIncome() {
		return this.totalBusIncome;
	}

	public void setTotalBusIncome(String totalBusIncome) {
		this.totalBusIncome = totalBusIncome;
	}

	public String getTotalEquity() {
		return this.totalEquity;
	}

	public void setTotalEquity(String totalEquity) {
		this.totalEquity = totalEquity;
	}

	public String getTotalIncome() {
		return this.totalIncome;
	}

	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}

	public String getTotalLiabilities() {
		return this.totalLiabilities;
	}

	public void setTotalLiabilities(String totalLiabilities) {
		this.totalLiabilities = totalLiabilities;
	}

	public String getTotalTax() {
		return this.totalTax;
	}

	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}

}