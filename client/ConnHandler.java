import java.net.*;
import java.io.*;


//Provide a basic interface for sending and recieving data over the network

public abstract class ConnHandler extends Thread
{
	protected Socket conn;
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
			while (in.read(buf) == -1); // Give the client ``some`` time
			String input = new String(buf, "UTF-8");
			return input.trim();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("Server crashed... Exiting...");
			System.exit(1);
		}
		return "Something happened :(";
	}

	public boolean send_string(String data)
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

	
	public abstract void conn_work(Board board, Players players) throws InterruptedException, IOException; 

	public void run()
	{

		Board board = GameState.get_instance().get_board();	
		Players players = GameState.get_instance().get_players();	

		try
		{

			System.out.println("Just connected to " +
			                   this.conn.getRemoteSocketAddress().toString());

			conn_work(board, players);
			
			conn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Disconnected");
	}

}
