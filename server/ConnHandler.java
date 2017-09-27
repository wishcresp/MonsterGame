import java.net.*;
import java.io.*;

public abstract class ConnHandler extends Thread 
{
	private Socket conn;
	protected int id;
	protected OutputStream out;
	protected InputStream in;

	public ConnHandler(Socket conn, int id) throws IOException 
	{
		this.id = id;
		this.conn = conn;
		in = conn.getInputStream();
		out = conn.getOutputStream();
	}

	public String get_string() 
	{
		try 
		{
			byte[] buf = new byte[1024];
			while (in.read(buf) == -1)
				; // Give the client ``some`` time
			String input = new String(buf, "UTF-8");
			return input.trim();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return "Something happened :(";
	}

	public boolean send_string(String data) 
	{
		try 
		{
			data = data + "\0";
			byte[] buf = data.getBytes("US-ASCII");
			out.write(buf);
			return true;
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	public abstract void conn_work(Board board, Players players) throws InterruptedException;

	public void run() 
	{
		Board board = Board.get_board_instance();
		Players players = Players.get_player_instance();

		try 		
		{
			System.out.println("Just connected to " + this.conn.getRemoteSocketAddress().toString());
			conn_work(board, players);
			conn.close();			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}		
		System.out.println("Disconnected");
	}
}