public abstract class Entity {
	/*
	 * Base abstract entity class
	 * 
	 * Monster and Player classes extend this class
	 * 
	 * A quick note on desired direction: The direction a player is actually going
	 * is `direction`, and that should be used for calculating where they will be
	 * moving etc. `desired_direction` is the direction the player has indicated
	 * they want to be moving, but as the game uses pacman style gameplay if a wall
	 * happens to be in the way and they cannot turn, then their `desired_direction`
	 * and actual `direction` will be different, and each tick their position and
	 * `desired_direction` needs to be checked to see if their `direction` can be
	 * updated so they can start moving in their `desired_direction`
	 */

	private int direction;
	private int desired_direction;

	private int pos_x, pos_y; // I renamed it from x,y.

	public Entity() 
	{
		/*
		 * 0 = LEFT 
		 * 1 = UP 
		 * 2 = RIGHT 
		 * 3 = DOWN
		 */
		direction = 0;
		desired_direction = 0;
		return;
	}

	public void move() 
	{
		String output;

		// DEBUG, find out the current player coordinate
		output = "\nPlayer coordinate before move: ";
		output += this.get_pos_x() + "," + this.get_pos_y();
		System.out.println(output);
		
		/*
		 * Get players direction, and apply the move
		 */
		switch (direction) 
		{		
		case 0: // (x, y - 1) LEFT
			if (check_move(this.get_pos_x(), this.get_pos_y() - 1))
				
				// Update the player position if valid
				this.set_pos_y(this.get_pos_y() - 1);
			break;
		
		case 1: // (x - 1, y) UP
			if (check_move(this.get_pos_x() - 1, this.get_pos_y()))
				// Update the player position if valid
				this.set_pos_x(this.get_pos_x() - 1);
			break;

		case 2: // (x, y + 1) RIGHT
			if (check_move(this.get_pos_x(), this.get_pos_y() + 1))
				// Update the player position if valid
				this.set_pos_y(this.get_pos_y() + 1);
			break;

		case 3: // (x + 1, y) DOWN
			if (check_move(this.get_pos_x() + 1, this.get_pos_y()))
				// Update the player position if valid
				this.set_pos_x(this.get_pos_x() + 1);
			break;
			
		default:
			System.out.println("invalid move");
		}

		// DEBUG, find out the current player coordinate
		output = "Player coordinate after move: ";
		output += this.get_pos_x() + "," + this.get_pos_y() + "\n";
		System.out.println(output);
	}

	public boolean check_move(int x, int y) 
	{
		/*
		 * Check to see if this coordinate is valid 
		 * e.g. When moving from (5,1) to (5,9) via teleport, 
		 * Check if (5,9) exists as a coordinate (not -1) and 
		 * does not contain a player.
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