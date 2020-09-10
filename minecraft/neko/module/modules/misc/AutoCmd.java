package neko.module.modules.misc;

import neko.module.Category;
import neko.module.Module;
import neko.utils.ChatUtils;

public class AutoCmd extends Module {
	public static double sec = 5;
	public static String cmd = "";
	public int count = 0;

	public AutoCmd() {
		super("AutoCmd", -1, Category.MISC, false);
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
			new ChatUtils().doCommand(cmd);
			count = 0;
		}
	}
	
}
