import javafx.application.Application;


public class GameClient extends Thread
{	
	static Board board;
	static Players players;
	
	public static void main(String[] args) throws InterruptedException
	{
		// Initialize
		Initialize();
		
		// Startup the net thread
		Thread listener = new NetClient();
		listener.start();
		
		// Start the main game loop
		GameLoop();
	}
	
	public static void Initialize()
	{		
		board = GameState.get_instance().get_board();
		players = GameState.get_instance().get_players();
	}
		

	public static void GameLoop() throws InterruptedException
	{
		// Gameloop runs within the JavaFX GUI
		Application.launch(UIWindow.class);
	}
}
