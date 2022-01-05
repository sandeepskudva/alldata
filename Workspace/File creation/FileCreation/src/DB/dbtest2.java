package DB;

import java.io.IOException;
import  java.sql.Connection;		
		
import  java.sql.ResultSet;		
import  java.sql.DriverManager;		
import  java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class  dbtest2 {				
    	public static void  main(String[] args) throws  ClassNotFoundException, SQLException,ParseException, NumberFormatException, InterruptedException, IOException {													
    		
    	 imageIns("111408976295");
    		
    		
		}
    	
    	
    	
    	public static String imageIns(String lanetxid) throws InterruptedException,  IOException, NumberFormatException, SQLException, ParseException {

    	  	
    		
    		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
    		String dbURL = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.36.20.51)(PORT = 1521))(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
    	    String strUserID = "pbatch";
    	    String strPassword = "batchp"; 
    	    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
    	    String Statement = "select tx_timestamp,tx_date,lane_id,tx_seq_number,plate_state,plate_number,plaza_id,dmv_plate_type from t_viol_tx where lane_tx_id = '"+lanetxid+"'";
    	    ResultSet rs = c.createStatement().executeQuery(Statement);
    	    
    	    String Statement1 = "select COUNT(LANE_TX_ID) from t_viol_image_tx where lane_tx_id  = '"+lanetxid+"'";
    	    ResultSet rs1 = c.createStatement().executeQuery(Statement1);
    	    
    	    
    	    int count =0;
    	    while(rs1.next()){
    	    
    	    count = rs1.getInt("COUNT(LANE_TX_ID)");
    	   
    	    }
    		
    	    if(count==0)
    	    {	
    	    
    		Time tx_timestamp =null;
    		Date tx_date =null;
    		String laneid =null;
    		String tx_seq_number=null;
    		String plate_state=null;
    		String plate_number = null;
    		String plaza_id =null;
    		String dmv_plate_type=null;		
    		
    	    
    		while(rs.next()){
    		
    			tx_timestamp =rs.getTime("TX_TIMESTAMP");
    			tx_date =rs.getDate("TX_DATE");
    			laneid =rs.getString("LANE_ID");
    			tx_seq_number=rs.getString("TX_SEQ_NUMBER");
    			plate_state=rs.getString("PLATE_STATE");
    			plate_number = rs.getString("PLATE_NUMBER");
    			plaza_id =rs.getString("PLAZA_ID");
    			dmv_plate_type=rs.getString("DMV_PLATE_TYPE");	
    	    	 
    	    	
    	    }   
    		
    		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    		df.setTimeZone(TimeZone.getTimeZone("EST"));
    		String date = df.format(tx_timestamp);
      		String format = date.toString();
     		String txdate = tx_date.toString();
     		
     		SimpleDateFormat df1 = new SimpleDateFormat("MM/dd/YYYY");
     	    String trandate = df1.format(tx_date);
     		
     		SimpleDateFormat df2 = new SimpleDateFormat("dd-MMM-YYYY hh.mm.SS a");
     		df2.setTimeZone(TimeZone.getTimeZone("EST"));
     		String datetime = df2.format(tx_timestamp);
    	    
    	    String imagefile = "01318E_18eE"+format+"SV";
    	    String zipfile = "NJTA_"+format+".ZIP";
    	    
    	    System.out.println("LANE_TX_ID :"+lanetxid);
    	    System.out.println("Image filename :"+imagefile);
    	    System.out.println("tx_date  :"+txdate);
    	    System.out.println("LANE_ID :"+laneid);
    	    System.out.println("TX_SEQ_NUMBER :"+tx_seq_number);
    	    System.out.println("PLATE_STATE :"+plate_state);
    	    System.out.println("PLATE_NUMBER :"+plate_number);
    	    System.out.println("PLAZA_ID :"+plaza_id);
    	    System.out.println("DMV_PLATE_TYPE :"+dmv_plate_type);
    	    
    	    System.out.println(datetime);
    	    System.out.println(trandate);
    	    System.out.println(zipfile);
    	    
    	   String insertscript1 ="insert into t_viol_image_tx(LANE_TX_ID,in_file_name,out_file_name,lane_image_cnt,proc_image_cnt,receive_date, process_date,lane_id,tx_seq_number,process_desc,ocr_conf,ocr_plate_state,ocr_plate_number,ocr_plate_type,ocr_plate_type_conf,ocr_status,tx_date,zip_file_name,tx_timestamp,update_ts,axle_count,plaza_id,img_file_index,IMP_TYPE) values";
           String insertscript2="('"+lanetxid+"','"+imagefile+"','"+imagefile+"','1','1',to_date('"+trandate+"','mm/dd/yyyy'),to_date('"+trandate+"','mm/dd/yyyy'),'"+laneid+"','"+tx_seq_number+"','IGR','850','"+plate_state+"','"+plate_number+"','"+dmv_plate_type+"','850','30',to_date('"+trandate+"','mm/dd/yyyy'),'"+zipfile+"','"+datetime+"','"+datetime+"','2','"+plaza_id+"','1','2')" ; 
           
           String statement2 = insertscript1+insertscript2;
           System.out.println(statement2);
           c.createStatement().executeQuery(statement2);
   	        c.createStatement().executeQuery("Commit");	  
    	    }
    	   
    	    
    	  

    	    c.close();
    		return "";   
    	} 
}