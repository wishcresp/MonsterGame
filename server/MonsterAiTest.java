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
	int monster_position = 4;
	
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

		MonsterAi monster = new MonsterAi(edges, monster_position);
		monster.find_shortest_path();
		monster.print_result(monster_position);
	}

	@Test
	public void test_gameboard() 
	{
		// Change this value for the source node position
		int monster_position = 4;

		GraphEdge[] edges = 
			{
				/*
				 * ROWS
				 */
				// TOP ROW (1,1 - 1,9)
				new GraphEdge(0, 1), new GraphEdge(1, 2), new GraphEdge(2, 3), new GraphEdge(3, 4), new GraphEdge(4, 5),
				new GraphEdge(5, 6), new GraphEdge(6, 7), new GraphEdge(7, 8),

				// MIDDLE ROW (5,0 - 5,10)
				new GraphEdge(18, 19), new GraphEdge(19, 20), new GraphEdge(20, 21), new GraphEdge(21, 22),
				new GraphEdge(22, 23), new GraphEdge(23, 24), new GraphEdge(24, 25), new GraphEdge(25, 26),

				// BOTTOM ROW (9,1 - 9,9)
				new GraphEdge(36, 37), new GraphEdge(37, 38), new GraphEdge(38, 39), new GraphEdge(39, 40),
				new GraphEdge(40, 41), new GraphEdge(41, 42), new GraphEdge(42, 43), new GraphEdge(43, 44),

				/*
				 * COLUMNS
				 */
				// LEFT COLUMN (1,1 - 9,1)
				new GraphEdge(0, 9), new GraphEdge(9, 12), new GraphEdge(12, 15), new GraphEdge(15, 18),
				new GraphEdge(18, 27), new GraphEdge(27, 30), new GraphEdge(30, 33), new GraphEdge(33, 36),

				// MIDDLE COLUMN (0,5 - 10,5)
				new GraphEdge(4, 10), new GraphEdge(10, 13), new GraphEdge(13, 16), new GraphEdge(16, 22),
				new GraphEdge(22, 28), new GraphEdge(28, 31), new GraphEdge(31, 34), new GraphEdge(34, 40),

				// RIGHT COLUMN (1,9 - 9,9)
				new GraphEdge(8, 11), new GraphEdge(11, 14), new GraphEdge(14, 17), new GraphEdge(17, 26),
				new GraphEdge(26, 29), new GraphEdge(29, 32), new GraphEdge(32, 35), new GraphEdge(35, 44),

				// TELEPORTATION LINKS
				new GraphEdge(4, 40), new GraphEdge(18, 26) 
				};

		MonsterAi monster = new MonsterAi(edges, monster_position);
		monster.find_shortest_path();
		monster.print_result(monster_position); 
	}
}