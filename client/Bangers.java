
import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Bangers 
{
	MediaPlayer media_player;
	Media track;
	public Bangers() 
	{

	}
	public void play(String filename)
	{
		track = new Media(new File(filename).toURI().toString());
		this.media_player = new MediaPlayer(track);
		media_player.setOnEndOfMedia(new Runnable() {
		       public void run() {
		         media_player.seek(Duration.ZERO);
		       }
		   });
		
		media_player.play();
	}

}
