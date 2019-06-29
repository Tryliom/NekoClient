package neko.module.modules.params;

import neko.Client;
import neko.gui.InGameGui;
import neko.guicheat.clickgui.settings.Setting;
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
	public static double cR=99;
	public static double cG=33;
	public static double cB=33;
	public static float width=2F;

	public HUD() {
		super("HUD", -1, Category.PARAMS, false);
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
	
	@Override
	public void setup() {
			Client.getNeko().settingsManager.rSetting(new Setting("HUDCoord", this, this.coord));
			Client.getNeko().settingsManager.rSetting(new Setting("HUDFPS", this, this.fps));
			Client.getNeko().settingsManager.rSetting(new Setting("HUDXP", this, this.fall));
			Client.getNeko().settingsManager.rSetting(new Setting("HUDMS", this, this.item));
			Client.getNeko().settingsManager.rSetting(new Setting("HUDTime", this, this.time));
			Client.getNeko().settingsManager.rSetting(new Setting("HUDPacket", this, this.packet));
			Client.getNeko().settingsManager.rSetting(new Setting("HUDStuff", this, this.stuff));
			Client.getNeko().settingsManager.rSetting(new Setting("HUDSelect", this, this.select));
			Client.getNeko().settingsManager.rSetting(new Setting("HUDRed", this, this.cR, 0, 100, true));
			Client.getNeko().settingsManager.rSetting(new Setting("HUDGreen", this, this.cG, 0, 100, true));
			Client.getNeko().settingsManager.rSetting(new Setting("HUDBlue", this, this.cB, 0, 100, true));
	}
	
	public void onRender3D() {		
		if (select && !u.isLock("--HUD select")) {
			try {
				BlockPos bp = mc.objectMouseOver.func_178782_a();
				if (bp!=null && !(mc.theWorld.getBlockState(bp).getBlock().getMaterial() == Material.air)) {
					RenderUtils.drawBlockESP(bp.getX() - mc.getRenderManager().renderPosX, bp.getY() - mc.getRenderManager().renderPosY,
							bp.getZ() - mc.getRenderManager().renderPosZ, this.cR/100, this.cG/100, this.cB/100, 0.2, this.cR/100, this.cG/100, this.cB/100, 0.2, width);
				} else if (bp==null && mc.pointedEntity!=null) {
					Entity entity = mc.pointedEntity;
					RenderUtils.drawEntityESP(u.getX(entity), u.getY(entity), u.getZ(entity), entity.width/2, entity.height+0.22F,
							this.cR/100, this.cG/100, this.cR/100, 0.2, this.cR/100, this.cG/100, this.cB/100, 0.2, width);
				}
			} catch (Exception e) {}
		}
	}
	
	public void onRender2D() {
		if (!mc.gameSettings.showDebugInfo)
			g.renderHUD();
	}
}
