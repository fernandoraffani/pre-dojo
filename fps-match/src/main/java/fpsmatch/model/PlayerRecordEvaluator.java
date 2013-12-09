package fpsmatch.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fpsmatch.logrecord.BattleLogRecord;
import fpsmatch.records.PlayerRecord;
import fpsmatch.records.WeaponRecord;

public class PlayerRecordEvaluator {

	private static String world = "<WORLD>";

	public static void updatePlayerRecords(BattleLogRecord logRecord,
			Map<String, PlayerRecord> playerRecords) {
		if (!logRecord.getKiller().equals(world)) {
			PlayerRecord killerPlayerRecord = playerRecords.get(logRecord
					.getKiller());
			if (killerPlayerRecord == null) {
				playerRecords.put(logRecord.getKiller(),
						createKillerRecord(logRecord));
			} else {
				updateKillerRecord(killerPlayerRecord, logRecord);
			}
		}
		PlayerRecord killedPlayerRecord = playerRecords.get(logRecord
				.getKilled());
		if (killedPlayerRecord == null) {
			playerRecords.put(logRecord.getKilled(),
					createKilledRecord(logRecord));
		} else {
			killedPlayerRecord.setDeaths(killedPlayerRecord.getDeaths() + 1);
			if(killedPlayerRecord.getKillingStreak() > killedPlayerRecord.getBestKillingStreak())
				killedPlayerRecord.setBestKillingStreak(killedPlayerRecord.getKillingStreak());
			killedPlayerRecord.setKillingStreak(0);
		}

	}

	private static void updateKillerRecord(PlayerRecord killerPlayerRecord,
			BattleLogRecord logRecord) {
		killerPlayerRecord.setKills(killerPlayerRecord.getKills() + 1);
		killerPlayerRecord.setKillingStreak(killerPlayerRecord.getKillingStreak() + 1);
		killerPlayerRecord.setWeaponUsed(updateWeapons(
				killerPlayerRecord.getWeaponUsed(), logRecord.getWeapon()));
		killerPlayerRecord.setLastKills(updateLastKills(
				killerPlayerRecord.getLastKills(), logRecord.getDateEvent()));
	}

	private static PlayerRecord createKillerRecord(BattleLogRecord logRecord) {
		PlayerRecord playerRecord = new PlayerRecord();
		playerRecord.setName(logRecord.getKiller());
		playerRecord.setDeaths(0);
		playerRecord.setKills(1);
		playerRecord.setKillingStreak(0);
		playerRecord.setWeaponUsed(updateWeapons(
				new HashMap<String, WeaponRecord>(), logRecord.getWeapon()));
		playerRecord.setLastKills(updateLastKills(new ArrayList<Calendar>(),
				logRecord.getDateEvent()));
		playerRecord.setKillingStreak(1);
		return playerRecord;
	}

	private static Map<String, WeaponRecord> updateWeapons(
			Map<String, WeaponRecord> weaponUsed, String weapon) {
		WeaponRecord weaponRecord = weaponUsed.get(weapon);
		if (weaponRecord == null)
			weaponUsed.put(weapon, new WeaponRecord(weapon, 1));
		else{
			weaponRecord.setKills(weaponRecord.getKills() + 1);
			weaponUsed.put(weapon, weaponRecord);
		}
		return weaponUsed;
	}

	private static List<Calendar> updateLastKills(List<Calendar> lastKills,
			Calendar event) {
		lastKills.add(event);
		return lastKills;
	}
	
	private static PlayerRecord createKilledRecord(BattleLogRecord logRecord) {
		PlayerRecord playerRecord = new PlayerRecord();
		playerRecord.setName(logRecord.getKilled());
		playerRecord.setDeaths(1);
		playerRecord.setKills(0);
		playerRecord.setKillingStreak(0);
		playerRecord.setWeaponUsed(new HashMap<String, WeaponRecord>());
		playerRecord.setLastKills(new ArrayList<Calendar>());
		return playerRecord;
	}

}
