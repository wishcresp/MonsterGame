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
	private int dimensions = 32;	// Board dimension set to 11 x 11
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
		
		// Each array position will be instantiated
		/*
		 * This is a placeholder, the board will have to be generated some
		 * other way
		 */
		for (int x = 0; x < dimensions; x++)
			for (int y = 0; y < dimensions; y++)
				this.BoardTiles[x][y] = new BoardTile(null, false);	
	}
		

	public void load_board(String gameboard)
	{
		// TODO: Write code to load in the gameboard
		return;
	}
	
	public void set_tile(int x, int y, Entity ent)
	{
		BoardTiles[x][y].set_ent(ent);
	}

	// Check if a tile on the board is free
	public boolean is_free(int x, int y)
	{
		return BoardTiles[x][y].get_ent() == null;
	}

	// Customize the board dimensions
	public void set_dimensions(int dimensions)
	{
		this.dimensions = dimensions;
		return;
	}
	
	public int get_dimensions()
	{
		int dimensions = this.dimensions;
		return dimensions;
	}

	// Return a string copy of the game board, will be a 
	// representation for the game board in the back end.
	public String get_layout()
	{
		String out = "";
		
		for (int x = 0; x < dimensions; x++)
		{
			for (int y = 0; y < dimensions; y++)
				out += ":" + BoardTiles[x][y].toString();
			// New line
			System.out.println("");
		}

		// Return string game board
		return out;
	}
	public void load_layout(String layout)
	{
		this.create_board();
		int x = 0;
		for (String row: layout.split("\n"))
		{
			
			int y = 0;
			for (String element: row.split(":"))
			{
				if (x >= dimensions || y >= dimensions)
					continue;
				System.out.println("Setting cell "+x+","+y);
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
	

	public BoardTile get_tile(int x, int y)
	{
		if (x > 0 && y > 0 && x <= dimensions && y <= dimensions)
			return BoardTiles[x][y];
		else
			return null;
	}

}
