import java.net.*;
import java.io.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Board
{
	private static Board board = new Board();

	private int dimensions = 11;
	private BoardTile[][] BoardTiles;


	private Board()
	{
	}

	@Before
	public void create_board()
	{
		this.BoardTiles = new BoardTile[dimensions][dimensions];
		for (int x = 0; x < dimensions; x++)
			for (int y = 0; y < dimensions; y++)
				this.BoardTiles[x][y] = new BoardTile(null);
	}

	public void set_tile(int x, int y, Entity ent)
	{
		BoardTiles[x][y].set_ent(ent);
	}

	public boolean is_free(int x, int y)
	{
		return BoardTiles[x][y].get_ent() == null;
	}


	public void set_dimensions(int dimensions)
	{
		return;
		//this.dimensions = dimensions;
	}

	@Test
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

		return out;
	}

	public BoardTile get_tile(int x, int y)
	{
		if (x > 0 && y > 0 &&
		    x <= dimensions && y <= dimensions)
			return BoardTiles[x][y];
		else
			return null;
	}

	public static Board get_board_instance()
	{
		return board;
	}
}
