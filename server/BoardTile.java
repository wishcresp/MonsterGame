import java.net.*;
import java.io.*;

public class BoardTile
{
	/*
	 * Very basic BoardTile class, just contains an Entity
	 * which needs to be done away with completely
	 * Someone needs to design this class, instead of `Entity`
	 * it should be some kind of sprite
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

	public String toString() // PLACEHOLDER
	{
		if (isWall == null)
			return "0";
		else
			return "1";
	}
}