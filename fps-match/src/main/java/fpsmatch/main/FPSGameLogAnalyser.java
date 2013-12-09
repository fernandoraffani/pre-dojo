package fpsmatch.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fpsmatch.logrecord.BattleLogRecord;
import fpsmatch.logrecord.LogRecord;
import fpsmatch.logrecord.MatchLogRecord;
import fpsmatch.logrecord.MatchLogRecord.MatchStatus;
import fpsmatch.model.AchievementEvaluator;
import fpsmatch.model.LeaderboardGenerator;
import fpsmatch.model.PlayerRecordEvaluator;
import fpsmatch.parser.LogParser;
import fpsmatch.records.PlayerRecord;
import fpsmatch.records.PlayerSummary;

public class FPSGameLogAnalyser {

	public static void main(String[] args) throws IOException {
		LogParser parser = new LogParser();
		File file = new File(args[0]);
		String line;
		BufferedReader br = new BufferedReader(new FileReader(file));
		Map<String, PlayerRecord> playerRecords = new HashMap<String, PlayerRecord>();
		while ((line = br.readLine()) != null) {
			LogRecord logRecord = parser.parseLineLog(line);
			if (logRecord instanceof BattleLogRecord) {
				PlayerRecordEvaluator.updatePlayerRecords(
						(BattleLogRecord) logRecord, playerRecords);
				AchievementEvaluator
						.evaluateFiveKillsInOneMinuteAchievement(playerRecords
								.get(((BattleLogRecord) logRecord).getKiller()));
			}
			else{
				if(((MatchLogRecord) logRecord).getMatchStatus() == MatchStatus.FINISH){
					break;
				}
			}
		}
		br.close();
		for(PlayerSummary summary : LeaderboardGenerator.leaderboardGenerator(playerRecords)){
			System.out.println(summary);
		}
	}

}
