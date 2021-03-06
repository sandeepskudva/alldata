package NoticeCreation;

import java.awt.AWTException;
import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;


import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.Ostermiller.util.LineEnds;

public class ACKPOSTACK {
	
	


	public static String[] query(String Statement,String value) throws SQLException
	{
		
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		   String dbURL = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.36.20.51)(PORT = 1521))(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
        String strUserID = "pbatch";
        String strPassword = "batchp"; 
        Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);      
        	    
	    ResultSet rs = c.createStatement().executeQuery(Statement);
	    String[] target =new String[200];
	   
	    int x=0;
	    while(rs.next()){
	    	 target[x] =rs.getString(value);
	    	 x++;
	    	
	    }
	     
	    return target;
	}
	
	public static String getDate() {
		//Calendar cal = Calendar.getInstance();
		 Date cal =  new Date();  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    
	    return sdf.format(cal);
	}
	
	
	public static String blank(int num) {
		
		StringBuilder s = new StringBuilder();
        for(int i=0;i<num;i++)
            s.append(" ");      
	    
	    return s.toString() ;
	}
	
	
	 static void ack(String DATFILE) throws SQLException, IOException
	{
       
		
		PrintWriter writer = new PrintWriter("E:\\TSG\\Workspace\\ACK\\"+DATFILE+".ACK");
		
    	
    	String Header1 ="H";
    	String curdate= getDate();
    	String[] RecordCount1 ; 
    	RecordCount1 =query("select * from t_mailhouse_statistics where FILE_NAME ='" +DATFILE+".DAT'","MASTER_COUNT");
    	String DRecordCount = "0000000000".substring(RecordCount1[0].length()) + RecordCount1[0];
    	String HRecord = Header1+DATFILE+".DAT"+blank(80).substring((DATFILE+".DAT").length())+curdate+curdate+DRecordCount;
    	System.out.println(HRecord);
    	 writer.println(HRecord);
    	//for(int i=0;i<Integer.parseInt(RecordCount1[0]);i++ )
         for(int i=0;i<1;i++ )
    	{	
    	String Header2 ="S";
    	String zip = DATFILE+"_001.ZIP";
    	String RecordCount2[]; 
    	RecordCount2=query("select * from t_mailhouse_statistics where FILE_NAME ='" +DATFILE+".DAT'","DETAIL_COUNT");
    	String SRecordCount = "0000".substring(RecordCount2[0].length()) + RecordCount2[i];
    	String SRecord = Header2 + zip+blank(80).substring((zip).length())+SRecordCount;
    	System.out.println(SRecord);
    	writer.println(SRecord);
    	
    	for(int j=0;j <Integer.parseInt(RecordCount1[i]);j++)
    	{
    	String Header3 ="D";
    	String anumber[];
    	anumber = query("select * from t_h_mailhouse_interface where mh_filename ='" +DATFILE+".DAT'","accountnumber");
    	
    	String accountnumber = anumber[i]+blank(16).substring(anumber[i].length());
    	String pdf[]=null;
    	pdf = query("select * from t_h_mailhouse_interface where mh_filename ='" +DATFILE+".DAT'","IMG_FILE_NAME");
    	String pdfs= null;
    	
    	try
    	{
    	 pdfs = pdf[i] + blank(60).substring(pdf[i].length());	
    		
    	}
    	catch(Exception e)
    	{
    		pdfs = blank(60);
    	}
    	String acktext ="MAIL  S ";
    	String MHRef[];
    	 MHRef = query("select * from t_h_mailhouse_detail D,t_h_mailhouse_interface T where D.DETAILKEY=T.DETAILKEY and T.mh_filename = '" +DATFILE+".DAT'","CSC_LOOKUP_KEY");
    	String Comment=blank(100);
    	String DRecord = Header3 + accountnumber+pdfs+curdate+acktext+MHRef[j]+Comment;    	
    	System.out.println(DRecord); 
    	writer.println(DRecord);
    	
    	 
    	
    	}
    	 writer.close();
    	    File f = new File("E:\\TSG\\Workspace\\ACK\\"+DATFILE+".ACK");
    	    int style = LineEnds.STYLE_UNIX;
    	    LineEnds.convert(f, style);
    	}
    	
	}
	 
	 
	 static void postack(String DATFILE) throws SQLException, IOException
		{
	       
			
			PrintWriter writer = new PrintWriter("E:\\TSG\\Workspace\\ACK\\"+DATFILE+".POSTACK");
			
	    	
	    	String Header1 ="H";
	    	String curdate= getDate();
	    	String[] RecordCount1 ; 
	    	RecordCount1 =query("select * from t_mailhouse_statistics where FILE_NAME ='" +DATFILE+".DAT'","MASTER_COUNT");
	    	String DRecordCount = "0000000000".substring(RecordCount1[0].length()) + RecordCount1[0];
	    	String HRecord = Header1+DATFILE+".DAT"+blank(80).substring((DATFILE+".DAT").length())+curdate+curdate+DRecordCount;
	    	System.out.println(HRecord);
	    	 writer.println(HRecord);
	    	//for(int i=0;i<Integer.parseInt(RecordCount1[0]);i++ )
	         for(int i=0;i<1;i++ )
	    	{	
	    	String Header2 ="S";
	    	String zip = DATFILE+"_001.ZIP";
	    	String RecordCount2[]; 
	    	RecordCount2=query("select * from t_mailhouse_statistics where FILE_NAME ='" +DATFILE+".DAT'","DETAIL_COUNT");
	    	String SRecordCount = "0000".substring(RecordCount2[0].length()) + RecordCount2[i];
	    	String SRecord = Header2 + zip+blank(80).substring((zip).length())+SRecordCount;
	    	System.out.println(SRecord);
	    	//writer.println(SRecord);
	    	
	    	for(int j=0;j <Integer.parseInt(RecordCount1[i]);j++)
	    	{
	    	String Header3 ="D";
	    	String anumber[];
	    	anumber = query("select * from t_h_mailhouse_interface where mh_filename ='" +DATFILE+".DAT'","accountnumber");
	    	
	    	String accountnumber = anumber[i]+blank(16).substring(anumber[i].length());
	    	String pdf[]=null;
	    	pdf = query("select * from t_h_mailhouse_interface where mh_filename ='" +DATFILE+".DAT'","IMG_FILE_NAME");
	    	String pdfs= null;
	    	
	    	try
	    	{
	    	 pdfs = pdf[i] + blank(60).substring(pdf[i].length());	
	    		
	    	}
	    	catch(Exception e)
	    	{
	    		pdfs = blank(60);
	    	}
	    	String acktext ="MAIL  C ";
	    	String MHRef[];
	    	 MHRef = query("select * from t_h_mailhouse_detail D,t_h_mailhouse_interface T where D.DETAILKEY=T.DETAILKEY and T.mh_filename = '" +DATFILE+".DAT'","CSC_LOOKUP_KEY");
	    	String Comment=blank(100);
	    	String DRecord = Header3 + accountnumber+pdfs+curdate+acktext+MHRef[j]+Comment;    	
	    	System.out.println(DRecord); 
	    	writer.println(DRecord);
	    	
	    	 
	    	
	    	}
	    	 writer.close();
	    	    File f = new File("E:\\TSG\\Workspace\\ACK\\"+DATFILE+".POSTACK");
	    	    int style = LineEnds.STYLE_UNIX;
	    	    LineEnds.convert(f, style);
	    	}
	    	
		}
	
	
	public static void  main(String args[]) throws InterruptedException, AWTException, EncryptedDocumentException, InvalidFormatException, IOException, SQLException
	{
		String DATFILE =JOptionPane.showInputDialog("Please enter DAT Filename");
		ack(DATFILE);
		postack(DATFILE);
		
    	
	    
	}   
}
