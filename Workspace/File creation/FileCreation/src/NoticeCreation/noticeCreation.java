package NoticeCreation;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class noticeCreation {

	
	
	
	public static void imageset(String lane_tx_id, String plate_number) throws SQLException
	{
		
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		   String dbURL = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.36.20.51)(PORT = 1521))(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
        String strUserID = "pbatch";
        String strPassword = "batchp"; 
        Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
        
        String Statement0 = "update t_viol_tx set TX_TIMESTAMP =systimestamp -10, POSTED_DATE = trunc(systimestamp) -5 , ENTRY_TIMESTAMP = systimestamp -10 , TX_DATE =trunc(systimestamp)  -10 where lane_tx_id in ("+lane_tx_id+")";
        String Statement1 = "update t_viol_tx set viol_tx_status=4 where  lane_tx_id in ("+lane_tx_id+")"; 
        String Statement2 = "Update T_VIOL_TX set VIOL_TX_STATUS = 5, EVENT_TYPE = 3, PLATE_STATE = 'NY', PLATE_COUNTRY= 'USA', PLATE_NUMBER = '"+plate_number+"' WHERE LANE_TX_ID in ("+lane_tx_id+")";
        String Statement3 = "delete from T_VIOL_LICA_QUEUE WHERE lane_tx_id in ("+lane_tx_id+")";
        String Statement4= "update t_viol_tx set viol_tx_status=6,event_type=4 WHERE lane_tx_id in ("+lane_tx_id+")";
        String Statement5= "update t_viol_tx set viol_tx_status=7, event_type=5  WHERE lane_tx_id in ("+lane_tx_id+")";
        String Statement6= "update t_viol_tx set viol_tx_status=131,event_type=6  WHERE lane_tx_id in ("+lane_tx_id+")";
        c.createStatement().executeQuery(Statement0);
        c.createStatement().executeQuery("Commit");
        c.createStatement().executeQuery(Statement1);
        c.createStatement().executeQuery("Commit");
        c.createStatement().executeQuery(Statement2);
        c.createStatement().executeQuery("Commit");
        c.createStatement().executeQuery(Statement3);
        c.createStatement().executeQuery("Commit");
        c.createStatement().executeQuery(Statement4);
        c.createStatement().executeQuery("Commit");
        c.createStatement().executeQuery(Statement5);
        c.createStatement().executeQuery("Commit");
        c.createStatement().executeQuery(Statement6);
        c.createStatement().executeQuery("Commit");
        
        String Statement7 = "select LANE_TX_ID from t_viol_image_tx h where zip_file_name like 'D%' and rownum = 1";	    
	    ResultSet rs = c.createStatement().executeQuery(Statement7);
	    String target=null;
	    while(rs.next()){
	    	 target =rs.getString("LANE_TX_ID");   
	    }
	     
	    String Statement8 = "update t_viol_image_tx set lane_tx_id = "+lane_tx_id+" where lane_tx_id ="+target;
	    c.createStatement().executeQuery(Statement8);
        c.createStatement().executeQuery("Commit");
   	    c.close();
	}
	
	
	public static void  main(String args[]) throws InterruptedException, AWTException, EncryptedDocumentException, InvalidFormatException, IOException, SQLException
	{
		
		
		String lane_tx_id =JOptionPane.showInputDialog("Please enter LANE_TX_ID");
		System.out.println("Lane transaction ID is "+lane_tx_id);
		
		int n = JOptionPane.showConfirmDialog(null,"Enetered LANE_TX_ID is "+lane_tx_id+" Do you want to continue with notice creation ?","Please Confirm",JOptionPane.YES_NO_OPTION);
    	
    	if(n==JOptionPane.NO_OPTION){
    	System.exit(0);
    	}
    	
    	String plate_number =JOptionPane.showInputDialog("Please enter plate_number");
    	System.out.println("Plate number is "+plate_number);
    	imageset(lane_tx_id,plate_number);
    	
	    
	}   
}
