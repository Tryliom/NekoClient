package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.gui.GuiNewChat;

public class NekoChat extends Module {
	private float height;
	private float width;
	private int color = 1;
	private static NekoChat instance;		
	
	public NekoChat() {
		super("NekoChat", -1, Category.RENDER);
	}
	
	public static NekoChat getChat() {
		if (instance==null)
			instance = new NekoChat();
		return instance;
	}
	
	public void onEnabled() {		
		if (u.display)
			u.addChat("§a§oNekoChat activé !");
		newChat();
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
			u.addChat("§c§oNekoChat désactivé !");
		super.onDisabled();
	}		
	
	public void newChat() {
		GuiNewChat chat = new GuiNewChat(mc);		
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	
	
}
