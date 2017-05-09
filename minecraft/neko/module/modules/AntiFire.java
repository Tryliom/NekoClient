package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class AntiFire extends Module {
	public AntiFire() {
		super("AntiFire", -1, Category.PLAYER);
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("§a§oAntiFire activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oAntiFire désactivé !");
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (mc.thePlayer.isBurning() && mc.thePlayer.onGround)
			for (int i=0;i<40;i++)
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer());
	}
}
