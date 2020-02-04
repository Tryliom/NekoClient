package neko.module.modules.player;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class AutoCraft extends Module {
	
	public AutoCraft() {
		super("AutoCraft", -1, Category.PLAYER, false);
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
		
	}
}
