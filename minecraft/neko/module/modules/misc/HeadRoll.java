package neko.module.modules.misc;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class HeadRoll extends Module {
	public HeadRoll() {
		super("HeadRoll", -1, Category.MISC);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onUpdate() {
		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(mc.thePlayer.rotationYaw, (float)Math.sin(mc.thePlayer.ticksExisted % 20 / 10.0D * 3.141592653589793D) * 90.0F, mc.thePlayer.onGround));
	}
}
