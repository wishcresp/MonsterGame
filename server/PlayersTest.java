import static org.junit.Assert.*;

import org.junit.Test;

public class PlayersTest 
{
	 Players players;

	@Test
	public void test() 
	{
		players = Players.get_player_instance();	

		// Testing something
		players.player_move("blaise", 1, 1);		
	}
}
