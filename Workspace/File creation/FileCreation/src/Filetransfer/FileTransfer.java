package Filetransfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.jcraft.jsch.*;

public class FileTransfer {
    public static void main(String args[]) throws SftpException, FileNotFoundException {

    	 JSch jsch = new JSch();
    	    Session session = null;
    	    try {
                session = jsch.getSession("50004561", "10.36.20.47", 22);

    	        session.setConfig("StrictHostKeyChecking", "no");
    	        session.setPassword("Rocky@123");
    	        session.connect();
    	        System.out.println("Connected to session successfully");
    	        Channel channel = session.openChannel("sftp");
    	        channel.connect();
    	        System.out.println("Connected to Channel successfully");
    	        ChannelSftp sftpChannel = (ChannelSftp) channel;

    	        // this will read file with the name test.txt and write to remote directory
    	        sftpChannel.cd("/app/vector/data_files/njtp_qatp");
    	        File f = new File("E:/TSG/Workspace/App/WebApp-01/Files/v20170727000037003.tr");
    	        sftpChannel.put(new FileInputStream(f), f.getName()); // here you can also change the target file name by replacing f.getName() with your choice of name

    	        sftpChannel.exit();
    	        session.disconnect();
    	    } catch (JSchException e) {
    	        e.printStackTrace();  
    	    }
    	
    }
}