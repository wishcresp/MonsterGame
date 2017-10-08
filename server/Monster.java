import java.util.Random;

public class Monster extends Entity {
	public Monster() {
		super();
		this.set_pos_x(5);
		this.set_pos_y(5);
	}

	// override move function from entity
	public void move(Players players, MonsterAi monster) 
	{
		System.out.println("FIRING UP THE AI");

		// Figure out the current monster node position
		int monster_node_postion = board.convert_to_node(this.get_pos_x(), this.get_pos_y());
		System.out.println(monster_node_postion);

		// Update the monster node 
		monster.set_monster_position(monster_node_postion);

		// RUN THE AI, calculate all the spots on the board
		monster.calculate_shortest_path();

		// New int array to hold all player distances
		int[] distance_array = new int[players.get_player_count()];

		// Loop through all the current players except monster
		for (int i = 0; i < players.get_player_count(); i++) 
		{
			// get the player
			Entity player = players.get_player(i);
			
			// store the current player node based on its coordinates
			int player_node = board.convert_to_node(player.get_pos_x(), player.get_pos_y());
			
			// store the player distance from the monster into the array
			int player_distance = monster.vertex_array[player_node].get_distance_from_source();
			distance_array[i] = player_distance;

			/*
			 * TODO FIGURE OUT WHY THIS VALUE ISNT UPDATING
			 */
			int player_destination = monster.vertex_array[player_node].get_monster_path();

			// print out the values of each player as debug
			System.out.println("|" + player_node + "," + player_distance + "," + player_destination + "|");
		}

		// Setup the variables for finding the smallest int
		int smallest = Integer.MAX_VALUE;
		int smallest_player = 0;

		// Try to find the smallest value in the array
		for (int i = 0; i < distance_array.length; i++) 
		{
			// Store the clear smaller value and update the smallest player
			if (smallest > distance_array[i]) 
			{
				smallest = distance_array[i];
				smallest_player = i;			
				
				// DEBUG PROMPT
				System.out.println("Player " + (smallest_player + 1) + 
						" is now the newest target with length of " + smallest);				
			} 
			
			/*
			 * If the smallest value is the equivalent to the current smallest value
			 * seed the random variable and create a 50% chance to set this player
			 * as the closest. This ensures unpredictability and fairness to the monster.
			 */
			else if (smallest == distance_array[i]) 
			{
				// Seed random variables
				Random rn = new Random();
				int rand = rn.nextInt();

				// 50% probability
				if (rand % 2 == 0) 
				{
					smallest = distance_array[i];
					smallest_player = i;
					
					// DEBUG prompt
					System.out.println("Player " + (smallest_player + 1) + 
							" is now the newest target with length of " + smallest);
				}
			}
		}

		// DEBUG prompt the player that is settled as the closest
		System.out.println("\nPlayer " + (smallest_player + 1) + " is the closest with a length of " + smallest);

		// With the index of the closest player, grab all its values
		Entity closest_player = players.get_player(smallest_player);
		
		// Find the node of the player with its coordinates 
		int closest_player_node = board.convert_to_node(closest_player.get_pos_x(), closest_player.get_pos_y());
		
		// Find the monster path based on where the node is located
		/*
		 *  TODO THIS NEEDS TO BE FIXED
		 */
		int closest_player_destination = monster.vertex_array[closest_player_node].get_monster_path();
		System.out.println(closest_player_destination);

		// convert the destination node into coordinates
		int[] coordinates = board.convert_to_coordinate(closest_player_destination);
		
		/*
		 *  UPDATE THE MONSTER
		 */
		// Set the new node position to the destination
		monster.set_monster_position(closest_player_destination);		

		// Update the coordinate value of the monster
		this.set_pos_x(coordinates[0]);
		this.set_pos_y(coordinates[1]);
		
		// DEBUG prompt where the monster has ended up
		System.out.println("Coordinates are now " + this.get_pos_x() + "," + this.get_pos_y() + " at node " + monster.get_monster_position());
	}
}