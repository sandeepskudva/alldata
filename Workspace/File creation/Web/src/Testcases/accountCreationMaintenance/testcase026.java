package Testcases.accountCreationMaintenance;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Webpages.accountHome;
import Webpages.stmtCorresPage;
import Webpages.webLogin;
import webAccountCreation.driverScript;

public class testcase026 extends driverScript  {

	public void case026(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException 
	{
		webLogin wl = new webLogin();
		accountHome ah = new accountHome();	
		stmtCorresPage stmt = new stmtCorresPage();
			
		boolean status=true;
		
		status = wl.gotologinmodule(report, logger);
		if(status)status=wl.login(report,logger,Rownum);
		if(status)status=ah.validatehome(report, logger,Rownum);
		if(status)status=ah.goToStmtCorrPage(report, logger);
		if(status)status=stmt.searchStatement(report, logger, Rownum);
		if(status)status=stmt.viewStatement(report, logger);
		
	}

}
