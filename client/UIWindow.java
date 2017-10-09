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
	Bangers banger[] = new Bangers[4];
	Bangers winbang = new Bangers();
	
	private UIBoard board = new UIBoard();
	private BorderPane game_window = new BorderPane();
	private GridPane num_window;
	private BorderPane reg_window;
	private GridPane ip_window;
	private GridPane sp_window;
	private BorderPane wait_window;
	
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
		/* Creates game window */
		game_stage.setResizable(false);
		Scene game_scene = new Scene(game_window, 550, 550);
		
		game_state = GameState.get_instance();
		players = game_state.get_players();
		PC_id = players.get_pc_id();
		
		/* Creates IP input Window */
		Stage ip_stage = new Stage();
		Label ip_label = new Label("Enter IP Address:");
		/* Temporary ip/port */
		ip_entry = new TextField("127.0.0.1");
		Label port_label = new Label("Enter Port:");
		port_entry = new TextField("3216");
		Button btn_ip = new Button("CONFIRM");
		
		ip_window = new GridPane();
		ip_window.add(ip_label, 0, 0);
		ip_window.add(ip_entry, 0, 1);
		ip_window.add(port_label, 0, 2);
		ip_window.add(port_entry, 0, 3);
		ip_window.add(btn_ip, 0, 4);
		
		Scene ip_scene = new Scene(ip_window, 550, 550);
		ip_stage.setScene(ip_scene);
		
		/* Creates Number of players input Window */
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
		Scene num_scene = new Scene(num_window, 550, 550);
		
		/* Creates Registration Window */
		Label name_label = new Label("Enter your name:");
		/* Temporary name */
		name_entry = new TextField("A guy");
		Button btn_name = new Button("CONFIRM");
		reg_window = new BorderPane();
		reg_window.setTop(name_label);
		reg_window.setCenter(name_entry);
		reg_window.setBottom(btn_name);
		Scene reg_scene = new Scene(reg_window, 550, 550);
		
		/* Creates starting position selection window */
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
		sp_window.add(btn_sp_two, 1, 1);
		sp_window.add(btn_sp_three, 0, 2);
		sp_window.add(btn_sp_four, 1, 2);
		Scene sp_scene = new Scene(sp_window, 550, 550);
		
		/* Waiting for players screen */
		Label wait_label = new Label("Waiting for players...");
		wait_window = new BorderPane();
		wait_window.setCenter(wait_label);
		Scene wait_scene = new Scene(wait_window, 550, 550);
		
		game_stage.setScene(ip_scene);
		game_stage.show();

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
			
				case UP:
					players.get_player(PC_id).set_ddir(0);
					break;
				case DOWN:
					players.get_player(PC_id).set_ddir(1);
					break;
				case LEFT:
					players.get_player(PC_id).set_ddir(2);
					break;
				case RIGHT:
					players.get_player(PC_id).set_ddir(3);
					break;
				default:
					/* Invalid key was pressed */
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
				
				// We now have the music
			    int rand = game_state.get_random_number();
				banger[rand].play();
				
				if (PC_id == 0)
					game_stage.setScene(num_scene);
				else
					game_stage.setScene(reg_scene);	
			}
		});
		
		/* When one players are selected. */
		btn_num_one.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				players.set_player_target(1);
				game_stage.setScene(reg_scene);	

			}
		});
		
		/* When two players are selected. */
		btn_num_two.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				players.set_player_target(2);
				game_stage.setScene(reg_scene);	
			}
		});
		
		/* When three players are selected. */
		btn_num_three.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				players.set_player_target(3);
				game_stage.setScene(reg_scene);	
			}
		});
		
		/* When four players are selected. */
		btn_num_four.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				players.set_player_target(4);
				game_stage.setScene(reg_scene);	
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
				
//				if (!spot_avaliable("1,1"))
//					btn_sp_one.setDisable(true);
//				if (!spot_avaliable("9,1"))
//					btn_sp_two.setDisable(true);
//				if (!spot_avaliable("1,9"))
//					btn_sp_three.setDisable(true);
//				if (!spot_avaliable("9,9"))
//					btn_sp_four.setDisable(true);
				
				game_stage.setScene(sp_scene);	
			}
		});
		
		/* When position confirm button is pressed. */
		btn_sp_one.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				wait_avaliable_spots(0);
				game_stage.setScene(game_scene);	
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
				game_stage.setScene(game_scene);
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
				game_stage.setScene(game_scene);
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
				game_stage.setScene(game_scene);
				game_stage.setTitle("Monster Game - Player: " + 
						players.get_player(PC_id).get_name());
				game_window.requestFocus();
				game_loop.play();
			}
		});
		
	    EventHandler<ActionEvent> eventHandler = e -> {
			/* Frees the gameboard and Recreates Game Board UI */
	    	board = null;
	    	System.gc();
	    	board = new UIBoard();
			board.update_board();
			game_window.setCenter(board.getBoard());
			
			if (game_state.won())
			{
				System.out.println("WINNNAR");
				
				for (int i = 0; i < 4; i++)
					banger[i].pause();
				winbang.play();
				try {Thread.sleep(5000);} catch (InterruptedException ex)
				{
					ex.printStackTrace();
				}
				System.exit(0);
			}

	    };
	    
	    game_loop = new Timeline(new KeyFrame(Duration.millis(64), eventHandler));
	    game_loop.setCycleCount(Timeline.INDEFINITE);
	    
	    
	   
	    // Finna music
	    for (int i = 0; i < 4; i++)
	    	banger[i] = new Bangers();
	    
	    // https://www.youtube.com/watch?v=QS0qjldeT9k
		banger[0].load("nigga.mp3");
		// CYDNEY / GREEN OLIVES - JIVE INTO THE NIGHT
		// https://www.youtube.com/watch?v=tKqO9gEPmpw
		banger[1].load("jive.mp3");	
		// Source: Bomberman - Redial (Old YouTube rip)
		banger[2].load("bomb.mp3");
		// Koinu - Kuroneko (Koinu Funky Kong ASMR mashup from MidnightSnacks 11 year anniversary party)
		banger[3].load("kong.mp3"); // Would love optional sprites for this
		// Like if when (rand == 4) you made the monster https://vignette1.wikia.nocookie.net/rare/images/6/69/Funky_Kong_Artwork_2_-_Donkey_Kong_Country.png/revision/latest?cb=20120424224116
		
		winbang.load("respec.mp3");
		
		for (int i = 0; i < 4; i++)
			banger[i].loop_on();

	}
	
	/* Waits for server to send avaliable spots*/
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
	
	/* Checks if a spot is avaliable */
	public boolean spot_avaliable(String s)
	{
		if (game_state.get_avaliable_spots().contains(s))
			return true;
		else
			return false;
	}
	
	
	
	
}

