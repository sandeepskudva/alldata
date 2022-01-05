package Testcases.accountCreationMaintenance;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Webpages.ConfirmationPage;
import Webpages.creationTagsPage;
import Webpages.accountContactDetailsPage;
import Webpages.paymentPage;
import Webpages.planPage;
import Webpages.signupPage;
import webAccountCreation.driverScript;


public class testcase002 extends driverScript  {
	public void case002(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException 
	{
		
		signupPage sp = new signupPage();		
		accountContactDetailsPage a = new accountContactDetailsPage();
    	creationTagsPage t = new creationTagsPage();
    	planPage p = new planPage();
    	paymentPage pp = new paymentPage();
    	ConfirmationPage cp = new ConfirmationPage();
    	boolean status=true;
    	status = sp.CreateBusiness(report,logger);
    	if(status)status=a.fillNameAdressSection(report,logger,Rownum,"BUSINESS","b@b.com");
    	if(status)status=t.filltagSectionBusiness(report,logger,Rownum);
    	if(status)status=p.fillPlanSection(report,logger,Rownum);
    	if(status)status=pp.fillPaySection(report,logger,Rownum);
    	if(status)status=pp.fillSecondaryPaySection(report, logger, Rownum);
    	if(status)status=pp.clickConfirmation(report,logger);
    	if(status)status=cp.clickCompleteOrder(report,logger);
    	if(status)status=cp.captureSRNumber(report, logger);
	}
}
