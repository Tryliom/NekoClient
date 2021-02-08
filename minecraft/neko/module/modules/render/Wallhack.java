package neko.module.modules.render;

import neko.Client;
import neko.gui.InGameGui;
import neko.manager.ModuleManager;
import neko.module.Category;
import neko.module.Module;
import neko.module.other.ModeType;
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
		super("Wallhack", -1, Category.RENDER, false);
	}	
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "§6Couleur R:§7 "+Wallhack.cR+"\n"
		+ "§6Couleur G:§7 "+Wallhack.cG+"\n"
		+ "§6Couleur B:§7 "+Wallhack.cB+"\n"
		+ "§6Couleur de ligne R:§7 "+Wallhack.clR+"\n"
		+ "§6Couleur de ligne G:§7 "+Wallhack.clG+"\n"
		+ "§6Couleur de ligne B:§7 "+Wallhack.clB+"\n"
		+ "§6Epaisseur de ligne:§7 "+Wallhack.width;
	}
	
	public void onRender3D() {
		for (Object o : var.mode.equals("Player") ? mc.theWorld.playerEntities : mc.theWorld.loadedEntityList) {
			if (o instanceof EntityLivingBase) {
				EntityLivingBase entity = (EntityLivingBase) o;     
	        	if (entity!=mc.thePlayer && (var.mode.equals(ModeType.Mob) ? !u.isPlayer(entity) : true))
	        		RenderUtils.drawEntityESP(u.getX(entity), u.getY(entity), u.getZ(entity), entity.width/2, entity.height+0.3F, cR, cG, cR, 0.11F, clR, clG, clB, 0.11F, width);
			}
        }
	}
}
