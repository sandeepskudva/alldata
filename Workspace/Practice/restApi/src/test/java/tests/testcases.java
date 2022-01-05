package tests;
import common.*;

import org.testng.annotations.Test;

import apiDefinitions.*;
import java.util.ArrayList;
import java.util.List;




public class testcases {

	usersAPI users = new usersAPI();
	postsAPI posts = new postsAPI();
	commentsAPI comments = new commentsAPI();
	
	@Test	
	public void validateEmail()
	{
		String username = "Samantha";
		String userid = users.getUserid(username);
		System.out.println("User id for "+username+" is "+userid);
		List <String> postids = posts.getPostid(userid);
		System.out.println(postids);
		List <String> emailids = new ArrayList<String>();
		
		for(int i=0;i<postids.size();i++)
		{	
		emailids = comments.getEmailids(postids.get(i));
		System.out.println("Email validation for Post with id "+postids.get(i)+" "+utilities.validateEmailList(emailids));
		System.out.println();
		}
		
	}	
	
	@Test
	public void UsernameNotFound()
	{
		String username = "Sandeep";
		String userid = users.getUserid(username);
		System.out.println("User id for "+username+" is "+userid);
	}

		
}
