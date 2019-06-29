package neko.module.modules.render;

import neko.Client;
import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class NoBlind extends Module {
	
	public NoBlind() {
		super("NoBlind", -1, Category.RENDER, false);
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
		if (mc.thePlayer.getActivePotionEffect(Potion.blindness) != null && !var.rang.getName().equalsIgnoreCase("Ange déchu")) {
			mc.thePlayer.removePotionEffect(Potion.blindness.getId());
		}
		if (mc.thePlayer.getActivePotionEffect(Potion.confusion) != null) {
			mc.thePlayer.removePotionEffect(Potion.confusion.getId());
		}
	}
	
	public void onAction() {
		if (mc.thePlayer!=null && var.rang.getName().equalsIgnoreCase("Ange déchu")) {
			mc.thePlayer.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 5));			
		}
	}
}
