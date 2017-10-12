import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UISprite extends ImageView 
{
	
	/* Type is used to specify the desired sprite when a new UISprite is 
	 * created. Rotation sets the orientation of the sprite. */
	public UISprite(int type, int rotation)
	{
		this.setPreserveRatio(true);
		this.setImage(choose_image(type));
		set_rotation(rotation);
	}
	
	/* Sets the sprite. */
	public Image choose_image(int type) 
	{
		Image image;
		
		/* Specific case for each type of sprite. eg. Walls and corners of
		 * various colours, backgrounds and player/monster sprites */
		switch (type) {
			case 0:
				image = new Image("/resources/oc_p.png");
				break;
			case 1:
				image = new Image("/resources/sl_p.png");
				break;
			case 2:
				image = new Image("/resources/cl_p.png");
				break;
			case 3:
				image = new Image("/resources/cl_r.png");
				break;
			case 4:
				image = new Image("/resources/sl_r.png");
				break;
			case 5:
				image = new Image("/resources/cl_b.png");
				break;
			case 6:
				image = new Image("/resources/sl_b.png");
				break;
			case 7:
				image = new Image("/resources/cl_y.png");
				break;
			case 8:
				image = new Image("/resources/sl_y.png");
				break;
			case 9:
				image = new Image("/resources/cl_g.png");
				break;
			case 10:
				image = new Image("/resources/sl_g.png");
				break;
			case 11:
				image = new Image("/resources/placeholder.png");
				break;
			case 12:
				image = new Image("/resources/rp.png");
				break;
			case 13:
				image = new Image("/resources/bp.png");
				break;
			case 14:
				image = new Image("/resources/yp.png");
				break;
			case 15:
				image = new Image("/resources/gp.png");
				break;
			case 16:
				image = new Image("/resources/dk.png");
				break;
			default:
				image = new Image("/resources/error.png");
		}
		return image;
	}
	
	/* Sets the sprite rotation */
	public void set_rotation(int rotation) 
	{
		switch (rotation) 
		{
			case 0:
				this.setRotate(0);
				break;
			case 1:
				this.setRotate(90);
				break;
			case 2:
				this.setRotate(180);
				break;
			case 3:
				this.setRotate(270);
		}
	}
}
