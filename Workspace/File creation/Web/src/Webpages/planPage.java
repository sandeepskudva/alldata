package Webpages;



import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;

public class planPage extends SuperBase {
static Genric  g = new Genric();
	
	

		
	
	
	
	@SuppressWarnings("finally")
	public boolean fillPlanSection(ExtentReports report,ExtentTest logger,int Rownum) throws IOException 
	{
		boolean status =true;
		
		try {  
				
		    String plans = g.ExcelRead(detail, Sheetpath, Rownum, 24);	
		    
			String[] plan = plans.split(",");
		
			for(int i=0;i<plan.length;i++)
			{
				 addplanAccountCreation(report, logger, plan[i]);
			}	 
		   
		    System.out.println("Completed filling plan Page");  	
		    
			
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
			r.addstep(report,logger, "Pass", testcasenumber, "Plan adding Sucessfull", false);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Plan adding Failed", true);
			return status;
		}
	}
	
		
	
	
	public void addplanAccountCreation(ExtentReports report,ExtentTest logger,String plan) throws InterruptedException, IOException
	{
		String buildplan = "//tr[td[text()='"+plan+"']]/td/div/ins[@class='iCheck-helper']";
		WebElement selectplan = driver.findElement(By.xpath(buildplan));
	    if(plan.equals("STANDARD"))
	    {
	    	new Actions(driver).moveToElement(selectplan).perform();;
	    	System.out.println("Plan is "+plan);
	    	r.addstep(report, logger, "Info", testcasenumber, plan+" is added", true);
	    }
	    else if(plan.equals("BUSINESS"))
	    {
	    	new Actions(driver).moveToElement(selectplan).perform();
	    	System.out.println("Plan is "+plan);
	    	r.addstep(report, logger, "Info", testcasenumber, plan+" is added", true);
	    }
	    else
	    {
	    System.out.println("Plan is "+plan);
	   
	    selectplan.click();
	    r.addstep(report, logger, "Info", testcasenumber, plan+" is added", true);
	    
	    }	
	
	}
	
	@SuppressWarnings("finally")
	public boolean addPlan(ExtentReports report,ExtentTest logger,String PlanName, boolean Tagspecific,boolean existingTag) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{

		boolean status =true;
		
		
		
		
		try {  
			Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
			Thread.sleep(2000);
			Select plandd = new Select(driver.findElement(By.id("planName")));
			plandd.selectByValue(PlanName);	 
			driver.findElement(By.id("planName")).click();
			Thread.sleep(5000);
			
			if(Tagspecific)
			{
			
				if(existingTag)
				{
					Select devicedd = new Select(driver.findElement(By.name("addTransponderNumber")));
					devicedd.selectByIndex(1);	 
					
				}
				else
				{
					Select mountdd = new Select(driver.findElement(By.name("addTransponderType")));
					mountdd.selectByIndex(1); 
					Select vehtypedd = new Select(driver.findElement(By.name("vehicle")));
					vehtypedd.selectByIndex(1);  
					
				}
				
			}
			
			WebElement addPlan = driver.findElement(By.name("btnAddPlan"));
			addPlan.click();
			Thread.sleep(5000);
			
			Genric.alertMessageCheck("S", "Plan "+PlanName+" was added.");
		}
			
		
		catch(Exception allexception)
		{
			status=false;
			System.out.println("Error occured in adding Plan :"+allexception.getMessage());
			
		}
		finally
		{
			if(status)
			{	
			
			r.addstep(report,logger, "Pass", testcasenumber, "Plan successfully added", true);
			}
			
			else
			
			
			r.addstep(report, logger, "Fail", testcasenumber,"Plan addition Failed", true);
			return status;
		}
	}

}
