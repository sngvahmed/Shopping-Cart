package persistenceLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

//Author :: AbdelGawad
//Modified :: Mosa
//Data :: 27/5/2014
//Modified Data :: 28/5/2014

public class MyFileDataSource {

	public class IShoppingCartRow {
		private int ID;
		private int customerID;
		private int sessionID;
		private String lastAccessedDate;

		public IShoppingCartRow() {// Default Contractor
		}

		public IShoppingCartRow(int ID, int customerID, int sessionID,
				String lastAccessedDate) {// Modifier Contractor
			this.ID = ID;
			this.customerID = customerID;
			this.sessionID = sessionID;
			this.lastAccessedDate = lastAccessedDate;
		}

		// Setter And Getter
		public int getID() {
			return this.ID;
		}

		public void setID(int ID) {
			this.ID = ID;
		}

		public int getCustomerID() {
			return this.customerID;
		}

		public void setCustomerID(int customerID) {
			this.customerID = customerID;
		}

		public int getSessionID() {
			return this.sessionID;
		}

		public void setSessionID(int sessionID) {
			this.sessionID = sessionID;
		}

		public String getLastAccessedDate() {
			return this.lastAccessedDate;
		}

		public void setLastAccessedDate(String lastAccessedDate) {
			this.lastAccessedDate = lastAccessedDate;
		}

		// *****************************

		public String toString() {
			return String.valueOf(ID + " " + customerID + " " + sessionID + " "
					+ lastAccessedDate);
		}

	}

	public class IShoppingCartTable {
		private IShoppingCartRow[] row;
		private int size;
		private int lastID;

		public IShoppingCartTable() {// Default Constructor
			this.row = new IShoppingCartRow[0];
			this.size = 0;
		}

		public void insertRow(IShoppingCartRow newRow) {// Modified Constructor
			this.row = Arrays.copyOf(this.row, this.row.length + 1);
			this.row[this.row.length - 1] = newRow;
			this.lastID++;
		}

		public void updateRow(int ID, int customerID, int sessionID,
				String lastAccessedDate) {
			for (int i = 0; i < this.row.length; ++i)
				if (this.row[i].ID == ID) {
					this.row[i] = new IShoppingCartRow(ID, customerID,
							sessionID, lastAccessedDate);
					return;
				}
			this.insertRow(new IShoppingCartRow(this.lastID, customerID,
					sessionID, lastAccessedDate));
			this.size++;
		}

		public void deleteRow(int ID) {
			for (int i = 0; i < this.row.length; ++i)
				if (this.row[i].ID == ID) {
					this.row[i].ID = 0;
					size--;
					break;
				}
		}

		public IShoppingCartRow[] getTable() {
			return this.row;
		}

		public void setTable(IShoppingCartRow[] table) {
			this.row = table;
		}

		public int getLastID() {
			return this.lastID;
		}

		public void setLastID(int lastID) {
			this.lastID = lastID;
		}

	}

	public class ICartItemRow {
		private int ID;
		private int IShoppingCartID;
		private int productID;
		private int qunatity;
		private double price;

		public ICartItemRow() {
		}

		public ICartItemRow(int ID, int IShoppingCartID, int productID,
				int quantity, double price) {
			this.ID = ID;
			this.IShoppingCartID = IShoppingCartID;
			this.productID = productID;
			this.qunatity = quantity;
			this.price = price;
		}

		public int getID() {
			return this.ID;
		}

		public void setID(int ID) {
			this.ID = ID;
		}

		public int getIShoppingCartID() {
			return this.IShoppingCartID;
		}

		public void setIShoppingCartID(int IShoppingCartID) {
			this.IShoppingCartID = IShoppingCartID;
		}

		public int getProductID() {
			return this.productID;
		}

		public void setProductID(int productID) {
			this.productID = productID;
		}

		public int getQunatity() {
			return this.qunatity;
		}

		public void setQunatity(int qunatity) {
			this.qunatity = qunatity;
		}

		public double getPrice() {
			return this.price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String toString() {
			return String.valueOf(ID + " " + IShoppingCartID + " " + productID
					+ " " + qunatity + " " + price);
		}

	}

	public class ICartItemTable {
		private ICartItemRow[] row;
		private int lastID;
		private int size;

		public ICartItemTable() {// Default Constructor
			this.row = new ICartItemRow[0];
			this.size = 0;
		}

		public void insertRow(ICartItemRow newRow) {
			this.row = Arrays.copyOf(this.row, this.row.length + 1);
			this.row[this.row.length - 1] = newRow;
			lastID++;
		}

		public void updateRow(int ID, int IShoppingCartID, int productID,
				int quantity, double price) {
			for (int i = 0; i < this.row.length; ++i)
				if (this.row[i].ID == ID) {
					this.row[i] = new ICartItemRow(ID, IShoppingCartID,
							productID, quantity, price);
					return;
				}
			this.insertRow(new ICartItemRow(this.lastID, IShoppingCartID,
					productID, quantity, price));
			this.size++;
		}

		public void deleteRow(int ID) {
			for (int i = 0; i < this.row.length; ++i)
				if (this.row[i].ID == ID) {
					this.row[i].ID = 0;
					this.size--;
					break;
				}
		}

		public ICartItemRow[] getTable() {
			return this.row;
		}

		public void setRow(ICartItemRow[] row) {
			this.row = row;
		}

		public int getLastID() {
			return this.lastID;
		}

		public void setLastID(int lastID) {
			this.lastID = lastID;
		}

	}

	public IShoppingCartTable shoppingCartTable;
	public ICartItemTable cartItemTable;

	public IShoppingCartRow ShoppingCartInsert(Scanner ScannerFile) {
		return new IShoppingCartRow(ScannerFile.nextInt(),
				ScannerFile.nextInt(), ScannerFile.nextInt(),
				ScannerFile.next());
	}

	public ICartItemRow CartItemInsert(Scanner ScannerFile) {
		return new ICartItemRow(ScannerFile.nextInt(), ScannerFile.nextInt(),
				ScannerFile.nextInt(), ScannerFile.nextInt(),
				ScannerFile.nextDouble());
	}

	public MyFileDataSource() throws exception.FileException { // DataSource_At_File

		Scanner ScannerFile = null;
		// For Shopping Cart
		try {
			ScannerFile = new Scanner(new File("ShoppingCartTable.txt"));
			this.shoppingCartTable = new IShoppingCartTable();
			int fileSize = ScannerFile.nextInt();
			this.shoppingCartTable.size = fileSize;
			while (fileSize-- > 0)
				this.shoppingCartTable
						.insertRow(ShoppingCartInsert(ScannerFile));
			this.shoppingCartTable.lastID = ScannerFile.nextInt();
			ScannerFile.close();
		} catch (FileNotFoundException e) {
			throw new exception.FileException("Shopping Cart file not found");
		}

		// For Cart Item
		try {
			ScannerFile = new Scanner(new File("CartItemTable.txt"));
			this.cartItemTable = new ICartItemTable();
			int fileSize = ScannerFile.nextInt();
			this.cartItemTable.size = fileSize;
			while (fileSize-- > 0)
				this.cartItemTable.insertRow(CartItemInsert(ScannerFile));
			this.cartItemTable.lastID = ScannerFile.nextInt();
			ScannerFile.close();
		} catch (FileNotFoundException e) {
			throw new exception.FileException("Cart Item file not found");
		}
	}

	public boolean CheckIDShoppingCart(int i) {
		return this.shoppingCartTable.row[i].ID != 0;
	}

	public boolean CheckIDCartItem(int i) {
		return this.cartItemTable.row[i].ID != 0;
	}

	public String SCToString(int i) { // Shopping Cart To String
		return this.shoppingCartTable.row[i].toString();
	}

	public String CTTOString(int i) { // Cart Item To String
		return this.cartItemTable.row[i].toString();
	}

	public void WritePrint(PrintWriter PrintWrite, String str) { // write String
		PrintWrite.println(str);
	}

	public void WritePrint(PrintWriter PrintWrite, int str) { // write integer
		PrintWrite.println(str);
	}

	public void saveData() throws exception.FileException { // Save Date To File
		PrintWriter PrintWrite = null;
		// For Shopping Cart
		try {
			PrintWrite = new PrintWriter("ShoppingCartTable.txt");
			WritePrint(PrintWrite, this.shoppingCartTable.size);
			for (int i = 0; i < this.shoppingCartTable.row.length; ++i)
				if (CheckIDShoppingCart(i))
					WritePrint(PrintWrite, SCToString(i));
			WritePrint(PrintWrite, this.shoppingCartTable.lastID);
			PrintWrite.close();
		} catch (FileNotFoundException e) {
			throw new exception.FileException(
					"Shopping Cart file not found (saving failed)");
		}
		// For CartItem
		try {
			PrintWrite = new PrintWriter("CartItemTable.txt");
			WritePrint(PrintWrite, this.cartItemTable.size);
			for (int i = 0; i < this.cartItemTable.row.length; ++i)
				if (CheckIDCartItem(i))
					WritePrint(PrintWrite, CTTOString(i));
			WritePrint(PrintWrite, this.cartItemTable.lastID);
			PrintWrite.close();
		} catch (FileNotFoundException e) {
			throw new exception.FileException(
					"Cart Item file not found (saving failed)");
		}
	}

}
