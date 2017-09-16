package neko.module.modules;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.item.ItemSword;

public class Autoblock extends Module {
	
	public Autoblock() {
		super("Autoblock", Keyboard.KEY_NONE, Category.COMBAT);
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
				mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, mc.thePlayer.getCurrentEquippedItem());
			}
		}
		
		
	}	
}
