import static org.junit.Assert.*;

import org.junit.Test;

public class EntityTest 
{
	static Board board;
	static GameState gamestate;

	@Test
	public void test_player_movement() 
	{
		gamestate = GameState.get_instance();
		board = gamestate.get_board();

		// Test player 1
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
		Player2.set_dir(2);
		Player2.move();
	}
}