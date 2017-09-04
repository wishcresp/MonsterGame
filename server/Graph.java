import java.util.ArrayList;

/*
 * With the combination of edges and nodes, we can
 * represent a really neat graph for the players and 
 * monster to work with.
 */

public class Graph 
{
	// Vertex array and quantity
	private GraphVertex[] vertex_array;
	private int no_of_vertexes;
	
	// Edge array and quantity
	private GraphEdge[] edge_array;
	private int no_of_edges;

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
		
		// Create new Vertex object, initialize with number of vertexes
		this.vertex_array = new GraphVertex[this.no_of_vertexes];
		
		for (int n = 0; n < this.no_of_vertexes; n++)
			this.vertex_array[n] = new GraphVertex();
		
		// Add all edges to the vertices, each edge gets two nodes
		// (to and from) TODO figure out 4 connections 
		this.no_of_vertexes = edge_array.length;
		
		for (int x = 0; x < this.no_of_edges; x++)
		{
			this.vertex_array[edge_array[x].get_from_vertex()].get_edges().add(edge_array[x]);
			this.vertex_array[edge_array[x].get_to_vertex()].get_edges().add(edge_array[x]);			
		}
	}
	
	private int calculate_no_of_vertexes(GraphEdge[] edge_array)
	{
		// Initialize local quantity variable
		no_of_vertexes = 0;
		
		// Loop through each object in the array
		for (GraphEdge edge : edge_array) 
		{
			// Update the vertex count by finding the highest to/from vertex index
			// This case looks at "to vertex index"
			if (edge.get_to_vertex() > no_of_vertexes)
				no_of_vertexes = edge.get_to_vertex();
			
			// This case looks at "from vertex index"
			if (edge.get_from_vertex() > no_of_vertexes)
				no_of_vertexes = edge.get_from_vertex();			
		}
		
		// Add an additional node
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
		this.vertex_array[0].set_distance(0);
		int next_vertex = 0;
		
		// visit every vertex
		for (int i = 0; i < this.vertex_array.length; i++)
		{
			// Loop around edges of current vertex
			ArrayList<GraphEdge> current_vertex_edges = this.vertex_array[next_vertex].get_edges();
			
			for (int edge_link = 0; edge_link < current_vertex_edges.size(); edge_link++)
			{
				int neighbour = current_vertex_edges.get(edge_link).find_neighbour(next_vertex);
				
				// Only if not visited/checked
				if (!this.vertex_array[neighbour].is_checked())
				{
					int unchecked = this.vertex_array[next_vertex].get_distance() + current_vertex_edges.get(edge_link).getLength();
					
					if (unchecked < vertex_array[neighbour].get_distance())					
						vertex_array[neighbour].set_distance(unchecked);
									
				}				
			}
			
			// All neighbours have been visited
			// so that means this vertex has been checked
			vertex_array[next_vertex].set_checked(true);
			
			// Next node will be the one with the shortest distance
			// TODO this bit will be redundant in the main code
			//       nextNode = getNodeShortestDistanced();

		}		
	}
	
	
	
	
	
	
		
}