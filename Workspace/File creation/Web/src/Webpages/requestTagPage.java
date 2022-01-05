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

public class requestTagPage extends SuperBase {
	@SuppressWarnings("finally")
	public boolean requestTag(ExtentReports report,ExtentTest logger,int Rownum, String Mountype) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		Genric g = new Genric();
		boolean status =true;
			
		
		try {  
			
			WebElement actual =driver.findElement(By.xpath("//h1"));
			String actualtext =actual.getText().trim();
			
			
			if(actualtext.equals("Request Tags"))
			{
				r.addstep(report,logger, "Pass", testcasenumber, "Tag Request Navigation Sucessfull", false);
			}
			else
			{
				r.addstep(report,logger, "Fail", testcasenumber, "Tag Request Navigation Failed", false);
			}
			
			
			String interior = g.ExcelRead(detail, Sheetpath, Rownum, 30);
			String exterior = g.ExcelRead(detail, Sheetpath, Rownum, 31); 
			
		}
		
		catch(Exception allexception)
		{
			status=false;
			System.out.println("Error occured in navigating to Home page"+allexception.getMessage());
			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Home Page navigation Passed", false);
			}
			
			else
			
			r.addstep(report, logger, "Fail", testcasenumber,"Home Page navigation Failed", true);
			return status;
		}
		   
		    
	}
	
	
		
	}
			

		    
	
	


