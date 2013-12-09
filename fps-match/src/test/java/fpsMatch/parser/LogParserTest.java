package fpsMatch.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fpsmatch.exception.ValidationException;
import fpsmatch.logrecord.BattleLogRecord;
import fpsmatch.logrecord.LogRecord;
import fpsmatch.logrecord.MatchLogRecord;
import fpsmatch.logrecord.MatchLogRecord.MatchStatus;
import fpsmatch.parser.LogParser;

public class LogParserTest {
	
	private LogParser logParser = new LogParser();

	@Test(expected = ValidationException.class)
	public void testParseLineLogEmptyLine() {
		logParser.parseLineLog("");
	}
	
	@Test(expected = ValidationException.class)
	public void testParseLineLogIncompleteLine() {
		logParser.parseLineLog("23/04/2013 15:34:22 - New match 11348965 ");
	}
	
	@Test(expected = ValidationException.class)
	public void testParseLineLogLineTooLong() {
		logParser.parseLineLog("23/04/2013 15:34:22 - New match 11348965 has started now");
	}
	
	@Test(expected = ValidationException.class)
	public void testParseLineLogLineWrongDate() {
		logParser.parseLineLog("23/04/213 15:34:22 - New match 11348965 has started");
	}
	
	@Test(expected = ValidationException.class)
	public void testParseLineLogLineWrongHour() {
		logParser.parseLineLog("23/04/2013 1534:22 - New match 11348965 has started");
	}
	
	@Test
	public void testParseLineLogMatchStart() {
		LogRecord logRecord = logParser.parseLineLog("23/04/2013 15:34:22 - New match 11348965 has started");
		assertEquals(MatchLogRecord.class, logRecord.getClass());
		assertEquals(11348965, ((MatchLogRecord) logRecord).getMatchId());
		assertEquals(MatchStatus.START, ((MatchLogRecord) logRecord).getMatchStatus());
	}
	
	@Test
	public void testParseLineLogMatchEnd() {
		LogRecord logRecord = logParser.parseLineLog("23/04/2013 15:34:22 - Match 11348965 has ended");
		assertEquals(MatchLogRecord.class, logRecord.getClass());
		assertEquals(11348965, ((MatchLogRecord) logRecord).getMatchId());
		assertEquals(MatchStatus.FINISH, ((MatchLogRecord) logRecord).getMatchStatus());
	}
	
	@Test
	public void testParseLineLogBattleLog() {
		LogRecord logRecord = logParser.parseLineLog("23/04/2013 15:34:22 - Match killed Zack using Gun");
		assertEquals(BattleLogRecord.class, logRecord.getClass());
		assertEquals("Match", ((BattleLogRecord) logRecord).getKiller());
		assertEquals("Zack", ((BattleLogRecord) logRecord).getKilled());
		assertEquals("Gun", ((BattleLogRecord) logRecord).getWeapon());
	}
	
	@Test
	public void testParseLineLogBattleLogDeathByDrown() {
		LogRecord logRecord = logParser.parseLineLog("23/04/2013 15:34:22 - <World> killed Zack by Drown");
		assertEquals(BattleLogRecord.class, logRecord.getClass());
		assertEquals("<World>", ((BattleLogRecord) logRecord).getKiller());
		assertEquals("Zack", ((BattleLogRecord) logRecord).getKilled());
		assertEquals("Drown", ((BattleLogRecord) logRecord).getWeapon());
	}

}
