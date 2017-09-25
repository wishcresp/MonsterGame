
public class GameState
{
	private static GameState gamestate = new GameState(); // Make this a singleton
	private boolean is_running = false;
	
	private GameState() 
	{
		return;
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