import java.net.*;
import java.io.*;

public class NetServer extends Thread 
{
	private ServerSocket sock = null;
	private int conn_target; // Update from the Players Singleton
								// for internal use only
	private int max_conn_target;
	private Thread[] conns;
	
	public NetServer(int port) 
	{
		try 
		{
			do
			{
				try {sock = new ServerSocket(port);}
				catch ( BindException e )
				{
					System.out.println("Erorr: Port already taken, waiting for port to free");
					Thread.sleep(1000);
				}
				
			} while (sock == null);
			
			
			sock.setSoTimeout(0); // Wait till we find some[one,thing]
		} catch (Exception e ) {
			e.printStackTrace();
		}
		Players players = GameState.get_instance().get_players();	

		conn_target = 1; // Get the initial connection then have them
							// set the proper value

		this.max_conn_target = players.get_max_players();
	}

	public void run() 
	{
		int conn_count = 0; // TODO: Have some way to decrement on drop out
		conns = new ServerConnHandler[this.max_conn_target];
		Players players = GameState.get_instance().get_players();

		System.out.println("Started server thread.");

		for (int i = 0; i < this.conn_target; i++) 
		{
			try 
			{
				System.out.println("Waiting for a client on " + sock.getLocalPort() + "...");

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
				 * Unfortunately the first connecting player has to set the
				 * player target before other players can connect
				 */

			} catch (IOException | InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
