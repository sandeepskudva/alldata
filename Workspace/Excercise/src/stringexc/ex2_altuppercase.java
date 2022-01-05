package stringexc;

public class ex2_altuppercase {
	
	public static void secondoutput(String input)
	{
		input = input.toLowerCase();
		StringBuilder output = new StringBuilder(input);
		input = input.toUpperCase();
		
		
		
		for(int i=0;i<input.length();i=i+2)
		{
			
			output.setCharAt(i,input.charAt(i));
		}
		
		System.out.println(output);
	}
	
	public static void main(String[] args) {
        
        String str= "APPLEapple";
        String newstr = null;
        int count = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<count;i++)
        {
               char ch =str.charAt(i);
               if(i%2!=0)
               {
                     char upch = Character.toLowerCase(ch);
                     sb.append(upch);
               }
               
               if((i==0)||(i%2==0))
               {
                     char upch = Character.toUpperCase(ch);
                     sb.append(upch);
               }
               newstr = sb.toString();
        }      
 System.out.println(newstr);

  secondoutput("APPLEapple");
 }



}
