/*
 * Edges are used to link two nodes in a graph. The tutorial
 * I am following will be designed for weighted networks. 
 * But this AI will need to identify the shortest path via
 * node count instead of length per edge.
 * source material:  
 * https://medium.com/@ssaurel/calculate-shortest-paths-in-java-by-implementing-dijkstras-algorithm-5c1db06b6541
 * TODO figure out referencing for this stuff
 */

// Represent the edges in the network
public class GraphEdge {
	private int from_vertex, to_vertex;
	
	
	  private int length;

	public GraphEdge(int from_vertex, int to_vertex,int length)
	{
		this.from_vertex = from_vertex;
		this.to_vertex = to_vertex;
		this.length = length;

	}

	// from_node accessor
	public int get_from_vertex()
	{
		return from_vertex;
	}
	
	// to_node accessor
	public int get_to_vertex()
	{
		return to_vertex;
	}
	
	public int getLength() {
		return length;
	}

	
	// Determine neighbouring node of supplied node, based on
	// the two nodes connected by this edge
	public int find_neighbour(int neighbour)
	{
		if (this.from_vertex == neighbour)		
			return this.to_vertex;
		else
			return this.from_vertex;
	}	
}