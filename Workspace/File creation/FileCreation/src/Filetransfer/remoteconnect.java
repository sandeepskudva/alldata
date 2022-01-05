package Filetransfer;



import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;




public class remoteconnect {

	public static void main(String[] args)  {
		
		 PowerShell powerShell = null;
		   try {
		       //Creates PowerShell session (we can execute several commands in the same session)
		       powerShell = PowerShell.openSession();
		       
		       //Execute a command in PowerShell session
		       PowerShellResponse response = powerShell.executeCommand("$pass = cat E:\\crede\\crede.txt | ConvertTo-SecureString -AsPlainText –Force");
		       
		       //Print results
		       System.out.println("Command1 executed" + response.getCommandOutput());
		       
		       //Execute another command in the same PowerShell session
		       response = powerShell.executeCommand("$cred = new-object -typename System.Management.Automation.PSCredential -argumentlist "+"TTTSSDEV\\30196589"+",$pass");
		       
		       //Print results
		       System.out.println("Command2 executed:" + response.getCommandOutput());
		       
                response = powerShell.executeCommand("Invoke-Command -ComputerName 10.36.20.41 -ScriptBlock {cmd.exe /c 'D:\\vector\\com\\VectorViolAcctCreation1.bat'} -credential $cred");
		       
		       //Print results
		       System.out.println("Command3 executed:" + response.getCommandOutput());
		   } catch(PowerShellNotAvailableException ex) {
		       //Handle error when PowerShell is not available in the system
		       //Maybe try in another way?
		   } finally {
		       //Always close PowerShell session to free resources.
		       if (powerShell != null)
		         powerShell.close();
		   }

	}
}

	
	


