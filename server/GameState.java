
public class GameState
{
	private static GameState gamestate = new GameState(); // Make this a singleton
	private boolean is_running = false;
	static Players players = new Players();
	static Board board = new Board();
	static Player player = new Player();

	
	private GameState() 
	{
		return;
	}
	
	public Players get_players()
	{
		return this.players;
	}
	
	public Board get_board()
	{
		return this.board;
	}
	
	public Entity get_player()
	{
		return this.player;
	}
	
	public boolean is_running()
	{
		return this.is_running;
	}
	
	public void change_run_state(boolean new_state)
	{
		this.is_running = new_state; 
	}
	
	public static GameState get_instance()
	{
		return gamestate;
	}	
}