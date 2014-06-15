package exception;

import java.lang.Exception;

//@author : Mosa
//creation date 23/5/2014

/*
 CartItemNotFountExceptio  Handling	
 */

@SuppressWarnings("serial")
public class CartItemNotFoundException extends Exception {

	public CartItemNotFoundException(String message) {
		super(message);
	}

}
