import java.net.*;
import java.io.*;

public class NetServer extends Thread
{
	private ServerSocket sock;
	private int conn_target; // Update from the Players Singleton
	                         // for internal use only
	private int max_conn_target;
	private int port;

	public NetServer(int port)
	{
		this.port = port;
		try
		{
			sock = new ServerSocket(port);
			sock.setSoTimeout(0); // Wait till we find some[one,thing]
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		

		Players players = Players.get_player_instance();

		conn_target = 1; // Get the initial connection then have them
		                 // set the proper value
		
		this.max_conn_target = 4; // This 
	}

	public void run()
	{
		int conn_count = 0; // TODO: Have some way to decrement on drop out
		Thread[] conns = new ServerConnHandler[this.max_conn_target];
		Players players = Players.get_player_instance();
		
		
		System.out.println("Started server thread.");

		for (int i = 0; i < this.conn_target; i++)
		{
			try
			{
				System.out.println("Waiting for a client on "+sock.getLocalPort()+"...");

				Socket conn = sock.accept();

				conns[i] = new ServerConnHandler(conn, conn_count);
				conns[i].start();
				conn_count++;
				
				if (i == 0) // If this is the first connection
					        // wait for the player to set the conn_target
				{
					while (players.get_player_target() == -1)
						Thread.sleep(100);
					this.conn_target = players.get_player_target();									
				}
				/*
				 * Unfortunately the first connecting player has to set the player
				 * target before other players can connect
				 */
				

			}
			catch (IOException | InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		// Once all the threads are started, start the game and die
		GameState.get_instance().change_run_state(true);
	}


}
