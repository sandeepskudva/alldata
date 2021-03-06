package accountCreation;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class devicePlanScreenshot {

	static void alwayswait(int timeinseconds) throws InterruptedException
	{
		Thread.sleep(timeinseconds * 1000);
		//System.out.println(timeinseconds+" seconds Wait is over");
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
	
	public static void Screenshot(WebDriver driver, String Path) throws IOException
	{	
	
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(scrFile, new File(Path+".jpg"));
	}
	

	public static void  main(String args[]) throws InterruptedException, AWTException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		String[] choices = { "Private", "Business", "Commercial" };
	    String tab = (String) JOptionPane.showInputDialog(null, "Choose now...",
	    "Please select account type", JOptionPane.QUESTION_MESSAGE, null,choices,choices[0]);	   
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    final long startTime = System.currentTimeMillis();
	    String Rootpath = "E://TSG//Workspace//Account creation//";
	    String Sheetpath =  Rootpath+"screenshot.xlsx";
	    int i=1;
	    int Rownum=1;
	   
	    String screenshotcaptured  =ExcelRead(tab, Sheetpath, i, 0);
	    while(screenshotcaptured.equals("Y"))
	    {	    	
	    	i++;	
	    	screenshotcaptured = ExcelRead(tab, Sheetpath, i, 0); 	    
	        Rownum = i;	    
	   
	    }	
	    
	    System.out.println(Rownum);
	    
	    
		String URL = ExcelRead(tab, Sheetpath, Rownum, 1);
		System.out.println(URL);
	    driver.get(URL);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    WebElement username = driver.findElement(By.id("s_swepi_1"));
	    WebElement password = driver.findElement(By.id("s_swepi_2"));
	    WebElement Loginbtn = driver.findElement(By.xpath("//input[@value='Login']"));
	    String uname = ExcelRead(tab, Sheetpath, Rownum, 2);
	    String pwd = ExcelRead(tab, Sheetpath, Rownum, 3);
	    username.sendKeys(uname);
	    password.sendKeys(pwd);
	    Loginbtn.click();
	    alwayswait(50);
	   
	    while(!screenshotcaptured.equals("END"))
	    { 
	    
	    
	    WebElement Accountstab = driver.findElement(By.xpath("//a[text()='Accounts']"));
	    Accountstab.click();
	    alwayswait(4);
	    WebElement accNotxtbox = driver.findElement(By.xpath("//input[@aria-label='Account Number']"));
	    String accountnumber = ExcelRead(tab, Sheetpath, i, 4);
	    accNotxtbox.sendKeys(accountnumber);
	    WebElement gobtn = driver.findElement(By.xpath("//span[text()='Go']"));
	    gobtn.click();	
	    alwayswait(4);
	    Screenshot(driver,Rootpath+accountnumber+"//AccountSearch");
//	    WebElement Accountinfotab = driver.findElement(By.xpath("//a[text()='Account Info']"));
//	    Accountinfotab.click();
//	    alwayswait(4);
//	    Screenshot(driver,Rootpath+accountnumber+"//Details");
	    WebElement Addresstab = driver.findElement(By.xpath("//a[text()='Toll Bill']"));
	    Addresstab.click();
	    alwayswait(4);
	    Screenshot(driver,Rootpath+accountnumber+"//TollBill");
//	    WebElement Vehiclestab = driver.findElement(By.xpath("//a[text()='Rebill Info']"));
//	    Vehiclestab.click();
//	    alwayswait(4);
//	    Screenshot(driver,Rootpath+accountnumber+"//Rebill Info");
//	    WebElement Financials = driver.findElement(By.xpath("//a[text()='Financials']"));
//	    Financials.click();
//	    alwayswait(4);
//	    WebElement Reversals = driver.findElement(By.xpath("//a[text()='Reversals']"));
//	    Reversals.click();
//	    alwayswait(4);
//	    Screenshot(driver,Rootpath+accountnumber+"//Reversals");
	   
	    
	    i++;
	    screenshotcaptured = ExcelRead(tab, Sheetpath, i, 0); 
	    System.out.println(screenshotcaptured);
	    }
	    if(screenshotcaptured.equals("END"))
	    {
	    	
	    	driver.close();
	    	final long endTime = System.currentTimeMillis();		   
		    JOptionPane.showMessageDialog(null,(i-1)+" Account screenshots taken Sucessfully \n" + "Time taken to create File " +(endTime-startTime)/1000 + " Seconds");
	    }
	  
	    }
	    
	      		
	}
	
