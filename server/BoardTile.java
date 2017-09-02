import java.net.*;
import java.io.*;

public class BoardTile
{
	/*
	 * Very basic BoardTile class, just contains an Entity
	 * which should probably be done away with completely
	 */
	
	// TODO If the board cell is NULL, is it possible for the boundary to be NULL
	
	Entity contains;
	Boolean isWall;
	
	public BoardTile(Entity ent)
	{
		this.contains = ent;
	}

	public void set_ent(Entity ent)
	{
		this.contains = ent;
	}

	public Entity get_ent()
	{
		return this.contains;
	}

	public String toString()
	{
		if (contains == null)
			return "0";
		else
			// Position contains player entity
			return "1";
	}
}