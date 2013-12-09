package fpsmatch.parser;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import fpsmatch.exception.ValidationException;
import fpsmatch.logrecord.BattleLogRecord;
import fpsmatch.logrecord.LogRecord;
import fpsmatch.logrecord.MatchLogRecord;
import fpsmatch.logrecord.MatchLogRecord.MatchStatus;

public class LogParser {
	
	private static String datePattern = "\\d\\d\\/\\d\\d\\/\\d\\d\\d\\d";
	private static String hourPattern = "\\d\\d\\:\\d\\d\\:\\d\\d";
	
	public LogRecord parseLineLog(String line) {
		String[] words = line.split("\\s");
		validateLogLine(words);
		return buildLogRecord(words);
	}

	private LogRecord buildLogRecord(String[] words) {
		return discoverTypeAndCreateLogRecord(words);
	}

	private void validateLogLine(String[] words) {
		validateLineWordCount(words.length);
		validateDate(words[0]);
		validateHour(words[1]);
	}

	private void validateLineWordCount(int length) {
		if(length != 7 && length != 8)
			throw new ValidationException("logLine is malformatted. Length: " + length);
	}
	
	private void validateDate(String dateString) {
		if(!dateString.matches(datePattern))
			throw new ValidationException("Invalid date format");
	}
	
	private void validateHour(String hourString) {
		if(!hourString.matches(hourPattern))
			throw new ValidationException("Invalid hour format");
	}
	
	private LogRecord discoverTypeAndCreateLogRecord(String[] words) {
		MatchStatus status = defineMatchLogStatus(words);
		return buildLogRecord(status, words);
	}

	private MatchStatus defineMatchLogStatus(String[] words) {
		if(words.length == 7)
			return MatchStatus.FINISH;
		if(words.length == 8 && words[4].equals("match"))
			return MatchStatus.START;
		return MatchStatus.NONE;
	}
	
	private LogRecord buildLogRecord(MatchStatus status, String[] words) {
		if(status == MatchStatus.NONE){
			BattleLogRecord logRecord = new BattleLogRecord();
			logRecord.setDateEvent(parseDate(words[0], words[1]));
			logRecord.setKiller(words[3]);
			logRecord.setKilled(words[5]);
			logRecord.setWeapon(words[7]);
			return logRecord;
		}else{
			MatchLogRecord logRecord = new MatchLogRecord();
			logRecord.setDateEvent(parseDate(words[0], words[1]));
			logRecord.setMatchStatus(status);
			logRecord.setMatchId(parseMatchId(status, words));
			return logRecord;
		}
	}

	private long parseMatchId(MatchStatus status, String[] words) {
		if(status == MatchStatus.START)
			return Long.parseLong(words[5]);
		else
			return Long.parseLong(words[4]);
	}

	private Calendar parseDate(String date, String hour) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Calendar calendar = new GregorianCalendar();
		try{
			calendar.setTime(formatter.parse(date + " " + hour));
		}catch(ParseException parseException){
			throw new ValidationException(parseException);
		}
		return calendar;
	}

}
