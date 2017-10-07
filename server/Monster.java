
public class Monster extends Entity 
{
	public Monster() 
	{
		super();		
	}
	
	// overide move function from entity
	public void move()
	{
		this.set_pos_x(5);
		this.set_pos_y(5);		
	}	
}