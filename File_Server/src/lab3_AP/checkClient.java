package lab3_AP;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class checkClient {

	  public static void main (String [] args ) throws IOException {
		
	  }
	  
	  public static void recieveFile(int SOCKET_PORT, String SERVER, String saveName) throws IOException{
		    int sizeOfFall = 1000000;
			int bytesRead;
		    int current = 0;
		    FileOutputStream istream = null;
		    BufferedOutputStream bos = null;
		    Socket sock = null;
		    try {
		      sock = new Socket(SERVER, SOCKET_PORT);
		      

		     
		      byte [] mybytearray  = new byte [sizeOfFall];
		      InputStream is = sock.getInputStream();
		      istream = new FileOutputStream(saveName);
		      bos = new BufferedOutputStream(istream);
		      bytesRead = is.read(mybytearray,0,mybytearray.length);
		      current = bytesRead;

		      do {
		         bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
		         if(bytesRead >= 0) current += bytesRead;
		      } while(bytesRead > -1);

		      bos.write(mybytearray, 0 , current);
		      bos.flush();
		      
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    finally {
		      if (istream != null) 
		    	  istream.close();
		      if (bos != null) 
		    	  bos.close();
		      if (sock != null) 
		    	  sock.close();
		    }
	  }
	
}
