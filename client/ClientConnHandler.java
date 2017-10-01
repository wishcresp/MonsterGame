import java.io.IOException;
import java.net.Socket;

public class ClientConnHandler extends ConnHandler
{

	public ClientConnHandler(Socket conn, int id) throws IOException 
	{
		super(conn, id);
	}

	@Override
	public void conn_work(Board board, Players players) throws InterruptedException 
	{
		int new_player_target = 0;
		GameState game_state = GameState.get_instance();
		
		
		// Send game info to client
		board.set_dimensions(Integer.valueOf(get_string())); // Board dimensions
		board.load_layout(get_string()); // Board layout
		this.id = Integer.valueOf(get_string()); // The client's ID
		game_state.players.set_pc_id(this.id);

		
		// TODO: Client interaction and player setup
		
		if (this.id == 0) // If we're the first player to connect
		{
			// Wait for the user to set the player target
			while (players.get_player_target() == -1);
			
			send_string(String.valueOf(players.get_player_target()));
		}
		
		// Create this player's object
		Entity player = players.get_player(this.id);
		
		// Set their name
		player.set_name(get_string());
		
		

		// Wait for list of avaliable spots then set it in gamestate
		game_state.set_avaliable_spots(get_string());
		
		
		// Send back the selected spot
		while (players.get_starter_spot() == "");
		send_string(players.get_starter_spot());
		

		while (game_state.is_running())
		{
			// Send our direction
			send_string(Integer.toString(players.get_player(players.get_pc_id()).get_ddir()));

			// TODO: Get player x and ys from server
			
		
			Thread.sleep(100);

		}	
	}

}
