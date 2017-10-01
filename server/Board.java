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

	static BoardBuilder boardBuilder;

	
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

		BoardBuilder.load_board("", dimensions, BoardTiles);
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
}