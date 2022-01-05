package common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class utilities {
	
	public static String isValidEmail(String emailaddress)
	{
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";  
	    Pattern pattern = Pattern.compile(regex);  
	    String result;
	       
	    Matcher matcher = pattern.matcher(emailaddress);  
	    if(matcher.matches())  
	    	result ="VALID";
	    else
	    	result ="NOT VALID";
	    
	    return result;
			
	}
	
	public static Map<String, String> validateEmailList(List<String> emailds)
	{		
				
		Map<String, String> emailValResult = new HashMap<String, String>();
		
		for (int i=0;i<emailds.size();i++)
		{
			emailValResult.put(emailds.get(i),isValidEmail(emailds.get(i)));
            
        }
		
		return emailValResult;
	}

}
