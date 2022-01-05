package Safeform;
import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class safeFormCreation {
	
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
	   // select.deselectAll();
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
	
	public static void createForm(WebDriver driver) 
	{

	WebElement cnf = driver.findElement(By.xpath("//a[text()='New Form Request']"));
	cnf.click();
	WebElement platform = driver.findElement(By.id("ASK_Platform"));
//	dropdown(driver,platform,"TCLG Vector");
	
	Actions a =new Actions(driver);
	a.sendKeys(Keys.TAB).sendKeys("T").perform();
	a.sendKeys(Keys.TAB).perform();

	
		
	//((ChromeDriver) driver).executeScript("document.getElementsByTagName('select')[0].dispatchEvent(new Event('change'))");
	
		
	}
	
	
	public static void  main(String args[]) throws InterruptedException, AWTException, EncryptedDocumentException, InvalidFormatException, IOException, SQLException
	{
		
		
      System.setProperty("webdriver.chrome.driver", "E:/Selenium/chromedriver.exe");
		
	    WebDriver driver = new ChromeDriver();
	    final long startTime = System.currentTimeMillis();
	   // String Rootpath = "E://TSG//Workspace//Account creation//";
	    String Sheetpath =  "E://TSG//Workspace//File creation//FileCreation//src//Safeform//safeformList.xlsx";
	    int i=1;
	  
	   
	    String Dataused  =ExcelRead("SAFE", Sheetpath, i, 0);
	    while(!Dataused.equals("END"))
	    {	    	
	    	
	    	Dataused = ExcelRead("SAFE", Sheetpath, i, 0); 	    
	             
	   
	    
	    
		String URL = "https://safe.acs-inc.com/";
		System.out.println(URL+i+Dataused);
	    driver.get(URL);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    
	    WebElement username = driver.findElement(By.id("LOGIN"));
	    WebElement password = driver.findElement(By.id("PWD"));
	    WebElement loginbtn = driver.findElement(By.xpath("//img[@alt='btnlogin']"));
	    
	    username.sendKeys("50004561");
	    password.sendKeys("Suraj.12");
	    loginbtn.click();
	    
	    Thread.sleep(2000);

	    
	    WebElement submitbtn = driver.findElement(By.xpath("//img[@alt='btnsubmit']"));
	    submitbtn.click();
	    Thread.sleep(2000);
	    createForm(driver);
	    
	    i++;	
	    Dataused  =ExcelRead("SAFE", Sheetpath, i, 0);
	    }
//	    if(Dataused.equals("END"))
//	    {
//	    	Thread.sleep(5000);
//	    	driver.close();
//	    	final long endTime = System.currentTimeMillis();		   
//		    JOptionPane.showMessageDialog(null,(i-1)+"SAFE Forms created" + "Time taken to create form " +(endTime-startTime)/1000 + " Seconds");
//	    }
	  
	 }
	

	
	
	
}
