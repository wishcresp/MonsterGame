import java.net.*;
import java.io.*;


public class Player
{
	private static Player player = new Player();

	private int player_target = 4;
	private int current_players = 0;

	private Entity Players[];

	private Player()
	{
	}


	public int get_player_target()
	{
		return this.player_target;
	}

	public void set_player_dir(int id, int dir)
	{
		Players[id].set_dir(dir);
	}

	public Entity get_player(int id)
	{
		return Players[id];
	}


	public void create_players()
	{
		Players = new Entity[player_target];
		for (int i = 0; i < player_target; i++)
			Players[i] = new Entity();
	}

	public static Player get_player_instance()
	{
		return player;
	}
}
