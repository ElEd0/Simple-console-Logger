/**
 * Created by Ed0 in 9 dic. 2018
 */
package es.ed0.test;

import es.ed0.scl.Logger;
import es.ed0.scl.LoggerFactory;
import es.ed0.scl.Logger.Level;

/**
 * 
 */
public class Logtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		new Logtest();

	}
	
	private Logtest() {

		Logger logger = LoggerFactory.getLogger(getClass().getName());
		
		logger.setUseErrStrForError(true);
		logger.setLogFilePath("newfile.txt");
		logger.setErrorLogFilePath("errors.txt");
		
		logger.info("Info message");
		logger.debug("Server", "something happened");
		logger.warn("WARNING", "asdasdd");
		
		
		
		
		try {
			Integer.valueOf("asdasd");
		} catch (NumberFormatException e) {
			logger.error("excpetion ocurred", e);
		}
		
		logger.error("fatsts", "un error");

		logger.info("Info message");
		logger.debug("Server", "something happened");
		logger.warn("WARNING", "asdasdd");
		
		logger.fatal("This is the best reason", "and the best msg");
		
		logger.debug("DEBUG MSG");
		logger.trace("TRACE MSG");
	}

}
