package accountCreation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class createPaymentFile {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		String Agencyname = "GSP";
		String FileOperation = "Payment";
		String CitationNumber = "T121201959116";
		int num = 2;
		String level = getLevel(CitationNumber);
		System.out.println(level);
		
		
		
		
		
	}
	
	public static String getLevel(String Noticenumber) throws SQLException
	{
		
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
	    String dbURL = "jdbc:oracle:thin:@10.36.20.51:1521/njrbtst";
	    String strUserID = "pbatch";
	    String strPassword = "pbatch443"; 
	    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
	    
	    String Statement = "SELECT CITATION_LEVEL_CD FROM V_CITATION_DETAIL WHERE CITATION_NUMBER = '"+Noticenumber+"' AND CITATION_STATUS_CD= 'OPEN'"; 
	    
	    ResultSet rs = c.createStatement().executeQuery(Statement);
	    String level = null;
	   
	    while(rs.next()){
	    	level =rs.getString("CITATION_LEVEL_CD");  
	    }
	   
	    if(level==null)
	    {	
	    	level = "NULL";		  

	    }
	  	    
		c.close();
		return level;
	}

}
