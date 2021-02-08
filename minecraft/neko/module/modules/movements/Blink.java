package neko.module.modules.movements;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.guicheat.clickgui.settings.Setting;
import neko.module.Category;
import neko.module.Module;
import neko.module.modules.combat.BowAimbot;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;

public class Blink extends Module {
	private EntityOtherPlayerMP fakePlayer = null;
	public static ArrayList<Packet> packet = new ArrayList<Packet>();
	public static double lastPosX;
	public static double lastPosY;
	public static double lastPosZ;
	public static float lastYaw;
	public static float lastPitch;
	public static boolean onGround;
	public static boolean full = false;
	
	public Blink() {
		super("Blink", -1, Category.MOVEMENT, false);
	}
	
	public void onEnabled() {		
		lastPosX=mc.thePlayer.posX;
		lastPosY=mc.thePlayer.posY;
		lastPosZ=mc.thePlayer.posZ;
		lastYaw=mc.thePlayer.rotationYaw;
		lastPitch=mc.thePlayer.rotationPitch;
		onGround=mc.thePlayer.onGround;
	    this.fakePlayer = new EntityOtherPlayerMP(mc.theWorld, mc.thePlayer.getGameProfile());
	    this.fakePlayer.clonePlayer(mc.thePlayer, true);
	    this.fakePlayer.copyLocationAndAnglesFrom(mc.thePlayer);
	    this.fakePlayer.rotationYawHead = mc.thePlayer.rotationYawHead;
	    mc.theWorld.addEntityToWorld(-2, this.fakePlayer);
		super.onEnabled();
	}
	
	public void onDisabled() {
	    mc.theWorld.removeEntityFromWorld(-2);
	    this.fakePlayer = null;
	    mc.theWorld.updateEntities();
	    new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				sendPacket();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {}
			}
		}).start();
		super.onDisabled();
	}		
	
	public void setValues() {
		this.values = "";
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
	
	@Override
	public void setup() {
		Client.getNeko().settingsManager.rSetting(new Setting("Blink_Full", this, full));
		
		super.setup();
	}
	
	public static boolean isValidPacket(Packet packet) {
		return full || ((Utils.isToggle("FastBow") && BowAimbot.getAim().haveBow()) 
				|| packet instanceof C03PacketPlayer
				|| packet instanceof C02PacketUseEntity 
				|| packet instanceof C08PacketPlayerBlockPlacement 
				|| packet instanceof C07PacketPlayerDigging 
				|| packet instanceof C0BPacketEntityAction 
				|| packet instanceof C09PacketHeldItemChange
				|| packet instanceof C13PacketPlayerAbilities);
	}
}
