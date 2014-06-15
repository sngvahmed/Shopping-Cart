package exception;

import java.lang.RuntimeException;

//@author AbdelGawad
//creation date : 23/5/2014

/*
 * IllegalQuantityException Handling
 */

@SuppressWarnings("serial")
public class IllegalQuantityException extends RuntimeException {

	public IllegalQuantityException(String message) {
		super(message);
	}

}
