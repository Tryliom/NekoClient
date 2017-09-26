package neko.module.modules.render;

import neko.Client;
import neko.module.Category;
import neko.module.Module;
import neko.module.modules.hide.Friends;
import neko.utils.RenderUtils;
import net.minecraft.entity.EntityLivingBase;

public class Tracers extends Module {
	public static float cR=0.99F;
	public static float cG=0.33F;
	public static float cB=0.33F;
	public static float width=2F;
	public static boolean friend=true;
	Client var = Client.getNeko();
	
	public Tracers() {
		super("Tracers", -1, Category.RENDER);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onRender3D() {
		boolean bob = mc.gameSettings.viewBobbing;
		mc.gameSettings.viewBobbing = false;
		for (Object o : var.mode.equals("Player") ? mc.theWorld.playerEntities : mc.theWorld.loadedEntityList) {
			if (o instanceof EntityLivingBase) {
				EntityLivingBase entity = (EntityLivingBase) o;     
	        	if (!entity.isDead && entity!=mc.thePlayer && !entity.getName().isEmpty() && (friend ? true : !Friends.isFriend(entity.getName())) && (var.mode.equals("Mob") ? !u.isPlayer(entity) : true)) {
	        		RenderUtils.drawTracerLine(u.getX(entity), u.getY(entity) + 1F, u.getZ(entity), cR, cG, cB, 0.5F, width);
	        	}
			}
        }
		mc.gameSettings.viewBobbing = bob;
	}
}
