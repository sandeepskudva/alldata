package Testcases.accountCreationMaintenance;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.vector.func.Genric;
import com.vector.func.SuperBase;


public class testcaseselector extends SuperBase{
	
	public static void be(String detail, String Sheetpath,int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		
	       Genric g = new Genric();
	     
		String URL = g.ExcelRead(detail, Sheetpath, Rownum, 1);
	    System.out.println(URL);
	    driver.get(URL);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().window().maximize();         
	}
	
	public static void af()
	{
		driver.close();
	}

	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void selection(ExtentReports report,ExtentTest logger,String testcasenumber,String detail, String Sheetpath, int Rownum) throws EncryptedDocumentException, InvalidFormatException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException 
	{
		be(detail,Sheetpath,Rownum);	
		
	    String classname = "Testcases.accountCreationMaintenance.testcase" + testcasenumber;
	    System.out.println(classname);
	    Class cls = Class.forName(classname);
	    Object obj =  (Object) cls.newInstance();
	    Method m = cls.getMethod("case"+testcasenumber,ExtentReports.class,ExtentTest.class,int.class) ;
	    m.invoke(obj,report,logger,Rownum);		
	    af();	
	}
		

}




//if(testcasenumber.equals("001"))
//{   	 	    	 
//	testcase001 c01 = new testcase001();
//	c01.case001(report,logger,Rownum);   	
//}
//else if(testcasenumber.equals("002"))
