package Testcases.accountCreationMaintenance;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Webpages.accountHome;
import Webpages.addTagsVehiclesPage;
import Webpages.webLogin;
import webAccountCreation.driverScript;

public class testcase015 extends driverScript  {

	public void case015(ExtentReports report,ExtentTest logger,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, SQLException 
	{
		webLogin wl = new webLogin();
		accountHome ah = new accountHome();
		addTagsVehiclesPage atv = new addTagsVehiclesPage();
			
		boolean status=true;
		
		status = wl.gotologinmodule(report, logger);
		if(status)status=wl.login(report,logger,Rownum);
		if(status)status=ah.validatehome(report, logger,Rownum);
		if(status)status=ah.gotoTagVehicle(report, logger);
		if(status)status=atv.goToAddVechile(report, logger, Rownum);
		if(status)status=atv.addVechile(report, logger, Rownum,"ANY");
		if(status)status=atv.messageCheckVehicle(report, logger, Rownum, "Valid");
		if(status)status=atv.modifyVechile(report, logger, Rownum);
		if(status)status=atv.messageCheckVehicle(report, logger, Rownum, "Changes");
		if(status)status=atv.goToAddVechile(report, logger, Rownum);
		if(status)status=atv.addVechile(report, logger, Rownum,"ANY");
		if(status)status=atv.messageCheckVehicle(report, logger, Rownum, "Valid");	
		if(status)status=atv.deleteVehicle(report, logger, Rownum);
		if(status)status=atv.messageCheckVehicle(report, logger, Rownum, "Deleted");	
	}

}
