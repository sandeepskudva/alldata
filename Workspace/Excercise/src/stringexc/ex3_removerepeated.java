package stringexc;

public class ex3_removerepeated {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String input= "Band baaja baaraat";
		String output= "";
        int j=0;
		for(int i=0;i<input.length()-1;i++)
		{
			j=i+1;
			if(input.charAt(i)!=input.charAt(j)){
				output= output + input.charAt(i);
				
			}
			else
			{
				i=i+1;
			}
		
		}
		output= output + input.charAt(input.length()-1);
		System.out.println(output);
	}

	}


