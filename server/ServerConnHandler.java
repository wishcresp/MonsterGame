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
		boolean player_joined = false;
		try 
		{
			
			int new_player_target = 0;
		
			// Send game info to client
			send_string(Integer.toString(board.get_dimensions())); // Board
																	// dimensions
			Thread.sleep(100);
			send_string(board.get_layout()); // Board layout
			Thread.sleep(100);
			send_string(Integer.toString(this.id)); // The client's ID
			System.out.println("Sent the players ID");
	
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
			else
			{
					// Otherwise send over the player target
					send_string(String.valueOf(players.get_player_target()));
			}
			Thread.sleep(100);
	
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
			
			String player_pos = get_string(); // Find which spot they
															// want
			/*String
			
			players.claim_spot(player_pos);  //TODO: FIX LATER
	
			String[] xy = spots_arr[player_pos].split(",");*/
			String[] xy = player_pos.split(",");
	
			player.set_pos_x(Integer.valueOf(xy[0])); // Set the player's starting
													// position
			player.set_pos_y(Integer.valueOf(xy[1]));
	
			players.add_player(player); // Finally add the player to the game state
			players.set_player_count(players.get_player_count()+1);
			System.out.println("Added player");
			player_joined = true;
			
	
			GameState game_state = GameState.get_instance();
			
			if (players.get_player_target() == this.id+1)
			{
				System.out.println("Last player just joined, let's go!");
				game_state.change_run_state(true);
			}
			else
			{
				System.out.println("Waiting for other players to join to start the game");
				while (game_state.is_running() == false)
					Thread.sleep(100); // Wait for the game to start
			}
			while (game_state.is_running())
			{
				System.out.println("Sending data for client " + this.id);
				// Get desired player.direction from client
				String dir = "";
				dir = get_string();
				
				// System.out.println("Setting player dir to "+dir);
				dir = dir.replaceAll("\\D+", "");
				int direction = Integer.valueOf(dir);
				players.set_player_ddir(id, direction);
	
				
				// Check if player has won
				//if (players.get_player_count() == 1 && !player.is_dead())
				if (players.get_player_count() == 0 && !player.is_dead()) // Disabled for debugging
				{
					send_string("WINRAR");
					System.out.println("WINRAR!!!!!!!!!!!!!!!");
				}
					
				
				
				
				// TODO: Send players x and ys
				String out = "";
				for (int i = 0; i < players.get_player_count(); i++)
				{
					Player cur = (Player) players.get_player(i);
					out += String.valueOf(cur.get_pos_y())+","+String.valueOf(cur.get_pos_x());
					out += ","+String.valueOf(cur.get_ddir());
					if (cur.is_dead() == true)
					{
						
					}
					out += ","+String.valueOf(i); // Player.id
					out += ":";
				}
				// Don't forget to add the monster ;)
				Entity cur = players.get_player(players.get_player_target());
				out += String.valueOf(cur.get_pos_y())+","+String.valueOf(cur.get_pos_x());
				out += ","+String.valueOf(cur.get_ddir());
				out += ","+String.valueOf(players.get_player_target()); // Player.id
				out += ":";
				
				send_string(out);
				System.out.println("Sent data for "+players.get_player_target()+" players");
				
				
				
				Thread.sleep(100);
			}
		}
		catch (IOException e)
		{
			if (player_joined)
			{
				System.out.println("Removing player from game");
				players.remove_player(this.id);
			}
			
			System.out.println("Client disconnected");
		}
	}
}