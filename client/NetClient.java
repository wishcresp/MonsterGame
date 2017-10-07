import java.net.*;
import java.io.*;

public class NetClient extends Thread
{
	
	Socket conn;
	public NetClient()
	{
	}

	public void run()
	{
		GameState game_state = GameState.get_instance();
		
		
		System.out.println("Started client thread.");

		ConnHandler chandle;
		
		try
		{
			// Wait for user to enter IP
			while (game_state.get_server_ip() == "")
			{
				try {
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
					System.out.println("There was an interupt while waiting for IP.");
					System.out.println(e.getMessage());
				}
			}
			
			System.out.println("Got IP "+game_state.get_server_ip()+":"+game_state.get_server_port());

			 conn = new Socket(game_state.get_server_ip(), game_state.get_server_port());
			 conn.setSoTimeout(0); // Wait till we find some[one,thing]

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
