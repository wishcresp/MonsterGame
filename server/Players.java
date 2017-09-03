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
	
	private static Players Players = new Players();
	private Players()
	{
		return;
	}
	
	// TODO: Have this load from file like the gameboard
	private String[] avaliable_spots = {"2,2", "2,10", "10,2", "10,10"};
	
	// Required for Server reasons
	private int player_target = -1; // Will be set by the first player to join
	private int current_players = 0;
	private int max_players = 4; // Soft limited according to spec

	private Entity players[];

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
		return "temp";
	}
	
	
	public static Players get_player_instance()
	{
		return Players;
	}
}
