package macroTR;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.JFrame;

public class TimerFrame {
  

    public static void main(String[] args) {
    	Date curDate = new Date();

    	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    	String DateToStr = format.format(curDate);
    	format = new SimpleDateFormat("hhmmss");    	
    	DateToStr = format.format(curDate);
        System.out.println(DateToStr);

    }
}

