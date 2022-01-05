package Filetransfer;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class changeICTXtoICRX {

	public static void main(String[] args) {
		try {
			
			String ICTXFilename = "022_008_20210407055243.ICTX";
			String ICRXFilename =(ICTXFilename.substring(4,7)+"_"+ICTXFilename.substring(0,3)+ICTXFilename.substring(7)).replace("ICTX", "ICRX");
			Scanner scanner = new Scanner(new File("D:\\TSG\\Workspace\\File creation\\ICTXTOICRX\\"+ICTXFilename));
			FileWriter fw = new FileWriter("D:\\TSG\\Workspace\\File creation\\ICTXTOICRX\\"+ICRXFilename);
			String line = null;
			String line2 = null;
			String line3 = null;
			int i=0;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				
				
				if(i!=0)
				{	
				line2 = line.substring(0,12)+"POST     "+line.substring(103,109);
				System.out.println(line2);
				fw.write(line2+"\n");
				}
				else
				{
				line3 = "ICRX"+line.substring(7,10)+line.substring(4,7)+line.substring(10);
				System.out.println(line3);
				fw.write(line3+"\n");
				}
				i++;
			}
			scanner.close();
			fw.close();
		} catch (FileNotFoundException e) {
			
			System.out.println("ICTX File not Present");
		}
		catch (IOException e1)
		{
			System.out.println("Unable to Read ICTX File");
		}	
	}

}