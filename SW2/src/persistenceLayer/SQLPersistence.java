package persistenceLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import shoppingCart.CartItem;
import shoppingCart.ICartItem;
import shoppingCart.IShoppingCart;
import shoppingCart.ShoppingCart;

//@author :: AbdelGawad 
//creation date ::27/5/2014
//Modified :: Nasser 

//Updated :: 28/5/2014

/*
 the class responsible for the SQL statement and execution From data base 
 */

public class SQLPersistence implements IPersistenceMechanism {

	private static MySQLDataSource connection = null;
	private static IPersistenceMechanism instance = null;

	private SQLPersistence() throws exception.SQLException { // Constrator
		connection = new MySQLDataSource("ShoppingCart", "root", "");
	}

	public static IPersistenceMechanism getInstance()
			throws exception.SQLException {
		// implementation
		if (instance == null)
			instance = new SQLPersistence();
		return instance;
	}

	// ********************** Save *****************************************
	public String UpdateIshoppingCart(IShoppingCart cart) {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(cart
				.getLastAccessedDate());
		return "UPDATE IShoppingCart SET LastAccess = '" + date
				+ "' WHERE ID = " + cart.getID() + ";";
	}

	public String UpdateICartItem(ICartItem cartItem) {
		return "UPDATE ICartItem SET Quantity = " + cartItem.getQuantity()
				+ ",ProductID = " + cartItem.getProductID() + ",Price = "
				+ cartItem.getUnitPrice() + " WHERE ID = " + cartItem.getID()
				+ ");";
	}

	public String InsertIntoIcartItem(int ID, ICartItem cartItem) {
		return "INSERT INTO ICartItem (IShoppingCartID, ProductID, Price, Quantity) VALUES ("
				+ ID
				+ ","
				+ cartItem.getProductID()
				+ ","
				+ cartItem.getUnitPrice() + "," + cartItem.getQuantity() + ")";
	}

	// save From Memory To Data Base
	@Override
	public void save(IShoppingCart cart) throws exception.SQLException {

		String sql = UpdateIshoppingCart(cart);
		connection.excuteUpdate(sql);

		ICartItem[] cartItem = cart.getItems();
		for (int i = 0; i < cartItem.length; ++i) {
			try {
				sql = UpdateICartItem(cartItem[i]);
				connection.excuteUpdate(sql);
			} catch (exception.SQLException e) {
				sql = InsertIntoIcartItem(cart.getID(), cartItem[i]);
				connection.excuteUpdate(sql);
			}
		}
	}

	// ####### End Save ########

	// SQL Statement Insert in Shopping
	public String InsertShoppingCart(int customerID, int sessionID) {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return "INSERT INTO IShoppingCart (CustomerID, SessionID, LastAccess) VALUES ("
				+ customerID + "," + sessionID + ",'" + date + "');";
	}

	@Override
	public IShoppingCart createCart(int sessionID, int customerID)
			throws exception.SQLException {
		String sql = InsertShoppingCart(customerID, sessionID);
		connection.excuteUpdate(sql);
		IShoppingCart cart = loadCart(sessionID, customerID);
		return cart;
	}

	// ** loadCart ***********************************************

	public String SelectIshoppingCart(int cartID) { // SQL Statement
		return "SELECT * FROM IShoppingCart WHERE ID = " + cartID + ";";
	}

	public String SelectCartItem(int cartID) { // SQL Statement
		return "SELECT * FROM ICartItem WHERE IShoppingCartID = " + cartID
				+ ";";
	}

	public String SelectSCWithSessionAndCustomer(int C, int S) {
		return "SELECT * FROM IShoppingCart WHERE CustomerID = " + C
				+ " AND SessionID = " + S + ";";
	}

	public IShoppingCart IshoppingCartFromDataBase(ResultSet result)
			throws exception.SQLException { // ShoppingCartDataBase
		try {
			return new ShoppingCart(result.getInt(1), result.getInt(2),
					result.getInt(3));
		} catch (SQLException e) {
			throw new exception.SQLException("Failed on DataBase ShoppingCart");
		}
	}

	public CartItem ICartItemFromDataBase(ResultSet result)
			throws exception.SQLException { // ShoppingCartDataBase
		try {
			return new CartItem(result.getInt(1), result.getInt(3),
					result.getInt(5), result.getDouble(4));
		} catch (SQLException e) {
			throw new exception.SQLException("Failed on DataBaseCartItem");
		}
	}

	@Override
	public IShoppingCart loadCart(int cartID) throws exception.SQLException {

		IShoppingCart cart = null;
		try {
			String sql = SelectIshoppingCart(cartID);
			ResultSet result = connection.executeQuery(sql);
			while (result.next())
				cart = IshoppingCartFromDataBase(result);

			if (cart == null)
				throw new exception.SQLException("Shopping Cart not found");

			sql = SelectCartItem(cartID);
			result = connection.executeQuery(sql);

			while (result.next())
				cart.addItem(ICartItemFromDataBase(result));

		} catch (SQLException e) {
			throw new exception.SQLException("Shopping Cart building failed");
		}
		return cart;
	}

	@Override
	public IShoppingCart loadCart(int sessionID, int customerID)
			throws exception.SQLException {

		IShoppingCart cart = null;
		try {
			String sql = SelectSCWithSessionAndCustomer(customerID, sessionID);
			ResultSet result = connection.executeQuery(sql);
			while (result.next())
				cart = IshoppingCartFromDataBase(result);

			if (cart == null)
				throw new exception.SQLException("Shopping Cart not found");

			sql = SelectCartItem(cart.getID());
			result = connection.executeQuery(sql);

			while (result.next())
				cart.addItem(ICartItemFromDataBase(result));

		} catch (SQLException e) {
			throw new exception.SQLException("Shopping Cart building failed");
		}
		return cart;
	}

	// ############ End LoadCart ###############################

	// ************* RemoveCart ********************************
	public String RemoveIShoppingCart(int ID) { // return SQl Statement
		return "DELETE FROM IShoppingCart WHERE ID = " + ID + ";";
	}

	public String RemoveCartItem(int ID) { // return SQL Statement
		return "DELETE FROM ICartItem WHERE IShoppingCartID = " + ID + ";";
	}

	public String CartItemDelete(int itemID) {
		return "DELETE FROM ICartItem WHERE ID = " + itemID + ";";
	}

	@Override
	public void removeCart(IShoppingCart cart) throws exception.SQLException {

		String sql = RemoveIShoppingCart(cart.getID());
		connection.excuteUpdate(sql);
		sql = RemoveCartItem(cart.getID());
		connection.excuteUpdate(sql);
	}

	@Override
	public void removeItem(int itemID) throws exception.SQLException {
		// implementation
		String sql = CartItemDelete(itemID);
		connection.excuteUpdate(sql);

	}

	public void closeConnection() throws exception.SQLException {
		connection.closeConnection();
	}

	// ######## END Remove #######################################

}
