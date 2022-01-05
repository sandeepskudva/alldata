package tests;
import org.testng.annotations.Test;

import apiDefinitions.usersAPI;


public class testcases {

	usersAPI users = new usersAPI();
		
	@Test	
	public void validateEmail()
	{
		String username = "Samantha";
		String userid = .getUserid(username);
		System.out.println("User id for "+username+" is "+userid);
		
	}	
	
	

		
}
