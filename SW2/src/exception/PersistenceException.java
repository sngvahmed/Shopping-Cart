package exception;

import java.lang.Exception;

// @author: Osama
// creation date: 5/22/2014

/*
 * PressistenceException Handling 
 */

@SuppressWarnings("serial")
public class PersistenceException extends Exception {

	public PersistenceException(String message) {
		super(message);
	}

}
