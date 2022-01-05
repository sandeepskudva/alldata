package Filetransfer;



import com.github.javafaker.Faker;



public class autoPopulate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//DataFactory df = new DataFactory();
		
		
		
		
		
		
		System.out.println("My Firstname is "+ getfirstname());
		
		System.out.println("My lastname is "+ getLastname());
		System.out.println("My address is "+ getAddress());

	}
	public static String getfirstname()
	{
		Faker f = new Faker();
		return f.name().firstName();
		
		
	}
	public static String getLastname()
	{
		Faker f = new Faker();
		return f.name().lastName();
		
	}
	public static String getAddress()
	{
		Faker f = new Faker();
		return f.address().streetAddress(false);
		
	}
}
