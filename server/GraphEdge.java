/*
 * The tutorial i am following is using weighted graphs
 * We are not using weighted graphs, so this may be 
 * redundant, however i still need to figure out what 
 * important bits i need to retract from this class 
 * to make the network work.
 * 
 * source material:  
 * https://medium.com/@ssaurel/calculate-shortest-paths-in-java-by-implementing-dijkstras-algorithm-5c1db06b6541
 * TODO figure out referencing for this stuff
 */

// Represent the edges in the network
public class GraphEdge {
	private int from_node, to_node;
	
	public GraphEdge(int from_node, int to_node)
	{
		this.from_node = from_node;
		this.to_node = to_node;
	}

	// from_node accessor
	public int getFromNode()
	{
		return from_node;
	}
	
	// to_node accessor
	public int getToNode()
	{
		return to_node;
	}
	
	// Determine neighbouring node of supplied node, based on
	// the two nodes connected by this edge
	public int findNeighbour(int neighbour)
	{
		if (this.from_node == neighbour)		
			return this.to_node;
		else
			return this.from_node;
	}	
}