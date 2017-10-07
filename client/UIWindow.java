/* Creates the main game client window. The rest of the client will likely
 * need to run from this class, it's probably possible to start the UI from
 * another class. Basically, treat the void start like the main
 * method. I'm not very familiar with threading so I might need some help with
 * that. */

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UIWindow extends Application
{
	private UIBoard board = new UIBoard();
	private BorderPane game_window = new BorderPane();
	private GridPane num_window;
	private BorderPane reg_window;
	private GridPane ip_window;
	private GridPane sp_window;
	
	private Button btn_sp_one;
	private Button btn_sp_two;
	private Button btn_sp_three;
	private Button btn_sp_four;
	
	protected TextField name_entry;
	protected TextField ip_entry;
	protected TextField port_entry;
	
	private int PC_id;	
	private Players players;
	private String[] poses;
	private GameState game_state;
	
	private Timeline game_loop;
	
	@Override
	public void start(Stage game_stage)
	{
		game_state = GameState.get_instance();
		players = game_state.get_players();
		PC_id = players.get_pc_id();
		
		/* Creates game window */
		Scene game_scene = new Scene(game_window, 500, 500);
		game_stage.setScene(game_scene);
		
		/* Creates IP input Window */
		Stage ip_stage = new Stage();
		Label ip_label = new Label("Enter IP Address:");
		/* Temporary ip/port*/
		ip_entry = new TextField("127.0.0.1");
		Label port_label = new Label("Enter Port:");
		port_entry = new TextField("3216");
		Button btn_ip = new Button("CONFIRM");
		ip_stage.show();
		
		ip_window = new GridPane();
		ip_window.add(ip_label, 0, 0);
		ip_window.add(ip_entry, 0, 1);
		ip_window.add(port_label, 0, 2);
		ip_window.add(port_entry, 0, 3);
		ip_window.add(btn_ip, 0, 4);
		
		Scene ip_scene = new Scene(ip_window, 500, 500);
		ip_stage.setScene(ip_scene);
		
		/* Creates Number of players input Window */
		Stage num_stage = new Stage();
		Button btn_num_one = new Button("One");
		Button btn_num_two = new Button("Two");
		Button btn_num_three = new Button("Three");
		Button btn_num_four = new Button("Four");
		btn_num_one.setPrefWidth(200);
		btn_num_two.setPrefWidth(200);
		btn_num_three.setPrefWidth(200);
		btn_num_four.setPrefWidth(200);
		num_window = new GridPane();
		num_window.add(new Label("Select number of players"), 0, 0);
		num_window.add(btn_num_one, 0, 1);
		num_window.add(btn_num_two, 0, 2);
		num_window.add(btn_num_three, 0, 3);
		num_window.add(btn_num_four, 0, 4);
		Scene num_scene = new Scene(num_window, 500, 500);
		num_stage.setScene(num_scene);
		
		/* Creates Registration Window */
		Stage reg_stage = new Stage();
		Label name_label = new Label("Enter your name:");
		/* Temporary name */
		name_entry = new TextField("A guy");
		Button btn_name = new Button("CONFIRM");
		reg_window = new BorderPane();
		reg_window.setTop(name_label);
		reg_window.setCenter(name_entry);
		reg_window.setBottom(btn_name);
		Scene reg_scene = new Scene(reg_window, 500, 500);
		reg_stage.setScene(reg_scene);
		
		
		/* Creates starting position selection window */
		Stage sp_stage = new Stage();
		Label sp_label = new Label("Select your starting postion:");
		btn_sp_one = new Button("TOP LEFT");
		btn_sp_two = new Button("TOP RIGHT");
		btn_sp_three = new Button("BOTTOM LEFT");
		btn_sp_four = new Button("BOTTOM RIGHT");
		btn_sp_one.setPrefWidth(200);
		btn_sp_two.setPrefWidth(200);
		btn_sp_three.setPrefWidth(200);
		btn_sp_four.setPrefWidth(200);
		sp_window = new GridPane();
		sp_window.add(new Label("Select starting position:"), 0, 0);
		sp_window.add(btn_sp_one, 0, 1);
		sp_window.add(btn_sp_two, 0, 2);
		sp_window.add(btn_sp_three, 1, 1);
		sp_window.add(btn_sp_four, 1, 2);
		Scene sp_scene = new Scene(sp_window, 500, 500);
		sp_stage.setScene(sp_scene);
		
		/* Key Listener for arrows */
		game_window.setOnKeyPressed(e ->
		{
			switch (e.getCode())
			{
				/* Konami code directions.
				 * UP = 0
				 * DOWN = 1
				 * LEFT = 2
				 * RIGHT = 3*/
			
				// Need to specify the player direction to set (x, 0) x=?
				case UP:
					System.out.println("UP WAS PRESSED");
					players.get_player(PC_id).set_ddir(0);
					break;
				case DOWN:
					System.out.println("DOWN WAS PRESSED");
					players.set_player_dir(PC_id, 1);
					players.get_player(PC_id).set_ddir(1);
					break;
				case LEFT:
					System.out.println("LEFT WAS PRESSED");
					players.get_player(PC_id).set_ddir(2);
					break;
				case RIGHT:
					System.out.println("RIGHT WAS PRESSED");
					players.get_player(PC_id).set_ddir(3);
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
				GameState.get_instance().set_server_ip(ip_entry.getText());
				GameState.get_instance().set_server_port(Integer.parseInt(port_entry.getText()));
				
				/* Waits for server to allocate player ID*/
				while (PC_id == -1)
				{
					System.out.println("PC_id = -1");
					PC_id = players.get_pc_id();
					try { Thread.sleep(100); } catch (Exception err) { }
				}
				
				ip_stage.hide();
				if (PC_id == 0)
				{
					num_stage.show();
				}
				else
				{
					reg_stage.show();
				}
				
			}
		});
		
		/* When one players are selected. */
		btn_num_one.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				players.set_player_target(1);
				num_stage.hide();
				reg_stage.show();

			}
		});
		
		/* When two players are selected. */
		btn_num_two.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				players.set_player_target(2);
				num_stage.hide();
				reg_stage.show();

			}
		});
		
		/* When three players are selected. */
		btn_num_three.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				players.set_player_target(3);
				num_stage.hide();
				reg_stage.show();

			}
		});
		
		/* When four players are selected. */
		btn_num_four.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				players.set_player_target(4);
				num_stage.hide();
				reg_stage.show();

			}
		});
		
		
		/* When Name confirm button is pressed*/
		btn_name.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				System.out.println("PC_ID is: " + PC_id);
				players.get_player(PC_id).set_name(name_entry.getText());

				while (poses != null)
				{
					poses = players.get_starter_spot().split(":");
					System.out.println("Waiting for poses");
					try { Thread.sleep(100); } catch (Exception ex) { }
				}
				
				reg_stage.hide();
				
				if (hide_button("1,1"))
					btn_sp_one.setDisable(true);
				if (hide_button("9:1"))
					btn_sp_two.setDisable(true);
				if (hide_button("1,9"))
					btn_sp_three.setDisable(true);
				if (hide_button("9,9"))
					btn_sp_four.setDisable(true);
				
				sp_stage.show();
			}
		});
		
		/* When position confirm button is pressed. */
		btn_sp_one.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				wait_avaliable_spots(0);
				sp_stage.hide();
				game_stage.show();
				game_stage.setTitle("Monster Game - Player: " + 
						players.get_player(PC_id).get_name());
				game_window.requestFocus();
				game_loop.play();
			}
		});
		
		/* When position confirm button is pressed. */
		btn_sp_two.setOnAction(new EventHandler<ActionEvent>()
		{			
			@Override
			public void handle(ActionEvent e)
			{
				wait_avaliable_spots(1);
				sp_stage.hide();
				game_stage.show();
				game_stage.setTitle("Monster Game - Player: " + 
						players.get_player(PC_id).get_name());
				game_window.requestFocus();
				game_loop.play();
			}
		});
		
		btn_sp_three.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				wait_avaliable_spots(2);
				sp_stage.hide();
				game_stage.show();
				game_stage.setTitle("Monster Game - Player: " + 
						players.get_player(PC_id).get_name());
				game_window.requestFocus();
				game_loop.play();
			}
		});
		
		btn_sp_four.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				wait_avaliable_spots(3);
				sp_stage.hide();
				game_stage.show();
				game_stage.setTitle("Monster Game - Player: " + 
						players.get_player(PC_id).get_name());
				game_window.requestFocus();
				game_loop.play();
			}
		});
		
	    EventHandler<ActionEvent> eventHandler = e -> {
			/* Creates Game Board UI */
			board.update_board();
			game_window.setCenter(board.getBoard());
	    };
	    
	    game_loop = new Timeline(new KeyFrame(Duration.millis(64), eventHandler));
	    game_loop.setCycleCount(Timeline.INDEFINITE);
	}
	
	public void wait_avaliable_spots(int i)
	{
		while (game_state.get_avaliable_spots() == "")
		{
			System.out.println("Waiting for avaliable spots");
			try { Thread.sleep(100); } catch (Exception ex) {}
		}
		poses = game_state.get_avaliable_spots().split(":");	
		players.set_starter_spot(poses[i]);
		
	}
	
	public boolean hide_button(String s)
	{
		if (s.contains(game_state.get_avaliable_spots()))
			return true;
		else
			return false;
	}
}

