package com.vector.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vector.func.Genric;
import com.vector.func.SuperTestNG;

public class BasePage extends SuperTestNG {
	
	Genric g = new Genric();
	
	@FindBy (xpath ="//a[contains(text(),'Account Opening')]")
	private WebElement accOpentab;
	
	
	
	public BasePage(WebDriver driver)
	{
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
		
	
	
	
	public void callaccOpentab()
	{
		
		try {  
			accOpentab.click();	
			g.alwayswait(10);
		}
		catch(Exception allexception)
		{
			System.out.println("Error occured in Navigating to Account Opening");
		}
	}
	

}
