package Testcases.accountCreationMaintenance;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Webpages.accountHome;
import Webpages.violationsPage;
import Webpages.webLogin;
import webAccountCreation.driverScript;

public class testcase028 extends driverScript  {

	public void case028(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, SQLException 
	{
		webLogin wl = new webLogin();
		accountHome ah = new accountHome();	
		violationsPage vp = new violationsPage();
			
		boolean status=true;
		
		status = wl.gotologinmodule(report, logger);
		if(status)status=wl.login(report,logger,Rownum);
		if(status)status=ah.validatehome(report, logger,Rownum);
		if(status)status=ah.goToViolationsPage(report, logger);
			
		
	}

}
