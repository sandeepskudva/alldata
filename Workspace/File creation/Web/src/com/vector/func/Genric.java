package com.vector.func;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.io.FileUtils;
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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Genric extends SuperBase {
	 
	public static char randomCharacter() {
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'a');
		return c;
	}
	
	
	public static boolean alertMessageCheck(String SucessORDanger,String expected) throws InterruptedException
	{
		String pageloaded=null;
		if(SucessORDanger.equals("S"))
		{	
			WebElement pageverify = driver.findElement(By.xpath("//div[@class='alert alert-success message_tag']"));
		
			pageloaded = pageverify.getText().trim();
			Thread.sleep(2000);
		}
		else
		{
			WebElement pageverify = driver.findElement(By.xpath("//div[@class='alert alert-danger error_msg']"));
			
			pageloaded = pageverify.getText().trim();
		}
		
		
		if(pageloaded.equals(expected))
		{
			return true;
		}
		else
		{
			return false;
			
		}
	}
	
	
	public static void presstab() throws AWTException
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		
	}
	
	 public void alwayswait(int timeinseconds) 
		{
			try {
				Thread.sleep(timeinseconds * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(timeinseconds+" seconds Wait is over");
		}

		public void controlplus(WebDriver driver,String character)
		{
			Actions actionObj = new Actions(driver);
		    actionObj.keyDown(Keys.CONTROL)
		             .sendKeys(Keys.chord(character))
		             .keyUp(Keys.CONTROL)
		             .perform();
		}
		
		public static void dropdown(WebDriver driver, WebElement element, String value)
		{
			Select select = new Select(element);
		    select.deselectAll();
		    select.selectByVisibleText(value);
		}
		
		public String ExcelRead(String Sheetname,String Path,int row1, int col1) throws EncryptedDocumentException, InvalidFormatException, IOException{

			
			FileInputStream fis=new FileInputStream(Path);
			Workbook wb=WorkbookFactory.create(fis);
			Sheet sh=wb.getSheet(Sheetname);
			Row row=sh.getRow(row1);
			Cell cell=row.getCell(col1);
			String cellvalue=cell.getStringCellValue();	
			return cellvalue;

		}



		public  void ExcelWrite(String Sheetname,String Path,int row1, int col1, String value) throws EncryptedDocumentException, InvalidFormatException, IOException{

			
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
		
		public static boolean isAlertPresent(WebDriver driver){
		    boolean foundAlert = false;
		    WebDriverWait wait = new WebDriverWait(driver,1 /*timeout in seconds*/);
		    try {
		        wait.until(ExpectedConditions.alertIsPresent());
		        foundAlert = true;
		    } catch (TimeoutException eTO) {
		        foundAlert = false;
		    }
		    return foundAlert;
		}
		
		
		public static String CaptureScreenshot(WebDriver driver,String Screenshot_name) 
	      {
			
				try{
					System.out.println("Path is"+screenshotpath);
					TakesScreenshot ts = (TakesScreenshot)driver;
					File source = ts.getScreenshotAs(OutputType.FILE);
					
					String dest = screenshotpath+Screenshot_name+".png";
					System.out.println("We are here"+dest);
					
					File destination = new File(dest);
					FileUtils.copyFile(source, destination);
					System.out.println("Screenshot taken");
					return dest;		
					 
				}
				catch(Exception e)
				{
					System.out.println("Exception in screenshot"+e.getMessage());
					return e.getMessage();
				}
			}
		
		
		public static String elementscreenshot(WebDriver driver,String Screenshot_name,WebElement ele, String path) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			BufferedImage  fullImg = ImageIO.read(source);

			// Get the location of element on the page
			Point point = ele.getLocation();

			// Get width and height of the element
			int eleWidth = ele.getSize().getWidth();
			int eleHeight = ele.getSize().getHeight();

			// Crop the entire page screenshot to get only element screenshot
			BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
			    eleWidth, eleHeight);
			ImageIO.write(eleScreenshot, "png", source);

			// Copy the element screenshot to disk
			String dest = path+Screenshot_name+".png";
			System.out.println("We are here"+dest);
			
			File destination = new File(dest);
			FileUtils.copyFile(source, destination);
			
			return dest;
			
		}
		
		@SuppressWarnings("finally")
		public String imageextract(String imagepath)
		{
	       File imageFile = new File(imagepath);
	        
	        ITesseract instance = new Tesseract();        // JNA Interface Mapping
	        instance.setDatapath(tessaractpath);   
	        String result = "0000";
	        try {
	            result = instance.doOCR(imageFile);
	            System.out.println(result);
	        } catch (TesseractException e) {
	        	
	            //System.err.println(e.getMessage());
	        }
	        finally
	        {
	        	return result;
	        }
		}
		
		public static void sendemail(String distributionlist,String subject,String filepath, String attachmentname)
		{

		final String username = "web_test@yahoo.com";
		final String password = "Welcome.123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.mail.yahoo.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		        new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(username, password);
		            }
		        });

		try {

		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(username));
		    message.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse(distributionlist));
		    message.setSubject(subject);
		    message.setText("PFA");

		    MimeBodyPart messageBodyPart = new MimeBodyPart();

		    Multipart multipart = new MimeMultipart();

		    messageBodyPart = new MimeBodyPart();
		    String file = filepath;
		    String fileName = attachmentname;
		    DataSource source = new FileDataSource(file);
		    messageBodyPart.setDataHandler(new DataHandler(source));
		    messageBodyPart.setFileName(fileName);
		    multipart.addBodyPart(messageBodyPart);

		    message.setContent(multipart);

		    System.out.println("Sending Email");

		    Transport.send(message);

		    System.out.println("Done");

		} catch (MessagingException e) {
		    e.printStackTrace();
		}
		}
		
		public static String waitForElementNotVisible(int timeOutInSeconds, WebDriver driver, String elementXPath) {
		    if ((driver == null) || (elementXPath == null) || elementXPath.isEmpty()) {

		        return "Wrong usage of WaitforElementNotVisible()";
		    }
		    try {
		        (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.invisibilityOfElementLocated(By
		                .xpath(elementXPath)));
		        return null;
		    } catch (TimeoutException e) {
		        return "Build your own errormessage...";
		    }
		    
		}
		
		
		
		
		@SuppressWarnings("finally")
		public String executefetchQuery(String query,String column) throws SQLException, EncryptedDocumentException, InvalidFormatException, IOException, ClassNotFoundException
		{
			String result = null;	 
			
				
			
			//DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		    Class.forName("com.mysql.cj.jdbc.Driver");
			
			String dbURL = ExcelRead("TestSuite", Sheetpath, 1, 8); 	
			
			String strUserID = "pbatch";
			String strPassword = ExcelRead("TestSuite", Sheetpath, 2, 8);	      
	        Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);       
	         	         
	        ResultSet rs = c.createStatement().executeQuery(query);
	 	    	   
	 	    while(rs.next()){
	 	    result =rs.getString(column);  
	 	    }
	 	 
	 	    if(result==null)
	 	    {	
	 	    	result = "0";
	 	    	

	 	    }	 
	 	    	
		
	 	     c.close();
        	 return result; 
        	 
        
	 		
	 	}
		
		
			public String getetcaccountid(String accountnumber) throws EncryptedDocumentException, InvalidFormatException, ClassNotFoundException, SQLException, IOException {
		   
				String query = "select etc_account_id from v_etc_account where accountnumber='"+accountnumber+"'";
				return executefetchQuery(query,"etc_account_id");
		}
		
		
			public static void highLightElement(WebDriver driver, WebElement element,String color)
			{
			JavascriptExecutor js=(JavascriptExecutor)driver; 
			 
			
			js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid "+color+";');", element);
			 
			try 
			{
			Thread.sleep(500);
			} 
			catch (InterruptedException e) {
			 
			System.out.println(e.getMessage());
			} 
			 
			//js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element); 
			 
			}
			
		
		
}	
	
