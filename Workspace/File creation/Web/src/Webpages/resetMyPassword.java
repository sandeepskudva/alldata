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




public class resetMyPassword  extends SuperBase {	
	
	
	@SuppressWarnings("finally")
	public boolean option1(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException 
	{
		boolean status=true;
		Genric  g = new Genric();
		
		String security1=g.ExcelRead(detail, Sheetpath, Rownum, 35);
		String[] s1 = security1.split("\\?");
		String squestion1 = s1[0]+"?";
		String sanswer1 = s1[1];
		
		String security2=g.ExcelRead(detail, Sheetpath, Rownum, 36);
		String[] s2 = security2.split("\\?");
		String squestion2 = s2[0]+"?";
		String sanswer2 = s2[1];
		
		String security3=g.ExcelRead(detail, Sheetpath, Rownum, 37);
		String[] s3 = security3.split("\\?");
		String squestion3 = s3[0]+"?";
		String sanswer3 = s3[1];
		
		
		
		try {			
		    
		    WebElement question = driver.findElement(By.xpath("//div[@class='bach']"));
		    WebElement answer = driver.findElement(By.id("tt_challengeAnswer"));
		    String securityquestion = question.getText();
		    System.out.println("Question is "+securityquestion);
		    if(securityquestion.contains(squestion1))
			{
		    	answer.sendKeys(sanswer1);
			}
		    else if(securityquestion.contains(squestion2))
			{
			    answer.sendKeys(sanswer2);
			}
		    else if(securityquestion.contains(squestion3))
			{
			    answer.sendKeys(sanswer3);
			}
		    else
		    {
		    	answer.sendKeys("wrong answer");
		    }
		    
		    
				    
		    driver.findElement(By.name("btnSubmit")).click();
		    
		    Thread.sleep(2000);
		    WebElement pageverify = driver.findElement(By.xpath("//h1"));
			
			String pageloaded = pageverify.getText().trim();
			Thread.sleep(2000);
			if(pageloaded.equals("Reset Password"))
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
			
			System.out.println("Error occured in filling option1"+allexception.getMessage());			
			
		}
		
		
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "PASS", testcasenumber, "filling option1 Sucessfull", true);
			}
			
			else{			
			r.addstep(report, logger, "Fail", testcasenumber,"filling option1 failed", true);
			}
			
			return status;
		}
		

	}
	
	@SuppressWarnings("finally")
	public boolean fillnewpassowrd(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException 
	{
		boolean status=true;
		Genric  g = new Genric();
		
		String passphrase=g.ExcelRead(detail, Sheetpath, Rownum, 15);
		
		
		
		try {			
		    
		    WebElement password = driver.findElement(By.id("tt_newPassword"));
		    WebElement confpassword = driver.findElement(By.id("tt_retypePassword"));
		    WebElement savebtn = driver.findElement(By.name("btnSubmit.x"));
		    
		    password.sendKeys(passphrase);
		    confpassword.sendKeys(passphrase);
		    savebtn.click();
		    
		    Thread.sleep(2000);
		    WebElement pageverify = driver.findElement(By.xpath("//div[@class='alert alert-success message_tag']"));
			
			String pageloaded = pageverify.getText().trim();
			Thread.sleep(2000);
			
			
			if(pageloaded.equals("Password Changed Successfully."))
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
			
			System.out.println("Error occured in filling option1"+allexception.getMessage());			
			
		}
		
		
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "PASS", testcasenumber, "Password Retrieval Sucessfull", true);
			}
			
			else{			
			r.addstep(report, logger, "Fail", testcasenumber,"Password Retrieval failed", true);
			}
			
			return status;
		}
		

	}
	
	
	
}
