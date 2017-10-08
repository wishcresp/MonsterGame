import java.util.Random;

public class Monster extends Entity 
{
	
	private int cooldown;
	
	public Monster() 
	{
		super();
		this.set_pos_x(5);
		this.set_pos_y(5);
	}

	// override move function from entity
	public void move(Players players, MonsterAi monster) 
	{
		// PROMPT
		System.out.println("\n//////////////// START OF THE AI PROCESS ////////////////\n");

		// DEBUG Where did the monster end up
		System.out.println("Monster coordinates BEFORE: " + this.get_pos_x() + "," + this.get_pos_y() + " at node "
				+ monster.get_monster_position());
					
		
		// Check is monster is still cooling down
		if (!check_cooldown())
		{
			return;
		}
	
		// get monster source position
		int monster_node_postion = board.convert_to_node(this.get_pos_x(), this.get_pos_y());

		// DEBUG PROMPT
		System.out.println("\n//// Coordinates are currently " + this.get_pos_x() + "," + this.get_pos_y() + " at node "
				+ monster_node_postion + " ////\n");
		
		// Set monster source
		monster.set_monster_position(monster_node_postion);

		// RUN THE MONSTER AI
		monster.calculate_shortest_path();

		// DEBUG TRY TO FIND OUT WHATS UPDATING
		//monster.print_result(monster_node_postion);
		//System.out.println("\n//////////////// END OF THE AI PROCESS ////////////////\n");

		// trying to store all the distances
		int[] distance_array = new int[players.get_player_count()];

		// Loop through all the current players except monster
		for (int i = 0; i < players.get_player_count(); i++) 
		{
			// Store player object
			Player player = (Player) players.get_player(i);

			// get player node
			int player_node = board.convert_to_node(player.get_old_pos_x(), player.get_old_pos_y());
			//get player distance
			int player_distance = monster.vertex_array[player_node].get_distance_from_source();
			//get player destination coordinates
			int player_destination = monster.vertex_array[player_node].get_monster_path();
			
			// Check if the player has died
			System.out.println("IS THE PLAYER DEAD? " + player.is_dead());
			
			// Make the player out of reach if dead
			if (player.is_dead()) {
				player_distance = Integer.MAX_VALUE;
			}

			// Print all the above variables
			System.out.println("|" + player_node + "," + player_distance + "," + player_destination + "|");
			
			// Store the distance of each player for checking
			distance_array[i] = player_distance;
		}

		// variables for finding smallest player
		int smallest = Integer.MAX_VALUE;
		int smallest_player = 0;

		// Try to find the smallest value in the array
		for (int i = 0; i < distance_array.length; i++) 
		{
			// the clear closest player is stored
			if (smallest > distance_array[i] ) 
			{
				smallest = distance_array[i];
				smallest_player = i;
				
				// prompt
				System.out.println(
						"Player " + (smallest_player + 1) + " is now the newest target with length of " + smallest);
			} 
			
			// 50% chance to assign as the closest if they are the same
			else if (smallest == distance_array[i]) 
			{
				// seed randomizer
				Random rn = new Random();
				int rand = rn.nextInt();

				// 50% probability of assigning the value
				if (rand % 2 == 0) 
				{
					smallest = distance_array[i];
					smallest_player = i;
					
					// prompt
					System.out.println(
							"Player " + (smallest_player + 1) + " is now the newest target with length of " + smallest);
				}
			}
		}

		
		// FOUND THE CLOSEST PLAYER
		System.out.println("\nPLAYER " + (smallest_player + 1) + " IS THE TARGET WITH LENGTH " + smallest);

		Entity closest_player = players.get_player(smallest_player);
		int closest_player_node = board.convert_to_node(closest_player.get_old_pos_x(), closest_player.get_old_pos_y());
		int closest_player_destination = monster.vertex_array[closest_player_node].get_monster_path();

		// THIS IS THE NODE THAT THE MONSTER SHOULD MOVE TO
		System.out.println("\nPlayer " + (smallest_player + 1) + " node is " + closest_player_node + " with destination "
				+ closest_player_destination);
		
		int[] coordinates = board.convert_to_coordinate(closest_player_destination);
		
		// IF monster node and closest player node is identical, kill the player
		
		// TODO SHOULD BE CHECKING THE PREVIOUS NODE OF THE PLAYER, REALLY MESSSED UP MY CODE HERE
		
		
		/*if (monster_node_postion == closest_player_node)
		{
			Player killed_player = (Player) closest_player;
			killed_player.kill();
			
			// Set the cool down
			set_cool_down(5);
			
			// TODO CHECK IF THE PLAYER IS DEAD, if its dead don't look for it
			
		}*/
		if (false) {}
		else
		{
			// monster.set_monster_position(closest_player_destination);
			monster.set_monster_position(closest_player_destination);

			// MAKE THE MOVE
			this.set_pos_x(coordinates[0]);
			this.set_pos_y(coordinates[1]);
		
			// DEBUG Where did the monster end up
			System.out.println("\nMonster coordinates AFTER = " + this.get_pos_x() + "," + this.get_pos_y() + " at node "
					+ monster.get_monster_position());
			
			
			if (monster.get_monster_position() == closest_player_node)
			{
				Player killed_player = (Player) closest_player;
				killed_player.kill();
				
				System.out.println("\nKILL THIS PLAYER");

				// Set the cool down
				set_cool_down(1);
				
				// TODO CHECK IF THE PLAYER IS DEAD, if its dead don't look for it
			}
			
		if (this.get_pos_x() == closest_player.get_pos_x() && this.get_pos_y() == closest_player.get_pos_y())
			((Player)closest_player).kill(); // Kill 'em if we're touching them
		
		
		// DEBUG prompt where the monster has ended up
		System.out.println("\n////Coordinates are now " + this.get_pos_x() + "," + this.get_pos_y() + " at node "
				+ monster.get_monster_position() + "////\n");
		}
		
		

	}
	
	
	public boolean check_cooldown()
	{
		if (this.cooldown <= 0)
		{
			return true;
		}
				
		System.out.println("ON COOL DOWN WITH REMAINING = " + this.cooldown);
		this.cooldown--;
		return false;			
	}

	
	public int get_cool_down() {
		return cooldown;
	}

	public void set_cool_down(int cooldown) {
		this.cooldown = cooldown;
	}
	
	
}