package Safeform;
import java.lang.Math;

public class clockAngle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int dicemax = 6;
		
		int value =  (int) (Math.random() * dicemax +1);
		System.out.println(value);
		
		if(value >6)
		{
			System.out.println("WALAAAh");
		}
		
		
		
		

	}

}
