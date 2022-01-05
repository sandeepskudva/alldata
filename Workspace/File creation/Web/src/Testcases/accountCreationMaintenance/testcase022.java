package Testcases.accountCreationMaintenance;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Webpages.accountHome;
import Webpages.planPage;
import Webpages.webLogin;
import webAccountCreation.driverScript;

public class testcase022 extends driverScript  {

	public void case022(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException 
	{
		webLogin wl = new webLogin();
		accountHome ah = new accountHome();
		planPage pp = new planPage();
		
			
		boolean status=true;
		
		status = wl.gotologinmodule(report, logger);
		if(status)status=wl.login(report,logger,Rownum);
		if(status)status=ah.validatehome(report, logger,Rownum);
		if(status)status=ah.goToPlansPage(report, logger);
		if(status)status=pp.addPlan(report, logger, "ACE", true, false);
		
		
		
	}

}
