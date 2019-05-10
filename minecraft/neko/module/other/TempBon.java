package neko.module.other;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import neko.Client;
import neko.utils.Utils;

public class TempBon {
	public static int delay;
	public static Timer t = new Timer(1000, new temp());
	
	public TempBon(int d) {
		delay=d;
		if (!t.isRunning())
			t.start();
	}
}

class temp implements ActionListener {
	Client var = neko.Client.getNeko();
	@Override
	public void actionPerformed(ActionEvent arg0) {
		TempBon.delay--;
		if (TempBon.delay<=0) {
			var.tempBonus=0;
			Utils.addChat("§cBonus temporaire finis !");
			TempBon.t.stop();
		}
		
	}
	
}
