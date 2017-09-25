/* For now we will stick with simple coloured cells to display the board
 * until I learn how to use sprites instead. - Sean */

import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;

public class UIBoard extends Pane
{
	private int board_width;
	private int board_height;
	private GridPane board;
	
	public UIBoard()
	{
		this.board_width = 11;
		this.board_height = 11;
		initialize_board();
	}
	
	public void initialize_board()
	{
		board = new GridPane();
		
		/* Fills board with Empty/Path Cells */
		for (int i = 0; i < board_width; i++) 
		{
			for (int j = 0; j < board_height; j++)
			{
				/* I know this is disgusting. It's draws the grey path players 
				 * can move on. Bernie did this by using a hard-coded string of
				 * the board, maybe that wasn't so bad after all... */
				
				/* Adds blank cell*/
				if (((i == 1 || i == 5 || i == 9) && j > 0 && j < 10) ||
				((j == 1 || j == 5 || j == 9) && i > 0 && i < 10))
				{
					board.add(new UICell(1), i, j);
				}
				
				/* Adds path cell*/
				else
				{
					board.add(new UICell(0), i, j);
				}
			}
		}
		
		/* Adds Players */
		board.add(new UICell(2), 1, 1);
		board.add(new UICell(2), 1, 9);
		board.add(new UICell(2), 9, 1);
		board.add(new UICell(2), 9, 9);
		
		/* Adds Monster */
		board.add(new UICell(3), 5, 5);
		
		/* Adds Portals*/
		board.add(new UICell(4), 0, 5);
		board.add(new UICell(4), 5, 0);
		board.add(new UICell(4), 10, 5);
		board.add(new UICell(4), 5, 10);
	}
	
	public GridPane getBoard()
	{
		return board;
	}
}
