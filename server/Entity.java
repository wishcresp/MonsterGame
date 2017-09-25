public abstract class Entity
{
	/* 
	 * Base abstract entity class
	 * 
	 * Monster and Player classes extend this class
	 * 
	 * 
	 * A quick note on desired direction:
	 * The direction a player is actually going is `direction`,
	 * and that should be used for calculating where they will be moving
	 * etc.
	 * `desired_direction` is the direction the player has indicated
	 * they want to be moving, but as the game uses pacman style gameplay
	 * if a wall happens to be in the way and they cannot turn, then their
	 * `desired_direction` and actual `direction` will be different, and each
	 * tick their position and `desired_direction` needs to be checked to see if
	 * their `direction` can be updated so they can start moving in their
	 * `desired_direction`
	 */
	
	private int direction;
	private int desired_direction;
	public int pos_x, pos_y; // I renamed it from x,y.
	private String name;
	
	
	// private TileSprite;
	/* Sean I imagine you'll be needing something like ^^ */
		
	public Entity()
	{
		direction = 0;
		desired_direction = 0;
		pos_x = 0; // These need to be set elsewhere
		pos_y = 0;
	}
	
	public String get_name()
	{
		return this.name;
	}
	public void set_name(String name)
	{
		this.name = name;
	}
	
	public int get_dir()
	{
		return direction;
	}
	
	public void set_dir(int dir)
	{
		this.direction = dir;
	}
	
	public int get_ddir()
	{
		return desired_direction;
	}
	
	public void set_ddir(int ddir)
	{
		this.desired_direction = ddir;
	}

}