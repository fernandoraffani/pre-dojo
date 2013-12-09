package fpsmatch.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import fpsmatch.records.PlayerRecord;
import fpsmatch.records.PlayerSummary;
import fpsmatch.records.WeaponRecord;

public class LeaderboardGenerator {
	
	public static List<PlayerSummary> leaderboardGenerator(Map<String, PlayerRecord> gameRecord ){
		updateBestKillingStreaks(gameRecord);
		List<PlayerRecord> playerRecords = new ArrayList<PlayerRecord>(gameRecord.values());
		Collections.sort(playerRecords, new Comparator<PlayerRecord>() {

		    @Override
		    public int compare(PlayerRecord o1, PlayerRecord o2) {
		    	return o2.compareTo(o1);
		    }
		});
		return createPlayerSummaries(playerRecords);
	}

	private static void updateBestKillingStreaks(
			Map<String, PlayerRecord> gameRecord) {
		for(String key : gameRecord.keySet()){
			PlayerRecord playerRecord = gameRecord.get(key);
			if(playerRecord.getBestKillingStreak() < playerRecord.getKillingStreak())
				playerRecord.setBestKillingStreak(playerRecord.getKillingStreak());
			gameRecord.put(key, playerRecord);
		}
		
	}

	private static List<PlayerSummary> createPlayerSummaries(
			List<PlayerRecord> playerRecords) {
		List<PlayerSummary> leaderboard = new ArrayList<PlayerSummary>();
		int place = 1;
		for(PlayerRecord playerRecord : playerRecords){
			leaderboard.add(convertPlayerRecord(playerRecord, place));
			place++;
		}
		
		return leaderboard;
	}

	private static PlayerSummary convertPlayerRecord(PlayerRecord playerRecord,
			int place) {
		PlayerSummary playerSummary = new PlayerSummary();
		playerSummary.setDeaths(playerRecord.getDeaths());
		playerSummary.setKills(playerRecord.getKills());
		playerSummary.setName(playerRecord.getName());
		playerSummary.setFiveKillsInOneMinuteAchievement(playerRecord.isFiveKillsInOneMinuteAchievement());
		playerSummary.setPreferredWeapon(calculatePreferredWeapon(playerRecord.getWeaponUsed()));
		if(place == 1){
			playerSummary.setNoDeathWinnerAchievement(AchievementEvaluator.evaluatNoDeathWinnerAchievement(playerRecord.getDeaths()));
		}
		playerSummary.setPlace(place);
		playerSummary.setKillingStreak(playerRecord.getBestKillingStreak());
		return playerSummary;
	}

	private static List<String> calculatePreferredWeapon(
			Map<String, WeaponRecord> weaponUsed) {
		List<String> weapons = new ArrayList<String>();
		List<WeaponRecord> weaponRecords = new ArrayList<WeaponRecord>(weaponUsed.values());
		Collections.sort(weaponRecords, new Comparator<WeaponRecord>() {

		    @Override
		    public int compare(WeaponRecord o1, WeaponRecord o2) {
		    	return new Integer(o2.getKills()).compareTo(o1.getKills());
		    }
		});
		for(WeaponRecord record : weaponRecords){
			if(record.getKills() == weaponRecords.get(0).getKills()){
				weapons.add(record.getWeapon());
			}
		}
		return weapons;
	}

}
