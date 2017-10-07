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

public class MonsterAiTest 
{	
	//@Test
	public void test_monster() 
	{
		GraphEdge[] edges = 
			{ 
					new GraphEdge(0, 1), new GraphEdge(1, 4),
					new GraphEdge(0, 2), new GraphEdge(2, 3), new GraphEdge(3, 4)
					
					//new GraphEdge(1, 3), new GraphEdge(1, 4), new GraphEdge(1, 5), new GraphEdge(2, 4), 
					//new GraphEdge(3, 5), new GraphEdge(4, 5), new GraphEdge(4, 6), new GraphEdge(4, 7), 
					//new GraphEdge(5, 6), new GraphEdge(6, 7) 
			};

		MonsterAi monster = new MonsterAi(edges);
		
		monster.set_monster_position(4); 

		monster.calculate_shortest_path();
		
		monster.print_result(monster.get_monster_position()); 
	}
	
	
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
		
		// Converts the values from the nodes to coordinates
		board.create_associative_array();
		
		////////////////// DEMO PLAYERS ////////////////////
		
		// PLAYER 1
		Player Player1 = new Player();
		Player1.set_pos_x(1);
		Player1.set_pos_y(1);

		/*
		 *  PLAYER 2 
		 *  should be same distance as player 3 only with different direction
		 */
		Player Player2 = new Player();
		Player2.set_pos_x(9);
		Player2.set_pos_y(4);
		
		/*
		 *  PLAYER 3 
		 *  should be same distance as player 2 only with different direction
		 */
		Player Player3 = new Player();
		Player3.set_pos_x(5);
		Player3.set_pos_y(9);
		

		// PLAYHER 4
		Player Player4 = new Player();
		Player4.set_pos_x(1);
		Player4.set_pos_y(9);
		
		//////////// TEST THE AI //////////////
		
		/*
		 * Setup the monster
		 */
		//monster = new MonsterAi(edges);
		monster = board.build_monster_graph();

		monster.set_monster_position(22); 
		monster.calculate_shortest_path();
		
		// trying to store all the distances
		int[] distance_array = new int[5];

		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// print coordinates of the players as nodes
		System.out.print("PLAYER 1");
		int player1_node = board.convert_to_node(Player1.get_pos_x(), Player1.get_pos_y());		
		int distance1 = monster.vertex_array[player1_node].get_distance_from_source();	
		int destination1 = monster.vertex_array[player1_node].get_monster_path();
				
		System.out.print("\nSource " + monster.get_monster_position() + " to vertex " + player1_node + " = " + distance1);
		System.out.println("\nDirection node = " + destination1 + "\n");	

		
		distance_array[1] = distance1;
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		System.out.print("PLAYER 2");
		int player2_node = board.convert_to_node(Player2.get_pos_x(), Player2.get_pos_y());
		int distance2 = monster.vertex_array[player2_node].get_distance_from_source();	
		int destination2 = monster.vertex_array[player2_node].get_monster_path();
				
		System.out.print("\nSource " + monster.get_monster_position() + " to vertex " + player2_node + " = " + distance2);
		System.out.println("\nDirection node = " + destination2 + "\n");	

		
		distance_array[2] = distance2;

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.print("PLAYER 3");
		int player3_node = board.convert_to_node(Player3.get_pos_x(), Player3.get_pos_y());
		int distance3 = monster.vertex_array[player3_node].get_distance_from_source();	
		int destination3 = monster.vertex_array[player3_node].get_monster_path();
				
		System.out.print("\nSource " + monster.get_monster_position() + " to vertex " + player3_node + " = " + distance3);
		System.out.println("\nDirection node = " + destination3 + "\n");	

		
		distance_array[3] = distance3;

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.print("PLAYER 4");
		int player4_node = board.convert_to_node(Player4.get_pos_x(), Player4.get_pos_y());
		int distance4 = monster.vertex_array[player4_node].get_distance_from_source();	
		int destination4 = monster.vertex_array[player4_node].get_monster_path();
		
		System.out.print("\nSource " + monster.get_monster_position() + " to vertex " + player4_node + " = " + distance4);
		System.out.println("\nDirection node = " + destination4 + "\n");	
		
		//monster.print_player_position(monster.get_monster_position(), player4_node);

		distance_array[4] = distance4;
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int smallest = Integer.MAX_VALUE;
        int smallest_player = 0;

		// Try to find the smallest value in the array
		for (int i = 1; i < distance_array.length; i++) 
		{
			if (smallest > distance_array[i]) 
			{ // TODO setup a randomizer if two players have the same path
				smallest = distance_array[i];
				smallest_player = i;
			}
		}

		System.out.println("Player " + smallest_player + " is the closest with a length of " + smallest);
		
		
		// TRYING TO CONVERT NODE TO COORDINATE
		// pretend player 2 is closest
		int[] coordinates = board.convert_to_coordinate(destination2);
		
		System.out.println("\nThe monster will move to node " + destination2 + " which has the coordinate " + coordinates[0] + "," + coordinates[1]);


		////////////////////// USING THE MONSTER CLASS ///////////////////////
		System.out.println("\n////////////////////// USING THE MONSTER CLASS ///////////////////////\n");
		/*
		 * This is the mockup players that i have manually generated
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
		
		Monster monster_entity;
		
		
		
		for (int i = 0; i < players.get_player_count() + 1; i++) // Plus one for the monster 
		{
			Entity player = players.get_player(i);
			
			if (player instanceof Monster)
			{
				monster_entity = (Monster) player;
				monster_entity.move(players, monster);
				monster_entity.move(players, monster);

			}
			else				
				player.move();			
		}
		
		
	}
}