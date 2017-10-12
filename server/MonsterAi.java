
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

/*
 * Each time the algorithm makes its way towards the target, build a
 * path for each node as it moves towards it. For example, from source node (0)
 * to target (2) node (0) should have a ["", distance = 0] node (1) should have
 * a ["1", distance = 1] node (2) should have a ["1", distance = 2]
 * 
 * When the node is unsettled, the new path will be applied to it. If the node
 * is settled, then you cannot change the path on it I need to investigate the
 * GraphVertex class for this
 */

public class MonsterAi  
{
	// This is where the monster node is located
	public GameState gamestate = GameState.get_instance();
	public GraphVertex[] vertex_array;
	private int monster_position;	
	private Graph graph;

	// constructor for the MonsterAi
	public MonsterAi(GraphEdge[] edge_array) 
	{
		// get the board specifications ready
		this.graph = new Graph(edge_array);

		this.vertex_array = graph.get_vertex_array();

		// This is the center of the board, the starting position is 22 (5,5)
		this.monster_position = 22;
	}

	// monster_position accessor
	public int get_monster_position() 
	{
		return monster_position;
	}

	// monster_position mutator
	public void set_monster_position(int monster_position) 
	{
		this.monster_position = monster_position;
	}

	/*
	 * This is where Djikstra's algorithm is implemented to calculate all the
	 * distances between the source node and the rest of the nodes in the graph It
	 * will scan the whole board so that the monster can figure out which entity is
	 * the closest to it.
	 */
	public void calculate_shortest_path() 
	{
		// All the nodes need to be reset to be used again
		for (int x = 0; x <= 44; x++) 
		{
			this.vertex_array[x].set_monster_path(-1);
			this.vertex_array[x].set_settled(false);
			this.vertex_array[x].set_distance_from_source(Integer.MAX_VALUE);
		}

		// The source node will have a distance of 0
		this.vertex_array[monster_position].set_distance_from_source(0);

		// Set the source vertex to the monster position
		int current_vertex = monster_position;

		// visit every single vertex in the array (starting with the source)
		for (int i = 0; i < this.vertex_array.length; i++) 
		{
			// Loop around edges of current vertex to find connected edges to vertex
			ArrayList<GraphEdge> amount_of_edges = this.vertex_array[current_vertex].get_edges();

			// Investigate all the connected vertices to current vertex via edge connections
			for (int x = 0; x < amount_of_edges.size(); x++) 
			{
				// Which index does the neighbour belongs to (from_vertex/to_vertex)
				int neighbour = amount_of_edges.get(x).find_neighbour(current_vertex);

				// Investigate the vertex if it is unsettled
				if (!this.vertex_array[neighbour].is_settled()) 
				{
					// Find distance of current vertex from the source
					int unchecked_distance = this.vertex_array[current_vertex].get_distance_from_source() + 1;

					// If the current vertex distance is shorter than the existing distance
					if (unchecked_distance < vertex_array[neighbour].get_distance_from_source()) 
					{
						// check if the monster is on top of the closest player
						if (vertex_array[neighbour].get_monster_path() == -1) 
						{
							// If the current vertex matches the source then fix the position to this spot
							if (current_vertex == monster_position)
								this.vertex_array[neighbour].set_monster_path(neighbour);

							// Otherwise the this node will contain the directional node to move to
							else
								this.vertex_array[neighbour]
										.set_monster_path(this.vertex_array[current_vertex].get_monster_path());
						}

						// Update the distance from the source with the shortest path of this node
						vertex_array[neighbour].set_distance_from_source(unchecked_distance);
					}
				}
			}

			// All neighbours have been checked, this vertex has been settled
			vertex_array[current_vertex].set_settled(true);

			// Get the next vertex to check via the shortest distance
			current_vertex = get_shortest_distance();
		}
	}

	private int get_shortest_distance() 
	{
		// These variables will retain the values of the shortest node
		int temp_vertex = 0;
		int temp_distance = Integer.MAX_VALUE;

		// Check each vertex
		for (int i = 0; i < this.vertex_array.length; i++) 
		{
			// Hold the value of the current vertex distance from the source
			int current_distance = this.vertex_array[i].get_distance_from_source();

			// If current vertex is not settled and distance is so far the lowest
			if (!this.vertex_array[i].is_settled() && current_distance < temp_distance) 
			{
				// Set the new lowest distance value
				temp_distance = current_distance;

				// Save the index of this vertex
				temp_vertex = i;
			}
		}
		// Return the vertex with the smallest value
		return temp_vertex;
	}
}