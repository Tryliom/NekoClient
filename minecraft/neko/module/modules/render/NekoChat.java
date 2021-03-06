package neko.module.modules.render;

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
		super("NekoChat", -1, Category.RENDER, false);
	}
	
	public static NekoChat getChat() {
		if (instance==null)
			instance = new NekoChat();
		return instance;
	}
	
	public void onEnabled() {		
		newChat();
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}		
	
	public void setValues() {
		this.values = "?6Couleur:?7 "+color+"\n"
				+ "?6Hauteur:?7 "+height+"\n"
				+ "?6Largeur:?7 "+width;
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
