import java.net.*;
import java.io.*;

public class NetServer extends Thread
{
	private ServerSocket sock;
	private int conn_target;
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
		

		Player players = Player.get_player_instance();
		conn_target = players.get_player_target();
	}

	public int get_conn_target()
	{
		return conn_target;
	}
	public void set_conn_target(int target)
	{
		this.conn_target = target;
	}

	public void run()
	{
		int conn_count = 0; // TODO: Have some way to decrement on drop out
		Thread[] conns = new ServerConnHandler[this.conn_target];

		System.out.println("Started server thread.");

		for (int i = 0; i < conn_target; i++)
		{
			try
			{
				System.out.println("Waiting for a client on "+sock.getLocalPort()+"...");

				Socket conn = sock.accept();

				conns[i] = new ServerConnHandler(conn, conn_count);
				conns[i].start();
				conn_count++;

			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}


}
