import static org.junit.Assert.*;

import org.junit.Test;

public class GameServerTest 
{

	// Declare class variables
	static int dim, player_target;
	static Board board;
	static Players players;
	static GameState gamestate;
	static Player player;
		
	@Test
	public void test_intialize() 
	{
		// Instantiate Board and players
		gamestate = GameState.get_instance();
		board = gamestate.get_board();
		players = gamestate.get_players();
		player = (Player) gamestate.get_player();

		// Amount of players to join
		player_target = players.get_player_target();

		// Generate the game board
		board.create_board();

		//fix this
		//board.create_board_array();
		//board.build_board_graph();
	}
}