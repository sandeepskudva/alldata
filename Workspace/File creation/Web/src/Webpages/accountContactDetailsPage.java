package Webpages;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;



public class accountContactDetailsPage extends SuperBase {	
	
	

	
	
	
	@SuppressWarnings("finally")
	public boolean fillNameAdressSection(ExtentReports report,ExtentTest logger,int Rownum,String accounttype,String secondary) throws IOException 
	{
		
		boolean status=true;
		try { 
			Genric  g = new Genric();
			String fname = g.ExcelRead(detail, Sheetpath, Rownum, 2);			
		    String mname = g.ExcelRead(detail, Sheetpath, Rownum, 3);			       
		    String lname = g.ExcelRead(detail, Sheetpath, Rownum, 4);		   
		    String cname = g.ExcelRead(detail, Sheetpath, Rownum, 4);		    
		    String Phone = g.ExcelRead(detail, Sheetpath, Rownum, 12);		    
		    String email = fname + "@"+lname+".com";	    
		    
		    
		    String add1 = g.ExcelRead(detail, Sheetpath, Rownum, 5);
		    String add2 = g.ExcelRead(detail, Sheetpath, Rownum, 6);		     
		    String city = g.ExcelRead(detail, Sheetpath, Rownum, 7);		   
		    String state = g.ExcelRead(detail, Sheetpath, Rownum, 8);		   
		    String zip = g.ExcelRead(detail, Sheetpath, Rownum, 9);	
		     
		    
		    String stmt = g.ExcelRead(detail, Sheetpath, Rownum, 13);
		    String username = fname+lname+stmt;
		    String psswd = g.ExcelRead(detail, Sheetpath, Rownum, 15);
		    String squestion = g.ExcelRead(detail, Sheetpath, Rownum, 16);
		    String sanswer = g.ExcelRead(detail, Sheetpath, Rownum, 17);	
		    String pin = g.ExcelRead(detail, Sheetpath, Rownum, 18);
		     
		     
		     if(accounttype.equals("PRIVATE") )
		     {
		   	contactdetailsPrivate(driver,fname,mname,lname,Phone,email,secondary);
			addressDetailsPrivate(driver,add1,add2,city,state,zip);		   
			logindetails(driver,stmt,username,psswd,squestion,sanswer,pin); 
			
		     }
		     else
		     {		     
		    contactdetailsBusiness(driver,fname,mname,lname,Phone,email,cname);
		    addressDetailsPrivate(driver,add1,add2,city,state,zip);		   
		    logindetails(driver,stmt,username,psswd,squestion,sanswer,pin);	
		    }
		     System.out.println("Completed Filling Account details"); 
		    
		}
		catch(Exception allexception)
		{
			status=false;
			System.out.println("Error occured in filling account details "+allexception.getMessage());			
		}
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Account details Passed", false);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Account details failed", true);
			return status;
		}
	}
	
	
	
	
	
	
	
	
	public static void contactdetailsPrivate(WebDriver driver,String Firstname,String Middlename,String Lastname,String phnumber,String email,String secondary) 
	{
		
        try
        {
        WebElement firstname = driver.findElement(By.name("firstName"));
        firstname.sendKeys(Firstname);
        
        WebElement middlename = driver.findElement(By.name("middleInitial"));
        middlename.sendKeys(Middlename);
        
        WebElement lastname = driver.findElement(By.name("lastName"));
        lastname.sendKeys(Lastname);       
        
        WebElement cellnumber = driver.findElement(By.name("cellPhone"));
        cellnumber.sendKeys(phnumber);
        
        WebElement email1 = driver.findElement(By.name("email"));
        email1.sendKeys(email);
        
        WebElement email2 = driver.findElement(By.name("reEmail"));
        email2.sendKeys(email); 	  
        
        if(secondary.equals("No"))
        {
        	
        }
        else
        {	
        	WebElement addSecondary = driver.findElement(By.id("addEmail"));
        	addSecondary.click();; 
        	
        	WebElement second1 = driver.findElement(By.name("secondaryEmail"));
        	second1.sendKeys(secondary); 
        
        	WebElement second2 = driver.findElement(By.name("secondaryReEmail"));
        	second2.sendKeys(secondary);
        }
        }
        catch(Exception allexception)
		{
			
			System.out.println("Error occured in contactdetailsPrivate "+allexception.getMessage());			
		}
        
        		
	}
	
	public static void contactdetailsBusiness(WebDriver driver,String Firstname,String Middlename,String Lastname,String phnumber,String email,String cname) 
	{
		
        
        WebElement firstname = driver.findElement(By.xpath("//input[@name='firstName']"));
        firstname.sendKeys(Firstname);
        
        WebElement middlename = driver.findElement(By.xpath("//input[@name='middleInitial']"));
        middlename.sendKeys(Middlename);
        
        WebElement lastname = driver.findElement(By.xpath("//input[@name='lastName']"));
        lastname.sendKeys(Lastname);
        
        WebElement company = driver.findElement(By.xpath("//input[@name='companyName']"));
        company.sendKeys(cname);       
        
        
        WebElement cellnumber = driver.findElement(By.xpath("(//input[@name='cellPhone'])[1]"));
        cellnumber.sendKeys(phnumber);
        
        WebElement email1 = driver.findElement(By.xpath("//input[@name='email']"));
        email1.sendKeys(email);
        
        WebElement email2 = driver.findElement(By.xpath("//input[@name='reEmail']"));
        email2.sendKeys(email); 	     
        
        
        		
	}
	
	
	
	public static void addressDetailsPrivate(WebDriver driver,String add1,String add2,String city,String state,String zip) 
	{
		try
		{
		WebElement Address1 = driver.findElement(By.xpath("//input[@name='address1']"));
		Address1.sendKeys(add1); 
		
		WebElement Address2= driver.findElement(By.xpath("//input[@name='address2']"));
		Address2.sendKeys(add2); 
		
		WebElement City= driver.findElement(By.xpath("//input[@name='city']"));
		City.sendKeys(city);
		
		WebElement State= driver.findElement(By.xpath("//select[@name='stateType']"));
		State.sendKeys(state);	
		
		WebElement Zip= driver.findElement(By.xpath("//input[@name='zipCode1']"));
		Zip.sendKeys(zip);	
		}
		 catch(Exception allexception)
		{
			
			System.out.println("Error occured in address "+allexception.getMessage());			
		}
        
        
	}
	
	
	public static void logindetails(WebDriver driver,String stmt,String username,String psswd,String squestion,String sanswer,String pin) 
	{
		
		try
		{
		Select dropdown1 = new Select(driver.findElement(By.xpath("//select[@name='statementType']")));
		dropdown1.selectByIndex(Integer.parseInt(stmt));
		
	
		
		WebElement Username= driver.findElement(By.xpath("//input[@name='userName']"));
		Username.sendKeys(username); 
		
		WebElement Password= driver.findElement(By.xpath("//input[@name='signUpPassword']"));
		Password.sendKeys(psswd);
		
		WebElement RePassword= driver.findElement(By.xpath("//input[@name='rePassword']"));
		RePassword.sendKeys(psswd);
		
		Select dropdown2 = new Select(driver.findElement(By.xpath("//select[@name='securityQuestion']")));
		dropdown2.selectByIndex(Integer.parseInt(squestion));	
		
		WebElement SAnswer= driver.findElement(By.xpath("//input[@name='securityAnswer']"));
		SAnswer.sendKeys(sanswer);	
		
		WebElement Pin= driver.findElement(By.xpath("//input[@name='digitPin']"));
		Pin.sendKeys(pin);	
		
		WebElement tagsandvehcilesbtn= driver.findElement(By.xpath("//button[@name='btnTollTagsVechicles']"));
		tagsandvehcilesbtn.click();	
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='radio2']")));
	 
		
		WebElement okbtn1= driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
		wait1.until(ExpectedConditions.elementToBeClickable(okbtn1));
		okbtn1.click();
		
		System.out.println("Address Standardized");
		
		WebElement okbtn2= driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
		wait1.until(ExpectedConditions.elementToBeClickable(okbtn2));
		okbtn2.click();
		
		
		
		
		
		}
		 catch(Exception allexception)
		{
			
			System.out.println("Error occured in logindetails "+allexception.getMessage());			
		}
		
		

		
        
        
	}
	

}
