package com.spider.db.entity;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "basketball_sporttery", schema = "", catalog = "crawler")
public class BasketballSportteryEntity {

    private long id;

    private long uniqueId;

    private Timestamp startDate;

    private String matchCode;

    private String homeTeam;
    
    private String awayTeam;

    private String league;

    private Timestamp updateTime = new Timestamp(System.currentTimeMillis());;

    private Double hdcHome;
    
    private Double hdcAway;
    
    private Double hdcLine;
    
    private Double hiloHigh;
    
    private Double hiloLow;
    
    private Double hiloLine;
    
    private Double mnlHome;
    
    private Double mnlAway;
    
    private Double wnmHome15;
    
    private Double wnmHome610;
    
    private Double wnmHome1115;
    
    private Double wnmHome1620;
    
    private Double wnmHome2125;
    
    private Double wnmHome26Plus;
    
    private Double wnmAway15;
    
    private Double wnmAway610;
    
    private Double wnmAway1115;
    
    private Double wnmAway1620;
    
    private Double wnmAway2125;
    
    private Double wnmAway26Plus;

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
    @Column(name = "start_date")
	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
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
    @Column(name = "home_team")
	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	@Basic
    @Column(name = "away_team")
	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	@Basic
    @Column(name = "league")
	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
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
    @Column(name = "hdc_home")
	public Double getHdcHome() {
		return hdcHome;
	}

	public void setHdcHome(Double hdcHome) {
		this.hdcHome = hdcHome;
	}

	@Basic
    @Column(name = "hdc_away")
	public Double getHdcAway() {
		return hdcAway;
	}

	public void setHdcAway(Double hdcAway) {
		this.hdcAway = hdcAway;
	}

	@Basic
    @Column(name = "hdc_line")
	public Double getHdcLine() {
		return hdcLine;
	}

	public void setHdcLine(Double hdcLine) {
		this.hdcLine = hdcLine;
	}

	@Basic
    @Column(name = "hilo_high")
	public Double getHiloHigh() {
		return hiloHigh;
	}

	public void setHiloHigh(Double hiloHigh) {
		this.hiloHigh = hiloHigh;
	}

	@Basic
    @Column(name = "hilo_low")
	public Double getHiloLow() {
		return hiloLow;
	}

	public void setHiloLow(Double hiloLow) {
		this.hiloLow = hiloLow;
	}

	@Basic
    @Column(name = "hilo_line")
	public Double getHiloLine() {
		return hiloLine;
	}

	public void setHiloLine(Double hiloLine) {
		this.hiloLine = hiloLine;
	}

	@Basic
    @Column(name = "mnl_home")
	public Double getMnlHome() {
		return mnlHome;
	}

	public void setMnlHome(Double mnlHome) {
		this.mnlHome = mnlHome;
	}

	@Basic
    @Column(name = "mnl_away")
	public Double getMnlAway() {
		return mnlAway;
	}

	public void setMnlAway(Double mnlAway) {
		this.mnlAway = mnlAway;
	}

	@Basic
    @Column(name = "wnm_home_1_5")
	public Double getWnmHome15() {
		return wnmHome15;
	}

	public void setWnmHome15(Double wnmHome15) {
		this.wnmHome15 = wnmHome15;
	}

	@Basic
    @Column(name = "wnm_home_6_10")
	public Double getWnmHome610() {
		return wnmHome610;
	}

	public void setWnmHome610(Double wnmHome610) {
		this.wnmHome610 = wnmHome610;
	}

	@Basic
    @Column(name = "wnm_home_11_15")
	public Double getWnmHome1115() {
		return wnmHome1115;
	}

	public void setWnmHome1115(Double wnmHome1115) {
		this.wnmHome1115 = wnmHome1115;
	}

	@Basic
    @Column(name = "wnm_home_16_20")
	public Double getWnmHome1620() {
		return wnmHome1620;
	}

	public void setWnmHome1620(Double wnmHome1620) {
		this.wnmHome1620 = wnmHome1620;
	}

	@Basic
    @Column(name = "wnm_home_21_25")
	public Double getWnmHome2125() {
		return wnmHome2125;
	}

	public void setWnmHome2125(Double wnmHome2125) {
		this.wnmHome2125 = wnmHome2125;
	}

	@Basic
    @Column(name = "wnm_home_26plus")
	public Double getWnmHome26Plus() {
		return wnmHome26Plus;
	}

	public void setWnmHome26Plus(Double wnmHome26Plus) {
		this.wnmHome26Plus = wnmHome26Plus;
	}

	@Basic
    @Column(name = "wnm_away_1_5")
	public Double getWnmAway15() {
		return wnmAway15;
	}

	public void setWnmAway15(Double wnmAway15) {
		this.wnmAway15 = wnmAway15;
	}

	@Basic
    @Column(name = "wnm_away_6_10")
	public Double getWnmAway610() {
		return wnmAway610;
	}

	public void setWnmAway610(Double wnmAway610) {
		this.wnmAway610 = wnmAway610;
	}

	@Basic
	@Column(name = "wnm_away_11_15")
	public Double getWnmAway1115() {
		return wnmAway1115;
	}

	public void setWnmAway1115(Double wnmAway1115) {
		this.wnmAway1115 = wnmAway1115;
	}

	@Basic
    @Column(name = "wnm_away_16_20")
	public Double getWnmAway1620() {
		return wnmAway1620;
	}

	public void setWnmAway1620(Double wnmAway1620) {
		this.wnmAway1620 = wnmAway1620;
	}

	@Basic
    @Column(name = "wnm_away_21_25")
	public Double getWnmAway2125() {
		return wnmAway2125;
	}

	public void setWnmAway2125(Double wnmAway2125) {
		this.wnmAway2125 = wnmAway2125;
	}

	@Basic
    @Column(name = "wnm_away_26plus")
	public Double getWnmAway26Plus() {
		return wnmAway26Plus;
	}

	public void setWnmAway26Plus(Double wnmAway26Plus) {
		this.wnmAway26Plus = wnmAway26Plus;
	}

	@Override
	public String toString() {
		return "BasketballSportteryEntity [id=" + id + ", uniqueId=" + uniqueId
				+ ", startDate=" + startDate + ", matchCode=" + matchCode
				+ ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam
				+ ", league=" + league + ", updateTime=" + updateTime
				+ ", hdcHome=" + hdcHome + ", hdcAway=" + hdcAway
				+ ", hdcLine=" + hdcLine + ", hiloHome=" + hiloHigh
				+ ", hiloAway=" + hiloLow + ", hiloLine=" + hiloLine
				+ ", mnlHome=" + mnlHome + ", mnlAway=" + mnlAway
				+ ", wnmHome15=" + wnmHome15 + ", wnmHome610=" + wnmHome610
				+ ", wnmHome1115=" + wnmHome1115 + ", wnmHome1620="
				+ wnmHome1620 + ", wnmHome2125=" + wnmHome2125
				+ ", wnmHome26Plus=" + wnmHome26Plus + ", wnmAway15="
				+ wnmAway15 + ", wnmAway610=" + wnmAway610 + ", wnmAway1115="
				+ wnmAway1115 + ", wnmAway1620=" + wnmAway1620
				+ ", wnmAway2125=" + wnmAway2125 + ", wnmAway26Plus="
				+ wnmAway26Plus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((awayTeam == null) ? 0 : awayTeam.hashCode());
		result = prime * result + ((hdcAway == null) ? 0 : hdcAway.hashCode());
		result = prime * result + ((hdcHome == null) ? 0 : hdcHome.hashCode());
		result = prime * result + ((hdcLine == null) ? 0 : hdcLine.hashCode());
		result = prime * result
				+ ((hiloHigh == null) ? 0 : hiloHigh.hashCode());
		result = prime * result
				+ ((hiloLine == null) ? 0 : hiloLine.hashCode());
		result = prime * result + ((hiloLow == null) ? 0 : hiloLow.hashCode());
		result = prime * result
				+ ((homeTeam == null) ? 0 : homeTeam.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((league == null) ? 0 : league.hashCode());
		result = prime * result
				+ ((matchCode == null) ? 0 : matchCode.hashCode());
		result = prime * result + ((mnlAway == null) ? 0 : mnlAway.hashCode());
		result = prime * result + ((mnlHome == null) ? 0 : mnlHome.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + (int) (uniqueId ^ (uniqueId >>> 32));
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((wnmAway1115 == null) ? 0 : wnmAway1115.hashCode());
		result = prime * result
				+ ((wnmAway15 == null) ? 0 : wnmAway15.hashCode());
		result = prime * result
				+ ((wnmAway1620 == null) ? 0 : wnmAway1620.hashCode());
		result = prime * result
				+ ((wnmAway2125 == null) ? 0 : wnmAway2125.hashCode());
		result = prime * result
				+ ((wnmAway26Plus == null) ? 0 : wnmAway26Plus.hashCode());
		result = prime * result
				+ ((wnmAway610 == null) ? 0 : wnmAway610.hashCode());
		result = prime * result
				+ ((wnmHome1115 == null) ? 0 : wnmHome1115.hashCode());
		result = prime * result
				+ ((wnmHome15 == null) ? 0 : wnmHome15.hashCode());
		result = prime * result
				+ ((wnmHome1620 == null) ? 0 : wnmHome1620.hashCode());
		result = prime * result
				+ ((wnmHome2125 == null) ? 0 : wnmHome2125.hashCode());
		result = prime * result
				+ ((wnmHome26Plus == null) ? 0 : wnmHome26Plus.hashCode());
		result = prime * result
				+ ((wnmHome610 == null) ? 0 : wnmHome610.hashCode());
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
		BasketballSportteryEntity other = (BasketballSportteryEntity) obj;
		if (awayTeam == null) {
			if (other.awayTeam != null)
				return false;
		} else if (!awayTeam.equals(other.awayTeam))
			return false;
		if (hdcAway == null) {
			if (other.hdcAway != null)
				return false;
		} else if (!hdcAway.equals(other.hdcAway))
			return false;
		if (hdcHome == null) {
			if (other.hdcHome != null)
				return false;
		} else if (!hdcHome.equals(other.hdcHome))
			return false;
		if (hdcLine == null) {
			if (other.hdcLine != null)
				return false;
		} else if (!hdcLine.equals(other.hdcLine))
			return false;
		if (hiloHigh == null) {
			if (other.hiloHigh != null)
				return false;
		} else if (!hiloHigh.equals(other.hiloHigh))
			return false;
		if (hiloLine == null) {
			if (other.hiloLine != null)
				return false;
		} else if (!hiloLine.equals(other.hiloLine))
			return false;
		if (hiloLow == null) {
			if (other.hiloLow != null)
				return false;
		} else if (!hiloLow.equals(other.hiloLow))
			return false;
		if (homeTeam == null) {
			if (other.homeTeam != null)
				return false;
		} else if (!homeTeam.equals(other.homeTeam))
			return false;
		if (league == null) {
			if (other.league != null)
				return false;
		} else if (!league.equals(other.league))
			return false;
		if (matchCode == null) {
			if (other.matchCode != null)
				return false;
		} else if (!matchCode.equals(other.matchCode))
			return false;
		if (mnlAway == null) {
			if (other.mnlAway != null)
				return false;
		} else if (!mnlAway.equals(other.mnlAway))
			return false;
		if (mnlHome == null) {
			if (other.mnlHome != null)
				return false;
		} else if (!mnlHome.equals(other.mnlHome))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (uniqueId != other.uniqueId)
			return false;
		if (wnmAway1115 == null) {
			if (other.wnmAway1115 != null)
				return false;
		} else if (!wnmAway1115.equals(other.wnmAway1115))
			return false;
		if (wnmAway15 == null) {
			if (other.wnmAway15 != null)
				return false;
		} else if (!wnmAway15.equals(other.wnmAway15))
			return false;
		if (wnmAway1620 == null) {
			if (other.wnmAway1620 != null)
				return false;
		} else if (!wnmAway1620.equals(other.wnmAway1620))
			return false;
		if (wnmAway2125 == null) {
			if (other.wnmAway2125 != null)
				return false;
		} else if (!wnmAway2125.equals(other.wnmAway2125))
			return false;
		if (wnmAway26Plus == null) {
			if (other.wnmAway26Plus != null)
				return false;
		} else if (!wnmAway26Plus.equals(other.wnmAway26Plus))
			return false;
		if (wnmAway610 == null) {
			if (other.wnmAway610 != null)
				return false;
		} else if (!wnmAway610.equals(other.wnmAway610))
			return false;
		if (wnmHome1115 == null) {
			if (other.wnmHome1115 != null)
				return false;
		} else if (!wnmHome1115.equals(other.wnmHome1115))
			return false;
		if (wnmHome15 == null) {
			if (other.wnmHome15 != null)
				return false;
		} else if (!wnmHome15.equals(other.wnmHome15))
			return false;
		if (wnmHome1620 == null) {
			if (other.wnmHome1620 != null)
				return false;
		} else if (!wnmHome1620.equals(other.wnmHome1620))
			return false;
		if (wnmHome2125 == null) {
			if (other.wnmHome2125 != null)
				return false;
		} else if (!wnmHome2125.equals(other.wnmHome2125))
			return false;
		if (wnmHome26Plus == null) {
			if (other.wnmHome26Plus != null)
				return false;
		} else if (!wnmHome26Plus.equals(other.wnmHome26Plus))
			return false;
		if (wnmHome610 == null) {
			if (other.wnmHome610 != null)
				return false;
		} else if (!wnmHome610.equals(other.wnmHome610))
			return false;
		return true;
	}

}