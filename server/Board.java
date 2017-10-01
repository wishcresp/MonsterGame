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

		Board.load_board("", dimensions, BoardTiles);
	}

	public void load_layout(String layout) 
	{
		int x = 0;
		for (String row : layout.split("\n")) 
		{

			int y = 0;
			for (String element : row.split(":")) 
			{
				BoardTiles[x][y].fromString(element);
				y++;
			}
			x++;
		}
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
	
	////////////// THIS IS WHERE I BUILD THE BOARD /////////////
	
	/*
	 * This is the board builder, it is where i will
	 * initialize and hard code the coordinates of the board
	 * for the players and monsters to move on.
	 */
	
	// create a java int array
 int[][] board_array = new int[dimensions][dimensions];
		
	/*
	 * This is important for translating coordinates to their corresponding node
	 * number.
	 */
	public void create_board_array() 
	{		
		// Initialize all spaces to -1 
		for (int x = 0; x < board_array.length; x++)
			for (int y = 0; y < board_array.length; y++)
				board_array[x][y] = -1;
		
		// Assign elements to the array
		/*
		 * Row 1
		 */
		board_array[1][1] = 0;
		board_array[1][2] = 1;
		board_array[1][3] = 2;
		board_array[1][4] = 3;
		board_array[1][5] = 4;
		board_array[1][6] = 5;
		board_array[1][7] = 6;
		board_array[1][8] = 7;
		board_array[1][9] = 8;

		/*
		 * Rows 2 - 4
		 */
		board_array[2][1] = 9;
		board_array[2][5] = 10;
		board_array[2][9] = 11;

		board_array[3][1] = 12;
		board_array[3][5] = 13;
		board_array[3][9] = 14;

		board_array[4][1] = 15;
		board_array[4][5] = 16;
		board_array[4][9] = 17;

		/*
		 * Row 5
		 */
		board_array[5][1] = 18;
		board_array[5][2] = 19;
		board_array[5][3] = 20;
		board_array[5][4] = 21;
		board_array[5][5] = 22;
		board_array[5][6] = 23;
		board_array[5][7] = 24;
		board_array[5][8] = 25;
		board_array[5][9] = 26;

		/*
		 * Rows 6 - 8
		 */
		board_array[6][1] = 27;
		board_array[6][5] = 28;
		board_array[6][9] = 29;

		board_array[7][1] = 30;
		board_array[7][5] = 31;
		board_array[7][9] = 32;

		board_array[8][1] = 33;
		board_array[8][5] = 34;
		board_array[8][9] = 35;

		/*
		 * Row 9
		 */
		board_array[9][1] = 36;
		board_array[9][2] = 37;
		board_array[9][3] = 38;
		board_array[9][4] = 39;
		board_array[9][5] = 40;
		board_array[9][6] = 41;
		board_array[9][7] = 42;
		board_array[9][8] = 43;
		board_array[9][9] = 44;

		System.out.println("");

		// print int array DEBUG
		/*for (int x = 0; x < board_array.length; x++)
		{
			for (int y = 0; y < board_array.length; y++)
				System.out.print("[" + board_array[x][y] + "]");
			System.out.println("");
		}*/
	}
	
	public  int[][] get_board_array()
	{
		return board_array;
	}

	public static void load_board(String gameboard, int dimensions, BoardTile[][] BoardTiles) 
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

	// Building a network for the board
	public void build_board_graph() 
	{
		/*
		 * GraphEdge(FROM, TO) To set a connection, specify the 'FROM' index and connect
		 * it to the 'TO' index. E.g. (Vertex 1 <--> Vertex 2) == (1, 2)
		 */

		// Change this value for the source node position
		int monster_position = 6;

		GraphEdge[] edges = 
			{
				/*
				 * ROWS
				 */
				// TOP ROW (1,1 - 1,9)
				new GraphEdge(0, 1), new GraphEdge(1, 2), new GraphEdge(2, 3), new GraphEdge(3, 4), new GraphEdge(4, 5),
				new GraphEdge(5, 6), new GraphEdge(6, 7), new GraphEdge(7, 8),

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
		/*monster.print_result(monster_position);*/
	}
	
}