public class Players
{
	/*
	 * Contains the array of players with the monster as the final player
	 * entity
	 * As far as direction goes, the direction the monster is moving in
	 * is Also handles info about player limits and connected players
	 */


	// TODO: Have this load from file like the game board
	private String[] avaliable_spots = {"1,1", "1,9", "9,1", "9,9"};
	
	// Required for Server reasons
	private int player_target = -1; // Will be set by the first player to join
	private int current_players = 0; // How many players are currently connected
	private int alive_players = 0; // How many players are currently alive
	private int max_players = 4; // Soft limited according to specification
	
	private Entity players[]; //TODO: MUTEX

	private boolean locked = false;
	
	public Players()
	{
		return;
	}
	
	
	public void claim_spot(String spot)
	{
		String[] tmp_spots = new String[avaliable_spots.length];
		for (int i = 0; i < avaliable_spots.length; i++)
			tmp_spots[i] = avaliable_spots[i];
			
		
		for (int i = 0; i < avaliable_spots.length; i++)
		{
			if (avaliable_spots[i].contains(spot))
				avaliable_spots[i] = "";
			else
				avaliable_spots[i] = tmp_spots[i];
		}
		
	}
	
	
	public void remove_player(int id)
	{
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

	// Return a string representation of the players and monster
	public String toString() 
	{
		String out = "";
		
		for (int i = 0; i < current_players; i++)
		{
			// Wait around before we get into things
			while (locked)
				try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
			Player cur = (Player) players[i];
			out += String.valueOf(cur.get_pos_y())+","+String.valueOf(cur.get_pos_x());
			out += ","+String.valueOf(cur.get_ddir());
			if (cur.is_dead() == true)
				out += ",D";
			else
				out += ","+String.valueOf(i); // Player.id
			out += ":";
		}
		// Don't forget to add the monster ;)
		Entity cur = players[player_target];
		out += String.valueOf(cur.get_pos_y())+","+String.valueOf(cur.get_pos_x());
		out += ","+String.valueOf(cur.get_ddir());
		out += ","+String.valueOf(player_target); // Player.id
		out += ":";
		return out;
	}


	public int get_alive_players() 
	{
		return alive_players;
	}


	public void set_alive_players(int alive_players) 
	{
		this.alive_players = alive_players;
	}	
	
	public void lock()
	{
		this.locked = true;
	}
	public void unlock()
	{
		this.locked = false;
	}
	
	public boolean islocked()
	{
		return this.locked;
	}
	
	
}