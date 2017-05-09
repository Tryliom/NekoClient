package neko.module.modules;

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
		
		if (u.isLock(this.getName()))
			return;
		
		if (u.display)
			u.addChat("§a§oItemESP activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
			u.addChat("§c§oItemESP désactivé !");
		super.onDisabled();
	}
	
	public void onRender3D() {
		if (u.isLock(this.getName())) {
			boolean display = u.display;
			u.display=false;
			this.isToggled=false;
			u.display=display;
			u.addWarn(this.getName());
			return;
		}
		
		for (Object o : mc.theWorld.loadedEntityList) {
			if (o instanceof EntityItem) {
				EntityItem entity = (EntityItem) o;     
	        	RenderUtils.drawEntityESP(u.getX(entity), u.getY(entity), u.getZ(entity), entity.width, entity.height+0.3F, cR, cG, cR, 0.2F, clR, clG, clB, 0.2F, width);
			}
        }
	}
}
