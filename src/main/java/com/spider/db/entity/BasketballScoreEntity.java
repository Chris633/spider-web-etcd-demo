package com.spider.db.entity;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "basketball_score", schema = "", catalog = "crawler")
public class BasketballScoreEntity {
	
	private Long id;
	
	private String matchCode;
	
	private Long uniqueId;
	
	private Integer europeId;
	
	private Timestamp startDate;
	
	private String leftTime;
	
	private String state;
	
	private String score;
	
	private String firstScore;
	
	private String secondScore;
	
	private String thirdScore;
	
	private String fourthScore;
	
	private String otScore;
	
	private Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

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
    @Column(name = "match_code")
	public String getMatchCode() {
		return matchCode;
	}

	public void setMatchCode(String matchCode) {
		this.matchCode = matchCode;
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
    @Column(name = "start_date")
	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
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
    @Column(name = "score")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Basic
    @Column(name = "1st_score")
	public String getFirstScore() {
		return firstScore;
	}

	public void setFirstScore(String firstScore) {
		this.firstScore = firstScore;
	}

	@Basic
    @Column(name = "2nd_score")
	public String getSecondScore() {
		return secondScore;
	}

	public void setSecondScore(String secondScore) {
		this.secondScore = secondScore;
	}

	@Basic
    @Column(name = "3rd_score")
	public String getThirdScore() {
		return thirdScore;
	}

	public void setThirdScore(String thirdScore) {
		this.thirdScore = thirdScore;
	}

	@Basic
    @Column(name = "4th_score")
	public String getFourthScore() {
		return fourthScore;
	}

	public void setFourthScore(String fourthScore) {
		this.fourthScore = fourthScore;
	}

	@Basic
    @Column(name = "ot_score")
	public String getOtScore() {
		return otScore;
	}

	public void setOtScore(String otScore) {
		this.otScore = otScore;
	}
	
	@Basic
    @Column(name = "update_time")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "BasketballScoreEntity [id=" + id + ", matchCode=" + matchCode
				+ ", uniqueId=" + uniqueId + ", europeId=" + europeId
				+ ", startDate=" + startDate + ", leftTime=" + leftTime
				+ ", state=" + state + ", score=" + score + ", firstScore="
				+ firstScore + ", secondScore=" + secondScore + ", thirdScore="
				+ thirdScore + ", fourthScore=" + fourthScore + ", otScore="
				+ otScore + ", updateTime=" + updateTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((europeId == null) ? 0 : europeId.hashCode());
		result = prime * result
				+ ((firstScore == null) ? 0 : firstScore.hashCode());
		result = prime * result
				+ ((fourthScore == null) ? 0 : fourthScore.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((leftTime == null) ? 0 : leftTime.hashCode());
		result = prime * result
				+ ((matchCode == null) ? 0 : matchCode.hashCode());
		result = prime * result + ((otScore == null) ? 0 : otScore.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result
				+ ((secondScore == null) ? 0 : secondScore.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result
				+ ((thirdScore == null) ? 0 : thirdScore.hashCode());
		result = prime * result
				+ ((uniqueId == null) ? 0 : uniqueId.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasketballScoreEntity other = (BasketballScoreEntity) obj;
		if (europeId == null) {
			if (other.europeId != null)
				return false;
		} else if (!europeId.equals(other.europeId))
			return false;
		if (firstScore == null) {
			if (other.firstScore != null)
				return false;
		} else if (!firstScore.equals(other.firstScore))
			return false;
		if (fourthScore == null) {
			if (other.fourthScore != null)
				return false;
		} else if (!fourthScore.equals(other.fourthScore))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (leftTime == null) {
			if (other.leftTime != null)
				return false;
		} else if (!leftTime.equals(other.leftTime))
			return false;
		if (matchCode == null) {
			if (other.matchCode != null)
				return false;
		} else if (!matchCode.equals(other.matchCode))
			return false;
		if (otScore == null) {
			if (other.otScore != null)
				return false;
		} else if (!otScore.equals(other.otScore))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (secondScore == null) {
			if (other.secondScore != null)
				return false;
		} else if (!secondScore.equals(other.secondScore))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (thirdScore == null) {
			if (other.thirdScore != null)
				return false;
		} else if (!thirdScore.equals(other.thirdScore))
			return false;
		if (uniqueId == null) {
			if (other.uniqueId != null)
				return false;
		} else if (!uniqueId.equals(other.uniqueId))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		return true;
	}
	
}
