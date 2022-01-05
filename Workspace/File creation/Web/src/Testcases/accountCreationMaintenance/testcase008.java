package Testcases.accountCreationMaintenance;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Webpages.forgotPasswordUsername;
import Webpages.webLogin;
import webAccountCreation.driverScript;

public class testcase008 extends driverScript  {

	public void case008(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException 
	{
		webLogin wl = new webLogin();
		forgotPasswordUsername fpu = new forgotPasswordUsername();
		
		
		boolean status=true;
		
		status = wl.gotologinmodule(report, logger);
		if(status)status=wl.forgotusername(report, logger);		
		if(status)status=fpu.usernameRet(report, logger, Rownum,"A");
		
	}

}
