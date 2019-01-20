/**
 * Created by Ed0 in 9 dic. 2018
 */
package es.ed0.scl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public abstract class LoggerFactory {
	
	public static HashMap<String, Logger> loggers;

	/**
	 * Returns the Logger for the specified class name, if it doesnt exist it will create a new one
	 * @param name
	 * @return
	 */
	public static Logger getLogger(Class<?> clazz) {
		return getLogger(clazz.getSimpleName());
	}
	
	/**
	 * Returns the Logger for the specified name, if it doesnt exist it will create a new one
	 * @param name
	 * @return
	 */
	public static Logger getLogger(String name) {
		if(loggers == null)
			loggers = new HashMap<String, Logger>();
		if(!loggers.containsKey(name))
			loggers.put(name, getDefaultLogger(name));
		return loggers.get(name);
	}
	
	
	/**
	 * Default implementation of Logger.<br>
	 * Will output to System.out (error to System.err if specified via {@link Logger#setUseErrStrForError(boolean)})
	 * @param withName
	 * @return
	 */
	private static Logger getDefaultLogger(String withName) {
		return new Logger() {

			@Override
			public void log(Level level, String msg) {
				log(level, "", msg, null);
			}

			@Override
			public void log(Level level, String reason, String msg) {
				log(level, reason + " -> ", msg, null);
			}

			@Override
			public void log(Level level, String msg, Throwable throwable) {
				log(level, "", msg, throwable);
			}

			private final void log(Level level, String reason, String msg, Throwable throwable) {
				if(level.toInt() < this.level)
					return;
				
				final String log = (appendTimespan ? "[" + getTime() + "]" : "")
						+ "[" + withName + "]"
						+ "[" + level.toString().toUpperCase() + "] "
						+ reason + msg
						+ (throwable == null ? "" : "  --  " + throwable.getMessage());
				
				LOG(((level.toInt() >= Level.error.toInt() && this.useErrStrForError) ? System.err : System.out),
						log, throwable);
				
				if(logFilePath != null) 
					logToFile(logFilePath, log);
				
				if (errorLogFilePath != null && level.toInt() >= Level.error.toInt())
					logToFile(errorLogFilePath, log);
			}
			
			private final void logToFile(String path, String msg) {
		    	try {
		    		final File f = new File(path);
		    		
		    		// if(f.length() > 100 * 1024 * 1024) /* 100 MB */ f.delete();
		    		f.createNewFile();
		    		
					final BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
					
					bw.write(msg + "\n");
					bw.flush();
					bw.close();
					
				} catch (IOException e) {
				}
			}

			
		};
	}

}
