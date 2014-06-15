package shoppingCart;

import java.util.Arrays;
import java.util.Date;

import exception.CartItemNotFoundException;
import exception.IllegalQuantityException;


// @author: Nasser
// creation date: 5/21/2014

/*
comments
*/

public class ShoppingCart implements IShoppingCart {
	
	private int id; // Shopping Cart id
	private int sessionID;
	private int customerID; // the owner of the Shopping Cart
	private Date lastAccessedDate;
	private ICartItem[] itemArray;
	private int size; // memory allocated in itemArray "number of CartItems"
	
	// constructor for nothing
	public ShoppingCart() { }
	
	// constructor for initialize the attributes
	public ShoppingCart(int cartID, int customerID, int sessionID) {
		// implementation
		this.id = cartID;
		this.customerID = customerID;
		this.sessionID = sessionID;
		this.lastAccessedDate = new Date();
		this.itemArray = new ICartItem[0];
		this.size = 0;
	}
	
	@Override
	public int getID() { // returns the Shopping Cart id
		return this.id;
	}

	@Override
	public int getSessionID() { // returns the session id of the Shopping Cart
		// implementation
		return this.sessionID;
	}

	@Override
	public int getCustomerID() { // returns the owner id of the Shopping Cart
		// implementation
		return this.customerID;
	}

	@Override
	public void addItem(ICartItem item) { // adds new Cart Item
		// implementation
		if(this.size == this.itemArray.length) // if the itemArray is fully allocated
			this.itemArray = Arrays.copyOf(this.itemArray, this.itemArray.length + 1); // expanding the size by one
		this.itemArray[this.size++] = item;
	}

	@Override
	public void updateQuantity(int cartItemID, int newQuantity) throws IllegalQuantityException, CartItemNotFoundException { // updates the quantity of a chosen Cart Item
		// implementation
		for(int i = 0; i < this.itemArray.length; ++i)
			if(this.itemArray[i].getID() == cartItemID) {
				if(newQuantity > 100)
					throw new IllegalQuantityException("Too much quantity. Process can't completed");
				this.itemArray[i].setQuantity(newQuantity);
				return;
			}
		throw new CartItemNotFoundException("Cart Item not found");
	}

	// private function for taking an index, removing the element in this index from the itemArray, and then returning a new array without the deleted element
	private ICartItem[] removeFromItemArray(int index) {
		ICartItem[] temp = new ICartItem[itemArray.length - 1];
		for(int i = 0, j = 0; i < itemArray.length; ++i) {
			if(i == index) continue;
			temp[j++] = itemArray[i];
		}
		return temp;
	}
	
	@Override
	public void removeItem(int cartItemID) throws CartItemNotFoundException { // removes a chosen Cart Item
		// implementation
		for(int i = 0; i < itemArray.length; ++i)
			if(itemArray[i].getID() == cartItemID) {
				itemArray = removeFromItemArray(i);
				return;
			}
		// throw exception in case the cart item not found
		throw new CartItemNotFoundException("Cart Item not found");
	}

	@Override
	public ICartItem getItem(int productID) throws CartItemNotFoundException { // returns a chosen Cart Item
		// implementation
		for(int i = 0; i < itemArray.length; ++i)
			if(itemArray[i].getProductID() == productID) return itemArray[i];
		// throw exception in case the cart item not found
		throw new CartItemNotFoundException("Cart Item not found");
	}

	@Override
	public ICartItem[] getItems() { // returns all Cart Items
		// implementation
		return this.itemArray;
	}

	@Override
	public int countItems() { // returns the number of Cart Items
		// implementation
		return this.size;
	}

	@Override
	public Date getLastAccessedDate() { // returns the last accessed date of the Shopping Cart
		return this.lastAccessedDate;
	}

}
