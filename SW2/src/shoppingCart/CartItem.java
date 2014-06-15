package shoppingCart;


// @author: Abdelgawad
// creation date: 5/21/2014

/*
this class implements the "ICartItem" interface & overrides its functions.
*/

public class CartItem implements ICartItem {

	private int id; // Cart Item id
	private int productID;
	private int quantity;
	private double price; // a unit price
	
	// constructor for nothing
	public CartItem() { }
	
	// constructor for setting the attributes
	public CartItem(int id, int productID, int quantity, double price) {
		// implementation
		this.id = id;
		this.productID = productID;
		this.quantity = quantity;
		this.price = price;
	}
	
	@Override
	public int getID() { // returns the Cart Item id
		// implementation
		return this.id;
	}

	@Override
	public int getProductID() { // returns the product id in the Cart Item
		// implementation
		return this.productID;
	}

	@Override
	public int getQuantity() { // returns the quantity of the product in the Cart Item
		// implementation
		return this.quantity;
	}

	@Override
	public double getUnitPrice() { // returns the unit price of the product in the Cart Item
		// implementation
		return this.price;
	}

	@Override
	public double getTotalCost() { // returns the total price of the Cart Item
		// implementation
		return this.quantity * this.price;
	}

	@Override
	public void setID(int id) { // gives the Cart Item an id
		// implementation
		this.id = id;
	}

	@Override
	public void setProductID(int id) { // sets the product ID in the Cart Item
		// implementation
		this.productID = id;
	}

	@Override
	public void setQuantity(int quantity) { // sets the quantity of the product in the Cart Item
		// implementation
		this.quantity = quantity;
	}

	@Override
	public void setUnitPrice(double price) { // sets the unit price of the product in the Cart Item
		// implementation
		this.price = price;
	}

}
