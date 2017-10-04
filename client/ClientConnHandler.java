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
		System.out.println("Set board dimensions to "+board.get_dimensions());
		board.load_layout(get_string()); // Board layout
		System.out.println("Loaded gameboard");
		this.id = Integer.valueOf(get_string()); // The client's ID
		System.out.println("Got client's ID");
		game_state.players.set_pc_id(this.id);

		
		// TODO: Client interaction and player setup
		
		if (this.id == 0) // If we're the first player to connect
		{
			// Wait for the user to set the player target
			while (players.get_player_target() == -1);
			{
				try {
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
					System.out.println("There was an interupt while waiting for player target.");
					System.out.println(e.getMessage());
				}
			}
			
			send_string(String.valueOf(players.get_player_target()));
		}
		// Otherwise get the player target
		players.set_player_target(Integer.valueOf(get_string()));
		players.create_players(); 
	
		
		// Get this player's object
		Entity player = players.get_player(this.id);
			
		while (player.get_name() == "")
			Thread.sleep(100);
		
		send_string(player.get_name());
		

		// Wait for list of avaliable spots then set it in gamestate
		game_state.set_avaliable_spots(get_string());
		
		
		// Send back the selected spot
		while (players.get_starter_spot() == "");
		{
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				System.out.println("There was an interupt while waiting for starting spot.");
				System.out.println(e.getMessage());
			}
		}
		
		send_string(players.get_starter_spot());
		

		while (game_state.is_running())
		{
			// Send our direction
			send_string(Integer.toString(players.get_player(players.get_pc_id()).get_ddir()));


			String[] coords = get_string().split(":"); // TODO: Get player x and ys from server
			for (int i = 0; i < players.get_player_count(); i++)
			{
				String[] xy = coords[i].split(",");
				players.get_player(i).set_pos_x(Integer.valueOf(xy[0]));
				players.get_player(i).set_pos_y(Integer.valueOf(xy[1]));
				players.get_player(i).set_ddir(Integer.valueOf(xy[2]));
			}
			
		
			Thread.sleep(100);

		}	
	}

}
