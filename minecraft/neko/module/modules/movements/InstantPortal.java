package neko.module.modules.movements;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class InstantPortal extends Module {

	public InstantPortal() {
		super("InstantPortal", -1, Category.MOVEMENT, false);
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	@Override
	public void onUpdate() {
		if(mc.thePlayer.timeInPortal > 0) {
			for(int i = 0; i < 15; i++) {
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
			}
		}
		super.onUpdate();
	}

}
