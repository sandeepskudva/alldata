package com.vector.pages;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vector.func.Genric;
import com.vector.func.SuperTestNG;

public class accountGeneralDetails extends SuperTestNG {
	
	static Genric  g = new Genric();
	
	@FindBy (xpath ="//a[contains(text(),'Account Opening')]")
	private WebElement accOpentab;
	
	
    
	
	public accountGeneralDetails(WebDriver driver)
	{
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
		
	
	
	
	public void fillDetails()
	{
		
		try {  
			
			accOpentab.click();	
			g.alwayswait(10);
			String pin = g.ExcelRead(accountype,Sheetpath , Rownum, 5);
		    String companyname = g.ExcelRead(accountype, Sheetpath, Rownum,6);
		    String fname = g.ExcelRead(accountype, Sheetpath, Rownum, 7);
		    String lname = g.ExcelRead(accountype, Sheetpath, Rownum, 8);
		    String Phone = g.ExcelRead(accountype, Sheetpath, Rownum, 9);
		    String Address1 = g.ExcelRead(accountype, Sheetpath, Rownum, 10);
		    String Address2 = g.ExcelRead(accountype, Sheetpath, Rownum, 11);
		    String pincode = g.ExcelRead(accountype, Sheetpath, Rownum, 12);
		    
		    if(accountype.equals("Private"))
		    {	
		    	
		   	    fillgeneraldetails(driver, pin,1, companyname,Sheetpath,Rownum);
		   	    contactdetailsPrivate(driver,fname,lname,Phone);
			    addressDetailsPrivate(driver, Address1, Address2, pincode);
			    
		    }
		    else if(accountype.equals("Business"))
		    {
		    	fillgeneraldetails(driver, pin,2, companyname,Sheetpath,Rownum);
		    	contactdetailsBusiness(driver,fname,lname,Phone);
		    	addressDetailsBusiness(driver, Address1, Address2, pincode);
		    }
		    else 
		    {
		    	fillgeneraldetails(driver, pin,3, companyname,Sheetpath,Rownum);
		    	contactdetailsBusiness(driver,fname,lname,Phone);
		    	addressDetailsBusiness(driver, Address1, Address2, pincode);
		    }
		    	
		    
			
		}
		catch(Exception allexception)
		{
			System.out.println("Error occured in filling account details ");
		}
	}
	
	
	
	
	
	static void fillgeneraldetails(WebDriver driver, String pinnumber,int accounttype, String companyname ,String sheetpath, int Rownum) throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
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
	    	orgname = g.ExcelRead("Private", sheetpath, Rownum, 14);
		    freq = g.ExcelRead("Private", sheetpath, Rownum, 15);
	    }
	    else if (accounttype==2)
	    {
	    	
	    	orgname = g.ExcelRead("Business", sheetpath, Rownum, 14);
		    freq = g.ExcelRead("Business", sheetpath, Rownum, 15);
	    }
	    else
	    {
	    	orgname = g.ExcelRead("Commercial", sheetpath, Rownum, 14);
		    freq = g.ExcelRead("Commercial", sheetpath, Rownum, 15);
	    }
	    
	    
	    
	    
		switch(accounttype)
		{
		  
		case 1 :  addbtn = driver.findElement(By.xpath("//button[@id='s_1_1_52_0_Ctrl']")); 
		          addbtn.click();
		          org = driver.findElement(By.xpath("//input[@name='s_1_1_30_0']"));
		          g.alwayswait(2);
		          org.clear();
		          org.sendKeys(orgname);
		          challengeQues = driver.findElement(By.xpath("//input[@name='s_1_1_34_0']"));
		          challengeQues.sendKeys("Name of your pet?");
		          pin = driver.findElement(By.xpath("//input[@name='s_1_1_27_0']"));
		          challengeAnswer = driver.findElement(By.xpath("//input[@name='s_1_1_43_0']"));
		          accntType = driver.findElement(By.xpath("//input[@name='s_1_1_33_0']"));
		          statementFreq = driver.findElement(By.name("s_1_1_22_0"));
		          statementFreq.clear();
		          statementFreq.sendKeys(freq);
		          pin.click();
		          ok = driver.findElement(By.id("s_4_1_63_0_Ctrl"));
		          ok.click();
		          break;
		          
		case 2 :  
	    		  commerical.click();
	    		  addbtn = driver.findElement(By.xpath("//button[@id='s_2_1_50_0_Ctrl']")); 
			      addbtn.click();
			      org = driver.findElement(By.xpath("//input[@name='s_2_1_28_0']"));
		          g.alwayswait(2);
		          org.clear();
		          org.sendKeys("NEW JERSEY E-ZPASS");
		          challengeQues = driver.findElement(By.xpath("//input[@name='s_2_1_32_0']"));
		  		  challengeQues.clear();
		          challengeQues.sendKeys("Name of your pet?");
			      pin = driver.findElement(By.xpath("//input[@name='s_2_1_24_0']"));
				  challengeAnswer = driver.findElement(By.xpath("//input[@name='s_2_1_41_0']"));
				  accntType = driver.findElement(By.xpath("//input[@name='s_2_1_31_0']"));
				  accntName = driver.findElement(By.xpath("//input[@name='s_2_1_30_0']"));
				  
				  dbaName = driver.findElement(By.xpath("//input[@name='s_2_1_9_0']"));
				  dbaName.sendKeys(companyname);
				  accntName.sendKeys(companyname);
				  statementFreq = driver.findElement(By.name("s_2_1_19_0"));
				  statementFreq.clear();
		          statementFreq.sendKeys("NONE");
		          pin.click();
		          ok = driver.findElement(By.id("s_4_1_61_0_Ctrl")); 
		          ok.click();
				  break;
				 
		case 3 :  commerical.click();
		  		  addbtn = driver.findElement(By.xpath("//button[@id='s_2_1_50_0_Ctrl']")); 
		  		  addbtn.click();
		  		  org = driver.findElement(By.xpath("//input[@name='s_2_1_28_0']"));
		  		  g.alwayswait(2);
		  		  org.clear();
		  		  org.sendKeys("NEW JERSEY E-ZPASS");
		  		  challengeQues = driver.findElement(By.xpath("//input[@name='s_2_1_32_0']"));
		  		  challengeQues.clear();
		          challengeQues.sendKeys("Name of your pet?");
		  		  accntType = driver.findElement(By.name("s_2_1_31_0"));
		          accntType.clear();
		          accntType.sendKeys("COMMERCIAL");
		  		  pin = driver.findElement(By.xpath("//input[@name='s_2_1_24_0']"));
		  		  challengeAnswer = driver.findElement(By.xpath("//input[@name='s_2_1_41_0']"));
		  		  
		  		  accntName = driver.findElement(By.xpath("//input[@name='s_2_1_30_0']"));
		  
		  		  dbaName = driver.findElement(By.xpath("//input[@name='s_2_1_9_0']"));
		  		  dbaName.sendKeys(companyname);
		  		  accntName.sendKeys(companyname);
		  		  statementFreq = driver.findElement(By.name("s_2_1_19_0"));
		  		  statementFreq.clear();
		  		  statementFreq.sendKeys("NONE");
		  		  pin.click();
		  		  ok= driver.findElement(By.id("s_4_1_61_0_Ctrl")); 
		  		  ok.click();
		  		  break;
				  
       default :  break;			  
				  
		
		}
		
		g.alwayswait(3);
        pin.sendKeys(pinnumber);   
	    challengeAnswer.sendKeys(pinnumber);
	    
	    
		
	}
	
	
	
	public static void contactdetailsPrivate(WebDriver driver,String Lastname,String Firstname,String phnumber) throws InterruptedException 
	{
		WebElement addbtn = driver.findElement(By.xpath("//button[@id='s_2_1_11_0_Ctrl']")); 
        addbtn.click();
        
        g.alwayswait(2);
               
        WebElement lastnamebox = driver.findElement(By.xpath("//td[@id='1_s_2_l_Last_Name']"));
        lastnamebox.click();
        WebElement lastname = driver.findElement(By.xpath("//input[@id='1_Last_Name']"));
      
        lastname.sendKeys(Lastname);
        
        WebElement firstnamebox = driver.findElement(By.xpath("//td[@id='1_s_2_l_First_Name']"));
        firstnamebox.click();
        WebElement firstname = driver.findElement(By.xpath("//input[@id='1_First_Name']"));
        firstname.sendKeys(Firstname);
        
        WebElement daynobox = driver.findElement(By.xpath("//td[@id='1_s_2_l_Contact_Phone_Number']"));
        daynobox.click();
        WebElement dayno = driver.findElement(By.xpath("//input[@id='1_Contact_Phone_Number']"));
        dayno.sendKeys(phnumber);  
        
        
        		
	}
	
	
	public static void contactdetailsBusiness(WebDriver driver,String Lastname,String Firstname,String phnumber) throws InterruptedException 
	{
		WebElement addbtn = driver.findElement(By.xpath("//button[@id='s_1_1_11_0_Ctrl']")); 
        addbtn.click();
        
        g.alwayswait(2);
               
        WebElement lastnamebox = driver.findElement(By.xpath("//td[@id='1_s_1_l_Last_Name']"));
        lastnamebox.click();
        WebElement lastname = driver.findElement(By.xpath("//input[@id='1_Last_Name']"));
      
        lastname.sendKeys(Lastname);
        
        WebElement firstnamebox = driver.findElement(By.xpath("//td[@id='1_s_1_l_First_Name']"));
        firstnamebox.click();
        WebElement firstname = driver.findElement(By.xpath("//input[@id='1_First_Name']"));
        firstname.sendKeys(Firstname);
        
        WebElement daynobox = driver.findElement(By.xpath("//td[@id='1_s_1_l_Contact_Phone_Number']"));
        daynobox.click();
        WebElement dayno = driver.findElement(By.xpath("//input[@id='1_Contact_Phone_Number']"));
        dayno.sendKeys(phnumber);  
        
        
        		
	}
	
	public static void addressDetailsPrivate(WebDriver driver,String add1,String add2,String zip) 
	{
		WebElement addbtn = driver.findElement(By.xpath("//button[@id='s_3_1_11_0_Ctrl']"));  
        addbtn.click();
        WebElement address1box = driver.findElement(By.id("1_s_3_l_Street_Address")); 
        address1box.click();
        WebElement address1 = driver.findElement(By.id("1_Street_Address"));        
        address1.sendKeys(add1);
        
        WebElement address2box = driver.findElement(By.id("1_s_3_l_Street_Address_2")); 
        address2box.click();
        WebElement address2 = driver.findElement(By.id("1_Street_Address_2"));
        address2.sendKeys(add2);
        
        WebElement zipcodetxt = driver.findElement(By.xpath("//td[@id='1_s_3_l_Postal_Code']"));
        zipcodetxt.click();
        
        WebElement zipcodetext = driver.findElement(By.xpath("//input[@id='1_Postal_Code']"));
        zipcodetext.sendKeys(zip);   
        
        WebElement zipcodeplustxt = driver.findElement(By.xpath("//td[@id='1_s_3_l_Zip_Plus']"));
        zipcodeplustxt.click(); 
        
        
	}
	
	public static void addressDetailsBusiness(WebDriver driver,String add1,String add2,String zip) 
	{
		WebElement addbtn = driver.findElement(By.xpath("//button[@id='s_3_1_11_0_Ctrl']"));  
        addbtn.click();
        WebElement address1box = driver.findElement(By.id("1_s_3_l_Street_Address")); 
        address1box.click();
        WebElement address1 = driver.findElement(By.id("1_Street_Address"));        
        address1.sendKeys(add1);
        
        WebElement address2box = driver.findElement(By.id("1_s_3_l_Street_Address_2")); 
        address2box.click();
        WebElement address2 = driver.findElement(By.id("1_Street_Address_2"));
        address2.sendKeys(add2);
        
        WebElement zipcodetxt = driver.findElement(By.xpath("//td[@id='1_s_3_l_Postal_Code']"));
        zipcodetxt.click();
        
        WebElement zipcodetext = driver.findElement(By.xpath("//input[@id='1_Postal_Code']"));
        zipcodetext.sendKeys(zip);   
        
        WebElement zipcodeplustxt = driver.findElement(By.xpath("//td[@id='1_s_3_l_Zip_Plus']"));
        zipcodeplustxt.click(); 
        
        
	}
	

}
