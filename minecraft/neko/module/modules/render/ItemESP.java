package neko.module.modules.render;

import neko.Client;
import neko.module.Category;
import neko.module.Module;
import neko.utils.RenderUtils;
import net.minecraft.entity.item.EntityItem;

public class ItemESP extends Module {
	public static float cR=0.99F;
	public static float cG=0.44F;
	public static float cB=0.44F;
	public static float width=2F;
	public static float clR=0.99F;
	public static float clG=0.44F;
	public static float clB=0.44F;
	Client var = Client.getNeko();
	
	public ItemESP() {
		super("ItemESP", -1, Category.RENDER);
	}	
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "§6Couleur de ligne R:§7 "+clR+"\n"
				+ "§6Couleur de ligne G:§7 "+clG+"\n"
				+ "§6Couleur de ligne B:§7 "+clB+"\n"
				+ "§6Couleur R:§7 "+cR+"\n"
				+ "§6Couleur G:§7 "+cG+"\n"
				+ "§6Couleur B:§7 "+cB+"\n"
				+ "§6Epaisseur bord:§7 "+width+"\n";
	}
	
	public void onRender3D() {
		for (Object o : mc.theWorld.loadedEntityList) {
			if (o instanceof EntityItem) {
				EntityItem entity = (EntityItem) o;     
	        	RenderUtils.drawEntityESP(u.getX(entity), u.getY(entity), u.getZ(entity), entity.width, entity.height+0.3F, cR, cG, cR, 0.2F, clR, clG, clB, 0.2F, width);
			}
        }
	}
}
