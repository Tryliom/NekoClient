package neko.module.modules.player;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemFood;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;

public class PotionSaver extends Module {
	
	public PotionSaver() {
		super("PotionSaver", -1, Category.PLAYER, false);
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
}
