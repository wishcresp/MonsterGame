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

	int monster_position;

	public MonsterAi(GraphEdge[] edge_array) 
	{
		super(edge_array);
		// This is the center of the board, the starting position is 22 (5,5)
		this.monster_position = 22;
	}

	// ACCESSORS AND MUTATORS //
	public int get_monster_position() 
	{
		return monster_position;
	}

	public void set_monster_position(int monster_position) 
	{
		this.monster_position = monster_position;
	}
	
	/*
	 * **VVVVV** THIS IS WHERE I IMPLEMENT THE ALGORITHM **VVVVV**
	 */
	public void calculate_shortest_path() 
	{
		
		
		// Set the source vertex to the monster position 
		int current_vertex = monster_position;
		
		
		// RESET ALL THE NODES
		for (int x = 0; x <= 44; x++)
		{
			
			this.vertex_array[x].set_monster_path(-1);
			this.vertex_array[x].set_settled(false);
			this.vertex_array[x].set_distance_from_source(Integer.MAX_VALUE);

		}
		
		
		this.vertex_array[monster_position].set_distance_from_source(0);
			
		// TODO STILL -1 before this line

		
		// visit every single vertex in the array (starting with the source)
		for (int i = 0; i < this.vertex_array.length; i++) 
		{
			// Loop around edges of current vertex to figure out which edges are connected to the vertex 
			ArrayList<GraphEdge> amount_of_edges = this.vertex_array[current_vertex].get_edges();

			// Investigate all the connected vertices to current vertex
			for (int x = 0; x < amount_of_edges.size(); x++) 
			{
				//  Figure out whether neighbour index belongs to [from] or [to] variable
				int neighbour = amount_of_edges.get(x).find_neighbour(current_vertex);

				// Investigate the unsettled vertex
				if (!this.vertex_array[neighbour].is_settled()) 
				{					
					// Find distance of this vertex from the source
					int unchecked_distance = this.vertex_array[current_vertex].get_distance_from_source() + 1;
					
					// If the distance is shorter than the existing distance
					if (unchecked_distance < vertex_array[neighbour].get_distance_from_source())
					{
						// TODO Set the initial direction next to the source
						// If the neighbour is (-1)
						/*
						 *  TODO for each time the algorithm makes its way towards the target, 
						 *  build a path for each node as it moves towards it.
						 *  For example, from source node (0) to target (2)
						 *  node (0) should have a ["", distance = 0] 
						 *  node (1) should have a ["1", distance = 1] 
						 *  node (2) should have a ["1", distance = 2] 
						 *  
						 *  When the node is unsettled, the new path will be applied to it.
						 *  If the node is settled, then you cannot change the path on it
						 *  I need to investigate the GraphVertex class for this
						 */
						
						// TODO THIS IS NOT LETTING SHIT UPDATE, set everything to -1 before it runs
						if (vertex_array[neighbour].get_monster_path() == -1)
						{
							// If the current vertex matches the source then fix the position
							if (current_vertex == monster_position)
								this.vertex_array[neighbour].set_monster_path(neighbour);
							else
								// Set the path to the previous node
								this.vertex_array[neighbour].set_monster_path(this.vertex_array[current_vertex].get_monster_path());
						}											
						// Update the distance from the source with the shorter path
						vertex_array[neighbour].set_distance_from_source(unchecked_distance);
					}
				}
			}

			// All neighbours have been checked so that means this vertex has been settled
			vertex_array[current_vertex].set_settled(true);
		
			// Find the next vertex with the shortest distance
			current_vertex = get_shortest_distance();
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