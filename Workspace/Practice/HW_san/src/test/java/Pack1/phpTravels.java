package Pack1;

import java.nio.file.FileSystemNotFoundException;
import java.sql.Driver;
import java.util.List;

import javax.sound.sampled.LineListener;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import groovyjarjarantlr4.v4.parse.ANTLRParser.id_return;

public class phpTravels {

	@Test
	public void xpathTest() throws InterruptedException{
	
    System.setProperty("webdriver.chrome.driver", "D:\\TSG\\Jars\\chromedriver.exe"); 	
	WebDriver driver = new ChromeDriver();
	driver.get("https://phptravels.com");
	driver.manage().window().maximize();
	
//	String expectedHeading = "APPLICATION TEST DRIVE";
//	
//	//Storing the text of the heading in a string
//	String heading = driver.findElement(By.xpath("//div[@class='col-sm-12']//h2")).getAttribute("innerHTML");
//	System.out.println("Heading is "+heading);
//	if(expectedHeading.equalsIgnoreCase(heading))
//      	System.out.println("The expected heading is same as actual heading --- "+heading);
//	else
//      	System.out.println("The expected heading doesn't match the actual heading --- "+heading);
//	
	List<WebElement> featurecontents = driver.findElements(By.xpath("//div[span[text()='Features']]//a"));
	
		
	for(Integer i=0;i<featurecontents.size();i++)
	{
		
		System.out.println("Text is "+featurecontents.get(i).getAttribute("innerHTML"));
	}
		
	driver.navigate().to("https://www.google.com");
	Thread.sleep(2000);
	driver.navigate().back();
	Thread.sleep(2000);
	driver.navigate().refresh();
	Thread.sleep(2000);
	driver.close();
	 
 }

}
