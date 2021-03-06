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





public class AssignDevice {
	
	
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
	

	
	
	
	public static void  main(String args[]) throws InterruptedException, AWTException, EncryptedDocumentException, InvalidFormatException, IOException, SQLException
	{
		
		   
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    final long startTime = System.currentTimeMillis();
	   // String Rootpath = "E://TSG//Workspace//Account creation//";
	    String Sheetpath =  "E://TSG//Workspace//Account creation//Datasheet.xlsm"
	    		+ "";
	    int i=1;
	    int Rownum=1;
	   String tab = "Device Assignment";
	    String Dataused  =ExcelRead(tab, Sheetpath, i, 0);
	    while(Dataused.equals("Y"))
	    {	    	
	    	i++;	
	    	Dataused = ExcelRead(tab, Sheetpath, i, 0); 	    
	        Rownum = i;	    
	   
	    }	
	    
	   
	    
	    
		String URL = ExcelRead(tab, Sheetpath, Rownum, 1);
		System.out.println(URL);
	    driver.get(URL);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    WebElement username = driver.findElement(By.id("s_swepi_1"));
	    WebElement password = driver.findElement(By.id("s_swepi_2"));
	    WebElement Loginbtn = driver.findElement(By.xpath("//input[@value='Login']"));
	    String uname = ExcelRead(tab, Sheetpath, Rownum, 2);
	    String pwd = ExcelRead(tab, Sheetpath, Rownum, 3);
	    username.sendKeys(uname);
	    password.sendKeys(pwd);
	    Loginbtn.click();
	    
	    alwayswait(20);
	    while(!Dataused.equals("END"))
	    { 
	    	alwayswait(60);
	    	enterPress();
	    	deviceassign(driver,Sheetpath,Rownum,tab);
	    	Rownum = Rownum + 1;
	    	System.out.println(Rownum+"***************************************************************************************");
	    	
	    
	    }
	    if(Dataused.equals("END"))
	    {
	    	
	    	driver.close();
	    	final long endTime = System.currentTimeMillis();		   
		    JOptionPane.showMessageDialog(null,(i-1)+" Device assigned sucessfully \n" + "Time taken to assign device " +(endTime-startTime)/1000 + " Seconds");
	    }
	  
	    }
	
	
	public static char randomCharacter() {
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'a');
		return c;
	}
	
	
	

	
	public static  void  deviceassign(WebDriver driver,String Sheetpath,int Rownum,String tab) throws InterruptedException, AWTException, EncryptedDocumentException, InvalidFormatException, IOException, SQLException
	{
		
		
	    
	   
	    
	    alwayswait(10);
	    WebElement Accountstab = driver.findElement(By.xpath("//a[text()='Accounts']"));
	    Accountstab.click();
	    WebElement accNotxtbox = driver.findElement(By.xpath("//input[@aria-label='Account Number']"));
	    String accountnumber = ExcelRead(tab, Sheetpath, Rownum, 4);
	    accNotxtbox.sendKeys(accountnumber);
	    WebElement gobtn = driver.findElement(By.xpath("//span[text()='Go']"));
	    gobtn.click();	
	    alwayswait(5);
	    WebElement Accountinfotab = driver.findElement(By.xpath("//a[text()='Account Info']"));
	    Accountinfotab.click();
	    WebElement Devicestab = driver.findElement(By.xpath("//a[text()='Devices']"));
	    Devicestab.click();
	    WebElement DeviceAssignmenttab = driver.findElement(By.xpath("//a[text()='Device Assignment']"));
	    DeviceAssignmenttab.click();
	    WebElement assignnew = driver.findElement(By.xpath("//button[@title='Device Assign:New']"));
	    while(assignnew.isEnabled())
	    {
	    	assignnew.click();
	    	alwayswait(4);
	    	WebElement assignmentaicon = driver.findElement(By.xpath("//span[@id='s_1_2_20_0_icon']"));
	    	assignmentaicon.click();
	    	WebElement Ok = driver.findElement(By.xpath("//button/span[text()='Ok']"));
	    	Ok.click();
	    	
	    	
	    
	    	
	    }
	    ExcelWrite(tab, Sheetpath, Rownum, 0, "Y"); 
	    System.out.println("Devices assigned");
	    
	    
	    
	    }
	    
	      		
	}
	



