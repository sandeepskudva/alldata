package Webpages;


import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;

public class paymentMethodPage extends SuperBase {
static Genric  g = new Genric();

@SuppressWarnings("finally")
public boolean primaryEdit(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException
{

	boolean status =true;
	
	
	
	
	try {  
		Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
		WebElement Edit =driver.findElement(By.xpath("//div[label[contains(text(),'Card Type:')]]//a[contains(text(),'Edit')]"));				
		Edit.click();
		Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
		String cardtype = g.ExcelRead(detail, Sheetpath, Rownum, 25);	
	    String cardnumber = g.ExcelRead(detail, Sheetpath, Rownum, 26);
	    String cmonth = g.ExcelRead(detail, Sheetpath, Rownum, 27);
	    String cyear = g.ExcelRead(detail, Sheetpath, Rownum, 28);
	    
	    System.out.println(cardtype+" : "+cardnumber+" : "+cmonth+" : "+cyear);
	    
	   
	       Select ctype = new Select(driver.findElement(By.xpath("//select[@name='cardType']")));
		   ctype.selectByVisibleText(cardtype);  
		   
		   WebElement cnumber = driver.findElement(By.xpath("//input[@name='accountNumber']"));
		   cnumber.clear();
		   cnumber.sendKeys(cardnumber);
		   
		   Select month = new Select(driver.findElement(By.xpath("//select[@name='expMonth']")));
		   month.selectByVisibleText(cmonth);

		   Select year = new Select(driver.findElement(By.xpath("//select[@name='expYear']")));
		   year.selectByVisibleText(cyear);	   
		  
		   
		   WebElement submit = driver.findElement(By.xpath("//button[@name='btnSave']"));
		   submit.click();
		   Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
		   Genric.alertMessageCheck("S", "Your Primary Card Changes were saved.");
	    
		
		
		System.out.println("Edit Primary card page seen");  	
	}
	
	catch(Exception allexception)
	{
		status=false;
		System.out.println("Error occured in Editing Primary Method"+allexception.getMessage());
		
	}
	finally
	{
		if(status)
		{	
		r.addstep(report,logger, "Pass", testcasenumber, "Editing Primary Payment Method successful", false);
		}
		
		else
		
		
		r.addstep(report, logger, "Fail", testcasenumber,"Editing Primary Payment Method Failed", true);
		return status;
	}
}

@SuppressWarnings("finally")
public boolean secondaryAddEdit(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException
{

	boolean status =true;
	
	
	
	
	try {  
		Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
		
		if(driver.findElements(By.xpath("//a[contains(text(),'Add')]")).size()==0)
		{
			r.addstep(report,logger, "Pass", testcasenumber, "Secondary Payment Method already Exists", true);	
		}
		else
		{	
		
		WebElement Add =driver.findElement(By.xpath("//a[contains(text(),'Add')]"));				
		Add.click();
		Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
		String cardtype = g.ExcelRead(detail, Sheetpath, Rownum, 32);	
		
	    String cardnumber = g.ExcelRead(detail, Sheetpath, Rownum, 33);
	    String cmonth = g.ExcelRead(detail, Sheetpath, Rownum, 27);
	    String cyear = g.ExcelRead(detail, Sheetpath, Rownum, 28);
	    
	    System.out.println(cardtype+" : "+cardnumber+" : "+cmonth+" : "+cyear);
	    
	   
	       Select ctype = new Select(driver.findElement(By.xpath("//select[@name='cardType']")));
		   ctype.selectByVisibleText(cardtype);  
		   
		   WebElement cnumber = driver.findElement(By.name("ccAccountNoMasked"));
		   cnumber.clear();
		   cnumber.sendKeys(cardnumber);
		   
		   Select month = new Select(driver.findElement(By.xpath("//select[@name='expMonth']")));
		   month.selectByVisibleText(cmonth);

		   Select year = new Select(driver.findElement(By.xpath("//select[@name='expYear']")));
		   year.selectByVisibleText(cyear);	 
		   
		   WebElement primarychk = driver.findElement(By.xpath("//ins[@class='iCheck-helper']"));
		  // if(primarychk.isSelected())
		   primarychk.click();   
		   
		   WebElement submit = driver.findElement(By.xpath("//button[@name='btnSave']"));
		   submit.click();
		   
		   Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
		   Genric.alertMessageCheck("S", "Your Secondary card was added.");
		   
		}
		   
		
		
		  	
	}
	
	catch(Exception allexception)
	{
		status=false;
		System.out.println("Error occured in Editing Secondary Method"+allexception.getMessage());
		
	}
	finally
	{
		if(status)
		{	
		r.addstep(report,logger, "Pass", testcasenumber, "Editing Secondary Payment Method successful", false);
		}
		
		else
		
		
		r.addstep(report, logger, "Fail", testcasenumber,"Editing Secondary Payment Method Failed", true);
		return status;
	}
}

@SuppressWarnings("finally")
public boolean validatePaymentPage(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException
{

	boolean status =true;
	
	
	
	
	try {  
		Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
		WebElement Pagemessage = driver.findElement(By.xpath("//div[div[div[h2[text()='Make Payment']]]]/div[2]/div"));
		String actualmessage = Pagemessage.getText();
		String expectedmessage = "You have outstanding violation tolls or fees. Please pay the open violations before you replenish your account.";
		System.out.println(actualmessage);
		if(actualmessage.equals(expectedmessage))
		status =true;
		else
		status = false;	
		
	}
		
	
	catch(Exception allexception)
	{
		status=false;
		System.out.println("Error occured in validating Message"+allexception.getMessage());
		
	}
	finally
	{
		if(status)
		{	
		r.addstep(report,logger, "Pass", testcasenumber, "Open Violations Message Validated successfully", true);
		}
		
		else
		
		
		r.addstep(report, logger, "Fail", testcasenumber,"Open Violations Message Validated Failed", true);
		return status;
	}
}

	
	
	

}
