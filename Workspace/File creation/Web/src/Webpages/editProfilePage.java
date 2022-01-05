package Webpages;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;

public class editProfilePage extends SuperBase {
	
	
	
	
	@SuppressWarnings("finally")
	public boolean emailSection(ExtentReports report,ExtentTest logger, int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		boolean status =true;
		try{
		Genric g = new Genric();
		String newEmail = g.ExcelRead(detail, Sheetpath, Rownum, 11);
		
		WebElement emailaddress = driver.findElement(By.name("emailAddress"));
		emailaddress.clear();		
		emailaddress.sendKeys(newEmail);
		WebElement confirmEmailaddress = driver.findElement(By.name("reEmailAddress"));
		confirmEmailaddress.clear();
		confirmEmailaddress.sendKeys(newEmail);
		
		WebElement updatebtn = driver.findElement(By.name("btnUpdate"));
		updatebtn.click();
		
		WebElement pageverify = driver.findElement(By.xpath("//div[@class='alert alert-success message_tag']"));
		
		String pageloaded = pageverify.getText().trim();
		Thread.sleep(2000);
		
		
		if(pageloaded.equals("Account Details Updated Successfully."))
		{
			status=true;
		}
		else
		{
			status=false;
			
		}
		
		}
		catch(Exception E)
		{
			status =false;			
			System.out.println("Error occured in updating email"+E.getMessage());	
		}
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Primary Email section updation Passed", true);
			}
			
			else			
			r.addstep(report, logger, "Fail", testcasenumber,"Primary Email section updation Failed", true);
			return status;
			
		}
		
	}
	
	
	
	@SuppressWarnings("finally")
	public boolean addSecondaryEmail(ExtentReports report,ExtentTest logger, int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		boolean status =true;
		try{
		Genric g = new Genric();
		String secondaryemail = g.ExcelRead(detail, Sheetpath, Rownum, 38);

		
		WebElement secondarylink = driver.findElement(By.id("secondaryEmailLink"));
		
		if(secondarylink.isDisplayed())
		{	
		secondarylink.click();
		WebElement secondaryaddress = driver.findElement(By.name("secondaryEmailAddress"));
		secondaryaddress.sendKeys(secondaryemail);
		
		WebElement confirmsddress = driver.findElement(By.name("reSecondaryEmailAddress"));
		confirmsddress.sendKeys(secondaryemail);
		
		WebElement updatebtn = driver.findElement(By.name("btnUpdate"));
		updatebtn.click();
		
		}
		else			
		System.out.println("Secondary Email present already");		
		
		Thread.sleep(2000);
		
		
		
		}
		catch(Exception E)
		{
			status =false;			
			System.out.println("Error occured in updating email"+E.getMessage());	
		}
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Secondary Email section updation Passed", true);
			}
			
			else			
			r.addstep(report, logger, "Fail", testcasenumber,"Secondary Email section updation Failed", true);
			return status;
			
		}
		
	}
	
	
	
	@SuppressWarnings("finally")
	public boolean addressSection(ExtentReports report,ExtentTest logger, int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		boolean status =true;
		try{
		Genric g = new Genric();
		String add1 = g.ExcelRead(detail, Sheetpath, Rownum, 5);
	    String add2 = g.ExcelRead(detail, Sheetpath, Rownum, 6);
	    String city = g.ExcelRead(detail, Sheetpath, Rownum, 7);
	    String state = g.ExcelRead(detail, Sheetpath, Rownum, 8);
	    String zip = g.ExcelRead(detail, Sheetpath, Rownum, 9);
		
		WebElement addresstxt1 = driver.findElement(By.name("addressLine1"));
		addresstxt1.clear();
		addresstxt1.sendKeys(add1);
		WebElement addresstxt2 = driver.findElement(By.name("addressLine2"));
		addresstxt2.clear();
		addresstxt2.sendKeys(add2);
		WebElement citytxt = driver.findElement(By.name("city"));
		citytxt.clear();
		citytxt.sendKeys(city);
		WebElement statetxt = driver.findElement(By.name("stateType"));
	    statetxt.sendKeys(state);
		WebElement ziptxt = driver.findElement(By.name("zipCode"));
		ziptxt.clear();
		ziptxt.sendKeys(zip);
		WebElement zipplustxt = driver.findElement(By.name("zipCodePlus"));		
		zipplustxt.clear();
		
		Thread.sleep(2000);
		
		WebElement updatebtn = driver.findElement(By.xpath("//div[@class='profile_box']//button[@name='btnUpdate']"));
		updatebtn.click();
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='adrsModal']//div[@class='modal-dialog']")));
		
		WebElement standbox = driver.findElement(By.xpath("//div[@id='adrsModal']//div[@class='modal-dialog']"));
		
		r.addstepelement(report, logger, "PASS", testcasenumber, "Standardized Address",standbox);
		
		WebElement ok = driver.findElement(By.name("btnAcceptAddress"));
		ok.click();
		
		WebElement pageverify = driver.findElement(By.xpath("//div[@class='alert alert-success message_tag']"));
		
		String pageloaded = pageverify.getText().trim();
		Thread.sleep(2000);
		
		
		if(pageloaded.equals("Account Details Updated Successfully."))
		{
			status=true;
		}
		else
		{
			status=false;
			
		}
		
				
		
		}
		catch(Exception E)
		{
			status =false;			
			System.out.println("Error occured in Login"+E.getMessage());	
		}
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Address section updation Passed", true);
			}
			
			else			
			r.addstep(report, logger, "Fail", testcasenumber,"Address section updation Failed", true);
			return status;
			
		}
		
	}

	public void contactSection(ExtentReports report,ExtentTest logger, int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		Genric g = new Genric();
		String phone = g.ExcelRead(detail, Sheetpath, Rownum, 12);	    
		
		WebElement cellphonetxt = driver.findElement(By.name("cellPhone"));
		cellphonetxt.sendKeys(phone);	
		
		WebElement updatebtn = driver.findElement(By.name("btnUpdate"));
		updatebtn.click();
		
	}
	
	@SuppressWarnings("finally")
	public boolean statementSection(ExtentReports report,ExtentTest logger, int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		Genric g = new Genric();
		boolean status = true;
		try{
		String statementtype = g.ExcelRead(detail, Sheetpath, Rownum, 13);
		
		Select statementdp = new Select(driver.findElement(By.xpath("//select[@name='deliveryType']")));
		statementdp.selectByIndex(Integer.parseInt(statementtype));		
		
		WebElement updatebtn = driver.findElement(By.name("btnUpdate"));
		updatebtn.click();
		}
		catch(Exception E)
		{
			status =false;			
			System.out.println("Error occured in Statement Delivery updation"+E.getMessage());	
		}
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Statement delivery section updation Passed", true);
			}
			
			else			
			r.addstep(report, logger, "Fail", testcasenumber,"Statement delivery section updation Failed", true);
			return status;
			
		}
		
	}
	
	@SuppressWarnings("finally")
	public boolean securitychangePassword(ExtentReports report,ExtentTest logger, int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		Genric g = new Genric();
		boolean status = true;
		String currentpassword = g.ExcelRead(detail, Sheetpath, Rownum, 15);
		String newpassword=null;
		if(currentpassword.equals("Welcome1"))
		{
			newpassword = "Welcome2";
		}
		else
		{
			newpassword = "Welcome1";
		}
		try{
		
		
		
		WebElement oldpaswd = driver.findElement(By.name("currentPassword2"));
		oldpaswd.sendKeys(currentpassword);
		
		WebElement newpaswd = driver.findElement(By.name("newPassword2"));
		newpaswd.sendKeys(newpassword);
		
		WebElement renewpaswd = driver.findElement(By.name("retypeNewPassword2"));
		renewpaswd.sendKeys(newpassword);	
		
		String security3=g.ExcelRead(detail, Sheetpath, Rownum, 37);
		String[] s3 = security3.split("\\?");
		String squestion3 = s3[0]+"?";
		//String sanswer3 = s3[1];
		
		Select question3 = new Select(driver.findElement(By.name("challengeQuestionThree")));
		question3.selectByVisibleText(squestion3);
		
		
	
		
	
		WebElement updatebtn = driver.findElement(By.xpath("//button[@name='btnSavePassword']"));
		updatebtn.click();	
		
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
		catch(Exception E)
		{
			status =false;			
			System.out.println("Error occured in Password updation"+E.getMessage());	
		}
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Password Changed Sucessfully", true);
			g.ExcelWrite(detail, Sheetpath, Rownum, 15, newpassword);
			}
			
			else			
			r.addstep(report, logger, "Fail", testcasenumber,"Unable to change Password", true);
			return status;
			
		}
		
	}
	
	@SuppressWarnings("finally")
	public boolean securitychangeQuestions(ExtentReports report,ExtentTest logger, int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		Thread.sleep(3000);
		Genric g = new Genric();
		boolean status = true;
		try{
			WebElement securitylink = driver.findElement(By.id("showPasswordLink"));
			securitylink.click();
			
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
		
		
			Select question1 = new Select(driver.findElement(By.name("challengeQuestion")));
			question1.selectByVisibleText(squestion1);
			
			WebElement answer1 = driver.findElement(By.name("challengeAnswer"));
			answer1.clear();
			answer1.sendKeys(sanswer1);
			
			Select question2 = new Select(driver.findElement(By.name("challengeQuestionTwo")));
			question2.selectByVisibleText(squestion2);
			
			WebElement answer2 = driver.findElement(By.name("challengeAnswerTwo"));
			answer2.clear();
			answer2.sendKeys(sanswer2);
			
			Select question3 = new Select(driver.findElement(By.name("challengeQuestionThree")));
			question3.selectByVisibleText(squestion3);
			
			WebElement answer3 = driver.findElement(By.name("challengeAnswerThree"));
			answer3.clear();
			answer3.sendKeys(sanswer3);
			
			String pin=g.ExcelRead(detail, Sheetpath, Rownum, 18);
			
			WebElement pintxt = driver.findElement(By.name("securityPin"));
			pintxt.clear();
			pintxt.sendKeys(pin);	
		
			WebElement updatebtn = driver.findElement(By.xpath("//button[@name='btnSavePassword']"));
			updatebtn.click();
			Thread.sleep(2000);
		    WebElement pageverify = driver.findElement(By.xpath("//div[@class='alert alert-success message_tag']"));
			
			String pageloaded = pageverify.getText().trim();
			Thread.sleep(2000);
			
			
			if(pageloaded.equals("Account Details Updated Successfully."))
			{
				status=true;
			}
			else
			{
				status=false;
				
			}
		}		
			
			
			
		catch(Exception E)
		{
			status =false;			
			System.out.println("Error occured in security section updation"+E.getMessage());	
		}
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Security section updation Passed", true);
			}
			
			else			
			r.addstep(report, logger, "Fail", testcasenumber,"Security section updation Failed", true);
			return status;
			
		}
		
	}
	
	
	@SuppressWarnings("finally")
	public boolean goToCloseAccount(ExtentReports report,ExtentTest logger,String Reason) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		Thread.sleep(3000);
		Genric g = new Genric();
		boolean status = true;
		try{
			Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
			WebElement closebtn = driver.findElement(By.id("closeAccountLink"));
			closebtn.click();
		    g.alwayswait(1);
			WebElement confirmYes = driver.findElement(By.xpath("//div[@id='closePopUp']//a[text()='Yes']"));
			confirmYes.click();
			Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
			
			Select closereasondd = new Select(driver.findElement(By.name("closeReason")));
			closereasondd.selectByVisibleText(Reason);
			
			WebElement closeaccountbtn = driver.findElement(By.name("btnCloseAccount"));
			closeaccountbtn.click();
			
			Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
			
			WebElement closeaccountMessage = driver.findElement(By.xpath("//h2"));
			closeaccountMessage.getText();
			
			if(!closeaccountMessage.equals("Your Account will be closed in 10 business days"))
			{
			 status = false	;
			}
			
		}		
			
			
			
		catch(Exception E)
		{
			status =false;			
			System.out.println("Error occured in navigating to account closure screen"+E.getMessage());	
		}
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Account Closure Navigation Passed", true);
			}
			
			else			
			r.addstep(report, logger, "Fail", testcasenumber,"Account Closure Navigation Failed", true);
			return status;
			
		}
		
	}
	
	
}
