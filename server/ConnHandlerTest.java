import static org.junit.Assert.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;



public class ConnHandlerTest
{
	private ServerSocket sock;
	private Socket conn;
	private int port = 3216;
	private ServerConnHandler test;
	private String test_data = "Shalom";
	
	@Before
	public void setup()
	{
		try
		{
			sock = new ServerSocket(port);
			sock.setSoTimeout(0); // Wait till we find some[one,thing]
			
			
			conn = sock.accept(); // Actually wait till the test client shows up		
			
			test = new ServerConnHandler(conn, 0);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

	@Test
	public void test_send_receive()
	{
		System.out.println("Sending to client    :" + test_data + ";");
		test.send_string(test_data);
		String test_string = test.get_string();
		/*
		 * This test requires a client that echos back what is sent to it
		 * I wrote one and threw it in a folder called "sockecho" on the GitHub 
		 */
		System.out.println("Got from client      :" + test_string + ";");
		System.out.println("Expected from client :" + test_data + ";");
		assertEquals(true, test_string.equals(test_data));
	}
}
