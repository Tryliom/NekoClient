package neko.module.modules.player;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class Sneak extends Module {
	Minecraft mc = Minecraft.getMinecraft();

	public Sneak() {
		super("Sneak", -1, Category.PLAYER);
	}
	
	public void onEnabled() {
		if (u.isLock(this.getName()))
			return;
		super.onEnabled();
	}
	
	public void onDisabled() {
		mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (u.isLock(this.getName())) {
			boolean display = u.display;
			u.display=false;
			this.isToggled=false;
			u.display=display;
			u.addWarn(this.getName());
			return;
		}
		
	}
}
