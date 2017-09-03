/*
 * Michael you will need to split this into two classes, one basic init class
 * and then run the actual game loop code in another class called "GameLoop"
 * as per the LucidChart mockup 
 */


public class GameClient extends Thread
{
	/*
	 *  TODO I'm not sure if static variables are a good idea
	 *  How do i prevent static methods and variables??
	 */	
	static Board board;
	static Players players;
	
	// FUCKING ECLIPSE WHY WONT YOU RUN MAIN????
	// use the drop down arrow boi
	public static void main(String[] args) throws InterruptedException
	{
		// Initialize
		Initialize();
		
		// Startup the net thread
		int port = 64646;
		Thread listener = new NetClient(port);
		listener.start();

		// Start the main game loop
		GameLoop();
	}
	
	public static void Initialize()
	{		
		board = Board.get_board_instance();		
		players = Players.get_player_instance();
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
			 * TODO: EVERYTHING
			 * Alright Sean you're up, have this draw the game board
			 * and the players, also you're gonna needa read user input
			 * and 
			 */
			
		}

	}
}
