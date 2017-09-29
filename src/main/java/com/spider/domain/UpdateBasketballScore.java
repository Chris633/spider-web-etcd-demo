package com.spider.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import com.spider.db.entity.BasketballScoreEntity;

public class UpdateBasketballScore implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String matchCode;
	
	private Long uniqueId;
	
	private Timestamp startDate;
	
	private String leftTime;
	
	private String state;
	
	private String score;
	
	private String firstScore;
	
	private String secondScore;
	
	private String thirdScore;
	
	private String fourthScore;
	
	private String otScore;
	
	private Timestamp updateTime;
	
	public UpdateBasketballScore() {
		
	}
	
	public UpdateBasketballScore(BasketballScoreEntity basketballScoreEntity) {
		this.matchCode = basketballScoreEntity.getMatchCode();
		this.uniqueId = basketballScoreEntity.getUniqueId();
		this.startDate = basketballScoreEntity.getStartDate();
		this.leftTime = basketballScoreEntity.getLeftTime();
		this.score = basketballScoreEntity.getScore();
		this.state = basketballScoreEntity.getState();
		this.firstScore = basketballScoreEntity.getFirstScore();
		this.secondScore = basketballScoreEntity.getSecondScore();
		this.thirdScore = basketballScoreEntity.getThirdScore();
		this.fourthScore = basketballScoreEntity.getFourthScore();
		this.updateTime = basketballScoreEntity.getUpdateTime();
		this.setOtScore(basketballScoreEntity.getOtScore());
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

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public String getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(String leftTime) {
		this.leftTime = leftTime;
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

	public String getFirstScore() {
		return firstScore;
	}

	public void setFirstScore(String firstScore) {
		this.firstScore = firstScore;
	}

	public String getSecondScore() {
		return secondScore;
	}

	public void setSecondScore(String secondScore) {
		this.secondScore = secondScore;
	}

	public String getThirdScore() {
		return thirdScore;
	}

	public void setThirdScore(String thirdScore) {
		this.thirdScore = thirdScore;
	}

	public String getFourthScore() {
		return fourthScore;
	}

	public void setFourthScore(String fourthScore) {
		this.fourthScore = fourthScore;
	}
	
	public String getOtScore() {
		return otScore;
	}

	public void setOtScore(String otScore) {
		this.otScore = otScore;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "UpdateBasketballScore [matchCode=" + matchCode + ", uniqueId="
				+ uniqueId + ", startDate=" + startDate + ", leftTime="
				+ leftTime + ", state=" + state + ", score=" + score
				+ ", firstScore=" + firstScore + ", secondScore=" + secondScore
				+ ", thirdScore=" + thirdScore + ", fourthScore=" + fourthScore
				+ ", updateTime=" + updateTime + "]";
	}

}
