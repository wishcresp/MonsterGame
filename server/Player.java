
public class Player extends Entity
{	
	
	private String name;
	boolean is_dead = false;
	
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
	
	public void kill()
	{
		this.is_dead = true;
	}
		
}
