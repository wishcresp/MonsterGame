public class Players
{
	/*
	 * Contains the array of players with the monster as the final player
	 * entity
	 * As far as direction goes, the direction the monster is moving in
	 * is Also handles info about player limits and connected players
	 */

	public Players()
	{
		return;
	}
	
	// TODO: Have this load from file like the game board
	private String[] avaliable_spots = {"1,1", "1,9", "9,1", "9,9"};
	
	// Required for Server reasons
	private int player_target = -1; // Will be set by the first player to join
	private int current_players = 0; // How many players are currently connected
	private int alive_players = 0; // How many players are currently alive
	private int max_players = 4; // Soft limited according to specification
	
	private Entity players[]; //TODO: MUTEX

	
	/*// TODO: Preserver IDs
	private void swap(int id1, int id2)
	{
		Entity tmp;
		
		tmp = players[id1];
		players[id1] = players[id2];
		players[id2] = tmp;
		
	}*/
	
	public void remove_player(int id)
	{
		/*swap(id, players.length-1);
		Entity tmp[] = new Entity[players.length-1];
		
		for (int i = 0; i < players.length-1; i++)
		{
			tmp[i] = players[i];
		}
		players = tmp;*/
		((Player)players[id]).kill();
	}
	
	
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
	// Player direction
	public void set_player_ddir(int id, int dir)
	{
		players[id].set_ddir(dir);
	}

	// getter for player id
	public Entity get_player(int id)
	{
		return players[id];
	}

	public void create_players()
	{
		// Create some empty objects
		players = new Entity[player_target + 1]; // One extra for the monster
		int i;

		// These actual objects will be created by the connection handler threads
		for (i = 0; i < player_target; i++)			
			players[i] = null; 
		
		// Monster at the end of the array
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
		// THIS KEEPS OVERWRITING THE MONSTER
		players[current_players] = player;
	}

	public String toString() 
	{
		String player_string = "";
		
		for (int i = 0; i < this.current_players; i++) 
		{
			/*
			 * Changed this around a bit, needs a getter
			 */
			player_string += Integer.toString(players[i].get_pos_x());
			player_string += ",";
			player_string += Integer.toString(players[i].get_pos_y());
			player_string += ";";
		}
		return player_string;
	}


	public int get_alive_players() 
	{
		return alive_players;
	}


	public void set_alive_players(int alive_players) 
	{
		this.alive_players = alive_players;
	}	
}