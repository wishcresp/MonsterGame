import java.net.*;
import java.io.*;

public class BoardTile
{
	Entity contains;
	
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