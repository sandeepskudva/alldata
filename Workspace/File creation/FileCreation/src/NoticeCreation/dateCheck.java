package NoticeCreation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dateCheck {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateInString = "20180601000000";
		Date date = sdf.parse(dateInString);
		
		
        int minsToAdd = 30; //or from input
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int cnt = 50;
        for (int i=0;i<cnt;i++)
        {
        calendar.add(Calendar.MINUTE, minsToAdd);
        date = calendar.getTime();
        
        String time1 = sdf.format(date);
        System.out.println(sdf.format(date));
        }

	}

}
