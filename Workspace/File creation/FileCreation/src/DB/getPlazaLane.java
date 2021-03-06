package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class getPlazaLane {

	public static void main(String[] args) throws SQLException {
		
		
		//String entryPlaza = getPlazaLane();
	//	String exitPlaza = getPlazaLane();
		
	//	System.out.println("Entry Plaza is "+entryPlaza);
		//System.out.println("Exit Plaza is "+exitPlaza);
		
		getsystime();
		
		
		
	}
	
	public static String getPlazaLane() throws SQLException
	{
        Random rand = new Random();
		
		String[] plaza = {"001","002","003","004","005","006","06A","007","07A","008","08A","009","010","011","012","013","13A","014","14A","14B","14C","15E","15W","16E","16W","17N","18E","18W","17S","06B","15X"};
		int index = rand.nextInt(plaza.length);		
		String selectedplaza = plaza[index];				
		String lane = getlane(selectedplaza,"13");
		String plazaLane = selectedplaza+lane;	
		
		return plazaLane;
		
	}

	
	
	public static String getlane(String plaza, String agencyid) throws SQLException
	{
		
		
		
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
			   String dbURL = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.36.20.51)(PORT = 1521))(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
		    String strUserID = "pbatch";
		    String strPassword = "batchp"; 
		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
		    
		    String Statement1 = "SELECT extern_lane_id FROM  ( SELECT extern_lane_id FROM t_lane where plaza_id in (select plaza_id from t_plaza where agency_id ="+agencyid+" and extern_plaza_id = '"+plaza+"')"; 
		    String Statement2 = "ORDER BY DBMS_RANDOM.VALUE )  WHERE ROWNUM = 1 ";
		   
		    ResultSet rs = c.createStatement().executeQuery(Statement1+Statement2);
		    String lane = null;
		    while(rs.next()){
		    	lane =rs.getString("EXTERN_LANE_ID");  
		    }
		    
		    if(lane==null)
		    {	
		    	lane = "NULL";

		    }
		  
		    
		   
		    	 String Alane = "    ".substring(lane.length()) + lane;
		   
			c.close();
		  
			return Alane;
		}
	
	public static String getsystime()
	{
		String time=null;
		
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");  
		   LocalDateTime now = LocalDateTime.now();  
		   System.out.println(dtf.format(now));  
		
		return time;
		
	}

}
