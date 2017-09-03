
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
		
		// Create vertices ready to be updated with the edges
		this.vertex_count = calculate_vertex_count(edges);
		this.vertices = new GraphVertex[this.vertex_count];
		
		for (int n = 0; n < this.vertex_count; n++)
			this.vertices[n] = new GraphVertex();
		
		// Add all edges to the vertices, each edge gets two nodes
		// (to and from) TODO figure out 4 connections 
		this.vertex_count = edges.length;
		
		for (int x = 0; x < this.edge_count; x++)
		{
			this.vertices[edges[x].get_from_vertex()].get_edges().add(edges[x]);
			this.vertices[edges[x].get_to_vertex()].get_edges().add(edges[x]);			
		}
	}
	
	private int calculate_vertex_count(GraphEdge[] edges)
	{
		// setup counter, TODO do i really need this to be global?
		vertex_count = 0;
		
		// Loop through each edge in the array
		for (GraphEdge edge : edges) 
		{
			
			if (edge.get_to_vertex() > vertex_count)
				vertex_count = edge.get_to_vertex();
			
			if (edge.get_from_vertex() > vertex_count)
				vertex_count = edge.get_from_vertex();			
		}
		
		vertex_count++;
		
		return vertex_count;
	}
	
	// TODO get that djikstra, im tired so its sleep time
	
		
}