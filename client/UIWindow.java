/* Creates the main game client window. I think The rest of the client will likely
 * need to run from this class. Basically, treat the void start like the main
 * method. I'm not very familiar with threading so I might need some help with
 * that. */

import javafx.application.Application; 
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

public class UIWindow extends Application
{
	private UIBoard board;
	private BorderPane window;
	
	public void start(Stage primaryStage)
	{
		board = new UIBoard();
		window = new BorderPane(); 
		window.setCenter(board.getBoard());
		
		Scene scene = new Scene(window, 500, 500);
		primaryStage.setTitle("Monster Game");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		window.requestFocus();
		window.setOnKeyPressed(e -> {
			switch (e.getCode()) {
				case UP:
					System.out.println("UP WAS PRESSED");
					break;
				case DOWN:
					System.out.println("DOWN WAS PRESSED");
					break;
				case LEFT:
					System.out.println("LEFT WAS PRESSED");
					break;
				case RIGHT:
					System.out.println("RIGHT WAS PRESSED");
					break;
				default:
					System.out.println("INVALID KEY WAS PRESSED");
			}
		});
	}
}
