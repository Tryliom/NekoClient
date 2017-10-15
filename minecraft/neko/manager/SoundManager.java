package neko.manager;

import java.io.IOException;
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
	Thread curr = null;

	public static SoundManager getSM() {
		return instance == null ? instance = new SoundManager() : instance;
	}

	public void startMusic() {
		if (curr == null || !curr.isAlive())
		curr = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
//					AudioInputStream in = AudioSystem.getAudioInputStream(new URL("http://nekohc.fr/controler/Neko/Nausicaah.mp3"));
//					AudioInputStream din = null;
//					AudioFormat baseFormat = in.getFormat();
//					AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(),
//							16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
//					din = AudioSystem.getAudioInputStream(decodedFormat, in);
//					// Play now.
//					rawplay(decodedFormat, din);
//					in.close();
					AudioInputStream audioIn = AudioSystem
							.getAudioInputStream(new URL("http://nekohc.fr/controler/Neko/Nausicaah.mp3"));
					Clip clip = AudioSystem.getClip();
					clip.open(audioIn);
					clip.start();
					clip.loop(999999);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}				
			}
		});		
		curr.start();
	}
	
	private void rawplay(AudioFormat targetFormat, AudioInputStream din) throws IOException, LineUnavailableException {
		byte[] data = new byte[4096];
		SourceDataLine line = getLine(targetFormat);
		if (line != null) {
			// Start
			line.start();
			int nBytesRead = 0, nBytesWritten = 0;
			while (nBytesRead != -1) {
				nBytesRead = din.read(data, 0, data.length);
				if (nBytesRead != -1)
					nBytesWritten = line.write(data, 0, nBytesRead);
			}
			// Stop
			line.drain();
			line.stop();
			line.close();
			din.close();
		}
	}

	private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException {
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		res = (SourceDataLine) AudioSystem.getLine(info);
		res.open(audioFormat);
		return res;
	}
}
