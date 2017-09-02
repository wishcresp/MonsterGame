import java.net.*;
import java.io.*;


public abstract class ConnHandler extends Thread
{
	private Socket conn;
	protected int id;

	public ConnHandler(Socket conn, int id)
	{
		this.id = id;
		this.conn = conn;
	}


	protected String get_string(InputStream in)
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

	protected boolean send_string(OutputStream out, String data)
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

	
	public abstract void conn_work(Board board, Player players, InputStream in,
			                       OutputStream out) throws InterruptedException; 

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


			conn_work(board, players, in, out);
			
			conn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Disconnected");
	}

}
