
public class GameServer extends Thread
{
	// Declare class variables
	static int dim, player_target;
	static Board board;
	static Players players;
	static GameState gamestate;
	
	
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
		gamestate = GameState.get_instance();
		board = gamestate.get_board();		
		players = gamestate.get_players();	
	
		// Amount of players to join
		player_target = players.get_player_target();
	
		
		
		// Generate the game board
		board.create_board();
		
		// Try to print out the string representation of the board
		System.out.println("\n" + board.get_layout());		
		
		// THIS IS TESTING OUT MY BRAND NEW NETWORK GRAPH
		board.build_board_graph();	
		
		
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
			
			// TODO: Michael you will need to delte and re-write this
			// Read up on the player objects and how the direction
			// and desired direction will work, movement
			// will be Pacman like
			// You're gonna need to write that out and then work on an AI
			// Also remember that player.x and player.y will be where the players
			// are, their position being stored as objects in the board tiles
			// was a bad idea and needs to be scraped
			
			
			
			// Cheeky dump of the gameboard for debugging purposes
			System.out.println(board.toString());
			Thread.sleep(100);
		}
	}		
}