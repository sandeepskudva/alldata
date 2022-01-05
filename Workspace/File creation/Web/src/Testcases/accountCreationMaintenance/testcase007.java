package Testcases.accountCreationMaintenance;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Webpages.forgotPasswordUsername;
import Webpages.resetMyPassword;
import Webpages.webLogin;
import webAccountCreation.driverScript;

public class testcase007 extends driverScript  {

	public void case007(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException 
	{
		webLogin wl = new webLogin();
		forgotPasswordUsername fpu = new forgotPasswordUsername();
		resetMyPassword rmp = new resetMyPassword();
		
		boolean status=true;
		
		status = wl.gotologinmodule(report, logger);
		if(status)status=wl.forgotPassword(report, logger);		
		if(status)status=fpu.passwordRet(report, logger, Rownum);
		if(status)status=rmp.option1(report, logger, Rownum);
		if(status)status=rmp.fillnewpassowrd(report, logger, Rownum);
	}

}
