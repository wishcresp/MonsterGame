
public class GameServer extends Thread
{
	// Declare class variables
	static int dim, player_target;
	static Board board;
	static Players players;
	static GameState gamestate;
	static MonsterAi monster;

	
	public static void main(String[] args) throws InterruptedException
	{
		// Startup the threads
		int port = 3216;
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
		
		// Create monster object
		monster = board.build_monster_graph();
		
		
		// Amount of players to join
		player_target = players.get_player_target();
		
		// Generate the game board
		board.create_board();
	
		board.create_associative_array();
	}
		
	public static void GameLoop() throws InterruptedException
	{		
		GameState game_state = GameState.get_instance();
		Players players = game_state.get_players();
		Board board = game_state.get_board();
		int start_position = 22;
		
		Entity monster_entity = null ;
		
		// Set the AI position in the GRAPH
		monster.set_monster_position(start_position);
		
		/*
		// TODO Get instance of monster in the array of players
		for (int i = 0; i <= players.get_player_count(); i++) 
		{
			Entity player = players.get_player(i);

			// If this is the monster
			if (player instanceof Monster)
				monster_entity = player;
		}

		monster_entity.set_pos_x(5);
		monster_entity.set_pos_y(5);
		*/
		
		/*
		 * Main Game Loop
		 */
		while (true) 
		{
			while (game_state.is_running() == false)

				Thread.sleep(100); // If the game isn't running, wait around
			
			for (int i = 0; i <= players.get_player_count(); i++) 
			{
				Entity player = players.get_player(i);

				// If this is the monster
				if (player instanceof Monster)
					monster_entity = player;
			}

			monster_entity.set_pos_x(5);
			monster_entity.set_pos_y(5);

			for (int i = 0; i < players.get_player_count(); i++) 
			{
				Entity player = players.get_player(i);
				player.move();
			}

			Thread.sleep(500);
		}
	}
}