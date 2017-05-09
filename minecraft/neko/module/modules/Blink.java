package neko.module.modules;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.Packet;

public class Blink extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	private EntityOtherPlayerMP fakePlayer = null;
	public static ArrayList<Packet> packet = new ArrayList<Packet>();
	public static boolean isOn;
	public static double lastPosX;
	public static double lastPosY;
	public static double lastPosZ;
	
	public Blink() {
		super("Blink", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("§a§oBlink activé !");
		lastPosX=mc.thePlayer.posX;
		lastPosY=mc.thePlayer.posY;
		lastPosZ=mc.thePlayer.posZ;
	    this.fakePlayer = new EntityOtherPlayerMP(mc.theWorld, mc.thePlayer.getGameProfile());
	    this.fakePlayer.clonePlayer(mc.thePlayer, true);
	    this.fakePlayer.copyLocationAndAnglesFrom(mc.thePlayer);
	    this.fakePlayer.rotationYawHead = mc.thePlayer.rotationYawHead;
	    mc.theWorld.addEntityToWorld(-2, this.fakePlayer);
	    isOn=true;
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oBlink désactivé !");
	    mc.theWorld.removeEntityFromWorld(-2);
	    this.fakePlayer = null;
	    isOn=false;
	    sendPacket();
	    mc.theWorld.updateEntities();
		super.onDisabled();
	}		
	
	public void sendPacket() {
		if (packet!=null && packet.size()!=0) {
			for (int k=0;k<packet.size();k++) {
				mc.thePlayer.sendQueue.addToSendQueue(packet.get(k));
			}
			packet.clear();
			return;
		}
	}
}
