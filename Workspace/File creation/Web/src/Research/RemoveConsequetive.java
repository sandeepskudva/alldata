	package Research;

import java.io.IOException;
import java.sql.SQLException;


import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;



public class RemoveConsequetive {

	public static void main(String[] args) throws SQLException, EncryptedDocumentException, InvalidFormatException, IOException, ClassNotFoundException {

		
		String word = "aaaabaas";
		String replaceword ="";
		int count =0;
		
		for(int i=0;i<word.length()-1;i++)
		{
			
			if(word.charAt(i)==word.charAt(i+1))
			{	
			count++;
			i++;
			}
				
			
		}
		System.out.println(count);
		System.out.println(word);
	
	}
	
}
