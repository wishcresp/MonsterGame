import java.util.Random;

public class GameServer extends Thread
{
	// Declare class variables
	private static Board board;
	private static Players players;
	private static GameState gamestate;
	private static MonsterAi monster;
	//private static GraphVertex[] graph;
	
	public static void main(String[] args) throws InterruptedException
	{

		// Setup the board and players
		Initialize(); 
			
		// Startup the threads
		int port = 3216;
		Thread listener = new NetServer(port);
		listener.start();
		
		
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
		monster = new MonsterAi(board.build_monster_graph());
	
		// Generate the game board
		board.create_board();
		board.create_associative_array();
		
		// Generate the random number
		Random rn = new Random();
		gamestate.set_random_number(Math.abs(rn.nextInt() % 4));
	}
		
	// Check if there's a player under the monster and kill if there is
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
		int start_position = 22;
		
		// Set the AI position in the GRAPH
		monster.set_monster_position(start_position);
		
		/*
		 * Main Game Loop
		 */
		while (game_state.is_running() == false)
			Thread.sleep(100); // If the game isn't running, wait around

		
		((Monster)players.get_player(players.get_player_count())).set_cool_down(5); // Give the players a chance to position themselves
		int tick = 0;
		while (true) 
		{
			players.lock(); // Lock the MUTEX
			
			check_kill(); // At every stage, check if we can kill the player
			
			for (int i = 0; i < players.get_player_count(); i++) 
			{
				Entity player = players.get_player(i);
				
				if (player instanceof Player && ((Player)player).is_dead())
					continue; // Skip the dead ones
				
				player.move();				
			}
			
			check_kill();
		
			// Lastly, move the monster
			if (tick == 0)
				((Monster)players.get_player(players.get_player_count())).move(players, monster);
			
			check_kill();
			
			
			players.unlock(); // Unlock the players
			
			System.out.println(players.toString()); // Debug the state of everything
			
			if (tick == 1)
				tick = 0;
			else
				tick++;
			
			Thread.sleep(256); // Finnaly, sleep
		}
	}
}