package Main;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.omg.CORBA.UnknownUserException;

import shoppingCart.ICartItem;
import shoppingCart.ShoppingCart;
import exception.CartItemNotFoundException;

public class ShoppingCartTest {
	ShoppingCart shop = new ShoppingCart(5, 5, 5);

	@Test
	public void testGetID() {
		int ShopID = shop.getID();
		if (ShopID < 0) {
			assertEquals("There Was error The ID is less than 0", 0,
					shop.getID());
		}
	}

	@Test
	public void testGetSessionID() {
		int ShopSessionID = shop.getSessionID();
		if (ShopSessionID < 0) {
			assertEquals("There Was error The SessionID is less than 0", 0,
					shop.getSessionID());
		}
	}

	@Test
	public void testGetCustomerID() {
		int ShopCustomerID = shop.getCustomerID();
		if (ShopCustomerID < 0) {
			assertEquals("There Was error The SessionID is less than 0", 0,
					shop.getCustomerID());
		}
	}

	@Test
	public void testGetItem() {
		ICartItem[] Cart = shop.getItems();
		assertNotNull(Cart);
	}

	@Test(expected = CartItemNotFoundException.class)
	public void testCountItems() throws CartItemNotFoundException {
		ICartItem cart = shop.getItem(0);
		assertNull(cart);
	}

	@Test
	public void testGetLastAccessedDate() {
		Date dat = new Date();
		assertEquals("Shold be today :D ", dat, shop.getLastAccessedDate());
	}
}
