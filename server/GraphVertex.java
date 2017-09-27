import java.util.ArrayList;

/*
 * The graph consists of vertices in order for the AI to know 
 * where it is moving to. This will represent the many movement
 * tiles on the board, as they will all be connected to each other
 * like grapes on a vine. Player and monster pieces will be placed
 * on this network to find the shortest route possible with Dijkstra's
 * algorithm for the MonsterAI.
 * 
 * The graph consists of vertices which are all connected to each 
 * other via edges. This is crucial in order for the AI to work. 
 * 
 * Each vertex will represent a tile on the game board, it will be
 * represented with integers (0 - 1 - 2), but the player movement will
 * be referenced to coordinates (1,1 - 1,2 - 1,3). Will have to make
 * both values associated with each other (0 == 1,1).
 * 
 * The monster pieces will find the shortest route towards any player 
 * possible with Dijkstras algorithm within the MonsterAI.
 *  
 * source: 
 * http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html 
 * also looking at
 * https://medium.com/@ssaurel/calculate-shortest-paths-in-java-by-implementing-dijkstras-algorithm-5c1db06b6541
 */

public class GraphVertex 
{
	// Declare object variables
	private ArrayList<GraphEdge> edges = new ArrayList<GraphEdge>();
	private int distance_from_source = Integer.MAX_VALUE;
	private boolean settled;
	private String string_path;
	
	// get number of edges connected to this vertex
	public ArrayList<GraphEdge> get_edges() 
	{
		return edges;
	}
	
	// set the amount of edges connected to this vertex
	public void set_edges(ArrayList<GraphEdge> edge_count) 
	{
		this.edges = edge_count;
	}	
		
	// get the distance of this vertex from the source vertex
	public int get_distance_from_source()
	{
		return distance_from_source;
	}	
	
	// set the value of the distance of this vertex from the source
	public void set_distance_from_source(int distance_from_source)
	{
		this.distance_from_source = distance_from_source;
	}
	
	// Check whether vertex has been settled
	public boolean is_settled()
	{
		return settled;
	}
	
	// Set the boolean to make a vertex settled/unsettled	
	public void set_settled(boolean settled) 
	{
		this.settled = settled;
	}
	
	// Check whether vertex has been settled
	public String is_string_path() 
	{
		return string_path;
	}

	// Set the boolean to make a vertex settled/unsettled
	public void set_string_path(String string_path) 
	{
		this.string_path = string_path;
	}
	
	
}
