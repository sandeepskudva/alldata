package Webpages;



import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;

public class violationsPage extends SuperBase {
static Genric  g = new Genric();	
	
	
	@SuppressWarnings("finally")
	public boolean violationSelection(ExtentReports report,ExtentTest logger,int Rownum) throws IOException 
	{
		boolean status =true;
		Genric g = new Genric();
		
		try {  
				
			Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");	
			String username=g.ExcelRead(detail, Sheetpath, Rownum, 14);
		  
			String citation_number=g.executefetchQuery("select citation_number from v_citation_detail where etc_account_id in (select etc_account_id from v_etc_account where user_name ='"+username+"') and rownum =1", "citation_number");
			
		    
		    WebElement checkbox = driver.findElement(By.xpath("//tr[td[a[text()='"+citation_number+"']]]//ins[@class='iCheck-helper']"));
		    checkbox.click();
		    Thread.sleep(2000);
		    WebElement paymentbtn = driver.findElement(By.name("btnPayment"));
		    paymentbtn.click();		
		    Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");		
		    
			
		}
		catch(Exception allexception)
		{
			status =false;
			System.out.println("Error occured in Citation Selection "+allexception.getMessage());			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Citation selection Sucessfull", false);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Citation selection Failed", true);
			return status;
		}
	}
	
	
	@SuppressWarnings("finally")
	public boolean payViolationCash(ExtentReports report,ExtentTest logger,int Rownum) throws IOException 
	{
		boolean status =true;
		try {  
				
		    String cardtype = g.ExcelRead(detail, Sheetpath, Rownum, 25);	
		    String cardnumber = g.ExcelRead(detail, Sheetpath, Rownum, 26);
		    String cmonth = g.ExcelRead(detail, Sheetpath, Rownum, 27);
		    String cyear = g.ExcelRead(detail, Sheetpath, Rownum, 28);
		    String ccode = g.ExcelRead(detail, Sheetpath, Rownum, 29);
		  
		   WebElement ctype = driver.findElement(By.name("cardType"));
		   ctype.sendKeys(cardtype);
		   WebElement cnumber = driver.findElement(By.name("accountNumber"));
		   cnumber.sendKeys(cardnumber);
		   WebElement month = driver.findElement(By.name("expMonth"));
		   month.sendKeys(cmonth);
		   WebElement year = driver.findElement(By.name("expYear"));
		   year.sendKeys(cyear);
		   WebElement code = driver.findElement(By.name("securityCode"));
		   code.sendKeys(ccode);	  
		   WebElement Continuebtn = driver.findElement(By.name("btnVerification"));
		   Continuebtn.click();
		   Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");		
		 
		  
		    System.out.println("Completed filling Violation Payments Page");  	
		    
			
		}
		catch(Exception allexception)
		{
			status =false;
			System.out.println("Error occured in filling Violation Payment Section "+allexception.getMessage());
			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Violation Payment Section Passed", false);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"ViolationPayment Section failed", true);
			return status;
		}
	}
	
		
	
	
	
}
