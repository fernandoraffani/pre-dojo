package fpsmatch.model;

import java.util.Calendar;
import java.util.List;

import fpsmatch.records.PlayerRecord;

public class AchievementEvaluator {

	private static long minute = 60000l;

	public static void evaluateFiveKillsInOneMinuteAchievement(
			PlayerRecord playerRecord) {
		if (playerRecord != null) {
			List<Calendar> lastKills = playerRecord.getLastKills();
			if (lastKills.size() >= 5
					&& isLessThanOneMinute(lastKills.get(lastKills.size() - 1),
							lastKills.get(lastKills.size() - 5))) {
				playerRecord.setFiveKillsInOneMinuteAchievement(true);
			}
		}
	}

	private static boolean isLessThanOneMinute(Calendar lastKillTime,
			Calendar fifthLastKillTime) {
		return (lastKillTime.getTimeInMillis() - fifthLastKillTime
				.getTimeInMillis()) < minute;

	}

	public static boolean evaluatNoDeathWinnerAchievement(Integer deaths) {
		return deaths == 0;
	}

}
