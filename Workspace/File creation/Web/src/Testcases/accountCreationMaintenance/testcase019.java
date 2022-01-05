package Testcases.accountCreationMaintenance;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Webpages.accountHome;
import Webpages.increaseBalancePage;
import Webpages.webLogin;
import webAccountCreation.driverScript;

public class testcase019 extends driverScript  {

	public void case019(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException 
	{
		webLogin wl = new webLogin();
		accountHome ah = new accountHome();
		increaseBalancePage ib = new increaseBalancePage();
		
			
		boolean status=true;
		
		status = wl.gotologinmodule(report, logger);
		if(status)status=wl.login(report,logger,Rownum);
		if(status)status=ah.validatehome(report, logger,Rownum);
		if(status)status=ah.increasebalance(report, logger);
		if(status)status=ib.completePayment(report, logger, Rownum, "12.00");
		if(status)status=ib.verifyPayment(report, logger, Rownum, "12.00");
		
	}

}
