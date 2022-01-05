package Research;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.reflections.Reflections;

import Testcases.accountCreationMaintenance.testcaseselector;



public class classcheck {
	
	 public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	    	
		  

//		    String className = "reporttest";
//		    String fullPathOfTheClass = "Research." + className;
//		    Class cls = Class.forName(fullPathOfTheClass);
//		    Object obj =  (Object) cls.newInstance();
//		    Method m = cls.getMethod("verifytitle");
//		    m.invoke(obj);
		    
		 
		 System.setProperty("webdriver.gecko.driver","D:\\Software\\geckodriver-v0.19.1-win64\\geckodriver.exe");
			ProfilesIni profile=new ProfilesIni();
			FirefoxProfile pf=profile.getProfile("SumitMirchi");
			WebDriver driver=new FirefoxDriver(pf); 
		
	    }

}
