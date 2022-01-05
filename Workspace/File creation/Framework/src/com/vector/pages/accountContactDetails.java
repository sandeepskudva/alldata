package com.vector.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vector.func.Genric;
import com.vector.func.SuperTestNG;

public class accountContactDetails extends SuperTestNG {
	
	static Genric  g = new Genric();
	
	@FindBy (xpath ="//a[contains(text(),'Account Opening')]")
	private WebElement accOpentab;
	
	
    
	
	public accountContactDetails(WebDriver driver)
	{
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
		
	
	
	
	public void fillDetails()
	{
		
		try {  
			
			
		    String fname = g.ExcelRead(accountype, Sheetpath, Rownum, 7);
		    String lname = g.ExcelRead(accountype, Sheetpath, Rownum, 8);
		    String Phone = g.ExcelRead(accountype, Sheetpath, Rownum, 9);
		    
		    
		    if(accountype.equals("Private"))
		    {	
		    	
		   	    
		   	    contactdetailsPrivate(driver,fname,lname,Phone);
			    
		    }
		    else if(accountype.equals("Business"))
		    {
		    	
		    	contactdetailsBusiness(driver,fname,lname,Phone);
		    	
		    }
		    else 
		    {
		    	
		    	contactdetailsBusiness(driver,fname,lname,Phone);
		    	
		    }
		    	
		    
			
		}
		catch(Exception allexception)
		{
			System.out.println("Error occured in filling account details ");
		}
	}
	
	
	
	
	
	
	
	
	public static void contactdetailsPrivate(WebDriver driver,String Lastname,String Firstname,String phnumber) throws InterruptedException 
	{
		WebElement addbtn = driver.findElement(By.xpath("//button[@id='s_2_1_11_0_Ctrl']")); 
        addbtn.click();
        
        g.alwayswait(2);
               
        WebElement lastnamebox = driver.findElement(By.xpath("//td[@id='1_s_2_l_Last_Name']"));
        lastnamebox.click();
        WebElement lastname = driver.findElement(By.xpath("//input[@id='1_Last_Name']"));
      
        lastname.sendKeys(Lastname);
        
        WebElement firstnamebox = driver.findElement(By.xpath("//td[@id='1_s_2_l_First_Name']"));
        firstnamebox.click();
        WebElement firstname = driver.findElement(By.xpath("//input[@id='1_First_Name']"));
        firstname.sendKeys(Firstname);
        
        WebElement daynobox = driver.findElement(By.xpath("//td[@id='1_s_2_l_Contact_Phone_Number']"));
        daynobox.click();
        WebElement dayno = driver.findElement(By.xpath("//input[@id='1_Contact_Phone_Number']"));
        dayno.sendKeys(phnumber);  
        
        
        		
	}
	
	
	public static void contactdetailsBusiness(WebDriver driver,String Lastname,String Firstname,String phnumber) throws InterruptedException 
	{
		WebElement addbtn = driver.findElement(By.xpath("//button[@id='s_1_1_11_0_Ctrl']")); 
        addbtn.click();
        
        g.alwayswait(2);
               
        WebElement lastnamebox = driver.findElement(By.xpath("//td[@id='1_s_1_l_Last_Name']"));
        lastnamebox.click();
        WebElement lastname = driver.findElement(By.xpath("//input[@id='1_Last_Name']"));
      
        lastname.sendKeys(Lastname);
        
        WebElement firstnamebox = driver.findElement(By.xpath("//td[@id='1_s_1_l_First_Name']"));
        firstnamebox.click();
        WebElement firstname = driver.findElement(By.xpath("//input[@id='1_First_Name']"));
        firstname.sendKeys(Firstname);
        
        WebElement daynobox = driver.findElement(By.xpath("//td[@id='1_s_1_l_Contact_Phone_Number']"));
        daynobox.click();
        WebElement dayno = driver.findElement(By.xpath("//input[@id='1_Contact_Phone_Number']"));
        dayno.sendKeys(phnumber);  
        
        
        		
	}
	
	public static void addressDetailsPrivate(WebDriver driver,String add1,String add2,String zip) 
	{
		WebElement addbtn = driver.findElement(By.xpath("//button[@id='s_3_1_11_0_Ctrl']"));  
        addbtn.click();
        WebElement address1box = driver.findElement(By.id("1_s_3_l_Street_Address")); 
        address1box.click();
        WebElement address1 = driver.findElement(By.id("1_Street_Address"));        
        address1.sendKeys(add1);
        
        WebElement address2box = driver.findElement(By.id("1_s_3_l_Street_Address_2")); 
        address2box.click();
        WebElement address2 = driver.findElement(By.id("1_Street_Address_2"));
        address2.sendKeys(add2);
        
        WebElement zipcodetxt = driver.findElement(By.xpath("//td[@id='1_s_3_l_Postal_Code']"));
        zipcodetxt.click();
        
        WebElement zipcodetext = driver.findElement(By.xpath("//input[@id='1_Postal_Code']"));
        zipcodetext.sendKeys(zip);   
        
        WebElement zipcodeplustxt = driver.findElement(By.xpath("//td[@id='1_s_3_l_Zip_Plus']"));
        zipcodeplustxt.click(); 
        
        
	}
	
	public static void addressDetailsBusiness(WebDriver driver,String add1,String add2,String zip) 
	{
		WebElement addbtn = driver.findElement(By.xpath("//button[@id='s_3_1_11_0_Ctrl']"));  
        addbtn.click();
        WebElement address1box = driver.findElement(By.id("1_s_3_l_Street_Address")); 
        address1box.click();
        WebElement address1 = driver.findElement(By.id("1_Street_Address"));        
        address1.sendKeys(add1);
        
        WebElement address2box = driver.findElement(By.id("1_s_3_l_Street_Address_2")); 
        address2box.click();
        WebElement address2 = driver.findElement(By.id("1_Street_Address_2"));
        address2.sendKeys(add2);
        
        WebElement zipcodetxt = driver.findElement(By.xpath("//td[@id='1_s_3_l_Postal_Code']"));
        zipcodetxt.click();
        
        WebElement zipcodetext = driver.findElement(By.xpath("//input[@id='1_Postal_Code']"));
        zipcodetext.sendKeys(zip);   
        
        WebElement zipcodeplustxt = driver.findElement(By.xpath("//td[@id='1_s_3_l_Zip_Plus']"));
        zipcodeplustxt.click(); 
        
        
	}
	

}
