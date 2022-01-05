package com.vector.func;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class  SuperTestNG {
	
	
	public WebDriver driver;
	public Genric genric;
	long startTime;
	long endTime;
	
	public static int i=1;
    public static int Rownum=1;
    public static String accountype;
    public static String Sheetpath =  "E://TSG//Workspace//Account creation//Datasheet.xlsx";
	@BeforeMethod
	public void precondition() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		Genric genric = new Genric();
		
		
		String[] choices = { "Private", "Business", "Commercial" };
	    accountype = (String) JOptionPane.showInputDialog(null, "Choose now...",
	            "Please select account type", JOptionPane.QUESTION_MESSAGE, null,choices,choices[0]);	
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		driver = new ChromeDriver(options); 
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://10.36.20.41/callcenter_enu/start.swe");
		startTime = System.currentTimeMillis();
	    
	    
	    String Dataused=genric.ExcelRead(accountype, Sheetpath, i, 0);
	    while(Dataused.equals("Y"))
	    {	    	
	    	i++;	
	        Dataused = genric.ExcelRead(accountype, Sheetpath, i, 0); 	    
	        Rownum = i;	    
	   
	    }	
	    
	    System.out.println(Rownum);
	    
	    String URL = genric.ExcelRead(accountype, Sheetpath, Rownum, 1);
		System.out.println(URL);
	    driver.get(URL);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	   
		
		   
		
		
	}
	
	@AfterMethod
	public void postcondition()
	{
		driver.close();
		endTime = System.currentTimeMillis();
		System.out.println("Time taken to create Account "+(endTime-startTime)/1000 + " Seconds");
	}
	
		
	
	
	
	
	
	 

}

     