package com.spider.manager.model;

public class BasketballProductModel {
	
	private Long id;
	
	private String matchDate;
	
	private String matchCode;
	
	private String matchLeague;
	
	private String homeTeam;
	
	private String awayTeam;
	
	private String hdcLines;
	
	private String hdc;
	
	private String hiloLines;
	
	private String hilo;
	
	private String mnl;
	
	private String wnm;
	
	private String absenceState = MatchModel.ABSENCE_STATE_DEFAULT;

	public BasketballProductModel() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
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

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getHdcLines() {
		return hdcLines;
	}

	public void setHdcLines(String hdcLines) {
		this.hdcLines = hdcLines;
	}

	public String getHdc() {
		return hdc;
	}

	public void setHdc(String hdc) {
		this.hdc = hdc;
	}

	public String getHiloLines() {
		return hiloLines;
	}

	public void setHiloLines(String hiloLines) {
		this.hiloLines = hiloLines;
	}

	public String getHilo() {
		return hilo;
	}

	public void setHilo(String hilo) {
		this.hilo = hilo;
	}

	public String getMnl() {
		return mnl;
	}

	public void setMnl(String mnl) {
		this.mnl = mnl;
	}

	public String getWnm() {
		return wnm;
	}

	public void setWnm(String wnm) {
		this.wnm = wnm;
	}

	public String getAbsenceState() {
		return absenceState;
	}

	public void setAbsenceState(String absenceState) {
		this.absenceState = absenceState;
	}

}
