/*
 * Edges are used to link two vertices in a graph.
 * 
 * So far this algorithm will account for weighted graphs, 
 * however this AI will only need to identify the shortest path 
 * via the least amount of nodes required in the path.
 * 
 * I will be making each edge connection equal to a single length,
 * although this algorithm can account for weighted networks. 
 * 
 * source material:   
 * https://medium.com/@ssaurel/calculate-shortest-paths-in-java-by-implementing-dijkstras-algorithm-5c1db06b6541
 */

// Represent the edges in the network
public class GraphEdge {
	private int from_vertex, to_vertex;
	private int length;

	/*
	 * Constructor for the Edge, will create a link between two vertex
	 */
	public GraphEdge(int from_vertex, int to_vertex)
	{
		this.from_vertex = from_vertex;
		this.to_vertex = to_vertex;
		
		// Default length size will be 1
		this.length = 1;
	}

	// get the starting vertex index
	public int get_from_vertex()
	{
		return from_vertex;
	}
	
	// get the ending vertex index
	public int get_to_vertex()
	{
		return to_vertex;
	}
	
	// get the length of the edge
	public int getLength() 
	{
		return length;
	}
	
	// Determine if neighbour is a connected vertex
	public int find_neighbour(int neighbour)
	{
		if (this.from_vertex == neighbour)		
			return this.to_vertex;
		else
			return this.from_vertex;
	}	
}