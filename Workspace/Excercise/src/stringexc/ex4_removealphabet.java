package stringexc;

public class ex4_removealphabet {
	
	static String  removealphabet(String input, char c)
	{
		String output="";
		
		for(int i=0;i<input.length();i++)
		{
			if(input.charAt(i)!=c)
			output += input.charAt(i);	
		}
		
		return output;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String output = removealphabet("Pressure Cooker",'c');
		System.out.println(output);

	}

}
