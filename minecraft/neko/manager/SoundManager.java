package neko.manager;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import neko.utils.Utils;

public class SoundManager {
	private static SoundManager instance = null;
	private Clip clip = null;
	private AudioInputStream audioIn;
	private int maxMusic = 0;
	public boolean canStart = false;

	public static SoundManager getSM() {
		return instance == null ? instance = new SoundManager() : instance;
	}
	
	public SoundManager() {
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				while (true)
//					try {
//						int i = Utils.getRandInt(10);
//						audioIn = AudioSystem.getAudioInputStream(new URL("http://nekohc.fr/song/music"+i+".wav"));
//						break;
//					} catch (UnsupportedAudioFileException | IOException e) {
//						continue;
//					}
//				try {
//					SoundManager.getSM().clip = AudioSystem.getClip();
//					SoundManager.getSM().clip.open(SoundManager.getSM().audioIn);
//					SoundManager.getSM().clip.loop(999);
//					FloatControl gainControl = (FloatControl) SoundManager.getSM().clip.getControl(FloatControl.Type.MASTER_GAIN);        
//			        gainControl.setValue(20f * (float) Math.log10(0.02f));
//			        SoundManager.getSM().clip.start();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//					
//			}
//		}).start();
	}

}
