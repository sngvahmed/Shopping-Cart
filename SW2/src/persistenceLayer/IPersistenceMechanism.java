package persistenceLayer;

import exception.PersistenceException;
import shoppingCart.IShoppingCart;

// @author: Osama
// creation date: 5/22/2014

//Modified by Ahmed
//updating date 23/5/2014

/*
 adding throws for all functions
 */

public interface IPersistenceMechanism {
	// Save Give Shopping Cart
	public void save(IShoppingCart cart) throws PersistenceException;

	public IShoppingCart createCart(int sessionID, int customerID)
			throws PersistenceException; // creates a new shopping cart

	// return a shopping cart by it ID
	public IShoppingCart loadCart(int cartID) throws PersistenceException;

	public IShoppingCart loadCart(int sessionID, int customerID)
			throws PersistenceException; // returns a shopping cart by its owner
											// ID & its session ID

	// remove a given shopping cart
	public void removeCart(IShoppingCart cart) throws PersistenceException;

	public void removeItem(int itemID) throws PersistenceException;

	public void closeConnection() throws PersistenceException;
}
