package AET;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;

public class createDRJAET {

	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
	String[] File_Type_Val = { "VIOLATION", "ETC TRANSACTION"};
	    
	String File_Type = (String) JOptionPane.showInputDialog(null, "Please Select the File Type...",
	"File Type Selection", JOptionPane.QUESTION_MESSAGE, null,File_Type_Val,File_Type_Val[0]);
	   	
	String File_Date = (String) JOptionPane.showInputDialog("Please Enter File Date in YYYYMMDD");
	    
	String File_Time = (String) JOptionPane.showInputDialog("Please Enter File Time in HHMMSS");
	    
	String RecordCount = (String) JOptionPane.showInputDialog("Please Enter No of Records");
	    
	if(File_Type.equals("VIOLATION"))
	  {
	    	createVTRDRJAET(File_Date,File_Time,RecordCount); 
	    	
			    }
	    else 
	    {
	    	createETCDRJAET(File_Date,File_Time,RecordCount);
	    	    }

	}

	// code to create ETCTransaction  File.
public static void createETCDRJAET(String Filedate,String HHMMSS,String RecordCount) throws IOException, ParseException
{

   String Device_no = (String) JOptionPane.showInputDialog("Please Enter the Device No");
	
    String txdate = (String) JOptionPane.showInputDialog("Please Transaction Date in YYYYYMMDD");
    
    String Plaza_ID="OSF";
	String YYYY = Filedate.substring(0, 4);
	String MM = Filedate.substring(4, 6);
	String DD = Filedate.substring(6, 8);
	String header = "H";
	
	String headertext = null; 
    String Trailertext= null;
    String Filename = null;     
   
    String Trailer = "E";
    int extref = randomnum();
    String Fileext =".TRX";
    Filename =YYYY+MM+DD+"33"+ Fileext;  
    System.out.println(Filename);
    
    String filepath ="E:\\TSG\\Workspace\\File creation\\FileCreation\\AET\\"+Filename;
    java.io.File file = new java.io.File(filepath);
    file.delete(); 
    BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));
    
    int rec = Integer.parseInt(RecordCount);
    String ARecordCount = "000000".substring(RecordCount.length()) + rec;
    headertext = header+ ARecordCount + YYYY + MM+ DD + HHMMSS+"33";
    bw.write(headertext+"\n");
    System.out.println(headertext);
    
	String DetailRecord1="[B";
    String DetailRecord2="*************10065";      
    String DetailRecord3="0000000500000";
    String DetailRecord4="00000000**10000000000000000000000000000000000+050";
    String DetailRecord5="0000000000";
   
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
       String Vehicle_clas=getclass();
       DetailRecord = DetailRecord1 +txdate+DetailRecord2+Device_no+DetailRecord3+Vehicle_clas+Vehicle_clas+Vehicle_clas+DetailRecord4+Time1+Plaza_ID+getLane()+"R"+getamount(Vehicle_clas)+DetailRecord5+extref+"]";
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
// code to create Violation File.
public static void createVTRDRJAET(String Filedate,String HHMMSS,String RecordCount) throws IOException, ParseException
{

    String Device_no = (String) JOptionPane.showInputDialog("Please Enter the Device No");
    
    String txdate = (String) JOptionPane.showInputDialog("Please Transaction Date in YYYYYMMDD");
    
    String Plaza_ID="OSF";
    String YYYY = Filedate.substring(0, 4);
    String MM = Filedate.substring(4, 6);
    String DD = Filedate.substring(6, 8);
    String header = "H";

    String headertext = null; 
    String Trailertext= null;
    String Filename = null;     

    String Trailer = "E";
    int extref = randomnum();
    String Fileext =".PBP";
    Filename =YYYY+MM+DD+"33"+ Fileext;  
    System.out.println(Filename);

    String filepath ="E:\\TSG\\Workspace\\File creation\\FileCreation\\AET\\"+Filename;
    java.io.File file = new java.io.File(filepath);
    file.delete(); 
    BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));

    int rec = Integer.parseInt(RecordCount);
    String ARecordCount = "000000".substring(RecordCount.length()) + rec; 
    headertext = header+ ARecordCount + YYYY + MM+ DD + HHMMSS+"33";
    bw.write(headertext+"\n");
    System.out.println(headertext);

	String DetailRecord1="[B";
    String DetailRecord2="*************10065";      
    String DetailRecord3="0000000500000";
    String DetailRecord4="00000000**50000000000000000000000000000000000+050";
    String DetailRecord5="0000000000";
	
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
      String Vehicle_clas=getclass();
      DetailRecord = DetailRecord1 +txdate+DetailRecord2+Device_no+DetailRecord3+Vehicle_clas+Vehicle_clas+Vehicle_clas+DetailRecord4+Time1+Plaza_ID+getLane()+"R"+getamount(Vehicle_clas)+DetailRecord5+extref+"100]";
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

public static String getLane() 
{
    Random rand = new Random();
     
    String Lane_ID=null;
	String[] Lane_ID_Val = { "01W", "02W", "03W","04W","11W"};
    int index = rand.nextInt(Lane_ID_Val.length);         
    String selectedplaza = Lane_ID_Val[index];                          
    Lane_ID = selectedplaza;     
    //System.out.println("Lane ID is "+Lane_ID);
    return Lane_ID;
       
}


public static String getamount(String Class) 
{
    String Amount=null;
     
   	switch(Class)
	{
	case "01" : 
	
    Amount = "0012500260";                         
    break;	
   
	case "02" : 
		
    Amount = "0070000835" ;                        
    break;	
	    
    case "03" : 
		
    Amount = "0127501425" ;                        
    break;	
	
    case "04" : 
 		
    Amount = "0170001900"  ;                      
    break;	
     
   case "05" : 
		
    Amount = "0212502375";                       
    break;	
	
	 case "06" : 
		
    Amount = "0255002850";                       
    break;	
	
	 case "07" : 
		
    Amount = "0297503325" ;                      
    break;	
	}
 	//System.out.println("amount is "+Amount);
	 	return Amount;

}


public static String getclass() 
{
    Random rand = new Random();
     
    String Class_ID=null;
	String[] Class_ID_Val = {"01","02","03","04","05","06","07"};                 //{"01","02","03","04","05","06","07"};
    int index = rand.nextInt(Class_ID_Val.length);         
    String selectedclass = Class_ID_Val[index];                          
    Class_ID = selectedclass;     
    //System.out.println("Class_IDis "+Class_ID);
    return Class_ID;
       
}
public static int randomnum() {
	
    Random r = new Random( System.currentTimeMillis() );
    return ((1 + r.nextInt(8)) * 100000 + r.nextInt(100000));
}
}