package lab3_AP;

import java.io.IOException;
import org.junit.AfterClass;
import org.junit.Test;

public class unitTestClient {
	
	  public final static int SOCKET_PORT = 55555;      
	  public final static String SERVER = "172.16.163.235";  
	  public final static String FILE_TO_RECEIVED = "/home/files/testing";  // you may change this
	  
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testMain() throws IOException {
		checkClient.recieveFile(SOCKET_PORT, SERVER, FILE_TO_RECEIVED);
	}

}
