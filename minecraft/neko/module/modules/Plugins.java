package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C14PacketTabComplete;

public class Plugins extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	public static int count=0;
	
	public Plugins() {
		super("Plugins", -1, Category.HIDE);
	}
	
	public void onEnabled() {		
		mc.thePlayer.sendQueue.addToSendQueue(new C14PacketTabComplete("/"));
		super.onEnabled();
	}
	
	public void onUpdate() {
		if (this.getToggled())
			count++;
		
		if (count>=120) {
			count=0;
			u.addChat("§cAucun plugins trouvés");
			this.toggleModule();
		}
	}
	
	
	
}
