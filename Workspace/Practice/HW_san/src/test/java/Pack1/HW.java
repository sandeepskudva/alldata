package Pack1;

import java.util.ArrayList;
import java.util.Iterator;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import jdk.internal.util.xml.impl.Input;





public class HW {

	@Test
	public void testHellowWorld() {
		
		System.setProperty("webdriver.chrome.driver","D:\\TSG\\Jars\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		driver.close();
	}
	
	@Test
	public void facto() {
		
		int num=5;
		int fact=1;
		
		for(int i=1;i <=num;i++) {
			
			fact = fact * i;
		}
		
		System.out.println("Factorial of "+num+" is "+fact);
		
		
	}
	
	@Test
	public static void fibonacci()
	{
		int n = 10;
		int arr[] = new int[n];
		arr[0] = 0;
		arr[1] = 1;
 		System.out.println("Fibonacci Numbers");
		for(int i=2;i<=(n-1);i++)
		{
			arr[i] = arr[i-1] + arr[i-2];
		}

		for(int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i] + " ");
		}	
	}
	@Test
	public static void amstrong()
	{
		int n =153;
		int temp =0;
		int sum=0;
		while(n>0)
		{
			temp = n%10;
			sum = sum + (temp*temp*temp);
			n = n/10;
		}
		
		if(n==sum)
		{
			System.out.println("Armstrong number");
		}
		else {
			System.out.println("Armstrong number");
		}
					
	}
	
	@Test
	public static void reverse()
	{
		int orignal =15315;
		int n= orignal;
		int temp =0;
		int sum=0;
		int start = (int) Math.pow(10,((n+"").length())-1);
		System.out.println(start);
		
		while(n>0)
		{
			temp = n%10;
			sum = start * temp + sum;
			start= start/10;
			n = n/10;			
		}
		
		
			System.out.println("Reverse of number "+orignal+" is "+sum);
				
	}
	@Test
	public static void vowelcons()
	{
		String input = "limca1aaa";
		input = input.toLowerCase();
		int vcount=0;
		int concount=0;
		
		for(int i=0;i<input.length();i++)
		{
			if(isvowel(input.charAt(i))=='v')
				vcount++;
			else if(isvowel(input.charAt(i))=='c')
				concount++;
		}
		System.out.println("vowel count is "+vcount+" and consonent count is "+concount);		
	}
	
public static Character isvowel(Character c) {
		
	
	if(c=='a' || c=='e' || c == 'i' || c=='e' || c=='u')
	{
		return 'v';
	}
	else if(c>='a' && c<='z')
	{
		return 'c';
	}
	else
	{
		return 'n';
	}
	
		
	}

	
	@Test
	public static void primelist()
	{
		int number =1000;
		ArrayList<Integer> primlist = new ArrayList<Integer>();
		
		for(int i=1; i<=number;i++)
		{
			if(isprime(i))
				primlist.add(i);
				
		}
		
		System.out.println(primlist);
				
				
	}
public static boolean isprime(int n) {
		
		
		for(int i =2;i < n;i++)
		{
			if(n%i==0)
			return false;	
			
		}
		
		return true;
	}
	
	

	@Test	
public void fibonaci() {
		
		int num1=0;
		int num2=1;
		int num3=0;
		
		int numbers = 10;
		
		if(numbers==1)
		{
			System.out.println(num1);
		}
		else if(numbers==2)
		{
			System.out.print(num1+" "+num2);
		}
		else {
			System.out.print(num1+" "+num2);
			for(int i=3;i <=numbers;i++) {
				
				num3 = num1 + num2;
				num1 = num2;
				num2=num3;
				System.out.print(" "+num3);
			}
			
			System.out.println();
		}	
		
		
	}
	
	

}
