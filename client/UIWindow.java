/* Creates the main game client window. The rest of the client will likely
 * need to run from this class, it's probably possible to start the UI from
 * another class. Basically, treat the void start like the main
 * method. I'm not very familiar with threading so I might need some help with
 * that. */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UIWindow extends Application
{
	private UIBoard board;
	private BorderPane game_window;
	private BorderPane reg_window;
	private BorderPane ip_window;
	
	protected TextField name_entry;
	protected TextField ip_entry;
	private String player_name = "";
	private String ip_address = "";
	
	@Override
	public void start(Stage game_stage)
	{
		/* Creates Game Board UI */
		board = new UIBoard();
		game_window = new BorderPane(); 
		game_window.setCenter(board.getBoard());
		Scene game_scene = new Scene(game_window, 500, 500);
		game_stage.setScene(game_scene);

		
		/* Creates Registration Window */
		Stage reg_stage = new Stage();
		Label name_label = new Label("Enter your name:");
		name_entry = new TextField();
		Button btn_name = new Button("CONFIRM");
		
		reg_window = new BorderPane();
		reg_window.setTop(name_label);
		reg_window.setCenter(name_entry);
		reg_window.setBottom(btn_name);
		Scene reg_scene = new Scene(reg_window, 500, 500);
		reg_stage.setScene(reg_scene);
		
		
		/* Creates IP input Window */
		Stage ip_stage = new Stage();
		Label ip_label = new Label("Enter IP Address:");
		ip_entry = new TextField();
		Button btn_ip = new Button("CONFIRM");
		
		ip_window = new BorderPane();
		ip_window.setTop(ip_label);
		ip_window.setCenter(ip_entry);
		ip_window.setBottom(btn_ip);
		Scene ip_scene = new Scene(ip_window, 500, 500);
		ip_stage.setScene(ip_scene);
		ip_stage.show();
		
		/* Key Listener for arrows */
		game_window.setOnKeyPressed(e -> {
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
		
		/* When IP confirm button is pressed*/
		btn_ip.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				/* Attempt to connect to the server in here, input validation
				 * server side? */
				
				ip_address = ip_entry.getText();
				ip_stage.hide();
				reg_stage.show();
			}
		});
		
		/* When name confirm button is pressed. */
		btn_name.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				/* Potentially connect to server and send player name? When the
				 * game is over, the winners name should be displayed on all
				 * players screen.
				 * 
				 *  Perhaps store the players name in a player object? */
				
				player_name = name_entry.getText();
				reg_stage.hide();
				game_stage.show();
				game_stage.setTitle("Monster Game - Player: " + player_name
						+ " - IP: " + ip_address);
				game_window.requestFocus();
			}
		});
	}
}

