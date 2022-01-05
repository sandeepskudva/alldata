package NoticeCreation;

public class createDMVFile {
	
	public static String setLength(String Orgstr,int newlength)
	{
		String newWord = Orgstr;
	    for(int count = Orgstr.length(); count < newlength; count++) {
	        newWord = newWord+" ";
	    }
	    return newWord;
		
	}
	
	public static String createDMVLine(int i,String platestate,String platenumber,String lanetxid,String lastname,String middlename,String firstname,String Address1,String Address2,String City,String State,String Zip)   {


		
        String REG="LFM";
		
	
		
		String CONST1 = "2017052679838432000163201714120161412017141201729000 14822   00VAN     99945   201703310000000000000000021";
	    String CONST2 = "00000000          00000000          000000000000   D";	    
	    String zip2="";
	    String VIN = "FER0012";
	    String License = "NJ1234";
	    String dmvtext =null;
	    String name =null;
		
		if(REG.equals("LFM"))
		{
			name = lastname + " " + firstname + " " + middlename;
				
		}
		else
		{   name = firstname + " " + middlename+ " " +lastname;
			
		}
	    
		dmvtext = setLength(platestate,2)+setLength(platenumber,10)+" 0"+setLength(platestate,2)+setLength(platenumber,8)+" 0"+setLength(lanetxid,12)+"NJT"+CONST1+setLength(lastname,20)+setLength(name,35)+setLength(Address1,35)+setLength(Address2,35)+setLength(City,30)+setLength(State,2)+setLength(Zip,5)+setLength(zip2,4)+setLength(VIN,30)+setLength(License,30)+CONST2;
	    
	    
	    
	    System.out.println(dmvtext);
	    System.out.println("Round "+i+" Completed");
    	
    
	
	
	
	return dmvtext.toUpperCase();   
} 

	public static void main(String[] args) {
		
        int i =1;
        String PlateState ="NJ";
        String platenumber="RAN01DO";
        String lanetxid="112242220414";
        String lastname="RANDY";
        String middlename = "";
        String firstname="KIJNA";
        String Address1="290 BOMER STREET";
        String Address2="";
        String City="GALLOWAY";
        String State="NJ";
        String Zip="08205";        
        
        
		createDMVLine(1,PlateState,platenumber,lanetxid,lastname,middlename,firstname,Address1,Address2,City,State,Zip);

	}

}
