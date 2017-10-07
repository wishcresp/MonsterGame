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
	static MonsterAi monster;

	@Test
	public void test_intialize() 
	{
		// Instantiate Board and players
		gamestate = GameState.get_instance();
		board = gamestate.get_board();

		players = gamestate.get_players();

		// Create monster object
		monster = board.build_monster_graph();

		// Amount of players to join
		player_target = players.get_player_target();

		// Generate the game board
		board.create_board();
		board.create_associative_array();
		
		GameState game_state = GameState.get_instance();
		Players players = game_state.get_players();
		Board board = game_state.get_board();
		int start_position = 22;
			
		
		Entity monster_entity;
		
		players.set_player_target(1);
		
		players.create_players();
		
		// PLAYER 1
		Player Player1 = new Player();
		Player1.set_pos_x(1);
		Player1.set_pos_y(1);

		players.add_player(Player1);
		players.set_player_count(players.get_player_count()+1);
		
		// Set the AI position in the GRAPH
		monster.set_monster_position(start_position);
		
		// TODO Get instance of monster in the array of players
		for (int i = 0; i <= players.get_player_count(); i++)
		{
			Entity player = players.get_player(i);
			
			// If this is the monster
			if (player instanceof Monster) {
				monster_entity = player;
			}
		}
	}
}