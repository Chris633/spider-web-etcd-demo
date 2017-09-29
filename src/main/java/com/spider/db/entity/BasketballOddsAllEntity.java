package com.spider.db.entity;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "basketball_odds_all", schema = "", catalog = "crawler")
public class BasketballOddsAllEntity {

	@Id
    @GeneratedValue
    @Column(name = "id")
	private Long id;
	
	@Basic
    @Column(name = "unique_id")
	private Long uniqueId;
	
	@Basic
    @Column(name = "europe_id")
	private Integer europeId;
	
	@Basic
    @Column(name = "match_code")
	private String matchCode;
	
	@Basic
    @Column(name = "match_league")
	private String matchLeague;
	
	@Basic
    @Column(name = "sporttery_hdc_lines")
	private String sportteryHdcLines;
	
	@Basic
    @Column(name = "sporttery_hdc_home")
	private String sportteryHdcHome;
	
	@Basic
    @Column(name = "sporttery_hdc_away")
	private String sportteryHdcAway;
	
	@Basic
    @Column(name = "sporttery_hdc_update")
	private String sportteryHdcUpdate;
	
	@Basic
    @Column(name = "sporttery_hdc_margin")
	private String sportteryHdcMargin;
	
	@Basic
    @Column(name = "sporttery_hilo_lines")
	private String sportteryHiloLines;
	
	@Basic
    @Column(name = "sporttery_hilo_high")
	private String sportteryHiloHigh;
	
	@Basic
    @Column(name = "sporttery_hilo_low")
	private String sportteryHiloLow;
	
	@Basic
    @Column(name = "sporttery_hilo_update")
	private String sportteryHiloUpdate;
	
	@Basic
    @Column(name = "sporttery_hilo_margin")
	private String sportteryHiloMargin;
	
	@Basic
    @Column(name = "crown_hdc_lines")
	private String crownHdcLines;
	
	@Basic
    @Column(name = "crown_hdc_home")
	private String crownHdcHome;
	
	@Basic
    @Column(name = "crown_hdc_away")
	private String crownHdcAway;
	
	@Basic
    @Column(name = "crown_hdc_update")
	private String crownHdcUpdate;

	@Basic
    @Column(name = "crown_hdc_margin")
	private String crownHdcMargin;
	
	@Basic
    @Column(name = "crown_hilo_lines")
	private String crownHiloLines;
	
	@Basic
    @Column(name = "crown_hilo_high")
	private String crownHiloHigh;
	
	@Basic
    @Column(name = "crown_hilo_low")
	private String crownHiloLow;
	
	@Basic
    @Column(name = "crown_hilo_update")
	private String crownHiloUpdate;
	
	@Basic
    @Column(name = "crown_hilo_margin")
	private String crownHiloMargin; 

	@Basic
    @Column(name = "bet365_hdc_lines")
	private String bet365HdcLines;
	
	@Basic
    @Column(name = "bet365_hdc_home")
	private String bet365HdcHome;
	
	@Basic
    @Column(name = "bet365_hdc_away")
	private String bet365HdcAway;
	
	@Basic
    @Column(name = "bet365_hdc_update")
	private String bet365HdcUpdate;

	@Basic
    @Column(name = "bet365_hdc_margin")
	private String bet365HdcMargin;
	
	@Basic
    @Column(name = "bet365_hilo_lines")
	private String bet365HiloLines;

	@Basic
    @Column(name = "bet365_hilo_high")
	private String bet365HiloHigh;

	@Basic
    @Column(name = "bet365_hilo_low")
	private String bet365HiloLow;

	@Basic
    @Column(name = "bet365_hilo_update")
	private String bet365HiloUpdate;
	
	@Basic
    @Column(name = "bet365_hilo_margin")
	private String bet365HiloMargin;
	
	@Basic
    @Column(name = "update_time")
	private Timestamp updateTime = new Timestamp(System.currentTimeMillis());
	
	@Transient
	private BasketballSportteryEntity basketballSportteryEntity;
	
	@Transient
    private String absenceState = "1";
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	
	public Integer getEuropeId() {
		return europeId;
	}

	public void setEuropeId(Integer europeId) {
		this.europeId = europeId;
	}

	public String getMatchCode() {
		return matchCode;
	}

	public void setMatchCode(String matchCode) {
		this.matchCode = matchCode;
	}

	
	public String getMatchLeague() {
		return matchLeague;
	}

	public void setMatchLeague(String matchLeague) {
		this.matchLeague = matchLeague;
	}

	
	public String getSportteryHdcLines() {
		return sportteryHdcLines;
	}

	public void setSportteryHdcLines(String sportteryHdcLines) {
		this.sportteryHdcLines = sportteryHdcLines;
	}

	
	public String getSportteryHdcHome() {
		return sportteryHdcHome;
	}

	public void setSportteryHdcHome(String sportteryHdcHome) {
		this.sportteryHdcHome = sportteryHdcHome;
	}

	public String getSportteryHdcAway() {
		return sportteryHdcAway;
	}

	public void setSportteryHdcAway(String sportteryHdcAway) {
		this.sportteryHdcAway = sportteryHdcAway;
	}

	
	public String getSportteryHdcUpdate() {
		return sportteryHdcUpdate;
	}

	public void setSportteryHdcUpdate(String sportteryHdcUpdate) {
		this.sportteryHdcUpdate = sportteryHdcUpdate;
	}

	
	public String getSportteryHdcMargin() {
		return sportteryHdcMargin;
	}

	public void setSportteryHdcMargin(String sportteryHdcMargin) {
		this.sportteryHdcMargin = sportteryHdcMargin;
	}

	public String getSportteryHiloLines() {
		return sportteryHiloLines;
	}

	public void setSportteryHiloLines(String sportteryHiloLines) {
		this.sportteryHiloLines = sportteryHiloLines;
	}

	public String getSportteryHiloHigh() {
		return sportteryHiloHigh;
	}

	public void setSportteryHiloHigh(String sportteryHiloHigh) {
		this.sportteryHiloHigh = sportteryHiloHigh;
	}

	public String getSportteryHiloLow() {
		return sportteryHiloLow;
	}

	public void setSportteryHiloLow(String sportteryHiloLow) {
		this.sportteryHiloLow = sportteryHiloLow;
	}

	
	public String getSportteryHiloUpdate() {
		return sportteryHiloUpdate;
	}

	public void setSportteryHiloUpdate(String sportteryHiloUpdate) {
		this.sportteryHiloUpdate = sportteryHiloUpdate;
	}

	public String getSportteryHiloMargin() {
		return sportteryHiloMargin;
	}

	public void setSportteryHiloMargin(String sportteryHiloMargin) {
		this.sportteryHiloMargin = sportteryHiloMargin;
	}

	public String getCrownHdcLines() {
		return crownHdcLines;
	}

	public void setCrownHdcLines(String crownHdcLines) {
		this.crownHdcLines = crownHdcLines;
	}

	public String getCrownHdcHome() {
		return crownHdcHome;
	}

	public void setCrownHdcHome(String crownHdcHome) {
		this.crownHdcHome = crownHdcHome;
	}

	public String getCrownHdcAway() {
		return crownHdcAway;
	}

	public void setCrownHdcAway(String crownHdcAway) {
		this.crownHdcAway = crownHdcAway;
	}

	public String getCrownHdcUpdate() {
		return crownHdcUpdate;
	}

	public void setCrownHdcUpdate(String crownHdcUpdate) {
		this.crownHdcUpdate = crownHdcUpdate;
	}

	public String getCrownHdcMargin() {
		return crownHdcMargin;
	}

	public void setCrownHdcMargin(String crownHdcMargin) {
		this.crownHdcMargin = crownHdcMargin;
	}

	public String getCrownHiloLines() {
		return crownHiloLines;
	}

	public void setCrownHiloLines(String crownHiloLines) {
		this.crownHiloLines = crownHiloLines;
	}

	public String getCrownHiloHigh() {
		return crownHiloHigh;
	}

	public void setCrownHiloHigh(String crownHiloHigh) {
		this.crownHiloHigh = crownHiloHigh;
	}

	public String getCrownHiloLow() {
		return crownHiloLow;
	}

	public void setCrownHiloLow(String crownHiloLow) {
		this.crownHiloLow = crownHiloLow;
	}

	public String getCrownHiloUpdate() {
		return crownHiloUpdate;
	}

	public void setCrownHiloUpdate(String crownHiloUpdate) {
		this.crownHiloUpdate = crownHiloUpdate;
	}

	public String getCrownHiloMargin() {
		return crownHiloMargin;
	}

	public void setCrownHiloMargin(String crownHiloMargin) {
		this.crownHiloMargin = crownHiloMargin;
	}

	public String getBet365HdcLines() {
		return bet365HdcLines;
	}

	public void setBet365HdcLines(String bet365HdcLines) {
		this.bet365HdcLines = bet365HdcLines;
	}

	public String getBet365HdcHome() {
		return bet365HdcHome;
	}

	public void setBet365HdcHome(String bet365HdcHome) {
		this.bet365HdcHome = bet365HdcHome;
	}

	public String getBet365HdcAway() {
		return bet365HdcAway;
	}

	public void setBet365HdcAway(String bet365HdcAway) {
		this.bet365HdcAway = bet365HdcAway;
	}

	public String getBet365HdcUpdate() {
		return bet365HdcUpdate;
	}

	public void setBet365HdcUpdate(String bet365HdcUpdate) {
		this.bet365HdcUpdate = bet365HdcUpdate;
	}

	public String getBet365HdcMargin() {
		return bet365HdcMargin;
	}

	public void setBet365HdcMargin(String bet365HdcMargin) {
		this.bet365HdcMargin = bet365HdcMargin;
	}

	public String getBet365HiloLines() {
		return bet365HiloLines;
	}

	public void setBet365HiloLines(String bet365HiloLines) {
		this.bet365HiloLines = bet365HiloLines;
	}

	public String getBet365HiloHigh() {
		return bet365HiloHigh;
	}

	public void setBet365HiloHigh(String bet365HiloHigh) {
		this.bet365HiloHigh = bet365HiloHigh;
	}

	public String getBet365HiloLow() {
		return bet365HiloLow;
	}

	public void setBet365HiloLow(String bet365HiloLow) {
		this.bet365HiloLow = bet365HiloLow;
	}

	public String getBet365HiloUpdate() {
		return bet365HiloUpdate;
	}

	public void setBet365HiloUpdate(String bet365HiloUpdate) {
		this.bet365HiloUpdate = bet365HiloUpdate;
	}

	
	public String getBet365HiloMargin() {
		return bet365HiloMargin;
	}

	public void setBet365HiloMargin(String bet365HiloMargin) {
		this.bet365HiloMargin = bet365HiloMargin;
	}
	
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
