import java.io.IOException;
import java.net.Socket;

public class ServerConnHandler extends ConnHandler 
{
	public ServerConnHandler(Socket conn, int id) throws IOException 
	{
		super(conn, id);
	}

	@Override
	public void conn_work(Board board, Players players) throws InterruptedException, IOException 
	{
		boolean player_joined = false;
		try 
		{
			int new_player_target = 0;
			GameState game_state = GameState.get_instance();
		
			// Send game info to client
			send_string(Integer.toString(board.get_dimensions())); // Board
																	// dimensions
			Thread.sleep(100); // Give the client some time to recieve so data doesn't get jumbled
			send_string(board.get_layout()); // Send the board layout
			Thread.sleep(100); // Give the client some time to recieve so data doesn't get jumbled
			// Send the clients ID and a random number
			send_string(Integer.toString(this.id)+":"+Integer.toString(game_state.get_random_number()));
			System.out.println("Sent the players ID "+this.id+" and random number "+String.valueOf(game_state.get_random_number()));
	
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
			{
				if (spots_arr[i] != "")
					avaliable_spots += spots_arr[i] + ":";
			}
			
			send_string(avaliable_spots); // Send the client the list of avaliable
											// spots
			
			String player_pos = get_string(); // Find which spot they
											  // want
			players.claim_spot(player_pos);
	
			String[] xy = player_pos.split(",");
	
			System.out.println("Claiming spot x:"+xy[0]+":"+xy[1]+":");
			System.out.flush();
			player.set_pos_x(Integer.valueOf(xy[0])); // Set the player's starting
													// position
			player.set_pos_y(Integer.valueOf(xy[1]));
	
			players.add_player(player); // Finally add the player to the game state
			players.set_player_count(players.get_player_count() + 1);
			players.set_alive_players(players.get_alive_players() + 1);
			System.out.println("Added player");
			player_joined = true;
			
	

			
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
				
				
				//System.out.println("Sending data for client " + this.id);
				// Get desired player.direction from client
				String dir = "";
				dir = get_string();
				
				// Wait for controller to perform calculations
				while (players.islocked())
					Thread.sleep(10);
				
				// System.out.println("Setting player dir to "+dir);
				dir = dir.replaceAll("\\D+", "");
				int direction = Integer.valueOf(dir);
				players.set_player_ddir(id, direction);
					
				// TODO: Send players x and ys
				String out = "";
				out = players.toString();
				
				// Wait around before we get into things
				while (players.islocked())
					Thread.sleep(10);
				// Check if player has won
				//if (players.get_alive_players() == 1 && !player.is_dead())
				if (players.get_alive_players() == 0 && (!player.is_dead() || true)) // Disabled for debugging
				{
					send_string("WINRAR");
					System.out.println("We have a winner!");
					System.exit(0);
				}
				
				send_string(out);
				//System.out.println("Sent data for "+players.get_player_target()+" players");
				
				if (player.is_dead())
					break; // Don't waste time sending data to the dead
				
				
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
		
		this.conn.close();
	}
}