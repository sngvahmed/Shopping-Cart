package Main;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exception.CartItemNotFoundException;
import exception.SQLException;
import persistenceLayer.SQLPersistence;
import shoppingCart.ShoppingCart;

public class SQLPersistenceTest {
	SQLPersistence SQL = null;

	public SQLPersistenceTest() throws SQLException {
		SQL = (SQLPersistence) SQLPersistence.getInstance();
	}

	@Test
	public void testGetInstance() throws SQLException {
		assertNotNull(SQL.getInstance());
	}

	@Before
	public void testSave1() throws SQLException {
		SQL.save(new ShoppingCart(5, 5, 5));
	}

	@After
	public void testSave() throws SQLException {
		SQL.removeCart(new ShoppingCart(5, 5, 5));
	}

	@Test
	public void testCreateCart() throws SQLException {
		assertNotNull(SQL.createCart(20, 20));
	}

	@Test
	public void testLoadCartInt() throws SQLException {
		assertNotNull(SQL.loadCart(3));
	}

	@Test
	public void testLoadCartIntInt() throws SQLException {
		assertNotNull(SQL.loadCart(20, 20));
	}

	@Test
	public void testRemoveCart() throws SQLException {
		SQL.removeCart(new ShoppingCart(12, 20, 20));
	}

}
