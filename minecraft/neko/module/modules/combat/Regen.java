package neko.module.modules.combat;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;

public class Regen extends Module {
	public static boolean bypass=false;
	public static int regen=100;
	private int delay=0;
	
	public Regen() {
		super("Regen", Keyboard.KEY_G, Category.COMBAT, false);
	}
		
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "�6Packet:�7 "+regen+"\n"
				+ "�6Bypass:�7 "+(bypass ? "�aActiv�" : "�cD�sactiv�");
	}
	
	public void onUpdate() {
		if (delay<20) {
			delay++;
			return;
		}
		delay=0;
		int reg=0;
		if (!bypass) {
			if(!mc.thePlayer.capabilities.isCreativeMode
					&& mc.thePlayer.getFoodStats().getFoodLevel() > 17
					&& mc.thePlayer.getHealth() < mc.thePlayer.getMaxHealth() && mc.thePlayer.getHealth() != 0
					&& mc.thePlayer.onGround)
					for(int i = 0; i < regen; i++) {
						mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer());
					}
		} else {
			int rand = (int) Math.random()*4-2;
			if (regen+rand<=0)
				reg=regen-rand;
			if (mc.thePlayer.isPotionActive(Potion.regeneration) && mc.thePlayer.getActivePotionEffect(Potion.regeneration).getDuration() != 0) {
				if(!mc.thePlayer.capabilities.isCreativeMode
						&& mc.thePlayer.getFoodStats().getFoodLevel() > 17
						&& mc.thePlayer.getHealth() < mc.thePlayer.getMaxHealth() && mc.thePlayer.getHealth() != 0
						&& mc.thePlayer.onGround)
						for(int i = 0; i < reg+rand; i++) {
							mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer());
						}
			}
		}
	}

}
