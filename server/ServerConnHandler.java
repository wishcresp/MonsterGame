import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerConnHandler extends ConnHandler
{

	public ServerConnHandler(Socket conn, int id) throws IOException 
	{
		super(conn, id);
	}

	@Override
	public void conn_work(Board board, Players players) throws InterruptedException 
	{
		// Send game info to client
		send_string(Integer.toString(board.get_dimensions()));

		boolean running = true;
		while (running)
		{
			// Get desired player.direction from client
			String dir = get_string();
			//System.out.println("Setting player dir to "+dir);
			dir = dir.replaceAll("\\D+","");
			int direction = Integer.valueOf(dir);
			players.set_player_dir(id, direction);

			// Send board information to client
			send_string(board.get_layout());

			Thread.sleep(100);

		}	
	}

}
