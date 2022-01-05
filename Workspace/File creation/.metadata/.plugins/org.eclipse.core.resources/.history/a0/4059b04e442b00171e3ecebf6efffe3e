package accountCreation;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DeviceAssignment {

	static void alwayswait(int timeinseconds) throws InterruptedException
	{
		Thread.sleep(timeinseconds * 1000);
		System.out.println(timeinseconds+" seconds Wait is over");
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
	

	public static void  main(String args[]) throws InterruptedException, AWTException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		//String[] choices = { "Private", "Business", "Commercial" };
	 //   String accountype = (String) JOptionPane.showInputDialog(null, "Choose now...",
	//            "Please select account type", JOptionPane.QUESTION_MESSAGE, null,choices,choices[0]);	   
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    final long startTime = System.currentTimeMillis();
	    String Sheetpath =  "E://TSG//Workspace//Account creation//Datasheet.xlsx";
	    int i=1;
	    int Rownum=1;
	    String tab = "Device Assignment";
	    String Dataused=ExcelRead(tab, Sheetpath, i, 0);
	    while(Dataused.equals("Y"))
	    {	    	
	    	i++;	
	        Dataused = ExcelRead(tab, Sheetpath, i, 0); 	    
	        Rownum = i;	    
	   
	    }	
	    
	    System.out.println(Rownum);
	    
	    
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
	    alwayswait(60);
	    WebElement Accountstab = driver.findElement(By.xpath("//a[text()='Accounts']"));
	    Accountstab.click();
	    WebElement accNotxtbox = driver.findElement(By.xpath("//input[@aria-label='Account Number']"));
	    String accountnumber = ExcelRead(tab, Sheetpath, i, 4);;
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
	    System.out.println("All Devices assigned");
	  
	    final long endTime = System.currentTimeMillis();
	    System.out.println("Time taken to create Account "+(endTime-startTime)/1000 + " Seconds");
	    
	    }
	    
	      		
	}
	
