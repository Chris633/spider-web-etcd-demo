package com.spider.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import com.spider.db.entity.BasketballOddsEntity;

public class UpdateBasketballOdds implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String matchCode;
	
	private Long uniqueId;
	
	private String company;
	
	private String state;
	
	private String score;
	
	private String leftTime;
	
	private String hiloLine;
	
	private Double hiloHigh;
	
	private Double hiloLow;
	
	private String hdcLine;
	
	private Double hdcHome;
	
	private Double hdcAway;
	
	private Timestamp updateTime;
	
	private static enum Company {
		
		Crown("lj", 3),
		Bet365("jbb", 8);
		
		private String name;
		
		private Integer num;

		private Company(String name, Integer num) {
			this.name = name;
			this.num = num;
		}
		
		public static String getName(Integer num) {
			Company[] companies = Company.values();
			for (int i = 0; i < companies.length; i++) {
				if (companies[i].num == num) {
					return companies[i].name;
				}
			}
			return null;
		}
	}
	
	public UpdateBasketballOdds() {
		
	}
	
	public UpdateBasketballOdds(BasketballOddsEntity basketballOddsEntity) {
		this.matchCode = String.valueOf(basketballOddsEntity.getUniqueId()).substring(8, 12);
		this.uniqueId = basketballOddsEntity.getUniqueId();
		this.company = Company.getName(basketballOddsEntity.getCompany());
		this.state = basketballOddsEntity.getState();
		this.score = basketballOddsEntity.getScore();
		this.leftTime = basketballOddsEntity.getLeftTime();
		
		if (basketballOddsEntity.getOddsType().equals(0)) {
			this.hdcLine = basketballOddsEntity.getLine();
			this.hdcHome = basketballOddsEntity.getOddsOne();
			this.hdcAway = basketballOddsEntity.getOddsTwo();
		} else if (basketballOddsEntity.getOddsType().equals(1)) {
			this.hiloLine = basketballOddsEntity.getLine();
			this.hiloHigh = basketballOddsEntity.getOddsOne();
			this.hiloLow = basketballOddsEntity.getOddsTwo();
		}
		
		this.updateTime = basketballOddsEntity.getUpdateTime();
	}
	
	@Override
	public String toString() {
		return "UpdateBasketballOdds [matchCode=" + matchCode + ", uniqueId="
				+ uniqueId + ", company=" + company + ", state=" + state
				+ ", score=" + score + ", leftTime=" + leftTime + ", hiloLine="
				+ hiloLine + ", hiloHigh=" + hiloHigh + ", hiloLow=" + hiloLow
				+ ", hdcLine=" + hdcLine + ", hdcHome=" + hdcHome
				+ ", hdcAway=" + hdcAway + ", updateTime=" + updateTime + "]";
	}

	public String getMatchCode() {
		return matchCode;
	}

	public void setMatchCode(String matchCode) {
		this.matchCode = matchCode;
	}

	public Long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(String leftTime) {
		this.leftTime = leftTime;
	}

	public String getHiloLine() {
		return hiloLine;
	}

	public void setHiloLine(String hiloLine) {
		this.hiloLine = hiloLine;
	}

	public Double getHiloHigh() {
		return hiloHigh;
	}

	public void setHiloHigh(Double hiloHigh) {
		this.hiloHigh = hiloHigh;
	}

	public Double getHiloLow() {
		return hiloLow;
	}

	public void setHiloLow(Double hiloLow) {
		this.hiloLow = hiloLow;
	}

	public String getHdcLine() {
		return hdcLine;
	}

	public void setHdcLine(String hdcLine) {
		this.hdcLine = hdcLine;
	}

	public Double getHdcHome() {
		return hdcHome;
	}

	public void setHdcHome(Double hdcHome) {
		this.hdcHome = hdcHome;
	}

	public Double getHdcAway() {
		return hdcAway;
	}

	public void setHdcAway(Double hdcAway) {
		this.hdcAway = hdcAway;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
