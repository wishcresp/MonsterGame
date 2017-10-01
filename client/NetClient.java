import java.net.*;
import java.io.*;

public class NetClient extends Thread
{
	private ServerSocket sock;

	public NetClient(int port)
	{
		try
		{
			sock = new ServerSocket(port);
			sock.setSoTimeout(0); // Wait till we find some[one,thing]
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void run()
	{
		System.out.println("Started client thread.");

		ConnHandler chandle;
		
		try
		{
			System.out.println("Waiting for a client on "+sock.getLocalPort()+"...");

			Socket conn = sock.accept();

			chandle = new ClientConnHandler(conn, 0);
			chandle.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		// Once we're connected, set the game state to running
		// TODO: Maybe change this to another condition
		GameState.get_instance().change_run_state(true);
	}


}
