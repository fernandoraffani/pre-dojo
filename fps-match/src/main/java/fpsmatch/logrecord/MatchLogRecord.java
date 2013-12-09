package fpsmatch.logrecord;

public class MatchLogRecord extends LogRecord {
	
	public enum MatchStatus{
		START,
		FINISH,
		NONE;
		
	}
	
	private long matchId;
	private MatchStatus matchStatus;
	
	public long getMatchId() {
		return matchId;
	}
	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}
	public MatchStatus getMatchStatus() {
		return matchStatus;
	}
	public void setMatchStatus(MatchStatus matchStatus) {
		this.matchStatus = matchStatus;
	}
	
	

}
