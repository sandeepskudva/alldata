package Filetransfer;
import java.util.regex.*;

public class countofPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String sentence = "He can do it. He lit 1 candle, but can he light 5 candles";
		String[] words = sentence.split(" ");
		
		int count=0;
		
		//Map<String, Integer> count = new HashMap<String, Integer>();
		for(int i=0;i<=words.length-1; i++) {
			if(words[i].contains("can")) {
				count++;
			}
			
		}
		
		System.out.println(count); 

	}

}
