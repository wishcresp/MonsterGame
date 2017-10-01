import static org.junit.Assert.*;

import org.junit.Test;

public class PlayersTest 
{
	 Players players;

	@Test
	public void test() 
	{
		players = GameState.get_instance().get_players();		
	
	}
}
