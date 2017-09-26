
public class Player extends Entity
{	
	public int pos_x, pos_y; // I renamed it from x,y.
	private String name;	
	
	public Player(String name, int pos_x, int pos_y) 
	{
		super();		
		this.name = name;
		this.pos_x = pos_x; // These need to be set elsewhere
		this.pos_y = pos_y;
		
	}	
	
	public String get_name()
	{
		return this.name;
	}
	
	public void set_name(String name)
	{
		this.name = name;
	}
	
	public int get_pos_x()
	{
		return this.pos_x;
	}
	
	public void set_pos_x(int pos_x)
	{
		this.pos_x = pos_x;
	}
	
	public int get_pos_y()
	{
		return this.pos_y;
	}
	
	public void set_pos_y(int pos_y)
	{
		this.pos_y = pos_y;
	}
	
}
