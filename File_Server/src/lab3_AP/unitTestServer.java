package lab3_AP;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class unitTestServer {

	public static void ReadFile(int SOCKET_PORT, String FILE_TO_SEND) throws IOException{
		    FileInputStream readFile = null;
		    BufferedInputStream buff = null;
		    OutputStream otstream = null;
		    ServerSocket servsock = null;
		    Socket sock = null;
		    
		    try {
		      servsock = new ServerSocket();
		      servsock.setReuseAddress(false);
		      servsock.bind(new InetSocketAddress(SOCKET_PORT));
		     
		       		
		       
		          sock = servsock.accept();
		          System.out.println("Connected : " + sock);
		    
		          File myFile = new File (FILE_TO_SEND);
		          byte [] mybytearray  = new byte [(int)myFile.length()];
		          readFile = new FileInputStream(myFile);
		          buff = new BufferedInputStream(readFile);
		          buff.read(mybytearray,0,mybytearray.length);
		          otstream = sock.getOutputStream();
		          otstream.write(mybytearray,0,mybytearray.length);
		          otstream.flush();
		          System.out.println("Done.");
		       
		          if (buff != null) 
		        	  buff.close();
		          if (otstream != null) 
		        	  otstream.close();
		          if (sock!=null) 
		        	  sock.close();
		      
		    } 
		    catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    finally {
		      if (servsock != null) 
		    	  servsock.close();
		    }
		    
		  
	  }
	  

	  
}
