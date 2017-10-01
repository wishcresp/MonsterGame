import static org.junit.Assert.*;

import org.junit.Test;

public class EntityTest {

	@Test
	public void test_player_movement() {
		/*
		 *  Set direction to Right and move, it 
		 *  should move properly
		 */
		Player Player1 = new Player();
		Player1.set_dir(2);		
		Player1.move();	
		
		/*
		 *  Set direction to Down and move, it 
		 *  should not move due to a wall
		 */
		Player Player2 = new Player();
		
		//Setup player coordinate
		Player2.set_pos_x(9);
		Player2.set_pos_y(1);

		Player2.set_dir(3);		
		Player2.move();	
		
	}

}
