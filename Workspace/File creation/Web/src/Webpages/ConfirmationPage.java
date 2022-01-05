package Webpages;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;



public class ConfirmationPage extends SuperBase {
	
	static Genric  g = new Genric();
	
	

		
	
	

	
	@SuppressWarnings("finally")
	public boolean clickCompleteOrder(ExtentReports report,ExtentTest logger) throws IOException 
	{
		boolean status =true;
		try{
			Thread.sleep(2000);
		
		WebElement completeOrder =driver.findElement(By.xpath("//button[@name='btnComplete']"));
		completeOrder.click();	
		Thread.sleep(10000);
		
		}
		catch(Exception allexception)
		{
			status=false;
			System.out.println("Error occured in completing order "+allexception.getMessage());		
		}
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Order Confirmation Section Passed", false);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Order Confirmation Section failed", true);
			return status;
		}	
		    
	}
	
	@SuppressWarnings("finally")
	public boolean captureaccountNumber(ExtentReports report,ExtentTest logger) throws IOException 
	{
		boolean status =true;
		String accountnumber=null;
		try{
					
		WebElement congrats =driver.findElement(By.xpath("//h1[contains(.,'Account Number')]"));
		String example =congrats.getText();
		accountnumber=example.substring(example.indexOf("Account Number is ")+18  , example.indexOf("."));
		System.out.println("Account number is : "+accountnumber);
		
		}
		catch(Exception allexception)
		{
			status=false;
			System.out.println("Error occured in account number"+allexception.getMessage());		
		}
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Account number created "+accountnumber, true);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Account creation failed", true);
			return status;
		}
		
		    
	}
	
	@SuppressWarnings("finally")
	public boolean captureSRNumber(ExtentReports report,ExtentTest logger) throws IOException 
	{
		boolean status =true;
		String SRnumber=null;
		try{
					
		WebElement congrats =driver.findElement(By.xpath("//h1[contains(.,'Request number')]"));
		String example =congrats.getText();
		SRnumber=example.substring(example.indexOf("Request number is ")+18  , example.indexOf("."));
		System.out.println("service number is : "+SRnumber);
		
		}
		catch(Exception allexception)
		{
			status=false;
			System.out.println("Error occured in SR number"+allexception.getMessage());		
		}
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Service Request "+SRnumber, true);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Service Request failed", true);
			return status;
		}
		
		    
	}
	
	

}
