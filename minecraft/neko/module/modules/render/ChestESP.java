package neko.module.modules.render;

import neko.module.Category;
import neko.module.Module;
import neko.utils.RenderUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityMobSpawner;

public class ChestESP extends Module {
	public static float cR=0.4F;
	public static float cG=0.6F;
	public static float cB=0.4F;
	public static float width=1F;
	public static float clR=0.4F;
	public static float clG=0.6F;
	public static float clB=0.4F;
	
	
	public ChestESP() {
		super("ChestESP", -1, Category.RENDER);
	}	
	
	public void onEnabled() {				
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "";
	}
	
	public void onRender3D() {		
		for (Object o : mc.theWorld.loadedTileEntityList) {
			if (o instanceof TileEntityChest || o instanceof TileEntityEnderChest || o instanceof TileEntityMobSpawner) {
				TileEntity chest = (TileEntity) o;     
				
    			RenderUtils.drawBlockESP(chest.getPos().getX()-mc.getRenderManager().renderPosX, chest.getPos().getY()-mc.getRenderManager().renderPosY, chest.getPos().getZ()-mc.getRenderManager().renderPosZ, cR, cG, cR, 0.11F, clR, clG, clB, 0.11F, width);
			}
        }
	}
}
