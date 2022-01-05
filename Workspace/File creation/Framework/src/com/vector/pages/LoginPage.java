package com.vector.pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vector.func.SuperTestNG;

public class LoginPage extends SuperTestNG {
	
	
   
	@FindBy (id ="s_swepi_1")
	private WebElement username;
	
	@FindBy (id ="s_swepi_2")
	private WebElement password;
	
	@FindBy (xpath="//input[@value='Login']")
	private WebElement Loginbtn;
	
	
	public LoginPage(WebDriver driver)
	{
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
		
	
	
	
	public void login(String uname, String pwd)
	{
		
		try {
			
			
        
	    
	    username.sendKeys(uname);
	    password.sendKeys(pwd);
	    Loginbtn.click();	   
	   
		
		
		}
		catch(Exception allexception)
		{
			System.out.println("Error occured in Login");
		}
	}
	
	
	
	

}
