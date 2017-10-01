
public class GameState
{
	private static GameState gamestate = new GameState(); // Make this a singleton
	private boolean is_running = false;
	private boolean is_ready = false; // Wait for client to send all relevant information
	private String avaliable_spots = "";
	
	
	static Players players = new Players();
	static Board board = new Board();

	
	private GameState() 
	{
		return;
	}
	

	public String get_avaliable_spots() 
	{
		return avaliable_spots;
	}

	public void set_avaliable_spots(String avaliable_spots)
	{
		this.avaliable_spots = avaliable_spots;
	}





	public Players get_players()
	{
		return this.players;
	}
	
	public Board get_board()
	{
		return this.board;
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