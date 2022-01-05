package Testcases.accountCreationMaintenance;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Webpages.accountHome;
import Webpages.paymentMethodPage;
import Webpages.webLogin;
import webAccountCreation.driverScript;

public class testcase020 extends driverScript  {

	public void case020(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException 
	{
		webLogin wl = new webLogin();
		accountHome ah = new accountHome();
		paymentMethodPage pm = new paymentMethodPage();
		
			
		boolean status=true;
		
		status = wl.gotologinmodule(report, logger);
		if(status)status=wl.login(report,logger,Rownum);
		if(status)status=ah.validatehome(report, logger,Rownum);
		if(status)status=ah.goToPaymentMethod(report, logger);
		if(status)status=pm.primaryEdit(report, logger,Rownum);
		if(status)status=pm.secondaryAddEdit(report, logger, Rownum);
		
		
	}

}
