package Tests;
import java.sql.Driver;
import config.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.*;


public class amazonCaseStuday {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\TSG\\Jars\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String url = propertiesFile.readProperties("URL");
		driver.get(url);
		driver.manage().window().maximize();
		WebElement login = driver.findElement(By.xpath("//a[@class='nav-a nav-a-2   nav-progressive-attribute']"));
		login.click();
		WebElement email = driver.findElement(By.xpath("//input[@type='email']"));
		email.sendKeys("sandysandeepkudva@gmail.com");
		WebElement continuebtn = driver.findElement(By.xpath("//span[@id='continue']"));
		continuebtn.click();
		WebElement passtxtbox = driver.findElement(By.xpath("//input[@type='password']"));
		passtxtbox.sendKeys("speed.12.track");
		WebElement Signinbtn = driver.findElement(By.id("signInSubmit"));
		Signinbtn.click();
		
		WebElement allbtn = driver.findElement(By.xpath("//a[@id='nav-hamburger-menu']/span[text()='All']"));
		allbtn.click();
		
		Set<String> windowname = driver.getWindowHandles();
		System.out.println(windowname);
		Thread.sleep(4000);
		WebElement menfash = driver.findElement(By.xpath("//a[@data-menu-id='10']"));
		menfash.click();
		
		
		
		Thread.sleep(4000);
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@data-menu-id='10']//a[text()='Clothing']"))).click();
		
		WebElement Jeans = driver.findElement(By.xpath("//span[text()='Jeans']"));
		Jeans.click();
		
		WebElement size36Regular = driver.findElement(By.xpath("//button[@value='36' and @id='a-autoid-14-announce']"));
		size36Regular.click();
		
		String parentwindow = driver.getWindowHandle();
		
		Thread.sleep(4000);
		WebElement jeans2 = driver.findElement(By.xpath("//img[@data-image-index='2']"));
		jeans2.click();
		
		Set<String> allwindows = driver.getWindowHandles();
		
//		Iterator itr = 
//	
//		
//		
//		
	WebElement dropele = driver.findElement(By.name("dropdown_selected_size_name"));
		
		Select se = new Select(dropele);
		se.selectByVisibleText("36");
		
		WebElement addtocardbtn = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
		addtocardbtn.click();
		
		
		
		
		
		//driver.close();

	}

}
