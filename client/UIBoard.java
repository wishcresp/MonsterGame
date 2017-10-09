/* Game board is a grid pane that contains sprites representing players
 * monsters and wall tiles. The board is generated and added to the game window. */

import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;


public class UIBoard extends Pane
{
	/* Creates the board */
	private GridPane board = new GridPane();
	
	public UIBoard()
	{
		set_background();
	}
	
	/* Sets the board's background */
	public void set_background()
	{
		/* Creates background */
		Image image = new Image("/resources/stars.jpg");
		BackgroundSize bg_size = new BackgroundSize(
				BackgroundSize.AUTO,
				BackgroundSize.AUTO,
				false,
				false,
				true,
				false);
		
		Background background = new Background(new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bg_size));
		
		board.setBackground(background);
	}
	
	/* Generates the gameboard */
	public void update_board()
	{
		/* Adds center allignment box */
		board.add(new UISprite(11, 0), 5, 5);
		
		/* Outer Border Corners */
		board.add(new UISprite(0, 2), 0, 0);
		board.add(new UISprite(0, 3), 10, 0);
		board.add(new UISprite(0, 1), 0, 10);
		board.add(new UISprite(0, 0), 10, 10);
		
		/* Outer Portals */	
		board.add(new UISprite(2, 2), 4, 0);
		board.add(new UISprite(2, 3), 6, 0);
		board.add(new UISprite(2, 3), 10, 4);
		board.add(new UISprite(2, 0), 10, 6);
		board.add(new UISprite(2, 1), 4, 10);
		board.add(new UISprite(2, 0), 6, 10);
		board.add(new UISprite(2, 2), 0, 4);
		board.add(new UISprite(2, 1), 0, 6);
		
		/* Outer top */
		board.add(new UISprite(1, 2), 1, 0);
		board.add(new UISprite(1, 2), 2, 0);
		board.add(new UISprite(1, 2), 3, 0);
		board.add(new UISprite(1, 2), 7, 0);
		board.add(new UISprite(1, 2), 8, 0);
		board.add(new UISprite(1, 2), 9, 0);
		
		/* Outer left */
		board.add(new UISprite(1, 1), 0, 1);
		board.add(new UISprite(1, 1), 0, 2);
		board.add(new UISprite(1, 1), 0, 3);
		board.add(new UISprite(1, 1), 0, 7);
		board.add(new UISprite(1, 1), 0, 8);
		board.add(new UISprite(1, 1), 0, 9);
		
		/* Outer right */
		board.add(new UISprite(1, 3), 10, 1);
		board.add(new UISprite(1, 3), 10, 2);
		board.add(new UISprite(1, 3), 10, 3);
		board.add(new UISprite(1, 3), 10, 7);
		board.add(new UISprite(1, 3), 10, 8);
		board.add(new UISprite(1, 3), 10, 9);
		
		/* Outer bottom */
		board.add(new UISprite(1, 0), 1, 10);
		board.add(new UISprite(1, 0), 2, 10);
		board.add(new UISprite(1, 0), 3, 10);
		board.add(new UISprite(1, 0), 7, 10);
		board.add(new UISprite(1, 0), 8, 10);
		board.add(new UISprite(1, 0), 9, 10);
		
		/* Inner walls*/
		/* Inner top-left (RED) */
		board.add(new UISprite(3, 0), 2, 2);
		board.add(new UISprite(3, 1), 4, 2);
		board.add(new UISprite(3, 2), 4, 4);
		board.add(new UISprite(3, 3), 2, 4);
		board.add(new UISprite(4, 0), 3, 2);
		board.add(new UISprite(4, 1), 4, 3);
		board.add(new UISprite(4, 2), 3, 4);
		board.add(new UISprite(4, 3), 2, 3);
		
		/* Inner top-right (BLUE) */
		board.add(new UISprite(5, 0), 6, 2);
		board.add(new UISprite(5, 1), 8, 2);
		board.add(new UISprite(5, 2), 8, 4);
		board.add(new UISprite(5, 3), 6, 4);
		board.add(new UISprite(6, 0), 7, 2);
		board.add(new UISprite(6, 1), 8, 3);
		board.add(new UISprite(6, 2), 7, 4);
		board.add(new UISprite(6, 3), 6, 3);
		
		/* Inner bottom-left (YELLOW) */
		board.add(new UISprite(7, 0), 2, 6);
		board.add(new UISprite(7, 1), 4, 6);
		board.add(new UISprite(7, 2), 4, 8);
		board.add(new UISprite(7, 3), 2, 8);
		board.add(new UISprite(8, 0), 3, 6);
		board.add(new UISprite(8, 1), 4, 7);
		board.add(new UISprite(8, 2), 3, 8);
		board.add(new UISprite(8, 3), 2, 7);
		
		/* Inner bottom-right (GREEN) */
		board.add(new UISprite(9, 0), 6, 6);
		board.add(new UISprite(9, 1), 8, 6);
		board.add(new UISprite(9, 2), 8, 8);
		board.add(new UISprite(9, 3), 6, 8);
		board.add(new UISprite(10, 0), 7, 6);
		board.add(new UISprite(10, 1), 8, 7);
		board.add(new UISprite(10, 2), 7, 8);
		board.add(new UISprite(10, 3), 6, 7);
		
		
		Players players;
		players = GameState.get_instance().get_players();
		
		
		while (players.islocked())
			try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
		//System.out.println("Drawing Players");
		for (int i = 0; i < players.get_player_count(); i++)
		{
			Entity player = players.get_player(i);
						
			if (player instanceof Monster)
				board.add(new UISprite(16, 0), player.get_pos_x(), player.get_pos_y());
			else {
				int rotation = 0;
				switch (player.get_dir())
				{
					case 0:
						rotation = 0;
						break;
					case 1:
						rotation = 2;
						break;
					case 2:
						rotation = 3;
						break;
					case 3:
						rotation = 1;
				}
				
				switch (player.get_id())
				{
					case "0":
						board.add(new UISprite(12, rotation), player.get_pos_x(), player.get_pos_y());
						break;
					case "1":
						board.add(new UISprite(13, rotation), player.get_pos_x(), player.get_pos_y());
						break;
					case "2":
						board.add(new UISprite(14, rotation), player.get_pos_x(), player.get_pos_y());
						break;
					case "3":
						board.add(new UISprite(15, rotation), player.get_pos_x(), player.get_pos_y());
						break;
					case "D": /* When dead */
						break;
					default:
						/* Displays error */
						board.add(new UISprite(18, 0), player.get_pos_x(), player.get_pos_x());
						break;
				}
			}
		}
	}
	
	public GridPane getBoard()
	{
		return board;
	}
}
