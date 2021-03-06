package NoticeCreation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;



public class createVTR {

	public static void main(String[] args) throws ParseException, IOException, SQLException {
		// TODO Auto-generated method stub
		
		createNJTPVtrFile("20180515","003344","90126789","10"); 
		//createDRJVtrFile("20180515","003344","90126789","10"); 

	}
public static void createNJTPVtrFile(String Filedate,String HHMMSS,String xfer,String RecordCount) throws IOException, ParseException, SQLException
{

	 String YYYY = Filedate.substring(0, 4);
	 String MM = Filedate.substring(4, 6);
	 String DD = Filedate.substring(6, 8);
	 String header = "H012.0";
	
	 String headertext = null; 
    String Trailertext= null;
    String Filename = null;
    

    String xfer10 = "00" + xfer;
    String AgencyID = "003";	     
    
   
    String ARecordCount = "00000000".substring(RecordCount.length()) + RecordCount;
    String Trailer = "T";
    String checksum = "e2ad";
    String Fileext =".tr";
    Filename = "v" + YYYY + MM + DD + HHMMSS + AgencyID + Fileext;  
    
    System.out.println(Filename);
    
    String filepath ="D:\\TSG\\Workspace\\App\\WebApp-01\\Files\\TestFile\\"+Filename;
    File file = new File(filepath);
    file.delete();
    BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));
    headertext = header+ xfer10 + AgencyID + YYYY + MM+ DD + HHMMSS + ARecordCount;
    bw.write(headertext+"\n");
    System.out.println(headertext);
    
    String DetailRecord0="D20330000000";
    int extref = randomnum();
    //System.out.println(extref);
    String entryPlaza = getPlazaLane();
    System.out.println(entryPlaza.length());
	String exitPlaza = getPlazaLane();
	System.out.println(exitPlaza.length());
    String DetailRecord1="1"+entryPlaza;
    String txdate = "20180601";
    String DetailRecord2="0010084781900053581"+exitPlaza;
    String DetailRecord3 ="0002290178000.00000.00002.00002.00002.00030301007201000002031000********C***007***********03S0335R0000000000000000800000000000000010000000128088080";
    String DetailRecord=null;
    
    int count = Integer.parseInt(RecordCount);
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	String dateInString = txdate + "000000";
	Date date = sdf.parse(dateInString);
	
	 int minsToAdd = 30; 
	Calendar calendar = Calendar.getInstance();
   calendar.setTime(date);
    for(int i=0;i<count;i++)
	   {	
   	 calendar.add(Calendar.MINUTE, minsToAdd);
        date = calendar.getTime();
        
        String Time1 = sdf.format(date);
        extref++;
   	 DetailRecord = DetailRecord0 +extref +DetailRecord1 + Time1 +DetailRecord2 +Time1 + DetailRecord3;
   	 System.out.println(DetailRecord);
   	 bw.write(DetailRecord+"\n");
	   }
	   
    
    
    
     Trailertext = Trailer + ARecordCount + checksum;
   

     System.out.println(Trailertext);
     bw.write(Trailertext+"\n");
     System.out.println("File created");
	 bw.flush();
	 bw.close();

}




public static void createDRJVtrFile(String Filedate,String HHMMSS,String xfer,String RecordCount) throws IOException, ParseException
{

	 String YYYY = Filedate.substring(0, 4);
	 String MM = Filedate.substring(4, 6);
	 String DD = Filedate.substring(6, 8);
	 String header = "H";
	
	 String headertext = null; 
    String Trailertext= null;
    String Filename = null;
    

    
    String AgencyID = "020";	     
    
   
    String ARecordCount = "000000".substring(RecordCount.length()) + RecordCount;
    String Trailer = "E";
    String serial="04";
    String Fileext =".VIO";
    Filename = YYYY + MM + DD + serial + Fileext;    
    System.out.println(Filename);
    
    String filepath ="D:\\TSG\\Workspace\\App\\WebApp-01\\Files\\TestFile\\"+Filename;
    File file = new File(filepath);
    file.delete();
    BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));
     
   
	headertext = header+ ARecordCount + YYYY + MM+ DD + HHMMSS + serial ;
    bw.write(headertext+"\n");
    System.out.println(headertext);
    
    String DetailRecord0="[B";
    int extref = randomnum();
    System.out.println(extref);
    String DetailRecord1="*******10**************000000010000002020200000000**50Y00000000000000000000000000000002+050";
    String txdate = "20180601";
    String DetailRecord2="NHL01WR00010*****00000000000";
    String DetailRecord3 ="000]";
    String DetailRecord=null;
    
    int count = Integer.parseInt(RecordCount);
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	String dateInString = txdate + "000000";
	Date date = sdf.parse(dateInString);
	
	 int minsToAdd = 30; 
	Calendar calendar = Calendar.getInstance();
   calendar.setTime(date);
    for(int i=0;i<count;i++)
	   {	
   	 calendar.add(Calendar.MINUTE, minsToAdd);
        date = calendar.getTime();
        
        String Time1 = sdf.format(date);
        extref++;
   	 DetailRecord = DetailRecord0 + Time1+ DetailRecord1  + Time1 +DetailRecord2  + extref + DetailRecord3;
   	 System.out.println(DetailRecord);
   	 bw.write(DetailRecord+"\n");
	   }
	    
    
     Trailertext = Trailer;  

     System.out.println(Trailertext);
     bw.write(Trailertext+"\n");
     System.out.println("File created");
	 bw.flush();
	 bw.close();

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

public static int randomnum() {
	
	    Random r = new Random( System.currentTimeMillis() );
	    return ((1 + r.nextInt(8)) * 10000 + r.nextInt(10000));
}
}
