package Webpages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;

public class paymentPage extends SuperBase {
static Genric  g = new Genric();

	
	
	
	@SuppressWarnings("finally")
	public boolean fillPaySection(ExtentReports report,ExtentTest logger,int Rownum) throws IOException 
	{
		boolean status =true;
		try {  
				
		    String cardtype = g.ExcelRead(detail, Sheetpath, Rownum, 25);	
		    String cardnumber = g.ExcelRead(detail, Sheetpath, Rownum, 26);
		    String cmonth = g.ExcelRead(detail, Sheetpath, Rownum, 27);
		    String cyear = g.ExcelRead(detail, Sheetpath, Rownum, 28);
		    String ccode = g.ExcelRead(detail, Sheetpath, Rownum, 29);
		  
		   WebElement ctype = driver.findElement(By.xpath("//select[@name='creditCardType']"));
		   ctype.sendKeys(cardtype);
		   WebElement cnumber = driver.findElement(By.xpath("//input[@name='cardNumberMasked']"));
		   cnumber.sendKeys(cardnumber);
		   WebElement month = driver.findElement(By.xpath("//select[@name='creditCExpMonth']"));
		   month.sendKeys(cmonth);
		   WebElement year = driver.findElement(By.xpath("//select[@name='creditCExpYear']"));
		   year.sendKeys(cyear);
		   WebElement code = driver.findElement(By.xpath("//input[@name='securityCode']"));
		   code.sendKeys(ccode);	   
		 
		  
		    System.out.println("Completed filling Payments Page");  	
		    
			
		}
		catch(Exception allexception)
		{
			status =false;
			System.out.println("Error occured in filling Payment Section "+allexception.getMessage());
			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Payment Section Passed", false);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Payment Section failed", true);
			return status;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean fillSecondaryPaySection(ExtentReports report,ExtentTest logger,int Rownum) throws IOException 
	{
		boolean status =true;
		try {  
				
		    String cardtype2 = g.ExcelRead(detail, Sheetpath, Rownum, 32);	
		    String cardnumber2 = g.ExcelRead(detail, Sheetpath, Rownum, 33);
		    String cmonth2 = g.ExcelRead(detail, Sheetpath, Rownum, 27);
		    String cyear2 = g.ExcelRead(detail, Sheetpath, Rownum, 28);
		   
		  
		   WebElement ctype = driver.findElement(By.name("secondaryCreditCardType"));
		   ctype.sendKeys(cardtype2);
		   WebElement cnumber = driver.findElement(By.name("secondaryCreditCardNumberMasked"));
		   cnumber.sendKeys(cardnumber2);
		   WebElement month = driver.findElement(By.name("secondaryCreditCExpMonth"));
		   month.sendKeys(cmonth2);
		   WebElement year = driver.findElement(By.name("secondaryCreditCExpYear"));
		   year.sendKeys(cyear2);
		   	   
		 
		  
		    System.out.println("Completed Secondary Payments Page");  	
		    
			
		}
		catch(Exception allexception)
		{
			status =false;
			System.out.println("Error occured in Secondary Payments Page "+allexception.getMessage());
			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Secondary Payments method adding Passed", false);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Secondary Payments method adding failed", true);
			return status;
		}
	}
	
	
	
	
	
	
	@SuppressWarnings("finally")
	public boolean clickConfirmation(ExtentReports report,ExtentTest logger) throws IOException
	{
		boolean status =true;
		
		try {  
			WebElement nextbtn =driver.findElement(By.xpath("//button[@name='btnConfirmation']"));
			nextbtn.click();
			
			System.out.println("Completed filling confirmation page");  	
		}
		
		catch(Exception allexception)
		{
			status=false;
			System.out.println("Error occured in Confirmation"+allexception.getMessage());
			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Payment Confirmation Passed", false);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Payment Confirmation failed", true);
			return status;
		}
		   
		    
	}
	
	
	
	

}
