package fpsmatch.records;

public class WeaponRecord {
	
	private String weapon;
	private int kills;
	
	public WeaponRecord(String weapon, int kills) {
		this.weapon = weapon;
		this.kills = kills;
	}
	public String getWeapon() {
		return weapon;
	}
	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}
	public int getKills() {
		return kills;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}

}
