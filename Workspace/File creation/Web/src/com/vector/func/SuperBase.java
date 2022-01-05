package com.vector.func;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class  SuperBase {
	
	
	public static WebDriver driver;
	public static reporting r= new reporting();
	public static String testcasenumber;
	public static boolean existingplate=false;
	public static Logger logfile;
	
	
	
//public static String testcasedesc;
	
	
	
	public Genric genric;
	long startTime;
	long endTime;
	
	public static int i=1;
    public static int Rownum=1;
    public static String detail= "QA";
    public static String commonpath= "E:\\TSG\\Workspace\\File creation\\Web\\";
    public static String Sheetpath = commonpath+"\\WebCreation.xlsx";
    public static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    public static String reportname = "Report_"+timeStamp+".html";
    public static String Runname = "Run"+timeStamp+"\\";
    public static String reportpath =commonpath+"Report\\Run\\"+Runname+reportname;
    public static String screenshotpath =commonpath+"Report\\Run\\"+Runname+"Screenshot\\";
    public static String tessaractpath ="E:\\TSG\\Selenium\\OCR old";
    public static String captchapath =commonpath+"Report\\Run\\"+Runname+"captcha\\";
    public static final String logfilename = commonpath+"Report\\Run\\"+Runname+"WebExecution.log";
		
		
	}
	

     