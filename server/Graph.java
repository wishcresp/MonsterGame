import java.util.ArrayList;

/*
 * With the combination of edges and nodes, we can
 * represent a really neat graph for the players and 
 * monster to work with.
 */

public class Graph 
{
	// Vertex array and quantity
	// Manages the 'checked' boolean and distance from source
	private GraphVertex[] vertex_array;
	private int no_of_vertexes;
	
	// Edge array and quantity
	// Manages the connection between each vertex and its length
	private GraphEdge[] edge_array;
	private int no_of_edges;

	// variable accessors DEBUG
	public GraphVertex[] get_vertex_array()
	{
		return vertex_array;
	}	
	public int get_no_of_vertexes() {
		return no_of_vertexes;
	}	
	public GraphEdge[] get_edge_array() {
		return edge_array;
	}	
	public int get_no_of_edges() {
		return no_of_edges;
	}
	
	/*
	 * Constructor will take in an array of Edges and
	 * build the graph by building corresponding vertices.
	 */
	public Graph(GraphEdge[] edge_array)
	{
		// Pass parameter into edge array
		this.edge_array = edge_array;
		
		// Ready the vertex for edges when they update
		this.no_of_vertexes = calculate_no_of_vertexes(edge_array);
		
		// Create new array based on the amount of vertices there are
		// If there are 8 nodes, create a array of length 8 (0-7)
		this.vertex_array = new GraphVertex[this.no_of_vertexes];
		
		// All the arrays will start off as null from creation,
		// Loop through each and instantiate the vertex object 
		// for each array
		for (int x = 0; x < this.no_of_vertexes; x++)
			this.vertex_array[x] = new GraphVertex();

		// Sum up all the edges in the network
		this.no_of_edges = edge_array.length;
		
		// The index 'x' focuses on the current vertex by index
		// it is for the amount of edges to add
		for (int x = 0; x < this.no_of_edges; x++)
		{ 
			this.vertex_array[edge_array[x].get_from_vertex()].get_edges().add(edge_array[x]);
			this.vertex_array[edge_array[x].get_to_vertex()].get_edges().add(edge_array[x]);
		}
	}
		
	private int calculate_no_of_vertexes(GraphEdge[] edge_array)
	{
		// Initialize local quantity variable
		int no_of_vertexes = 0;
		
		// Loop through each object in the array
		for (GraphEdge edge : edge_array) 
		{
			// Find the highest vertex number
			// This case looks at "to vertex index"
			if (edge.get_to_vertex() > no_of_vertexes)
				no_of_vertexes = edge.get_to_vertex();
			
			// This case looks at "from vertex index"
			if (edge.get_from_vertex() > no_of_vertexes)
				no_of_vertexes = edge.get_from_vertex();			
		}
		
		// Add an additional vertex value
		no_of_vertexes++;
				
		// return the amount of vertexes
		return no_of_vertexes;
	}
		
	/*
	 * **VVVVV** THIS IS WHERE I IMPLEMENT THE ALGORITHM **VVVVV**
	 */	
	public void find_shortest_path()
	{
		// vertex 0 as source
		this.vertex_array[0].set_distance_from_source(0);
		int next_vertex = 0;
		
		// visit every vertex
		for (int i = 0; i < this.vertex_array.length; i++)
		{
			// Loop around edges of current vertex
			// Create arraylist which stores the edge count of the vertex
			ArrayList<GraphEdge> current_vertex_edges = this.vertex_array[next_vertex].get_edges();
			
			// Loop through all avaialable edges
			for (int edge_link = 0; edge_link < current_vertex_edges.size(); edge_link++)
			{
				// Store the index of the neighbour attached
				int neighbour = current_vertex_edges.get(edge_link).find_neighbour(next_vertex);
				
				// Check when vertex has not been visited/checked
				if (!this.vertex_array[neighbour].is_checked())
				{
					int unchecked = this.vertex_array[next_vertex].get_distance_from_source() + current_vertex_edges.get(edge_link).getLength();
					
					if (unchecked < vertex_array[neighbour].get_distance_from_source())					
						vertex_array[neighbour].set_distance_from_source(unchecked);					
				}				
			}
			
			// All neighbours have been visited
			// so that means this vertex has been checked
			vertex_array[next_vertex].set_checked(true);
			
			// Next node will be the one with the shortest distance
			// TODO this bit may be redundant to the program, but we shall see
			next_vertex = get_shortest_distance();
		}		
	}
	
	private int get_shortest_distance()
	{
		int temp_vertex = 0;
		int temp_distance = Integer.MAX_VALUE;
		
		for (int i = 0; i < this.vertex_array.length; i++)
		{
			int current_distance = this.vertex_array[i].get_distance_from_source();
			
			if (!this.vertex_array[i].is_checked() && current_distance < temp_distance)
			{
				temp_distance = current_distance;
				temp_vertex = i;
			}			
		}
		
		return temp_vertex;		
	}
	
	// Display the result, i need to see if this works
	public void print_result()
	{
		String output = "Number of vertices = " + this.no_of_vertexes;
		output += "\nNumber of edges = " + this.no_of_edges;
		
		for (int i = 0; i < this.vertex_array.length; i++)			
			 output += ("\nThe shortest distance from vertex 0 to vertex " + i + " is " + vertex_array[i].get_distance_from_source());
		
		// Print all the details
		System.out.println(output);			
	}		
}