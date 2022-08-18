package com.coffeemachine.exceptions;

import com.coffeemachine.util.Constants;

public class UserNotFoundException extends Exception{
	public UserNotFoundException(){
		System.out.println(Constants.USER_NOT_FOUND);
	}
}
