package Webpages;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;



public class creationTagsPage extends SuperBase {
	
	static Genric  g = new Genric();
	
	
	@SuppressWarnings("finally")
	public boolean filltagSectionPrivate(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		
		 boolean status =true;
			try{	
		    String country = g.ExcelRead(detail, Sheetpath, Rownum, 10);		   
		    String state = g.ExcelRead(detail, Sheetpath, Rownum, 8);
		    String platetype = g.ExcelRead(detail, Sheetpath, Rownum, 19);
		    String vehicletype = g.ExcelRead(detail, Sheetpath, Rownum, 20); 
		    String pmake = g.ExcelRead(detail, Sheetpath, Rownum, 21); 
		    String pmodel = g.ExcelRead(detail, Sheetpath, Rownum, 22); 
		    String pyear = g.ExcelRead(detail, Sheetpath, Rownum, 23); 
		    String interior = g.ExcelRead(detail, Sheetpath, Rownum, 30);
			String exterior = g.ExcelRead(detail, Sheetpath, Rownum, 31); 
			
			String isREntal = g.ExcelRead(detail, Sheetpath, Rownum, 31); 
		   	    
		    addplate(country,state, platetype, vehicletype, pmake, pmodel,pyear,isREntal) ; 
		    addtag(interior,exterior,"Private");
		    clickPlansPayments();
		  
		   
		    System.out.println("Completed filling tags and Payments Page");  	
			}
			catch(Exception allexception)
			{
				status =false;
				System.out.println("Error occured in filling tags and Payments Page "+allexception.getMessage());
							
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "tags and Payments Page Passed", false);
				}
				
				else
				
					r.addstep(report, logger, "Fail", testcasenumber,"tags and Payments Page Failed" , true);
				
				return status;
			}	
		
	}
	
	
	@SuppressWarnings("finally")
	public boolean filltagSectionBusiness(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		
		 boolean status =true;
			try{	
	   	    
				String interior = g.ExcelRead(detail, Sheetpath, Rownum, 30);
				String exterior = g.ExcelRead(detail, Sheetpath, Rownum, 31); 
				addtag(interior,exterior,"Business");
				clickPlansPayments();
		 
		   
		    System.out.println("Completed filling tags and Payments Page");  	
			}
			catch(Exception allexception)
			{
				status =false;
				System.out.println("Error occured in filling tags and Payments Page "+allexception.getMessage());
							
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "tags and Payments Page Passed", false);
				}
				
				else
				
					r.addstep(report, logger, "Fail", testcasenumber,"tags and Payments Page Failed" , true);
				
				return status;
			}
		    
			
		
		
	}
	
	
	
	
	
	@SuppressWarnings("finally")
	public boolean uploadVehicle(ExtentReports report,ExtentTest logger,String path) throws IOException
	{
		boolean status = true;
		try
		{
			WebElement vehicleupload = driver.findElement(By.name("fileUpload"));		
			vehicleupload.sendKeys(path);	
		}
		catch(Exception e)
		{
			status = false;
			
		}
		
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "tags and Payments Page Passed", false);
			}
			
			else
			
				r.addstep(report, logger, "Fail", testcasenumber,"tags and Payments Page Failed" , true);
			
			return status;
		}
		
	}
	
	
	
	
	
	
	
	
	public void addplate(String country,String state,String platetype,String vehicletype,String pmake, String pmodel,String pyear,String isRental) throws InterruptedException 
	{
		   
		
	   
	    String pname;
	    if(existingplate)
	    {
	    	pname = "G102022M";
	    }
	    else
	    {	
	    int RandomNum =  1000 +(int)(Math.random() * 9999); 	
		 pname = Genric.randomCharacter()+(RandomNum+"")+Genric.randomCharacter();
	    }
		    
		WebElement plate_number =driver.findElement(By.xpath("//input[@name='vehicleid[0].vehicleLicense']"));
		plate_number.sendKeys(pname);
		
        		
		WebElement plate_country = driver.findElement(By.xpath("//select[@name='vehicleid[0].vehicleCountry']"));
		plate_country.sendKeys(country);
	   
		
		WebElement plate_state = driver.findElement(By.xpath("//select[@name='vehicleid[0].vehicleState']"));
		plate_state.sendKeys(state);
		
		Select dropdown2 = new Select(driver.findElement(By.xpath("//select[@name='vehicleid[0].vehiclePlateType']")));
		dropdown2.selectByIndex(0);
		
		Select dropdown3 = new Select(driver.findElement(By.xpath("//select[@name='vehicleid[0].vehicleType']")));
		dropdown3.selectByIndex(Integer.parseInt(vehicletype));
		
		
		
		try{
		    // Waits for 20 seconds
		    WebDriverWait wait = new WebDriverWait(driver, 20);

		    // Wait until expected condition size of the dropdown increases and becomes more than 1
		    wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>(){
		        public Boolean apply(WebDriver driver)  
		        {
		        	Select dropdown4 = new Select(driver.findElement(By.xpath("//select[@name='vehicleid[0].vehicleMake']")));
					
				   
		            return dropdown4.getOptions().size()>1;
		        }
		    });

		    //To select the first option
		    
		    Select dropdown4 = new Select(driver.findElement(By.xpath("//select[@name='vehicleid[0].vehicleMake']")));
		    dropdown4.selectByIndex(Integer.parseInt(pmake));
		    if(isRental.equals('Y'))
		    {
		    			    	
		    	WebElement rentalflag = driver.findElement(By.id("rentalFlag"));
				rentalflag.click();	
				
				
				String Startdate = g.ExcelRead(detail, Sheetpath, Rownum, 40);							
				String Enddate =  g.ExcelRead(detail, Sheetpath, Rownum, 41); 				
				
				
				String starttime = "12:30 AM";	
				String endtime = "11:30 PM";
				
				WebElement startdatetxt = driver.findElement(By.name("vehicleStartDateField"));
				startdatetxt.sendKeys(Startdate);
				WebElement starttimetxt = driver.findElement(By.name("vehicleStartTimeField"));
				starttimetxt.sendKeys(starttime);
				WebElement enddatetxt = driver.findElement(By.name("vehicleStartDateField"));
				enddatetxt.sendKeys(Enddate);
				WebElement endtimetxt = driver.findElement(By.name("vehicleStartTimeField"));
				endtimetxt.sendKeys(endtime);	
		    }	
		
		
		Select dropdown5 = new Select(driver.findElement(By.xpath("//select[@name='vehicleid[0].vehicleModel']")));
		dropdown5.selectByIndex(Integer.parseInt(pmodel));
		
		
		WebElement year= driver.findElement(By.xpath("//input[@name='vehicleid[0].vehicleYear']"));
		year.sendKeys(pyear);
		
		}
        
		catch(Throwable e){
		    System.out.println("Error found: "+e.getMessage());
		}
	    
		
	}
	
	
	
	
	
	public void addtag(String interior,String exterior,String accounttype) throws InterruptedException
	{
		try{
			WebElement interiornum =driver.findElement(By.name("interiorTollTagsNo"));	
			WebElement exteriornum =driver.findElement(By.name("exteriorTollTagsNo"));
			if(accounttype.equals("Business"))
			{
				interiornum.clear();
				exteriornum.clear();
			}
			
		    interiornum.sendKeys(interior);			    
		    
		    exteriornum.sendKeys(exterior);
		}
		catch(Exception allexception)
		{
			System.out.println("Error occured in filling Plan Section "+allexception.getMessage());
		
		}
		
		
		   
		    
		    
	}
	
	public void clickPlansPayments() throws InterruptedException
	{
		try{
		Thread.sleep(2000);
		WebElement nextbtn =driver.findElement(By.name("btnPlansAndPayment"));
		nextbtn.click();	
		Thread.sleep(5000);
		}
		catch(Exception allexception)
		{
			System.out.println("Error occured in filling plan payment Section "+allexception.getMessage());
		
		}
		    
		    
	}
	
	
	@SuppressWarnings("finally")
	public boolean validateMessage(ExtentReports report,ExtentTest logger,String platenumber,String platestate) throws InterruptedException, IOException
	{
		boolean status = true;
	
		try{
		
		WebElement message =driver.findElement(By.xpath("//div[@class='alert alert-danger error_msg']"));
		String errormessage = message.getText();
		System.out.println(errormessage);
		String expectedmessage = "A vehicle with the Plate Number "+platenumber+" and State "+platestate+" is already registered. Please contact the E-ZPass Customer Service Center by calling 1-888-288-6865 for assistance.";
			if(errormessage.equals(expectedmessage))
			{
				status = true;	
			}
			else
			{
			   status =false;
			}
		}
		catch(Exception allexception)
		{
			status=false;
			System.out.println("Error message not seen "+allexception.getMessage());
		
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "message validated", true);
			}
			
			else
			
				r.addstep(report, logger, "Fail", testcasenumber,"Cannot validate message" , true);
			
			return status;
		}
	}
}
