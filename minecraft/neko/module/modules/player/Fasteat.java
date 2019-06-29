package neko.module.modules.player;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Fasteat extends Module {
	private int packet=25;
	private static Fasteat instance=null;
	
	public Fasteat() {
		super("Fasteat", -1, Category.PLAYER, false);
		instance=this;
	}
	
	public static Fasteat getFast() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "§6Packet:§7 "+packet;
	}
	
	public void onUpdate() {
		if (!this.getToggled())
			return;
		
		if (mc.thePlayer.isUsingItem()) {
			ItemStack s = mc.thePlayer.getItemInUse();
			if (s==null) 
				return;
			if (!mc.thePlayer.onGround)
				return;
			if ((s.getItem() instanceof ItemFood)) {} else
			if ((s.getItem() instanceof ItemPotion)) {} else
			if ((s.getItem() instanceof ItemBucketMilk)) {} else
				return;
			for (int i = 0; i < packet; i++) {
		        Minecraft.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
		    }
		}
	}

	public int getPacket() {
		return packet;
	}

	public void setPacket(int packet) {
		this.packet = packet;
	}
	
	
}
