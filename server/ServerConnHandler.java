import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
		send_string(Integer.toString(board.get_dimensions())); // Board dimensions
		send_string(board.get_layout()); // Board layout
		send_string(Integer.toString(this.id)); // The client's ID

		
		// TODO: Client interaction and player setup
		
		if (this.id == 0) // If this is the first player to connect
		{
			new_player_target = Integer.valueOf(get_string());
			
			if (new_player_target < 0 || new_player_target > players.get_max_players())
				new_player_target = players.get_max_players(); // Basic validation
			
			players.set_player_target(new_player_target); // Let them set the player target
		}
		
		// Create this player's object
		Player player = new Player();
		
		// Set their name
		player.set_name(get_string());
		
		
		// TODO: wait until player above them has picked
		// a spot
		
		players.get_avaliable_spots()
		send_string(""); // Send the client the list of avaliable spots 
		
		
		
		
		GameState game_state = GameState.get_instance();
		while (game_state.is_running() == false)
			Thread.sleep(100); // Wait for the game to start
		
		while (game_state.is_running())
		{
			// Get desired player.direction from client
			String dir = get_string();
			//System.out.println("Setting player dir to "+dir);
			dir = dir.replaceAll("\\D+","");
			int direction = Integer.valueOf(dir);
			players.set_player_dir(id, direction);

			

			Thread.sleep(100);

		}	
	}

}
