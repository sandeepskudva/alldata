package Filetransfer;
import java.util.*;
public class createRandom2digit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random = new Random();
		 String num1 = null;
		int num = random.nextInt(99);
		if(num+"".length() == 1)
		{
		   num1 = "0"+num;
		}
		else
		{
			num1 = ""+num;
		}
		System.out.println(num1);
	}

}
