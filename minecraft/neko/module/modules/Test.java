package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class Test extends Module {
	private static Test instance;
	
	public Test() {
		super("Test", -1, Category.HIDE);
		this.instance=this;
	}
	
	public static Test getTest() {
		return instance;
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("§a§oTest activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oTest désactivé !");		
		super.onDisabled();
	}
	
	public void onAction() {
	}
}		
