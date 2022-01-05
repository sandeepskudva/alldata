package Webpages;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;




public class webLogin  extends SuperBase {	
	
	
	@SuppressWarnings("finally")
	public boolean login(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException 
	{
		boolean status=true;
		Genric  g = new Genric();
		String username=g.ExcelRead(detail, Sheetpath, Rownum, 14);
		String password=g.ExcelRead(detail, Sheetpath, Rownum, 15);
		try {
			
		    
		    WebElement logintxtbox = driver.findElement(By.id("login"));
		    logintxtbox.sendKeys(username);
		    WebElement passwordtxtbox = driver.findElement(By.id("loginPassword"));
		    passwordtxtbox.sendKeys(password);
			
		    WebElement ele = driver.findElement(By.xpath("//img[@border=0]"));	
		    String spath =Genric.elementscreenshot(driver,"image", ele, captchapath);
		    String imageText =g.imageextract(spath).trim();		    
		    driver.findElement(By.id("jcaptcha_response")).sendKeys(imageText);		    
		    
		    driver.findElement(By.name("btnLogin")).click();
		    
		    Thread.sleep(2000);
		    
		    String currentURL = driver.getCurrentUrl();
		    System.out.println(currentURL);
		    if(currentURL.contains("error"))
		    status =false;	
		}
		
		
		catch(Exception allexception)
		{
			
			status =false;
			
			System.out.println("Error occured in Login"+allexception.getMessage());			
			
		}
		
		
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "INFO", testcasenumber, "Login Executed Sucessfully", false);
			}
			
			else{			
			r.addstep(report, logger, "Fail", testcasenumber,"Login failed", true);
			}
			
			return status;
		}
		

	}
	
	@SuppressWarnings("finally")
	public boolean gotologinmodule(ExtentReports report,ExtentTest logger) throws IOException
	{
		boolean status=true;
		try
		{
		WebElement loginmodule = driver.findElement(By.xpath("//a[contains(text(),'Click Here To Log In')]"));
	    loginmodule.click();
		}
	    finally
		{
			if(status)
			{	
			r.addstep(report,logger, "INFO", testcasenumber, "Login module Sucessfull", true);
			}
			
			else{			
			r.addstep(report, logger, "Fail", testcasenumber,"Login module failed", true);
			}
			
			return status;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean forgotPassword(ExtentReports report,ExtentTest logger) throws IOException
	{
		boolean status=true;
		try
		{
		WebElement forgotp = driver.findElement(By.xpath("//a[contains(text(),'Forgot Username')]"));
		forgotp.click();
		String currentURL = driver.getCurrentUrl();
	    System.out.println(currentURL);
	    if(currentURL.contains("error"))
	    status =false;
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
		WebElement pageverify = driver.findElement(By.xpath("//h1"));
		
		String pageloaded = pageverify.getText().trim();
		Thread.sleep(2000);
		if(pageloaded.equals("Forgot Password or Username"))
		{
			status=true;
		}
		else
		{
			status=false;
			
		}
		}
	    finally
		{
			if(status)
			{	
			r.addstep(report,logger, "INFO", testcasenumber, "Forgot Password navigation Sucessfull", true);
			}
			
			else{			
			r.addstep(report, logger, "Fail", testcasenumber,"Forgot Password navigation failed", true);
			}
			
			return status;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean forgotusername(ExtentReports report,ExtentTest logger) throws IOException
	{
		boolean status=true;
		try
		{
		WebElement forgotp = driver.findElement(By.xpath("//a[contains(text(),'Forgot Username')]"));
		forgotp.click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
		WebElement pageverify = driver.findElement(By.xpath("//h1"));
		
		String pageloaded = pageverify.getText().trim();
		Thread.sleep(2000);
		if(pageloaded.equals("Forgot Password or Username"))
		{
			status=true;
		}
		else
		{
			status=false;
			
		}
		}
	    finally
		{
			if(status)
			{	
			r.addstep(report,logger, "INFO", testcasenumber, "Forgot Password navigation Sucessfull", true);
			}
			
			else{			
			r.addstep(report, logger, "Fail", testcasenumber,"Forgot Password navigation failed", true);
			}
			
			return status;
		}
	}
	@SuppressWarnings("finally")
	public boolean usernameextract(ExtentReports report,ExtentTest logger) throws IOException
	{
		boolean status=true;
		try
		{
		WebElement usernamecatch = driver.findElement(By.xpath("//strong"));
		String username = usernamecatch.getText().trim();
		
		if(!username.isEmpty())
		{
			status=true;
		}
		else
		{
			status=false;
			
		}
		}
	    finally
		{
			if(status)
			{	
			r.addstep(report,logger, "PASS", testcasenumber, "Username sucessfully retrieved", true);
			}
			
			else{			
			r.addstep(report, logger, "Fail", testcasenumber,"Username retrieval failed", true);
			}
			
			return status;
		}
	}
	
}
