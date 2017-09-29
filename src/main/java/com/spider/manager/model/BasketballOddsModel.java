package com.spider.manager.model;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.spider.db.entity.BasketballSportteryEntity;

public class BasketballOddsModel {

	private Long id;
	
	private Long uniqueId;
	
	private Integer europeId;
	
	private String matchCode;
	
	private String matchLeague;
	
	private String sportteryHdcLines;
	
	private String sportteryHdcHome;
	
	private String sportteryHdcAway;
	
	private String sportteryHdcUpdate;
	
	private String sportteryHdcMargin;
	
	private String sportteryHiloLines;
	
	private String sportteryHiloHigh;
	
	private String sportteryHiloLow;
	
	private String sportteryHiloUpdate;
	
	private String sportteryHiloMargin;
	
	private String crownHdcLines;
	
	private String crownHdcHome;
	
	private String crownHdcAway;
	
	private String crownHdcUpdate;
	
	private String crownHdcMargin;
	
	private String crownHiloLines;
	
	private String crownHiloHigh;
	
	private String crownHiloLow;
	
	private String crownHiloUpdate;
	
	private String crownHiloMargin; 
	
	private String bet365HdcLines;
	
	private String bet365HdcHome;
	
	private String bet365HdcAway;
	
	private String bet365HdcUpdate;
	
	private String bet365HdcMargin;
	
	private String bet365HiloLines;
	
	private String bet365HiloHigh;
	
	private String bet365HiloLow;
	
	private String bet365HiloUpdate;
	
	private String bet365HiloMargin;
	
	private Timestamp updateTime = new Timestamp(System.currentTimeMillis());
	
	@Transient
	private BasketballSportteryEntity basketballSportteryEntity;
	
	@Transient
    private String absenceState = "1";
	
	@Id
    @GeneratedValue
    @Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Basic
    @Column(name = "unique_id")
	public Long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	@Basic
    @Column(name = "europe_id")
	public Integer getEuropeId() {
		return europeId;
	}

	public void setEuropeId(Integer europeId) {
		this.europeId = europeId;
	}

	@Basic
    @Column(name = "match_code")
	public String getMatchCode() {
		return matchCode;
	}

	public void setMatchCode(String matchCode) {
		this.matchCode = matchCode;
	}

	@Basic
    @Column(name = "match_league")
	public String getMatchLeague() {
		return matchLeague;
	}

	public void setMatchLeague(String matchLeague) {
		this.matchLeague = matchLeague;
	}

	@Basic
    @Column(name = "sporttery_hdc_lines")
	public String getSportteryHdcLines() {
		return sportteryHdcLines;
	}

	public void setSportteryHdcLines(String sportteryHdcLines) {
		this.sportteryHdcLines = sportteryHdcLines;
	}

	@Basic
    @Column(name = "sporttery_hdc_home")
	public String getSportteryHdcHome() {
		return sportteryHdcHome;
	}

	public void setSportteryHdcHome(String sportteryHdcHome) {
		this.sportteryHdcHome = sportteryHdcHome;
	}

	@Basic
    @Column(name = "sporttery_hdc_away")
	public String getSportteryHdcAway() {
		return sportteryHdcAway;
	}

	public void setSportteryHdcAway(String sportteryHdcAway) {
		this.sportteryHdcAway = sportteryHdcAway;
	}

	@Basic
    @Column(name = "sporttery_hdc_update")
	public String getSportteryHdcUpdate() {
		return sportteryHdcUpdate;
	}

	public void setSportteryHdcUpdate(String sportteryHdcUpdate) {
		this.sportteryHdcUpdate = sportteryHdcUpdate;
	}

	@Basic
    @Column(name = "sporttery_hdc_margin")
	public String getSportteryHdcMargin() {
		return sportteryHdcMargin;
	}

	public void setSportteryHdcMargin(String sportteryHdcMargin) {
		this.sportteryHdcMargin = sportteryHdcMargin;
	}

	@Basic
    @Column(name = "sporttery_hilo_lines")
	public String getSportteryHiloLines() {
		return sportteryHiloLines;
	}

	public void setSportteryHiloLines(String sportteryHiloLines) {
		this.sportteryHiloLines = sportteryHiloLines;
	}

	@Basic
    @Column(name = "sporttery_hilo_high")
	public String getSportteryHiloHigh() {
		return sportteryHiloHigh;
	}

	public void setSportteryHiloHigh(String sportteryHiloHigh) {
		this.sportteryHiloHigh = sportteryHiloHigh;
	}

	@Basic
    @Column(name = "sporttery_hilo_low")
	public String getSportteryHiloLow() {
		return sportteryHiloLow;
	}

	public void setSportteryHiloLow(String sportteryHiloLow) {
		this.sportteryHiloLow = sportteryHiloLow;
	}

	@Basic
    @Column(name = "sporttery_hilo_update")
	public String getSportteryHiloUpdate() {
		return sportteryHiloUpdate;
	}

	public void setSportteryHiloUpdate(String sportteryHiloUpdate) {
		this.sportteryHiloUpdate = sportteryHiloUpdate;
	}

	@Basic
    @Column(name = "sporttery_hilo_margin")
	public String getSportteryHiloMargin() {
		return sportteryHiloMargin;
	}

	public void setSportteryHiloMargin(String sportteryHiloMargin) {
		this.sportteryHiloMargin = sportteryHiloMargin;
	}

	@Basic
    @Column(name = "crown_hdc_lines")
	public String getCrownHdcLines() {
		return crownHdcLines;
	}

	public void setCrownHdcLines(String crownHdcLines) {
		this.crownHdcLines = crownHdcLines;
	}

	@Basic
    @Column(name = "crown_hdc_home")
	public String getCrownHdcHome() {
		return crownHdcHome;
	}

	public void setCrownHdcHome(String crownHdcHome) {
		this.crownHdcHome = crownHdcHome;
	}

	@Basic
    @Column(name = "crown_hdc_away")
	public String getCrownHdcAway() {
		return crownHdcAway;
	}

	public void setCrownHdcAway(String crownHdcAway) {
		this.crownHdcAway = crownHdcAway;
	}

	@Basic
    @Column(name = "crown_hdc_update")
	public String getCrownHdcUpdate() {
		return crownHdcUpdate;
	}

	public void setCrownHdcUpdate(String crownHdcUpdate) {
		this.crownHdcUpdate = crownHdcUpdate;
	}

	@Basic
    @Column(name = "crown_hdc_margin")
	public String getCrownHdcMargin() {
		return crownHdcMargin;
	}

	public void setCrownHdcMargin(String crownHdcMargin) {
		this.crownHdcMargin = crownHdcMargin;
	}

	@Basic
    @Column(name = "crown_hilo_lines")
	public String getCrownHiloLines() {
		return crownHiloLines;
	}

	public void setCrownHiloLines(String crownHiloLines) {
		this.crownHiloLines = crownHiloLines;
	}

	@Basic
    @Column(name = "crown_hilo_high")
	public String getCrownHiloHigh() {
		return crownHiloHigh;
	}

	public void setCrownHiloHigh(String crownHiloHigh) {
		this.crownHiloHigh = crownHiloHigh;
	}

	@Basic
    @Column(name = "crown_hilo_low")
	public String getCrownHiloLow() {
		return crownHiloLow;
	}

	public void setCrownHiloLow(String crownHiloLow) {
		this.crownHiloLow = crownHiloLow;
	}

	@Basic
    @Column(name = "crown_hilo_update")
	public String getCrownHiloUpdate() {
		return crownHiloUpdate;
	}

	public void setCrownHiloUpdate(String crownHiloUpdate) {
		this.crownHiloUpdate = crownHiloUpdate;
	}

	@Basic
    @Column(name = "crown_hilo_margin")
	public String getCrownHiloMargin() {
		return crownHiloMargin;
	}

	public void setCrownHiloMargin(String crownHiloMargin) {
		this.crownHiloMargin = crownHiloMargin;
	}

	@Basic
    @Column(name = "bet365_hdc_lines")
	public String getBet365HdcLines() {
		return bet365HdcLines;
	}

	public void setBet365HdcLines(String bet365HdcLines) {
		this.bet365HdcLines = bet365HdcLines;
	}

	@Basic
    @Column(name = "bet365_hdc_home")
	public String getBet365HdcHome() {
		return bet365HdcHome;
	}

	public void setBet365HdcHome(String bet365HdcHome) {
		this.bet365HdcHome = bet365HdcHome;
	}

	@Basic
    @Column(name = "bet365_hdc_away")
	public String getBet365HdcAway() {
		return bet365HdcAway;
	}

	public void setBet365HdcAway(String bet365HdcAway) {
		this.bet365HdcAway = bet365HdcAway;
	}

	@Basic
    @Column(name = "bet365_hdc_update")
	public String getBet365HdcUpdate() {
		return bet365HdcUpdate;
	}

	public void setBet365HdcUpdate(String bet365HdcUpdate) {
		this.bet365HdcUpdate = bet365HdcUpdate;
	}

	@Basic
    @Column(name = "bet365_hdc_margin")
	public String getBet365HdcMargin() {
		return bet365HdcMargin;
	}

	public void setBet365HdcMargin(String bet365HdcMargin) {
		this.bet365HdcMargin = bet365HdcMargin;
	}

	@Basic
    @Column(name = "bet365_hilo_lines")
	public String getBet365HiloLines() {
		return bet365HiloLines;
	}

	public void setBet365HiloLines(String bet365HiloLines) {
		this.bet365HiloLines = bet365HiloLines;
	}

	@Basic
    @Column(name = "bet365_hilo_high")
	public String getBet365HiloHigh() {
		return bet365HiloHigh;
	}

	public void setBet365HiloHigh(String bet365HiloHigh) {
		this.bet365HiloHigh = bet365HiloHigh;
	}

	@Basic
    @Column(name = "bet365_hilo_low")
	public String getBet365HiloLow() {
		return bet365HiloLow;
	}

	public void setBet365HiloLow(String bet365HiloLow) {
		this.bet365HiloLow = bet365HiloLow;
	}

	@Basic
    @Column(name = "bet365_hilo_update")
	public String getBet365HiloUpdate() {
		return bet365HiloUpdate;
	}

	public void setBet365HiloUpdate(String bet365HiloUpdate) {
		this.bet365HiloUpdate = bet365HiloUpdate;
	}

	@Basic
    @Column(name = "bet365_hilo_margin")
	public String getBet365HiloMargin() {
		return bet365HiloMargin;
	}

	public void setBet365HiloMargin(String bet365HiloMargin) {
		this.bet365HiloMargin = bet365HiloMargin;
	}
	
	@Basic
    @Column(name = "update_time")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public BasketballSportteryEntity getBasketballSportteryEntity() {
		return basketballSportteryEntity;
	}

	public void setBasketballSportteryEntity(
			BasketballSportteryEntity basketballSportteryEntity) {
		this.basketballSportteryEntity = basketballSportteryEntity;
	}

	public String getAbsenceState() {
		return absenceState;
	}

	public void setAbsenceState(String absenceState) {
		this.absenceState = absenceState;
	}
}
