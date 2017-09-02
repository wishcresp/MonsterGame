import java.net.*;
import java.io.*;


/*
 * Michael you will need to split this into two classes, one basic init class
 * and then run the actual game loop code in another class called "GameLoop"
 * as per the LucidChart mockup 
 */


public class GameServer extends Thread
{
	public static void main(String[] args)
	{
		// Basic setup and init
		int port = 64646;
		Board board = Board.get_board_instance();
		Players players = Players.get_player_instance();
		players.create_players();
		board.create_board();
		int player_target = players.get_player_target();
		int dim = board.get_dimensions();


		// Initialise player objects, poorly done,
		// my rough player and board tracking required
		// they both contain player positions, 
		// this needs to be overhauled
		for (int i = 0; i < player_target; i++)
		{
			Entity p = players.get_player(i);
			board.set_tile(p.x, p.y, p);
		}



		// Startup the threads
		Thread listener = new NetServer(port);
		listener.start();

		/*
		 *  Main Game Loop
		 */
		boolean running = true;
		while (running)
		{
			
			
			/*
			 * Very basic startup code for rough manual testing
			 * 
			 * Moves players around and then updates the board object with
			 * their new positions
			 * 
			 * Will definately need to be redone, is placeholder
			 */
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
