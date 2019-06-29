package neko.module.modules.misc;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;

public class AutoMLG extends Module {
	private static AutoMLG instance;
	private double fall = 2;
	
	public AutoMLG() {
		super("AutoMLG", -1, Category.MISC, false);
		instance=this;
	}
	
	public static AutoMLG getMLG() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}		
	
	public void setValues() {
		this.values = "";
	}
	
	public void onUpdate() {	
		boolean bucket=false;
		try {
			Item i = mc.thePlayer.getCurrentEquippedItem().getItem();
			if (Item.getIdFromItem(i)==326 || Item.getIdFromItem(i)==325) {
				if (Item.getIdFromItem(i)==325)
					bucket=true;
			} else
				return;			
		} catch (Exception e) {
			return;
		}
		int i;
		double a = mc.thePlayer.posX;
		double b = mc.thePlayer.posY;
		double c = mc.thePlayer.posZ;
		boolean work=true;
		if (mc.thePlayer.fallDistance>=fall) {
			for (i=-1;work;i--) {
				if (mc.theWorld.getBlockState(new BlockPos(a, b+i, c)).getBlock().getMaterial()!=Material.air && mc.theWorld.getBlockState(new BlockPos(a, b+i+1, c)).getBlock().getMaterial()!=Material.water && mc.thePlayer.getDistance(a, b+i, c)<5) {
					work=false;
				}
				if (i<-20)
					return;
			}
			mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(mc.thePlayer.rotationYaw, 95.0F, mc.thePlayer.onGround));	
	        Minecraft.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(Minecraft.thePlayer.inventory.getCurrentItem()));
		} else if (bucket && mc.thePlayer.isInWater()) {
			mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(mc.thePlayer.rotationYaw, 95.0F, mc.thePlayer.onGround));	
	        Minecraft.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(Minecraft.thePlayer.inventory.getCurrentItem()));
		}
	}

	public double getFall() {
		return fall;
	}

	public void setFall(double fall) {
		this.fall = fall;
	}	
}








