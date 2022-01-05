package com.vector.testcases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.vector.func.Genric;
import com.vector.func.SuperTestNG;
import com.vector.pages.LoginPage;

public class testaccountLogin extends SuperTestNG {
	
	@Test
	public void accountLogin() throws EncryptedDocumentException, InvalidFormatException, IOException
	{
	
	LoginPage lp = new LoginPage(driver);
	Genric g = new Genric();
	
	String uname = g.ExcelRead(accountype, Sheetpath, Rownum, 2);
    String pwd = g.ExcelRead(accountype, Sheetpath, Rownum, 3);
	lp.login(uname, pwd);
	}

}
