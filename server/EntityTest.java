import static org.junit.Assert.*;

import org.junit.Test;

public class EntityTest 
{
	static Board board;
	static GameState gamestate;

	@Test
	public void test_player_movement() 
	{
		/*
		 * Get instances to prevent static variables
		 */
		gamestate = GameState.get_instance();
		board = gamestate.get_board();

		/*
		 * Set direction to Right and move, it should move normally
		 */
		Player Player1 = new Player();
		
		// Setup player coordinate
		Player1.set_pos_x(1);
		Player1.set_pos_y(1);

		// RIGHT
		Player1.set_dir(3);
		Player1.move();

		/*
		 * Set direction to Down and move, it should not move due to a wall
		 */
		Player Player2 = new Player();

		// Setup player coordinate
		Player2.set_pos_x(9);
		Player2.set_pos_y(1);

		// Down
		Player2.set_dir(1);
		Player2.move();
		
		/*
		 * Set direction to Right and move, it should move TELEPORT
		 */
		Player Player3 = new Player();
		
		// Setup player coordinate
		Player3.set_pos_x(9);
		Player3.set_pos_y(2);

		// LEFT
		Player3.set_dir(2);
		Player3.move();

		
	}
}