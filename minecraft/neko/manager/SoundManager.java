package neko.manager;

import java.net.URL;
import java.util.Scanner;
import java.util.Vector;
import java.util.function.Consumer;

import neko.module.other.JLayerPlayerPausable;
import neko.module.other.Music;
import neko.module.other.enums.MusicMode;

public class SoundManager {
	private static SoundManager instance = null;
	// Liste des musiques
	private Vector<Music> list = new Vector<Music>();
	public static MusicMode mm;
	public static String currPath = "";
	private SoundJLayer currAudio;
	public boolean canStart = false;
	public boolean stopByButton = false;
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
		this.searchMusicList();
		if (this.list.size()!=0) {
			this.currPath = this.list.get(0).getPath();
			this.startNewMusic();
		}
		this.mm = MusicMode.Loop;
	}
	
	public void searchMusicList() {
		Vector<Music> list = new Vector<Music>();
		try {				
			// Récupère la liste des musiques dispo via une token
			URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=m6664fc558b2507c6b63b09c22c75715");
			Scanner sc = new Scanner(url.openStream(), "UTF-8");		
			String l;
			String var1 = "";
			String var2 = "";
			String var3 = "";
			try {
				while ((l = sc.nextLine()) != null) {
					String sr[] = l.split("<br>");
					for (int i = 0;i<sr.length;i++) {
						if (sr[i].startsWith("name=")) {
							var1=sr[i].replaceFirst(".....", "");
						}
						if (sr[i].startsWith("time=")) {
							var2=sr[i].replaceFirst(".....", "");
						}
						if (sr[i].startsWith("path=")) {
							var3=sr[i].replaceFirst(".....", "");
						}
						if (!var3.isEmpty()) {
							list.add(new Music(var1, var2, var3));
							var1 = "";
							var2 = "";
							var3 = "";
						}
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Erreur BDD: Get Sound");
		}			
		System.out.println(list.size());
		this.list = list;
	}
	
	public Vector<Music> getList() {
		return this.list;
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
	
	public void startNewMusic() {
		if (this.currPath.isEmpty()) {
			this.searchMusicList();
			if (this.list.size()!=0) {
				this.currPath = this.list.get(0).getPath();
				this.startNewMusic();
			}
		}
		c.accept(this.currPath);
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
