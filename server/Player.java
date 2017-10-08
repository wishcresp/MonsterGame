
public class Player extends Entity
{	
	
	private String name;
	private boolean is_dead = false;
	
	public Player() 
	{
		super();		
	}	
	
	public String get_name()
	{
		return this.name;
	}
	
	public void set_name(String name)
	{
		this.name = name;
	}
	
	public boolean is_dead()
	{
		return this.is_dead;
	}
	
	public void kill()
	{
		this.is_dead = true;
		Players players = GameState.get_instance().get_players();
		players.set_alive_players((players.get_alive_players()-1));
	}
		
}
