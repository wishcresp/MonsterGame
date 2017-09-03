
/*
 * With the combination of edges and nodes, we can
 * represent a really neat graph for the players and 
 * monster to work with.
 */

public class Graph 
{
	// Declare class variables
	private GraphVertex[] vertices;
	private int vertex_count;
	private GraphEdge[] edges;
	private int edge_count;

	/*
	 * Constructor will take in an array of Edges and
	 * build the graph by building corresponding vertices.
	 */
	public Graph(GraphEdge[] edges)
	{
		this.edges = edges;
		
		//this.vertex_count = 
	}
	
	private int calculate_vertex_count()
	{
		return 5;
		
	}
	
	
	
	
}
