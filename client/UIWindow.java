/* Contains the game UI. Is called from Gameclient. Generates and displays the 
 * gameboard UIBoard. Communicates with the GameState to send information
 * over to the server. */

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UIWindow extends Application
{
	/* Music */
	public Bangers banger[] = new Bangers[4];
	public Bangers winbang = new Bangers();
	
	/* Gameboard and UI elements */
	private UIBoard board = new UIBoard();
	private BorderPane game_window = new BorderPane();
	private BorderPane num_window;
	private BorderPane reg_window;
	private BorderPane ip_window;
	private BorderPane sp_window;
	private BorderPane win_window;
	
	private Button btn_sp_one;
	private Button btn_sp_two;
	private Button btn_sp_three;
	private Button btn_sp_four;
	
	protected TextField name_entry;
	protected TextField ip_entry;
	protected TextField port_entry;
	
	private int PC_id;	
	private Players players;
	private GameState game_state;
	
	private Timeline game_loop;
	
	@Override
	public void start(Stage game_stage)
	{	
		/* Gets data from server */
		game_state = GameState.get_instance();
		players = game_state.get_players();
		PC_id = players.get_pc_id();	
		
		/* Creates pane for Vertical spacing */
		Pane spacer = new Pane();
		spacer.setMinHeight(40);
		
		/* Creates game window */
		game_stage.setResizable(false);
		Scene game_scene = new Scene(game_window, 550, 550);
		
		
		/* Creates IP input Window */
		Label ip_label = new Label("Enter IP Address:");
		ip_label.setFont(Font.font(18));
		ip_window = new BorderPane();
		/* Temporary ip/port */
		ip_entry = new TextField("");
		ip_entry.setMaxWidth(200);
		ip_entry.setAlignment(Pos.CENTER);
		ip_entry.setFont(Font.font(24));
		
		Label port_label = new Label("Enter Port:");
		port_label.setAlignment(Pos.CENTER);
		port_label.setFont(Font.font(18));
		
		port_entry = new TextField("3216");
		port_entry.setMaxWidth(200);
		port_entry.setAlignment(Pos.CENTER);
		port_entry.setFont(Font.font(24));
		
		Button btn_ip = new Button("CONFIRM");
		btn_ip.setAlignment(Pos.CENTER);
		btn_ip.setMinWidth(100);
		btn_ip.setMinHeight(50);
		btn_ip.setFont(Font.font(20));
		
		/* https://i.imgur.com/lXOFDVuh.jpg
		 */
		Image title = new Image("resources/title.png");
		ImageView title_view = new ImageView(title);
		
		VBox ip_input = new VBox();
		ip_input.getChildren().addAll(ip_label, ip_entry, port_label, 
				port_entry, btn_ip);
		ip_input.setAlignment(Pos.CENTER);
		ip_window.setCenter(ip_input);
		
		ip_window.setTop(title_view);
		ip_window.setStyle("-fx-background-color: beige");
		Scene ip_scene = new Scene(ip_window, 550, 550);
		
		
		/* Creates number of players input Window */
		VBox num_label_pane = new VBox();
		Label num_label = new Label("Select number of players:");
		num_label.setFont(Font.font("", FontWeight.BOLD, 32));
		num_label.setStyle("-fx-text-fill: white;");
		num_label_pane.setAlignment(Pos.CENTER);
		num_label_pane.getChildren().addAll(spacer, num_label);
		
		Button btn_num_one = new Button("One");
		Button btn_num_two = new Button("Two");
		Button btn_num_three = new Button("Three");
		Button btn_num_four = new Button("Four");
		
		btn_num_one.setPrefWidth(225);
		btn_num_one.setPrefHeight(50);
		btn_num_one.setFont(Font.font(28));
		btn_num_one.setDisable(true);
		
		btn_num_two.setPrefWidth(225);
		btn_num_two.setPrefHeight(50);
		btn_num_two.setFont(Font.font(28));
		
		btn_num_three.setPrefWidth(225);
		btn_num_three.setPrefHeight(50);
		btn_num_three.setFont(Font.font(28));
		
		btn_num_four.setPrefWidth(225);
		btn_num_four.setPrefHeight(50);
		btn_num_four.setFont(Font.font(28));

		GridPane num_boxes = new GridPane();
		num_boxes.add(btn_num_one, 0, 0);
		num_boxes.add(btn_num_two, 1, 0);
		num_boxes.add(btn_num_three, 0, 1);
		num_boxes.add(btn_num_four, 1, 1);
		num_boxes.setAlignment(Pos.CENTER);
		
		num_window = new BorderPane();
		num_window.setTop(num_label_pane);
		num_window.setBottom(num_boxes);
		
		Scene num_scene = new Scene(num_window, 550, 550);
		
		/* 
		 * https://thumbs.dreamstime.com/b/havanese-de-salto-34230736.jpg
		 */
		Image leap = new Image("resources/leap.jpg");
		BackgroundSize bg_size = new BackgroundSize(
				BackgroundSize.AUTO,
				BackgroundSize.AUTO,
				false,
				false,
				true,
				false);
		
		Background num_background = new Background(new BackgroundImage(leap,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bg_size));
		num_window.setBackground(num_background);
		
		
		/* Creates Name Registration Window */
		VBox name_label_pane = new VBox();
		Label name_label = new Label("Enter your name:");
		name_label.setFont(Font.font("", FontWeight.BOLD, 38));
		name_label.setStyle("-fx-text-fill: black;");
		name_label_pane.setAlignment(Pos.CENTER);
		name_label_pane.getChildren().addAll(spacer, name_label);
		
		VBox name_entry_pane = new VBox();
		name_entry = new TextField("");
		name_entry.setPrefWidth(400); 
		name_entry.setFont(Font.font(32));
		name_entry.setAlignment(Pos.BOTTOM_CENTER);
		name_entry_pane.getChildren().addAll(name_entry, spacer);
		
		VBox btn_name_pane = new VBox();
		Button btn_name = new Button("CONFIRM");
		btn_name.setPrefHeight(100);
		btn_name.setPrefWidth(200);
		btn_name.setFont(Font.font(28));
		btn_name_pane.setAlignment(Pos.CENTER);
		btn_name_pane.getChildren().addAll(btn_name, spacer);
		
		reg_window = new BorderPane();
		reg_window.setTop(name_label_pane);
		reg_window.setCenter(name_entry_pane);
		reg_window.setBottom(btn_name_pane);
		
		Scene reg_scene = new Scene(reg_window, 550, 550);
		
		/* https://i.redd.it/ye08k37ynfqz.jpg 
		 */
		Image shocked = new Image("resources/helpme.jpg");		
		Background reg_background = new Background(new BackgroundImage(shocked,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bg_size));
		reg_window.setBackground(reg_background);
		
		/* Creates starting position selection window */
		VBox sp_window_pane = new VBox();
		Label sp_label = new Label("Select starting position:");
		sp_label.setFont(Font.font("", FontWeight.BOLD, 38));
		sp_label.setStyle("-fx-text-fill: black;");
		sp_window_pane.setAlignment(Pos.CENTER);
		sp_window_pane.getChildren().add(sp_label);
		
		btn_sp_one = new Button("TOP LEFT");
		btn_sp_two = new Button("TOP RIGHT");
		btn_sp_three = new Button("BOTTOM LEFT");
		btn_sp_four = new Button("BOTTOM RIGHT");
		btn_sp_one.setPrefWidth(200);
		btn_sp_two.setPrefWidth(200);
		btn_sp_three.setPrefWidth(200);
		btn_sp_four.setPrefWidth(200);
		btn_sp_one.setPrefHeight(50);
		btn_sp_two.setPrefHeight(50);
		btn_sp_three.setPrefHeight(50);
		btn_sp_four.setPrefHeight(50);
		btn_sp_one.setFont(Font.font(20));
		btn_sp_two.setFont(Font.font(20));
		btn_sp_three.setFont(Font.font(20));
		btn_sp_four.setFont(Font.font(20));
		
		GridPane sp_grid = new GridPane();
		sp_grid.add(btn_sp_one, 0, 1);
		sp_grid.add(btn_sp_two, 1, 1);
		sp_grid.add(btn_sp_three, 0, 2);
		sp_grid.add(btn_sp_four, 1, 2);
		sp_grid.setAlignment(Pos.CENTER);
		
		sp_window = new BorderPane();
		sp_window.setTop(sp_window_pane);
		sp_window.setBottom(sp_grid);
		Scene sp_scene = new Scene(sp_window, 550, 550);
		
		
		/*
		 * https://i.redd.it/gm94k33xdonz.jpg
		 */
		Image sit = new Image("resources/sit.jpg");		
		Background sp_background = new Background(new BackgroundImage(sit,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bg_size));
		sp_window.setBackground(sp_background);
		
		
		/* Creates winner window */
		win_window = new BorderPane();
		Scene win_scene = new Scene(win_window, 550, 550);
		
		/*
		 * https://i.ytimg.com/vi/J67Pkgtp3so/maxresdefault.jpg
		 */
		Image win = new Image("resources/winner.jpg");		
		Background win_background = new Background(new BackgroundImage(win,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bg_size));
		win_window.setBackground(win_background);
		
		
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
				GameState.get_instance().set_server_ip(ip_entry.getText());
				GameState.get_instance().set_server_port(Integer.parseInt(port_entry.getText()));
				
				/* Waits for server to allocate player ID */
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
				/* Debug */
				System.out.println("PC_ID is: " + PC_id);
				
				/* Sets the player name */
				players.get_player(PC_id).set_name(name_entry.getText());
				
				/* Waiting for server to send avalaible spots */
				while (game_state.get_avaliable_spots() == "")
				{
					System.out.println("Waiting for avaliable spots");
					try { Thread.sleep(100); } catch (Exception ex) { }
				}
				
				/* Debug */
				System.out.println("Avaliable Spots: "+ game_state.get_avaliable_spots());
				
				if (!spot_avaliable("1,1"))
					btn_sp_one.setDisable(true);
				if (!spot_avaliable("1,9"))
					btn_sp_two.setDisable(true);
				if (!spot_avaliable("9,1"))
					btn_sp_three.setDisable(true);
				if (!spot_avaliable("9,9"))
					btn_sp_four.setDisable(true);
				
				game_stage.setScene(sp_scene);	
			}
		});
		
		/* When starting position button is pressed. */
		btn_sp_one.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				wait_avaliable_spots("1,1");				
				game_stage.setScene(game_scene);	
				game_stage.setTitle("Monster Game - Player: " + 
						players.get_player(PC_id).get_name());
				game_window.requestFocus();
				game_loop.play();
			}
		});
		
		/* When starting position button is pressed. */
		btn_sp_two.setOnAction(new EventHandler<ActionEvent>()
		{			
			@Override
			public void handle(ActionEvent e)
			{
				wait_avaliable_spots("1,9");
				game_stage.setScene(game_scene);
				game_stage.setTitle("Monster Game - Player: " + 
						players.get_player(PC_id).get_name());
				game_window.requestFocus();
				game_loop.play();
			}
		});
		
		/* When starting position button is pressed. */
		btn_sp_three.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				wait_avaliable_spots("9,1");		
				game_stage.setScene(game_scene);
				game_stage.setTitle("Monster Game - Player: " + 
						players.get_player(PC_id).get_name());
				game_window.requestFocus();
				game_loop.play();
			}
		});
		
		/* When starting position button is pressed. */
		btn_sp_four.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				wait_avaliable_spots("9,9");			
				game_stage.setScene(game_scene);
				game_stage.setTitle("Monster Game - Player: " + 
						players.get_player(PC_id).get_name());
				game_window.requestFocus();
				game_loop.play();
			}
		});
		
		/* Gameloop, called by timeline */
	    EventHandler<ActionEvent> eventHandler = e -> {
			
	    	/* Frees the gameboard in memory and recreates game board UI */
	    	board = null;
	    	System.gc();
	    	board = new UIBoard();
			board.update_board();
			game_window.setCenter(board.getBoard());
			
			/* Quits the game when winner is determined */
			if (game_state.won())
			{			
				for (int i = 0; i < 4; i++)
					banger[i].pause();
				game_stage.setScene(win_scene);
				winbang.play();
				game_loop.stop();
			}
	    };
	    
	    /* Kills the program when the GUI is closed */
	    game_stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	        @Override
	        public void handle(WindowEvent t) {
	            Platform.exit();
	            System.exit(0);
	        }
	    });
	    
	    /* Creates a game loop timeline that updates every 64 milliseconds */
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
	public void wait_avaliable_spots(String s)
	{
		while (game_state.get_avaliable_spots() == "")
		{
			System.out.println("Waiting for avaliable spots");
			try { Thread.sleep(100); } catch (Exception ex) {}
		}
		/* hardcoding im sorry */
		players.set_starter_spot(s);
		
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

