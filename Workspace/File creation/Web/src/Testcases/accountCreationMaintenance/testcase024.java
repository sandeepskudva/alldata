package Testcases.accountCreationMaintenance;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Webpages.accountHome;
import Webpages.transacationsPage;
import Webpages.webLogin;
import webAccountCreation.driverScript;

public class testcase024 extends driverScript  {

	public void case024(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException 
	{
		webLogin wl = new webLogin();
		accountHome ah = new accountHome();
		transacationsPage tp = new transacationsPage();
			
		boolean status=true;
		
		status = wl.gotologinmodule(report, logger);
		if(status)status=wl.login(report,logger,Rownum);
		if(status)status=ah.validatehome(report, logger,Rownum);
		if(status)status=ah.goToTransactionsPage(report, logger);
		if(status)status=tp.searchByTranDateOptionalN(report, logger, Rownum, "posted_date");
		
	}

}
