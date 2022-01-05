package webAccountCreation;

import java.awt.Desktop;
import java.io.File;

import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;
import com.vector.func.reporting;

import Testcases.accountCreationMaintenance.testcaseselector;

public class driverScript extends SuperBase {
	
	
	static ExtentReports report;
	static ExtentTest logger;
	public static void main(String[] args) throws Exception {
		
		try{
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
		//System.setProperty("webdriver.ie.driver", "E:\\Selenium\\IEDriverServer.exe");
		 
		  String testcasestatus= "TestSuite";		
		int i=1;
	    int Rownum=1;
	    int testcasecount=0;
	    
	    Genric g = new Genric();
	    
	   
	    String runstatus  =g.ExcelRead(testcasestatus, Sheetpath, i, 1);
	     
	    detail = g.ExcelRead(testcasestatus, Sheetpath, 0, 8);
	    System.out.println("details is "+detail);
	    
	    
	    report= new ExtentReports(reportpath);
	    report.loadConfig(new File("E:\\TSG\\Workspace\\File creation\\Web\\src\\config\\config.xml"));
	    report.assignProject("NJREBID");
	    
	    System.out.println("Report Path set : "+reportpath); 
		
	
	    
	    while (!runstatus.equals("END"))
	    {
	    	
	    	runstatus = g.ExcelRead(testcasestatus, Sheetpath, i, 1); 
	    	if(runstatus.equals("Y"))
	    	{   		
	    		final long startTime = System.currentTimeMillis();
	    		testcasecount++;
	    		
	    	     
	    	     System.out.println(runstatus);	    
	    	      testcasenumber = g.ExcelRead(testcasestatus, Sheetpath, i, 0);
	    	      String testcasedesc = g.ExcelRead(testcasestatus, Sheetpath, i, 2);
	    	     System.out.println("Executing Testcase : "+testcasenumber);
	    	     reporting.writeToLogFile("Starting testcase number : "+testcasenumber);
	    	     logger = report.startTest("Testcase_"+testcasenumber,testcasedesc);	
	    	    System.out.println("Testcase started : "+Rownum);
	    	     
	    	     testcaseselector ts = new testcaseselector();
	    	      driver = new ChromeDriver();
	    	     //driver = new FirefoxDriver();
	    	     //driver = new InternetExplorerDriver();
	    	    ts.selection(report,logger,testcasenumber,detail,Sheetpath, Rownum);   	
	    	    report.endTest(logger);
	    	    reporting.writeToLogFile("Ending testcase number : "+testcasenumber);
	    	    reporting.writeToLogFile("******************************************");
	    	    System.out.println("Testcase ended : "+testcasenumber);
	        
	    	final long endTime = System.currentTimeMillis();		
	    	System.out.println("Time taken to execute test case "+testcasenumber+" is "+(endTime-startTime)/1000 + " Seconds");	
	    	System.out.println(Rownum+"***************************************************************************************");
	    	
	    	}
	    	
	    	Rownum++; 
	    	i++;	
	    	
	    	
	    		  
	    }	 
	    if(testcasecount ==0)
	    {
	    	logger = report.startTest("NO Testcase");	
	    	r.addstep(report,logger, "FATAL", "none", "No Testcase set for run", false);
	    	report.endTest(logger);
	    }

	    
	   
	}
		catch(Exception allexception)
		{
			
			System.out.println("Error occured - Some value is null "+allexception.getMessage());		
			report.endTest(logger);
			
		}
		finally
		{			
			report.flush();	
			Thread.sleep(3000);
			
			File htmlFile = new File(reportpath);
			Desktop.getDesktop().browse(htmlFile.toURI());
		    //Genric.sendemail("aditie.singh@conduent.com", "Automation Test Results", reportpath, "Report.html");
		}
	}
	
	

}


