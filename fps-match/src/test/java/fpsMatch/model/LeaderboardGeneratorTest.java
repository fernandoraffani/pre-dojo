package fpsMatch.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import fpsmatch.model.LeaderboardGenerator;
import fpsmatch.records.PlayerRecord;
import fpsmatch.records.PlayerSummary;
import fpsmatch.records.WeaponRecord;

public class LeaderboardGeneratorTest {

	@Test
	public void testLeaderboardGeneratorWithTwoRecords() {
		List<PlayerSummary> playerSummaries = LeaderboardGenerator.leaderboardGenerator(getTwoPlayerRecords());
		assertEquals(2, playerSummaries.size());
		assertEquals("Joe", playerSummaries.get(0).getName());
		assertEquals(getJoeRecord(), playerSummaries.get(0).toString());
		assertEquals("John", playerSummaries.get(1).getName());
		assertEquals(getJohnRecord(), playerSummaries.get(1).toString());
	}

	

	private Map<String, PlayerRecord> getTwoPlayerRecords() {
		PlayerRecord playerRecord1 = getPlayerRecord1();
		PlayerRecord playerRecord2 = getPlayerRecord2();
		Map<String, PlayerRecord> playerRecords = new HashMap<String, PlayerRecord>();
		playerRecords.put(playerRecord1.getName(), playerRecord1);
		playerRecords.put(playerRecord2.getName(), playerRecord2);
		return playerRecords;
	}

	private PlayerRecord getPlayerRecord2() {
		PlayerRecord playerRecord = new PlayerRecord();
		playerRecord.setName("John");
		playerRecord.setDeaths(2);
		playerRecord.setKills(3);
		playerRecord.setNoDeathWinnerAchievement(false);
		playerRecord.setFiveKillsInOneMinuteAchievement(false);
		playerRecord.setWeaponUsed(getWeaponUsed2());
		return playerRecord;
	}

	private Map<String, WeaponRecord> getWeaponUsed2() {
		Map<String, WeaponRecord> weaponRecords = new HashMap<String, WeaponRecord>();
		weaponRecords.put("Gun", new WeaponRecord("Gun", 2));
		weaponRecords.put("Rifle", new WeaponRecord("Rifle", 1));
		return weaponRecords;
	}

	private PlayerRecord getPlayerRecord1() {
		PlayerRecord playerRecord = new PlayerRecord();
		playerRecord.setName("Joe");
		playerRecord.setDeaths(1);
		playerRecord.setKills(4);
		playerRecord.setNoDeathWinnerAchievement(false);
		playerRecord.setFiveKillsInOneMinuteAchievement(false);
		playerRecord.setWeaponUsed(getWeaponUsed1());
		return playerRecord;

	}
	
	private Map<String, WeaponRecord> getWeaponUsed1() {
		Map<String, WeaponRecord> weaponRecords = new HashMap<String, WeaponRecord>();
		weaponRecords.put("Gun", new WeaponRecord("Gun", 2));
		weaponRecords.put("Rifle", new WeaponRecord("Rifle", 2));
		return weaponRecords;
	}
	
	private Object getJoeRecord() {
		return "PlayerSummary [place=1. name=Joe | kills=4 | deaths=1| killingStreak=0 | preferredWeapon=[Gun, Rifle] | fiveKillsInOneMinuteAchievement=false | noDeathWinnerAchievement=false | ]";
	}
	
	private Object getJohnRecord() {
		return "PlayerSummary [place=2. name=John | kills=3 | deaths=2| killingStreak=0 | preferredWeapon=[Gun] | fiveKillsInOneMinuteAchievement=false | noDeathWinnerAchievement=false | ]";
	}

}
