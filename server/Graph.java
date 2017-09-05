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
	private GraphVertex[] vertex_array;	
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
		
	/*
	 * **VVVVV** THIS IS WHERE I IMPLEMENT THE ALGORITHM **VVVVV**
	 */	
	public void find_shortest_path()
	{
		// set vertex 0 as source TODO make this dynamic with monster position
		this.vertex_array[0].set_distance_from_source(0);
		
		// Pointer that focuses on a specific vertex in the array
		int next_vertex = 0;
		
		// visit every single vertex in the array
		for (int i = 0; i < this.vertex_array.length; i++)
		{
			// Loop around edges of current vertex
			// Create array list which stores the amount of edges of the vertex
			ArrayList<GraphEdge> current_vertex_edges = this.vertex_array[next_vertex].get_edges();
			
			// Loop through all available edges on the current vertex
			for (int edge_link = 0; edge_link < current_vertex_edges.size(); edge_link++)
			{
				// Store the index of the attached neighbouring vertex
				int neighbour = current_vertex_edges.get(edge_link).find_neighbour(next_vertex);
				
				// If this vertex has been unsettled, begin checking it
				if (!this.vertex_array[neighbour].is_settled())
				{
					// Figure out how far this index is from the source node
					int unchecked_distance = this.vertex_array[next_vertex].get_distance_from_source() + 1;
					
					// If the unchecked distance is less than the previous distance 
					if (unchecked_distance < vertex_array[neighbour].get_distance_from_source())	
						// Update the distance from the source 
						vertex_array[neighbour].set_distance_from_source(unchecked_distance);					
				}				
			}
			
			// All neighbours have been checked so that means this vertex has been settled
			vertex_array[next_vertex].set_settled(true);
			
			// Find the next vertex with the shortest distance
			next_vertex = get_shortest_distance();
		}		
	}
	
	private int get_shortest_distance()
	{
		// Declare function variables
		int temp_vertex = 0;
		int temp_distance = Integer.MAX_VALUE;
		
		// Check each vertex
		for (int i = 0; i < this.vertex_array.length; i++)
		{
			// Hold the value of the current vertices distance from the source
			int current_distance = this.vertex_array[i].get_distance_from_source();
			
			// If the current vertex is not settled and its distance is so far the lowest
			if (!this.vertex_array[i].is_settled() && current_distance < temp_distance)
			{
				// Set the new lowest distance value
				temp_distance = current_distance;
				// Store this vertex as a reference
				temp_vertex = i;
			}			
		}
		// Return the vertex with the smallest value
		return temp_vertex;		
	}
	
	// Display the result, i need to see if this works
	public void print_result()
	{
		String output = "Number of vertices = " + this.no_of_vertices;
		output += "\nNumber of edges = " + this.no_of_edges;
		
		for (int i = 0; i < this.vertex_array.length; i++)			
			 output += ("\nThe shortest distance from vertex 0 to vertex " + i + " is " + vertex_array[i].get_distance_from_source());
		
		// Print all the details
		System.out.println(output);			
	}	
	
	// ACCESSORS AND MUTATORS //
	public GraphVertex[] get_vertex_array()
	{
		return vertex_array;
	}	
	public int get_no_of_vertices() 
	{
		return no_of_vertices;
	}	
	public GraphEdge[] get_edge_array() 
	{
		return edge_array;
	}	
	public int get_no_of_edges() 
	{
		return no_of_edges;
	}
}