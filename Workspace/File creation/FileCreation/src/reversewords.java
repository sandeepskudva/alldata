
public class reversewords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String sentence = "sihT si elpmas txet"; 
		String[] words = words =sentence.split(" ");
		String newsentence="";
		for(int i=0;i<words.length;i++)
		{
			newsentence = newsentence + " " +reverseword(words[i]);
			
		}
		System.out.println(newsentence);
	}

	
	public static String reverseword(String text)
	{
		String reverse = "";
		for (int i = text.length()-1; i >= 0; i--)
		reverse = reverse + text.charAt(i);
		
		return reverse;	
		
	}
}
	

