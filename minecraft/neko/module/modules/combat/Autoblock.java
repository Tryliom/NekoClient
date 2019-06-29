package neko.module.modules.combat;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;

public class Autoblock extends Module {
	
	public Autoblock() {
		super("Autoblock", -1, Category.COMBAT, false);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (mc.thePlayer.getCurrentEquippedItem()!=null && mc.thePlayer.getCurrentEquippedItem().getItem()!=null) {
			if (mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword) {
				mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
			}
		}
	}	
	
	public void setValues() {
		this.values = "";
	}
}
