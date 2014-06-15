package persistenceLayer;

import exception.PersistenceException;



// @author: Osama
// creation date: 5/23/2014
//Modified by Mosa

/*
this class is for getting an object of the Persistence type I'll work with.
*/


/*
adding throws for all functions
*/

public class PersistenceFactory {
	
	// this function takes a string represents the type of the persistence & returns an object of the wanted persistence
	public IPersistenceMechanism loadMechanism(String type) throws PersistenceException {
		
		IPersistenceMechanism instance = null;
		if(type.equalsIgnoreCase("SQL")) // SQLPersistence
			instance = SQLPersistence.getInstance();
		else if(type.equalsIgnoreCase("File")) // FilePersistence
			instance = FilePersistence.getInstance();
		else
			System.out.println("WRONG!");
		return instance;
	}
	
}
