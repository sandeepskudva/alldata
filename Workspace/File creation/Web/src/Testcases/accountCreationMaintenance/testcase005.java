package Testcases.accountCreationMaintenance;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Webpages.creationTagsPage;
import Webpages.accountContactDetailsPage;
import Webpages.signupPage;
import webAccountCreation.driverScript;

public class testcase005 extends driverScript  {

	public void case005(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException 
	{
		existingplate=true;
		signupPage sp = new signupPage();		
		accountContactDetailsPage a = new accountContactDetailsPage();
    	creationTagsPage t = new creationTagsPage();    
    	boolean status=true;
    	status = sp.CreatePrivate(report,logger);
    	if(status)status=a.fillNameAdressSection(report,logger,Rownum,"PRIVATE","c@c.com");
    	if(status)status=t.filltagSectionPrivate(report,logger,Rownum);   
    	if(status)status=t.validateMessage(report,logger,"G102022M","NJ");   
    	
	}

}
