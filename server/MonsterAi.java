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

public class MonsterAi extends Graph{
	/*
	 * Writing everything inside the graph class first, will move the algorithm
	 * into this class as per the lucid chart design
	 */


	public MonsterAi(GraphEdge[] edge_array) {
		super(edge_array);
	}

	/*
	 * **VVVVV** THIS IS WHERE I IMPLEMENT THE ALGORITHM **VVVVV**
	 */
	public void find_shortest_path() {
		// set vertex 0 as source TODO make this dynamic with monster position
		this.vertex_array[0].set_distance_from_source(0);

		// Pointer that focuses on a specific vertex in the array
		int next_vertex = 0;

		// visit every single vertex in the array
		for (int i = 0; i < this.vertex_array.length; i++) {
			// Loop around edges of current vertex
			// Create array list which stores the amount of edges of the vertex
			ArrayList<GraphEdge> current_vertex_edges = this.vertex_array[next_vertex].get_edges();

			// Loop through all available edges on the current vertex
			for (int edge_link = 0; edge_link < current_vertex_edges.size(); edge_link++) {
				// Store the index of the attached neighbouring vertex
				int neighbour = current_vertex_edges.get(edge_link).find_neighbour(next_vertex);

				// If this vertex has been unsettled, begin checking it
				if (!this.vertex_array[neighbour].is_settled()) {
					// Figure out how far this index is from the source node
					int unchecked_distance = this.vertex_array[next_vertex].get_distance_from_source() + 1;

					// If the unchecked distance is less than the previous
					// distance
					if (unchecked_distance < vertex_array[neighbour].get_distance_from_source())
						// Update the distance from the source
						vertex_array[neighbour].set_distance_from_source(unchecked_distance);
				}
			}

			// All neighbours have been checked so that means this vertex has
			// been settled
			vertex_array[next_vertex].set_settled(true);

			// Find the next vertex with the shortest distance
			next_vertex = get_shortest_distance();
		}
	}

	private int get_shortest_distance() {
		// Declare function variables
		int temp_vertex = 0;
		int temp_distance = Integer.MAX_VALUE;

		// Check each vertex
		for (int i = 0; i < this.vertex_array.length; i++) {
			// Hold the value of the current vertices distance from the source
			int current_distance = this.vertex_array[i].get_distance_from_source();

			// If the current vertex is not settled and its distance is so far
			// the lowest
			if (!this.vertex_array[i].is_settled() && current_distance < temp_distance) {
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
