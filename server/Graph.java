import java.util.ArrayList;

/*
 * With the combination of edges and nodes, we can create 
 * a graph network that will represent the string copy of 
 * the game board. Each movement tile in the game board will
 * be represented as a vertex, the direction and movement 
 * will be limited to the edge connection to other vertices.
 *
 * In this class, the Graph will be prepared with GraphEdge
 * and GraphVertex classes, a set of predefined object
 * instantiations will build the graph in the board class.
 * 
 * We take in an array of edges, and build the corresponding
 * vertices to build the graph.
 * 
 * source material:   
 * https://medium.com/@ssaurel/calculate-shortest-paths-in-java-by-implementing-dijkstras-algorithm-5c1db06b6541
 */

public class Graph 
{
	// GRAPH VARIABLES //
	/*
	 *  Vertex array manages the 'settled' boolean 
	 *  as well as the distance from the source vertex
	 *  
	 *  Number of vertexes will identify the quantity of nodes in the network
	 */
	protected GraphVertex[] vertex_array;	
	private int no_of_vertices;
	
	/*
	 * Manages the connection between each vertex and the length of each edge, 
	 * the default value will be set to 1 as this is not a weighted network.
	 * 
	 * Number of edges will identify the quantity of edges in the network
	 */
	private GraphEdge[] edge_array;		
	private int no_of_edges;
	
	/*
	 * Constructor will take in an array of Edges and
	 * build the graph by building corresponding vertices.
	 */
	public Graph(GraphEdge[] edge_array)
	{
		// Initialize edge array
		this.edge_array = edge_array;
		
		// Ready the vertices in anticipation of the edge creation
		this.no_of_vertices = calculate_no_of_vertices(edge_array);
		
		/*
		 * Create new array based on the amount of vertices there are.
		 * If there are 8 nodes, create an array of length 8 (0-7)
		 */				
		this.vertex_array = new GraphVertex[this.no_of_vertices];
		
		/*
		 * All the arrays will start off as null from creation,
		 * loop through each and instantiate each array
		 */		
		for (int x = 0; x < this.no_of_vertices; x++)
			this.vertex_array[x] = new GraphVertex();

		// Set value to quantity of edges in the array
		this.no_of_edges = edge_array.length;
		
		/*
		 * The index 'x' focuses on the current vertex,
		 * and represents the amount of edges to add
		 */
		for (int x = 0; x < this.no_of_edges; x++)
		{ 
			this.vertex_array[edge_array[x].get_from_vertex()].get_edges().add(edge_array[x]);
			this.vertex_array[edge_array[x].get_to_vertex()].get_edges().add(edge_array[x]);
		}
	}
		
	/*
	 * Figure out how many vertices there are based on the connections
	 */
	private int calculate_no_of_vertices(GraphEdge[] edge_array)
	{
		// Initialize function variable
		int no_of_vertexes = 0;
		
		// Loop through each 'edge' in the array
		for (GraphEdge edge : edge_array) 
		{
			/*
			 *  Find the highest vertex index in the network
			 */
			// Beginning with the "to vertex index"
			if (edge.get_to_vertex() > no_of_vertexes)
				no_of_vertexes = edge.get_to_vertex();
			
			// This case looks at "from vertex index"
			if (edge.get_from_vertex() > no_of_vertexes)
				no_of_vertexes = edge.get_from_vertex();			
		}
		
		// Add an additional value to compensate for the array ([0] start)
		no_of_vertexes++;
				
		// return the amount of vertexes
		return no_of_vertexes;
	}
	
	// DJIKSTRA OVER IN THIS SPOT //
	
	// Display the result, i need to see if this works
	public void print_result(int source_node)
	{
		String output = "";
		/*output = "\nNumber of vertices = " + this.no_of_vertices;
		output += "\nNumber of edges = " + this.no_of_edges + "\n";
		*/
		for (int i = 0; i < this.vertex_array.length; i++)		
		{
			output += ("\nDistance from vertex " + source_node + " to vertex " + i + " is " + vertex_array[i].get_distance_from_source());
			output += ("\nThe direction set to this node is " + vertex_array[i].get_monster_path() + "\n");
		}
		
		// Print all the details
		System.out.println(output);			
	}	
	
	// Display the result, i need to see if this works
	public void print_player_position(int source_node, int player_node) {
		String output = "";
		
		output += ("\nDistance from vertex " + source_node + " to vertex " + player_node + " is "
				+ vertex_array[player_node].get_distance_from_source());
		output += ("\nThe direction set to this node is " + vertex_array[player_node].get_monster_path() + "\n");

		// Print all the details
		System.out.println(output);
	}
	
	// ACCESSORS AND MUTATORS //
	public GraphVertex[] get_vertex_array() {
		return vertex_array;
	}

	public int get_no_of_vertices() {
		return no_of_vertices;
	}

	public GraphEdge[] get_edge_array() {
		return edge_array;
	}

	public int get_no_of_edges() {
		return no_of_edges;
	}
}