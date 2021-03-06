package Filetransfer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class createACK {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		String DATFILENAME =  "DRJT_TEVDJB_20190524105345.DAT"; //"SJ_TEVCMA_20190318062212.DAT";//"SJ_TEVCMB_20190322024529.DAT";
		String MAILDATE =getMHDATE(DATFILENAME);
		String ZIPFILENAME =DATFILENAME.replace(".DAT","_001.ZIP");
		String RecordCount = getRecordCount(DATFILENAME);
		
		String HRecordCount = "0000000000".substring(RecordCount.length()) + RecordCount;
		String SRecordCount = "0000".substring(RecordCount.length()) + RecordCount;
		
		String HeaderRecord = "H"+addspaces(DATFILENAME,80)+MAILDATE+MAILDATE+HRecordCount;
		System.out.println(HeaderRecord);
		String SRecord ="S"+addspaces(ZIPFILENAME,80)+SRecordCount;
		System.out.println(SRecord);
		String DetailRecord = null;
		ArrayList<String> accountnumber = getDatafromMailhouse(DATFILENAME,"accountnumber");
		ArrayList<String> pdfname = getDatafromMailhouse(DATFILENAME,"IMG_FILE_NAME");	
		ArrayList<String> DETAILKEY = getDatafromMailhouse(DATFILENAME,"DETAILKEY");
		
		
		for(int i=0;i < Integer.parseInt(RecordCount);i++)
		{
		 DetailRecord= "D"+ addspaces(accountnumber.get(i),16)+addspaces(pdfname.get(i),60)+MAILDATE+addspaces("MAIL",6)+"S "+DETAILKEY.get(i)+"      ";
		 System.out.println(DetailRecord);
		}
		
		
		
	}
	
	public static String addspaces(String s,int totallength)
	{
		int length = s.length();
		int numberofspaces = totallength- length;
		for(int i=0;i<numberofspaces;i++)
		{
			s = s+" ";
		}
		
	 return s;	
	}
	
	public static String getMHDATE(String DATFILENAME) throws SQLException
	{
		
		
		
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		    String dbURL = "jdbc:oracle:thin:@10.36.20.51:1521/njrbtst";
		    String strUserID = "pbatch";
		    String strPassword = "pbatch443"; 
		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
		    
		    String Statement = "select to_char(CURDATE,'YYYYMMDD') as \"CURDATE\"  from t_h_mailhouse_interface where mh_filename = '"+DATFILENAME+"' and rownum=1"; 
		    
		    ResultSet rs = c.createStatement().executeQuery(Statement);
		    String xfer = null;
		   
		    while(rs.next()){
		    	xfer =rs.getString("CURDATE");  
		    }
		   
		    if(xfer==null)
		    {	
		    	xfer = "NULL";		  

		    }
		  	    
			c.close();
			return xfer;
		}
	
	public static String getRecordCount(String DATFILENAME) throws SQLException
	{
		
		
		
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		    String dbURL = "jdbc:oracle:thin:@10.36.20.51:1521/njrbtst";
		    String strUserID = "pbatch";
		    String strPassword = "pbatch443"; 
		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
		    
		    String Statement = "select count(*) as \"acount\" from t_h_mailhouse_interface where mh_filename = '"+DATFILENAME+"'"; 
		    
		    
		    ResultSet rs = c.createStatement().executeQuery(Statement);
		    String xfer = null;
		   
		    while(rs.next()){
		    	xfer =rs.getString("acount");  
		    }
		   
		    if(xfer==null)
		    {	
		    	xfer = "NULL";		  

		    }
		  	    
			c.close();
			return xfer;
		}
	
	public static ArrayList<String> getDatafromMailhouse(String DATFILENAME,String columnToFetch) throws SQLException
	{
		
		
		
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		    String dbURL = "jdbc:oracle:thin:@10.36.20.51:1521/njrbtst";
		    String strUserID = "pbatch";
		    String strPassword = "pbatch443"; 
		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
		    
		    String Statement = "select "+columnToFetch+" from t_h_mailhouse_interface where mh_filename = '"+DATFILENAME+"'"; 
		    String result = null;
		    ResultSet rs = c.createStatement().executeQuery(Statement);
		    
		    ArrayList<String> acc = new ArrayList<String>();
		    while(rs.next()){
		    	result =rs.getString(columnToFetch);  
		    	acc.add(result);
		    }
		    
		  	    
			c.close();
			return acc;
		}
	
	

}
