import java.net.*;
import java.io.*;


public class Players
{
	/*
	 * Contains the array of players with the monster as the final player
	 * entity
	 * 
	 * As far as direction goes, the direction the monster is moving in
	 * is 
	 */
	
	private static Players players = new Players();
	private Players()
	{
			return;
	}
	
	// Required for Server reasons
	private int player_target = 4;
	private int current_players = 0;

	private Entity Players[];




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
		int i;
		Players = new Entity[player_target+1]; // One extra for the monster
		for (i = 0; i < player_target; i++)
			Players[i] = new Player();
		Players[i++] = new Monster();
	}

	public static Players get_player_instance()
	{
		return players;
	}
}
