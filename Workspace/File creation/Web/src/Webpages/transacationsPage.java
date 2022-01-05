package Webpages;



import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;

public class transacationsPage extends SuperBase {
static Genric  g = new Genric();	
	
	
	@SuppressWarnings("finally")
	public boolean searchByTranDateOptionalN(ExtentReports report,ExtentTest logger,int Rownum,String dateType) throws IOException 
	{
		boolean status =true;
		Genric g = new Genric();
		
		try {  
				
		    String date = g.ExcelRead(detail, Sheetpath, Rownum, 42);	
		    String deviceno = g.ExcelRead(detail, Sheetpath, Rownum, 41);	
		    String plateno = g.ExcelRead(detail, Sheetpath, Rownum,40);	
		    String accountno = g.ExcelRead(detail, Sheetpath, Rownum, 34);
		    Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
		    int index=1;
		    
		    if(dateType.equals("posted_date"))
		    {
		    	index=0;
		    }
		    
		    
		    
		    Select searchtype = new Select(driver.findElement(By.name("searchDateType")));
		    searchtype.selectByIndex(index);
			
		   
		    
		    WebElement startdate = driver.findElement(By.id("startDateAll"));
		    startdate.click();
		    Thread.sleep(2000);
		    WebElement clearCalandar = driver.findElement(By.xpath("//div[div[@id='startDateAll']]//button[text()='Clear']"));
		    clearCalandar.click();		    
		    startdate.sendKeys(date);
		    
		    Thread.sleep(2000);
		    
		    WebElement enddate = driver.findElement(By.id("endDateAll"));
		    enddate.click();
		    Thread.sleep(2000);
		    clearCalandar = driver.findElement(By.xpath("//div[div[@id='endDateAll']]//button[text()='Clear']"));
		    clearCalandar.click();
		    enddate.sendKeys(date);
		    
//		    if(!deviceno.equals("N"))
//		    {
//		    	WebElement devicenotxt = driver.findElement(By.id("transponderNumber"));
//		    	devicenotxt.sendKeys(deviceno);
//		    }
//		    if(!plateno.equals("N"))
//		    {
//		    	WebElement plateotxt = driver.findElement(By.id("licensePlateNumber"));
//		    	plateotxt.sendKeys(plateno);
//		    }
//		    
		    WebElement viewbtn = driver.findElement(By.name("btnView"));
		    viewbtn.click();
		    
		    Genric.waitForElementNotVisible(1,driver,"//div[@id='spinner']");
		    String columnName="Count";
		    
		   
			//String etcid =accountno.substring(4,accountno.length()-1)+"'";
		    String etcid = g.getetcaccountid(accountno);
		    
		    
		    String query = "select count(lane_tx_id) as "+columnName+" from t_account_toll where "+dateType+" =to_date('"+date+"','mm/dd/yyyy') and etc_account_id ='"+etcid;
				    
		    String resultcount = g.executefetchQuery(query,columnName);
		    
		    int expectedcount = driver.findElements(By.xpath("//span[@class='footable-toggle']")).size();
		    
		    if(!(expectedcount==Integer.parseInt(resultcount)))
		    {	
		    status =false;
		    r.addstep(report, logger, "Fail", testcasenumber,"Counts not matching", true);
		    }
		    else
		    {
		    	r.addstep(report, logger, "Pass", testcasenumber,"Counts matching", true);	
		    }
		    
		    
		    
		    System.out.println("Completed search criteria"); 	
		    
			
		}
		catch(Exception allexception)
		{
			status =false;
			System.out.println("Error occured in Search Section "+allexception.getMessage());			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Search using "+dateType+" Sucessfull", false);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Search using "+dateType+" Failed", true);
			return status;
		}
	}
	
		
	
	
	
}
