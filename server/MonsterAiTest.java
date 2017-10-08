import static org.junit.Assert.*;
import org.junit.Test;

/*
 * Michael Dao s3668300 6/09/17
 * This is the JUnit test for the Monster Ai. 
 * 
 * There will be a simple graph generated, where there should be 
 * 8 total vertices and 14 edges. 
 * 
 * From there on, the algorithm will start at the source node 0,
 * and will start measuring the shortest distance to all other 
 * nodes in the graph.
 */

public class MonsterAiTest {
	static Board board;
	static GameState gamestate;
	static Players players;
	static MonsterAi monster;

	@Test
	public void test_gameboard() 
	{
		//////////// CREATE PLAYERS //////////////
		/*
		 * Get instances to prevent static variables
		 */
		gamestate = GameState.get_instance();
		board = gamestate.get_board();
		board.create_associative_array();

		////////////////// DEMO PLAYERS ////////////////////
		// PLAYER 1
		Player Player1 = new Player();
		Player1.set_pos_x(1);
		Player1.set_pos_y(1);

		// PLAYER 2
		Player Player2 = new Player();
		Player2.set_pos_x(9);
		Player2.set_pos_y(4);

		// PLAYER 3
		Player Player3 = new Player();
		Player3.set_pos_x(5);
		Player3.set_pos_y(6);
		//Player3.kill(); // KILL THE 3rd player

		// PLAYER 4
		Player Player4 = new Player();
		Player4.set_pos_x(1);
		Player4.set_pos_y(9);

		/*
		 * Adding the mock up players that i have manually generated
		 */
		players = gamestate.get_players();
		players.set_player_target(4);
		players.create_players();

		players.add_player(Player1);
		players.set_player_count(players.get_player_count() + 1);

		players.add_player(Player2);
		players.set_player_count(players.get_player_count() + 1);

		players.add_player(Player3);
		players.set_player_count(players.get_player_count() + 1);

		players.add_player(Player4);
		players.set_player_count(players.get_player_count() + 1);

		//////////// TEST THE AI //////////////
		/*
		 * Setup the monster
		 */
		monster = board.build_monster_graph();
		monster.set_monster_position(22);
		Monster monster_entity;

		// LOOP IT
		for (int x = 1; x < 6; x++) 
		{
			System.out.println("\n////////////////////// RUNNING " + x + " ///////////////////////\n");
			
			for (int i = 0; i < players.get_player_count() + 1; i++) // Plus one for the monster
			{
				Entity player = players.get_player(i);

				if (player instanceof Monster) 
				{
					monster_entity = (Monster) player;
					monster_entity.move(players, monster);
				} 
				else
					player.move();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// @Test
	public void test_monster() {
		GraphEdge[] edges = { 
				new GraphEdge(0, 1), new GraphEdge(1, 4), new GraphEdge(0, 2), new GraphEdge(2, 3),
				new GraphEdge(3, 4)

				// new GraphEdge(1, 3), new GraphEdge(1, 4), new GraphEdge(1, 5), new
				// GraphEdge(2, 4),
				// new GraphEdge(3, 5), new GraphEdge(4, 5), new GraphEdge(4, 6), new
				// GraphEdge(4, 7),
				// new GraphEdge(5, 6), new GraphEdge(6, 7)
		};

		MonsterAi monster = new MonsterAi(edges);

		monster.set_monster_position(4);

		monster.calculate_shortest_path();

		monster.print_result(monster.get_monster_position());
	}
}