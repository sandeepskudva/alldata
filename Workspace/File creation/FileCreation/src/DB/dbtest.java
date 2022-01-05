package DB;

import  java.sql.Connection;		
		
import  java.sql.ResultSet;		
import  java.sql.DriverManager;		
import  java.sql.SQLException;

public class  dbtest {				
    	public static void  main(String[] args) throws  ClassNotFoundException, SQLException{
    			
    			
    		String filename = "20161001000008004.tr";
    		String xfer = getxferid(filename);
    		if(xfer.equals("NULL"))
    		{	    			
    			insertxferid(filename);
    			xfer = getxferid(filename);
    		}
    		
    	}	
    		
    	public static String getxferid(String filename) throws SQLException
    	{
    		
    		
    		
    		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
    			   String dbURL = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.36.20.51)(PORT = 1521))(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
    		    String strUserID = "pbatch";
    		    String strPassword = "batchp"; 
    		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
    		    
    		    String Statement = "select XFER_CONTROL_ID from t_xfer_control where XFER_FILE_NAME = '"+filename+"'"; 
    		    System.out.println(Statement);
    		    ResultSet rs = c.createStatement().executeQuery(Statement);
    		    String xfer = null;
    		    while(rs.next()){
    		    	xfer =rs.getString("XFER_CONTROL_ID");  
    		    }
    		    System.out.println(xfer);
    		    if(xfer==null)
    		    {	
    		    	xfer = "NULL";

    		    }
    		    
    		    
    			c.close();
    			return xfer;
    		}

    	public static void insertxferid(String filename) throws SQLException
    	{
    		
    		
    		
    		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
    			   String dbURL = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.36.20.51)(PORT = 1521))(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
    		    String strUserID = "pbatch";
    		    String strPassword = "batchp"; 
    		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
    		    
    		    String Statement = "insert into t_xfer_control (XFER_CONTROL_ID, XFER_FILE_ID, XFER_FILE_NAME, DATE_CREATED, TIME_CREATED, FILE_SIZE, FILE_CHECKSUM, NUM_RECS, HASH_TOTAL, XFER_PATH, XFER_XMIT_STATUS, XFER_RETRY, UPDATE_TS, FILE_TYPE) values ((select Max( XFER_CONTROL_ID) + 1 from t_xfer_control where length( XFER_CONTROL_ID) = 8),711, '"+filename+"', to_date('25-11-2015', 'dd-mm-yyyy'), '08:00:13', 3857466, 0, 9, 0, '/app/vector/data_files/njtp_qatp', 'S', '0', '19-AUG-13 08.00.13.000 AM -04:00', 'tr')";
    		    c.createStatement().executeQuery(Statement);
    	    	c.createStatement().executeQuery("Commit");
    	    	c.close();
    	}
    		
		}
