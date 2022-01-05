package Webpages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.SuperBase;




public class signupPage  extends SuperBase {	
	
	


	public signupPage()
	{
		
		
		PageFactory.initElements(driver, this);
		
	}
	

	
	
	@SuppressWarnings("finally")
	public boolean CreatePrivate(ExtentReports report,ExtentTest logger) throws IOException 
	{
		boolean status=true;
		try {
	   WebElement agree =driver.findElement(By.xpath("//div[input[@id='agreeCheckbox1']]//ins[@class='iCheck-helper']"));
		agree.click();
		WebElement agreelink =driver.findElement(By.xpath("//a[@id='agreeLink1']/span"));
		agreelink.click();
		Thread.sleep(1000);
		
		  String currentURL = driver.getCurrentUrl();
		    System.out.println(currentURL);
		    if(currentURL.contains("error"))
		    status =false;
		System.out.println("Private account signup..."); 
		
		
		
		}
		catch(Exception allexception)
		{
			
			status =false;
			
			System.out.println("Error occured in filling Private account Signup "+allexception.getMessage());			
			
		}
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Info", testcasenumber, "Private account signup...", false);
			}
			
			else{			
			r.addstep(report, logger, "Fail", testcasenumber,"Private account Signup failed", true);
			}
			
			return status;
		}
		

	}
	
	@SuppressWarnings("finally")
	public boolean CreateBusiness(ExtentReports report,ExtentTest logger) throws InterruptedException, IOException
	{
		
			boolean status=true;
	        try{
			WebElement agree =driver.findElement(By.xpath("//div[input[@id='agreeCheckbox2']]//ins[@class='iCheck-helper']"));
		    agree.click();
		    WebElement agreelink =driver.findElement(By.xpath("//a[@id='agreeLink2']/span"));
		    agreelink.click();
		    System.out.println("Business account signup..."); 
		    
		      Thread.sleep(1000);
			
			  String currentURL = driver.getCurrentUrl();
			    System.out.println(currentURL);
			    if(currentURL.contains("error"))
			    status =false;
		    
	        }
		    
		    catch(Exception allexception)
			{
		    	status =false;
				System.out.println("Error occured in filling Plan Section "+allexception.getMessage());				
				
			}
	        finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Info", testcasenumber, "Business account signup...", false);
				}
				
				else
				{				
				r.addstep(report, logger, "Fail", testcasenumber,"Plan section failed", true);
				}
				
				return status;
			}
	
	}

}
