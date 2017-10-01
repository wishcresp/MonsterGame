import java.io.IOException;
import java.net.Socket;

public class ServerConnHandler extends ConnHandler 
{
	public ServerConnHandler(Socket conn, int id) throws IOException 
	{
		super(conn, id);
	}

	@Override
	public void conn_work(Board board, Players players) throws InterruptedException 
	{
		int new_player_target = 0;

		// Send game info to client
		send_string(Integer.toString(board.get_dimensions())); // Board
																// dimensions
		send_string(board.get_layout()); // Board layout
		send_string(Integer.toString(this.id)); // The client's ID

		// TODO: Client interaction and player setup

		if (this.id == 0) // If this is the first player to connect
		{
			new_player_target = Integer.valueOf(get_string());

			if (new_player_target < 0 || new_player_target > players.get_max_players())
				new_player_target = players.get_max_players(); // Basic
																// validation

			players.set_player_target(new_player_target); // Let them set the
															// player target

			// Now that we know how many player's there's going to be, let the
			// player
			// object init the players
			players.create_players();
		}

		// Create this player's object
		Player player = new Player();

		// Set their name
		player.set_name(get_string());

		// TODO: Validate player's chosen spot is not null
		// Wait until player above them has picked a spot
		if (this.id > 0)
			
			while (players.get_player(this.id - 1) == null)
				Thread.sleep(100);
		
		String[] spots_arr = players.get_avaliable_spots();
		String avaliable_spots = "";
		
		for (int i = 0; i < spots_arr.length; i++)
			avaliable_spots += spots_arr[i] + ":";
		
		send_string(avaliable_spots); // Send the client the list of avaliable
										// spots
		
		int player_pos = Integer.valueOf(get_string()); // Find which spot they
														// want
		players.claim_spot(player_pos);

		String[] xy = spots_arr[player_pos].split(",");

		player.set_pos_x(Integer.valueOf(xy[0])); // Set the player's starting
												// position
		player.set_pos_y(Integer.valueOf(xy[1]));

		players.add_player(player); // Finally add the player to the game state

		GameState game_state = GameState.get_instance();
		
		while (game_state.is_running() == false)
			Thread.sleep(100); // Wait for the game to start

		while (game_state.is_running()) 
		{
			// Get desired player.direction from client
			String dir = get_string();
			
			// System.out.println("Setting player dir to "+dir);
			dir = dir.replaceAll("\\D+", "");
			
			int direction = Integer.valueOf(dir);
			
			players.set_player_dir(id, direction);

			
			// TODO: Send players x and ys
			
			
			
			Thread.sleep(100);
		}
	}
}