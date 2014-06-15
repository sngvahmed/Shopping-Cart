package webServer;


import java.util.Scanner;

import exception.CartItemNotFoundException;
import exception.IllegalQuantityException;
import exception.PersistenceException;
import persistenceLayer.IPersistenceMechanism;
import persistenceLayer.PersistenceFactory;
import shoppingCart.CartItem;
import shoppingCart.IShoppingCart;

//@author of this class/function
//creation date

/*
comments
*/

public class WebServer {
	
	public static IShoppingCart cart = null;
	public static PersistenceFactory PF = new PersistenceFactory();
	public static IPersistenceMechanism PM = null;
	public static Scanner cin = new Scanner(System.in);
	public static String cmd = "";
	
	public static void mechanishmMenu() {
		System.out.println("SQL: for SQL Persistence");
		System.out.println("File: for File Persistence");
		cmd = cin.next();
		try {
			if(PM != null) PM.closeConnection();
			PM = PF.loadMechanism(cmd);
			System.out.println("DONE!");
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void queryMenu() {
		System.out.println("1: to create cart (by session & customer ID)");
		System.out.println("2: to load cart (by ID)");
		System.out.println("3: to load cart (by session & customer ID)");
		System.out.println("4: to delete cart");
		System.out.println("5: to save cart");
		cmd = cin.next();
		if(cmd.equals("1")) {
			System.out.println("enter session ID & your ID:");
			int sessionID = cin.nextInt();
			int customerID = cin.nextInt();
			try {
				cart = PM.createCart(sessionID, customerID);
				System.out.println("DONE!");
			} catch (PersistenceException e) {
				System.out.println(e.getMessage());
			}
		} else if(cmd.equals("2")) {
			System.out.println("enter the cart ID:");
			int cartID = cin.nextInt();
			try {
				cart = PM.loadCart(cartID);
				System.out.println("DONE!");
			} catch (PersistenceException e) {
				System.out.println(e.getMessage());
			}
		} else if(cmd.equals("3")) {
			System.out.println("enter session ID & your ID:");
			int sessionID = cin.nextInt();
			int customerID = cin.nextInt();
			try {
				cart = PM.loadCart(sessionID, customerID);
				System.out.println("DONE!");
			} catch (PersistenceException e) {
				System.out.println(e.getMessage());
			}
		} else if(cmd.equals("4")) {
			if(cart == null) System.out.println();
			else {
				try {
					PM.removeCart(cart);
				} catch (PersistenceException e) {
					System.out.println(e.getMessage());
				}
			}
		} else if(cmd.equals("5")) {
			if(cart == null) System.out.println();
			else {
				try {
					PM.save(cart);
					System.out.println("DONE!");
				} catch (PersistenceException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	public static void updatesMenu() {
		System.out.println("1: to add new item");
		System.out.println("2: to update item");
		System.out.println("3: to delete item");
		System.out.println("4: to print current cart");
		cmd = cin.next();
		if(cmd.equals("1")) {
			System.out.println("enter the product ID, quantity , & price:");
			int productID = cin.nextInt();
			int quantity = cin.nextInt();
			double price = cin.nextDouble();
			cart.addItem(new CartItem(-1, productID, quantity, price));
			try {
				PM.save(cart);
				cart = PM.loadCart(cart.getID());
			} catch (PersistenceException e) {
				System.out.println(e.getMessage());
				return;
			}
			System.out.println("DONE!");
		} else if(cmd.equals("2")) {
			System.out.println("enter the item ID & the new quantity:");
			int cartItemID = cin.nextInt();
			int newQuantity = cin.nextInt();
			try {
				cart.updateQuantity(cartItemID, newQuantity);
				System.out.println("DONE!");
			} catch (IllegalQuantityException | CartItemNotFoundException e) {
				System.out.println(e.getMessage());
			}
		} else if(cmd.equals("3")) {
			System.out.println("enter the item ID");
			int cartItemID = cin.nextInt();
			try {
				cart.removeItem(cartItemID);
				PM.removeItem(cartItemID);
			} catch (CartItemNotFoundException | PersistenceException e) {
				System.out.println(e.getMessage());
			}
		} else if(cmd.equals("4")) {
			
		}
	}
	
	public static void main(String[] args) {
		// implementation
		while(true) {
			System.out.println("1: to choose a mechanism");
			System.out.println("2: to make a query");
			System.out.println("3: to make an update on the current cart");
			System.out.println("4: to exit");
			cmd = cin.next();
			if(cmd.equals("1")) {
				mechanishmMenu();
			} else if(cmd.equals("2")) {
				if(PM == null) System.out.println("choose the mechanism first");
				else queryMenu();
			} else if(cmd.equals("3")) {
				if(PM == null) System.out.println("choose the mechanism first");
				else updatesMenu();
			} else if(cmd.equals("4")) {
				if(PM != null) {
					try {
						PM.closeConnection();
					} catch (PersistenceException e) {
						System.out.println(e.getMessage());
					}
				}
				break;
			} else {
				
			}
		}
		cin.close();
	}

}


















