package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.client.C03PacketPlayer;

public class PushUp extends Module {
	private int packet=100;
	private static PushUp instance;
	
	public PushUp() {
		super("PushUp", -1, Category.PLAYER);
		instance=this;
	}
	
	public static PushUp getPush() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (Utils.getEntityInRange(1.2)!=null)
			for (int i=0;i<packet;i++)
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(mc.thePlayer.onGround));
	}

	public int getPacket() {
		return packet;
	}

	public void setPacket(int packet) {
		this.packet = packet;
	}
	
	
}
