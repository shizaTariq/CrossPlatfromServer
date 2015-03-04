package lab3_AP;

import java.io.IOException;
import org.junit.Test;

public class JavaServerTest {
	
	public final static int SOCKET_PORT = 55555;  // you may change this
	public final static String FILE_TO_SEND = "/home/files/send";  // you may change this


	@Test
	public void testMain() throws IOException {
		unitTestServer.ReadFile(SOCKET_PORT, FILE_TO_SEND);
	}

}
