package Webpages;



import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;

public class stmtCorresPage extends SuperBase {
static Genric  g = new Genric();	
	
	
	@SuppressWarnings("finally")
	public boolean searchStatement(ExtentReports report,ExtentTest logger,int Rownum) throws IOException 
	{
		boolean status =true;
		Genric g = new Genric();
		
		try {  
				
		    String date = g.ExcelRead(detail, Sheetpath, Rownum, 42);	
		    
		    Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");		
		   
		    
		    WebElement startdate = driver.findElement(By.id("startDate"));
		    startdate.click();
		    Thread.sleep(2000);
		    WebElement clearCalandar = driver.findElement(By.xpath("//div[div[@id='startDate']]//button[text()='Clear']"));
		    clearCalandar.click();		    
		    startdate.sendKeys(date);
		    
		    Thread.sleep(2000);
		    
		    WebElement enddate = driver.findElement(By.id("endDate"));
		    enddate.click();
		    Thread.sleep(2000);
		    WebElement todayCalandar = driver.findElement(By.xpath("//div[div[@id='endDate']]//button[text()='Today']"));
		    todayCalandar.click();
		    Thread.sleep(2000);
		    
		    WebElement viewStatement = driver.findElement(By.xpath("//table[@id='statementItem']//button[contains(text(),'VIEW')]"));
		    viewStatement.click();		   
		    
		    Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
		    

		    
		    System.out.println("Completed Statement search criteria"); 	
		    
			
		}
		catch(Exception allexception)
		{
			status =false;
			System.out.println("Error occured in Statement Search Section "+allexception.getMessage());			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Statement Search Sucessfull", false);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Statement Search Failed", true);
			return status;
		}
	}
	
	
	
	@SuppressWarnings("finally")
	public boolean viewStatement(ExtentReports report,ExtentTest logger) throws IOException 
	{
		boolean status =true;
		
		
		try {  
				
			String parentHandle = driver.getWindowHandle();
			for (String winHandle : driver.getWindowHandles()) {
			    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			
			if(driver.getCurrentUrl().contains("batastatement"))
			{	
			}
			else
			{
				
			WebElement pageverify = driver.findElement(By.xpath("//div[@class='alert alert-danger error_msg']"));
			String pageloaded = pageverify.getText().trim();
			
			if(pageloaded.contains("Please try again later."))
			{
				r.addstep(report, logger, "Fail", testcasenumber,"Statement not generated", true);
				driver.close();
			}
			else
			{
				r.addstep(report, logger, "Pass", testcasenumber,"Statement generated", true);
			}
			}
			
			
			
			
				
			driver.switchTo().window(parentHandle); 
			
			
		}
		catch(Exception allexception)
		{
			status =false;
			System.out.println("Error occured in Statement Search Section "+allexception.getMessage());			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Statement Search Sucessfull", true	);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Statement Search Failed", true);
			return status;
		}
	}
	
	
	
}
