package Webpages;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;

public class accountHome extends SuperBase {
	@SuppressWarnings("finally")
	public boolean validatehome(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		Genric g = new Genric();
		boolean status =true;
		
		String username=g.ExcelRead(detail, Sheetpath, Rownum, 14);
		
		
		
		try {  
			String accountnumber=g.executefetchQuery("select ACCOUNT_NO from v_etc_account where user_name ='"+username+"'", "ACCOUNT_NO");
			
			String expectedhometext = "ACCOUNT # "+accountnumber;
			
			WebElement actual =driver.findElement(By.xpath("//h3[contains(text(),'ACCOUNT #')]"));
			String actualhometext =actual.getText().trim();
			
			
			if(actualhometext.equals(expectedhometext))
			{
				Genric.highLightElement(driver, actual,"green");
				
				r.addstep(report,logger, "Pass", testcasenumber, "Login Sucessfull account number validated "+accountnumber, true);
				
				
			}
			else
			{
				r.addstep(report,logger, "Fail", testcasenumber, "Login failed account number different", false);
			}
			
			System.out.println("Home page seen");  	
		}
		
		catch(Exception allexception)
		{
			status=false;
			System.out.println("Error occured in navigating to Home page"+allexception.getMessage());
			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Home Page navigation Passed", false);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Home Page navigation Failed", true);
			return status;
		}
		   
		    
	}
	
	@SuppressWarnings("finally")
	public boolean perofrmeditProfile(ExtentReports report,ExtentTest logger) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
	
		boolean status =true;
		
		
		
		
		try {  
			
			WebElement editprofile =driver.findElement(By.xpath("//a[(text()='Edit Profile')]"));
			editprofile.click();
			Thread.sleep(2000);
			
			
			System.out.println("edit profile page seen");  	
		}
		
		catch(Exception allexception)
		{
			status=false;
			System.out.println("Error occured in navigating to edit profile page"+allexception.getMessage());
			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Edit Profile Page navigation Passed", true);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Edit Profile Page navigation Failed", true);
			return status;
			
		}
	}		
		 
		@SuppressWarnings("finally")
		public boolean increasebalance(ExtentReports report,ExtentTest logger) throws EncryptedDocumentException, InvalidFormatException, IOException
		{
		
			boolean status =true;
			
			
			
			
			try {  
				
				WebElement editprofile =driver.findElement(By.xpath("//a[text()='Increase Balance']"));
				editprofile.click();
				
				
				System.out.println("Increase balance page seen");  	
			}
			
			catch(Exception allexception)
			{
				status=false;
				System.out.println("Error occured in navigating to Increase Balance page"+allexception.getMessage());
				
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "Increase Balance navigation Passed", false);
				}
				
				else
				
				
				r.addstep(report, logger, "Fail", testcasenumber,"Increase Balance Page navigation Failed", true);
				return status;
			}
		}
		
		
		@SuppressWarnings("finally")
		public boolean gotoTagVehicle(ExtentReports report,ExtentTest logger) throws EncryptedDocumentException, InvalidFormatException, IOException
		{
		
			boolean status =true;
			
			
			
			
			try {  
				
				WebElement tagsVehicles =driver.findElement(By.xpath("//li/a[contains(text(),'Tags/Vehicles')]"));
				tagsVehicles.click();
				
				Thread.sleep(2000);
			    WebElement pageverify = driver.findElement(By.xpath("//h1[1]"));
				
				String pageloaded = pageverify.getText().trim();
				
				System.out.println(pageloaded);
				Thread.sleep(2000);
				if(pageloaded.equals("TAG LIST"))
				{
					status=true;
				}
				else
				{
					status=false;
					
				}
				
				
				
			}
			
			catch(Exception allexception)
			{
				status=false;
				System.out.println("Error occured in navigating to TAG LIST page"+allexception.getMessage());
				
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "TAG LIST Page navigation Passed", false);
				}
				
				else		
				
				r.addstep(report, logger, "Fail", testcasenumber,"TAG LIST Page navigation Failed", true);
				return status;
			}
		}
		
		
		@SuppressWarnings("finally")
		public boolean goToPaymentMethod(ExtentReports report,ExtentTest logger) throws EncryptedDocumentException, InvalidFormatException, IOException
		{
		
			boolean status =true;
			
			
			
			
			try {  
				Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
				Actions action = new Actions(driver);
				WebElement Paymentpointer =driver.findElement(By.xpath("//a[contains(text(),'Payment  ')]"));
				action.moveToElement(Paymentpointer).build().perform();
				Thread.sleep(1000);
				WebElement PaymentMethod =driver.findElement(By.xpath("//div[@class='account_navigation-column']//a[contains(text(),'Payment Method')]"));
				PaymentMethod.click();
				
				
				System.out.println("Payment Method page seen");  	
			}
			
			catch(Exception allexception)
			{
				status=false;
				System.out.println("Error occured in navigating to Payment Method page"+allexception.getMessage());
				
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "Payment Method navigation Passed", false);
				}
				
				else
				
				
				r.addstep(report, logger, "Fail", testcasenumber,"Payment Method navigation Failed", true);
				return status;
			}
		}
		
		
		@SuppressWarnings("finally")
		public boolean goToPlansPage(ExtentReports report,ExtentTest logger) throws EncryptedDocumentException, InvalidFormatException, IOException
		{
		
			boolean status =true;
			
			
			
			
			try {  
				Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
				Actions action = new Actions(driver);
				WebElement accountpointer =driver.findElement(By.xpath("//a[contains(text(),'My Account ')]/img[@class='arrow_icon']"));
				action.moveToElement(accountpointer).build().perform();
				Thread.sleep(1000);
				WebElement plansmenu =driver.findElement(By.xpath("//div[@class='account_navigation-column']//a[contains(text(),'Plans')]"));
				plansmenu.click();			
				
				System.out.println("plans page seen");  	
			}
			
			catch(Exception allexception)
			{
				status=false;
				System.out.println("Error occured in navigating to plans page"+allexception.getMessage());
				
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "Plans navigation Passed", false);
				}
				
				else
				
				
				r.addstep(report, logger, "Fail", testcasenumber,"Plans Method navigation Failed", true);
				return status;
			}
		}
		
		@SuppressWarnings("finally")
		public boolean goToTransactionsPage(ExtentReports report,ExtentTest logger) throws EncryptedDocumentException, InvalidFormatException, IOException
		{
		
			boolean status =true;
			
			
			
			
			try {  
				Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
				Actions action = new Actions(driver);
				WebElement activitypointer =driver.findElement(By.xpath("//a[contains(text(),'Activity')]/img[@class='arrow_icon']"));
				action.moveToElement(activitypointer).build().perform();
				Thread.sleep(1000);
				WebElement transactionsmenu =driver.findElement(By.xpath("//div[@class='account_navigation-column']//a[contains(text(),'Transactions')]"));
				transactionsmenu.click();			
				
				System.out.println("Transactions page seen");  	
			}
			
			catch(Exception allexception)
			{
				status=false;
				System.out.println("Error occured in navigating to Transactions page"+allexception.getMessage());
				
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "Transactions page navigation Passed", false);
				}
				
				else
				
				
				r.addstep(report, logger, "Fail", testcasenumber,"Transactions page navigation Failed", true);
				return status;
			}
		}
		
		@SuppressWarnings("finally")
		public boolean goToStmtCorrPage(ExtentReports report,ExtentTest logger) throws EncryptedDocumentException, InvalidFormatException, IOException
		{
		
			boolean status =true;			
			
			
			try {  
				Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
				Actions action = new Actions(driver);
				WebElement activitypointer =driver.findElement(By.xpath("//a[contains(text(),'Activity')]/img[@class='arrow_icon']"));
				action.moveToElement(activitypointer).build().perform();
				Thread.sleep(1000);
				WebElement statementsmenu =driver.findElement(By.xpath("//div[@class='account_navigation-column']//a[contains(text(),'Statements')]"));
				statementsmenu.click();			
				
				System.out.println("statements page seen");  	
			}
			
			catch(Exception allexception)
			{
				status=false;
				System.out.println("Error occured in navigating to statements page"+allexception.getMessage());
				
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "statements page navigation Passed", false);
				}
				
				else			
				
				r.addstep(report, logger, "Fail", testcasenumber,"statements page navigation Failed", true);
				return status;
			}
		}
		
		@SuppressWarnings("finally")
		public boolean goToViolationsPage(ExtentReports report,ExtentTest logger) throws EncryptedDocumentException, InvalidFormatException, IOException
		{
		
			boolean status =true;
			
			
			
			
			try {  
				Thread.sleep(1000);
				Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
				Actions action = new Actions(driver);
				WebElement activitypointer =driver.findElement(By.xpath("//a[contains(text(),'My Account')]/img[@class='arrow_icon']"));
				action.moveToElement(activitypointer).build().perform();
				Thread.sleep(1000);
				WebElement transactionsmenu =driver.findElement(By.xpath("//div[@class='account_navigation-column']//a[contains(text(),'Violations')]"));
				transactionsmenu.click();
				Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
				
				System.out.println("Violations page seen");  	
			}
			
			catch(Exception allexception)
			{
				status=false;
				System.out.println("Error occured in navigating to Violations page"+allexception.getMessage());
				
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "Violations page navigation Passed", false);
				}
				
				else
				
				
				r.addstep(report, logger, "Fail", testcasenumber,"Violations page navigation Failed", true);
				return status;
			}
		}
		
		
		
	}
			

		    
	
	


