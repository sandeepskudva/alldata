
public class checkProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int num = 112;
		int countup=0;
		int countdown=0;
		int nearest=0;
		countup =nearestup(num);
		countdown =nearestdown(num);
		
		if(countup<countdown)
		{
			nearest = num + countup;
		}
		else
		{
			nearest = num - countdown;
		}
		System.out.println(nearest);
	}

	
	public static boolean ispalindrome(String text)
	{
		String reverse = "";
		for (int i = text.length()-1; i >= 0; i--)
		reverse = reverse + text.charAt(i);
		if(text.equals(reverse))
		return true;
		else		
		return false;	
		
	}
	
	public static int nearestup(int num)
	{
		int counter =0;
	    boolean palindrome = false;
	    while(palindrome==false)
	    {
	     if(ispalindrome(num+""))	
	    	 palindrome =true;
	     num = num+1;
	     counter = counter+1;
	    }
		
		return counter-1;	
		
	}
	public static int nearestdown(int num)
	{
		int counter =0;
	    boolean palindrome = false;
	    while(palindrome==false)
	    {
	     if(ispalindrome(num+""))	
	    	 palindrome =true;
	     num = num-1;
	     counter = counter+1;
	    }
		
		return counter-1;	
		
	}
}
