package com.twobytes.express.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STMAS")
public class Stmas implements Serializable {
	
	private static final long serialVersionUID = -1596012178955775679L;
	private String stkcod;
	private String stkdes;
	private String stkdes2;
	private String stkgrp;
	private String barcod;
	
	@Id
    @Column(name="STKCOD")
	public String getStkcod() {
		return stkcod;
	}
	
	public void setStkcod(String stkcod) {
		this.stkcod = stkcod;
	}
	
	@Column(name="STKDES")
	public String getStkdes() {
		return stkdes;
	}
	
	public void setStkdes(String stkdes) {
		this.stkdes = stkdes;
	}
	
	@Column(name="STKDES2")
	public String getStkdes2() {
		return stkdes2;
	}
	
	public void setStkdes2(String stkdes2) {
		this.stkdes2 = stkdes2;
	}
	
	@Column(name="STKGRP")
	public String getStkgrp() {
		return stkgrp;
	}
	
	public void setStkgrp(String stkgrp) {
		this.stkgrp = stkgrp;
	}
	
	@Column(name="BARCOD")
	public String getBarcod() {
		return barcod;
	}
	
	public void setBarcod(String barcod) {
		this.barcod = barcod;
	}
	
}
