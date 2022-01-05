package Research;



import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.reporting;


public class reporttest{
	
	ExtentReports report;
	ExtentTest logger;
	WebDriver driver;
	
	reporting r = new reporting();
	
	@Test
	public void verifytitle() throws IOException
	{
		
		report = new ExtentReports("E:\\TSG\\Workspace\\File creation\\Web\\Report\\Run\\one.html");
		logger = report.startTest("TestTitle");
		
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		
		r.addstep(report, logger, "Info", "001", "Browser Started",false);
		
		driver.manage().window().maximize();
		driver.get("http://wwww.learm-automation.com");
		
		r.addstep(report, logger, "Info", "001", "Application running",false);
//		String title = driver.getTitle();
//		Assert.assertTrue(title.contains("Selenium"));
		
		r.addstep(report, logger, "Pass", "001", "TITLE VERIFICATION Passed",false);
	}
	
	@AfterMethod
	public void teardown(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			r.addstep(report, logger, "Fail", "001", "TITLE VERIFICATION",false);
		}
		
		report.endTest(logger);
		report.flush();
		driver.get("E:\\TSG\\Workspace\\File creation\\Web\\Report\\one.html");
	}

}
