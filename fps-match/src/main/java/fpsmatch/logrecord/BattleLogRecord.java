package fpsmatch.logrecord;

public class BattleLogRecord extends LogRecord {
	
	private String killer;
	private String killed;
	private String weapon;
	
	public String getKiller() {
		return killer;
	}
	public void setKiller(String killer) {
		this.killer = killer;
	}
	public String getKilled() {
		return killed;
	}
	public void setKilled(String killed) {
		this.killed = killed;
	}
	public String getWeapon() {
		return weapon;
	}
	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

}
