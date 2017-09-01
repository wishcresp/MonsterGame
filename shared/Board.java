import java.net.*;
import java.io.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Board
{
	private static Board board = new Board(); // Create new board object
	private int dimensions = 9;	// Board dimension set to 9 x 9
	private BoardTile[][] BoardTiles; // Board 2D array
	
	// Define board dimension and instantiate array
	//@Before
	public void create_board()
	{
		// Define board with the dimension 9 x 9
		this.BoardTiles = new BoardTile[dimensions][dimensions];
		
		// Each array position will be instantiated
		for (int x = 0; x < dimensions; x++)
			for (int y = 0; y < dimensions; y++)
				this.BoardTiles[x][y] = new BoardTile(null);
	}
	
	public void set_tile(int x, int y, Entity ent)
	{
		// TODO What does ent do
		BoardTiles[x][y].set_ent(ent);
	}

	// TODO What is_free? Is it when the tile is free??
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

	//@Test
	public int get_dimensions()
	{
		int dimensions = this.dimensions;
		assertEquals(11, dimensions);
		return dimensions;
	}

	public String get_layout()
	{
		String out = "";
		for (int x = 0; x < dimensions; x++)
			for (int y = 0; y < dimensions; y++)
				out += ":"+BoardTiles[x][y].toString();

		// TODO Is it returning the game board?
		return out;
	}

	public BoardTile get_tile(int x, int y)
	{
		if (x > 0 && y > 0 && x <= dimensions && y <= dimensions)
			return BoardTiles[x][y];
		else
			return null;
	}

	public static Board get_board_instance()
	{
		return board;
	}
}
