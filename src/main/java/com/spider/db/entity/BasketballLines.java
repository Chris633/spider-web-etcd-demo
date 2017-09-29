package com.spider.db.entity;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "basketball_lines", schema = "", catalog = "crawler")
public class BasketballLines {

	private long id;
	
	private long uniqueId;
	
	private String hdcLines;
	
	private String hiloLines;
	
	private Timestamp updateTime = new Timestamp(System.currentTimeMillis());
	
	public BasketballLines() {

	}
	
	public BasketballLines(BasketballSportteryEntity basketballSportteryEntity) {
		this.uniqueId = basketballSportteryEntity.getUniqueId();
		this.hdcLines = basketballSportteryEntity.getHdcLine().toString();
		this.hiloLines = basketballSportteryEntity.getHiloLine().toString();
	}

	@Id
    @GeneratedValue
    @Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
    @Column(name = "unique_id")
	public long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(long uniqueId) {
		this.uniqueId = uniqueId;
	}

	@Basic
    @Column(name = "hdc_lines")
	public String getHdcLines() {
		return hdcLines;
	}

	public void setHdcLines(String hdcLines) {
		this.hdcLines = hdcLines;
	}

	@Basic
    @Column(name = "hilo_lines")
	public String getHiloLines() {
		return hiloLines;
	}

	public void setHiloLines(String hiloLines) {
		this.hiloLines = hiloLines;
	}

	@Basic
    @Column(name = "update_time")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
