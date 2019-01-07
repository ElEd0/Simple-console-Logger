/**
 * Created by Ed0 in 9 dic. 2018
 */
package es.ed0.scl;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Abstract class containing some basic functionality and utilities<br>
 * Implementation can be found in {@link LoggerFactory#getDefaultLogger(String)}
 */
public abstract class Logger {

	
	public static enum Level {
		trace, debug, info, warn, error, fatal;
		
		public int toInt() {
			switch(this) {
			case trace: return 1;
			case debug: return 2;
			case info: return 3;
			case warn: return 4;
			case error: return 5;
			case fatal: return 6;
			default: return 0;
			}
		}
	}
	
	protected int level = 0;
	protected boolean useErrStrForError = true;
	protected boolean appendTimespan = true;
	protected boolean dontUseConsole = false;
	protected String logFilePath, errorLogFilePath;
	
	abstract void log(Level level, String msg);
	abstract void log(Level level, String reason, String msg);
	abstract void log(Level level, String msg, Throwable throwable);

	
	public void info(String msg) { log(Level.info, msg); }
	public void info(String reason, String msg) { log(Level.info, reason, msg); }
	public void info(String msg, Throwable throwable) { log(Level.info, msg, throwable); }

	
	public void debug(String msg) { log(Level.debug, msg); }
	public void debug(String reason, String msg) { log(Level.debug, reason, msg); }
	public void debug(String msg, Throwable throwable) { log(Level.debug, msg, throwable); }

	
	public void warn(String msg) { log(Level.warn, msg); }
	public void warn(String reason, String msg) { log(Level.warn, reason, msg); }
	public void warn(String msg, Throwable throwable) { log(Level.warn, msg, throwable); }

	
	public void trace(String msg) { log(Level.trace, msg); }
	public void trace(String reason, String msg) { log(Level.trace, reason, msg); }
	public void trace(String msg, Throwable throwable) { log(Level.trace, msg, throwable); }

	
	public void error(String msg) { log(Level.error, msg); }
	public void error(String reason, String msg) { log(Level.error, reason, msg); }
	public void error(String msg, Throwable throwable) { log(Level.error, msg, throwable); }

	public void fatal(String msg) { log(Level.fatal, msg); }
	public void fatal(String reason, String msg) { log(Level.fatal, reason, msg); }
	public void fatal(String msg, Throwable throwable) { log(Level.fatal, msg, throwable); }
	

	/**
	 * This logger will only output logs at the specified level or above
	 * @param level
	 */
	public void setlevel(Level level) { this.level = level.toInt(); }
	public int getLevel() { return level; }
	
	/**
	 * If specified all logs will level {@link Level#error} or higher
	 * will output to the error output stream implementation (System.err as default)<br>
	 * Default: true
	 */
	public void setUseErrStrForError(boolean useErrStrForError) { this.useErrStrForError = useErrStrForError; }
	/**
	 * Specify a file to write all the logs including errors. Stack traces not included;
	 */
	public void setLogFilePath(String path) { this.logFilePath = path; }
	/**
	 * Specify a file to write all the errors. Stack traces not included;
	 */
	public void setErrorLogFilePath(String path) { this.errorLogFilePath = path; }
	/**
	 * Append the timespan at the beggining of every log
	 */
	public void setAppendTimespan(boolean appendTimespan) { this.appendTimespan = appendTimespan; }
	/**
	 * This Logger will not log into the console, however it will keep writing the logs into the specified files
	 */
	public void setDontUseConsole(boolean dontUseConsole) { this.dontUseConsole = dontUseConsole; }

	/**
	 * Will output the given message and throwable (if any)
	 * to the given PrintStream
	 */
	public final void LOG(PrintStream out, String text, Throwable t) {
		if(dontUseConsole)
			return;
		out.println(text);
		if(t != null)
			t.printStackTrace(out);
	}
	
	/**
	 * Gets the time
	 * @return time in format dd-mm-yyy hh:mm.ss
	 */
	public String getTime() {
    	final Calendar cal = GregorianCalendar.getInstance();
    	cal.setTime(new Date(System.currentTimeMillis()));
    	return cal.get(Calendar.DAY_OF_MONTH) + "-"
    	+ cal.get(Calendar.MONTH) + "-"
    	+ cal.get(Calendar.YEAR) + " "
    	+ cal.get(Calendar.HOUR_OF_DAY) + ":"
    	+ cal.get(Calendar.MINUTE) + "."
    	+ cal.get(Calendar.SECOND);
	}
	
	
	
	
}
