import static org.junit.Assert.*;

import org.junit.Test;

public class PlayersTest 
{
	 Players players;

	@Test
	public void test() 
	{
		players = GameState.get_instance().get_players();	
		
		Player player1 = new Player();
		
		players.add_player(player1);
		
	
	}
}
