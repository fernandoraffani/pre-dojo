package fpsMatch.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import fpsmatch.logrecord.BattleLogRecord;
import fpsmatch.model.PlayerRecordEvaluator;
import fpsmatch.records.PlayerRecord;
import fpsmatch.records.WeaponRecord;

public class PlayerRecordEvaluatorTest {
	

	@Test
	public void testUpdatePlayerRecordsCreateBothRecords() {
		Map<String, PlayerRecord> playerRecords = new HashMap<String, PlayerRecord>();
		BattleLogRecord battleRecord = new BattleLogRecord();
		Calendar eventDate = Calendar.getInstance();
		battleRecord.setDateEvent(eventDate);
		battleRecord.setKiller("John");
		battleRecord.setKilled("Joe");
		battleRecord.setWeapon("Gun");
		PlayerRecordEvaluator.updatePlayerRecords(battleRecord, playerRecords);
		assertEquals(2, playerRecords.size());
		assertEquals(1L, playerRecords.get("John").getKills().longValue());
		assertEquals(0L, playerRecords.get("John").getDeaths().longValue());
		assertEquals("John", playerRecords.get("John").getName());
		assertEquals(1, playerRecords.get("John").getLastKills().size());
		assertEquals(eventDate, playerRecords.get("John").getLastKills().get(0));
		assertEquals(1, playerRecords.get("John").getWeaponUsed().size());
		assertEquals(1, playerRecords.get("John").getWeaponUsed().get("Gun").getKills());
		assertEquals(0L, playerRecords.get("Joe").getKills().longValue());
		assertEquals(1L, playerRecords.get("Joe").getDeaths().longValue());
		assertEquals("Joe", playerRecords.get("Joe").getName());
		assertEquals(0, playerRecords.get("Joe").getLastKills().size());
		assertEquals(0, playerRecords.get("Joe").getWeaponUsed().size());
	}
	
	@Test
	public void testUpdatePlayerRecordsCreateRecordDeathByWorld() {
		Map<String, PlayerRecord> playerRecords = new HashMap<String, PlayerRecord>();
		BattleLogRecord battleRecord = new BattleLogRecord();
		Calendar eventDate = Calendar.getInstance();
		battleRecord.setDateEvent(eventDate);
		battleRecord.setKiller("<WORLD>");
		battleRecord.setKilled("Joe");
		battleRecord.setWeapon("Gun");
		PlayerRecordEvaluator.updatePlayerRecords(battleRecord, playerRecords);
		assertEquals(1, playerRecords.size());
		assertEquals(0L, playerRecords.get("Joe").getKills().longValue());
		assertEquals(1L, playerRecords.get("Joe").getDeaths().longValue());
		assertEquals("Joe", playerRecords.get("Joe").getName());
		assertEquals(0, playerRecords.get("Joe").getLastKills().size());
		assertEquals(0, playerRecords.get("Joe").getWeaponUsed().size());
	}
	
	@Test
	public void testUpdatePlayerRecordsKillerHasRecord() {
		Map<String, PlayerRecord> playerRecords = new HashMap<String, PlayerRecord>();
		playerRecords.put("John", getPlayerRecordWithKillsAndDeaths());
		
		BattleLogRecord battleRecord = new BattleLogRecord();
		Calendar eventDate = Calendar.getInstance();
		battleRecord.setDateEvent(eventDate);
		battleRecord.setKiller("John");
		battleRecord.setKilled("Joe");
		battleRecord.setWeapon("Gun");
		PlayerRecordEvaluator.updatePlayerRecords(battleRecord, playerRecords);
		assertEquals(2, playerRecords.size());
		assertEquals(4L, playerRecords.get("John").getKills().longValue());
		assertEquals(4L, playerRecords.get("John").getDeaths().longValue());
		assertEquals("John", playerRecords.get("John").getName());
		assertEquals(4, playerRecords.get("John").getLastKills().size());
		assertEquals(eventDate, playerRecords.get("John").getLastKills().get(3));
		assertEquals(2, playerRecords.get("John").getWeaponUsed().size());
		assertEquals(3, playerRecords.get("John").getWeaponUsed().get("Gun").getKills());
		assertEquals(1, playerRecords.get("John").getWeaponUsed().get("Rifle").getKills());
		assertEquals(0L, playerRecords.get("Joe").getKills().longValue());
		assertEquals(1L, playerRecords.get("Joe").getDeaths().longValue());
		assertEquals("Joe", playerRecords.get("Joe").getName());
		assertEquals(0, playerRecords.get("Joe").getLastKills().size());
		assertEquals(0, playerRecords.get("Joe").getWeaponUsed().size());
	}
	
	@Test
	public void testUpdatePlayerRecordsKilledHasRecord() {
		Map<String, PlayerRecord> playerRecords = new HashMap<String, PlayerRecord>();
		playerRecords.put("John", getPlayerRecordWithKillsAndDeaths());
		BattleLogRecord battleRecord = new BattleLogRecord();
		Calendar eventDate = Calendar.getInstance();
		battleRecord.setDateEvent(eventDate);
		battleRecord.setKiller("Joe");
		battleRecord.setKilled("John");
		battleRecord.setWeapon("Gun");
		PlayerRecordEvaluator.updatePlayerRecords(battleRecord, playerRecords);
		assertEquals(2, playerRecords.size());
		assertEquals(3L, playerRecords.get("John").getKills().longValue());
		assertEquals(5L, playerRecords.get("John").getDeaths().longValue());
		assertEquals("John", playerRecords.get("John").getName());
		assertEquals(3, playerRecords.get("John").getLastKills().size());
		assertEquals(2, playerRecords.get("John").getWeaponUsed().size());
		assertEquals(2, playerRecords.get("John").getWeaponUsed().get("Gun").getKills());
		assertEquals(1, playerRecords.get("John").getWeaponUsed().get("Rifle").getKills());
		assertEquals(1L, playerRecords.get("Joe").getKills().longValue());
		assertEquals(0L, playerRecords.get("Joe").getDeaths().longValue());
		assertEquals("Joe", playerRecords.get("Joe").getName());
		assertEquals(1, playerRecords.get("Joe").getLastKills().size());
		assertEquals(eventDate, playerRecords.get("Joe").getLastKills().get(0));
		assertEquals(1, playerRecords.get("Joe").getWeaponUsed().size());
		assertEquals(1, playerRecords.get("Joe").getWeaponUsed().get("Gun").getKills());
	}

	private PlayerRecord getPlayerRecordWithKillsAndDeaths() {
		PlayerRecord playerRecord = new PlayerRecord();
		playerRecord.setName("John");
		playerRecord.setKills(3);
		playerRecord.setDeaths(4);
		playerRecord.setLastKills(getKillsList());
		playerRecord.setWeaponUsed(getWeaponsUsed());
		return playerRecord;
	}

	private Map<String, WeaponRecord> getWeaponsUsed() {
		Map<String, WeaponRecord> records = new HashMap<String, WeaponRecord>();
		WeaponRecord weaponRecord1 = new WeaponRecord("Gun", 2);
		records.put("Gun", weaponRecord1);
		WeaponRecord weaponRecord2 = new WeaponRecord("Rifle", 1);
		records.put("Rifle", weaponRecord2);
		return records;
	}

	private List<Calendar> getKillsList() {
		List<Calendar> kills = new ArrayList<Calendar>();
		kills.add(Calendar.getInstance());
		kills.add(Calendar.getInstance());
		kills.add(Calendar.getInstance());
		return kills;
	}

	

}
