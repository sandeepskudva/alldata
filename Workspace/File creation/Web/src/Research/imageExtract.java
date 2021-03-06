package Research;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import net.sourceforge.tess4j.*;


import com.vector.func.Genric;

public class imageExtract {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, InterruptedException, TesseractException {
		// TODO Auto-generated method stub
		try{
			
		
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://10.36.158.76/en/home/index.shtml#nogo");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	    driver.manage().window().maximize();    
	    
	    WebElement loginmodule = driver.findElement(By.xpath("//a[contains(text(),'Click Here To Log In')]"));
	    loginmodule.click();
	  //Loading jQuery Real Person Captcha demonstration page
	    
	    driver.findElement(By.id("login")).sendKeys("honetest0");;
	    driver.findElement(By.id("loginPassword")).sendKeys("Welcome1");
	    WebElement ele = driver.findElement(By.xpath("//img[@border=0]"));
	   
	 
	    //Setting the captcha values
	    
	    //Submit the form
	   
	
		String spath =Genric.elementscreenshot(driver,"image", ele, "E:\\TSG\\");
		Tesseract instance = Tesseract.getInstance();
		BufferedImage image = ImageIO.read(new File(spath)); 
		String imageText = instance.doOCR(image);
//		System.out.println(imageText);
//		
	    
	    
	    
//	    Ocr.setUp(); // one time setup
//	    System.out.println("setup done");
//	    Ocr ocr = new Ocr();
//	    System.out.println("object created");
//	    ocr.startEngine("eng", Ocr.SPEED_FASTEST);
//	    Thread.sleep(2000);
//	    if(Genric.isAlertPresent(driver))			  
//		  {	 
//	      System.out.println("Alert Found");	
//		  Alert alert=driver.switchTo().alert();
//		  alert.dismiss();
//		  }
//	    System.out.println("engine started");
//		  String imageText = ocr.recognize(image,Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);	
		  
		  
		 
		 
		  
						
		  System.out.println("Text Image :"+imageText);
		  driver.findElement(By.id("jcaptcha_response")).sendKeys(imageText);
		  
		  driver.findElement(By.name("btnLogin")).click(); 
		  
		}
		catch(ElementNotVisibleException E)
		{
			System.out.println("ElementNotVisibleException  :"+E.getMessage());
		}
	    
	}

}
