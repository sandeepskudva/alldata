package Webpages;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;




public class forgotPasswordUsername  extends SuperBase {	
	
	
	@SuppressWarnings("finally")
	public boolean passwordRet(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException 
	{
		boolean status=true;
		Genric  g = new Genric();
		String username=g.ExcelRead(detail, Sheetpath, Rownum, 14);
		System.out.println(username);
		String zipcode=g.ExcelRead(detail, Sheetpath, Rownum, 9);
		System.out.println(zipcode);
		try {			
			
			
		    WebElement usernametxt = driver.findElement(By.name("username"));
		    usernametxt.sendKeys(username);
		    WebElement zipcodetxt = driver.findElement(By.id("tt_pzipCode"));
		    zipcodetxt.sendKeys(zipcode);
				    
		    driver.findElement(By.name("btnSubmit")).click();
		    
		    Thread.sleep(2000);
		    WebElement pageverify = driver.findElement(By.xpath("//h1"));
			
			String pageloaded = pageverify.getText().trim();
			Thread.sleep(2000);
			if(pageloaded.equals("Reset My Password"))
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
			
			status =false;
			
			System.out.println("Error occured in Password retrieval "+allexception.getMessage());			
			
		}
		
		
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "PASS", testcasenumber, "filling Password Retrieval form Sucessfull", true);
			}
			
			else{			
			r.addstep(report, logger, "Fail", testcasenumber,"filling Password Retrieval form failed", true);
			}
			
			return status;
		}
		

	}
	
	@SuppressWarnings("finally")
	public boolean usernameRet(ExtentReports report,ExtentTest logger,int Rownum,String AORT) throws EncryptedDocumentException, InvalidFormatException, IOException 
	{
		boolean status=true;
		Genric  g = new Genric();
		String tagoraccount=g.ExcelRead(detail, Sheetpath, Rownum, 34);
		String zipcode=g.ExcelRead(detail, Sheetpath, Rownum, 9);
		try {			
		    if(AORT.equals("A"))
		    {
		    WebElement accnumbertxt = driver.findElement(By.id("tt_accountNumber"));
		    accnumbertxt.sendKeys(tagoraccount);
		    }
		    else
		    {	
		    WebElement tagnumbertxt = driver.findElement(By.id("tt_accountNumber"));
		    tagnumbertxt.sendKeys(tagoraccount);
		    }
		    WebElement zipcodetxt = driver.findElement(By.id("tt_uzipCode"));
		    zipcodetxt.sendKeys(zipcode);	    
		    driver.findElement(By.name("btnLookup")).click();
		    
		    Thread.sleep(2000);
		}
		
		
		catch(Exception allexception)
		{
			
			status =false;
			
			System.out.println("Error occured in username retrieval"+allexception.getMessage());			
			
		}
		
		
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "PASS", testcasenumber, "username Retrieval Sucessfull", true);
			}
			
			else{			
			r.addstep(report, logger, "Fail", testcasenumber,"username Retrieval failed", true);
			}
			
			return status;
		}
		

	}
	
	
	
	
}
