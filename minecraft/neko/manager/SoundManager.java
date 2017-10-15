package neko.manager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {
	private static SoundManager instance = null;
	Clip clip = null;
	AudioInputStream audioIn;

	public static SoundManager getSM() {
		return instance == null ? instance = new SoundManager() : instance;
	}
	
	public SoundManager() {
		try {
			audioIn = AudioSystem.getAudioInputStream(new URL("http://nekohc.fr/controler/Neko/music.wav"));
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
	}

	public void startMusic() {
		if (clip == null || !clip.isRunning()) {
			try {
				clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.loop(999);
				clip.start();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stopMusic() {
		if (clip!=null && clip.isActive())
			clip.close();
	}

}
