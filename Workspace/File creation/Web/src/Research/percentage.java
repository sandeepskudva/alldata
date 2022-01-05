	package Research;

import java.io.IOException;
import java.sql.SQLException;


import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.vector.func.Genric;

import ch.qos.logback.core.net.SyslogOutputStream;

public class percentage {

	public static void main(String[] args) throws SQLException, EncryptedDocumentException, InvalidFormatException, IOException, ClassNotFoundException {

		   int a=10;
		   int b=20;
		  float perc = (((float)a/b)*100) ;
		  
		  System.out.println(perc);
		    
		  int n=5;
		  for(int i=0;i<=n;i++)
		  {
			for(int j=0;j<i;j++)
			{
			  System.out.print("*");
			}
		 
		  System.out.println(" ");
		  }
		
	
	  for(int k=n;k>=0;k--)
	  {
		for(int l=0;l<n;l++)
		{
		  if(l>=k)
		  {	  
			System.out.print("*");
		  }
		  else
		  {
			System.out.print(" ");  
		  }
		}
	 
	  System.out.println("");
	  }
	

	
	
	for(int k=0;k<n;k++)
	  {
		for(int l=0;l<n;l++)
		{
		  if(l>=k)
		  {	  
			System.out.print("*");
		  }
		  else
		  {
			System.out.print(" ");  
		  }
		}
	 
	  System.out.println("");
	  }
	

	}
	
	
	
	
	
}
