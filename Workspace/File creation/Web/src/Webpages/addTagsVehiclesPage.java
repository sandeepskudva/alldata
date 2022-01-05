package Webpages;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;



public class addTagsVehiclesPage extends SuperBase {
	
	static Genric  g = new Genric();
	
	
	@SuppressWarnings("finally")
	public boolean goToAddVechile(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		
		 boolean status =true;
			try{	
				
			WebElement addvehiclebtn = driver.findElement(By.name("btnAddVehicle"))	;
			addvehiclebtn.click();	    
		     System.out.println("Adding Vehcile");  	
			}
			catch(Exception allexception)
			{
				status =false;
				System.out.println("Error occured in add vehicle Navigation"+allexception.getMessage());
							
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "Add vehicle Navigation Page Passed", false);
				}
				
				else			
					r.addstep(report, logger, "Fail", testcasenumber,"Add vehicle Navigation Page Failed" , true);
				
				return status;
			}		
		
	}
	
	@SuppressWarnings("finally")
	public boolean addVechile(ExtentReports report,ExtentTest logger,int Rownum,String pnumber) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		
		 boolean status =true;
			try{	
				if(pnumber.equals("0000"))
				{
				pnumber = g.ExcelRead(detail, Sheetpath, Rownum, 40).toUpperCase();
				}
				else
				{
				int RandomNum =  1000 +(int)(Math.random() * 9999); 	
				pnumber = Genric.randomCharacter()+(RandomNum+"")+Genric.randomCharacter();
				g.ExcelWrite(detail, Sheetpath, Rownum, 40, pnumber.toUpperCase());    
				}
				
				String country = g.ExcelRead(detail, Sheetpath, Rownum, 10);		   
			    String state = g.ExcelRead(detail, Sheetpath, Rownum, 8);
			   // String platetype = g.ExcelRead(detail, Sheetpath, Rownum, 19);
			    String vehicletype = g.ExcelRead(detail, Sheetpath, Rownum, 20); 
			    String pmake = g.ExcelRead(detail, Sheetpath, Rownum, 21); 
			    String pmodel = g.ExcelRead(detail, Sheetpath, Rownum, 22); 
			    String pyear = g.ExcelRead(detail, Sheetpath, Rownum, 23); 
			    String isREntal = g.ExcelRead(detail, Sheetpath, Rownum, 39);
			    
			    
			    Thread.sleep(1000);
				
				WebElement platebumbertxt = driver.findElement(By.name("licensePlate"));
				platebumbertxt.sendKeys(pnumber.toUpperCase());
				
				WebElement platestate = driver.findElement(By.id("stateType_select"));
				platestate.sendKeys(state);
				
				WebElement platecountry = driver.findElement(By.name("plateCountry"));
				platecountry.sendKeys(country);
											
				Select platetypedd = new Select(driver.findElement(By.name("vehiclePlateType")));
				platetypedd.selectByVisibleText("STANDARD");
				
						
				Select makedd = new Select(driver.findElement(By.name("vehicleMake")));
				makedd.selectByIndex(Integer.parseInt(pmake));
				
				Thread.sleep(1000);
				
				Select modeldd = new Select(driver.findElement(By.id("modelType")));
				modeldd.selectByIndex(Integer.parseInt(pmodel));					
				
				WebElement yeartxt = driver.findElement(By.name("vehicleYear"));
				yeartxt.sendKeys(pyear);			
				
				Select vehicletypedd = new Select(driver.findElement(By.id("vehicleType")));
				vehicletypedd.selectByIndex(Integer.parseInt(vehicletype));		
				
				
				if(isREntal.equals("Y"))
				{
				WebElement rentalflag = driver.findElement(By.xpath("//ins[@class='iCheck-helper']"));
				rentalflag.click();
				
				   Date date = new Date(System.currentTimeMillis());
				    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				   			
			     	String Startdate =	formatter.format(date);
				
				
				String Enddate =  formatter.format(date);
				
				String starttime = "12:30 AM";	
				String endtime = "11:30 PM";
				
				WebElement startdatetxt = driver.findElement(By.name("vehicleStartDateField"));
				startdatetxt.sendKeys(Startdate);
				WebElement starttimetxt = driver.findElement(By.name("vehicleStartTimeField"));
				starttimetxt.sendKeys(starttime);
				WebElement enddatetxt = driver.findElement(By.name("vehicleEndDateField"));
				enddatetxt.sendKeys(Enddate);
				WebElement endtimetxt = driver.findElement(By.name("vehicleEndTimeField"));
				endtimetxt.sendKeys(endtime);				
							
				}	
				Genric.presstab();
				Genric.presstab();
				
				WebElement addVehcilebtn = driver.findElement(By.name("btnAddVehicle"));
				addVehcilebtn.click();
				
				
			}
			
			
			catch(Exception allexception)
			{
				status =false;
				System.out.println("Error occured in adding vehicle "+allexception.getMessage());
							
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "adding vehiclen Page Passed", false);
				}
				
				else			
					r.addstep(report, logger, "Fail", testcasenumber,"adding vehicle Page Failed" , true);
				
				return status;
			}		
		
	}
	
	
	@SuppressWarnings("finally")
	public boolean modifyVechile(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		
		 boolean status =true;
			try{	
				
				String pnumber = g.ExcelRead(detail, Sheetpath, Rownum, 40);				
				
						
				
				WebElement editlink = driver.findElement(By.xpath("//tr[td[text()='"+pnumber+"']]//a[contains(text(),'Edit')]"));
				editlink.click();
				
				Select makedd = new Select(driver.findElement(By.name("vehicleMake")));
				makedd.selectByIndex(Integer.parseInt("4"));
				
				Thread.sleep(1000);				
				
				
				WebElement saveVehcilebtn = driver.findElement(By.name("btnSave"));
				saveVehcilebtn.click();
				
				
			}
			
			
			catch(Exception allexception)
			{
				status =false;
				System.out.println("Error occured in modifying vehicle"+allexception.getMessage());
							
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "modifying vehicle Page Passed", true);
				}
				
				else			
					r.addstep(report, logger, "Fail", testcasenumber,"modifying vehicle Page Failed" , true);
				
				return status;
			}		
		
	}
	
	
	@SuppressWarnings("finally")
	public boolean deleteVehicle(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		
		 boolean status =true;
			try{	
				
				String pnumber = g.ExcelRead(detail, Sheetpath, Rownum, 40);				
				
						
				
				WebElement deleteLink = driver.findElement(By.xpath("//tr[td[text()='"+pnumber+"']]//a[contains(text(),'Delete')]"));
				deleteLink.click();
				
				Thread.sleep(1000);
				
							
				WebElement deleteVehcilebtn = driver.findElement(By.name("btnDelete"));
				deleteVehcilebtn.click();
				
				
			}
			
			
			catch(Exception allexception)
			{
				status =false;
				System.out.println("Error occured in deleting vehicle"+allexception.getMessage());
							
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "Deleting vehicle Page Passed", true);
				}
				
				else			
					r.addstep(report, logger, "Fail", testcasenumber,"Deleting vehicle Page Failed" , true);
				
				return status;
			}		
		
	}
	
	
	
	
	
	@SuppressWarnings("finally")
	public boolean messageCheckVehicle(ExtentReports report,ExtentTest logger,int Rownum,String result) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		
		 boolean status =true;
			try{
				String pnumber = g.ExcelRead(detail, Sheetpath, Rownum, 40);
				String state = g.ExcelRead(detail, Sheetpath, Rownum, 8);
				if(result.equals("Valid"))
				{
				Genric.alertMessageCheck("S","Vehicle "+pnumber+" was added.");
				}
				else if(result.equals("Changes"))
				{
				Genric.alertMessageCheck("S","Vehicle changes were saved.");	
				}
				else if(result.equals("Deleted"))
				{
					Genric.alertMessageCheck("S","Vehicle "+pnumber+" was deleted.");	
				}
				else
				{
				Genric.alertMessageCheck("D","A vehicle with the Plate Number "+pnumber+" and State "+state+" is already registered. Please contact the E-ZPass Customer Service Center by calling 1-888-288-6865 for assistance.");
				}
			}
			catch(Exception allexception)
			{
				status =false;
				System.out.println("Error occured in message "+allexception.getMessage());
							
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "Message validation Passed", true);
				}
				
				else			
					r.addstep(report, logger, "Fail", testcasenumber,"Message validation Failed" , true);
				
				return status;
			}		
		
	}
	
	
	
	@SuppressWarnings("finally")
	public boolean requestTag(ExtentReports report,ExtentTest logger) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		
		boolean status =true;
			try{	
				
			WebElement orderTagbtn = driver.findElement(By.name("btnOrderTollTag"))	;
			orderTagbtn.click();	    
		     System.out.println("Requesting tag");  	
			}
			catch(Exception allexception)
			{
				status =false;
				System.out.println("Error occured in Tag request Navigation"+allexception.getMessage());
							
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "Tag request Navigation Page Passed", false);
				}
				
				else			
					r.addstep(report, logger, "Fail", testcasenumber,"Tag request Navigation Page Failed" , true);
				
				return status;
			}		
		
	}
	
	
	@SuppressWarnings("finally")
	public boolean fillRequestTag(ExtentReports report,ExtentTest logger,int Rownum,int Quantity,String plan) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		String mountType = g.ExcelRead(detail, Sheetpath, Rownum, 30);	
		if(mountType.equals("1"))
		mountType= "INTERIOR";
		else
	    mountType= "EXTERIOR";	
	      
	    String vehicleType = g.ExcelRead(detail, Sheetpath, Rownum, 20);	 
		
		
		 boolean status =true;
			try{	
			
			Select mntdd = new Select(driver.findElement(By.name("transponderType")));
			mntdd.selectByValue(mountType);	
			
			Select vehtypedd = new Select(driver.findElement(By.name("iagCode")));
			vehtypedd.selectByIndex(Integer.parseInt(vehicleType));	
			
			Select quantitydd = new Select(driver.findElement(By.name("quantity")));
			quantitydd.selectByIndex(Quantity-1);	
			
			if(plan.equals("N"))
			{
				
			}
			else
			{	
			Select plandd = new Select(driver.findElement(By.name("planType")));
			plandd.selectByValue(plan);	
			}
			Thread.sleep(2000);
			
			WebElement continuebtn = driver.findElement(By.name("btnContinue"))	;
			continuebtn.click();
				
			    
		     
			}
			catch(Exception allexception)
			{
				status =false;
				System.out.println("Error occured in filling Tag request Navigation"+allexception.getMessage());
							
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "filling Tag request Passed", false);
				}
				
				else			
					r.addstep(report, logger, "Fail", testcasenumber,"filling Tag request Failed" , true);
				
				return status;
			}		
		
	}
	
	
	@SuppressWarnings("finally")
	public boolean submitTagRequest(ExtentReports report,ExtentTest logger,int Rownum,String Quantity,String plan) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		String mountType = g.ExcelRead(detail, Sheetpath, Rownum, 30);	
		if(mountType.equals("1"))
		mountType= "INTERIOR";
		else
	    mountType= "EXTERIOR";	
	      
	   
		
		
		 boolean status =true;
		 String notmatching = "";
			try{	
				
			WebElement verification = driver.findElement(By.xpath("//h1"));
			String pagename = verification.getText().trim();
			
			if(!pagename.equals("Tag Request Verification"))
			status = false;	
			
			
			WebElement type = driver.findElement(By.xpath("//div[label[text()='Type:']]//p"));
			String selectedType = type.getText().trim();
			
			if(!selectedType.equals(mountType))
			notmatching	= "Mount Type";
			
			WebElement quantity = driver.findElement(By.xpath("//div[label[text()='Quantity:']]//p"));
			String selectedquantity = quantity.getText().trim();
			
			if(!selectedquantity.equals(Quantity))
			notmatching	= notmatching + " Quantity";
			
			WebElement Plans = driver.findElement(By.xpath("//div[label[text()='Plan Name:']]//p"));
			String selectedPlan = Plans.getText().trim();
			if(plan.equals("N"))
			{
				
			}
			else
			{	
				if(!selectedPlan.equals(plan))
			    notmatching	= notmatching + " Plan";
			}		
			
			WebElement deposit = driver.findElement(By.xpath("//div[label[text()='Deposit Amount:']]//p"));
			String amount = deposit.getText().trim();
			
			float depositamount = Float.parseFloat(amount.substring(1));
			
			System.out.println("Deposit amount is "+depositamount);
			
			if(depositamount==0)
			{
				System.out.println("No Payment required");
			}
			else
			{
				System.out.println("Payment required");
				
			}		
			
			
			
			if(notmatching.equals(""))
			{
				r.addstep(report,logger, "Pass", testcasenumber, "Fields Verified", false);
				WebElement submitRequestbtn = driver.findElement(By.name("btnSubmitRequest"));
				Thread.sleep(2000);
				submitRequestbtn.click();   
				Thread.sleep(2000);
				WebElement verification2 = driver.findElement(By.xpath("//h1"));
				String pagename2 = verification2.getText().trim();
				
				if(!pagename2.equals("Tag Request Confirmation"))
				status = false;	
			}
			else
			{
				r.addstep(report, logger, "Fail", testcasenumber, notmatching+" is not matching" , true);
				
			}
				
			
		     
			}
			catch(Exception allexception)
			{
				status =false;
				System.out.println("Error occured in Tag Request Verification "+allexception.getMessage());
							
			}
			finally
			{
				if(status)
				{	
				r.addstep(report,logger, "Pass", testcasenumber, "Tag Request Confirmed", true);
				}
				
				else			
					r.addstep(report, logger, "Fail", testcasenumber,"Tag Request Verification Failed" , true);
				
				return status;
			}		
		
	}
	
	
	
	@SuppressWarnings("finally")
	public boolean reportStolen(ExtentReports report,ExtentTest logger) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
	
		boolean status =true;
		
		
		
		
		try {  
			
			WebElement reportLostlnk =driver.findElement(By.xpath("//a[contains(text(),'Report Stolen')][1]"));
			reportLostlnk.click();
			
			Thread.sleep(2000);
		    WebElement pageverify = driver.findElement(By.xpath("//h1[1]"));
			
			String pageloaded = pageverify.getText().trim();
			
			System.out.println(pageloaded);
			Thread.sleep(2000);
			if(pageloaded.equals("Reporting Stolen Tag"))
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
			status=false;
			System.out.println("Error occured in navigating to Reporting Stolen Tag page"+allexception.getMessage());
			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Reporting Stolen Tag Page navigation Passed", true);
			}
			
			else		
			
			r.addstep(report, logger, "Fail", testcasenumber,"Reporting Stolen Tag Page navigation Failed", true);
			return status;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean reportLost(ExtentReports report,ExtentTest logger) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
	
		boolean status =true;
		
		
		
		
		try {  
			
			WebElement reportLostlnk =driver.findElement(By.xpath("//a[contains(text(),'Report Lost')][1]"));
			reportLostlnk.click();
			
			Thread.sleep(2000);
		    WebElement pageverify = driver.findElement(By.xpath("//h1[1]"));
			
			String pageloaded = pageverify.getText().trim();
			
			System.out.println(pageloaded);
			Thread.sleep(2000);
			if(pageloaded.equals("Reporting Lost Tag"))
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
			status=false;
			System.out.println("Error occured in navigating to Reporting Lost Tag page"+allexception.getMessage());
			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Reporting Lost Tag Page navigation Passed", true);
			}
			
			else		
			
			r.addstep(report, logger, "Fail", testcasenumber,"Reporting Lost Tag Page navigation Failed", true);
			return status;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean changeStatus(ExtentReports report,ExtentTest logger,String devicestatus ,String replacement) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
	
		boolean status =true;
		
		
		
		
		try {  
			
			Select dropdown = new Select(driver.findElement(By.name("newStatus")));
			WebElement selectedValue = dropdown.getFirstSelectedOption();
			String value = selectedValue.getText().trim();
			
			
			if(value.equals(devicestatus))
			{
				status=true;
			}
			else
			{
				status=false;
				
			}
			
			if(replacement.equals("Y"))
			{
				WebElement repcheckbox = driver.findElement(By.xpath("//ins[@class='iCheck-helper']"));
				repcheckbox.click();
			}
			
			WebElement continuebtn = driver.findElement(By.name("btnContinue"));
			//continuebtn.click();
			
			
		}
		
		catch(Exception allexception)
		{
			status=false;
			System.out.println("Error occured in Reporting Status to "+devicestatus+": "+allexception.getMessage());
			
		}
		finally
		{
			if(status)
			{	
			r.addstep(report,logger, "Pass", testcasenumber, "Reporting"+devicestatus+" Passed", true);
			}
			
			else		
			
			r.addstep(report, logger, "Fail", testcasenumber,"Reporting"+devicestatus+" Failed", true);
			return status;
		}
	}
	
	
	
}
