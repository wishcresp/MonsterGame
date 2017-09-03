public class BoardTile
{
	/*
	 * Very basic BoardTile class, just contains an Entity
	 * which needs to be done away with completely
	 * Someone needs to design this class, instead of `Entity`
	 * it should be some kind of sprite
	 */
		
	Entity contains;
	Boolean boundary;
	
	public BoardTile(Entity ent, Boolean boundary)
	{
		this.contains = ent;
		this.boundary = boundary;
	}
	
	public void set_ent(Entity ent)
	{
		this.contains = ent;
	}

	public Entity get_ent()
	{
		return this.contains;
	}
	
	// false will set this as a movement tile
	public void set_wall(Boolean boundary)
	{
		this.boundary = boundary;
	}

	public String toString() 
	{
		if (this.boundary)
			return "0";
		else
			return "_";
	}
}