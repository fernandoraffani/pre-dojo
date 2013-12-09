package fpsmatch.logrecord;

import java.util.Calendar;

public abstract class LogRecord {
	
	private Calendar dateEvent;
	
	public Calendar getDateEvent() {
		return dateEvent;
	}
	public void setDateEvent(Calendar dateEvent) {
		this.dateEvent = dateEvent;
	}

}
