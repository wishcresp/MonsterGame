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
	@Test
	public void test() 
	{
		GraphEdge[] edges = 
			{ 
					new GraphEdge(0, 2), new GraphEdge(0, 3), new GraphEdge(0, 4), new GraphEdge(0, 1),
					new GraphEdge(1, 3), new GraphEdge(1, 4), new GraphEdge(1, 5), new GraphEdge(2, 4), 
					new GraphEdge(3, 5), new GraphEdge(4, 5), new GraphEdge(4, 6), new GraphEdge(4, 7), 
					new GraphEdge(5, 6), new GraphEdge(6, 7) 
					};

		MonsterAi monster = new MonsterAi(edges);
		monster.find_shortest_path();
		monster.print_result();
	}
}
