public class Players
{
	/*
	 * Contains the array of players with the monster as the final player
	 * entity
	 * 
	 * As far as direction goes, the direction the monster is moving in
	 * is 
	 * 
	 * Also handles info about player limits and connected players
	 */
	
	public Players()
	{
		return;
	}
	
	// Required for Server reasons
	private int player_target = -1; // Will be set by the first player to join
	private int current_players = 0;
	private int max_players = 4; // Soft limited according to spec
	private int PC_id = -1; // Player Character ID
	private String starter_spot = "";

	
	private Entity players[];

	private void swap(int id1, int id2)
	{
		Entity tmp;
		
		tmp = players[id1];
		players[id1] = players[id2];
		players[id2] = tmp;
		
	}
	
	public void remove_player(int id)
	{
		swap(id, players.length-1);
		Entity tmp[] = new Entity[players.length-1];
		
		for (int i = 0; i < players.length-1; i++)
		{
			tmp[i] = players[i];
		}
		players = tmp;
	}
	
	
	public int get_pc_id()
	{
		return this.PC_id;
	}
	
	public void set_pc_id(int id)
	{
		this.PC_id = id;
	}
	
	public int get_player_target()
	{
		return this.player_target;
	}
	public void set_player_target(int player_target)
	{
		this.player_target = player_target;
	}
	public int get_max_players()
	{
		return this.max_players;
	}
	public int get_player_count()
	{
		return this.current_players;
	}
	public void set_player_count(int player_count)
	{
		this.current_players = player_count;
	}

	public void set_player_dir(int id, int dir)
	{
		players[id].set_dir(dir);
	}

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
			players[i] = new Player(); // These actual objects will be created by the connection
			                   // handler threads
		players[i] = new Monster();
	}
	
	public void add_player(Player player)
	{
		players[current_players] = player;
	}

	
	public String fromString() 
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

	public String get_starter_spot()
	{
		return starter_spot;
	}

	public void set_starter_spot(String starter_spot)
	{
		this.starter_spot = starter_spot;
	}
	
	
}
