package Filetransfer;


import java.io.InputStream;

import com.jcraft.jsch.*;

public class runCommand {
	
	public static void main(String[] args) {

   	  JSch jsch = new JSch();
		Session session = null;
		String command1="ls -ltr";
	    try{
	    	
	    	session = jsch.getSession("50004561", "10.36.20.47", 22);

	        session.setConfig("StrictHostKeyChecking", "no");
	        session.setPassword("Rocky@123");
	        session.connect();
	        System.out.println("Connected to session successfully");
	    	
	    	Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand(command1);
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1024];
	        while(true){
	          while(in.available()>0){
	            int i=in.read(tmp, 0, 1024);
	            if(i<0)break;
	            System.out.print(new String(tmp, 0, i));
	          }
	          if(channel.isClosed()){
	            System.out.println("exit-status: "+channel.getExitStatus());
	            break;
	          }
	          try{Thread.sleep(1000);}catch(Exception ee){}
	        }
	        channel.disconnect();
	        session.disconnect();
	        System.out.println("DONE");
	    }catch(Exception e){
	    	e.printStackTrace();
	    }

	}

}
