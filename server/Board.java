/*
 * Notes on the design of this singleton:
 * 	What we need from this object:
 * 		Blaise:
 * 			I need a toString method to sync this between
 * 			the server and client
 * 
 * 	To get this off the ground I've got some basic BoardTile
 *  objects to work with, it's just an array of those
 *  
 *  Also an empty tile is set to null, there's probably a better
 *  way of handling that
 *  
 */

/*
 * This class will be responsible for the back end of the game
 * it does not display any data, instead it will represent 
 * the game board and objects
*/

public class Board
{
	// Declare class variables
	private static Board board = new Board(); // Make this a singleton
	private int dimensions = 11;	// Board dimension set to 11 x 11
	private BoardTile[][] BoardTiles; // Board 2D array	
	
	// Instantiate a new board object
	private Board() 
	{
		return;
	}
	
	// Define board dimension and instantiate array
	public void create_board()
	{
		// Define board with the dimensions `dimensions` x `dimensions`
		this.BoardTiles = new BoardTile[dimensions][dimensions];
		
		// Index for gameboard
		int x,y;
		
		// Initialize each array position on the board to NULL
		for (x = 0; x < dimensions; x++)
			for (y = 0; y < dimensions; y++)
				// Ent will be null and wall will be true at this pos
				this.BoardTiles[x][y] = new BoardTile(null, true);	
		this.load_board("");
		
		// TODO: Instead of storing the player's location
        // on the gameboard, simply store it as x and y
        // coordinates that correspond to positions
        // on the `BoardTiles` array	
	}		

	public void load_board(String gameboard)
	{
		// TODO: Write code to load in the game board from string
		
		// Index for game board
		int x,y;

		// Draw the game board movement tiles
		// Starting with the rows
		// 1,1 -> 1,9
		x = 1;
		for (y = 1; y < dimensions - 1; y++)
			BoardTiles[x][y].set_wall(false);
		
		// 5,0 -> 5,10
		x = 5;
		for (y = 0; y < dimensions; y++)
			BoardTiles[x][y].set_wall(false);
		
		// 9,1 -> 9,9
		x = 9;
		for (y = 1; y < dimensions - 1; y++)
			BoardTiles[x][y].set_wall(false);
		
		// Now for the columns
		// 1,1 -> 9,1
		y = 1;
		for (x = 1; x < dimensions - 1; x++)
			BoardTiles[x][y].set_wall(false);
		
		// 0,5 -> 10,5
		y = 5;
		for (x = 0; x < dimensions; x++)
			BoardTiles[x][y].set_wall(false);
		
		// 1,9 -> 9,9
		y = 9;
		for (x = 1; x < dimensions - 1; x++)
			BoardTiles[x][y].set_wall(false);
		
		return;
	}

	// Customize the board dimensions
	public void set_dimensions(int dimensions)
	{
		this.dimensions = dimensions;
		return;
	}	
	// Getter for board dimension
	public int get_dimensions()
	{
		int dimensions = this.dimensions;
		return dimensions;
	}

	// Return a strong copy of the game board, will be a 
	// representation for the game board in the back end.
	public String get_layout()
	{
		String out = "";
		
		for (int x = 0; x < dimensions; x++)
		{
			for (int y = 0; y < dimensions; y++)
				out += ":" + BoardTiles[x][y].toString();
			
			// Create a new line
			out += "\n";
		}
		
		// Return string game board
		return out;
	}

	// Returns a boardTile object
	public BoardTile get_tile(int x, int y)
	{
		if (x > 0 && y > 0 && x <= dimensions && y <= dimensions)
			return BoardTiles[x][y];
		else
			return null;
	}

	// Board instance
	public static Board get_board_instance()
	{
		return board;
	}
	
	// Building a network for the board
	public void build_board_graph()
	{
		GraphEdge[] edges = {
				// SAMPLE GRAPH, JUST DOING A DEBUG
				// GraphEdge(FROM, TO, Length
				new GraphEdge(0, 2), new GraphEdge(0, 3), new GraphEdge(0, 4),
				new GraphEdge(0, 1), new GraphEdge(1, 3), new GraphEdge(1, 4),
			    new GraphEdge(1, 5), new GraphEdge(2, 4), new GraphEdge(3, 5),
			    new GraphEdge(4, 5), new GraphEdge(4, 6), new GraphEdge(4, 7),
			    new GraphEdge(5, 6), new GraphEdge(6, 7)						
			};
		
	    Graph graph = new Graph(edges);
	    graph.find_shortest_path();
	    graph.print_result(); 		
	}
	
	
}
