package com.spider.db.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wsy on 2016/1/8.
 */
@Entity
@Table(name = "manual_matches", schema = "", catalog = "crawler")
public class ManualMatchesEntity {
	
	private Long id;
	
    private String europeId;

    private Long uniqueId;
    
    private String w500Id;

	private String durationTime = "";
	
	private String half = "";
	
	private String score;
	
	private String halfScore;
	
	private Integer homeRedcard;
	
	private Integer awayRedcard;

    
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
    public String getEuropeId() {

        return europeId;
    }

    public void setEuropeId(String europeId) {

        this.europeId = europeId;
    }
    
    @Basic
    @Column(name = "unique_id")
    public Long getUniqueId() {

        return uniqueId;
    }

    public void setUniqueId(Long uniqueId) {

        this.uniqueId = uniqueId;
    }
    
    @Column(name = "w500_id")
	public String getW500Id() {
		return w500Id;
	}

	public void setW500Id(String w500Id) {
		this.w500Id = w500Id;
	}
    
    @Column(name = "duration_time", length = 10)
    public String getDurationTime() {

        return durationTime  ;
    }

    public void setDurationTime(String durationTime) {

        this.durationTime = durationTime;
    }
    
    @Column(name = "half", length = 10)
    public String getHalf() {
		return half;
	}

	public void setHalf(String half) {
		this.half = half;
	}

	@Column(name = "score", length = 10)
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Column(name = "half_score", length = 10)
	public String getHalfScore() {
		return halfScore;
	}

	public void setHalfScore(String halfScore) {
		this.halfScore = halfScore;
	}

	@Column(name = "home_redcard", length = 10)
	public Integer getHomeRedcard() {
		return homeRedcard;
	}

	public void setHomeRedcard(Integer homeRedcard) {
		this.homeRedcard = homeRedcard;
	}

	@Column(name = "away_redcard", length = 10)
	public Integer getAwayRedcard() {
		return awayRedcard;
	}

	public void setAwayRedcard(Integer awayRedcard) {
		this.awayRedcard = awayRedcard;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((awayRedcard == null) ? 0 : awayRedcard.hashCode());
		result = prime * result
				+ ((durationTime == null) ? 0 : durationTime.hashCode());
		result = prime * result
				+ ((europeId == null) ? 0 : europeId.hashCode());
		result = prime * result + ((half == null) ? 0 : half.hashCode());
		result = prime * result
				+ ((halfScore == null) ? 0 : halfScore.hashCode());
		result = prime * result
				+ ((homeRedcard == null) ? 0 : homeRedcard.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result
				+ ((uniqueId == null) ? 0 : uniqueId.hashCode());
		result = prime * result + ((w500Id == null) ? 0 : w500Id.hashCode());
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
		ManualMatchesEntity other = (ManualMatchesEntity) obj;
		if (awayRedcard == null) {
			if (other.awayRedcard != null)
				return false;
		} else if (!awayRedcard.equals(other.awayRedcard))
			return false;
		if (durationTime == null) {
			if (other.durationTime != null)
				return false;
		} else if (!durationTime.equals(other.durationTime))
			return false;
		if (europeId == null) {
			if (other.europeId != null)
				return false;
		} else if (!europeId.equals(other.europeId))
			return false;
		if (half == null) {
			if (other.half != null)
				return false;
		} else if (!half.equals(other.half))
			return false;
		if (halfScore == null) {
			if (other.halfScore != null)
				return false;
		} else if (!halfScore.equals(other.halfScore))
			return false;
		if (homeRedcard == null) {
			if (other.homeRedcard != null)
				return false;
		} else if (!homeRedcard.equals(other.homeRedcard))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (uniqueId == null) {
			if (other.uniqueId != null)
				return false;
		} else if (!uniqueId.equals(other.uniqueId))
			return false;
		if (w500Id == null) {
			if (other.w500Id != null)
				return false;
		} else if (!w500Id.equals(other.w500Id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ManualMatchesEntity [id=" + id + ", europeId=" + europeId
				+ ", uniqueId=" + uniqueId + ", w500Id=" + w500Id
				+ ", durationTime=" + durationTime + ", half=" + half
				+ ", score=" + score + ", halfScore=" + halfScore
				+ ", homeRedcard=" + homeRedcard + ", awayRedcard="
				+ awayRedcard + "]";
	}

}
