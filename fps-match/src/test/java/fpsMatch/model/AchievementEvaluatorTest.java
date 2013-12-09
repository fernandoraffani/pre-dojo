package fpsMatch.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import fpsmatch.model.AchievementEvaluator;
import fpsmatch.records.PlayerRecord;

public class AchievementEvaluatorTest {

	@Test
	public void testEvaluateFiveKillsInOneMinuteAchievementPlayerWithLessThanFiveKills() {
		PlayerRecord playerRecord = getPlayerRecordWithLessThanFiveKills();
		AchievementEvaluator.evaluateFiveKillsInOneMinuteAchievement(playerRecord);
		assertEquals(false, playerRecord.isFiveKillsInOneMinuteAchievement());
	}
	
	@Test
	public void testEvaluateFiveKillsInOneMinuteAchievementPlayerWithFiveKills() {
		PlayerRecord playerRecord = getPlayerRecordWithLessThanFiveKills();
		playerRecord.getLastKills().add(Calendar.getInstance());
		AchievementEvaluator.evaluateFiveKillsInOneMinuteAchievement(playerRecord);
		assertEquals(true, playerRecord.isFiveKillsInOneMinuteAchievement());
	}
	
	@Test
	public void testEvaluateFiveKillsInOneMinuteAchievementPlayerWithFiveKillsButNotInAMinute() {
		PlayerRecord playerRecord = getPlayerRecordWithFiveKillsInMoreThanAMinute();
		AchievementEvaluator.evaluateFiveKillsInOneMinuteAchievement(playerRecord);
		assertEquals(false, playerRecord.isFiveKillsInOneMinuteAchievement());
	}

	private PlayerRecord getPlayerRecordWithFiveKillsInMoreThanAMinute() {
		PlayerRecord playerRecord = new PlayerRecord();
		List<Calendar> kills = new ArrayList<Calendar>();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -2);
		kills.add(calendar);
		kills.add(Calendar.getInstance());
		kills.add(Calendar.getInstance());
		kills.add(Calendar.getInstance());
		kills.add(Calendar.getInstance());
		playerRecord.setLastKills(kills);
		return playerRecord;
	}

	private PlayerRecord getPlayerRecordWithLessThanFiveKills() {
		PlayerRecord playerRecord = new PlayerRecord();
		playerRecord.setLastKills(getFourLastKills());
		return playerRecord;
	}

	private List<Calendar> getFourLastKills() {
		List<Calendar> kills = new ArrayList<Calendar>();
		kills.add(Calendar.getInstance());
		kills.add(Calendar.getInstance());
		kills.add(Calendar.getInstance());
		kills.add(Calendar.getInstance());
		return kills;
	}

	@Test
	public void testEvaluatNoDeathWinnerAchievementWithDeaths() {
		assertEquals(false, AchievementEvaluator.evaluatNoDeathWinnerAchievement(1));
	}
	
	@Test
	public void testEvaluatNoDeathWinnerAchievementWithoutDeaths() {
		assertEquals(true, AchievementEvaluator.evaluatNoDeathWinnerAchievement(0));
	}

}
