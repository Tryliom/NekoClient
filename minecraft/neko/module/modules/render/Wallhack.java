package neko.module.modules.render;

import neko.Client;
import neko.gui.InGameGui;
import neko.module.Category;
import neko.module.Module;
import neko.utils.RenderUtils;
import neko.utils.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class Wallhack extends Module {
	public static float cR=0.99F;
	public static float cG=0.33F;
	public static float cB=0.33F;
	public static float width=2F;
	public static float clR=0.33F;
	public static float clG=0.33F;
	public static float clB=0.33F;
	Client var = Client.getNeko();
	
	public Wallhack() {
		super("Wallhack", -1, Category.RENDER);
	}	
	
	public void onEnabled() {		
		
		if (u.isLock(this.getName()))
			return;
		
		super.onEnabled();
	}
	
	public void onDisabled() {
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
		
		for (Object o : var.mode.equals("Player") ? mc.theWorld.playerEntities : mc.theWorld.loadedEntityList) {
			if (o instanceof EntityLivingBase) {
				EntityLivingBase entity = (EntityLivingBase) o;     
	        	if (entity!=mc.thePlayer && (var.mode.equals("Mob") ? !u.isPlayer(entity) : true))
	        		RenderUtils.drawEntityESP(u.getX(entity), u.getY(entity), u.getZ(entity), entity.width, entity.height+0.3F, cR, cG, cR, 0.2F, clR, clG, clB, 0.2F, width);
			}
        }
	}
}
