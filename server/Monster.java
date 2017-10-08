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
		System.out.println("FIRING UP THE AI");
		
		// Check is monster is still cooling down
		if (!check_cooldown())
		{
			return;
		}
		

		int monster_node_postion = board.convert_to_node(this.get_pos_x(), this.get_pos_y());

		System.out.println("\n//// Coordinates are currently " + this.get_pos_x() + "," + this.get_pos_y() + " at node "
				+ monster.get_monster_position() + " ////\n");

		System.out.println(monster_node_postion);

		monster.set_monster_position(monster_node_postion);

		monster.calculate_shortest_path();

		// DEBUG TRY TO FIND OUT WHATS UPDATING
		System.out.println("\n//////////////// START OF THE AI PROCESS ////////////////\n");
		monster.print_result(monster_node_postion);
		System.out.println("\n//////////////// END OF THE AI PROCESS ////////////////\n");

		// trying to store all the distances
		int[] distance_array = new int[players.get_player_count()];

		// Loop through all the current players except monster
		for (int i = 0; i < players.get_player_count(); i++) 
		{
			Entity player = players.get_player(i);

			int player_node = board.convert_to_node(player.get_pos_x(), player.get_pos_y());
			int player_distance = monster.vertex_array[player_node].get_distance_from_source();
			int player_destination = monster.vertex_array[player_node].get_monster_path();

			System.out.println("|" + player_node + "," + player_distance + "," + player_destination + "|");

			distance_array[i] = player_distance;
		}

		int smallest = Integer.MAX_VALUE;
		int smallest_player = 0;

		// Try to find the smallest value in the array
		for (int i = 0; i < distance_array.length; i++) 
		{
			if (smallest > distance_array[i]) 
			{
				smallest = distance_array[i];
				smallest_player = i;
				System.out.println(
						"Player " + (smallest_player + 1) + " is now the newest target with length of " + smallest);
			} 
			else if (smallest == distance_array[i]) 
			{
				Random rn = new Random();
				int rand = rn.nextInt();

				if (rand % 2 == 0) 
				{
					smallest = distance_array[i];
					smallest_player = i;
					System.out.println(
							"Player " + (smallest_player + 1) + " is now the newest target with length of " + smallest);
				}
			}
		}

		// FOUND THE CLOSEST PLAYER
		
		System.out.println("\nPlayer " + (smallest_player + 1) + " is the closest with a length of " + smallest);

		Entity closest_player = players.get_player(smallest_player);
		int closest_player_node = board.convert_to_node(closest_player.get_pos_x(), closest_player.get_pos_y());
		int closest_player_destination = monster.vertex_array[closest_player_node].get_monster_path();

		// THIS IS THE NODE THAT THE MONSTER SHOULD MOVE TO
		System.out.println("\nPlayer " + (smallest_player + 1) + " node is " + closest_player_node + " with destination "
				+ closest_player_destination);
		
		System.out.println("\nMonster node is " + monster_node_postion);

		int[] coordinates = board.convert_to_coordinate(closest_player_destination);
		
		// IF monster node and closest player node is identical, kill the player
		if (monster_node_postion == closest_player_node)
		{
			Player killed_player = (Player) closest_player;
			killed_player.kill();
			
			// Set the cool down
			set_cool_down(5);
			
			// TODO CHECK IF THE PLAYER IS DEAD, if its dead don't look for it
			
		}
		else
		{
			// monster.set_monster_position(closest_player_destination);
			monster.set_monster_position(closest_player_destination);

			// MAKE THE MOVE
			this.set_pos_x(coordinates[0]);
			this.set_pos_y(coordinates[1]);
		}
		
		// DEBUG prompt where the monster has ended up
		System.out.println("\n////Coordinates are now " + this.get_pos_x() + "," + this.get_pos_y() + " at node "
				+ monster.get_monster_position() + "////\n");
		System.out.println("Coordinates are now " + this.get_pos_x() + "," + this.get_pos_y() + " at node "
				+ monster.get_monster_position());
	}
	
	
	public boolean check_cooldown()
	{
		if (this.cooldown <= 0)
		{
			return true;
		}
				
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