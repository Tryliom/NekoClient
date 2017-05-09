package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C03PacketPlayer.C05PacketPlayerLook;

public class NoLook extends Module {
	public NoLook() {
		super("NoLook", -1, Category.Special);
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("§a§oNoLook activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oNoLook désactivé !");
		super.onDisabled();
	}
	
	public void onUpdate() {
		float yaw = mc.thePlayer.rotationYaw%360+180;
		float pitch = mc.thePlayer.rotationPitch%360+180;
		
		if (yaw>360) {
			yaw-=360;
		}
		if (pitch>360) {
			pitch-=360;
		}
		mc.getNetHandler().addToSendQueue(new C05PacketPlayerLook(pitch, yaw, mc.thePlayer.onGround));
	}
}
