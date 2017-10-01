import java.util.ArrayList;

/*
 * This is my plan
 * Create a model / graph that consists of vertices and edges
 * with that, you will place the player and monster piece onto
 * different sections around the network. With this implemented,
 * there will be a possibility for Dijkstra's algorithm to work as it should
 * 
 * Using this link as a reference:
 * http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html 
 */

public class MonsterAi extends Graph
{
	/*
	 * Writing everything inside the graph class first, will move the algorithm
	 * into this class as per the lucid chart design
	 */
	
	int monster_position;

	public MonsterAi(GraphEdge[] edge_array, int monster_position) 
	{
		super(edge_array);
		this.monster_position = monster_position;
	}

	/*
	 * **VVVVV** THIS IS WHERE I IMPLEMENT THE ALGORITHM **VVVVV**
	 */
	public void find_shortest_path() 
	{
		// This handles the source node, e.g. setting 0 as monster position
		this.vertex_array[monster_position].set_distance_from_source(0);
		int next_vertex = monster_position;

		/*
		 *  TODO for each time the algorithm makes its way towards the target, 
		 *  build a path for each node as it moves towards it.
		 *  For example, from source node (0) to target (2)
		 *  node (0) should have a [0, distance = 0] 
		 *  node (1) should have a [0-1, distance = 1] 
		 *  node (2) should have a [0-1-2, distance = 2] 
		 *  
		 *  When the node is unsettled, the new path will be applied to it.
		 *  If the node is settled, then you cannot change the path on it
		 *  I need to investigate the GraphVertex class for this
		 */
		
		// Trying to create a string path
		String string_path = Integer.toString(monster_position) + ",";
		
		// visit every single vertex in the array
		for (int i = 0; i < this.vertex_array.length; i++) 
		{
			/*
			 * Loop around edges of current vertex and create array list which stores the
			 * amount of edges of the vertex
			 */
			ArrayList<GraphEdge> current_vertex_edges = this.vertex_array[next_vertex].get_edges();

			// Investigate all connected vertex to current vertex
			for (int edge_link = 0; edge_link < current_vertex_edges.size(); edge_link++) 
			{
				/*
				 *  Pull the index of the neighbour from the (from/to) neighbour node
				 */
				int neighbour = current_vertex_edges.get(edge_link).find_neighbour(next_vertex);

				// Check the unsettled node
				if (!this.vertex_array[neighbour].is_settled()) 
				{
					/*
					 *  Figure out how far this index is from the source node
					 */
					int unchecked_distance = this.vertex_array[next_vertex].get_distance_from_source() + 1;

					if (unchecked_distance < vertex_array[neighbour].get_distance_from_source())
					{
						
						vertex_array[neighbour].set_string_path(string_path);
						
						// Update the distance from the source if distance is less than previous distance
						vertex_array[neighbour].set_distance_from_source(unchecked_distance);
					}
				}
			}

			// All neighbours have been checked so that means this vertex has been settled
			vertex_array[next_vertex].set_settled(true);
			
			// Update the shortest string path
			vertex_array[next_vertex].set_string_path(string_path);

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

			// If the current vertex is not settled and its distance is so far
			// the lowest
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
}