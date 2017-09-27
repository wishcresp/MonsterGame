public abstract class Entity
{
	/* 
	 * Base abstract entity class
	 * 
	 * Monster and Player classes extend this class
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

	private int pos_x, pos_y; // I renamed it from x,y.
	
	
	// private TileSprite;
	/* Sean I imagine you'll be needing something like ^^ */
		
	public Entity()
	{
	
		
		direction = 0;
		desired_direction = 0;
		
	}
	
	// not sure if it should be static
	public void move() 
	{
		// JUST A SAMPLE
		String direction = "LEFT";


		// DEBUG, find out the current player coordinate
		System.out.println(player1.get_pos_x() + player1.get_pos_y());

		switch (direction) 
		{
		// (x - 1, y)
		case "UP":
			if (check_move(player1.get_pos_x() - 1, player1.get_pos_y()))
				// Update the player position if valid
				player1.set_pos_x(player1.get_pos_x() - 1);
			break;

		// (x + 1, y)
		case "DOWN":
			if (check_move(player1.get_pos_x() + 1, player1.get_pos_y()))
				// Update the player position if valid
				player1.set_pos_x(player1.get_pos_x() + 1);
			break;

		// (x, y - 1)
		case "LEFT":
			if (check_move(player1.get_pos_x(), player1.get_pos_y() - 1))
				// Update the player position if valid
				player1.set_pos_y(player1.get_pos_y() - 1);
			break;

		// (x, y + 1)
		case "RIGHT":
			if (check_move(player1.get_pos_x(), player1.get_pos_y() + 1))
				// Update the player position if valid
				player1.set_pos_y(player1.get_pos_y() + 1);
			break;

		default:
			System.out.println("INVALID KEY WAS PRESSED");
		}

		// DEBUG, find out the current player coordinate
		System.out.println(player1.get_pos_x() + player1.get_pos_y());

	}

	// shouldnt be static but oh well, we will need to move this elsewhere
	public boolean check_move(int x, int y) 
	{
		/*
		 * Maybe we can check to see if this coordinate exists/valid e.g. By moving from
		 * coordinate point (5,1) -> (5,9) via teleport Check to see if 5,9 exists as a
		 * coordinate, and if the player can move there. Meaning there is no player
		 * currently occupying that spot
		 */

		return true;
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
	
	
	
	
	

	public int get_pos_x()
	{
		return this.pos_x;
	}
	
	public void set_pos_x(int pos_x)
	{
		this.pos_x = pos_x;
	}
	
	public int get_pos_y()
	{
		return this.pos_y;
	}
	
	public void set_pos_y(int pos_y)
	{
		this.pos_y = pos_y;
	}

}
