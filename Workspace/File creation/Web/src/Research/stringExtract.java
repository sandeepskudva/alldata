	package Research;

import java.io.IOException;
import java.sql.SQLException;


import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.vector.func.Genric;

import ch.qos.logback.core.net.SyslogOutputStream;

public class stringExtract {

	public static void main(String[] args) throws SQLException, EncryptedDocumentException, InvalidFormatException, IOException, ClassNotFoundException {
//		String example1 = "Congratulations! Thank you for signing up with NJ E-ZPass®! Your Account Number is 2000197911078.\n Please print this page for your records.";
//		String answer =example1.substring(example1.indexOf("Account Number is ")+18  , example1.indexOf("."));
//		String example2 = "Congratulations, you are almost there! Your Service Request Number is 1-4057766977.\n You will be contacted print this page for your records.";
//		String answer2 =example2.substring(example2.indexOf("Request Number is ")+18  , example2.indexOf("."));
//		System.out.println(answer);
//		System.out.println(answer2);
//		
//		 Date date = new Date(System.currentTimeMillis());
//		    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
//		    String s = formatter.format(date);
//		    System.out.println(s);
		
		   Genric g = new Genric();
		   String columnName = "Count"; 
		   String accountno =  "2000189073589";
		   String date = "01/03/2017";
		   String etcid =accountno.substring(4,accountno.length()-1)+"'";
		    
		    
		    String query = "select count(lane_tx_id) as "+columnName+" from t_account_toll where tx_date =to_date('"+date+"','mm/dd/yyyy') and etc_account_id ='"+etcid;
				    
		    String resultcount = g.executefetchQuery(query,columnName);
		    System.out.println(resultcount);
		    
		    
		}

}
