
import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Bangers 
{
	public MediaPlayer media_player;
	public Media track;
	public Bangers() 
	{

	}
	
	public void loop_on()
	{
		media_player.setOnEndOfMedia(new Runnable() {
		       public void run() {
		         media_player.seek(Duration.ZERO);
		       }
		   });
	}
	
	public void pause()
	{
		media_player.pause();
	}
	
	public void play()
	{
		media_player.play();
	}
	
	public void load(String filename)
	{
		track = new Media(new File(filename).toURI().toString());
		this.media_player = new MediaPlayer(track);


	}

}
