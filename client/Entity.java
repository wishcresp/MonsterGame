public abstract class Entity
{
	/* 
	 * Base entity class
	 * A Monster and Player object should be 
	 */
	
	
	private int direction;
	private int desired_direction;
	public int x, y;
	
	//Private Tile Sprite;
	public Entity()
	{
		direction = 0;
		desired_direction = 0;
		x = 2;
		y = 2;
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