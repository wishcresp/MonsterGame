import java.net.*;
import java.io.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


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
 */


public class Board
{
	private static Board board = new Board(); // Make this a singleton
	private Board() // Private initaliser
	{
		return;
	}
	
	
	private int dimensions = 11;	// Board dimension set to 11 x 11
	private BoardTile[][] BoardTiles; // Board 2D array
	
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
				this.BoardTiles[x][y] = new BoardTile(null);
	}
	
	
	public void set_tile(int x, int y, Entity ent)
	{
		// TODO What does ent do
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

	public String get_layout()
	{
		String out = "";
		for (int x = 0; x < dimensions; x++)
			for (int y = 0; y < dimensions; y++)
				out += ":"+BoardTiles[x][y].toString();

		// TODO Is it returning the game board?
		// Yeah it's returning a string copy of the game board
		// This is needed to synchronise what they look like between the client
		// and server
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
