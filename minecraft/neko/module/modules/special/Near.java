package neko.module.modules.special;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;

public class Near extends Module {
	public static BlockPos spawn = new BlockPos(0, 70, 0);
	public static int radius = 0;
	public static boolean say=false;
	public static boolean noname=false;
	
	public Near() {
		super("Near", -1, Category.Special, false);
	}
	
	public void onEnabled() {	
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}	
	
	public void setValues() {
		this.values = "§6Point de départ:§7 "+spawn+"\n"+
				"§6Radius autour du point de départ à ignorer:§7 "+radius+"\n"+
				"§6Say:§7 "+Utils.displayBool(say)+"\n"+
				"§6Copie sans les pseudos:§7 "+Utils.displayBool(noname);
	}

}
