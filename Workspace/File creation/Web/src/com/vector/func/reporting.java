package com.vector.func;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class reporting extends SuperBase{
	
	public void addstep(ExtentReports report,ExtentTest logger,String stepstatus,String testcasenumber,String message,boolean screenshot) throws IOException 
	{	
		String screenshotname;
		writeToLogFile(message);	
		screenshotname = testcasenumber+"_"+message;
		if(screenshot)
		{		
			String screenshotpath;
			String image;
			
			if(stepstatus.toUpperCase().equals("PASS"))			
			{
				screenshotpath = Genric.CaptureScreenshot(driver,screenshotname);
			    image = logger.addScreenCapture(screenshotpath);
				logger.log(LogStatus.PASS,screenshotname,screenshotname+image);	
			}
			else if(stepstatus.toUpperCase().equals("FAIL"))
			{
			
				screenshotpath = Genric.CaptureScreenshot(driver,screenshotname);
				image = logger.addScreenCapture(screenshotpath);
				logger.log(LogStatus.FAIL,screenshotname,screenshotname+image);	
			
			}
			else if(stepstatus.toUpperCase().equals("INFO"))
			{	
				screenshotpath = Genric.CaptureScreenshot(driver,screenshotname);
				image = logger.addScreenCapture(screenshotpath);
				logger.log(LogStatus.INFO,screenshotname,screenshotname+image);
			}
			else if(stepstatus.toUpperCase().equals("FATAL"))
			{	
				screenshotpath = Genric.CaptureScreenshot(driver,screenshotname);
				image = logger.addScreenCapture(screenshotpath);
				logger.log(LogStatus.FATAL,screenshotname,screenshotname+image);
			}
			else;
		
			}
		else
		{		
			
			if(stepstatus.toUpperCase().equals("PASS"))			
			{
				logger.log(LogStatus.PASS,screenshotname);	
			}
			else if(stepstatus.toUpperCase().equals("FAIL"))
			{
			
				
				logger.log(LogStatus.FAIL,screenshotname);
			
			}
			else if(stepstatus.toUpperCase().equals("INFO"))
			{	
				
				logger.log(LogStatus.INFO,screenshotname);	
			}
			else if(stepstatus.toUpperCase().equals("FATAL"))
			{	
				
				logger.log(LogStatus.FATAL,screenshotname);	
			}
			else;
			 
		
			}
			
		   
			
	}
	
	public void addstepelement(ExtentReports report,ExtentTest logger,String stepstatus,String testcasenumber,String message,WebElement ele) throws IOException 
	{	
		String screenshotname;
		screenshotname = testcasenumber+"_"+message;
		 writeToLogFile(message);		
			String screenshotpath1;
			String image;
			
			if(stepstatus.toUpperCase().equals("PASS"))			
			{						
				screenshotpath1 = Genric.elementscreenshot(driver,screenshotname, ele, screenshotpath);
			    image = logger.addScreenCapture(screenshotpath1);
				logger.log(LogStatus.PASS,screenshotname,screenshotname+image);	
			}
			else if(stepstatus.toUpperCase().equals("FAIL"))
			{
			
				screenshotpath1 = Genric.elementscreenshot(driver,screenshotname, ele, screenshotpath);
			    image = logger.addScreenCapture(screenshotpath1);
				logger.log(LogStatus.FAIL,screenshotname,screenshotname+image);	
			
			}
			else if(stepstatus.toUpperCase().equals("INFO"))
			{	
				screenshotpath1 = Genric.elementscreenshot(driver,screenshotname, ele, screenshotpath);
			    image = logger.addScreenCapture(screenshotpath1);
				logger.log(LogStatus.INFO,screenshotname,screenshotname+image);	
			}
			else;
		
			}
	
	
	public static void writeToLogFile(String message) throws IOException 
	{
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		File file = new File(logfilename);
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String entrylog = timeStamp + " : " + message;
		
		fw = new FileWriter(file.getAbsoluteFile(), true);
		bw = new BufferedWriter(fw);
		bw.append(entrylog);
		bw.append("\n");
		
		bw.close();
		fw.close();
		
	}
	

}
