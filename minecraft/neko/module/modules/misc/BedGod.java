package neko.module.modules.misc;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;

public class BedGod extends Module {
	private static BedGod instance;
	
	public BedGod() {
		super("BedGod", -1, Category.MISC);
		instance=this;
	}
	
	public static BedGod getBed() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		mc.thePlayer.sleeping = true;
		mc.thePlayer.sleepTimer = 20;
		super.onDisabled();		
	}		
	
	public void setValues() {
		this.values = "";
	}
	
	public void onUpdate() {	
		if (!this.mc.thePlayer.isPlayerSleeping()) {
	      return;
		}
		mc.thePlayer.sleeping = false;
		mc.thePlayer.sleepTimer = 0;
	}	
}








