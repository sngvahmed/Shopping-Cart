package exception;

//@author : Mosa
//creation date : 24/5/2014

/*
 FileException Handling
 */

@SuppressWarnings("serial")
public class FileException extends PersistenceException {

	// constructor
	public FileException(String message) {
		// implementation
		super(message);
	}

}
