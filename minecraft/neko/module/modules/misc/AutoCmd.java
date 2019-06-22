package neko.module.modules.misc;

import neko.module.Category;
import neko.module.Module;

public class AutoCmd extends Module {
	public static int sec = 5;
	public static String cmd = "";
	public int count = 0;

	public AutoCmd() {
		super("AutoCmd", -1, Category.MISC);
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		count = 0;
		super.onDisabled();
	}
	
	public void onUpdate() {
		count++;
		if (count>=sec*20) {
			mc.thePlayer.sendChatMessage(cmd);
			count = 0;
		}
	}
	
}
