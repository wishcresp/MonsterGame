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
	private int dimensions = 11; // Board dimension set to 11 x 11
	private BoardTile[][] BoardTiles; // Board 2D array

	// Instantiate a new board object
	public Board() 
	{
		return;
	}

	// Define board dimension and instantiate array
	public void create_board() 
	{
		// Define board with the dimensions `dimensions` x `dimensions`
		this.BoardTiles = new BoardTile[dimensions][dimensions];

		// Index for game board
		int x, y;

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

	
	public void load_layout(String layout)
	{
		int x = 0;
		for (String row: layout.split("\n"))
		{
			
			int y = 0;
			for (String element: row.split(":"))
			{
				BoardTiles[x][y].fromString(element);
				y++;
			}
			x++;
		}
		
	/*	for (int x = 0; x < dimensions; x++)
		{
			for (int y = 0; y < dimensions; y++)
				out += ":" + BoardTiles[x][y].toString();
			// New line
			System.out.println("");
		}*/

	}
	
	public void load_board(String gameboard) 
	{

		// Index for game board
		int x, y;

		// Draw the game board walls
		// 1,1 -> 1,9
		x = 1;
		for (y = 1; y < dimensions - 1; y++)
			BoardTiles[x][y].set_wall(false);

		// 5,1 -> 5,9
		x = 5;
		for (y = 1; y < dimensions - 1; y++)
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

		// 1,5 -> 9,5
		y = 5;
		for (x = 1; x < dimensions - 1; x++)
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

	// Building a network for the board
	public void build_board_graph() 
	{
		/*
		 * GraphEdge(FROM, TO) To set a connection, specify the 'FROM'
		 * index and connect it to the 'TO' index.				 
		 * E.g. (Vertex 1 <--> Vertex 2) == (1, 2)
		 */
		
		// Change this value for the source node position
		int monster_position = 6;

		
		GraphEdge[] edges = 
			{
				/*
				 * ROWS
				 */
				// TOP ROW (1,1 - 1,9)
				new GraphEdge(0, 1), new GraphEdge(1, 2), new GraphEdge(2, 3), new GraphEdge(3, 4), 
				new GraphEdge(4, 5), new GraphEdge(5, 6), new GraphEdge(6, 7), new GraphEdge(7, 8), 
				
				// MIDDLE ROW (5,0 - 5,10)
				new GraphEdge(18, 19), new GraphEdge(19, 20), new GraphEdge(20, 21), new GraphEdge(21, 22), 
				new GraphEdge(22, 23), new GraphEdge(23, 24), new GraphEdge(24, 25), new GraphEdge(25, 26), 

				// BOTTOM ROW (9,1 - 9,9)
				new GraphEdge(36, 37), new GraphEdge(37, 38), new GraphEdge(38, 39), new GraphEdge(39, 40), 
				new GraphEdge(40, 41), new GraphEdge(41, 42), new GraphEdge(42, 43), new GraphEdge(43, 44),

				/*
				 * COLUMNS
				 */
				// LEFT COLUMN (1,1 - 9,1)
				new GraphEdge(0, 9), new GraphEdge(9, 12), new GraphEdge(12, 15), new GraphEdge(15, 18),
				new GraphEdge(18, 27), new GraphEdge(27, 30), new GraphEdge(30, 33), new GraphEdge(33, 36),

				// MIDDLE COLUMN (0,5 - 10,5)
				new GraphEdge(4, 10), new GraphEdge(10, 13), new GraphEdge(13, 16), new GraphEdge(16, 22),
				new GraphEdge(22, 28), new GraphEdge(28, 31), new GraphEdge(31, 34), new GraphEdge(34, 40),
				

				// RIGHT COLUMN (1,9 - 9,9)
				new GraphEdge(8, 11), new GraphEdge(11, 14), new GraphEdge(14, 17), new GraphEdge(17, 26),
				new GraphEdge(26, 29), new GraphEdge(29, 32), new GraphEdge(32, 35), new GraphEdge(35, 44),
				
				// TELEPORTATION LINKS
				new GraphEdge(4, 40), new GraphEdge(18, 26)
		};

		MonsterAi monster = new MonsterAi(edges, monster_position);
		monster.find_shortest_path();
		monster.print_result(monster_position);
	}
}