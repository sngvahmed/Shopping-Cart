package shoppingCart;

import java.util.Date;

import exception.CartItemNotFoundException;
import exception.IllegalQuantityException;


// @author: Osama
// creation date: 5/20/2014

/*
this interface contains all functions which will be implemented in case implementing
this interface.

the implementation of the functions may be different from one class to another.

*/


// Modified by @author: Nasser
// updating date: 5/21/2014
// return type in "getCustomerID (from void to int)" & 
// "addItem(from int to void)" functions
// "removeCartItem" function is now can throw exception
// "getItem" function is now can throw exception

/*
"getCustomerID" function has to return the customer id so the return type changed
to int.

"addItem" function has to return nothing so the return type changed to void.
 
 "removeCartItem" function has been changed to throw exception
 in case the cart item not found.
 
 "getItem" function has been changed to throw exception in case
 the cart item not found.
 
*/

public interface IShoppingCart {
	
	public int getID(); // returns the Shopping Cart id
	public int getSessionID(); // returns the session id of the Shopping Cart
	public int getCustomerID(); // returns the customer id who owns the Shopping Cart
	public void addItem(ICartItem item); // adds new Cart Item
	public void updateQuantity(int cartItemID, int newQuantity) throws IllegalQuantityException, CartItemNotFoundException; // updates the quantity of a chosen Cart Item
	public void removeItem(int cartItemID) throws CartItemNotFoundException; // removes a chosen Cart Item
	public ICartItem getItem(int productID) throws CartItemNotFoundException; // returns the Cart Item of a chosen product
	public ICartItem[] getItems(); // returns all Cart Items in the Shopping Cart
	public int countItems(); // returns the number of Cart Items in the Shopping Cart
	public Date getLastAccessedDate(); // returns the last accessed date of the Shopping Cart
	
}
