package neko.module.modules.misc;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;

public class Autonyah extends Module {
	Minecraft mc = Minecraft.getMinecraft();

	public Autonyah() {
		super("Autonyah", -1, Category.MISC);
	}
	
	public void onEnabled() {		
		u.nyah=true;
		super.onEnabled();
	}
	
	public void onDisabled() {
		u.nyah=false;
		super.onDisabled();
	}
	
	public String getValues() {
		return "§6Préfix du "+var.prefixCmd+"nyah:§7 "+Utils.nyahh+"\n"
				+ "§6Temps entre chaque nyah:§7 "+Utils.nyahSec+"sec";
	}
}
