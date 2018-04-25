package neko.module.modules.combat;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;

public class Knockback extends Module {
	
	public Knockback() {
		super("Knockback", -1, Category.COMBAT);
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
