import java.net.*;
import java.io.*;


public class ConnHandler extends Thread
{
	private Socket conn;
	private int id;

	public ConnHandler(Socket conn, int id)
	{
		this.id = id;
		this.conn = conn;
	}


	private String get_string_from_client(InputStream in)
	{
		try
		{
			byte[] buf = new byte[1024];
			while (in.read(buf) == -1); // Give the client ``some`` time
			String input = new String(buf, "UTF-8");
			return input;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return "Something happened :(";
	}

	private boolean send_string_to_client(OutputStream out, String data)
	{
		try
		{
			data = data+"\0";

			byte[] buf = data.getBytes("US-ASCII");
			out.write(buf);

			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}


	public void run()
	{

		Board board = Board.get_board_instance();
		Player players = Player.get_player_instance();

		try
		{

			System.out.println("Just connected to " +
			                   this.conn.getRemoteSocketAddress().toString());
			InputStream in = conn.getInputStream();
			OutputStream out = conn.getOutputStream();


			// Send game info to client
			send_string_to_client(out, Integer.toString(board.get_dimensions()));

			boolean running = true;
			while (running)
			{
				// Get desired player.direction from client
				String dir = get_string_from_client(in);
				//System.out.println("Setting player dir to "+dir);
				dir = dir.replaceAll("\\D+","");
				int direction = Integer.valueOf(dir);
				players.set_player_dir(id, direction);

				// Send board information to client
				send_string_to_client(out, board.get_layout());

				Thread.sleep(100);

			}
			conn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Client disconnected");
	}

}
