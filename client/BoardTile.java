public class BoardTile
{
	/*
	 * Very basic BoardTile class, just contains an Entity
	 * which needs to be done away with completely
	 * Someone needs to design this class, instead of `Entity`
	 * it should be some kind of sprite
	 */
		
	// TODO We can probably use entity for player and monster representation on a board
	
	public Entity contains;
	public Boolean wall;
	
	// Constructor for a single BoardTile
	public BoardTile(Entity ent, boolean wall)
	{
		this.contains = ent;
		this.wall = wall;
	}
	
	// Setter and getter for Entity object
	public void set_ent(Entity ent)
	{
		this.contains = ent;
	}
	public Entity get_ent()
	{
		return this.contains;
	}
	
	// a false boolean will set this boardTile as movement tile
	public void set_wall(Boolean boundary)
	{
		this.wall = boundary;
	}

	// If the BoardTile has a wall, print 0
	// else print a blank space
	public String toString() 
	{
		if (this.wall)
			return "_";
		else
			return "0";
	}
	public void fromString(String element) 
	{
		if (element == "_")
			this.wall = true;
		else
			this.wall = false;
	}
}
