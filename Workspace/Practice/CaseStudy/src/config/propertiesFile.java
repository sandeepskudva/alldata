package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class propertiesFile {
	
	static Properties prop = new Properties();
		public static String readProperties(String propertyname)
	{
		
		try {
			InputStream input = 	new FileInputStream("D:\\TSG\\Workspace\\Practice\\CaseStudy\\src\\config\\config.properties");
			prop.load(input);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return prop.getProperty(propertyname);
	}
		
		
	

}
