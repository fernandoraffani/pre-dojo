package fpsmatch.records;

import java.util.List;

public class PlayerSummary {
	
	private String name;
	private int kills;
	private int deaths;
	private List<String> preferredWeapon;
	private boolean fiveKillsInOneMinuteAchievement;
	private boolean noDeathWinnerAchievement;
	private int place;
	private int killingStreak;
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKills() {
		return kills;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
	public int getDeaths() {
		return deaths;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	public List<String> getPreferredWeapon() {
		return preferredWeapon;
	}
	public void setPreferredWeapon(List<String> preferredWeapon) {
		this.preferredWeapon = preferredWeapon;
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
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	@Override
	public String toString() {
		return "PlayerSummary [place=" + place + ". name=" + name + " | kills=" + kills + " | deaths="
				+ deaths + "| killingStreak=" + killingStreak + " | preferredWeapon=" + preferredWeapon
				+ " | fiveKillsInOneMinuteAchievement="
				+ fiveKillsInOneMinuteAchievement
				+ " | noDeathWinnerAchievement=" + noDeathWinnerAchievement
				+ " | ]";
	}
	public int getKillingStreak() {
		return killingStreak;
	}
	public void setKillingStreak(int killingStreak) {
		this.killingStreak = killingStreak;
	}
	
	
	

}
