package neko.manager;

import java.io.IOException;
import java.net.URL;
import java.util.function.Consumer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.advanced.AdvancedPlayer;
import neko.module.other.JLayerPlayerPausable;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;
import paulscode.sound.SoundSystem;

public class SoundManager {
	private static SoundManager instance = null;
	// Liste des musiques
//	private ArrayList<Music> list = new ArrayList<>();
	private SoundJLayer currAudio;
	public boolean canStart = false;
	public boolean stopByButton = false;
	public int timeUntilRestart = 10;
	public Consumer<String> c = (String s) -> {
		try {			
			currAudio = new SoundJLayer(s);					
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			currAudio.play();
			canStart=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public static SoundManager getSM() {
		return instance == null ? instance = new SoundManager() : instance;
	}
	
	public SoundManager() {
		this.startNewMusic();
	}
	
	public void stopMusic() {
		if (this.currAudio!=null && this.currAudio.player!=null && !this.currAudio.player.isPaused) {
			this.currAudio.pause();
			this.currAudio.player.close();
		}
		stopByButton = true;
	}
	
	public boolean isActive() {
		return canStart && !stopByButton && this.currAudio!=null;
	}
	
	public void restartMusic() {
			this.canStart=false;	
			stopByButton = false;
			if (this.currAudio.player!=null)
				this.currAudio.player.close();
			this.startNewMusic();
	}	
	
	private void startNewMusic() {
		c.accept("https://nekohc.fr/song/test.mp3");
	}

}

class SoundJLayer extends JLayerPlayerPausable.PlaybackListener implements Runnable
{
	private String filePath;
	public JLayerPlayerPausable player;
	private Thread playerThread;	

	public SoundJLayer(String filePath)
	{
		this.filePath = filePath;	
	}

	public void pause()
	{
		this.player.pause();

		this.playerThread.interrupt();
		this.playerThread = null;
	}

	public void pauseToggle()
	{
		if (this.player.isPaused == true)
		{
			this.play();
		}
		else
		{
			this.pause();
		}
	}

	public void play()
	{
		if (this.player == null)
		{
			this.playerInitialize();
		}

		this.playerThread = new Thread(this, "AudioPlayerThread");

		this.playerThread.start();
	}

	private void playerInitialize()
	{
		try
		{
			String urlAsString = this.filePath;

			this.player = new JLayerPlayerPausable
			(
				new java.net.URL(urlAsString),
				this 
			);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	// PlaybackListener members

	public void playbackStarted(JLayerPlayerPausable.PlaybackEvent playbackEvent)
	{
		System.out.println("playbackStarted()");
	}

	public void playbackFinished(JLayerPlayerPausable.PlaybackEvent playbackEvent)
	{
		System.out.println("playbackEnded()");
	}	

	// IRunnable members

	public void run()
	{
		try
		{
			this.player.resume();
		}
		catch (javazoom.jl.decoder.JavaLayerException ex)
		{
			ex.printStackTrace();
		}

	}
}
