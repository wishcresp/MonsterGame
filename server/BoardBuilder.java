
/*
 * This is the board builder class, it is where i will
 * initialize and hard code the coordinates of the board
 * for the players and monsters to move on.
 */
public class BoardBuilder 
{
	// create a java int array
	static int[][] intArray = new int[11][11];

	/*
	 * This is important for translating coordinates
	 * to their corresponding node number.
	 */
	public static void create_int_array() 
	{		
		// Initialize all spaces to -1 
		for (int x = 0; x < intArray.length; x++)
			for (int y = 0; y < intArray.length; y++)
				intArray[x][y] = -1;
		
		// Assign elements to the array
		/*
		 * Row 1
		 */
		intArray[1][1] = 0;
		intArray[1][2] = 1;
		intArray[1][3] = 2;
		intArray[1][4] = 3;
		intArray[1][5] = 4;
		intArray[1][6] = 5;
		intArray[1][7] = 6;
		intArray[1][8] = 7;
		intArray[1][9] = 8;

		/*
		 * Rows 2 - 4
		 */
		intArray[2][1] = 9;
		intArray[2][5] = 10;
		intArray[2][9] = 11;

		intArray[3][1] = 12;
		intArray[3][5] = 13;
		intArray[3][9] = 14;

		intArray[4][1] = 15;
		intArray[4][5] = 16;
		intArray[4][9] = 17;

		/*
		 * Row 5
		 */
		intArray[5][1] = 18;
		intArray[5][2] = 19;
		intArray[5][3] = 20;
		intArray[5][4] = 21;
		intArray[5][5] = 22;
		intArray[5][6] = 23;
		intArray[5][7] = 24;
		intArray[5][8] = 25;
		intArray[5][9] = 26;

		/*
		 * Rows 6 - 8
		 */
		intArray[6][1] = 27;
		intArray[6][5] = 28;
		intArray[6][9] = 29;

		intArray[7][1] = 30;
		intArray[7][5] = 31;
		intArray[7][9] = 32;

		intArray[8][1] = 33;
		intArray[8][5] = 34;
		intArray[8][9] = 35;

		/*
		 * Row 9
		 */
		intArray[9][1] = 36;
		intArray[9][2] = 37;
		intArray[9][3] = 38;
		intArray[9][4] = 39;
		intArray[9][5] = 40;
		intArray[9][6] = 41;
		intArray[9][7] = 42;
		intArray[9][8] = 43;
		intArray[9][9] = 44;

		// print int array DEBUG
		for (int x = 0; x < intArray.length; x++)
		{
			for (int y = 0; y < intArray.length; y++)
				System.out.print("[" + intArray[x][y] + "]");
			System.out.println("");
		}
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
	public static void build_board_graph() 
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
		monster.print_result(monster_position);
	}
}
