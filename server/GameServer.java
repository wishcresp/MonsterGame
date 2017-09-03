import java.net.*;
import java.io.*;


/*
 * Michael you will need to split this into two classes, one basic init class
 * and then run the actual game loop code in another class called "GameLoop"
 * as per the LucidChart mockup 
 */


public class GameServer extends Thread
{
	/*
	 *  TODO I'm not sure if static variables are a good idea
	 *  How do i prevent static methods and variables??
	 */	
	static int dim, player_target;
	static Board board;
	static Players players;
	
	// FUCKING ECLIPSE WHY WONT YOU RUN MAIN????
	// use the drop down arrow boi
	public static void main(String[] args) throws InterruptedException
	{
		// Initialize
		Initialize();
		
		// Startup the threads
		int port = 64646;
		Thread listener = new NetServer(port);
		listener.start();

		// Start the main game loop
		GameLoop();
	}
	
	public static void Initialize()
	{		
		board = Board.get_board_instance();		
		players = Players.get_player_instance();
		players.create_players();
		board.create_board();
		player_target = players.get_player_target();
		dim = board.get_dimensions();
		
		// Initialise player objects, poorly done,
		// my rough player and board tracking required
		// they both contain player positions, 
		// this needs to be overhauled
		/*for (int i = 0; i < player_target; i++)
		{
			Entity p = players.get_player(i);
			board.set_tile(p.x, p.y, p);
		}*/		
	}
		
	// TODO Do i need to try to avoid static????
	// idk lol, if it works it works...
	public static void GameLoop() throws InterruptedException
	{
		GameState game_state = GameState.get_instance();
		/*
		 *  Main Game Loop
		 */
		while (true)
		{
			while (game_state.is_running() == false)
				Thread.sleep(100); // If the game isn't running, wait around
			/*
			 * Very basic startup code for rough manual testing
			 * 
			 * Moves players around and then updates the board object with
			 * their new positions
			 * 
			 * Will definately need to be redone, is placeholder
			 */
			
			// TODO: Michael you will need to delte and re-write this
			// Read up on the player objects and how the direction
			// and desired direction will work, movement
			// will be Pacman like
			// You're gonna need to write that out and then work on an AI
			// Also remember that player.x and player.y will be where the players
			// are, their position being stored as objects in the board tiles
			// was a bad idea and needs to be scraped
			
			
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
