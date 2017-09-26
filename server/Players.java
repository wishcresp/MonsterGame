public class Players
{
	/*
	 * Contains the array of players with the monster as the final player
	 * entity
	 * As far as direction goes, the direction the monster is moving in
	 * is Also handles info about player limits and connected players
	 */
	
	private static Players Players = new Players();
	
	private Players()
	{
		return;
	}
	
	// TODO: Have this load from file like the game board
	private String[] avaliable_spots = {"2,2", "2,10", "10,2", "10,10"};
	
	// Required for Server reasons
	private int player_target = -1; // Will be set by the first player to join
	private int current_players = 0; // How many players are currently connected
	private int max_players = 4; // Soft limited according to specification
	
	private Entity players[];
	private Player test[];


	// Player target
	public int get_player_target()
	{
		return this.player_target;
	}
	
	public void set_player_target(int player_target)
	{
		this.player_target = player_target;
	}
	
	// max players
	public int get_max_players()
	{
		return this.max_players;
	}
	
	// Current amount of players
	public int get_player_count()
	{
		return this.current_players;
	}	
	
	public void set_player_count(int player_count)
	{
		this.current_players = player_count;
	}
	
	// Player direction
	public void set_player_dir(int id, int dir)
	{
		players[id].set_dir(dir);
	}

	// getter for player id
	public Entity get_player(int id)
	{
		return players[id];
	}

	public void create_players()
	{
		// Create some empty objects
		int i;
		players = new Entity[player_target+1]; // One extra for the monster
		for (i = 0; i < player_target; i++)
			players[i] = null; // These actual objects will be created by the connection
			                   // handler threads
		i++;
		
		players[i] = new Monster();
	}
	
	public String[] get_avaliable_spots()
	{
		return this.avaliable_spots;
	}
	
	public void claim_spot(int spot_id)
	{
		this.avaliable_spots[spot_id] = null;
	}
	
	public void add_player(Player player)
	{
		players[current_players] = player;
	}

	public String toString() 
	{
		// TODO: Return a string representation of the
		// players positions

		String player_string = "";
		
		// THIS IS A TEST FOR PLAYER DIRECTIONING (name, pos_x, pos_y)
		test[1] = new Player("Blaise", 1, 1);
		
		for (int i = 0; i < this.current_players; i++) 
		{
			player_string += Integer.toString(test[i].get_pos_x());
			player_string += ",";
			player_string += Integer.toString(test[i].get_pos_y());
			player_string += ";";
		}

		return player_string;
	}

	public static Players get_player_instance() 
	{
		return Players;
	}

	// not sure if it should be static
	public void player_move(String name, int x, int y) 
	{
		// JUST A SAMPLE
		String direction = "LEFT";

		// THIS IS A TEST FOR PLAYER DIRECTIONING (name, pos_x, pos_y)
		Player player1 = new Player(name, x, y);

		// DEBUG, find out the current player coordinate
		System.out.println(player1.get_pos_x() + player1.get_pos_y());

		switch (direction) 
		{
		// (x - 1, y)
		case "UP":
			if (check_move(player1.get_pos_x() - 1, player1.get_pos_y()))
				// Update the player position if valid
				player1.set_pos_x(player1.get_pos_x() - 1);
			break;

		// (x + 1, y)
		case "DOWN":
			if (check_move(player1.get_pos_x() + 1, player1.get_pos_y()))
				// Update the player position if valid
				player1.set_pos_x(player1.get_pos_x() + 1);
			break;

		// (x, y - 1)
		case "LEFT":
			if (check_move(player1.get_pos_x(), player1.get_pos_y() - 1))
				// Update the player position if valid
				player1.set_pos_y(player1.get_pos_y() - 1);
			break;

		// (x, y + 1)
		case "RIGHT":
			if (check_move(player1.get_pos_x(), player1.get_pos_y() + 1))
				// Update the player position if valid
				player1.set_pos_y(player1.get_pos_y() + 1);
			break;

		default:
			System.out.println("INVALID KEY WAS PRESSED");
		}

		// DEBUG, find out the current player coordinate
		System.out.println(player1.get_pos_x() + player1.get_pos_y());

	}

	// shouldnt be static but oh well, we will need to move this elsewhere
	public boolean check_move(int x, int y) 
	{
		/*
		 * Maybe we can check to see if this coordinate exists/valid e.g. By moving from
		 * coordinate point (5,1) -> (5,9) via teleport Check to see if 5,9 exists as a
		 * coordinate, and if the player can move there. Meaning there is no player
		 * currently occupying that spot
		 */

		return true;
	}
	
	
}
