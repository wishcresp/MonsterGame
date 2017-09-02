import java.net.*;
import java.io.*;

public class GameServer extends Thread
{
	public static void main(String[] args)
	{
		int port = 64646;

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



		Thread listener = new NetServer(port);
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
