
public class GameState
{
	private static GameState gamestate = new GameState(); // Make this a singleton
	private boolean is_running = false;
	private String avaliable_spots = "";
	private String server_ip = "";
	private int server_port;
	private boolean winner = false;
	
	
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


	public String get_server_ip()
	{
		return server_ip;
	}


	public void set_server_ip(String server_ip)
	{
		this.server_ip = server_ip;
	}


	public int get_server_port()
	{
		return server_port;
	}


	public void set_server_port(int server_port)
	{
		this.server_port = server_port;
	}


	public boolean won()
	{
		return winner;
	}
	public void win(boolean winner) 
	{
		this.winner = winner;
	}	
}