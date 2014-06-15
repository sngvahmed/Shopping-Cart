package exception;

//@author : Mosa
//creation date :: 5/26/2014

/*
 * SQL Exception Handling
 */

@SuppressWarnings("serial")
public class SQLException extends PersistenceException {

	public SQLException(String message) {
		super(message);
	}

}
