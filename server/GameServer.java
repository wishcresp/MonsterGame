
public class GameServer extends Thread
{
	// Declare class variables
	static int dim, player_target;
	static Board board;
	static Players players;
	
	public static void main(String[] args) throws InterruptedException
	{
		// Startup the threads
		int port = 6432;
		Thread listener = new NetServer(port);
		listener.start();
		
		// Setup the board and players
		Initialize(); 

		// Start the main game loop
		GameLoop(); 

		// This is here to see if it ends
		System.out.println("Terminating program");
	}
	
	public static void Initialize()
	{		
		// Instantiate Board and players
		board = Board.get_board_instance();		
		players = Players.get_player_instance();
		
		//players.create_players(); Let the first player to join
		// create the players when they know how many they want
		
		// Generate the game board
		board.create_board();
		
		// Amount of players to join
		player_target = players.get_player_target();
		
		// Return dimensions to dim integer (11 is the dimension)
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
		
		// Try to print out the string representation of the board
		System.out.println("\n" + board.get_layout());
		
		
		
		
	}
		
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
						if (p.pos_x > 0 && board.is_free(p.pos_x-1,p.pos_y))
						{
							board.set_tile(p.pos_x - 1, p.pos_y, p);
							board.set_tile(p.pos_x, p.pos_y, null);
							p.pos_x--;
						}
						break;
						
					case 1: // DOWN
						if (p.pos_x < dim && board.is_free(p.pos_x+1,p.pos_y))
						{
							board.set_tile(p.pos_x+1, p.pos_y, p);
							board.set_tile(p.pos_x, p.pos_y, null);
							p.pos_x++;
						}
						break;
				}
			}
			

			System.out.println(board.toString()); // Cheeky dump of the gameboard for debugging
			                                      // purposes
			
			
			Thread.sleep(100);
		}
	}
}