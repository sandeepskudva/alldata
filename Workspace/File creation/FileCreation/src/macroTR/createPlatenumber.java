package macroTR;

import java.util.Random;

public class createPlatenumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String platenumber;
		int RandomNum =  1000 +(int)(Math.random() * 9999); 
		platenumber = randomCharacter()+(RandomNum+"")+randomCharacter();
		System.out.println(platenumber);
		

	}
	
	public static char randomCharacter() {
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'a');
		return c;
	}

}
