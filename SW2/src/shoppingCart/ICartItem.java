package shoppingCart;


// @author: Osama
// creation date: 5/20/2014

/*
this interface contains all functions which will be implemented in case implementing
this interface.

the implementation of the functions may be different from one class to another.

*/

public interface ICartItem {
	
	public int getID(); // returns the Cart Item id
	public int getProductID(); // returns the product ID in the Cart Item 
	public int getQuantity(); // returns the quantity of the product in the Cart Item
	public double getUnitPrice(); // returns the unit price of the product in the Cart Item
	public double getTotalCost(); // returns the total cost of the Cart Item
	public void setID(int id); //sets the Cart Item id
	public void setProductID(int id); // sets the product id in this Cart Item
	public void setQuantity(int quantity); // sets the quantity of the product in the Cart Item
	public void setUnitPrice(double price); // sets the unit price of the product in the Cart Item
	
}
