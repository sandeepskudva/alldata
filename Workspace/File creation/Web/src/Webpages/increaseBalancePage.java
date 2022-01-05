package Webpages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;

public class increaseBalancePage extends SuperBase {
static Genric  g = new Genric();

	
	
	
	@SuppressWarnings("finally")
	public boolean addNewAndPay(ExtentReports report,ExtentTest logger,int Rownum) throws IOException 
	{
		boolean status =true;
		try {  
				
		    
			String cFirstname =  g.ExcelRead(detail, Sheetpath, Rownum, 2);	
			String cLastname =  g.ExcelRead(detail, Sheetpath, Rownum, 4);	
			String cardtype = g.ExcelRead(detail, Sheetpath, Rownum, 32);	
		    String cardnumber = g.ExcelRead(detail, Sheetpath, Rownum, 33);
		    String cmonth = g.ExcelRead(detail, Sheetpath, Rownum, 27);
		    String cyear = g.ExcelRead(detail, Sheetpath, Rownum, 28);
		    String ccode = g.ExcelRead(detail, Sheetpath, Rownum, 29);	    
		    
		    Thread.sleep(2000);
		    
		   WebElement firstname = driver.findElement(By.name("firstName"));
		   firstname.sendKeys(cFirstname);
		   WebElement lastname= driver.findElement(By.name("lastName"));
		   lastname.sendKeys(cLastname);
		  
		   WebElement ctype = driver.findElement(By.xpath("//select[@name='cardType']"));
		   ctype.sendKeys(cardtype);
		   
		   WebElement cnumber = driver.findElement(By.xpath("//input[@name='maskedCardNumber']"));
		   cnumber.sendKeys(cardnumber);
		   
		   
		   WebElement month = driver.findElement(By.xpath("//select[@name='expMonth']"));
		   month.sendKeys(cmonth);
		   WebElement year = driver.findElement(By.xpath("//select[@name='expYear']"));
		   year.sendKeys(cyear);
		   WebElement code = driver.findElement(By.xpath("//input[@name='securityCode']"));
		   code.sendKeys(ccode);	   
		 
		  
		    System.out.println("Completed filling Increase Balances Page");  	
		    
			
		}
		catch(Exception allexception)
		{
			status =false;
			System.out.println("Error occured in filling New card Data"+allexception.getMessage());
			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Entering New card data Passed ", false);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Entering New card data failed", true);
			return status;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean payWithExisting(ExtentReports report,ExtentTest logger,int Rownum) throws IOException 
	{
		boolean status =true;
		try {  
				
			 String ccode = g.ExcelRead(detail, Sheetpath, Rownum, 29);		   
			
			 WebElement code = driver.findElement(By.xpath("//div[@id='cvv2code']//input[@name='securityCode']"));
			 code.sendKeys(ccode);	
		   	   
		 
		  
		    System.out.println("Completed using existing card");  	
		    
			
		}
		catch(Exception allexception)
		{
			status =false;
			System.out.println("Error occured in Payment with existing Card "+allexception.getMessage());
			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Entering Existing Card data Passed", false);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Entering Existing Card data failed", true);
			return status;
		}
	}
	
	
	
	
	
	
	@SuppressWarnings("finally")
	public boolean completePayment(ExtentReports report,ExtentTest logger,int Rownum, String amount) throws IOException
	{
		boolean status =true;
		
		try {  
			WebElement paymentAmounttxt =driver.findElement(By.name("transactionAmount"));
			paymentAmounttxt.clear();
			paymentAmounttxt.sendKeys(amount);
			
			
			
			Select paymentdd = new Select(driver.findElement(By.name("selectedCardRowId")));
			List<WebElement> optionSelect = paymentdd.getOptions();
			
			int size= optionSelect.size();
			
			if(size==2)
			{
				paymentdd.selectByValue("NEW");				
				status =addNewAndPay(report, logger, Rownum);
				
			}
			else
			{
				paymentdd.selectByIndex(0);
				status =payWithExisting(report, logger, Rownum);
			}
			
			if(!status)
			return status;	
			Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
			WebElement continuebtn = driver.findElement(By.name("btnContinue"));
			continuebtn.click();
			System.out.println("Completed filling confirmation page");  	
			Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
			continuebtn = driver.findElement(By.name("btnContinue"));
			continuebtn.click();
			Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
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
			r.addstep(report,logger, "Pass", testcasenumber, "Payment Confirmation Passed", true);
			}
			
			else			
			r.addstep(report, logger, "Fail", testcasenumber,"Payment Confirmation failed", true);
			return status;
		}
		   
		    
	}
	
	
	@SuppressWarnings("finally")
	public boolean verifyPayment(ExtentReports report,ExtentTest logger,int Rownum, String amount) throws IOException
	{
		boolean status =true;
		
		try 
		{  
			WebElement increaseamount =driver.findElement(By.xpath("//p[contains(text(),'Your')]/b[1]"));
			String repamount = increaseamount.getText().trim();
			
			if(!amount.equals(repamount.substring(1)))
            status =false;
			Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
			
			WebElement returnbtn =driver.findElement(By.name("btnreturn"));
			returnbtn.click();
			Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
		}
		
		catch(Exception allexception)
		{
			status=false;
			System.out.println("Error occured in verifying payment"+allexception.getMessage());
			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Payment Verification Passed", false);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Payment Verification failed", true);
			return status;
		}
		   
		    
	}
	
	

}
