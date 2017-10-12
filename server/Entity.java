public abstract class Entity 
{
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
	public static Board board;
	public static GameState game_state;
	
	private int direction;
	private int desired_direction;
	private int pos_x, pos_y; // I renamed it from x,y.
	private int dimensions = 11;

	public Entity() 
	{
		/*
		 * 0 = UP 1 = DOWN 2 = LEFT 3 = RIGHT
		 */
		direction = -1;
		desired_direction = -1;
		return;
	}

	public void move() 
	{
		game_state = GameState.get_instance();
		board = game_state.get_board();

		// Desired direction handling
		boolean moved = false;

		/*
		 * Get players direction, and apply the move
		 */
		switch (desired_direction) 
		{
		case 0: // UP
			if (check_move(this.get_pos_x() - 1, this.get_pos_y())) 
			{
				// Update the player position if valid
				this.set_pos_x(this.get_pos_x() - 1);

				// teleport to node 40
				if (this.get_pos_x() == 0)
					this.set_pos_x(9);

				moved = true;
			}
			break;
			
		case 1: //DOWN
			if (check_move(this.get_pos_x() + 1, this.get_pos_y())) 
			{
				// Update the player position if valid
				this.set_pos_x(this.get_pos_x() + 1);

				// teleport to node 4
				if (this.get_pos_x() == 10)
					this.set_pos_x(1);

				moved = true;
			}
			break;

		case 2: // LEFT
			if (check_move(this.get_pos_x(), this.get_pos_y() - 1)) 
			{
				// Update the player position if valid
				this.set_pos_y(this.get_pos_y() - 1);

				// Teleport to node 26
				if (this.get_pos_y() == 0)
					this.set_pos_y(9);

				moved = true;
			}
			break;

		case 3: // RIGHT
			if (check_move(this.get_pos_x(), this.get_pos_y() + 1)) 
			{
				// Update the player position if valid
				this.set_pos_y(this.get_pos_y() + 1);

				// Teleport to node 18
				if (this.get_pos_y() == 10)
					this.set_pos_y(1);

				moved = true;
			}
			break;

		default:
		}

		if (moved)
			direction = desired_direction;
		
		else 
		{
			switch (direction) 
			{
			case 0: // UP
				if (check_move(this.get_pos_x() - 1, this.get_pos_y())) 
				{
					// Update the player position if valid
					this.set_pos_x(this.get_pos_x() - 1);

					// teleport to node 40
					if (this.get_pos_x() == 0)
						this.set_pos_x(9);
				}
				break;
				
			case 1: // DOWN
				if (check_move(this.get_pos_x() + 1, this.get_pos_y())) 
				{
					// Update the player position if valid
					this.set_pos_x(this.get_pos_x() + 1);

					// teleport to node 4
					if (this.get_pos_x() == 10)
						this.set_pos_x(1);
				}
				break;

			case 2: // LEFT
				if (check_move(this.get_pos_x(), this.get_pos_y() - 1)) 
				{
					// Update the player position if valid
					this.set_pos_y(this.get_pos_y() - 1);

					// Teleport to node 26
					if (this.get_pos_y() == 0)
						this.set_pos_y(9);
				}
				break;

			case 3: // RIGHT
				if (check_move(this.get_pos_x(), this.get_pos_y() + 1))
				{
					// Update the player position if valid
					this.set_pos_y(this.get_pos_y() + 1);

					// Teleport to node 18
					if (this.get_pos_y() == 10)
						this.set_pos_y(1);
				}
				break;

			default:
			}
		}
	}

	public boolean check_move(int x, int y) 
	{
		/*
		 * Check to see if this coordinate is valid e.g. When moving from (5,1) to (5,9)
		 * via teleport, Check if (5,9) exists as a coordinate (not -1) and does not
		 * contain a player.
		 */
		game_state = GameState.get_instance();
		board = game_state.get_board();

		int[][] board_array = new int[dimensions][dimensions];

		board_array = board.get_board_array();

		// If player hits a wall, return false
		if (board_array[x][y] == -1) 		
			return false;
		
		// get the players
		Players players = game_state.get_players();

		// If player hits a wall, return false
		for (int i = 0; i < players.get_player_count(); i++) 
		{
			Entity player = players.get_player(i);

			/* Block player from moving if there is another player*/
			if (x == player.get_pos_x() && y == player.get_pos_y() && ((Player)player).is_dead() == false)			
				return false;			
		}
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