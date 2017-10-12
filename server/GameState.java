
public class GameState 
{
	// This class contains getters, setters and objects for managing the whole game
	
	private static GameState gamestate = new GameState(); // Make this a singleton
	private boolean is_running = false;
	private Players players = new Players();
	private Board board = new Board();
	private int random_number;
	private GraphVertex[] graphVertex;

	private GameState() 
	{
		return;
	}
	
	public GraphVertex[] get_graph_vertex() 
	{
		return this.graphVertex;
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
	
	public int get_random_number()
	{
		return this.random_number;
	}
	
	public void set_random_number(int num)
	{
		this.random_number = num;
	}
}