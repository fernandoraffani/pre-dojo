package fpsmatch.records;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class PlayerRecord {

	private String name;
	private Integer kills;
	private Integer deaths;
	private Map<String, WeaponRecord> weaponUsed;
	private List<Calendar> lastKills;
	private boolean fiveKillsInOneMinuteAchievement;
	private boolean noDeathWinnerAchievement;
	private Integer killingStreak = 0;
	private Integer bestKillingStreak = 0;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getKills() {
		return kills;
	}

	public void setKills(Integer kills) {
		this.kills = kills;
	}

	public Integer getDeaths() {
		return deaths;
	}

	public void setDeaths(Integer deaths) {
		this.deaths = deaths;
	}

	public Map<String, WeaponRecord> getWeaponUsed() {
		return weaponUsed;
	}

	public void setWeaponUsed(Map<String, WeaponRecord> weaponUsed) {
		this.weaponUsed = weaponUsed;
	}

	public List<Calendar> getLastKills() {
		return lastKills;
	}

	public void setLastKills(List<Calendar> lastKills) {
		this.lastKills = lastKills;
	}

	public boolean isFiveKillsInOneMinuteAchievement() {
		return fiveKillsInOneMinuteAchievement;
	}

	public void setFiveKillsInOneMinuteAchievement(
			boolean fiveKillsInOneMinuteAchievement) {
		this.fiveKillsInOneMinuteAchievement = fiveKillsInOneMinuteAchievement;
	}

	public boolean isNoDeathWinnerAchievement() {
		return noDeathWinnerAchievement;
	}

	public void setNoDeathWinnerAchievement(boolean noDeathWinnerAchievement) {
		this.noDeathWinnerAchievement = noDeathWinnerAchievement;
	}

	public int compareTo(PlayerRecord o1) {
		return new Integer(kills - deaths).compareTo(new Integer(o1.getKills() - o1.getDeaths()));
	}

	public Integer getKillingStreak() {
		return killingStreak;
	}

	public void setKillingStreak(Integer killingStreak) {
		this.killingStreak = killingStreak;
	}

	public Integer getBestKillingStreak() {
		return bestKillingStreak;
	}

	public void setBestKillingStreak(Integer bestKillingStreak) {
		this.bestKillingStreak = bestKillingStreak;
	}



}
