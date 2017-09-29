package com.spider.db.entity;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "basketball_odds", schema = "", catalog = "crawler")
public class BasketballOddsEntity {
	
	private Long id;
	
	private Integer europeId;
	
	private Long uniqueId;
	
	private Integer company;
	
	private String line;
	
	private Double oddsOne;
	
	private Double oddsTwo;
	
	private Integer oddsType;
	
	private String score;
	
	private String leftTime;
	
	private String state;
	
	private Timestamp updateTime = new Timestamp(System.currentTimeMillis());

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
    @Column(name = "europe_id")
	public Integer getEuropeId() {
		return europeId;
	}

	public void setEuropeId(Integer europeId) {
		this.europeId = europeId;
	}

	@Basic
    @Column(name = "line")
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Basic
    @Column(name = "odds_one")
	public Double getOddsOne() {
		return oddsOne;
	}

	public void setOddsOne(Double oddsOne) {
		this.oddsOne = oddsOne;
	}

	@Basic
    @Column(name = "odds_two")
	public Double getOddsTwo() {
		return oddsTwo;
	}

	public void setOddsTwo(Double oddsTwo) {
		this.oddsTwo = oddsTwo;
	}

	@Basic
    @Column(name = "update_time")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Basic
	@Column(name = "company")
	public Integer getCompany() {
		return company;
	}

	public void setCompany(Integer company) {
		this.company = company;
	}

	@Basic
	@Column(name = "score")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Basic
	@Column(name = "left_time")
	public String getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(String leftTime) {
		this.leftTime = leftTime;
	}

	@Basic
	@Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Basic
	@Column(name = "odds_type")
	public Integer getOddsType() {
		return oddsType;
	}

	public void setOddsType(Integer oddsType) {
		this.oddsType = oddsType;
	}
	
	@Basic
	@Column(name = "unique_id")
	public Long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
	}

	@Override
	public String toString() {
		return "BasketballOddsEntity [id=" + id + ", europeId=" + europeId
				+ ", company=" + company + ", line=" + line + ", oddsOne="
				+ oddsOne + ", oddsTwo=" + oddsTwo + ", oddsType=" + oddsType
				+ ", score=" + score + ", leftTime=" + leftTime + ", state="
				+ state + ", updateTime=" + updateTime + "unique_id=" + uniqueId + "]";
	}
	
}
