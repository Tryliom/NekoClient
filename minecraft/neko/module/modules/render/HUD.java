package neko.module.modules.render;

import neko.gui.InGameGui;
import neko.manager.ModuleManager;
import neko.module.Category;
import neko.module.Module;
import neko.utils.RenderUtils;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;

public class HUD extends Module {
	public static boolean fall=true;
	public static boolean coord=true;
	public static boolean fps=true;
	public static boolean item=true;
	public static boolean packet=true;
	public static boolean time=true;
	public static boolean select=false;
	public static boolean stuff=false;
	public static float cR=0.99F;
	public static float cG=0.33F;
	public static float cB=0.33F;
	public static float width=2F;

	public HUD() {
		super("HUD", -1, Category.RENDER);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "§6Coord: "+(HUD.coord ? "§aActivé" : "§cDésactivé")+"\n"
		+ "§6Fps: "+(HUD.fps ? "§aActivé" : "§cDésactivé")+"\n"
		+ "§6Xp: "+(HUD.fall ? "§aActivé" : "§cDésactivé")+"\n"
		+ "§6Item: "+(HUD.item ? "§aActivé" : "§cDésactivé")+"\n"
		+ "§6Time: "+(HUD.time ? "§aActivé" : "§cDésactivé")+"\n"
		+ "§6Select: "+(HUD.select ? "§aActivé" : "§cDésactivé")+"\n"
		+ "§6Couleur R:§7 "+HUD.cR+"\n"
		+ "§6Couleur G:§7 "+HUD.cG+"\n"
		+ "§6Couleur B:§7 "+HUD.cB+"\n"
		+ "§6Epaisseur bord:§7 "+HUD.width;
	}
	
	public void onRender3D() {		
		if (select && !u.isLock("--HUD select")) {
			try {
				BlockPos bp = mc.objectMouseOver.func_178782_a();
				if (bp!=null && !(mc.theWorld.getBlockState(bp).getBlock().getMaterial() == Material.air)) {
					RenderUtils.drawBlockESP(bp.getX() - mc.getRenderManager().renderPosX, bp.getY() - mc.getRenderManager().renderPosY, bp.getZ() - mc.getRenderManager().renderPosZ, cR, cG, cB, 0.12F, cR, cG, cB, 0.12F, width);
				} else if (bp==null && mc.pointedEntity!=null) {
					Entity entity = mc.pointedEntity;
					RenderUtils.drawEntityESP(u.getX(entity), u.getY(entity), u.getZ(entity), entity.width/2, entity.height+0.22F, cR, cG, cR, 0.11F, cR, cG, cB, 0.11F, width);
				}
			} catch (Exception e) {}
		}
	}
	
	public void onRender2D() {
		if (!mc.gameSettings.showDebugInfo)
			g.renderHUD();
	}
}
