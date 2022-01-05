	package Research;



import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.reporting;



public class smoketest  {
	
	ExtentReports report;
	ExtentTest logger;
	WebDriver driver;
	reporting r= new reporting();
	
	@Test
	public void verifytitle() throws IOException
	{
		report = new ExtentReports("E:\\TSG\\Workspace\\File creation\\Web\\Report\\one.html");
		logger = report.startTest("TestTitle");
		
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		
		
		r.addstep(report, logger, "INFO", "001", "Browser Started", true);
		
		
		
		driver.manage().window().maximize();
		driver.get("http://wwww.learm-automation.com");
		
		r.addstep(report, logger, "Info", "001", "Application is running", false);
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Selenium"));
		
		r.addstep(report, logger, "PASS", "001", "Title verified", false);
	}
	
	@AfterMethod
	public void teardown(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
//			String screenshotpath = Genric.CaptureScreenshot(driver, result.getName());
//			String image = logger.addScreenCapture(screenshotpath);
			r.addstep(report, logger, "FAIL", "001", "Title verification failed", false);
		}
		
		report.endTest(logger);
		report.flush();
		driver.get("E:\\TSG\\Workspace\\File creation\\Web\\Report\\one.html");
//		SendAttachment sa = new SendAttachment();
//		sa.sendemail("E:\\TSG\\Workspace\\File creation\\Web\\Report\\one.html");
		
	}

}
