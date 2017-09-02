import java.net.*;
import java.io.*;

public class BoardTile
{
	/*
	 * Very basic BoardTile class, just contains an Entity
	 * which should probably be done away with completely
	 */
	
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
			return "1";
	}
}