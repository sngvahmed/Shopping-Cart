package persistenceLayer;

import java.text.SimpleDateFormat;
import java.util.Date;

import persistenceLayer.MyFileDataSource.ICartItemRow;
import shoppingCart.CartItem;
import shoppingCart.ICartItem;
import shoppingCart.IShoppingCart;
import shoppingCart.ShoppingCart;

//@author AbdelGawad
//Date : 5/25/2014

//Modifier : Mosa

public class FilePersistence implements IPersistenceMechanism {

	private static MyFileDataSource file;

	private static IPersistenceMechanism instance = null;

	private FilePersistence() throws exception.FileException { // Constructor
		file = new MyFileDataSource();
	}

	public static IPersistenceMechanism getInstance()
			throws exception.FileException { // return FilePresistence
		if (instance == null)
			instance = new FilePersistence();
		return instance;
	}

	@Override
	public void save(IShoppingCart cart) { // save To file
		String date = new SimpleDateFormat("yyyy-MM-dd").format(cart
				.getLastAccessedDate());
		file.shoppingCartTable.updateRow(cart.getID(), cart.getCustomerID(),
				cart.getSessionID(), date);
		ICartItem[] cartItem = cart.getItems();
		for (int i = 0; i < cartItem.length; ++i) {
			int ID = cartItem[i].getID();
			int productID = cartItem[i].getProductID();
			int quantity = cartItem[i].getQuantity();
			double price = cartItem[i].getUnitPrice();
			file.cartItemTable.updateRow(ID, cart.getID(), productID, quantity,
					price);
		}
	}

	@Override
	public IShoppingCart createCart(int sessionID, int customerID)
			throws exception.FileException {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		file.shoppingCartTable.updateRow(-1, customerID, sessionID, date);
		return this.loadCart(sessionID, customerID);
	}

	@Override
	public IShoppingCart loadCart(int cartID) throws exception.FileException {
		IShoppingCart cart = null;
		for (int i = 0; i < file.shoppingCartTable.getTable().length; ++i)
			if (file.shoppingCartTable.getTable()[i].getID() == cartID) {
				int customerID = file.shoppingCartTable.getTable()[i]
						.getCustomerID();
				int sessionID = file.shoppingCartTable.getTable()[i]
						.getSessionID();
				cart = new ShoppingCart(cartID, customerID, sessionID);
				if (cart != null) {
					for (int j = 0; j < file.cartItemTable.getTable().length; ++j)
						if (file.cartItemTable.getTable()[j]
								.getIShoppingCartID() == cartID) {
							int ID = file.cartItemTable.getTable()[j].getID();
							int productID = file.cartItemTable.getTable()[j]
									.getProductID();
							int qunatity = file.cartItemTable.getTable()[j]
									.getQunatity();
							double price = file.cartItemTable.getTable()[j]
									.getPrice();
							cart.addItem(new CartItem(ID, productID, qunatity,
									price));
						}
				}
				break;
			}
		if (cart == null)
			throw new exception.FileException("Shopping Cart not found");
		return cart;
	}

	// check session id and customer id
	public boolean CheckIDS(int i, int sessionID, int customerID) {
		return file.shoppingCartTable.getTable()[i].getSessionID() == sessionID
				&& file.shoppingCartTable.getTable()[i].getCustomerID() == customerID;
	}

	// check shopping cartID
	public boolean CheckIDS(int i, int j) {
		return file.cartItemTable.getTable()[j].getIShoppingCartID() == file.shoppingCartTable
				.getTable()[i].getID();
	}

	// return new cart item object
	public CartItem newCartItem(ICartItemRow ctrow) {
		int ID = ctrow.getID();
		int productID = ctrow.getProductID();
		int qunatity = ctrow.getQunatity();
		double price = ctrow.getPrice();
		return new CartItem(ID, productID, qunatity, price);
	}

	@Override
	public IShoppingCart loadCart(int sessionID, int customerID)
			throws exception.FileException {
		IShoppingCart cart = null;

		for (int i = 0; i < file.shoppingCartTable.getTable().length; ++i)
			if (CheckIDS(i, sessionID, customerID)) {
				int ShtiD = file.shoppingCartTable.getTable()[i].getID(); // SHOPPINGCARTID
				cart = new ShoppingCart(ShtiD, customerID, sessionID);
				if (cart != null) {
					for (int j = 0; j < file.cartItemTable.getTable().length; ++j)
						if (CheckIDS(i, j)) {
							ICartItemRow ctrow = file.cartItemTable.getTable()[j];
							cart.addItem(newCartItem(ctrow));
						}
				}
				break;
			}
		if (cart == null)
			throw new exception.FileException("Shopping Cart not found");
		return cart;
	}

	@Override
	public void removeCart(IShoppingCart cart) {
		file.shoppingCartTable.deleteRow(cart.getID());
		ICartItem[] cartItem = cart.getItems();
		for (int i = 0; i < cartItem.length; ++i)
			file.cartItemTable.deleteRow(cartItem[i].getID());
	}

	@Override
	public void removeItem(int itemID) throws exception.FileException {
		file.cartItemTable.deleteRow(itemID);
	}

	public void closeConnection() throws exception.FileException {
		file.saveData();
	}

}
