package accountCreation;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;





public class AccountCreation {
	
	
	static void alwayswait(int timeinseconds) throws InterruptedException
	{
		Thread.sleep(timeinseconds * 1000);
		System.out.println(timeinseconds+" seconds Wait is over");
		
	}

	static void controlplus(WebDriver driver,String character)
	{
		Actions actionObj = new Actions(driver);
	    actionObj.keyDown(Keys.CONTROL)
	             .sendKeys(Keys.chord(character))
	             .keyUp(Keys.CONTROL)
	             .perform();
	}
	
	static void dropdown(WebDriver driver, WebElement element, String value)
	{
		Select select = new Select(element);
	    select.deselectAll();
	    select.selectByVisibleText(value);
	}
	
	static void generaldetails(WebDriver driver, String pinnumber,int accounttype, String companyname ,String sheetpath, int Rownum) throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		WebElement addbtn = null;
		WebElement pin=null;
		WebElement challengeQues=null;
	    WebElement challengeAnswer=null;
	    
	    WebElement accntType=null; 
	    WebElement accntName=null;
	    WebElement dbaName=null;
	    WebElement commerical = driver.findElement(By.xpath("//li[a[contains(text(),'Commercial')]]"));
	    WebElement org=null;
	    WebElement statementFreq =null;
	    WebElement ok = null;
	    String orgname;
	    String freq;
	    if(accounttype==1)
	    {
	    	orgname = ExcelRead("Private", sheetpath, Rownum, 14);
		    freq = ExcelRead("Private", sheetpath, Rownum, 15);
	    }
	    else if (accounttype==2)
	    {
	    	
	    	orgname = ExcelRead("Business", sheetpath, Rownum, 14);
		    freq = ExcelRead("Business", sheetpath, Rownum, 15);
	    }
	    else
	    {
	    	orgname = ExcelRead("Commercial", sheetpath, Rownum, 14);
		    freq = ExcelRead("Commercial", sheetpath, Rownum, 15);
	    }
	    
	    
	    
	    
		switch(accounttype)
		{
		  
		case 1 :  addbtn = driver.findElement(By.xpath("//div[div[contains(text(),'Private')]]//span[text()='New']")); 
		          addbtn.click();
		          org = driver.findElement(By.xpath("//input[@aria-label='Agency']"));
		          alwayswait(2);
		          org.clear();
		          org.sendKeys(orgname);
		          challengeQues = driver.findElement(By.xpath("//input[contains(@aria-label,'Question')]"));
		          challengeQues.sendKeys("Name of your pet?");
		          pin = driver.findElement(By.xpath("//input[contains(@aria-label,'PIN')]"));
		          challengeAnswer = driver.findElement(By.xpath("//input[contains(@aria-label,'Answer')]"));
		          accntType = driver.findElement(By.xpath("//input[@aria-label='Account Type']"));
		          statementFreq = driver.findElement(By.xpath("//input[contains(@aria-label,'Frequency')]"));
		          statementFreq.clear();
		          statementFreq.sendKeys(freq);
		          pin.click();
		          ok = driver.findElement(By.xpath("//button[@data-display='Ok']"));
		          ok.click();
		          break;
		          
		case 2 :  
	    		  commerical.click();
	    		  addbtn = driver.findElement(By.xpath("//div[div[contains(text(),'Business')]]//span[text()='New']")); 
			      addbtn.click();
			      org = driver.findElement(By.xpath("//input[@aria-label='Agency']"));
		          alwayswait(2);
		          org.clear();
		          org.sendKeys("NEW JERSEY E-ZPASS");
		          challengeQues = driver.findElement(By.xpath("//input[contains(@aria-label,'Question')]"));
		  		  challengeQues.clear();
		          challengeQues.sendKeys("Name of your pet?");
			      pin = driver.findElement(By.xpath("//input[contains(@aria-label,'PIN')]"));
				  challengeAnswer = driver.findElement(By.xpath("//input[contains(@aria-label,'Answer')]"));
				  accntType = driver.findElement(By.xpath("//input[@aria-label='Account Type']"));
				  accntName = driver.findElement(By.xpath("//input[@aria-label='Account Name']"));
				  
				  dbaName = driver.findElement(By.xpath("//input[@aria-label='DBA Name']"));
				  dbaName.sendKeys(companyname);
				  accntName.sendKeys(companyname);
				  statementFreq = driver.findElement(By.xpath("//input[contains(@aria-label,'Frequency')]"));
				  statementFreq.clear();
		          statementFreq.sendKeys("NONE");
		          pin.click();
		          ok = driver.findElement(By.xpath("//button[@data-display='Ok']")); 
		          ok.click();
				  break;
				 
		case 3 :  commerical.click();
		  		  addbtn = driver.findElement(By.xpath("//div[div[contains(text(),'Business')]]//span[text()='New']")); 
		  		  addbtn.click();
		  		  org = driver.findElement(By.xpath("//input[@aria-label='Agency']"));
		  		  alwayswait(2);
		  		  org.clear();
		  		  org.sendKeys("NEW JERSEY E-ZPASS");
		  		  challengeQues = driver.findElement(By.xpath("//input[contains(@aria-label,'Question')]"));
		  		  challengeQues.clear();
		          challengeQues.sendKeys("Name of your pet?");
		  		  accntType = driver.findElement(By.xpath("//input[@aria-label='Account Type']"));
		          accntType.clear();
		          accntType.sendKeys("COMMERCIAL");
		  		  pin = driver.findElement(By.xpath("//input[contains(@aria-label,'PIN')]"));
		  		  challengeAnswer = driver.findElement(By.xpath("//input[contains(@aria-label,'Answer')]"));
		  		  
		  		  accntName = driver.findElement(By.xpath("//input[@aria-label='Account Name']"));
		  
		  		  dbaName = driver.findElement(By.xpath("//input[@aria-label='DBA Name']"));
		  		  dbaName.sendKeys(companyname);
		  		  accntName.sendKeys(companyname);
		  		  statementFreq = driver.findElement(By.xpath("//input[contains(@aria-label,'Frequency')]"));
		  		  statementFreq.clear();
		  		  statementFreq.sendKeys("NONE");
		  		  pin.click();
		  		  ok = driver.findElement(By.xpath("//button[@data-display='Ok']")); 
		  		  ok.click();
		  		  break;
				  
       default :  break;			  
				  
		
		}
		
		alwayswait(3);
        pin.sendKeys(pinnumber);   
	    challengeAnswer.sendKeys(pinnumber);
	    
	    
		
	}
	
	
	public static String ExcelRead(String Sheetname,String Path,int row1, int col1) throws EncryptedDocumentException, InvalidFormatException, IOException{

			
		FileInputStream fis=new FileInputStream(Path);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(Sheetname);
		Row row=sh.getRow(row1);
		Cell cell=row.getCell(col1);
		String cellvalue=cell.getStringCellValue();	
		return cellvalue;

	}



	public static void ExcelWrite(String Sheetname,String Path,int row1, int col1, String value) throws EncryptedDocumentException, InvalidFormatException, IOException{

		
		FileInputStream fis=new FileInputStream(Path);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(Sheetname);
		Row row=sh.getRow(row1);
		Cell cell=row.createCell(col1);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue(value);
		FileOutputStream fos=new FileOutputStream(Path);
		wb.write(fos);
		fos.close();
		

	 }
	
	
	

	public static void contactdetailsPrivate(WebDriver driver,String Lastname,String Firstname,String phnumber) throws InterruptedException 
	{
		WebElement addbtn = driver.findElement(By.xpath("//div[div[contains(text(),'Contacts')]]//span[text()='New']")); 
        addbtn.click();
        
        alwayswait(2);
               
        WebElement lastnamebox = driver.findElement(By.xpath("//td[contains(@id,'Last_Name')]"));
        lastnamebox.click();
        WebElement lastname = driver.findElement(By.xpath("//input[@id='1_Last_Name']"));
      
        lastname.sendKeys(Lastname);
        
        WebElement firstnamebox = driver.findElement(By.xpath("//td[contains(@id,'First_Name')]"));
        firstnamebox.click();
        WebElement firstname = driver.findElement(By.xpath("//input[@id='1_First_Name']"));
        firstname.sendKeys(Firstname);
        
        WebElement daynobox = driver.findElement(By.xpath("//td[contains(@id,'Contact_Phone_Number')]"));
        daynobox.click();
        WebElement dayno = driver.findElement(By.xpath("//input[@id='1_Contact_Phone_Number']"));
        dayno.sendKeys(phnumber);  
        
        
        		
	}
	
	
	public static void contactdetailsBusiness(WebDriver driver,String Lastname,String Firstname,String phnumber) throws InterruptedException 
	{
		WebElement addbtn = driver.findElement(By.xpath("//div[div[contains(text(),'Contacts')]]//span[text()='New']")); 
        addbtn.click();
        
        alwayswait(2);
               
        WebElement lastnamebox = driver.findElement(By.xpath("//td[contains(@id,'Last_Name')]"));
        lastnamebox.click();
        WebElement lastname = driver.findElement(By.xpath("//input[@id='1_Last_Name']"));
      
        lastname.sendKeys(Lastname);
        
        WebElement firstnamebox = driver.findElement(By.xpath("//td[contains(@id,'First_Name')]"));
        firstnamebox.click();
        WebElement firstname = driver.findElement(By.xpath("//input[@id='1_First_Name']"));
        firstname.sendKeys(Firstname);
        
        WebElement daynobox = driver.findElement(By.xpath("//td[contains(@id,'Contact_Phone_Number')]"));
        daynobox.click();
        WebElement dayno = driver.findElement(By.xpath("//input[@id='1_Contact_Phone_Number']"));
        dayno.sendKeys(phnumber);  
        
        
        		
	}
	
	public static void addressDetailsPrivate(WebDriver driver,String add1,String add2,String zip) 
	{
		WebElement addbtn = driver.findElement(By.xpath("//div[div[contains(text(),'Addresses')]]//span[text()='New']"));  
        addbtn.click();
        WebElement address1box = driver.findElement(By.xpath("//td[contains(@id,'Address')][2]")); 
        address1box.click();
        WebElement address1 = driver.findElement(By.id("1_Street_Address"));        
        address1.sendKeys(add1);
        
        WebElement address2box = driver.findElement(By.xpath("//td[contains(@id,'Address')][3]"));  
        address2box.click();
        WebElement address2 = driver.findElement(By.id("1_Street_Address_2"));
        address2.sendKeys(add2);
        
        WebElement zipcodetxt = driver.findElement(By.xpath("//td[contains(@id,'Postal_Code')]"));
        zipcodetxt.click();
        
        WebElement zipcodetext = driver.findElement(By.xpath("//input[@id='1_Postal_Code']"));
        zipcodetext.sendKeys(zip);   
        
        WebElement zipcodeplustxt = driver.findElement(By.xpath("//td[@id='1_s_3_l_Zip_Plus']"));
        zipcodeplustxt.click(); 
        
        
	}
	
	public static void addressDetailsBusiness(WebDriver driver,String add1,String add2,String zip) 
	{
		WebElement addbtn = driver.findElement(By.xpath("//div[div[contains(text(),'Addresses')]]//span[text()='New']"));  
        addbtn.click();
        WebElement address1box = driver.findElement(By.xpath("//td[contains(@id,'Address')][2]")); 
        address1box.click();
        WebElement address1 = driver.findElement(By.id("1_Street_Address"));        
        address1.sendKeys(add1);
        
        WebElement address2box = driver.findElement(By.xpath("//td[contains(@id,'Address')][3]")); 
        address2box.click();
        WebElement address2 = driver.findElement(By.id("1_Street_Address_2"));
        address2.sendKeys(add2);
        
        WebElement zipcodetxt = driver.findElement(By.xpath("//td[contains(@id,'Postal_Code')]"));
        zipcodetxt.click();
        
        WebElement zipcodetext = driver.findElement(By.xpath("//input[@id='1_Postal_Code']"));
        zipcodetext.sendKeys(zip);   
        
        WebElement zipcodeplustxt = driver.findElement(By.xpath("//td[contains(@id,'Zip_Plus')]"));
        zipcodeplustxt.click(); 
        
        
	}
	
	
	public static void replenishment(WebDriver driver,String accountype,String sheetpath, int Rownum) throws AWTException, EncryptedDocumentException, InvalidFormatException, IOException 
	{
		   WebElement addReplbtn = null;
		    WebElement rebilltypefield = null;
		    WebElement rebilltype = null;
		    WebElement ccnumfield = null;
		    WebElement ccnum = null;
		    WebElement ccmonthfield = null;
		    WebElement ccmonth = null;
		    WebElement ccyearfield = null;
		    WebElement ccyear = null;
		    
		    String Rpaytype = ExcelRead(accountype, sheetpath, Rownum, 16);
		    String cnum = ExcelRead(accountype, sheetpath, Rownum, 17);
		    String cmonth = ExcelRead(accountype, sheetpath, Rownum, 18);
		    String cyear = ExcelRead(accountype, sheetpath, Rownum, 19);
		    
		    
		    	
		    
		    if(accountype.equals("Private"))
		    {   
		    	addReplbtn= driver.findElement(By.xpath("//div[div[contains(text(),'Replenishment')]]//span[text()='New']"));
		    	addReplbtn.click();
		    	rebilltypefield = driver.findElement(By.xpath("//td[contains(@id,'Pay_Type')]"));
		        rebilltypefield.click();
		        rebilltype = driver.findElement(By.id("1_Rebill_Pay_Type"));
		        rebilltype.sendKeys(Rpaytype);
		    	if(Rpaytype.equals("CHECK"))
		    	{
		    		
		    	}
		    	else if(Rpaytype.equals("CASH"))
		    	{
		    		
		    	}
		    		
		    	else
		    	{	
		    	  
		        
		        ccnumfield = driver.findElement(By.xpath("//td[contains(@id,'Card_Number')]"));
		        ccnumfield.click();	  
		        ccnum = driver.findElement(By.id("1_Card_Number"));
		        ccnum.sendKeys(cnum);
		        ccmonthfield = driver.findElement(By.xpath("//td[contains(@id,'Month')]"));
		        ccmonthfield.click();	
		        ccmonth = driver.findElement(By.id("1_Expiration_Month"));
		        ccmonth.sendKeys(cmonth);
		        ccyearfield = driver.findElement(By.xpath("//td[contains(@id,'Year')]"));
		        ccyearfield.click();
		        ccyear = driver.findElement(By.id("1_Expiration_Year"));
		        ccyear.sendKeys(cyear);
		    	}
		    }   	    
		    else
		    {
		    	addReplbtn= driver.findElement(By.xpath("//div[div[contains(text(),'Replenishment')]]//span[text()='New']"));
		    	addReplbtn.click();
		    	rebilltypefield = driver.findElement(By.xpath("//td[contains(@id,'Pay_Type')]"));
		        rebilltypefield.click();
		        rebilltype = driver.findElement(By.id("1_Rebill_Pay_Type"));
		        rebilltype.sendKeys(Rpaytype);
		        
		        if(Rpaytype.equals("CHECK"))
		    	{
		    		
		    	}
		    	else
		    	{	
		    	ccnumfield = driver.findElement(By.xpath("//td[contains(@id,'Card_Number')]"));
		        ccnumfield.click();	  
		        ccnum = driver.findElement(By.id("1_Card_Number"));
		        ccnum.sendKeys(cnum);
		        ccmonthfield = driver.findElement(By.xpath("//td[contains(@id,'Month')]"));
		        ccmonthfield.click();	
		        ccmonth = driver.findElement(By.id("1_Expiration_Month"));
		        ccmonth.sendKeys(cmonth);
		        ccyearfield = driver.findElement(By.xpath("//td[contains(@id,'Year')]"));
		        ccyearfield.click();
		        ccyear = driver.findElement(By.id("1_Expiration_Year"));
		        ccyear.sendKeys(cyear);
		    	}
		    }
		    
		   savefunc();
		
	}
	
	
	public static void vehciles(WebDriver driver,String accountype,String sheetpath, int Rownum) throws AWTException, InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	{
		WebElement addvehicle = null;
	    WebElement platenumberfield = null;
	    WebElement platenumber = null;
	    WebElement plateyearfield = null;
	    WebElement plateyear = null;
	    WebElement platemakefield = null;
	    WebElement platemake = null;
	    WebElement platemodelfield = null;
	    WebElement platemodel = null;
	    
	    alwayswait(2);
	    
	    int RandomNum =  1000 +(int)(Math.random() * 9999); 
	    String pname = randomCharacter()+(RandomNum+"")+randomCharacter();
	    String pyear = ExcelRead(accountype, sheetpath, Rownum, 21);
	    String pmake = ExcelRead(accountype, sheetpath, Rownum, 22);
	    String pmodel = ExcelRead(accountype, sheetpath, Rownum, 23);
	    
	    
	    
	   
	    	int i= 1;
	    	addvehicle= driver.findElement(By.xpath("//div[div[contains(text(),'Vehicles')]]//span[text()='New']"));
	    	addvehicle.click();
	    	platenumberfield = driver.findElement(By.xpath("//td[contains(@id,'Plate_Number')]"));
	    	platenumberfield.click();
	    	platenumber = driver.findElement(By.name("Plate_Number"));    	
			
	    	platenumber.sendKeys(pname);
	    	plateyearfield = driver.findElement(By.xpath("//td[contains(@id,'Year')]"));
	    	plateyearfield.click();
	    	plateyear = driver.findElement(By.id("1_Year_of_Vehicle"));
	    	plateyear.sendKeys(pyear);
	    	platemakefield = driver.findElement(By.xpath("//td[contains(@id,'Make')]"));
	    	platemakefield.click();
	    	platemake = driver.findElement(By.id("1_Vehicle_Make"));
	    	platemake.sendKeys(pmake);
	    	platemodelfield = driver.findElement(By.xpath("//td[contains(@id,'Model')]"));
	    	platemodelfield.click();
	    	platemodel = driver.findElement(By.id("1_Vehicle_Model"));
	    	platemodel.sendKeys(pmodel);
	    
	    
	    
	    	
	    	savefunc();
	    	
	    	alwayswait(3);
	    	int value = driver.findElements(By.id("btn-accept")).size();
	    	System.out.println("Value is " +value);
	    	if(value>0)
	    	{	
	    	while(driver.findElement(By.id("btn-accept")).isDisplayed())
	    	{
	    		i++;
	    		alwayswait(1);
	    		
	    		driver.findElement(By.id("btn-accept")).click();
	    		plateyearfield.click();
	    		platenumberfield.click();
	    		platenumber = driver.findElement(By.name("Plate_Number"));
	    		platenumber.clear();
	    		platenumber.sendKeys(pname+i);    		
	    		savefunc();
	    		
	    		alwayswait(3);
	    	}
	    	}
	    	
	    
		
	}
	
	
	public static void devicerequest(WebDriver driver,String accountype,String sheetname,int Rownum) throws AWTException, EncryptedDocumentException, InvalidFormatException, IOException 
	{
		WebElement adddevicerequest = null;
	    WebElement devicedescfield = null;
	    WebElement devicedesc = null;
	    WebElement modelselector = null;
	    
	    WebElement modelnumber = null;
	    
	    String drequesttype = ExcelRead(accountype, sheetname, Rownum, 24);
	    String drequestvalue = ExcelRead(accountype, sheetname, Rownum, 25);
	    
	    
	    if(accountype.equals("Private"))
	    {
	    	adddevicerequest= driver.findElement(By.xpath("//div[div[contains(text(),'Requests')]]//span[text()='New']"));
	    	adddevicerequest.click();
	    	devicedescfield = driver.findElement(By.xpath("//td[contains(@id,'Smart_Issue')]"));
	    	devicedescfield.click();
	    	devicedesc = driver.findElement(By.xpath("//td[contains(@id,'Smart_Issue')]//span[contains(@id,'icon')]"));
	    	devicedesc.click();
	    	modelselector = driver.findElement(By.xpath("//input[contains(@aria-labelledby,'PopupQueryCombobox_Label')]"));
	    	modelselector.clear();
	    	modelselector.sendKeys(drequesttype);
	    	modelnumber = driver.findElement(By.xpath("//input[contains(@aria-labelledby,'PopupQuerySrchspec_Label')]"));
	    	modelnumber.sendKeys(drequestvalue);
	    	modelnumber.sendKeys(Keys.ENTER);    	
	    	savefunc();
	    }
	    else
	    {
	    	adddevicerequest= driver.findElement(By.xpath("//div[div[contains(text(),'Requests')]]//span[text()='New']"));
	    	adddevicerequest.click();
	    	devicedescfield = driver.findElement(By.xpath("//td[contains(@id,'Smart_Issue')]"));
	    	devicedescfield.click();
	    	devicedesc = driver.findElement(By.xpath("//td[contains(@id,'Smart_Issue')]//span[contains(@id,'icon')]"));
	    	devicedesc.click();
	    	modelselector = driver.findElement(By.xpath("//input[contains(@aria-labelledby,'PopupQueryCombobox_Label')]"));
	    	modelselector.clear();
	    	modelselector.sendKeys(drequesttype);
	    	modelnumber = driver.findElement(By.xpath("//input[contains(@aria-labelledby,'PopupQuerySrchspec_Label')]"));
	    	modelnumber.sendKeys(drequestvalue);
	    	modelnumber.sendKeys(Keys.ENTER);    	
	    	savefunc();
	    }
		
	}
	
	public static void addplan(WebDriver driver,String plantoadd) throws AWTException 
	{
		    WebElement adddPlan = null;
	        WebElement planName = null;  	   
	    	adddPlan= driver.findElement(By.xpath("//div[div[contains(text(),'Options')]]//span[text()='New']"));
	    	adddPlan.click();
	    	planName = driver.findElement(By.id("1_Plan_Name"));
	    	planName.sendKeys(plantoadd); 
	    	if(plantoadd.equals("NJTBD"))
	    	{
	    		WebElement devicerequest = driver.findElement(By.xpath("//td[contains(@id,'SR_Number')]"));
	    		devicerequest.click();
	    		WebElement icon = driver.findElement(By.xpath("//td[contains(@id,'Plan')]"));
	    		icon.click();
	    		WebElement Ok = driver.findElement(By.xpath("//button/span[text()='Ok']"));
	        	Ok.click();
	    	}
	    
	    
		
	}
	
	
	public static void addPaymentList(WebDriver driver,String accountype,String sheetpath,int Rownum) throws AWTException, InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	{
		WebElement addPayment = null;
		WebElement processPay = null;
	       
	    
	    	alwayswait(10);
	    	addPayment= driver.findElement(By.xpath("//div[div[contains(text(),'Payments List')]]//span[text()='New']"));
	    	//addPayment= driver.findElement(By.id("s_3_1_13_0_Ctrl"));
	    	while(!addPayment.isEnabled())
	    	{
	    		addPayment= driver.findElement(By.xpath("//div[div[contains(text(),'Payments List')]]//span[text()='New']"));
	    	}
	    	addPayment.click();	
	    	String Rpaytype = ExcelRead(accountype, sheetpath, Rownum, 16);
	    	String checkno = ExcelRead(accountype, sheetpath, Rownum, 17);
	    	if(Rpaytype.equals("CHECK"))
	    	{
	    	WebElement checknumberbox = driver.findElement(By.xpath("//td[contains(@id,'Check_Number')]"));
	    	checknumberbox.click();
	    	WebElement checknum = driver.findElement(By.id("1_Check_Number"));
	    	checknum.sendKeys(checkno);;
	    	
	    	}
	    	else if(Rpaytype.equals("CASH"))
	    	{
	    		
	    	}
	    		
	    	alwayswait(2);
	    	savefunc();
	    	alwayswait(5);
	    	processPay= driver.findElement(By.xpath("//span[text()='Process Pay']"));
	    	processPay.click();
	    	alwayswait(10);
	    	enterPress();
	       
	    	
	    
		
	}
	
	
	
	
	public static void savefunc() throws AWTException
	{
		    Robot r  = new Robot();
		    r.keyPress(KeyEvent.VK_CONTROL);
		    r.keyPress(KeyEvent.VK_S);
		    r.keyRelease(KeyEvent.VK_CONTROL);
		    r.keyRelease(KeyEvent.VK_S);
		    
	}
	
	public static void enterPress() throws AWTException
	{
		    Robot r  = new Robot();
		    r.keyPress(KeyEvent.VK_ENTER);
		    r.keyRelease(KeyEvent.VK_ENTER);
		    
	}
	
	public static void deviceAssignment(WebDriver driver) throws InterruptedException, AWTException
	{
		WebElement Devicestab = driver.findElement(By.xpath("//a[text()='Devices']"));
	    Devicestab.click();
	    WebElement DeviceAssignmenttab = driver.findElement(By.xpath("//a[text()='Device Assignment']"));
	    DeviceAssignmenttab.click();
	    WebElement assignnew = driver.findElement(By.xpath("//button[@title='Device Assign:New']"));
	   	assignnew.click();
	  	alwayswait(4);
	   	WebElement assignmentaicon = driver.findElement(By.xpath("//span[@id='s_1_2_20_0_icon']"));
    	assignmentaicon.click();
    	WebElement Ok = driver.findElement(By.xpath("//button/span[text()='Ok']"));
    	Ok.click();
    	alwayswait(2);
	   	savefunc();	   
	    System.out.println("Devices assigned");
	}
	public static void alertfunc(WebDriver driver) throws AWTException
	{
		
		    try {
		        WebDriverWait wait = new WebDriverWait(driver, 2);
		       // wait.until(ExpectedConditions.alertIsPresent());
		        Alert alert = driver.switchTo().alert();
		        alert.accept();
		    } catch (Exception e) {
		        //exception handling
		    }
		
	}

	
	public static void backdate(String accountnumber) throws SQLException
	{
		
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		   String dbURL = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.36.20.51)(PORT = 1521))(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
        String strUserID = "pbatch";
        String strPassword = "batchp"; 
        Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
        String Statement1 = "update S_asset set START_DT = TO_DATE('2016/08/01', 'yyyy/mm/dd') where ROW_ID  in (select ROW_ID from s_asset a, v_etc_account etc where a.X_ETC_ACCOUNT_ID = etc.ETC_ACCOUNT_ID and etc.ACCOUNT_NO in ('"+accountnumber+"')and TYPE_CD = 'ACCOUNT PLAN')"; 
        String Statement2 = "update S_asset set START_DT = TO_DATE('2016/08/01', 'yyyy/mm/dd') where ROW_ID  in (select ROW_ID from s_asset a, v_etc_account etc where a.X_ETC_ACCOUNT_ID = etc.ETC_ACCOUNT_ID and etc.ACCOUNT_NO in ('"+accountnumber+"')and TYPE_CD = 'DEVICE')";
        String Statement3 = "update S_asset set INSTALL_DT = TO_DATE('2016/08/01', 'yyyy/mm/dd') where ROW_ID  in (select ROW_ID from s_asset a, v_etc_account etc where a.X_ETC_ACCOUNT_ID = etc.ETC_ACCOUNT_ID and etc.ACCOUNT_NO in ('"+accountnumber+"')and TYPE_CD = 'DEVICE')";
        String Statement4= "Update s_org_ext_x   set attrib_21 = 05 where par_row_id in   (select row_id from s_org_ext crm_account where crm_account.service_emp_cnt in (select ETC_ACCOUNT_ID from v_etc_account where ACCOUNT_NO in ('"+accountnumber+"')))";
        String Statement5= "Update s_org_ext set last_mgr_revw_dt = '01-AUG-2016' where SERVICE_EMP_CNT in (select ETC_ACCOUNT_ID from v_etc_account where ACCOUNT_NO in ('"+accountnumber+"'))";
        c.createStatement().executeQuery(Statement1);
        c.createStatement().executeQuery("Commit");
        c.createStatement().executeQuery(Statement2);
        c.createStatement().executeQuery("Commit");
        c.createStatement().executeQuery(Statement3);
        c.createStatement().executeQuery("Commit");
        c.createStatement().executeQuery(Statement4);
        c.createStatement().executeQuery("Commit");
        c.createStatement().executeQuery(Statement5);
        c.createStatement().executeQuery("Commit");
   	   c.close();
	}
	
	
	public static void  main(String args[]) throws InterruptedException, AWTException, EncryptedDocumentException, InvalidFormatException, IOException, SQLException
	{
		
		String[] choices = { "Private", "Business", "Commercial" };
	    String accountype = (String) JOptionPane.showInputDialog(null, "Choose now...",
	    "Please select account type", JOptionPane.QUESTION_MESSAGE, null,choices,choices[0]);	   
		System.setProperty("webdriver.chrome.driver", "E:/Selenium/chromedriver.exe");
		
	    WebDriver driver = new ChromeDriver();
	    final long startTime = System.currentTimeMillis();
	   // String Rootpath = "E://TSG//Workspace//Account creation//";
	    String Sheetpath =  "E://TSG//Workspace//Account creation//Datasheet.xlsm";
	    int i=1;
	    int Rownum=1;
	   
	    String Dataused  =ExcelRead(accountype, Sheetpath, i, 0);
	    while(Dataused.equals("Y"))
	    {	    	
	    	i++;	
	    	Dataused = ExcelRead(accountype, Sheetpath, i, 0); 	    
	        Rownum = i;	    
	   
	    }	
	    
	   
	    
	    
		String URL = ExcelRead(accountype, Sheetpath, Rownum, 1);
		System.out.println(URL);
	    driver.get(URL);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    WebElement username = driver.findElement(By.id("s_swepi_1"));
	    WebElement password = driver.findElement(By.id("s_swepi_2"));
	    WebElement Loginbtn = driver.findElement(By.xpath("//input[@value='Login']"));
	    String uname = ExcelRead(accountype, Sheetpath, Rownum, 2);
	    String pwd = ExcelRead(accountype, Sheetpath, Rownum, 3);
	    username.sendKeys(uname);
	    password.sendKeys(pwd);
	    Loginbtn.click();
	    alwayswait(60);
	   
	    while(!Dataused.equals("END"))
	    { 
	    	acccre(driver,Sheetpath,Rownum,accountype);
	    	Rownum = Rownum + 1;
	    	System.out.println(Rownum+"***************************************************************************************");
	    	
	    
	    }
	    if(Dataused.equals("END"))
	    {
	    	
	    	driver.close();
	    	final long endTime = System.currentTimeMillis();		   
		    JOptionPane.showMessageDialog(null,(i-1)+"All accounts created" + "Time taken to create File " +(endTime-startTime)/1000 + " Seconds");
	    }
	  
	    }
	
	
	public static char randomCharacter() {
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'a');
		return c;
	}
	
	
	

	
	public static  void  acccre(WebDriver driver,String Sheetpath,int Rownum,String accountype) throws InterruptedException, AWTException, EncryptedDocumentException, InvalidFormatException, IOException, SQLException
	{
		
		
	    
	    WebElement accOpentab =driver.findElement(By.xpath("//a[contains(text(),'Account Opening')]"));
	    accOpentab.click();
	    
	    alwayswait(10);
	   // controlplus(driver, "N");
	    
	    String pin = ExcelRead(accountype,Sheetpath , Rownum, 5);
	    String companyname = ExcelRead(accountype, Sheetpath, Rownum,6);
	    String fname = ExcelRead(accountype, Sheetpath, Rownum, 7);
	    String lname = ExcelRead(accountype, Sheetpath, Rownum, 8);
	    String Phone = ExcelRead(accountype, Sheetpath, Rownum, 9);
	    String Address1 = ExcelRead(accountype, Sheetpath, Rownum, 10);
	    String Address2 = ExcelRead(accountype, Sheetpath, Rownum, 11);
	    String pincode = ExcelRead(accountype, Sheetpath, Rownum, 12);
	    
	    
	    if(accountype.equals("Private"))
	    {	
	    	
	   	    generaldetails(driver, pin,1, companyname,Sheetpath,Rownum);
	   	    contactdetailsPrivate(driver,fname,lname,Phone);
		    addressDetailsPrivate(driver, Address1, Address2, pincode);
		    
	    }
	    else if(accountype.equals("Business"))
	    {
	    	generaldetails(driver, pin,2, companyname,Sheetpath,Rownum);
	    	contactdetailsBusiness(driver,fname,lname,Phone);
		    addressDetailsPrivate(driver, Address1, Address2, pincode);
	    }
	    else 
	    {
	    	generaldetails(driver, pin,3, companyname,Sheetpath,Rownum);
	    	contactdetailsBusiness(driver,fname,lname,Phone);
	    	addressDetailsBusiness(driver, Address1, Address2, pincode);
	    }
	    
	   
	    	
	   	    
	    
	    
	   // WebElement savebtn = driver.findElement(By.xpath("//button[@id='s_3_1_16_0_Ctrl']"));
	   // savebtn.click();
	    
	   savefunc();
	   alwayswait(8);
	   enterPress();
	
	   
	   
	    WebElement Replenishments =driver.findElement(By.xpath("//a[text()='Replenishments']"));
	   
	    Replenishments.click();
	    replenishment(driver,accountype,Sheetpath,Rownum);
	    
	  
	    
	    	WebElement vehicles =driver.findElement(By.xpath("//li[a[text()='Vehicles']]"));
		    alwayswait(2);
		    vehicles.click();
		    vehciles(driver,accountype,Sheetpath,Rownum);
		    WebElement devicerequest =driver.findElement(By.xpath("//li[a[text()='Device Request']]"));
		    alwayswait(2);
		    devicerequest.click();
		    devicerequest(driver,accountype,Sheetpath,Rownum);
		    WebElement plans =driver.findElement(By.xpath("//li[a[text()='Plans']]"));
		    alwayswait(2);
		    plans.click();
		    if(accountype.equals("Commercial"))
		    {
		    String planname = "NJTBDOFFPK";
		    addplan(driver,planname);
		    WebElement business = driver.findElement(By.xpath("//li[text()='"+planname+"']"));
		    business.click();
		    }
		    
		    
		    WebElement paybtn = null;
		    paybtn = driver.findElement(By.xpath("//span[text()='Pay']"));
	    	paybtn.click();
	    	alwayswait(10);
	    	enterPress();
		    addPaymentList(driver, accountype,Sheetpath,Rownum);
		    
		    alwayswait(15);
		    enterPress();
		    System.out.println("account is created.. Popup needs to be clicked");
		    alertfunc(driver);
		    alwayswait(10);	   
		    WebElement Accountinfotab = driver.findElement(By.xpath("//a[text()='Account Info']"));
		    Accountinfotab.click();
		     
		    
		   String accountNumber = driver.findElement(By.xpath("//input[@aria-label ='Account Number']")).getAttribute("value");;
		   System.out.println("accountnumber is "+accountNumber);
		   ExcelWrite(accountype, Sheetpath, Rownum, 0, "Y"); 
		   ExcelWrite(accountype, Sheetpath, Rownum, 4,accountNumber);
		   alwayswait(5	);
	       // deviceAssignment(driver);
		  // backdate(accountNumber);	   
		 //  final long endTime = System.currentTimeMillis();
		  // System.out.println("Time taken to create Account "+(endTime-startTime)/1000 + " Seconds");
		  
		    
	    
	    }
	    
	      		
	}
	



