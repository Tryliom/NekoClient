package neko.module.other;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Active {
	public static double bonus=0;
	public static int time=0;
	public static javax.swing.Timer t = new Timer(1000, new actif());
	
	public Active(double bonus, int time) {
		this.bonus = bonus;
		this.time = time;
		t.start();
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public long getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}	
}

class actif implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Active.time<=0) {			
			Active.bonus=0;
			Active.t.stop();
		} else if (Active.time>7200) {
			Active.time=7200;
		} else
			Active.time--;
		
	}
	
}
