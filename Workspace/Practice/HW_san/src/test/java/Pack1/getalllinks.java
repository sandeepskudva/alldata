package Pack1;

import java.sql.Driver;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
public class getalllinks {
	
	@Test
	 public void  linktest(){
		 System.setProperty("webdriver.chrome.driver", "D:\\TSG\\Jars\\chromedriver.exe"); 	
		 WebDriver driver = new ChromeDriver();
		 
		 
		 //Launching sample website
		 driver.get("https://artoftesting.com/sampleSiteForSelenium");
		 driver.manage().window().maximize();
		 
		 //Get list of web-elements with tagName  - a
		 List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		 
		 //Traversing through the list and printing its text along with link address
		 for(WebElement link:allLinks){
		 System.out.println(link.getText() + " - " + link.getAttribute("href"));
		 }
		 
		 //Commenting driver.quit() for user to easily verify the links
		 //driver.quit();
		 } 

	@Test
	public void  getlinktext(){
		 System.setProperty("webdriver.chrome.driver", "D:\\TSG\\Jars\\chromedriver.exe"); 	
		 WebDriver driver = new ChromeDriver();
		 
		 
		 //Launching sample website
		 driver.get("http://demo.guru99.com/v1/");
		 driver.manage().window().maximize();
		 
		 WebElement selLink = driver.findElement(By.xpath("//a[contains(text(),'Selenium')and @data-toggle='dropdown'] "));
		 selLink.click();
		 //Get list of web-elements with tagName  - a
//		 List<WebElement> allLinks = driver.findElements(By.tagName("a"));
//		 
//		 //Traversing through the list and printing its text along with link address
//		 for(WebElement link:allLinks){
//		 if(link.getText()!="")
//		 {
//			System.out.println(link.getText());
//		 }
//		 }
		 
		 //Commenting driver.quit() for user to easily verify the links
		 //driver.quit();
		 } 
}
