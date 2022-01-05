package ServiceNowTicket;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class serviceNow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    String URL ="https://conduent.service-now.com//itserviceportal";	    
	    driver.get(URL);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    driver.findElement(By.id("username")).sendKeys("50004561");
	    driver.findElement(By.name("login")).click();
	    driver.findElement(By.id("Ecom_User_ID")).sendKeys("50004561");
	    driver.findElement(By.id("Ecom_Password")).sendKeys("Rubina@12");
	    driver.findElement(By.name("loginButton2")).click();
	    driver.findElement(By.xpath("//h2[text()='Request IT']")).click();
	    driver.findElement(By.xpath("//h2[@title='Create Incident']")).click();
	    driver.findElement(By.xpath("//a[span[@id='select2-chosen-1']]//b[@role='presentation']")).click();
	    driver.findElement(By.id("s2id_autogen1_search")).sendKeys("Issue impacting a client");
	    driver.findElement(By.xpath("//a[span[@id='select2-chosen-5']]//b[@role='presentation']")).click();
	    
	    
	    
	    driver.findElement(By.id("s2id_autogen5_search")).click();
		driver.findElement(By.id("s2id_autogen5_search")).sendKeys("Transportation Department");
		driver.findElement(By.id("s2id_autogen5_search")).click();
	    
	    

	}

}
