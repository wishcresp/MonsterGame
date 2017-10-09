import java.util.Random;

public class GameServer extends Thread
{
	// Declare class variables
	private static int dim, player_target;
	private static Board board;
	private static Players players;
	private static GameState gamestate;
	private static MonsterAi monster;

	
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
		
		// Generate the random number
		Random rn = new Random();
		gamestate.set_random_number(rn.nextInt() % 4);
	}
		
	
	private static boolean check_kill()
	{
		Monster monster_e = ((Monster)players.get_player(players.get_player_count()));

		// Check to see if the monster's eating anyone
		for (int i = 0; i < players.get_player_count(); i++)
		{
			Player player;
			player = ((Player)players.get_player(i));
			if (monster_e.get_pos_x() == player.get_pos_x() && monster_e.get_pos_y() == player.get_pos_y() && player.is_dead() == false)
			{
				player.kill(); // Kill 'em if we're touching them
				//System.out.println("\nKILL THIS PLAYER");
				monster_e.set_cool_down(10);
				return true;
			}
		}

		return false;	
	}
	
	public static void GameLoop() throws InterruptedException
	{		
		GameState game_state = GameState.get_instance();
		Players players = game_state.get_players();
		Board board = game_state.get_board();
		int start_position = 22;
		
		Monster monster_entity = null ;
		
		// Set the AI position in the GRAPH
		monster.set_monster_position(start_position);
		
		/*
		 * Main Game Loop
		 */
		while (game_state.is_running() == false)
			Thread.sleep(100); // If the game isn't running, wait around

		
		((Monster)players.get_player(players.get_player_count())).set_cool_down(5);
		while (true) 
		{
			players.lock();
			
			
			check_kill();
			
			for (int i = 0; i < players.get_player_count(); i++) 
			{
				Entity player = players.get_player(i);
				
				if (player instanceof Player && ((Player)player).is_dead())
					continue; // Skip the dead ones
				
				if (player instanceof Monster)
				{
					/*monster_entity = (Monster) player;
					monster_entity.move(players, monster);*/
				}
				else				
					player.move();				
			}
			
			check_kill();
		
			
			// Lastly, move the monster
			Monster monster_e = ((Monster)players.get_player(players.get_player_count()));
			monster_e.move(players, monster);
			
			check_kill();
			
			
			players.unlock();
			
			System.out.println(players.toString());
			
			Thread.sleep(250);
		}
	}
}