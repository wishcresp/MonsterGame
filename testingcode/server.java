import java.net.*;
import java.io.*;

public class server extends Thread
{
	private ServerSocket sock;
	private int conn_target;
	private int port;

	public server(int port)
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
		this.port = port;

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
		Thread[] conns = new ConnHandler[this.conn_target];

		System.out.println("Started server thread.");

		for (int i = 0; i < conn_target; i++)
		{
			try
			{
				System.out.println("Waiting for a client on "+sock.getLocalPort()+"...");

				Socket conn = sock.accept();

				conns[i] = new ConnHandler(conn, conn_count);
				conns[i].start();
				conn_count++;

			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args)
	{
		int port = 6464;

		Board board = Board.get_board_instance();
		Player players = Player.get_player_instance();

		players.create_players();
		board.create_board();

		int player_target = players.get_player_target();
		int dim = board.get_dimensions();


		for (int i = 0; i < player_target; i++)
		{
			Entity p = players.get_player(i);
			board.set_tile(p.x, p.y, p);
		}



		Thread listener = new server(port);
		listener.start();

		/*
		 *  Main Game Loop
		 */
		boolean running = true;
		while (running)
		{
			for (int i = 0; i < player_target; i++)
			{
				Entity p = players.get_player(i);
				switch (p.get_dir())
				{
					case 0: // UP
						if (p.x > 0 && board.is_free(p.x-1,p.y))
						{
							board.set_tile(p.x-1, p.y, p);
							board.set_tile(p.x, p.y, null);
							p.x--;
						}
						break;
					case 1: // DOWN
						if (p.x < dim && board.is_free(p.x+1,p.y))
						{
							board.set_tile(p.x+1, p.y, p);
							board.set_tile(p.x, p.y, null);
							p.x++;
						}
						break;
				}
			}
			try
			{
				Thread.sleep(100);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}
}
